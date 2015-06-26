package com.serviceImpl;

import net.sf.json.JSONObject;

import com.TokenizationSys.Engine.EngineForLifeCycleCtrl;
import com.TokenizationSys.Engine.TokenSystemEngine;
import com.TokenizationSys.Utils.Configuration;
import com.service.LifeCycleCtrlService;

public class LifeCycleCtrlServiceImpl implements LifeCycleCtrlService {

	
	private TokenSystemEngine tse =TokenSystemEngine.getInstance();
	private EngineForLifeCycleCtrl eLife = new EngineForLifeCycleCtrl(tse);
	
	public String lifeCtrl(JSONObject jo){
		String result = null;
		
			jo.put("type", Configuration.msgFromLifeCtrl);
			result = eLife.process(jo);
			
		//System.out.println("LifeCycleCtrlServiceImpl result:"+result);
		
		return result;
	}
}
