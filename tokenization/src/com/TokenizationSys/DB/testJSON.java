package com.TokenizationSys.DB;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONObject;

/**
 *测试用例
 *@author Yao Shui He
 */
public class testJSON {
	

	public static void main(String[] args)  {
	
//	testTr();
//	testCardholder();

		
		testVault();

		
	}
	public static void testVault(){
		JSONObject c=new JSONObject();
	
		c.put("pan", "323132314221");
		c.put("panLen", "2");
		c.put("panExpiryTime", "1805");
		c.put("token", "-1");
		c.put("tokenLen", "-1");
		c.put("tokenExpiryTime", "1805");
		c.put("tokenLocation", "TEE");
		c.put("tokenReqLevel", "60");
		c.put("tokenAssignedLevel", "40");
		c.put("trId", "6d");
		c.put("sharedKey", "-1");
		c.put("protocol", "web");
		c.put("accountVerResult", "verifyed");
		c.put("accountVerRefLen", "6");
		c.put("accountVerRef", "123456");
		c.put("accountVerResultTsp", "YesOK");
		c.put("riskScore", "300");
		c.put("riskScoreTsp", "500");
		c.put("addrMismatchFlag", "01");
		c.put("cardholderNumber", "15828556454");
		c.put("cardholderMail", "294536480@qq.com");
		c.put("cardholderCheckCode", "CCV");
		c.put("cardholderBillAddr", "UESTC");
		c.put("cardholderShipAddr", "ESECLAB");
		c.put("cardholderPostCode", "611731");
		c.put("accountAge", "365");
		c.put("extra1", "extra2");
		c.put("extra2", "extra2");
		Vault vault=Vault.getVault();
		JSONObject pkg=vault.updateMapping(c);
		int responseCode=pkg.getInt("responseCode");
		JSONObject content=pkg.getJSONObject("responseContent");
		System.out.println("responseCode:"+responseCode);
		System.out.println("responseContent:"+content.toString());
	}
	public static void testCardholder(){
		JSONObject c=new JSONObject();
		c.put("cardholderId", "6d");
		c.put("trId", "tr13231");
		c.put("cardholderPan", "323132314221");
		c.put("cardholderName", "杨箫");
		c.put("cardholderLocation", "Sichuan"); 
		c.put("cardholderIp", "192.168.1.1");
		c.put("cardholderDeviceInfo", "Nokia");
		c.put("extra1", "extra1");
		c.put("extra2", "extra2");
		Vault vault=Vault.getVault();
		JSONObject pkg=vault.checkDevice(c);
		int responseCode=pkg.getInt("responseCode");
		JSONObject content=pkg.getJSONObject("responseContent");
		System.out.println("responseCode:"+responseCode);
		System.out.println("responseContent:"+content.toString());
	}
	public static void  testTr(){
		JSONObject tr=new JSONObject();
		
		tr.put("trId", "1d");
		tr.put("trDomainCtrl", "1321312");
		tr.put("posEntryMode", "你好啊");
		tr.put("merchantId", "8220");
		tr.put("extra1", "extra1");
		tr.put("extra2", "extra2");
	    
		Vault vault=Vault.getVault();
		//返回报文为一个JSON对象，包含responseCode和responseContent，
		//responseCode为返回码
		//responseContent为一个JSON对象
		JSONObject pkg=vault.registerTr(tr);
		int resCode=pkg.getInt("responseCode");
		JSONObject content=pkg.getJSONObject("responseContent");
		

		System.out.println("responseCode:"+resCode);
		System.out.println("responseContent:"+content.toString());
	}	
	//内部测试请忽略
	public static void sql(){
		JSONObject c=new JSONObject();	
//		String sql="SELECT cardholderIp,cardholderLocation,cardholderDeviceInfo FROM cardholder WHERE cardholderId='"+"5d"+"';";
		String sql="UPDATE vault SET token='"+"fffff1111ssss"+"',"
		   +"tokenLen='"+"13"+"'"
		   +" WHERE pan='"+"323132314221"
		   +" AND trId='"+"6d"+"';";
		System.out.println(sql);
		c.put("sql", sql);
		Vault vault=Vault.getVault();
		JSONObject pkg=vault.sqlTest(c);
		int responseCode=pkg.getInt("responseCode");
		JSONObject content=pkg.getJSONObject("responseContent");
		System.out.println("responseCode:"+responseCode);
		System.out.println("responseContent:"+content.toString());
		
	}
	
}
