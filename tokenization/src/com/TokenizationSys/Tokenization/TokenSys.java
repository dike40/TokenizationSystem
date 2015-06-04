package com.TokenizationSys.Tokenization;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONObject;
import com.TokenizationSys.DeTokenization.DeTokenProc;
import com.TokenizationSys.DeTokenization.MsgOfDeTokReslt;
import com.TokenizationSys.DeTokenization.SecureAlgorithm;



public class TokenSys {
	
	

	public static void main(String[] args) {
/*		//新用户1，为新的PAN申请Token的ID&V流程
	    String tokReqId = "30715775744";
	    String pan = "6227003819000500086";
		int panLen = pan.length();
	    int panExpDate = 1702;
	    String token = "";
	   	int tokenLen = token.length();
	    int reqTokAssuLevel = 7;
	    int tokenLoc = 1;
	    int protocol = 1;
	    boolean accotVerReslt = true;
	    String accotVerRef = "02&03&08";
	    int accotVerRefLen = accotVerRef.length();
	    int tokReqRiskSco = 0; //暂时没使用
	    boolean addrMisIndic = true; 
	    //&10_192.168.1.1
	    String cardhldData = "01_姚水河&02_452601199002230658&03_15884478560&04_154417412@qq.com&05_324&06_四川省成都市郫县&07_四川省成都市郫县&08_100000&09_305&11_四川省成都市郫县&12_交易流通速度";
	    int cardhldDataLen = cardhldData.length();
	    String devInfo = "SAMSUNG Galaxy S6";
	    int devInfolen = devInfo.length();
		int tokDomRestrtContrl = 2;
	    int posEntryMode = 2;
		String merchId = "12345678910";
		boolean isUpdate = false;
	
		Id_V idv = new Id_V(tokReqId, panLen, pan, panExpDate,
					tokenLen, token, 
					reqTokAssuLevel, tokenLoc,protocol, 
					accotVerReslt, accotVerRefLen,
					accotVerRef, tokReqRiskSco, addrMisIndic,
					cardhldDataLen, cardhldData, devInfolen,
					devInfo, tokDomRestrtContrl, posEntryMode, merchId,
					isUpdate);
		
		MsgOfIdvToIssuer idToIs = idv.reqIdv();
	
		boolean reqStatus = true;
		String reasonCode = "";
		int reasonCodeLen = 0;
		
		MsgOfIssuerToIdv isToId = new MsgOfIssuerToIdv(reqStatus, reasonCodeLen, reasonCode);
		MsgOfIdvReslt idvReslt = idv.getIdvReslt(isToId);
		
		//新用户1，为新的PAN申请Token
		TokenProc tp = new TokenProc(panLen, pan, tokReqId);
	    tp.reqTok();*/
	    
/*		//老用户1，为新的PAN申请Token的ID&V流程
	    String tokReqId = "30715775744";
	    String pan = "4227882930490199384";
		int panLen = pan.length();
	    int panExpDate = 1702;
	    String token = "";
	   	int tokenLen = token.length();
	    int reqTokAssuLevel = 7;
	    int tokenLoc = 1;
	    int protocol = 1;
	    boolean accotVerReslt = true;
	    String accotVerRef = "02&03&08";
	    int accotVerRefLen = accotVerRef.length();
	    int tokReqRiskSco = 0; //暂时没使用
	    boolean addrMisIndic = true; 
	    //&10_192.168.1.1
	    String cardhldData = "01_姚水河&02_452601199002230658&03_15884478560&04_154417412@qq.com&05_324&06_四川省成都市郫县&07_四川省成都市郫县&08_100000&09_305&11_四川省成都市郫县&12_交易流通速度";
	    int cardhldDataLen = cardhldData.length();
	    String devInfo = "SAMSUNG Galaxy S6";
	    int devInfolen = devInfo.length();
		int tokDomRestrtContrl = 2;
	    int posEntryMode = 2;
		String merchId = "12345678910";
		boolean isUpdate = false; 
	
		Id_V idv = new Id_V(tokReqId, panLen, pan, panExpDate,
					tokenLen, token, 
					reqTokAssuLevel, tokenLoc,protocol, 
					accotVerReslt, accotVerRefLen,
					accotVerRef, tokReqRiskSco, addrMisIndic,
					cardhldDataLen, cardhldData, devInfolen,
					devInfo, tokDomRestrtContrl, posEntryMode, merchId,
					isUpdate);
		
		MsgOfIdvToIssuer idToIs = idv.reqIdv();
	
		boolean reqStatus = true;
		String reasonCode = "";
		int reasonCodeLen = 0;
		
		MsgOfIssuerToIdv isToId = new MsgOfIssuerToIdv(reqStatus, reasonCodeLen, reasonCode);
		MsgOfIdvReslt idvReslt = idv.getIdvReslt(isToId);
		
		//老用户1，为新的PAN申请Token
		TokenProc tp = new TokenProc(panLen, pan, tokReqId);
	    tp.reqTok();*/
	    
/*		//老用户1，为token更新担保级别
	    String tokReqId = "30715775744";
	    String pan = "";//"4227882930490199384";
		int panLen = pan.length();
	    int panExpDate = 1702;
	    String token = "8294819718063020086";
	   	int tokenLen = token.length();
	    int reqTokAssuLevel = 14;
	    int tokenLoc = 1;
	    int protocol = 1;
	    boolean accotVerReslt = true;
	    String accotVerRef = "02&03&08";
	    int accotVerRefLen = accotVerRef.length();
	    int tokReqRiskSco = 0; //暂时没使用
	    boolean addrMisIndic = true; 
	    //&10_192.168.1.1
	    String cardhldData = "01_姚水河&02_452601199002230658&03_15884478560&04_154417412@qq.com&05_324&06_四川省成都市郫县&07_四川省成都市郫县&08_100000&09_305&11_四川省成都市郫县&12_交易流通速度";
	    int cardhldDataLen = cardhldData.length();
	    String devInfo = "SAMSUNG Galaxy S6";
	    int devInfolen = devInfo.length();
		int tokDomRestrtContrl = 2;
	    int posEntryMode = 2;
		String merchId = "12345678910";
		boolean isUpdate = true; 
	
		Id_V idv = new Id_V(tokReqId, panLen, pan, panExpDate,
					tokenLen, token, 
					reqTokAssuLevel, tokenLoc,protocol, 
					accotVerReslt, accotVerRefLen,
					accotVerRef, tokReqRiskSco, addrMisIndic,
					cardhldDataLen, cardhldData, devInfolen,
					devInfo, tokDomRestrtContrl, posEntryMode, merchId,
					isUpdate);
		
		MsgOfIdvToIssuer idToIs = idv.reqIdv();
	
		boolean reqStatus = true;
		String reasonCode = "";
		int reasonCodeLen = 0;
		
		MsgOfIssuerToIdv isToId = new MsgOfIssuerToIdv(reqStatus, reasonCodeLen, reasonCode);
		MsgOfIdvReslt idvReslt = idv.getIdvReslt(isToId);*/
		
		
	    //测试DeToken模块：
		String tokenTemp = "9443029498666150086";
		String posEntryModeTemp = "2";
		//获得当前时间
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		String reqDate = sdf.format(now);//必须是12.5秒内
		//System.out.println("当前时间：" + reqDate);
		
		String str = tokenTemp+"&"+posEntryModeTemp+"&"+reqDate;//字符串类型的明文
		byte[] content = null;// byte[]类型的明文
		byte[] tokCrypto = null;// byte[]类型的密文
		try {
			content = str.getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		GenerateSharekey gk = GenerateSharekey.getGS();
		gk.generKey();
		
		    tokCrypto = SecureAlgorithm.Encrypt(content, gk.getKeyBytes());
	
		    //System.out.println("tokCrypto：" + tokCrypto);
		
		    /*Token请求方ID*/
		    String tokReqId = "30715775744";
		    /*Token*/
		    String token = "9443029498666150086";
		    /*Token的长度*/	
			int tokenLen = token.length();
		    /*Token的有效日期*/
		    int tokenExpDate = 1508;//改了数据库为1508
		    
			/*Token密码tokCrypto，前面已经声明*/
			
			/*设备信息*/
		    String devInfo = "SAMSUNG Galaxy S6";
			 /*设备信息长度*/
		    int devInfolen = devInfo.length();
		    /*持卡人身份号*/
			String cardhldId = "452601199002230658";
			/*POS进入方式*/
		    int posEntryMode = 2;
		DeTokenProc drp = new DeTokenProc(tokReqId, tokenLen,  token,
				 tokenExpDate, tokCrypto, devInfolen, 
				 devInfo,  cardhldId,  posEntryMode);
		MsgOfDeTokReslt dtr = drp.reqDeTok();
		System.out.println("Detoken---->reqStatus: " + dtr.isReqStatus());
		System.out.println("Detoken---->reasonCode: " + dtr.getReasonCode());
		System.out.println("Detoken---->reasonCodeLen: " + dtr.getReasonCodeLen());
		System.out.println("Detoken---->pan: " + dtr.getPan());
		System.out.println("Detoken---->panLen: " + dtr.getPanLen());
		System.out.println("Detoken---->panExpDate: " + dtr.getPanExpDate());
		
		JSONObject jo=new JSONObject();
		jo.put("tokenStr", token);
		jo.put("trId", tokReqId);
		jo.put("tokenExpDate", tokenExpDate);
		jo.put("devInfo", devInfo);
		jo.put("cardhldId", cardhldId);
		jo.put("tokCrypto", new String(tokCrypto));
		jo.put("posEntryMode", posEntryMode);
		System.out.print(jo.toString());

	}
	



}
