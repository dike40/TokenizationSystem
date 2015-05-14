package com.TokenizationSys.Registry;

import java.util.Calendar;
import java.util.Random;

import net.sf.json.JSONObject;

import com.TokenizationSys.DB.ResponseCode;
import com.TokenizationSys.DB.Vault;
import com.TokenizationSys.Utils.Configuration;

public class TRInfoProcess {

	/**TR ID
	 * 
	 *   1-3 TSP number; 4-11 should be unique;
	 * 
	 * 
	 * */
	private MsgOfTRRegistry mMsgOfTRRegistry;
	
	public TRInfoProcess(MsgOfTRRegistry msg){
		this.mMsgOfTRRegistry = msg;
	}
	
	public String generateTRID() {
		
		/*TODO generate TR_ID*/
		StringBuilder sb = new StringBuilder();
		sb.append(Configuration.TSP_NUMBER);
		sb.append(getRandomNumber());
		
		String idString = sb.toString();// TR ID
		
		
		this.mMsgOfTRRegistry.setTRID(idString);		
		int resultcode = registerInVault(this.mMsgOfTRRegistry);
		
		while(resultcode == ResponseCode.TR_ALREADY_EXIST){
			StringBuilder sb_twice = new StringBuilder();
			sb_twice.append(Configuration.TSP_NUMBER);
			sb_twice.append(getRandomNumber());
			idString = sb_twice.toString();
			this.mMsgOfTRRegistry.setTRID(idString);		
			resultcode = registerInVault(this.mMsgOfTRRegistry);
		}
		
		if (resultcode == ResponseCode.UNKNOWN_ERROR) {
			return resultcode+"";
		}

		return idString;
		
	}
	public int registerInVault(MsgOfTRRegistry msgOfTRRegistry){
		
		JSONObject tr=new JSONObject();
		
		tr.put("trId", msgOfTRRegistry.getTRID());
		tr.put("trDomainCtrl", msgOfTRRegistry.getTokenDomain());
		tr.put("merchantId", msgOfTRRegistry.getMerchantId());
		tr.put("posEntryMode", msgOfTRRegistry.getPosEntryMode());
		tr.put("extra1", msgOfTRRegistry.getParam_1());
		tr.put("extra2", msgOfTRRegistry.getparam_2());
		
		
		Vault vault=Vault.getVault();
		//ע��
		JSONObject pkg=vault.registerTr(tr);
		int resCode=pkg.getInt("responseCode");
		//�����TR�Ƿ����
		//int res1=vault.isTrExist(tr);

		
		return resCode;
	}
	public String getRandomNumber(){
		 /*
	     * ���س���Ϊ��strLength�������������ǰ�油0 ����,�ٺ������ʱ�䡣
	     */
		StringBuilder resultString = new StringBuilder();
		int strLength = 6; //���������Ϊ6�����Ϊ����λ��
	   
			Calendar a=Calendar.getInstance();
	        int year = a.get(Calendar.YEAR);//�õ���
	        
	        resultString.append(year%100);//�õ������λ
	        
	        Random rm = new Random();
	        
	        // ��������
	        double pross = (1 + rm.nextDouble()) * Math.pow(10, strLength);

	        // ����õĻ�������ת��Ϊ�ַ���
	        String fixLenthString = String.valueOf(pross);

	        // ���ع̶��ĳ��ȵ������
	        resultString.append(fixLenthString.substring(1, strLength + 1));
	   
	       
		
		return resultString.toString();
	}
	
	
}
