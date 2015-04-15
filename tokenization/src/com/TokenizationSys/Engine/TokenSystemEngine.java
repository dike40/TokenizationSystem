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
	
	public String[] getMsg(JSONObject jo) {
		// TODO get message
		System.out.println("获取报文中。。。成功:"+jo.toString());
		String[] param = null ;
		
		if(jo.get("type").equals(Configuration.msgFromRegistry)){
			String[] temp = {(String)jo.get("domain"), 
			(String)jo.get("mode"),  
			(String)jo.get("merchantId"),
			(String)jo.get("param1"),
			(String)jo.get("param2")
			};
			param = temp;
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
		System.out.println("返回报文。。。成功:"+param);
		return param;
	}

}
