package com.serviceImpl;

import net.sf.json.JSONObject;

import com.TokenizationSys.Engine.EngineForTokenization;
import com.TokenizationSys.Engine.TokenSystemEngine;
import com.TokenizationSys.Utils.Configuration;
import com.service.TokenizationService;

public class TokenizationServiceImpl implements TokenizationService{
	
	private TokenSystemEngine tse =TokenSystemEngine.getInstance();
	private EngineForTokenization eTokenization = new EngineForTokenization(tse);

	public String tokenization(JSONObject jo){
		String result = null;
		try {
			jo.put("type", Configuration.msgFromTokenization);
			result = eTokenization.process(jo);
		
		} catch (Exception e) {
		
			System.out.println("error from TokenizationService:"+e.toString());
			return "error from TokenizationService:"+e.toString()+result;
		}
	
		return result;
	
	}

}
