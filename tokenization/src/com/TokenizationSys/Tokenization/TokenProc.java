package com.TokenizationSys.Tokenization;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import com.TokenizationSys.DB.CardholderInfoBuilder;
import com.TokenizationSys.DB.ResponseCode;
import com.TokenizationSys.DB.Vault;
import net.sf.json.JSONObject;

/**
* Tokenization���̴���ģ��
*/
public class TokenProc {
	/*Token����ID*/
	String tokReqId = "";
	/*PAN*/
	private String pan = "";
	/*PAN�ĳ���*/	
	private int panLen = pan.length();

	/*Tokenizationģ�����շ��ظ�Tokenizationϵͳ�Ľ������*/
	MsgOfTokReslt tr = null;
	/*Vault��Ķ������ڲ���Vault������*/
	Vault vault = null;
	
	/*Tokenizationģ��Ĺ��캯��*/
	public TokenProc(int panLen, String pan, String tokReqId) {
		this.panLen = panLen;
		this.pan = pan;
		this.tokReqId = tokReqId;
		
		vault = Vault.getVault();
	}
	
	/*���ø÷�������Tokenization���̣�����Tokenization�����Tokenizationϵͳ*/
	public MsgOfTokReslt reqTok(int assigTokAssuLevel) {
        
		/*����Token����λ*/
		String head = null;
		/*Token�Ƿ�ͨ��LuhnУ��ı�־*/
		boolean isPass;
		/*����Token���м�λ�������ܳ��ȼ�����λ�ټ�������λ*/
		int middleDigit = panLen - 5;
		/*����Token���м���*/
		String middle;
		/*����Token�ĺ���λ*/
		String lastFour;
		/*Token�ĳ���*/	
		int tokenLen;
		/*Token*/
	    String token = "";
	    /*Token����Ч����*/
	    int tokenExpDate = -1;
	    
	    JSONObject js1;
	    CardholderInfoBuilder index1 = new CardholderInfoBuilder();
	    JSONObject pkg1;
		int responseCode1;

        	do {
    	    	do {
    				do { 
    					head = new RanBitGenerator(1).getRanBits(); 
    				}
    				while((head.equals("0")) || (head.equals("3")) || (head.equals("4")) 
    						|| (head.equals("5")) || (head.equals("6")));
    				//System.out.println(head);
    					

    				middle = new RanBitGenerator(middleDigit).getRanBits();
    				//System.out.println(middle);
    				lastFour = pan.substring(pan.length() - 4, pan.length());
    				//System.out.println(lastFour);
    				token = head + middle + lastFour;
    				System.out.println("token:" + token);
    				
    				isPass = new LuhnCheck().passLuhnCheck(token);
    			}
    			while(isPass);
    	    	
    	    	
    	    	index1.put("token", token);
    			index1.put("trId", tokReqId);
    			js1 = index1.build();
    			pkg1 = vault.isToken2TrExist(js1);
    			responseCode1 = pkg1.getInt("responseCode");
    	    }
    	    while(ResponseCode.TOKEN_TRID_ALREADY_EXIST == responseCode1);
	    
        tokenLen = token.length();
        /*��ȡ��ǰʱ�䣬��ת����YYMM�ĸ�ʽ*/
        Calendar cal = Calendar.getInstance();
		//����ľ��ǰѵ�ǰ���ڼ�assigTokAssuLevel����
		cal.add(Calendar.MONTH, assigTokAssuLevel);
		SimpleDateFormat format = new SimpleDateFormat("yyMM");
		//System.out.println("now is:" + format.format(Calendar.getInstance().getTime()));
		
        /*����Token����Ч����*/
        
        tokenExpDate = Integer.parseInt(format.format(cal.getTime()));
        System.out.println("tokenExpDate:" + tokenExpDate);
        
        JSONObject js2;
        CardholderInfoBuilder insert2 = new CardholderInfoBuilder();
        insert2.put("pan", pan);
        insert2.put("trId", tokReqId);
        insert2.put("token", token);
        insert2.put("tokenLen", tokenLen);
        insert2.put("tokenExpiryTime", tokenExpDate);
        js2 = insert2.build();
        System.out.println(js2.toString());
        
    	JSONObject pkg2 = vault.insertToken(js2);
    	
    	int responseCode2 = pkg2.getInt("responseCode");
    	if(ResponseCode.INSERT_TOKEN_TOKENLEN_SUCCESS == responseCode2) {
    		System.out.println("����token,tokenLen,tokenExpDate�ɹ�");
    	}
    	else if (ResponseCode.INSERT_TOKEN_TOKENLEN_ERROR == responseCode2) {
    		System.out.println("����token,tokenLen,tokenExpDateʧ��");
    	}

		tr = new MsgOfTokReslt(tokenLen, token, tokenExpDate);
		return tr;
	}
	
	public boolean passTokenKey(String tokenKey) {
		CardholderInfoBuilder index = new CardholderInfoBuilder();
	    index.put("pan", pan);
		index.put("trId", tokReqId);
		index.put("sharedKey", tokenKey);
		JSONObject js = index.build();
		JSONObject pkg = vault.setSharedKey(js);
		int responseCode = pkg.getInt("responseCode");
		if(ResponseCode.SUCCESS == responseCode) {
			System.out.println("��Vault��д��sharedKey�ɹ�");
			return true;
		}
		else {
			System.out.println("��Vault��д��sharedKeyʧ��");
			return false;
		}
	}
}


