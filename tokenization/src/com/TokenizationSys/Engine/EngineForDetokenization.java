package com.TokenizationSys.Engine;

import com.TokenizationSys.DeTokenization.DeTokenProc;
import com.TokenizationSys.DeTokenization.MsgOfDeTokReslt;
import com.TokenizationSys.Utils.hexAndString;

import net.sf.json.JSONObject;

public class EngineForDetokenization extends EngineDecorator{
	
	private MsgOfDeTokReslt mod;

	public EngineForDetokenization(TokenSystemEngine TSE) {
		super(TSE);
		// TODO Auto-generated constructor stub
	}

	public String process(JSONObject jo) {
		
		connect();
		
		try {
			doDeToken(getMessage(jo));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return e.getMessage();
		}
		
		String result = sendBack();
		
		return result;
	}

	private String[] connect() {
		
		// 建立连接 初始化数据；
		String result[] = super.SetConnect();//暂时没用；不同的连接方式有用到
		
		
		
		return result;	
	}
	
	private String[] getMessage(JSONObject jo) throws Exception {		
		
		return super.getMsg(jo);//通过jsonobject获得数据
	}
	
	private void doDeToken(String[] param) {
		//String tokenTemp = param[0];
		String token = param[0]; 
		String tokReqId = param[1];
		int tokenExpDate = Integer.parseInt(param[2]);
		String devInfo = param[3];
		String cardhldId = param[4];
		byte[] tokCrypto = hexAndString.hexToBytes(param[5]);
		int posEntryMode = Integer.parseInt(param[6]);
		
		System.out.println("tokCrypto Len in detoken:"+tokCrypto.length);
		
		
			int tokenLen = token.length();
		  
		    int devInfolen = devInfo.length();
		   
		DeTokenProc drp = new DeTokenProc(tokReqId, tokenLen,  token,
				 tokenExpDate, tokCrypto, devInfolen, 
				 devInfo,  cardhldId,  posEntryMode);
		
		this.mod = drp.reqDeTok();
		
	}

	
	
	
	private String sendBack() {
		
		
		JSONObject jo = new JSONObject();
		jo.put("panLen", mod.getPanLen());
		jo.put("pan", mod.getPan());
		jo.put("panExpDate", mod.getPanExpDate());
		
		
		
		return jo.toString();
		
	}
	
	

}
