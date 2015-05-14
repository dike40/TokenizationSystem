package com.TokenizationSys.Utils;

public class convertByteAndString {

	/**
	 * 
	 * for tokenization usage 
	 * String to byte
	 * byte to string
	 * 
	 * each char of string to a byte! not 2 chars to a byte
	 * 
	 * */
	
	public static String byteToString(byte[] buf){
		StringBuilder sb = new StringBuilder(buf.length);
		String tmp = "";
		for (int i = 0; i < buf.length; i++) {
			tmp = Integer.toHexString(0xff & buf[i]);
			sb.append(tmp);
		}
		return sb.toString();
				
	}
	
	public static byte[] stringToBte(String hexString){
		
		char[] hex = hexString.toCharArray();
		int length = hex.length ;
		byte[] rawData = new byte[length];
		for (int i = 0; i < length; i++) {			
			int high = 0;
			int low = Character.digit(hex[i], 16);			
			int value = (high << 4) | low;
			if (value > 127) {
				value -= 256;
			}
			
			rawData[i] = (byte) value;
		}
		return rawData;
	}
}
