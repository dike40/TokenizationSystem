package com.TokenizationSys.DeTokenization;
/**
* De-tokenizationģ�����շ��ظ�Tokenizationϵͳ�Ľ������
*/
public class MsgOfDeTokReslt {
	/*����״̬*/
	private boolean reqStatus = false;
	/*ԭ�����*/
	private String reasonCode = "";
	/*ԭ����볤��*/
	private int reasonCodeLen = reasonCode.length();
	/*PAN*/
	private String pan = "";
	/*PAN�ĳ���*/	
	private int panLen = pan.length();
    /*PAN����Ч����*/
	private int panExpDate = -1;
    
    /*MsgOfDeTokReslt�Ĺ��캯��*/
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
