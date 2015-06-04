package com.TokenizationSys.LifeCycleCtrl;

public class MsgOfLifeCtrl {
	
	private String token;
	private String trId;
	private String tokenManageType;
	private String newTokenExpDate;
	
	
	public void setToken(String token) {
		this.token = token;
	}
	public String getToken(){
		return token;
		
	}
	
	public void setTrId(String trId) {
		this.trId = trId;
	}
	public String getTrId(){
		return trId;
		
	}
	
	public void setTokenManageType(String tokenManageType) {
		this.tokenManageType = tokenManageType;
	}
	public String getTokenManageType(){
		return tokenManageType;
		
	}
	
	public void setNewTokenExpDate(String newTokenExpDate) {
		this.newTokenExpDate = newTokenExpDate;
	}
	public String getNewTokenExpDate(){
		return newTokenExpDate;
		
	}
	
	
}
