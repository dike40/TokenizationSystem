package com.TokenizationSys.Engine;

import net.sf.json.JSONObject;

import com.TokenizationSys.Utils.Configuration;

public class TokenSystemEngine implements Engine {
	
	private static TokenSystemEngine mEngine = null;

	public static TokenSystemEngine getInstance() {
		
		
		if(mEngine == null){
			mEngine = new TokenSystemEngine();
		}
		
		return mEngine;
	}
	
	private TokenSystemEngine () {
		
	}
	
	public String[] SetConnect(String ...param) {
		// TODO set connection
		System.out.println("建立连接。。。成功:"+param);
		return param;
	}
	
	public String[] getMsg(JSONObject jo) throws Exception {
		// TODO get message
		System.out.println("获取报文中。。。成功:"+jo.toString());
		String[] param = null ;
	try {
		
		
		if(jo.get("type").equals(Configuration.msgFromRegistry)){
			String[] temp = {(String)jo.get("domain"), 
			(String)jo.getString("mode"),  
			(String)jo.getString("merchantId"),
			(String)jo.getString("param1"),
			(String)jo.getString("param2")
			};
			param = temp;
		}
		else if (jo.get("type").equals(Configuration.msgFromTokenization)) {
			
			String[] temp = {(String)jo.getString("tokReqId"), 
					(String)jo.getString("panLen"),  
					(String)jo.getString("pan"),
					(String)jo.getString("panExpDate"),
					(String)jo.getString("tokenLen"),
					(String)jo.getString("token"),
					(String)jo.getString("reqTokAssuLevel"),
					(String)jo.getString("tokenLoc"),
					(String)jo.getString("protocol"),
					(String)jo.getString("accotVerReslt"),
					(String)jo.getString("accotVerRefLen"),
					(String)jo.getString("accotVerRef"),
					(String)jo.getString("tokReqRiskSco"),
					(String)jo.getString("addrMisIndic"),
					(String)jo.getString("cardhldDataLen"),
					(String)jo.getString("cardhldData"),
					(String)jo.getString("devInfoLen"),
					(String)jo.getString("devInfo"),
					(String)jo.getString("tokDomRestrtContrl"),
					(String)jo.getString("posEntryMode"),
					(String)jo.getString("merchId"),
					(String)jo.getString("isUpdate")
					
					};
					param = temp;
			
			
		}
		else if (jo.get("type").equals(Configuration.msgFromDeTokenization)){
			String[] temp ={
					(String)jo.getString("tokenStr"),
					(String)jo.getString("trId"),
					(String)jo.getString("tokenExpDate"),
					(String)jo.getString("devInfo"),
					(String)jo.getString("cardhldId"),
					(String)jo.getString("tokCrypto"),
					(String)jo.getString("posEntryMode")
					
					
			};
			param = temp;
		}
		else if (jo.get("type").equals(Configuration.msgFromLifeCtrl)){
			String[] temp ={
					(String)jo.getString("token"),
					(String)jo.getString("trId"),
					(String)jo.getString("tokenManageType"),
					(String)jo.getString("newTokenExpDate")
					
		
			};
			param = temp;
		}
		
	} catch (Exception e) {
		// TODO: handle exception
		System.out.println("Get Message Error:"+e.getMessage());
		
		throw new Exception("Get Message Error:"+e.getMessage());
	}
		
		return param;	
	}
	
	public String[] DeInfo(String ...param) {
		// TODO read message
		System.out.println("解析报文。。。成功:"+param);
		return param;
	}

	public String[] sendBackMsg(String ...param) {
		// TODO return message
		System.out.println("返回报文。。。成功:"+param[0]);
		return param;
	}

}
