package com.TokenizationSys.Tokenization;
/**
*��������������ͨ��Tokenizationϵͳ�ظ���ID&Vģ�������
*/

public class IssuerToIdv {
	/*����״̬*/
	boolean reqStatus;
	/*ԭ����볤��*/
	int reasonCodeLen;
	/*ԭ�����*/
	String reasonCode;
	
	/*IssuerToIdv�Ĺ��캯��*/
	public IssuerToIdv(boolean reqStatus, int reasonCodeLen, String reasonCode) {
		this.reqStatus = reqStatus;
		this.reasonCodeLen = reasonCodeLen;
		this.reasonCode = reasonCode;
	}
}
