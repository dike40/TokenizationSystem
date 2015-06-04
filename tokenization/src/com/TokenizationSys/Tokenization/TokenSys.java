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
/*		//���û�1��Ϊ�µ�PAN����Token��ID&V����
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
	    int tokReqRiskSco = 0; //��ʱûʹ��
	    boolean addrMisIndic = true; 
	    //&10_192.168.1.1
	    String cardhldData = "01_Ҧˮ��&02_452601199002230658&03_15884478560&04_154417412@qq.com&05_324&06_�Ĵ�ʡ�ɶ���ۯ��&07_�Ĵ�ʡ�ɶ���ۯ��&08_100000&09_305&11_�Ĵ�ʡ�ɶ���ۯ��&12_������ͨ�ٶ�";
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
		
		//���û�1��Ϊ�µ�PAN����Token
		TokenProc tp = new TokenProc(panLen, pan, tokReqId);
	    tp.reqTok();*/
	    
/*		//���û�1��Ϊ�µ�PAN����Token��ID&V����
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
	    int tokReqRiskSco = 0; //��ʱûʹ��
	    boolean addrMisIndic = true; 
	    //&10_192.168.1.1
	    String cardhldData = "01_Ҧˮ��&02_452601199002230658&03_15884478560&04_154417412@qq.com&05_324&06_�Ĵ�ʡ�ɶ���ۯ��&07_�Ĵ�ʡ�ɶ���ۯ��&08_100000&09_305&11_�Ĵ�ʡ�ɶ���ۯ��&12_������ͨ�ٶ�";
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
		
		//���û�1��Ϊ�µ�PAN����Token
		TokenProc tp = new TokenProc(panLen, pan, tokReqId);
	    tp.reqTok();*/
	    
/*		//���û�1��Ϊtoken���µ�������
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
	    int tokReqRiskSco = 0; //��ʱûʹ��
	    boolean addrMisIndic = true; 
	    //&10_192.168.1.1
	    String cardhldData = "01_Ҧˮ��&02_452601199002230658&03_15884478560&04_154417412@qq.com&05_324&06_�Ĵ�ʡ�ɶ���ۯ��&07_�Ĵ�ʡ�ɶ���ۯ��&08_100000&09_305&11_�Ĵ�ʡ�ɶ���ۯ��&12_������ͨ�ٶ�";
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
		
		
	    //����DeTokenģ�飺
		String tokenTemp = "9443029498666150086";
		String posEntryModeTemp = "2";
		//��õ�ǰʱ��
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		String reqDate = sdf.format(now);//������12.5����
		//System.out.println("��ǰʱ�䣺" + reqDate);
		
		String str = tokenTemp+"&"+posEntryModeTemp+"&"+reqDate;//�ַ������͵�����
		byte[] content = null;// byte[]���͵�����
		byte[] tokCrypto = null;// byte[]���͵�����
		try {
			content = str.getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		GenerateSharekey gk = GenerateSharekey.getGS();
		gk.generKey();
		
		    tokCrypto = SecureAlgorithm.Encrypt(content, gk.getKeyBytes());
	
		    //System.out.println("tokCrypto��" + tokCrypto);
		
		    /*Token����ID*/
		    String tokReqId = "30715775744";
		    /*Token*/
		    String token = "9443029498666150086";
		    /*Token�ĳ���*/	
			int tokenLen = token.length();
		    /*Token����Ч����*/
		    int tokenExpDate = 1508;//�������ݿ�Ϊ1508
		    
			/*Token����tokCrypto��ǰ���Ѿ�����*/
			
			/*�豸��Ϣ*/
		    String devInfo = "SAMSUNG Galaxy S6";
			 /*�豸��Ϣ����*/
		    int devInfolen = devInfo.length();
		    /*�ֿ�����ݺ�*/
			String cardhldId = "452601199002230658";
			/*POS���뷽ʽ*/
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
