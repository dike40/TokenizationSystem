package com.TokenizationSys.Tokenization.IDV;
/**
*过程三：发卡行通过Tokenization系统回复给ID&V模块的数据
*/

public class MsgOfIssuerToIdv {
	/*请求状态*/
	private boolean reqStatus = false;
	/*原因代码*/
	private String reasonCode = "";
	/*原因代码长度*/
	private int reasonCodeLen = reasonCode.length();
	
	/*IssuerToIdv的构造函数*/
	public MsgOfIssuerToIdv(boolean reqStatus, int reasonCodeLen, String reasonCode) {
		this.reqStatus = reqStatus;
		this.reasonCodeLen = reasonCodeLen;
		this.reasonCode = reasonCode;
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


	public void setReqStatus(boolean reqStatus) {
		this.reqStatus = reqStatus;
	}

	public void setReasonCodeLen(int reasonCodeLen) {
		this.reasonCodeLen = reasonCodeLen;
	}

	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}
	
	
}
