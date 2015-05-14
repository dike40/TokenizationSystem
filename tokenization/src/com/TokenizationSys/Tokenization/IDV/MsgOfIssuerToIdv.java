package com.TokenizationSys.Tokenization.IDV;
/**
*��������������ͨ��Tokenizationϵͳ�ظ���ID&Vģ�������
*/

public class MsgOfIssuerToIdv {
	/*����״̬*/
	private boolean reqStatus = false;
	/*ԭ�����*/
	private String reasonCode = "";
	/*ԭ����볤��*/
	private int reasonCodeLen = reasonCode.length();
	
	/*IssuerToIdv�Ĺ��캯��*/
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
