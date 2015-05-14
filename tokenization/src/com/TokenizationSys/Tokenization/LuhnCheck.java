package com.TokenizationSys.Tokenization;
/*
 * LuhnУ��ģ�飬ģ10У��
 */
public class LuhnCheck {

	public boolean passLuhnCheck(String accoutNum) {
		/*������˺ŵĳ���*/
		int len = accoutNum.length();
		/*��������˺�ÿһλ��ȡ��������������*/
		int[] Digit = new int[len];
		int sumOdd = 0;
		int sumEven = 0;
		for (int i = 0; i < len; i++) {  
			Digit[i] = Integer.parseInt(accoutNum.substring(len - i - 1, len - i));// ����ĩһλ��ʼ��ȡ��ÿһλ�ϵ���ֵ  
            //System.out.println("��" + i + "λ�����ǣ�" + Digit[i]);  
        }
		for (int i = 0; i < len; i++) {  
			if(i % 2 == 0){
				if ((Digit[i] * 2) > 9) 
	            {
	            	Digit[i] = Digit[i] * 2 - 9; 
	            	//System.out.println("��" + i + "λ�����ǣ�" + Digit[i]);
	            }	 
	            else {
	            	Digit[i] = Digit[i] * 2;  
	            	//System.out.println("��" + i + "λ�����ǣ�" + Digit[i]);
	            } 	
	            sumEven += Digit[i]; 
	            //System.out.println("��ʱż����sumEven�ǣ�" + sumEven);
            }
			else {
				sumOdd += Digit[i];
	            //System.out.println("��" + i + "λ�����ǣ�" + Digit[i]);
	            //System.out.println("��ʱ������sumOdd�ǣ�" + sumOdd);
            }
        }
		//System.out.println("ż��λ�ĺ��ǣ�" + sumEven);
        //System.out.println("����λ�ĺ��ǣ�" + sumOdd);  
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
