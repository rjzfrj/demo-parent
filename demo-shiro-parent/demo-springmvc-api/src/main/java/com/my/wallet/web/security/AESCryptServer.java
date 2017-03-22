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
public class AESCryptServer {

	private static String iv = "123456!@#$%qwert";// 虚拟的 iv (需更改)
	private static IvParameterSpec ivspec;
	private static SecretKeySpec keyspec;
	private static Cipher cipher_encrypt;
	private static Cipher cipher_decrypt;
	public static String charset = "UTF-8";
	private static String SecretKey = "123456!@#$%qwert";// 虚拟的 密钥 (需更改）
							          
	static {
		try {
			ivspec = new IvParameterSpec(iv.getBytes(charset));
			keyspec = new SecretKeySpec(SecretKey.getBytes(charset), "AES");
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
	
	/**
	  * <p>Description:加密</p>
	  * @param text
	  * @return
	  * @author lzf
	  * @date 2016年3月24日 下午3:45:22
	 */
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
	
	/**
	  * <p>Description:解密</p>
	  * @param text
	  * @return
	  * @author lzf
	  * @date 2016年3月24日 下午3:32:56
	 */
	public static String decryptBase64(String text) {
		if(text==null){
			return text;
		}
		try {
			return new String(decrypt(text),charset).trim();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return text;
	}
	

	public static byte[] decrypt(String code) throws Exception {
		if (code == null || code.length() == 0){
			throw new Exception("Empty string");
		}
		byte[] decrypted = null;
		try {
			decrypted = decrypt(Base64.decode(code));
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
		String content = "130726198602146911"; 
		String encryptStr = AESCryptServer.encryptBase64(content); //"OdlqZK59SkhjDBO3mtVdlyAq7ii+yilpafjfixGDpHPclVWcfGeTtvkDn3DJ+waF5X1Ho9LeualksTJlv2WiejvBDRK+7+V3eMVr5BvVUJg3VZiXqvhFP7FGP2XzbRJl";//
		encryptStr="PRmWSfHbWlzHUp7Cs/Hq14e8wH+ZhNZFiOFmJ6vslEM=";
		System.out.println("加密base64:"+encryptStr);
//		加密base64:HxxYppCK6bguuUs4bSwGZE8WOV31G9UOI1+93O7y9smZTff+xYLkyVPov5y3bHmfdWXnOPU9VNed9TU/fN/zN4OiTmvHPiSYLDC/BYvH/w+DcXAJyfvf6TKXZqsuPOhL
//		解密base64:http://www.yulebaodian.cn/upload/hls/20140704112925412905/20140704112925412905.m3u8         
		String decryptStr = AESCryptServer.decryptBase64("hO++RxU1pUoEhxuH9ZI5R5dVo3Y1QWWXX4xUw+V6guU=");
		System.out.println("解密base64:"+decryptStr);
//		
		
		/****************************设置加密数据**********************************/
//		//1先解密秘钥
		String tcpSecret="hO++RxU1pUoEhxuH9ZI5R5dVo3Y1QWWXX4xUw+V6guU=";
		String tcpSecretStr = AESCryptServer.decryptBase64(tcpSecret);
		System.out.println("秘钥解密base64密码是:"+tcpSecretStr);
		String idCard="130726198602146910";
		
		//加密内容和解密内容都是用下面的方法
		String idCardMw=new AESCrypt(tcpSecretStr).encryptBase64(idCard);
		System.out.println("身份证号：=="+idCard+"加密后："+idCardMw);
		String idCardJM=new AESCrypt(tcpSecretStr).decryptBase64(idCardMw);
		System.out.println("身份证号密文：=="+idCardMw+"解密后："+idCardJM);
		String yerarmor="pZJUQb3amJJDYN4pcYtCEA==";
		String cvv="AjypXF0O7knKdmV/f5FNEA==";
		System.out.println("yerarmor:"+AESCryptServer.decryptBase64(yerarmor));
		System.out.println("cvv:"+AESCryptServer.decryptBase64(cvv));
		
		
	}
	
	
}

