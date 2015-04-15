package com.TokenizationSys.Tokenization;
/**
*过程三：发卡行通过Tokenization系统回复给ID&V模块的数据
*/

public class IssuerToIdv {
	/*请求状态*/
	boolean reqStatus;
	/*原因代码长度*/
	int reasonCodeLen;
	/*原因代码*/
	String reasonCode;
	
	/*IssuerToIdv的构造函数*/
	public IssuerToIdv(boolean reqStatus, int reasonCodeLen, String reasonCode) {
		this.reqStatus = reqStatus;
		this.reasonCodeLen = reasonCodeLen;
		this.reasonCode = reasonCode;
	}
}
