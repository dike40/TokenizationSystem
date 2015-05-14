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
* De-Tokenization���̴���ģ��
*/
public class DeTokenProc {
	
	public static final long TIMETOLERANCE = (long) (1000*1200);  //12.5������̶ȣ��ο�������
	/*Token����ID*/
    String tokReqId = "";
    /*Token*/
    String token = "";
    /*Token�ĳ���*/	
	int tokenLen = token.length();
    /*Token����Ч����*/
    int tokenExpDate = -1;
	/*Token����*/
	byte[] tokCrypto = null;
	/*�豸��Ϣ*/
    String devInfo = "";
	 /*�豸��Ϣ����*/
    int devInfolen = devInfo.length();
    /*�ֿ�����ݺ�*/
	String cardhldId = "";
	/*POS���뷽ʽ*/
    int posEntryMode = -1;
    
    
    
    /*De-Tokenizationģ�����շ��ظ�Tokenizationϵͳ�Ľ������*/
    MsgOfDeTokReslt dtr = null;
    /*Vault��Ķ������ڲ���Vault������*/
	Vault vault = null;
    
    /*De-Tokenizationģ��Ĺ��캯��*/
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
    
	/*���ø÷�������De-Tokenization���̣�����De-Tokenization�����Tokenizationϵͳ*/
	public MsgOfDeTokReslt reqDeTok() {	
		/*����״̬*/
		boolean reqStatus = false;
		/*ԭ�����*/
		String reasonCode = "";
		/*ԭ����볤��*/
		int reasonCodeLen = reasonCode.length();
		/*PAN*/
	    String pan = "";
		/*PAN�ĳ���*/	
		int panLen = pan.length();
	    /*PAN����Ч����*/
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
		/*Vault���Ƿ����Խ��յ���token,trIdΪ����������������û������д����ԭ���˳�*/
		if(ResponseCode.TOKEN_TRID_ALREADY_EXIST == responseCode) {
			index.clear();
			index.put("token", token);
			index.put("trId", tokReqId);
			index.put("tokenExpiryTime", tokenExpDate);
			js = index.build();
			pkg=vault.checkTokenExpiryTime(js);
			responseCode = pkg.getInt("responseCode");
			if(ResponseCode.TOKEN_EXPIRY_TIME_MATCH == responseCode) {
				/*��ȡ��ǰʱ�䣬��ת����YYMM�ĸ�ʽ*/
				Date now = new Date();
		        SimpleDateFormat sdf = new SimpleDateFormat("yyMM");  
		        String dateNowStr = sdf.format(now);
		        Integer iDateNowStr = Integer.parseInt(dateNowStr);
		        /*Token��Ч������˿�ʱ��Ա��Ƿ�ʱ��û�����������ʱ����д����ԭ���˳�*/
		        if(tokenExpDate > iDateNowStr) {
		        	index.clear();
					index.put("cardholderId", cardhldId);
					index.put("cardholderDeviceInfo", devInfo);
					js = index.build();
		        	pkg=vault.checkDevice(js);
					responseCode = pkg.getInt("responseCode");
					if(ResponseCode.CARDHOLDER_DEVICE_MATCH == responseCode) {
						/*��ȡ���û��Ĺ�����Կ����Token�������*/
						index.clear();
	        			index.put("token", token);
	        			index.put("trId", tokReqId);
	        			js = index.build();
	        			pkg=vault.getSharedKey(js);
						responseCode = pkg.getInt("responseCode");
						if(ResponseCode.SUCCESS == responseCode) {
							content = pkg.getJSONObject("responseContent");
							String sharedKeyStr = content.getString("sharedKey");//��ȡsharedKey
							
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
			        		
			        		/*���õ���tokCrypto�����Ĳ�֣��������ݿ����ݶԱ�*/			        		
			        		String[] originStrArray = originStr.split("&"); 
			        		String tokenTemp = originStrArray[0];
			        		String posEntryModeTemp = originStrArray[1];
			        		
			        		String reqDate = originStrArray[2];
			        		
			        		
			        		
			        		/*���token��POS���뷽ʽ�����ݿ��ж�Ӧ��ʱ�����̶��ڷ�Χ�ڣ��������������д����ԭ���˳�*/
			        		if(tokenTemp.equals(token) && posEntryModeTemp.equals(posEntryMode + "")
			        		  && canBeTolerated(reqDate, TIMETOLERANCE)) {
			        			
			        			/*����״̬*/
			        			reqStatus = true;
			        			/*ԭ�����*/
			        			reasonCode = "";
			        			/*��ѯVault���PAN*/
			        			pkg=vault.getPanAndPanExpiryTime(js);
								responseCode = pkg.getInt("responseCode");
			        			if(ResponseCode.SUCCESS == responseCode) {
			        				content = pkg.getJSONObject("responseContent");
			        				pan = content.getString("pan");
			        				panLen = pan.length();
			        				panExpDate = Integer.parseInt(content.getString("panExpiryTime")); 
			        			}
			        			else {
			        				reasonCode = "��Vault�л�ȡPan��panExpDateʧ��" ;
			        			}
			        		}
			        		else {
			        			reasonCode = "Token��������";
			        		}	
						}
						else {
							reasonCode = "��ȡsharedKeyʧ��";
						}
					}
					else {
						reasonCode = "�豸��Ϣ����";
					}	
		        }
		        else {
		        	reasonCode = "�ѳ���Token��Ч����";
		        }
			}
			else {
				reasonCode = "Token��Ч���ڲ�ƥ��";
			}
		}
		else{
			reasonCode = "��Token��Ӧ��Pan�����ڣ�����������";
		}
		//System.out.println(reasonCode);
		reasonCodeLen = reasonCode.length();
		dtr = new MsgOfDeTokReslt(reqStatus, reasonCodeLen, reasonCode,
				panLen, pan, panExpDate);
	    return dtr;

	}
	

	/*�Ա�ʱ��date��˿�ʱ��Ĳ���Ƿ������̶���*/
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
            System.out.println("ʱ���ӳ������̷�Χ��");
            return true;
        } 
        else {
        	System.out.println("ʱ���ӳٲ������̷�Χ�ڣ�");
            return false;
        }
	}
}
