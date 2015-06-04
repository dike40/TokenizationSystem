package com.TokenizationSys.Tokenization.IDV;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONObject;
import com.TokenizationSys.DB.*;

/**
*ID&V���̴���ģ��
*/
public class Id_V {
	/*Token����ID*/
	String tokReqId = "";
	/*PAN*/
	private String pan = "";
	/*PAN�ĳ���*/	
	private int panLen = pan.length();
    /*PAN����Ч����*/
    int panExpDate = -1;
    
    /*Token*/
    String token = "";
    /*Token�ĳ���*/	
	int tokenLen = token.length();
    
    /*����Token��������*/
    int reqTokAssuLevel = -1;
    /*Tokenλ��*/
    int tokenLoc = -1;
    /*Э��*/
    int protocol = -1;
    /*�˻���֤���*/
    boolean accotVerReslt = false;
    /*�˻���֤����*/
    String accotVerRef = "";
    /*�˻���֤���ó���*/
    int accotVerRefLen = accotVerRef.length();
    /*Token���󷽷�������*/
    int tokReqRiskSco = -1;
    /*��ַ����ָʾ��*/
    boolean addrMisIndic = false;
    /*�ֿ�������*/
    String cardhldData = "";
    /*�ֿ������ݳ���*/
    int cardhldDataLen = cardhldData.length();
    /*�豸��Ϣ*/
    String devInfo = "";
	 /*�豸��Ϣ����*/
    int devInfolen = devInfo.length();
    /*Token���*/
	int tokDomRestrtContrl = -1;
	/*POS���뷽ʽ*/
    int posEntryMode = -1;
	/*�̻�ID*/
	String merchId = "";
	
	/*���ָô�ID&V���������������µ�Token�Ļ��Ǹ��µ��������
	 *true: ���µ�������
	 *false: �����µ�Token(Ĭ��)*/
	boolean isUpdate = false;
	
	
	
	/*�ֿ������ݲ�ֺ����´���Ķ���*/
	CardhldData chd = null;
	/*ID&Vģ��ͨ��Tokenizationϵͳ���͸������еĲ�ѯ������Ķ���*/
	MsgOfIdvToIssuer idToIs = null;
	/*������ͨ��Tokenizationϵͳ�ظ���ID&Vģ������ݶ���*/
	MsgOfIssuerToIdv isToId = null;
	/*ID&Vģ�����շ��ظ�Tokenizationϵͳ�Ľ������*/
	MsgOfIdvReslt idvReslt = null;
	/*Vault��Ķ������ڲ���Vault������*/
	Vault vault = null;
	
	/*ID&Vģ��Ĺ��캯��*/
	public Id_V(String tokReqId, int panLen, String pan, int panExpDate,
			int tokenLen, String token, 
			int reqTokAssuLevel, int tokenLoc,int protocol, 
			 boolean accotVerReslt, int accotVerRefLen,
			String accotVerRef, int tokReqRiskSco, boolean addrMisIndic,
			int cardhldDataLen, String cardhldData, int devInfolen,
			String devInfo, int tokDomRestrtContrl, int posEntryMode, String merchId,
			boolean isUpdate) {
		//super();
		this.tokReqId = tokReqId;
		this.panLen = panLen;
		this.pan = pan;
		this.panExpDate = panExpDate;
		this.tokenLen = tokenLen;
		this.token = token;
		this.reqTokAssuLevel = reqTokAssuLevel;
		this.tokenLoc = tokenLoc;
		this.protocol = protocol;
		this.accotVerReslt = accotVerReslt;
		this.accotVerRefLen = accotVerRefLen;
		this.accotVerRef = accotVerRef;
		this.tokReqRiskSco = tokReqRiskSco;
		this.addrMisIndic = addrMisIndic;
		this.cardhldDataLen = cardhldDataLen;
		this.cardhldData = cardhldData;
		this.devInfolen = devInfolen;
		this.devInfo = devInfo;
		this.tokDomRestrtContrl = tokDomRestrtContrl;
		this.posEntryMode = posEntryMode;
		this.merchId = merchId;
		this.isUpdate = isUpdate; 
		
		chd = new CardhldData(cardhldData);
		vault = Vault.getVault();
	}
	
	/*�ֿ������ݲ�ֺ����´������*/
	class CardhldData {
		/*����*/
		String accotName = "";
		/*��ݺ�*/
		String id = "";
		/*�ֻ���*/
		String mobileNo = "";
		/*��������*/
		String email = "";
		/*CVN/ CAV2 / CVC2 / CVV2 / CID*/
		String checkCode = "";
		/*�˵���ַ*/
		String billAddr = "";
		/*���͵�ַ*/
		String shipAddr = "";
		/*��������*/
		String postCode = "";
		
		/*��ʾ����Ӧ�����Ƿ�ͨ����֤�ı��*/
		boolean accotNameFlag = false;
		boolean idFlag = false;
		boolean mobileNoFlag = false;
		boolean emailFlag = false;
		boolean checkCodeFlag = false;
		boolean billAddrFlag = false;
		boolean shipAddrFlag = false;
		boolean postCodeFlag = false;
		
		/*�˻�ʹ��ʱ��*/
		String accotAge = "";
		/*IP��ַ*/
		String ipAddr = "";
		/*����λ��*/
		String geoLoc = "";
		/*������ͨ�ٶ�*/
		String transVelocity = "";
		
		/*�ֿ������ݲ�ֺ����´������Ĺ��캯��*/
		public CardhldData(String cardhldData) {
			/*������Ų�ֺ�������ֿ������ݵļ�ֵ��*/
			Map<String, String> cardhldDataMap = new HashMap<String, String>();
			/*�ԡ�&���͡�_�����Ų��ԭ�ȵĳֿ������ݣ�������cardhldDataMap��*/
			String[] cardhldDataArray=cardhldData.split("&"); 
		      for (int i = 0; i < cardhldDataArray.length; i++) {
		    	  String[] tempArray=cardhldDataArray[i].split("_");
		    	  cardhldDataMap.put(tempArray[0], tempArray[1]);
		      }
		      /*����ֺ�ĳֿ������ݸ�ֵ�������ֿ������ݱ���*/
		      accotName = cardhldDataMap.get("01");
		      id = cardhldDataMap.get("02");
			  mobileNo = cardhldDataMap.get("03");
			  email = cardhldDataMap.get("04");
			  checkCode = cardhldDataMap.get("05");
			  billAddr = cardhldDataMap.get("06");
			  shipAddr = cardhldDataMap.get("07");
			  postCode = cardhldDataMap.get("08");
			  accotAge = cardhldDataMap.get("09");
			  ipAddr = cardhldDataMap.get("10");
			  geoLoc = cardhldDataMap.get("11");
			  transVelocity = cardhldDataMap.get("12");
		}

	}
	
	/*���ø÷�������ID&V���̣������򷢿��в�ѯ������Ķ���*/
	public MsgOfIdvToIssuer reqIdv() {
		
		JSONObject js;
		CardholderInfoBuilder index = new CardholderInfoBuilder();
		JSONObject pkg;
		int responseCode;
		JSONObject content;
		
		/*���Ϊ����Token����*/
		if((!pan.equals("")) && (token.equals("")) && (isUpdate == false)) {
			index.put("pan", pan);
			index.put("trId", tokReqId);
			js = index.build();
			pkg = vault.isPan2TrExist(js);
			responseCode = pkg.getInt("responseCode");
			//content = pkg.getJSONObject("responseContent");
			if(ResponseCode.PAN_TRID_ALREAY_EXIST == responseCode) {
				/*
				 * �Ը���pan��trIdΪ��������ѯToken�Ƿ���ڣ�
				 * ������ɾ��������Ϣ��
				 * ����������Ϊ�µ�PAN�����µ�Token��ִ�У�
				 * û������IDV�жϡ�
				 * */
				pkg = vault.isTokenExpiry(js);
				responseCode = pkg.getInt("responseCode");
				if (responseCode == ResponseCode.TOKEN_ALREADY_EXPIRY) { //���Token����
					/*ɾ��*/
					pkg = vault.deleteMapping(js);
					responseCode = pkg.getInt("responseCode");
					if(ResponseCode.MAPPING_DELETE_SUCCESS == responseCode) {
						idToIs = verCardhldData();
					}
					else if (ResponseCode.MAPPING_DELETE_FAILURE == responseCode) {
						throw new RuntimeException("���������PAN���ڣ���Ӧ��Token��ʧЧ����ɾ��ʧ��");
					}
				}
				else if (responseCode == ResponseCode.TOKEN_NOT_EXPIRY) { //���Tokenû����
					throw new RuntimeException("Tokenδ���ڣ��ݲ�����������");
				}	
			}
			else if (ResponseCode.PAN_TRID_NOT_EXIST == responseCode) {
				idToIs = verCardhldData();
			}	
		}
		/*���Ϊ���µ����������*/
		else if((pan.equals("")) && (!token.equals("")) && (isUpdate == true)) {
			index.put("token", token);
			index.put("trId", tokReqId);
			js = index.build();
			pkg=vault.isToken2TrExist(js);
			responseCode=pkg.getInt("responseCode");
			//content=pkg.getJSONObject("responseContent");
			if(ResponseCode.TOKEN_TRID_ALREADY_EXIST == responseCode) {
				idToIs = verCardhldData();
			}
			else if (ResponseCode.TOKEN_TRID_NOT_EXIST == responseCode) {
				throw new RuntimeException("������µ��������Token�����ڣ�����������");
			}
		}
		else {
			throw new RuntimeException("ID&V�������ݲ���ȷ������������");
		}
		return idToIs;	
	}
	
	public MsgOfIdvToIssuer verCardhldData() {
		/*�跢���н�����֤�����ݵĳ���*/
		int tokAssuDataLen = 0;
		/*�跢���н�����֤������*/
		String tokAssuData = "";
		/*��ʱ�����跢���н�����֤�����ݵ��ַ���*/
		StringBuilder sb = new StringBuilder();
	       /*���Token�����Ѱ���֤���ֳֿ������ݣ��������֤�����ݱ����Ϊtrue*/
				if(true == accotVerReslt) {
					String[] accotVerRefArray = accotVerRef.split("&");
					for (int i = 0; i < accotVerRefArray.length; i++) {
				    	 switch (Integer.parseInt(accotVerRefArray[i])) {
				    	 case 1: chd.accotNameFlag = true;
				    	            break;
				    	 case 2: chd.idFlag = true;
		 	                        break;
				    	 case 3: chd.mobileNoFlag = true;
		 	                        break;
				    	 case 4: chd.emailFlag = true;
		 	                        break;
				    	 case 5: chd.checkCodeFlag = true;
		 	                        break;
				    	 case 6: chd.billAddrFlag = true;
		 	                        break;
				    	 case 7: chd.shipAddrFlag = true;
		 	                        break;
				    	 case 8: chd.postCodeFlag = true;
		 	                        break;
		 	             default: break;
				    	 }	
				    }
					
	                /*�ѽ��յ��ģ���δ�õ���֤�ĳֿ������ݰ���ʽƴ�ӳ��ַ���sb*/
					tokAssuDataAppend(chd, sb);
				}
				else {
					/*�ѽ��յ��ģ���δ�õ���֤�ĳֿ������ݰ���ʽƴ�ӳ��ַ���sb*/
					tokAssuDataAppend(chd, sb);
				}
				
				tokAssuData = sb.toString();
				tokAssuDataLen = tokAssuData.length();
				/*����System.out.println("tokAssuData:" + tokAssuData + " " + "tokAssuDataLen:" + tokAssuDataLen);*/
				MsgOfIdvToIssuer idToIs = new MsgOfIdvToIssuer(panLen, pan, panExpDate, tokAssuDataLen, tokAssuData);
				return idToIs;
	}
	
    /*���ܷ�������֤������÷�������ID&V�����Tokenizationϵͳ*/
	public MsgOfIdvReslt getIdvReslt(MsgOfIssuerToIdv isToId) {
		/*��ȡ��������֤���*/
		this.isToId = isToId;
		/*ָ��Token��������*/
		int assigTokAssuLevel = 0;
		/*ID&V����״̬*/
		boolean reqStatus = false;
		/*ԭ����볤��*/
		int reasonCodeLen = 0;
		/*ԭ�����*/
		String reasonCode = "";
		
		JSONObject js;
		CardholderInfoBuilder insert = new CardholderInfoBuilder();
		JSONObject pkg;
		int responseCode;
		//JSONObject content;
		
		/*�����������֤״̬Ϊ�ɹ�������֤�ɹ��ĳֿ������ݱ����Ϊtrue*/
		if(isToId.isReqStatus()){
			String[] tokAssuDataArray1 = idToIs.getTokAssuData().split("&");
			  for (int i = 0; i < tokAssuDataArray1.length; i++) {
				 // System.out.println("tokAssuDataArray1["+i+"]"+tokAssuDataArray1[i]);
		    	  String[] tokAssuDataArray2=tokAssuDataArray1[i].split("_");
		    	  
		    	  
		    	  for (int j = 0; j < tokAssuDataArray2.length; j += 2) {
		    		  // System.out.println("tokAssuDataArray2["+j+"]"+tokAssuDataArray2[j]);
				    	 switch (Integer.parseInt(tokAssuDataArray2[j])) {
				    	 case 1: chd.accotNameFlag = true;
				    	            break;
				    	 case 2: chd.idFlag = true;
		 	                        break;
				    	 case 3: chd.mobileNoFlag = true;
		 	                        break;
				    	 case 4: chd.emailFlag = true;
		 	                        break;
				    	 case 5: chd.checkCodeFlag = true;
		 	                        break;
				    	 case 6: chd.billAddrFlag = true;
		 	                        break;
				    	 case 7: chd.shipAddrFlag = true;
		 	                        break;
				    	 case 8: chd.postCodeFlag = true;
		 	                        break;
		 	             default: break;
				    	 }
					}   
		      }
		}
		else {
			/*�˴�Ϊһ���յĲ���*/
		}
		
		/*����getAccVerLevel()������ȡ�˻���֤����*/
		int accVerLevel = getAccVerLevel();
		/*����getRiskScore()������ȡ��������*/
		int riskScore = getRiskScore();
		/*����getTokAssuLevel()������ȡָ��Token��������*/
		assigTokAssuLevel = getTokAssuLevel(accVerLevel, riskScore);
		System.out.println("assigTokAssuLevel:" + assigTokAssuLevel);
		/*������Token��������Ϊ0����ָ��Token��������Ϊ0ʱ��ID&V��֤���ɹ�*/
		if((0 == assigTokAssuLevel) && (0 < reqTokAssuLevel)) {
			reqStatus = false;
			reasonCode = isToId.getReasonCode();
			
		}
		else {
			reqStatus = true;
			reasonCode = "";
		}
		
		/*����ǰ��Vaultд������*/
	    if (isUpdate) {   //���Ϊ���µ������������
	    	System.out.println("Starting ���µ�update Vault");
	    	insert.put("token", token);
	    	insert.put("trId", tokReqId);
	    	insert.put("tokenLocation", tokenLoc);
			insert.put("tokenReqLevel", reqTokAssuLevel);
			insert.put("tokenAssignedLevel", assigTokAssuLevel);
			insert.put("protocol", protocol);
			insert.put("accountVerResult", accotVerReslt);
			insert.put("accountVerRefLen", accotVerRefLen);
			insert.put("accountVerRef", accotVerRef);
			insert.put("accountVerResultTsp", accVerLevel);
			insert.put("riskScore", tokReqRiskSco);
			insert.put("riskScoreTsp", riskScore);
			insert.put("addrMismatchFlag", addrMisIndic);
			insert.put("cardholderNumber", chd.mobileNo);
			insert.put("cardholderMail", chd.email);
			insert.put("cardholderCheckCode", chd.checkCode);
			insert.put("cardholderBillAddr", chd.billAddr);
			insert.put("cardholderShipAddr", chd.shipAddr);
			insert.put("cardholderPostCode", chd.postCode);
			insert.put("accountAge", chd.accotAge);
			insert.put("tokenStatus", "SUSPEND");
			insert.put("extra2", "extra2");
			js = insert.build();
			System.out.println(insert.toString());
	    	pkg = vault.updateMapping(js);
	    	responseCode=pkg.getInt("responseCode");
	    	switch(responseCode) {
	    	case ResponseCode.TOKEN_TRID_NOT_EXIST:
	    		System.out.println("Ҫ���µ���Ŀ������");
	    		break;
	    	case ResponseCode.MAPPING_UPDATE_SUCCESS:
	    		System.out.println("Vault�и��µ�������ɹ�");
	    		break;
	    	case ResponseCode.MAPPING_UPDATE_FAILUER:
	    		System.out.println("Vault�и��µ�������ʧ��");
	    		break;
	    	}
	    	
	    }
	    else {   //���Ϊ����Token������
	    	System.out.println("Starting ����token��insert Vault");
			insert.put("pan", pan);
			insert.put("panLen", panLen);
			insert.put("panExpiryTime", panExpDate);
			insert.put("token", "-1");
			insert.put("tokenLen", "-1");
			insert.put("tokenExpiryTime", "-1");
			insert.put("tokenLocation", tokenLoc);
			insert.put("tokenReqLevel", reqTokAssuLevel);
			insert.put("tokenAssignedLevel", assigTokAssuLevel);
			insert.put("trId", tokReqId);
			insert.put("sharedKey", "-1");
			insert.put("protocol", protocol);
			insert.put("accountVerResult", accotVerReslt);
			insert.put("accountVerRefLen", accotVerRefLen);
			insert.put("accountVerRef", accotVerRef);
			insert.put("accountVerResultTsp", accVerLevel);
			insert.put("riskScore", tokReqRiskSco);
			insert.put("riskScoreTsp", riskScore);
			insert.put("addrMismatchFlag", addrMisIndic);
			insert.put("cardholderNumber", chd.mobileNo);
			insert.put("cardholderMail", chd.email);
			insert.put("cardholderCheckCode", chd.checkCode);
			insert.put("cardholderBillAddr", chd.billAddr);
			insert.put("cardholderShipAddr", chd.shipAddr);
			insert.put("cardholderPostCode", chd.postCode);
			insert.put("accountAge", chd.accotAge);
			insert.put("tokenStatus", "SUSPEND");
			insert.put("extra2", "extra2");
			js = insert.build();
			pkg = vault.insertMapping(js);
			responseCode = pkg.getInt("responseCode");
			switch(responseCode) {
	    	case ResponseCode.PAN_TRID_ALREAY_EXIST:
	    		System.out.println("����ʧ�ܣ�Ҫ�����ӳ���Ѿ�����");
	    		break;
	    	case ResponseCode.MAPPING_INSERT_SUCCESS:
	    		System.out.println("Vault�в���ӳ��ɹ�");
	    		break;
	    	case ResponseCode.MAPPING_INSERT_ERROR:
	    		System.out.println("Vault�в���ӳ��ʧ��");
	    		break;
	    	}
	    }
	    
		reasonCodeLen = reasonCode.length();
		MsgOfIdvReslt idvReslt = new MsgOfIdvReslt(assigTokAssuLevel, reqStatus, reasonCodeLen, reasonCode, tokenLen, token);
		return idvReslt;
		
	}
	
	/*���ø÷�����ȡָ��Token��������*/
	public int getTokAssuLevel(int accVerLevel, int riskScore) {
		int assigTokAssuLevel = 0;
		
		/*����getAccVerLevel()������ȡ�˻���֤����
		int accVerLevel = getAccVerLevel();
		����getRiskScore()������ȡ��������
		int riskScore = getRiskScore();*/
		
		if(17 <= reqTokAssuLevel) {
			System.out.println("�����벻����16����������Token��������");
		}
		else if(0 >= reqTokAssuLevel) { 
			assigTokAssuLevel = 0;
		}
		else {
			
			/*����4�ָ�ָ��Token�������������Ķ���*/
			Rater r1 = new Level13To16Rater();
			Rater r2 = new Level9To12Rater();
			Rater r3 = new Level5To8Rater();
			Rater r4 = new Level1To4Rater();
			/*Ӧ��ְ����ģʽ���������ǵĺ���������*/
			r1.setSuccessRater(r2);
			r2.setSuccessRater(r3);
			r3.setSuccessRater(r4);
			/*����Rating()������ָ��ָ��Token����������������*/
			r1.Rating(reqTokAssuLevel, accVerLevel, riskScore);
			/*��ȡ�������ָ��Token��������*/
			assigTokAssuLevel = r1.getAssigTokAssuLevel();
		}
		return assigTokAssuLevel;
	}
	
	/*���ø÷�����ȡ�˻���֤����*/
	public int getAccVerLevel() {
		int accVerLevel = 0;
		/*��ȡ��ǰʱ�䣬��ת����YYMM�ĸ�ʽ*/
		Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyMM");  
        String dateNowStr = sdf.format(now);
        Integer iDateNowStr = Integer.parseInt(dateNowStr);
        /*��δ����PAN����Ч���ڣ������˻���֤����Ϊ1*/
		if(panExpDate > iDateNowStr) {
			accVerLevel = 1;
			/*���˻����������֤����֤�ɹ��������˻���֤����Ϊ2*/
			if(chd.accotNameFlag && chd.idFlag) {
				accVerLevel = 2;
				/*���ֻ��Ż�Email��У������֤�ɹ��������˻���֤����Ϊ3*/
				if (chd.mobileNoFlag || chd.emailFlag || chd.checkCodeFlag)
					/*����if (false)*/
				{
					accVerLevel = 3;
					/*���˵���ַ���ʼĵ�ַ������������֤�ɹ��������˻���֤����Ϊ4*/
					if (chd.billAddrFlag || chd.shipAddrFlag || chd.postCodeFlag)
					{
						accVerLevel = 4;
					}
				}
			}
		}
		    System.out.println("accVerLevel:" + accVerLevel);
			return accVerLevel;
	}

	/*�ѽ��յ��ģ���δ�õ���֤�ĳֿ������ݰ���ʽƴ�ӳ��ַ���sb*/
	public void tokAssuDataAppend(CardhldData chd, StringBuilder sb) {
		
		if((null != chd.accotName) && (false == chd.accotNameFlag)) {
			sb = sb.append("01_"+ chd.accotName + "&");
		}
		if((null != chd.id) && (false == chd.idFlag)) {
			sb = sb.append("02_"+ chd.id + "&");
		}
		if((null != chd.mobileNo) && (false == chd.mobileNoFlag)) {
			sb = sb.append("03_"+ chd.mobileNo + "&");
		}
		if((null != chd.email) && (false == chd.emailFlag)) {
			sb = sb.append("04_"+ chd.email + "&");
		}
		if((null != chd.checkCode) && (false == chd.checkCodeFlag)) {
			sb = sb.append("05_"+ chd.checkCode + "&");
		}
		if((null != chd.billAddr) && (false == chd.billAddrFlag)) {
			sb = sb.append("06_"+ chd.billAddr + "&");
		}
		if((null != chd.shipAddr) && (false == chd.shipAddrFlag)) {
			sb = sb.append("07_"+ chd.shipAddr + "&");
		}
		if((null != chd.postCode) && (false == chd.postCodeFlag)) {
			sb = sb.append("08_"+ chd.postCode + "&");
		}
	}
	
	/*���ø÷�����ȡ��������*/
	public int getRiskScore() {
		int riskScore = 0;
		//Token���
		switch(tokDomRestrtContrl) {
		case 0: riskScore += 40;
        		break;
		case 1: riskScore += 20;
		        break;
		case 2: riskScore += 20;
		        break;
		case 3: riskScore += 0;
		        break;
		default: break;
		}
		
		//Tokenλ��
		switch(tokenLoc) {
		case 0: riskScore += 50;
        		break;
		case 1: riskScore += 35;
		        break;
		case 2: riskScore += 0;
		        break;
		case 3: riskScore += 45;
		        break;
		case 4: riskScore += 25;
				break;
		case 5: riskScore += 25;
        		break;
		default: break;
		}
		
		//Token���
		switch(posEntryMode) {
		case 0: riskScore += 30;
	    		break;
		case 1: riskScore += 21;
		        break;
		case 2: riskScore += 15;
		        break;
		case 3: riskScore += 27;
		        break;
		default: break;
		}
		
		//Э��
		switch(protocol) {
		case 0: riskScore += 30;
	    		break;
		case 1: riskScore += 21;
		        break;
		case 2: riskScore += 15;
		        break;
		default: break;
		}
		
		//��ַ����ָʾ��
		if(true == addrMisIndic) {
			riskScore += 0;
		}
		else if(false == addrMisIndic) {
			riskScore += 10;
		}
		
		//�˻�ʹ��ʱ��
		if(0 == Integer.parseInt(chd.accotAge)) {
			riskScore += 20;
		}
		else if((0 < Integer.parseInt(chd.accotAge)) && (Integer.parseInt(chd.accotAge)<= 180)) {
			riskScore += 12;
		}
		else if((180 < Integer.parseInt(chd.accotAge)) && (Integer.parseInt(chd.accotAge)<= 365)) {
			riskScore += 10;
		}
		else if((365 < Integer.parseInt(chd.accotAge)) && (Integer.parseInt(chd.accotAge)<= 1825)) {
			riskScore += 8;
		}
		else{
			riskScore += 0;
		}
		/*������id�ĳֿ����Ƿ����*/
        JSONObject js;
        CardholderInfoBuilder index = new CardholderInfoBuilder();
        index.put("cardholderId", chd.id);
        js = index.build();
        JSONObject pkg = vault.isCardholderExist(js);
		int responseCode = pkg.getInt("responseCode");
		//JSONObject content = pkg.getJSONObject("responseContent");
		/*�����ڣ� ����룬���ҳֿ�����Ϣ����Ϊƥ��*/
		if(ResponseCode.CARDHOLDER_NOT_EXIST == responseCode) {
			index.clear();
			index.put("cardholderId", chd.id);
			
			index.put("cardholderName", chd.accotName);
		
			index.put("cardholderIp", chd.ipAddr);
			index.put("cardholderLocation", chd.geoLoc);
			index.put("cardholderDeviceInfo", devInfo);
			index.put("extra1", "0");
			index.put("extra2", "0");
			js = index.build();
			//System.out.println(js.toString());
			
	        pkg = vault.insertCardholderInfo(js);
			responseCode = pkg.getInt("responseCode");
			switch(responseCode) {
	    	case ResponseCode.CARDHOLDER_INSERT_ERROR:
	    		System.out.println("����ֿ�����Ϣʧ��");
	    		break;
	    	case ResponseCode.CARDHOLDER_INSERT_SUCCESS:
	    		System.out.println("�ֿ�����Ϣ����ɹ�");
	    		/*�������ƥ�䴦��*/
		        riskScore += 0;
	    		break;
	    	case ResponseCode.CARDHOLDER_ALREADY_EXIST:
	    		System.out.println("����ʧ�ܣ��ֿ�����Ϣ�Ѵ���");
	    		break;
	    	}
		}
		/*���ڣ� ���ѯ������ƥ����Ϣ������*/
		else if(ResponseCode.CARDHOLDER_ALREADY_EXIST == responseCode) {
			//IP��ַ�Ƿ��볣��IP��ͬ
			index.clear();
			index.put("cardholderId", chd.id);
			index.put("cardholderIp", chd.ipAddr);
			js = index.build();
			pkg = vault.checkIp(js);
			responseCode = pkg.getInt("responseCode");
			
			if(ResponseCode.CARDHOLDER_IP_MATCH == responseCode) {
				riskScore += 0;
			}
			else if(ResponseCode.CARDHOLDER_IP_UNMATCH == responseCode) {
				riskScore += 10;
			}
			
			//����λ���Ƿ��볣�õ���λ����ͬ
			index.clear();
			index.put("cardholderId", chd.id);
			index.put("cardholderLocation", chd.geoLoc);
			js = index.build();
			pkg = vault.checkLocation(js);
			responseCode = pkg.getInt("responseCode");
			
			if(ResponseCode.CARDHOLDER_LOCATION_MATCH == responseCode) {
				riskScore += 0;
			}
			else if(ResponseCode.CARDHOLDER_LOCATION_UNMATCH == responseCode) {
				riskScore += 10;
			}
			
			//�豸��Ϣ�Ƿ��볣���豸��Ϣ��ͬ
			index.clear();
			index.put("cardholderId", chd.id);
			index.put("cardholderDeviceInfo", devInfo);
			js = index.build();
			pkg = vault.checkDevice(js);
			responseCode = pkg.getInt("responseCode");
			
			if(ResponseCode.CARDHOLDER_DEVICE_MATCH == responseCode) {
				riskScore += 0;
			}
			else if(ResponseCode.CARDHOLDER_DEVICE_UNMATCH == responseCode) {
				riskScore += 10;
			}
		}
		System.out.println("riskScore:" + riskScore);
		return riskScore;
	}
}
