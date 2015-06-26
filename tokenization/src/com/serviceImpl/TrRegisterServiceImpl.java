package com.serviceImpl;

import net.sf.json.JSONObject;

import com.TokenizationSys.Engine.EngineForRegistry;
import com.TokenizationSys.Engine.TokenSystemEngine;
import com.TokenizationSys.Utils.Configuration;
import com.service.TrRegisterService;

public class TrRegisterServiceImpl implements TrRegisterService {

	private TokenSystemEngine tse =TokenSystemEngine.getInstance();
	private EngineForRegistry eRegistry = new EngineForRegistry(tse);
	
	public String register(JSONObject jo){
		String result = null;
		try {
			
			jo.put("type", Configuration.msgFromRegistry);		
			result = eRegistry.process(jo);//进入TR ID生成阶段；
			
		} catch (Exception e) {
			
			System.out.println("error from TrRegisterService:"+e.toString());
		}
		
		
		return result;				
	}
}
