package com.TokenizationSys.Engine;

import net.sf.json.JSONObject;

import com.TokenizationSys.DB.ResponseCode;
import com.TokenizationSys.Registry.MsgOfTRRegistry;
import com.TokenizationSys.Registry.TRInfoProcess;
import com.TokenizationSys.Utils.Configuration;
import com.sun.org.apache.bcel.internal.generic.NEW;

public class EngineForRegistry extends EngineDecorator{

	
	private MsgOfTRRegistry mMsgOfTRRegistry ;
	private TRInfoProcess mTrInfoProcess ;
	private String TR_ID;
	private String vaultReturnCode;
	
	
	public EngineForRegistry(TokenSystemEngine TSE) {
		
		super(TSE);
	}
	
	public String[] process(JSONObject jo) {
		
		connect();
		getInfo(getMessage(jo));
		Register();	
		
		String[] result = sendBack();
		
		return result;
	}

	private String[] connect() {
		
		// �������� ��ʼ�����ݣ�
		String result[] = super.SetConnect();//��ʱû�ã���ͬ�����ӷ�ʽ���õ�
		
		mMsgOfTRRegistry = new MsgOfTRRegistry();
		
		return result;	
	}
	
	private String[] getMessage(JSONObject jo) {		
		
		return super.getMsg(jo);//ͨ��jsonobject�������
	}
	
	
	private void getInfo(String ...param) {
		
		String[] result = super.DeInfo(param);//��ʱδ�õ�
		//��װע����Ϣ����		
		mMsgOfTRRegistry.setTokenDomain(result[0]);
		mMsgOfTRRegistry.setPosEntryMode(result[1]);
		mMsgOfTRRegistry.setMerchantId(result[2]);
		mMsgOfTRRegistry.setParam_1(result[3]);
		mMsgOfTRRegistry.setParam_2(result[4]);
		
	}
	
	
	
	private void Register(){
		
		
		mTrInfoProcess = new TRInfoProcess(mMsgOfTRRegistry);	
		
		TR_ID = mTrInfoProcess.generateTRID();
		if (TR_ID.equals(ResponseCode.UNKNOWN_ERROR+"")) {
			System.out.println("ERROR REGISTION!");
			vaultReturnCode = Configuration.vaultReturnCode.fault.getResult();
		} 
		System.out.println("TR_ID:"+TR_ID);
		vaultReturnCode = Configuration.vaultReturnCode.sucess.getResult();
	}
	
	private String[] sendBack() {
		String[] result = null;
		if (vaultReturnCode == Configuration.vaultReturnCode.sucess.getResult()) {
			//TODO return TR_ID and success;
			String[] temp = {vaultReturnCode,TR_ID};
			result = temp;
			
			super.sendBackMsg(result);
			
		} else if(vaultReturnCode == Configuration.vaultReturnCode.fault.getResult()){
			//TODO return fault and reason;
			
			String[] temp = {vaultReturnCode,TR_ID};
			result = temp;
			
			super.sendBackMsg(result);
		}
		
		return result;
	}
	
	
}
