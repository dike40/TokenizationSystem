package com.TokenizationSys.Engine;

import com.TokenizationSys.Tokenization.GenerateSharekey;
import com.TokenizationSys.Tokenization.MsgOfTokReslt;
import com.TokenizationSys.Tokenization.TokenProc;
import com.TokenizationSys.Tokenization.IDV.Id_V;
import com.TokenizationSys.Tokenization.IDV.MsgOfIdvReslt;
import com.TokenizationSys.Tokenization.IDV.MsgOfIdvToIssuer;
import com.TokenizationSys.Tokenization.IDV.MsgOfIssuerToIdv;
import com.TokenizationSys.Utils.hexAndString;
import com.issuer.issuer;

import net.sf.json.JSONObject;



public class EngineForTokenization extends EngineDecorator{

	
	private MsgOfIdvReslt moi;
	private MsgOfTokReslt mot;
	
	
	public EngineForTokenization(TokenSystemEngine TSE) {
		super(TSE);
	}

	public String process(JSONObject jo) {
		
		connect();
		try {

			moi =doIDV(getMessage(jo));// jo-type:tokenization
			mot = doToken(getMessage(jo));
		} catch (Exception e) {
		
			return e.getMessage();
		}
		
		String result = sendBack(moi,mot);
		
		return result;
	}

	private String[] connect() {
		
		// 建立连接 初始化数据；
		String result[] = super.SetConnect();//暂时没用；不同的连接方式有用到
		
		//mMsgOfTRRegistry = new MsgOfTRRegistry();
		
		return result;	
	}
	
	private String[] getMessage(JSONObject jo) throws Exception {		
		
		return super.getMsg(jo);//通过jsonobject获得数据
	}
	
	
	private MsgOfIdvReslt doIDV(String ...param) throws Exception{
		
		Id_V idv = new Id_V(param[0],
		                    Integer.parseInt(param[1]),
		                    param[2],
		                    Integer.parseInt(param[3]),
		                    Integer.parseInt(param[4]),
		                    param[5],
		                    Integer.parseInt( param[6]),
		                    Integer.parseInt(param[7]),
		                    Integer.parseInt( param[8]),
		                    Boolean.getBoolean( param[9]),
		                    Integer.parseInt(param[10]),
		                    param[11],
		                    Integer.parseInt( param[12]),
		                    Boolean.getBoolean( param[13]),
		                    Integer.parseInt( param[14]),
		                    param[15],
		                    Integer.parseInt( param[16]),
		                    param[17],
		                    Integer.parseInt(param[18]),
		                    Integer.parseInt( param[19]),
		                    param[20],
		                    Boolean.getBoolean(param[21])
		);
	
		MsgOfIdvToIssuer idToIs = idv.reqIdv();

		String[] temp  = issuer.issue(idToIs); // issuer doing in issuer.java
		boolean reqStatus = Boolean.getBoolean(temp[0]);
		String reasonCode = temp[1];
		int reasonCodeLen = reasonCode.length();
	
		MsgOfIssuerToIdv isToId = new MsgOfIssuerToIdv(reqStatus, reasonCodeLen, reasonCode);
		MsgOfIdvReslt idvReslt = idv.getIdvReslt(isToId);
		
		return idvReslt;
		
	}
	
	private MsgOfTokReslt doToken(String ...param)throws Exception {
		TokenProc tp = new TokenProc(Integer.parseInt(param[1]), param[2], param[0]);
		
		MsgOfTokReslt mr = tp.reqTok(moi.getAssigTokAssuLevel());
		GenerateSharekey gs = GenerateSharekey.getGS();
		gs.generKey();
		System.out.println(hexAndString.bytesToHexString(gs.getKeyBytes()));
		tp.passTokenKey(hexAndString.bytesToHexString(gs.getKeyBytes()));		
		return mr;	
		
	}
	
	
	
	private String sendBack(MsgOfIdvReslt moi,MsgOfTokReslt mot) {
		GenerateSharekey gs = GenerateSharekey.getGS();
		JSONObject jo = new JSONObject();
		jo.put("tokenLen", ""+mot.getTokenLen());
		jo.put("token", mot.getToken());
		jo.put("assigTokAssuLevel", ""+moi.getAssigTokAssuLevel());		
		jo.put("tokenKey", hexAndString.bytesToHexString(gs.getKeyBytes()) );
		jo.put("tokenExpDate", mot.getTokenExpDate());

		System.out.println("token and tokenkey"+jo.toString());
		
		return jo.toString();
		
	}
	

}
