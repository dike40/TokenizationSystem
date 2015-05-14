package com.TokenizationSys.Tokenization.IDV;
/**
*�����ģ�ID&Vģ�����շ��ظ�Tokenizationϵͳ�Ľ������
*/

public class MsgOfIdvReslt {
	/*ָ��Token��������*/
	private int assigTokAssuLevel = -1;
	/*����״̬*/
	private boolean reqStatus = false;
	/*ԭ�����*/
	private String reasonCode = "";
	/*ԭ����볤��*/
	private int reasonCodeLen = reasonCode.length();
   	/*Token*/
	private String token = "";
	 /*Token�ĳ���*/	
	private int tokenLen = token.length();

	
	/*MsgOfIdvReslt�Ĺ��캯��*/
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
