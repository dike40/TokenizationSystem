package com.TokenizationSys.Tokenization;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import com.TokenizationSys.DB.CardholderInfoBuilder;
import com.TokenizationSys.DB.ResponseCode;
import com.TokenizationSys.DB.Vault;
import net.sf.json.JSONObject;

/**
* Tokenization过程处理模块
*/
public class TokenProc {
	/*Token请求方ID*/
	String tokReqId = "";
	/*PAN*/
	private String pan = "";
	/*PAN的长度*/	
	private int panLen = pan.length();

	/*Tokenization模块最终返回给Tokenization系统的结果数据*/
	MsgOfTokReslt tr = null;
	/*Vault类的对象，用于操作Vault中数据*/
	Vault vault = null;
	
	/*Tokenization模块的构造函数*/
	public TokenProc(int panLen, String pan, String tokReqId) {
		this.panLen = panLen;
		this.pan = pan;
		this.tokReqId = tokReqId;
		
		vault = Vault.getVault();
	}
	
	/*调用该方法请求Tokenization过程，返回Tokenization结果给Tokenization系统*/
	public MsgOfTokReslt reqTok(int assigTokAssuLevel) {
        
		/*生成Token的首位*/
		String head = null;
		/*Token是否通过Luhn校验的标志*/
		boolean isPass;
		/*生成Token的中间位数，是总长度减掉首位再减掉后四位*/
		int middleDigit = panLen - 5;
		/*生成Token的中间数*/
		String middle;
		/*生成Token的后四位*/
		String lastFour;
		/*Token的长度*/	
		int tokenLen;
		/*Token*/
	    String token = "";
	    /*Token的有效日期*/
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
        /*获取当前时间，并转换成YYMM的格式*/
        Calendar cal = Calendar.getInstance();
		//下面的就是把当前日期加assigTokAssuLevel个月
		cal.add(Calendar.MONTH, assigTokAssuLevel);
		SimpleDateFormat format = new SimpleDateFormat("yyMM");
		//System.out.println("now is:" + format.format(Calendar.getInstance().getTime()));
		
        /*设置Token的有效日期*/
        
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
    		System.out.println("插入token,tokenLen,tokenExpDate成功");
    	}
    	else if (ResponseCode.INSERT_TOKEN_TOKENLEN_ERROR == responseCode2) {
    		System.out.println("插入token,tokenLen,tokenExpDate失败");
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
			System.out.println("向Vault中写入sharedKey成功");
			return true;
		}
		else {
			System.out.println("向Vault中写入sharedKey失败");
			return false;
		}
	}
}


