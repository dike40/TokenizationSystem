package com.serviceImpl;

import net.sf.json.JSONObject;

import com.TokenizationSys.Engine.EngineForDetokenization;
import com.TokenizationSys.Engine.TokenSystemEngine;
import com.TokenizationSys.Utils.Configuration;
import com.service.DetokenizationService;

public class DetokenizationServiceImpl implements DetokenizationService {
	
	private TokenSystemEngine tse =TokenSystemEngine.getInstance();
	private EngineForDetokenization eDeTokenization = new EngineForDetokenization(tse);

	public String deTokenization(JSONObject jo){
		String result = null;
		try {
			jo.put("type", Configuration.msgFromDeTokenization);
			result = eDeTokenization.process(jo);		
		} catch (Exception e) {	
			
			System.out.println("error from DetokenizationService:"+e.toString());		
		}	
		return result;
	
	}

}
