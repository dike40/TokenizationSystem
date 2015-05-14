package com.TokenizationSys.DeTokenization;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.*;  
import javax.crypto.spec.*;  

/**
 * 
 * @author yangxiao
 * 
 * AES128 �㷨������ģʽΪECB�����ģʽΪ pkcs7��ʵ�ʾ���pkcs5��
 * 
 *
 */
public class SecureAlgorithm {
	
	static final String algorithmStr="AES/ECB/PKCS5Padding";
	
	static private KeyGenerator keyGen;
	
	static private Cipher cipher;
	
	static boolean isInited=false;
	
	//��ʼ��
	static private void init()
	{
		
		//��ʼ��keyGen
		try {
			keyGen=KeyGenerator.getInstance("AES");
		} catch (NoSuchAlgorithmException e) {
	
			e.printStackTrace();
		}
		keyGen.init(128);
		
		//��ʼ��cipher
		try {
			cipher=Cipher.getInstance(algorithmStr);
		} catch (NoSuchAlgorithmException e) {
			
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			
			e.printStackTrace();
		}
		
		isInited=true;
	}
	
	public static byte[] GenKey()
	{
		if(!isInited)//���û�г�ʼ����,���ʼ��
		{
			init();
		}
		return keyGen.generateKey().getEncoded();
	}
	
	public static byte[] Encrypt(byte[] content,byte[] keyBytes)
	{
		byte[] encryptedText=null;
		
		if(!isInited)//Ϊ��ʼ��
		{
			init();
		}
		
		Key key=new SecretKeySpec(keyBytes,"AES");
		
		try {
			cipher.init(Cipher.ENCRYPT_MODE, key);
		} catch (InvalidKeyException e) {
		
			e.printStackTrace();
		}
		
		try {
			encryptedText=cipher.doFinal(content);
		} catch (IllegalBlockSizeException e) {
		
			e.printStackTrace();
		} catch (BadPaddingException e) {
		
			e.printStackTrace();
		}
		
		return encryptedText;
	}
	
	//����Ϊbyte[]
	public static byte[] DecryptToBytes(byte[] content,byte[] keyBytes)
	{
		byte[] originBytes=null;
		if(!isInited)
		{
			init();
		}
		
		Key key=new SecretKeySpec(keyBytes,"AES");
		
		try {
			
			cipher.init(Cipher.DECRYPT_MODE, key);
		} catch (InvalidKeyException e) {
		
			e.printStackTrace();
		}
		
		//����
		try {
			/*	int kk =0;
			for (byte i:content) {
				System.out.println("num:"+ ++kk +"byte:"+i);
			}*/
			originBytes=cipher.doFinal(content);
		} catch (IllegalBlockSizeException e) {
		
			e.printStackTrace();
		} catch (BadPaddingException e) {
		
			e.printStackTrace();
		}
		
		return originBytes;
	}
}
