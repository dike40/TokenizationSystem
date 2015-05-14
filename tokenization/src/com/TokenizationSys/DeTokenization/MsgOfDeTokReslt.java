package com.TokenizationSys.DeTokenization;
/**
* De-tokenization模块最终返回给Tokenization系统的结果数据
*/
public class MsgOfDeTokReslt {
	/*请求状态*/
	private boolean reqStatus = false;
	/*原因代码*/
	private String reasonCode = "";
	/*原因代码长度*/
	private int reasonCodeLen = reasonCode.length();
	/*PAN*/
	private String pan = "";
	/*PAN的长度*/	
	private int panLen = pan.length();
    /*PAN的有效日期*/
	private int panExpDate = -1;
    
    /*MsgOfDeTokReslt的构造函数*/
	public MsgOfDeTokReslt(boolean reqStatus, int reasonCodeLen, String reasonCode,
			int panLen, String pan, int panExpDate) {
		this.reqStatus = reqStatus;
		this.reasonCodeLen = reasonCodeLen;
		this.reasonCode = reasonCode;
		this.panLen = panLen;
		this.pan = pan;
		this.panExpDate = panExpDate;
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

	public int getPanLen() {
		return panLen;
	}

	public String getPan() {
		return pan;
	}

	public int getPanExpDate() {
		return panExpDate;
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

	public void setPanLen(int panLen) {
		this.panLen = panLen;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public void setPanExpDate(int panExpDate) {
		this.panExpDate = panExpDate;
	}
	
}
