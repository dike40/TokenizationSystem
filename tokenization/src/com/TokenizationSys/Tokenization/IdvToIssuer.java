package com.TokenizationSys.Tokenization;
/**
*���̶���ID&Vģ��ͨ��Tokenizationϵͳ���͸������еĲ�ѯ������
*/

public class IdvToIssuer {
	/*PAN�ĳ���*/
	int panLen;
	/*PAN*/
    String pan;
    /*PAN����Ч����*/
    int panExpDate;
    /*Token����ָʾ��*/
	private static final String tokReqIndic = "Tok_Req_ID&V";
	/*Token�������ݳ���*/
	int tokAssuDataLen;
	/*Token��������*/
	String tokAssuData;
	
	/*IdvToIssuer�Ĺ��캯��*/
	public IdvToIssuer(int panLen, String pan, int panExpDate,
			int tokAssuDataLen, String tokAssuData) {
		this.panLen = panLen;
		this.pan = pan;
		this.panExpDate = panExpDate;
		this.tokAssuDataLen = tokAssuDataLen;
		this.tokAssuData = tokAssuData;
	}
}
