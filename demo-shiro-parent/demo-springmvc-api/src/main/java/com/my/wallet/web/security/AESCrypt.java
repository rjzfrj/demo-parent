package com.my.wallet.web.security;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 
 * @author Administrator
 */
public class AESCrypt {

	private static String iv = "123456!@#$%qwert";// 虚拟的 iv (需更改)
	private static IvParameterSpec ivspec;
	private static SecretKeySpec keyspec;
	private static Cipher cipher_encrypt;
	private static Cipher cipher_decrypt;
	public static String charset = "UTF-8";

	
	/**
	 * 构造函数
	 * @param keyText
	 */
	public AESCrypt(String secretKey) {
		try {
			ivspec = new IvParameterSpec(iv.getBytes(charset));
			keyspec = new SecretKeySpec(secretKey.getBytes(charset), "AES");
			cipher_encrypt = Cipher.getInstance("AES/CBC/NoPadding");
			cipher_encrypt.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
			cipher_decrypt = Cipher.getInstance("AES/CBC/NoPadding");
			cipher_decrypt.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (InvalidAlgorithmParameterException e) {
			e.printStackTrace();
		}
	}
	

	public static byte[] encrypt(String text) throws Exception {
		if (text == null || text.length() == 0){
			throw new Exception("Empty string");
		}
		byte[] encrypted = null;
		try {
			encrypted = cipher_encrypt.doFinal(padString(text).getBytes(charset));
		} catch (Exception e) {
			throw new Exception("[encrypt] " + e.getMessage());
		}

		return encrypted;
	}
	
	public static String encryptBase64(String text) {
		if(text==null){
			return text;
		}
		try {
			return Base64.encode(encrypt(text));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return text;
	}
	
	public static String decryptBase64(String text) {
		if(text==null){
			return text;
		}
		try {
			return new String(decrypt(text),charset).trim();
		} catch (Exception e) {
		}
		return text;
	}
	

	public static byte[] decrypt(String code) throws Exception {
		if (code == null || code.length() == 0){
			throw new Exception("Empty string");
		}
		byte[] decrypted = null;
		try {
			byte[] codes=Base64.decode(code);
			decrypted = decrypt(codes);
		} catch (Exception e) {
			throw new Exception("[decrypt] " + e.getMessage());
		}
		return decrypted;
	}

	public static byte[] decrypt(byte[] code) throws Exception {
		byte[] decrypted = null;
		try {
			decrypted = cipher_decrypt.doFinal(code);
		} catch (Exception e) {
			throw new Exception("[decrypt] " + e.getMessage());
		}
		return decrypted;
	}
	

	private static String padString(String source) {
		char paddingChar = ' ';
		int size = 16;
		int x = source.length() % size;
		int padLength = size - x;

		for (int i = 0; i < padLength; i++) {
			source += paddingChar;
		}

		return source;
	}
	
	public static void main(String[] args) throws Exception {

		
		/****************************设置加密数据**********************************/
		//1先解密秘钥
		String tcpSecret="hO++RxU1pUoEhxuH9ZI5R5dVo3Y1QWWXX4xUw+V6guU=";
		String userKeyText = new AESCrypt("123456!@#$%qwert").decryptBase64(tcpSecret);
		System.out.println("秘钥解密base64:"+userKeyText);
		//用解完密的传输秘钥得到原始秘钥进行加密
		String str="13439915863";
		AESCrypt aesCrypt = new AESCrypt(userKeyText);
		String jiamiParaValue = aesCrypt.encryptBase64(str);
		System.out.println("str加密前"+str+"加密后"+jiamiParaValue);
		String jiemiSecretStr = aesCrypt.decryptBase64(jiamiParaValue);
		System.out.println("str加密后"+jiamiParaValue+"解密后"+jiemiSecretStr);
		
		
	}
	
	
}

