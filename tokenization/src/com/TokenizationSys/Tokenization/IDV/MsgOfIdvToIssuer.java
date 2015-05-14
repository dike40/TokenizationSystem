package com.TokenizationSys.Tokenization.IDV;
/**
*过程二：ID&V模块通过Tokenization系统发送给发卡行的查询报文类
*/

public class MsgOfIdvToIssuer {
	/*PAN*/
	private String pan = "";
	/*PAN的长度*/	
	private int panLen = pan.length();
    /*PAN的有效日期*/
    private int panExpDate = -1;
    /*Token请求指示器*/
	private static final String tokReqIndic = "Tok_Req_ID&V";
	/*Token担保数据*/
	private String tokAssuData = "";
	/*Token担保数据长度*/
	private int tokAssuDataLen = -1;
	
	
	/*IdvToIssuer的构造函数*/
	public MsgOfIdvToIssuer(int panLen, String pan, int panExpDate,
			int tokAssuDataLen, String tokAssuData) {
		this.panLen = panLen;
		this.pan = pan;
		this.panExpDate = panExpDate;
		this.tokAssuDataLen = tokAssuDataLen;
		this.tokAssuData = tokAssuData;
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

	public static String getTokreqindic() {
		return tokReqIndic;
	}

	public int getTokAssuDataLen() {
		return tokAssuDataLen;
	}

	public String getTokAssuData() {
		return tokAssuData;
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

	public void setTokAssuDataLen(int tokAssuDataLen) {
		this.tokAssuDataLen = tokAssuDataLen;
	}

	public void setTokAssuData(String tokAssuData) {
		this.tokAssuData = tokAssuData;
	}
	
}
