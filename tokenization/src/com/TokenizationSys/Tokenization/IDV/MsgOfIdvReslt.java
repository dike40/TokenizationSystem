package com.TokenizationSys.Tokenization.IDV;
/**
*过程四：ID&V模块最终返回给Tokenization系统的结果数据
*/

public class MsgOfIdvReslt {
	/*指定Token担保级别*/
	private int assigTokAssuLevel = -1;
	/*请求状态*/
	private boolean reqStatus = false;
	/*原因代码*/
	private String reasonCode = "";
	/*原因代码长度*/
	private int reasonCodeLen = reasonCode.length();
   	/*Token*/
	private String token = "";
	 /*Token的长度*/	
	private int tokenLen = token.length();

	
	/*MsgOfIdvReslt的构造函数*/
	public MsgOfIdvReslt(int assigTokAssuLevel, boolean reqStatus, int reasonCodeLen,
			String reasonCode, int tokenLen, String token) {
		this.assigTokAssuLevel = assigTokAssuLevel;
		this.reqStatus = reqStatus;
		this.reasonCodeLen = reasonCodeLen;
		this.reasonCode = reasonCode;
		this.tokenLen = tokenLen;
		this.token = token;
	}

	public int getAssigTokAssuLevel() {
		return assigTokAssuLevel;
	}

	public boolean isReqStatus() {
		return reqStatus;
	}

	public int getReasonCodeLen() {
		return reasonCodeLen;
	}

	public String getReasonCode() {
		return reasonCode;
	}

	public int getTokenLen() {
		return tokenLen;
	}

	public String getToken() {
		return token;
	}

	public void setAssigTokAssuLevel(int assigTokAssuLevel) {
		this.assigTokAssuLevel = assigTokAssuLevel;
	}

	public void setReqStatus(boolean reqStatus) {
		this.reqStatus = reqStatus;
	}

	public void setReasonCodeLen(int reasonCodeLen) {
		this.reasonCodeLen = reasonCodeLen;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}

	public void setTokenLen(int tokenLen) {
		this.tokenLen = tokenLen;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
}
