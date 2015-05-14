package com.TokenizationSys.Tokenization.IDV;
/**
*���̶���ID&Vģ��ͨ��Tokenizationϵͳ���͸������еĲ�ѯ������
*/

public class MsgOfIdvToIssuer {
	/*PAN*/
	private String pan = "";
	/*PAN�ĳ���*/	
	private int panLen = pan.length();
    /*PAN����Ч����*/
    private int panExpDate = -1;
    /*Token����ָʾ��*/
	private static final String tokReqIndic = "Tok_Req_ID&V";
	/*Token��������*/
	private String tokAssuData = "";
	/*Token�������ݳ���*/
	private int tokAssuDataLen = -1;
	
	
	/*IdvToIssuer�Ĺ��캯��*/
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
