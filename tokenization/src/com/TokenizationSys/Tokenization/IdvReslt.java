package com.TokenizationSys.Tokenization;
/**
*过程四：ID&V模块最终返回给Tokenization系统的结果数据
*/

public class IdvReslt {
	/*指定Token担保级别*/
	int assigTokAssuLevel;
	/*请求状态*/
	boolean reqStatus;
	/*原因代码长度*/
	int reasonCodeLen;
	/*原因代码*/
	String reasonCode;
	
	/*IdvReslt的构造函数*/
	public IdvReslt(int assigTokAssuLevel, boolean reqStatus, int reasonCodeLen,
			String reasonCode) {
		this.assigTokAssuLevel = assigTokAssuLevel;
		this.reqStatus = reqStatus;
		this.reasonCodeLen = reasonCodeLen;
		this.reasonCode = reasonCode;
	}
}
