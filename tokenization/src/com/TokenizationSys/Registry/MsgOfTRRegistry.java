package com.TokenizationSys.Registry;

public class MsgOfTRRegistry {

	
	private String TRID;
	private String tokenDomain; //Token Domain Restriction Controls; 01/02
	private String posEntryMode;
	private String merchantId;
	private String param_1;
	private String param_2;
	

	public  MsgOfTRRegistry() {
		
	}
	/*****************get and set***********************/	
	public String getTokenDomain() {
		return this.tokenDomain;	
	}
	public void setTokenDomain(String tokenDomain){
		this.tokenDomain = tokenDomain;
	}
	/****************************************/
	public String getTRID() {
		return this.TRID;
	}
	public void setTRID(String TRID) {
		this.TRID = TRID;
	}
	/****************************************/
	public String getPosEntryMode() {
		return this.posEntryMode;
	}
	public void setPosEntryMode(String posEntryMode) {
		this.posEntryMode = posEntryMode;
	}
	/****************************************/
	public String getMerchantId() {
		return this.merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	
	/****************************************/
	public String getParam_1() {
		return this.param_1;
	}
	public void setParam_1(String param_1) {
		this.param_1 = param_1;
	}
	/****************************************/
	public String getparam_2() {
		return this.param_2;
	}
	public void setParam_2(String param_2) {
		this.param_2 = param_2;
	}
	
	
}
