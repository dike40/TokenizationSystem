package com.TokenizationSys.Engine;

import com.TokenizationSys.LifeCycleCtrl.MsgOfLifeCtrl;
import com.TokenizationSys.LifeCycleCtrl.lifeCtrlProcess;

import net.sf.json.JSONObject;

public class EngineForLifeCycleCtrl extends EngineDecorator{
	
	private MsgOfLifeCtrl mMsgOfLifeCtrl;
	//private String vaultReturnCode;
	//private String newTime;

	public EngineForLifeCycleCtrl(TokenSystemEngine TSE) {
		super(TSE);
	}

	
	public String process(JSONObject jo) {
		
		connect();
		try {
			
		doManage(getMessage(jo));
			
			} catch (Exception e) {
			// TODO: handle exception
				return e.getMessage();
		}
		JSONObject resultJO = contrlLife(mMsgOfLifeCtrl);
		
		String result = sendBack(resultJO);
		
		return result;
	}



	private String sendBack(JSONObject jo) {
		// TODO Auto-generated method stub
		 return jo.toString();
	}


	private JSONObject contrlLife(MsgOfLifeCtrl mMsgOfLifeCtrl2) {
		lifeCtrlProcess mlifeCtrlProcess = new lifeCtrlProcess(mMsgOfLifeCtrl2);
		return mlifeCtrlProcess.goManagement();
		
		// TODO Auto-generated method stub
		
	}


	private void doManage(String ...message) {
		// TODO Auto-generated method stub
		mMsgOfLifeCtrl.setToken(message[0]);
		mMsgOfLifeCtrl.setTrId(message[1]);
		mMsgOfLifeCtrl.setTokenManageType(message[2]);
		mMsgOfLifeCtrl.setNewTokenExpDate(message[3]);
		
	}


	private String[] getMessage(JSONObject jo) throws Exception {
		
		return super.getMsg(jo);
		
		
	}


	private void connect() {
		// TODO Auto-generated method stub
		super.SetConnect();
		mMsgOfLifeCtrl = new MsgOfLifeCtrl();
	}
	

}
