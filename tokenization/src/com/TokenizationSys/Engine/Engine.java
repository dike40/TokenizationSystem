package com.TokenizationSys.Engine;

import net.sf.json.JSONObject;

public interface Engine {
	
	public String[] SetConnect(String ...param);
	
	public String[] getMsg(JSONObject jo) throws Exception;
	
	public String[] DeInfo(String ...param);

	public String[] sendBackMsg(String ...param);
}
