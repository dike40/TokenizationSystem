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
		//注册
		JSONObject pkg=vault.registerTr(tr);
		int resCode=pkg.getInt("responseCode");
		//检测是TR是否存在
		//int res1=vault.isTrExist(tr);

		
		return resCode;
	}
	public String getRandomNumber(){
		 /*
	     * 返回长度为【strLength】的随机数，在前面补0 后面,再后面加上时间。
	     */
		StringBuilder resultString = new StringBuilder();
		int strLength = 6; //随机数长度为6；年份为后两位；
	   
			Calendar a=Calendar.getInstance();
	        int year = a.get(Calendar.YEAR);//得到年
	        
	        resultString.append(year%100);//得到年后两位
	        
	        Random rm = new Random();
	        
	        // 获得随机数
	        double pross = (1 + rm.nextDouble()) * Math.pow(10, strLength);

	        // 将获得的获得随机数转化为字符串
	        String fixLenthString = String.valueOf(pross);

	        // 返回固定的长度的随机数
	        resultString.append(fixLenthString.substring(1, strLength + 1));
	   
	       
		
		return resultString.toString();
	}
	
	
}
