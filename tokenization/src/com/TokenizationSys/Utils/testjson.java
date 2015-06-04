package com.TokenizationSys.Utils;

public class testjson {
	
	public static void main(String[] arg){
		
		String tokenString = "9636343603819040080";
		byte[] result = convertByteAndString.stringToBte(tokenString);
		for(int i :result){
			System.out.println(i);
		}
		
		
		System.out.println(convertByteAndString.byteToString(result));
	}
	
	

}
