package com.TokenizationSys.Tokenization;
/**
 * 
 * 过程一：发起IDV请求时的报文应包含的数据
 * */
public class MessageOfAskIDV {

	/*PAN的长度*/	
	private int panLen;
	/*PAN*/
	private String pan;
    /*PAN的有效日期*/
	private  int panExpDate;
    /*Token位置*/
	private int tokenLoc;
    /*协议*/
	private int protocol;
    /*POS进入方式*/
	private int posEntryMode;
    /*请求Token担保级别*/
	private int reqTokAssuLevel;
    /*Token请求方ID*/
	private String tokReqId;
    /*账户验证结果*/
	private  boolean accotVerReslt;
    /*账户验证引用长度*/
	private int accotVerRefLen;
    /*账户验证引用*/
	private String accotVerRef;
    /*Token请求方风险评分*/
	private int tokReqRiskSco;
    /*地址错配指示器*/
	private  boolean addrMisIndic;
    /*持卡人数据长度*/
	private int cardhldDataLen;
    /*持卡人数据*/
	private String cardhldData;
    /*设备信息长度*/
	private int devInfolen;
    /*设备信息*/
	private String devInfo;
    /*Token域控*/
	private int tokDomRestrtContrl;
	/*商户ID*/
	private String merchId;

	public MessageOfAskIDV(int panLen, String pan, int panExpDate, int tokenLoc,
			int protocol, int posEntryMode, int reqTokAssuLevel,
			String tokReqId, boolean accotVerReslt, int accotVerRefLen,
			String accotVerRef, int tokReqRiskSco, boolean addrMisIndic,
			int cardhldDataLen, String cardhldData, int devInfolen,
			String devInfo, int tokDomRestrtContrl, String merchId) {
		//super();
		this.panLen = panLen;
		this.pan = pan;
		this.panExpDate = panExpDate;
		this.tokenLoc = tokenLoc;
		this.protocol = protocol;
		this.posEntryMode = posEntryMode;
		this.reqTokAssuLevel = reqTokAssuLevel;
		this.tokReqId = tokReqId;
		this.accotVerReslt = accotVerReslt;
		this.accotVerRefLen = accotVerRefLen;
		this.accotVerRef = accotVerRef;
		this.tokReqRiskSco = tokReqRiskSco;
		this.addrMisIndic = addrMisIndic;
		this.cardhldDataLen = cardhldDataLen;
		this.cardhldData = cardhldData;
		this.devInfolen = devInfolen;
		this.devInfo = devInfo;
		this.tokDomRestrtContrl = tokDomRestrtContrl;
		this.merchId = merchId;
		
		//chd = new CardhldData(cardhldData);
	}
	

}
