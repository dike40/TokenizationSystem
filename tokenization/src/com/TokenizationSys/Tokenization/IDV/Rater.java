package com.TokenizationSys.Tokenization.IDV;
/**
 * 
 * �����ߵĸ���
 *
 */
abstract class Rater {
	/*��̬�ģ�����Rater�����๲��*/
	static int assigTokAssuLevel = -1; 
	/*��̵�������*/
	protected Rater successRater = null;
	/*���ú�̵������ߵķ���*/
	public void setSuccessRater(Rater successRater) {
		this.successRater = successRater;
	}
	/*�����ķ���*/
	public abstract void Rating(int reqTokAssuLevel, int accVerLevel, int riskScore); 
	/*��ȡָ��Token��������ķ���*/
    public int getAssigTokAssuLevel() {
		return assigTokAssuLevel;
	}
}
