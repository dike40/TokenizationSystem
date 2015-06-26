package com.TokenizationSys.LifeCycleCtrl;


import com.TokenizationSys.DB.ResponseCode;
import com.TokenizationSys.DB.Vault;
import com.TokenizationSys.Utils.Configuration;

import net.sf.json.JSONObject;

public class lifeCtrlProcess {
	
	private MsgOfLifeCtrl mMsgOfLifeCtrl;
	private Vault mVault;
	public lifeCtrlProcess(MsgOfLifeCtrl mMsgOfLifeCtrl){
		this.mMsgOfLifeCtrl = mMsgOfLifeCtrl;
		mVault = Vault.getVault();
	}
	
	public JSONObject goManagement(){
		
		JSONObject jo = new JSONObject();
		
		if(mMsgOfLifeCtrl.getTokenManageType().equals(Configuration.lifeTypeUnlink)){
			JSONObject mappingData = new JSONObject();
			mappingData.put("token", mMsgOfLifeCtrl.getToken());
			mappingData.put("trId", mMsgOfLifeCtrl.getTrId());
			
			JSONObject resultJsonObject = mVault.deleteMappingByToken(mappingData);
			
			jo.put("vaultReturnCode", ""+resultJsonObject.getInt("responseCode"));
			
		}
		else if (mMsgOfLifeCtrl.getTokenManageType().equals(Configuration.lifeTypeSuspend)) {
			
			/* suspend 的时候 因为是pan来做索引取得token 所以 报文里token字段代表pan 需要再转化*/
			
			JSONObject mappingData = new JSONObject();
			mappingData.put("pan", mMsgOfLifeCtrl.getToken());
			mappingData.put("trId", mMsgOfLifeCtrl.getTrId());	
			
			JSONObject returnJsonObject = mVault.getToken(mappingData);
			int returncode= returnJsonObject.getInt("responseCode"); 
			
			if (ResponseCode.SUCCESS == returncode) {
				
				JSONObject tokenObj = returnJsonObject.getJSONObject("responseContent"); 
				String token = tokenObj.getString("token");
			
				mappingData.put("token", token);
				mappingData.put("trId", mMsgOfLifeCtrl.getTrId());
				mappingData.put("tokenStatus", "SUSPEND");
			
				JSONObject resultJsonObject = mVault.updateTokenStatus(mappingData);
			
				jo.put("vaultReturnCode", ""+resultJsonObject.getInt("responseCode"));
			}
			else{
				jo.put("vaultReturnCode", ""+ResponseCode.TOKEN_STATUS_UPDATE_FAILURE);
			}
		}
		else if (mMsgOfLifeCtrl.getTokenManageType().equals(Configuration.lifeTypeActivate)) {
			
			JSONObject mappingData = new JSONObject();
			mappingData.put("token", mMsgOfLifeCtrl.getToken());
			mappingData.put("trId", mMsgOfLifeCtrl.getTrId());
			mappingData.put("tokenStatus", "ACTIVATE");
			
			JSONObject resultJsonObject = mVault.updateTokenStatus(mappingData);
			
			jo.put("vaultReturnCode", ""+resultJsonObject.getInt("responseCode"));
		}
		else if (mMsgOfLifeCtrl.getTokenManageType().equals(Configuration.lifeTypeNewTime)) {
			
			JSONObject mappingData = new JSONObject();
			mappingData.put("token", mMsgOfLifeCtrl.getToken());
			mappingData.put("trId", mMsgOfLifeCtrl.getTrId());
			mappingData.put("tokenExpiryTime", mMsgOfLifeCtrl.getNewTokenExpDate() );
			
			JSONObject resultJsonObject = mVault.updateTokenExpiryTime(mappingData);
			
			jo.put("vaultReturnCode", ""+resultJsonObject.getInt("responseCode"));
		
		}
		
		switch (jo.getInt("vaultReturnCode")) {
		case 42:
			jo.put("resultDetail", "TOKEN_EXPIRY_UPDATE_SUCCESS");
			break;
		case 43:
			jo.put("resultDetail", "TOKEN_EXPIRY_UPDATE_FAILURE");
			break;
		case 44:
			jo.put("resultDetail", "TOKEN_STATUS_UPDATE_SUCCESS");
			break;
		case 45:
			jo.put("resultDetail", "TOKEN_STATUS_UPDATE_FAILURE");
			break;
		case 46:
			jo.put("resultDetail", "TOKEN_UNLINK_SUCCESS");
			break;
		case 47:
			jo.put("resultDetail", "TOKEN_UNLINK_FAILURE");
			break;

		default:
			break;
		}
		return jo;
		
		
	}

}
