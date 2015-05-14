package com.TokenizationSys.Tokenization;
/**
* Tokenization模块最终返回给Tokenization系统的结果数据
*/
public class MsgOfTokReslt {
	 /*Token*/
    private String token = "";
    /*Token的长度*/	
	private int tokenLen = token.length();
	/*Token的有效日期*/
    int tokenExpDate = -1;
     
    /*MsgOfTokReslt的构造函数*/
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
