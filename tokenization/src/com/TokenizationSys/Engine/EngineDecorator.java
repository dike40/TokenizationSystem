package com.TokenizationSys.Engine;

import net.sf.json.JSONObject;

public class EngineDecorator implements Engine{

	TokenSystemEngine TSE;
	
	public EngineDecorator (TokenSystemEngine TSE) {
		this.TSE = TSE;
	}
	


	public String[] SetConnect(String ...param) {
		return TSE.SetConnect(param);
	}



	public String[] DeInfo(String ...param) {
		return TSE.DeInfo(param);
	}



	public String[] getMsg(JSONObject jo) throws Exception {
		return TSE.getMsg(jo);
	}



	public String[] sendBackMsg(String ...param) {
		return TSE.sendBackMsg(param);
	}

	
}
