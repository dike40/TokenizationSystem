package com.TokenizationSys.Tokenization;
/*
 * Luhn校验模块，模10校验
 */
public class LuhnCheck {

	public boolean passLuhnCheck(String accoutNum) {
		/*待检测账号的长度*/
		int len = accoutNum.length();
		/*将待检测账号每一位提取出来存入数组中*/
		int[] Digit = new int[len];
		int sumOdd = 0;
		int sumEven = 0;
		for (int i = 0; i < len; i++) {  
			Digit[i] = Integer.parseInt(accoutNum.substring(len - i - 1, len - i));// 从最末一位开始提取，每一位上的数值  
            //System.out.println("第" + i + "位数字是：" + Digit[i]);  
        }
		for (int i = 0; i < len; i++) {  
			if(i % 2 == 0){
				if ((Digit[i] * 2) > 9) 
	            {
	            	Digit[i] = Digit[i] * 2 - 9; 
	            	//System.out.println("第" + i + "位数字是：" + Digit[i]);
	            }	 
	            else {
	            	Digit[i] = Digit[i] * 2;  
	            	//System.out.println("第" + i + "位数字是：" + Digit[i]);
	            } 	
	            sumEven += Digit[i]; 
	            //System.out.println("此时偶数和sumEven是：" + sumEven);
            }
			else {
				sumOdd += Digit[i];
	            //System.out.println("第" + i + "位数字是：" + Digit[i]);
	            //System.out.println("此时奇数和sumOdd是：" + sumOdd);
            }
        }
		//System.out.println("偶数位的和是：" + sumEven);
        //System.out.println("奇数位的和是：" + sumOdd);  
        if ((sumOdd + sumEven) % 10 == 0) {
        	System.out.println("pass Luhn"); 
        	return true;
        }    
        else {
        	System.out.println("did not pass Luhn"); 
        	return false;
        }	
	}
}
