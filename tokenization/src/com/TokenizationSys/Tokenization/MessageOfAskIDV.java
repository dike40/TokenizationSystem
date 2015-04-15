package com.TokenizationSys.Tokenization;
/**
 * 
 * ����һ������IDV����ʱ�ı���Ӧ����������
 * */
public class MessageOfAskIDV {

	/*PAN�ĳ���*/	
	private int panLen;
	/*PAN*/
	private String pan;
    /*PAN����Ч����*/
	private  int panExpDate;
    /*Tokenλ��*/
	private int tokenLoc;
    /*Э��*/
	private int protocol;
    /*POS���뷽ʽ*/
	private int posEntryMode;
    /*����Token��������*/
	private int reqTokAssuLevel;
    /*Token����ID*/
	private String tokReqId;
    /*�˻���֤���*/
	private  boolean accotVerReslt;
    /*�˻���֤���ó���*/
	private int accotVerRefLen;
    /*�˻���֤����*/
	private String accotVerRef;
    /*Token���󷽷�������*/
	private int tokReqRiskSco;
    /*��ַ����ָʾ��*/
	private  boolean addrMisIndic;
    /*�ֿ������ݳ���*/
	private int cardhldDataLen;
    /*�ֿ�������*/
	private String cardhldData;
    /*�豸��Ϣ����*/
	private int devInfolen;
    /*�豸��Ϣ*/
	private String devInfo;
    /*Token���*/
	private int tokDomRestrtContrl;
	/*�̻�ID*/
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
