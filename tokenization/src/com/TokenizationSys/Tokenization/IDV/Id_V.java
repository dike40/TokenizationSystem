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
*ID&V过程处理模块
*/
public class Id_V {
	/*Token请求方ID*/
	String tokReqId = "";
	/*PAN*/
	private String pan = "";
	/*PAN的长度*/	
	private int panLen = pan.length();
    /*PAN的有效日期*/
    int panExpDate = -1;
    
    /*Token*/
    String token = "";
    /*Token的长度*/	
	int tokenLen = token.length();
    
    /*请求Token担保级别*/
    int reqTokAssuLevel = -1;
    /*Token位置*/
    int tokenLoc = -1;
    /*协议*/
    int protocol = -1;
    /*账户验证结果*/
    boolean accotVerReslt = false;
    /*账户验证引用*/
    String accotVerRef = "";
    /*账户验证引用长度*/
    int accotVerRefLen = accotVerRef.length();
    /*Token请求方风险评分*/
    int tokReqRiskSco = -1;
    /*地址错配指示器*/
    boolean addrMisIndic = false;
    /*持卡人数据*/
    String cardhldData = "";
    /*持卡人数据长度*/
    int cardhldDataLen = cardhldData.length();
    /*设备信息*/
    String devInfo = "";
	 /*设备信息长度*/
    int devInfolen = devInfo.length();
    /*Token域控*/
	int tokDomRestrtContrl = -1;
	/*POS进入方式*/
    int posEntryMode = -1;
	/*商户ID*/
	String merchId = "";
	
	/*区分该次ID&V请求是用来生成新的Token的或是更新担保级别的
	 *true: 更新担保级别
	 *false: 生成新的Token(默认)*/
	boolean isUpdate = false;
	
	
	
	/*持卡人数据拆分后重新打包的对象*/
	CardhldData chd = null;
	/*ID&V模块通过Tokenization系统发送给发卡行的查询报文类的对象*/
	MsgOfIdvToIssuer idToIs = null;
	/*发卡行通过Tokenization系统回复给ID&V模块的数据对象*/
	MsgOfIssuerToIdv isToId = null;
	/*ID&V模块最终返回给Tokenization系统的结果数据*/
	MsgOfIdvReslt idvReslt = null;
	/*Vault类的对象，用于操作Vault中数据*/
	Vault vault = null;
	
	/*ID&V模块的构造函数*/
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
	
	/*持卡人数据拆分后重新打包的类*/
	class CardhldData {
		/*姓名*/
		String accotName = "";
		/*身份号*/
		String id = "";
		/*手机号*/
		String mobileNo = "";
		/*电子邮箱*/
		String email = "";
		/*CVN/ CAV2 / CVC2 / CVV2 / CID*/
		String checkCode = "";
		/*账单地址*/
		String billAddr = "";
		/*寄送地址*/
		String shipAddr = "";
		/*邮政编码*/
		String postCode = "";
		
		/*表示各对应数据是否通过验证的标记*/
		boolean accotNameFlag = false;
		boolean idFlag = false;
		boolean mobileNoFlag = false;
		boolean emailFlag = false;
		boolean checkCodeFlag = false;
		boolean billAddrFlag = false;
		boolean shipAddrFlag = false;
		boolean postCodeFlag = false;
		
		/*账户使用时间*/
		String accotAge = "";
		/*IP地址*/
		String ipAddr = "";
		/*地理位置*/
		String geoLoc = "";
		/*交易流通速度*/
		String transVelocity = "";
		
		/*持卡人数据拆分后重新打包的类的构造函数*/
		public CardhldData(String cardhldData) {
			/*用来存放拆分后的序号与持卡人数据的键值对*/
			Map<String, String> cardhldDataMap = new HashMap<String, String>();
			/*以“&”和“_”符号拆分原先的持卡人数据，并存入cardhldDataMap中*/
			String[] cardhldDataArray=cardhldData.split("&"); 
		      for (int i = 0; i < cardhldDataArray.length; i++) {
		    	  String[] tempArray=cardhldDataArray[i].split("_");
		    	  cardhldDataMap.put(tempArray[0], tempArray[1]);
		      }
		      /*将拆分后的持卡人数据赋值给各个持卡人数据变量*/
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
	
	/*调用该方法请求ID&V过程，返回向发卡行查询报文类的对象*/
	public MsgOfIdvToIssuer reqIdv() {
		
		JSONObject js;
		CardholderInfoBuilder index = new CardholderInfoBuilder();
		JSONObject pkg;
		int responseCode;
		JSONObject content;
		
		/*如果为申请Token过程*/
		if((!pan.equals("")) && (token.equals("")) && (isUpdate == false)) {
			index.put("pan", pan);
			index.put("trId", tokReqId);
			js = index.build();
			pkg = vault.isPan2TrExist(js);
			responseCode = pkg.getInt("responseCode");
			//content = pkg.getJSONObject("responseContent");
			if(ResponseCode.PAN_TRID_ALREAY_EXIST == responseCode) {
				/*
				 * 以给定pan和trId为索引，查询Token是否过期，
				 * 过期则删除整个信息，
				 * 接下来当做为新的PAN申请新的Token来执行，
				 * 没过期则IDV中断。
				 * */
				pkg = vault.isTokenExpiry(js);
				responseCode = pkg.getInt("responseCode");
				if (responseCode == ResponseCode.TOKEN_ALREADY_EXPIRY) { //如果Token过期
					/*删除*/
					pkg = vault.deleteMapping(js);
					responseCode = pkg.getInt("responseCode");
					if(ResponseCode.MAPPING_DELETE_SUCCESS == responseCode) {
						idToIs = verCardhldData();
					}
					else if (ResponseCode.MAPPING_DELETE_FAILURE == responseCode) {
						throw new RuntimeException("所以申请的PAN存在，对应的Token已失效，但删除失败");
					}
				}
				else if (responseCode == ResponseCode.TOKEN_NOT_EXPIRY) { //如果Token没过期
					throw new RuntimeException("Token未过期，暂不能重新申请");
				}	
			}
			else if (ResponseCode.PAN_TRID_NOT_EXIST == responseCode) {
				idToIs = verCardhldData();
			}	
		}
		/*如果为更新担保级别过程*/
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
				throw new RuntimeException("申请更新担保级别的Token不存在，请重新输入");
			}
		}
		else {
			throw new RuntimeException("ID&V请求内容不正确，请重新输入");
		}
		return idToIs;	
	}
	
	public MsgOfIdvToIssuer verCardhldData() {
		/*需发卡行进行验证的数据的长度*/
		int tokAssuDataLen = 0;
		/*需发卡行进行验证的数据*/
		String tokAssuData = "";
		/*暂时保存需发卡行进行验证的数据的字符串*/
		StringBuilder sb = new StringBuilder();
	       /*如果Token请求方已帮验证部分持卡人数据，则给已验证的数据标记设为true*/
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
					
	                /*把接收到的，但未得到验证的持卡人数据按格式拼接成字符串sb*/
					tokAssuDataAppend(chd, sb);
				}
				else {
					/*把接收到的，但未得到验证的持卡人数据按格式拼接成字符串sb*/
					tokAssuDataAppend(chd, sb);
				}
				
				tokAssuData = sb.toString();
				tokAssuDataLen = tokAssuData.length();
				/*测试System.out.println("tokAssuData:" + tokAssuData + " " + "tokAssuDataLen:" + tokAssuDataLen);*/
				MsgOfIdvToIssuer idToIs = new MsgOfIdvToIssuer(panLen, pan, panExpDate, tokAssuDataLen, tokAssuData);
				return idToIs;
	}
	
    /*接受发卡行验证结果，该方法返回ID&V结果给Tokenization系统*/
	public MsgOfIdvReslt getIdvReslt(MsgOfIssuerToIdv isToId) {
		/*获取发卡行验证结果*/
		this.isToId = isToId;
		/*指定Token担保级别*/
		int assigTokAssuLevel = 0;
		/*ID&V请求状态*/
		boolean reqStatus = false;
		/*原因代码长度*/
		int reasonCodeLen = 0;
		/*原因代码*/
		String reasonCode = "";
		
		JSONObject js;
		CardholderInfoBuilder insert = new CardholderInfoBuilder();
		JSONObject pkg;
		int responseCode;
		//JSONObject content;
		
		/*如果发卡行验证状态为成功，则将验证成功的持卡人数据标记设为true*/
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
			/*此处为一个空的操作*/
		}
		
		/*调用getAccVerLevel()方法获取账户验证级别*/
		int accVerLevel = getAccVerLevel();
		/*调用getRiskScore()方法获取风险评分*/
		int riskScore = getRiskScore();
		/*调用getTokAssuLevel()方法获取指定Token担保级别*/
		assigTokAssuLevel = getTokAssuLevel(accVerLevel, riskScore);
		System.out.println("assigTokAssuLevel:" + assigTokAssuLevel);
		/*当请求Token担保级别不为0，而指定Token担保级别为0时，ID&V验证不成功*/
		if((0 == assigTokAssuLevel) && (0 < reqTokAssuLevel)) {
			reqStatus = false;
			reasonCode = isToId.getReasonCode();
			
		}
		else {
			reqStatus = true;
			reasonCode = "";
		}
		
		/*返回前向Vault写入数据*/
	    if (isUpdate) {   //如果为更新担保级别的请求
	    	System.out.println("Starting 更新的update Vault");
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
	    		System.out.println("要更新的条目不存在");
	    		break;
	    	case ResponseCode.MAPPING_UPDATE_SUCCESS:
	    		System.out.println("Vault中更新担保级别成功");
	    		break;
	    	case ResponseCode.MAPPING_UPDATE_FAILUER:
	    		System.out.println("Vault中更新担保级别失败");
	    		break;
	    	}
	    	
	    }
	    else {   //如果为申请Token的请求
	    	System.out.println("Starting 生成token的insert Vault");
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
	    		System.out.println("插入失败，要插入的映射已经存在");
	    		break;
	    	case ResponseCode.MAPPING_INSERT_SUCCESS:
	    		System.out.println("Vault中插入映射成功");
	    		break;
	    	case ResponseCode.MAPPING_INSERT_ERROR:
	    		System.out.println("Vault中插入映射失败");
	    		break;
	    	}
	    }
	    
		reasonCodeLen = reasonCode.length();
		MsgOfIdvReslt idvReslt = new MsgOfIdvReslt(assigTokAssuLevel, reqStatus, reasonCodeLen, reasonCode, tokenLen, token);
		return idvReslt;
		
	}
	
	/*调用该方法获取指定Token担保级别*/
	public int getTokAssuLevel(int accVerLevel, int riskScore) {
		int assigTokAssuLevel = 0;
		
		/*调用getAccVerLevel()方法获取账户验证级别
		int accVerLevel = getAccVerLevel();
		调用getRiskScore()方法获取风险评分
		int riskScore = getRiskScore();*/
		
		if(17 <= reqTokAssuLevel) {
			System.out.println("请输入不大于16的整数请求Token担保级别！");
		}
		else if(0 >= reqTokAssuLevel) { 
			assigTokAssuLevel = 0;
		}
		else {
			
			/*创建4种给指定Token担保级别评级的对象*/
			Rater r1 = new Level13To16Rater();
			Rater r2 = new Level9To12Rater();
			Rater r3 = new Level5To8Rater();
			Rater r4 = new Level1To4Rater();
			/*应用职责链模式，设置它们的后续处理者*/
			r1.setSuccessRater(r2);
			r2.setSuccessRater(r3);
			r3.setSuccessRater(r4);
			/*调用Rating()方法给指定指定Token担保级别评级评级*/
			r1.Rating(reqTokAssuLevel, accVerLevel, riskScore);
			/*获取评级后的指定Token担保级别*/
			assigTokAssuLevel = r1.getAssigTokAssuLevel();
		}
		return assigTokAssuLevel;
	}
	
	/*调用该方法获取账户验证级别*/
	public int getAccVerLevel() {
		int accVerLevel = 0;
		/*获取当前时间，并转换成YYMM的格式*/
		Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyMM");  
        String dateNowStr = sdf.format(now);
        Integer iDateNowStr = Integer.parseInt(dateNowStr);
        /*若未超过PAN的有效日期，则设账户验证级别为1*/
		if(panExpDate > iDateNowStr) {
			accVerLevel = 1;
			/*若账户姓名和身份证号验证成功，则设账户验证级别为2*/
			if(chd.accotNameFlag && chd.idFlag) {
				accVerLevel = 2;
				/*若手机号或Email或校验码验证成功，则设账户验证级别为3*/
				if (chd.mobileNoFlag || chd.emailFlag || chd.checkCodeFlag)
					/*测试if (false)*/
				{
					accVerLevel = 3;
					/*若账单地址或邮寄地址或邮政编码验证成功，则设账户验证级别为4*/
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

	/*把接收到的，但未得到验证的持卡人数据按格式拼接成字符串sb*/
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
	
	/*调用该方法获取风险评分*/
	public int getRiskScore() {
		int riskScore = 0;
		//Token域控
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
		
		//Token位置
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
		
		//Token域控
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
		
		//协议
		switch(protocol) {
		case 0: riskScore += 30;
	    		break;
		case 1: riskScore += 21;
		        break;
		case 2: riskScore += 15;
		        break;
		default: break;
		}
		
		//地址错配指示器
		if(true == addrMisIndic) {
			riskScore += 0;
		}
		else if(false == addrMisIndic) {
			riskScore += 10;
		}
		
		//账户使用时间
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
		/*检测给定id的持卡人是否存在*/
        JSONObject js;
        CardholderInfoBuilder index = new CardholderInfoBuilder();
        index.put("cardholderId", chd.id);
        js = index.build();
        JSONObject pkg = vault.isCardholderExist(js);
		int responseCode = pkg.getInt("responseCode");
		//JSONObject content = pkg.getJSONObject("responseContent");
		/*不存在， 则插入，并且持卡人信息设置为匹配*/
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
	    		System.out.println("插入持卡人信息失败");
	    		break;
	    	case ResponseCode.CARDHOLDER_INSERT_SUCCESS:
	    		System.out.println("持卡人信息插入成功");
	    		/*三项都当做匹配处理*/
		        riskScore += 0;
	    		break;
	    	case ResponseCode.CARDHOLDER_ALREADY_EXIST:
	    		System.out.println("插入失败，持卡人信息已存在");
	    		break;
	    	}
		}
		/*存在， 则查询，返回匹配信息并更新*/
		else if(ResponseCode.CARDHOLDER_ALREADY_EXIST == responseCode) {
			//IP地址是否与常用IP相同
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
			
			//地理位置是否与常用地理位置相同
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
			
			//设备信息是否与常用设备信息相同
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
