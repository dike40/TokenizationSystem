package com.TokenizationSys.Tokenization;
/**
* Tokenizationģ�����շ��ظ�Tokenizationϵͳ�Ľ������
*/
public class MsgOfTokReslt {
	 /*Token*/
    private String token = "";
    /*Token�ĳ���*/	
	private int tokenLen = token.length();
	/*Token����Ч����*/
    int tokenExpDate = -1;
     
    /*MsgOfTokReslt�Ĺ��캯��*/
	public MsgOfTokReslt(int tokenLen, String token, int tokenExpDate) {
		this.tokenLen = tokenLen;
		this.token = token;
		this.tokenExpDate = tokenExpDate;
	}

	public int getTokenLen() {
		return tokenLen;
	}

	public String getToken() {
		return token;
	}
	
	public int getTokenExpDate() {
		return tokenExpDate;
	}

	public void setTokenLen(int tokenLen) {
		this.tokenLen = tokenLen;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public void setTokenExpDate(int tokenExpDate) {
		this.tokenExpDate = tokenExpDate;
	}
	
}
