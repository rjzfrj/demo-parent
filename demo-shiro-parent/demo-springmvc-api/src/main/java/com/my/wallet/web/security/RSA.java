package com.my.wallet.web.security;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

public class RSA {
	
	public static final String KEY_ALGORITHM = "RSA";
	public static final String SIGNATURE_ALGORITHM = "SHA1WithRSA";

	public static final String PUBLIC_KEY = "RSAPublicKey";
	public static final String PRIVATE_KEY = "RSAPrivateKey";

	public static byte[] decryptBASE64(String key) throws Exception {
//		return (new BASE64Decoder()).decodeBuffer(key);
		return Base64.decode(key);
	}

	public static String encryptBASE64(byte[] key) throws Exception {
//		return (new BASE64Encoder()).encodeBuffer(key);
		return Base64.encode(key);
	}
	
	public static String sign(String data, String privateKey) {
		try {
			// 解密由base64编码的私钥
			byte[] keyBytes = decryptBASE64(privateKey);
			// 构造PKCS8EncodedKeySpec对象
			PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
			// KEY_ALGORITHM 指定的加密算法
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			// 取私钥匙对象
			PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
			// 用私钥对信息生成数字签名
			Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
			signature.initSign(priKey);
			signature.update(data.getBytes("UTF-8"));
			return encryptBASE64(signature.sign());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Map<String, Object> initKey() throws Exception {
		KeyPairGenerator keyPairGen = KeyPairGenerator
				.getInstance(KEY_ALGORITHM);
		keyPairGen.initialize(1024);
		KeyPair keyPair = keyPairGen.generateKeyPair();
		// 公钥
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		// 私钥
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		Map<String, Object> keyMap = new HashMap<String, Object>(2);
		keyMap.put(PUBLIC_KEY, publicKey);
		keyMap.put(PRIVATE_KEY, privateKey);
		return keyMap;
	}
	
	public static boolean verify(String data, String publicKey, String sign) {
		try {
			// 解密由base64编码的公钥
			byte[] keyBytes = decryptBASE64(publicKey);
			// 构造X509EncodedKeySpec对象
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
			// KEY_ALGORITHM 指定的加密算法
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			// 取公钥匙对象
			PublicKey pubKey = keyFactory.generatePublic(keySpec);
			Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
			signature.initVerify(pubKey);
			signature.update(data.getBytes("UTF-8"));
			// 验证签名是否正常
			return signature.verify(decryptBASE64(sign));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		Map<String, Object> keyMap = RSACoder.initKey();
		String publicKey = RSACoder.getPublicKey(keyMap);
		String privateKey = RSACoder.getPrivateKey(keyMap);
		
		System.out.println("publicKey:");
		System.out.println(publicKey);
		System.out.println();
		System.out.println("privateKey:");
		System.out.println(privateKey);
		System.out.println();
		String source = "outOrderId=10001&orderAmt=0.1&notifyUrl=http://192.168.1.63:8080/app_test/test&orderTime=20150625180339893";
		String sign = sign(source, privateKey);
		System.out.println("sign source:");
		System.out.println(source);
		System.out.println();
		System.out.println("sign result:");
		System.out.println(sign);
		boolean b = verify(source, publicKey, sign);
		System.out.println("check result:");
		System.out.println(b);
		
		
		
		
	}
}
