package com.TokenizationSys.Tokenization;
/**
*�����ģ�ID&Vģ�����շ��ظ�Tokenizationϵͳ�Ľ������
*/

public class IdvReslt {
	/*ָ��Token��������*/
	int assigTokAssuLevel;
	/*����״̬*/
	boolean reqStatus;
	/*ԭ����볤��*/
	int reasonCodeLen;
	/*ԭ�����*/
	String reasonCode;
	
	/*IdvReslt�Ĺ��캯��*/
	public IdvReslt(int assigTokAssuLevel, boolean reqStatus, int reasonCodeLen,
			String reasonCode) {
		this.assigTokAssuLevel = assigTokAssuLevel;
		this.reqStatus = reqStatus;
		this.reasonCodeLen = reasonCodeLen;
		this.reasonCode = reasonCode;
	}
}
