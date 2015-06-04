package com.TokenizationSys.Utils;

public class Configuration {
	
	public static String TSP_NUMBER = "307";

	
	
	
	public enum vaultReturnCode{
		sucess("01"),
		fault("00");
		public final String result;
		
		
		private vaultReturnCode(String result){
			this.result = result;
		}		   
		public String getResult() {  
		     return result; 
			
		}
	}
	public static String errorRequest = "no such servlet request!";
	public static String msgFromRegistry = "registry";
	public static String msgFromTokenization = "tokenization";
	public static String msgFromDeTokenization = "detokenization";
	public static String msgFromLifeCtrl = "lifeCtrl";
	
	public static String lifeTypeUnlink = "unlink";
	public static String lifeTypeSuspend = "suspend";
	public static String lifeTypeActivate = "activate";
	public static String lifeTypeNewTime = "newtime";
	
}
