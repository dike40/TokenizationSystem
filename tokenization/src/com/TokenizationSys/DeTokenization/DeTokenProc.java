package com.TokenizationSys.DeTokenization;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.codec.binary.Base64;

import net.sf.json.JSONObject;
import com.TokenizationSys.DB.CardholderInfoBuilder;
import com.TokenizationSys.DB.ResponseCode;
import com.TokenizationSys.DB.Vault;
import com.TokenizationSys.Utils.hexAndString;

/**
* De-Tokenization过程处理模块
*/
public class DeTokenProc {
	
	public static final long TIMETOLERANCE = (long) (1000*1200);  //12.5秒的容忍度（参考银联）
	/*Token请求方ID*/
    String tokReqId = "";
    /*Token*/
    String token = "";
    /*Token的长度*/	
	int tokenLen = token.length();
    /*Token的有效日期*/
    int tokenExpDate = -1;
	/*Token密码*/
	byte[] tokCrypto = null;
	/*设备信息*/
    String devInfo = "";
	 /*设备信息长度*/
    int devInfolen = devInfo.length();
    /*持卡人身份号*/
	String cardhldId = "";
	/*POS进入方式*/
    int posEntryMode = -1;
    
    
    
    /*De-Tokenization模块最终返回给Tokenization系统的结果数据*/
    MsgOfDeTokReslt dtr = null;
    /*Vault类的对象，用于操作Vault中数据*/
	Vault vault = null;
    
    /*De-Tokenization模块的构造函数*/
	public DeTokenProc(String tokReqId, int tokenLen, String token,
			int tokenExpDate, byte[] tokCrypto, int devInfolen, 
			String devInfo, String cardhldId, int posEntryMode) {
		this.tokReqId = tokReqId;
		this.tokenLen = tokenLen;
		this.token = token;
		this.tokenExpDate = tokenExpDate;
		this.tokCrypto = tokCrypto;
		this.devInfolen = devInfolen;
		this.devInfo = devInfo;
		this.cardhldId = cardhldId;
		this.posEntryMode = posEntryMode;
		vault = Vault.getVault();
	}
    
	/*调用该方法请求De-Tokenization过程，返回De-Tokenization结果给Tokenization系统*/
	public MsgOfDeTokReslt reqDeTok() {	
		/*请求状态*/
		boolean reqStatus = false;
		/*原因代码*/
		String reasonCode = "";
		/*原因代码长度*/
		int reasonCodeLen = reasonCode.length();
		/*PAN*/
	    String pan = "";
		/*PAN的长度*/	
		int panLen = pan.length();
	    /*PAN的有效日期*/
	    int panExpDate = -1;
	    
	    
	    JSONObject js;
		CardholderInfoBuilder index = new CardholderInfoBuilder();
		JSONObject pkg;
		int responseCode;
		JSONObject content;
	    index.put("token", token);
		index.put("trId", tokReqId);
		js = index.build();
		pkg = vault.isToken2TrExist(js);
		responseCode = pkg.getInt("responseCode");
		/*Vault中是否有以接收到的token,trId为索引的项，有则继续，没有则填写错误原因并退出*/
		if(ResponseCode.TOKEN_TRID_ALREADY_EXIST == responseCode) {
			index.clear();
			index.put("token", token);
			index.put("trId", tokReqId);
			index.put("tokenExpiryTime", tokenExpDate);
			js = index.build();
			pkg=vault.checkTokenExpiryTime(js);
			responseCode = pkg.getInt("responseCode");
			if(ResponseCode.TOKEN_EXPIRY_TIME_MATCH == responseCode) {
				/*获取当前时间，并转换成YYMM的格式*/
				Date now = new Date();
		        SimpleDateFormat sdf = new SimpleDateFormat("yyMM");  
		        String dateNowStr = sdf.format(now);
		        Integer iDateNowStr = Integer.parseInt(dateNowStr);
		        /*Token有效日期与此刻时间对比是否超时，没有则继续，超时则填写错误原因并退出*/
		        if(tokenExpDate > iDateNowStr) {
		        	index.clear();
					index.put("cardholderId", cardhldId);
					index.put("cardholderDeviceInfo", devInfo);
					js = index.build();
		        	pkg=vault.checkDevice(js);
					responseCode = pkg.getInt("responseCode");
					if(ResponseCode.CARDHOLDER_DEVICE_MATCH == responseCode) {
						/*获取与用户的共享密钥，将Token密码解密*/
						index.clear();
	        			index.put("token", token);
	        			index.put("trId", tokReqId);
	        			js = index.build();
	        			pkg=vault.getSharedKey(js);
						responseCode = pkg.getInt("responseCode");
						if(ResponseCode.SUCCESS == responseCode) {
							content = pkg.getJSONObject("responseContent");
							String sharedKeyStr = content.getString("sharedKey");//获取sharedKey
							
			        		byte[] sharedKey = hexAndString.hexToBytes(sharedKeyStr);
			        		SecureAlgorithm sa = new SecureAlgorithm();
			        		byte[] originBytes = sa.DecryptToBytes(tokCrypto, sharedKey);
			        		
			        		String originStr =null;
			        		try {
			        			originStr = new String(originBytes, "utf-8");
			        		} catch (UnsupportedEncodingException e) {
			        			e.printStackTrace();
			        		}
			        		//System.out.println(originStr);
			        		
			        		/*将得到的tokCrypto的明文拆分，并于数据库内容对比*/			        		
			        		String[] originStrArray = originStr.split("&"); 
			        		String tokenTemp = originStrArray[0];
			        		String posEntryModeTemp = originStrArray[1];
			        		
			        		String reqDate = originStrArray[2];
			        		
			        		
			        		
			        		/*如果token、POS进入方式与数据库中对应，时间容忍度在范围内，则继续，否则填写错误原因并退出*/
			        		if(tokenTemp.equals(token) && posEntryModeTemp.equals(posEntryMode + "")
			        		  && canBeTolerated(reqDate, TIMETOLERANCE)) {
			        			
			        			/*请求状态*/
			        			reqStatus = true;
			        			/*原因代码*/
			        			reasonCode = "";
			        			/*查询Vault获得PAN*/
			        			pkg=vault.getPanAndPanExpiryTime(js);
								responseCode = pkg.getInt("responseCode");
			        			if(ResponseCode.SUCCESS == responseCode) {
			        				content = pkg.getJSONObject("responseContent");
			        				pan = content.getString("pan");
			        				panLen = pan.length();
			        				panExpDate = Integer.parseInt(content.getString("panExpiryTime")); 
			        			}
			        			else {
			        				reasonCode = "从Vault中获取Pan和panExpDate失败" ;
			        			}
			        		}
			        		else {
			        			reasonCode = "Token密码有误";
			        		}	
						}
						else {
							reasonCode = "获取sharedKey失败";
						}
					}
					else {
						reasonCode = "设备信息有误";
					}	
		        }
		        else {
		        	reasonCode = "已超过Token有效日期";
		        }
			}
			else {
				reasonCode = "Token有效日期不匹配";
			}
		}
		else{
			reasonCode = "此Token对应的Pan不存在，请重新输入";
		}
		//System.out.println(reasonCode);
		reasonCodeLen = reasonCode.length();
		dtr = new MsgOfDeTokReslt(reqStatus, reasonCodeLen, reasonCode,
				panLen, pan, panExpDate);
	    return dtr;

	}
	

	/*对比时间date与此刻时间的差距是否在容忍度内*/
	public boolean canBeTolerated(String date, long timeTolerance) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date dt = null;
	    Date now = new Date();
		try {
			dt = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		} 
        if (now.getTime() - dt.getTime() <= timeTolerance) {
            System.out.println("时间延迟在容忍范围内");
            return true;
        } 
        else {
        	System.out.println("时间延迟不在容忍范围内！");
            return false;
        }
	}
}
