package com.TokenizationSys.Tokenization;

import com.TokenizationSys.DeTokenization.SecureAlgorithm;

public class GenerateSharekey {
	private byte[] keyBytes = null;
	private static GenerateSharekey gs = new GenerateSharekey();
	
	public static GenerateSharekey getGS(){
		return gs;
	}
	public void generKey() {
		keyBytes = SecureAlgorithm.GenKey();
	}
	
	
	public  byte[] getKeyBytes() {
		return keyBytes;
	}
	
}
