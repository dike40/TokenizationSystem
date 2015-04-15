package com.TokenizationSys.Tokenization;
/**
*过程二：ID&V模块通过Tokenization系统发送给发卡行的查询报文类
*/

public class IdvToIssuer {
	/*PAN的长度*/
	int panLen;
	/*PAN*/
    String pan;
    /*PAN的有效日期*/
    int panExpDate;
    /*Token请求指示器*/
	private static final String tokReqIndic = "Tok_Req_ID&V";
	/*Token担保数据长度*/
	int tokAssuDataLen;
	/*Token担保数据*/
	String tokAssuData;
	
	/*IdvToIssuer的构造函数*/
	public IdvToIssuer(int panLen, String pan, int panExpDate,
			int tokAssuDataLen, String tokAssuData) {
		this.panLen = panLen;
		this.pan = pan;
		this.panExpDate = panExpDate;
		this.tokAssuDataLen = tokAssuDataLen;
		this.tokAssuData = tokAssuData;
	}
}
