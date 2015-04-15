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
	
	public static String msgFromRegistry = "registry";
	public static String msgFromTokenization = "tokenization";
	public static String msgFromLifeCtrl = "lifeCtrl";
}
