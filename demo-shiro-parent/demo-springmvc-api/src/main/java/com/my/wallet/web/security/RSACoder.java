package com.my.wallet.web.security;

import java.security.Key;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.crypto.Cipher;

/** */
/**
 * RSA安全编码组件
 * 
 * @version 1.0
 * @since 1.0
 */
public class RSACoder {
	public static final String KEY_ALGORITHM = "RSA";
	public static final String SIGNATURE_ALGORITHM = "SHA1WithRSA";

	public static final String PUBLIC_KEY = "RSAPublicKey";
	public static final String PRIVATE_KEY = "RSAPrivateKey";
	
	/** */
	/**
	 * 用私钥对信息生成数字签名
	 * 
	 * @param data
	 *            加密数据
	 * @param privateKey
	 *            私钥
	 * 
	 * @return
	 * @throws Exception
	 */
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

	/** */
	/**
	 * 校验数字签名
	 * 
	 * @param data
	 *            加密数据
	 * @param publicKey
	 *            公钥
	 * @param sign
	 *            数字签名
	 * 
	 * @return 校验成功返回true 失败返回false
	 * @throws Exception
	 * 
	 */
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

	/** */
	/**
	 * 解密<br>
	 * 用私钥解密 http://www.5a520.cn http://www.feng123.com
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPrivateKey(byte[] data, String key)
			throws Exception {
		// 对密钥解密
		byte[] keyBytes = decryptBASE64(key);
		// 取得私钥
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
		// 对数据解密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		return cipher.doFinal(data);
	}

	/** */
	/**
	 * 解密<br>
	 * 用私钥解密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPublicKey(byte[] data, String key)
			throws Exception {
		// 对密钥解密
		byte[] keyBytes = decryptBASE64(key);

		// 取得公钥
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key publicKey = keyFactory.generatePublic(x509KeySpec);
		// 对数据解密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
		return cipher.doFinal(data);
	}

	/** */
	/**
	 * 加密<br>
	 * 用公钥加密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPublicKey(byte[] data, String key)
			throws Exception {
		// 对公钥解密
		byte[] keyBytes = decryptBASE64(key);
		// 取得公钥
		X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key publicKey = keyFactory.generatePublic(x509KeySpec);
		// 对数据加密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		return cipher.doFinal(data);
	}

	/** */
	/**
	 * 加密<br>
	 * 用私钥加密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPrivateKey(byte[] data, String key)
			throws Exception {
		// 对密钥解密
		byte[] keyBytes = decryptBASE64(key);
		// 取得私钥
		PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
		// 对数据加密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		return cipher.doFinal(data);
	}

	/** */
	/**
	 * 取得私钥
	 * 
	 * @param keyMap
	 * @return
	 * @throws Exception
	 */
	public static String getPrivateKey(Map<String, Object> keyMap)
			throws Exception {
		Key key = (Key) keyMap.get(PRIVATE_KEY);
		return encryptBASE64(key.getEncoded());
	}

	/** */
	/**
	 * 取得公钥
	 * 
	 * @param keyMap
	 * @return
	 * @throws Exception
	 */
	public static String getPublicKey(Map<String, Object> keyMap)
			throws Exception {
		Key key = (Key) keyMap.get(PUBLIC_KEY);
		return encryptBASE64(key.getEncoded());
	}

	/** */
	/**
	 * 初始化密钥
	 * 
	 * @return
	 * @throws Exception
	 */
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
	
	
	/** 
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, String> params) {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        String prestr = "";
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            if(value!=null && value!=""){
	            if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
	                prestr = prestr + key + "=" + value;
	            } else {
	                prestr = prestr + key + "=" + value + "&";
	            }
            }
        }
    		
		
        return prestr;
    }
    public static String createLinkString(TreeMap<String, String> params) {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        String prestr = "";
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            if(value!=null && value!=""){
	            if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
	                prestr = prestr + key + "=" + value;
	            } else {
	                prestr = prestr + key + "=" + value + "&";
	            }
            }
        }
    		
		
        return prestr;
    }

	public static byte[] decryptBASE64(String key) throws Exception {
		return Base64.decode(key);
	}

	public static String encryptBASE64(byte[] key) throws Exception {
		return Base64.encode(key);
	}
	
	public static Map<String, String> getKeys() throws Exception {
		Map<String, String> keyMap = new HashMap<String, String>(2);
		
		String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCP/Oo2NXDWV0nem6UQRk+0WExbaS+qwj48pvhzp2Tm8wSyu7GlIVIeBFY+dlRXx8Dpxyk/cURRbXxEvuIaMdLzTsAbdM63kwVuWND5aYbtZJn8hJWUf6bZJj7iOScb2ZNmdF28p4EPoIjocf/h9AvbH8lp6Z4gwSHbreF+YfjhewIDAQAB";
		String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAI/86jY1cNZXSd6bpRBGT7RYTFtpL6rCPjym+HOnZObzBLK7saUhUh4EVj52VFfHwOnHKT9xRFFtfES+4hox0vNOwBt0zreTBW5Y0Plphu1kmfyElZR/ptkmPuI5JxvZk2Z0XbyngQ+giOhx/+H0C9sfyWnpniDBIdut4X5h+OF7AgMBAAECgYAXTCjpVIRJah49splmpLnIyHCyabaYvo0rayjQLcmZxMCB2vTHbzjGOpVpzeOfwGZqbRszdLRFKJ3u0aPr8RvcnDPFJAuG09v3Z9P2w9mznD3OD++mm40lfz2aPiY0vjIjUVnu1L2fQ9dM39jeCU3xFjnKrtnZ5hC0Q4FpnT9lwQJBAMjgR0Ekr/q2sYwRAWOC73LKf35OAfwC72orMVHPmActNkCu0euyoeBLiKtriMyCVlh0esi7rG0TNutO5qFm0IMCQQC3gDHgaDKtxk48NCxeEuBZGncwPrkpEOmIPfOjQETZqzKIJkUOXEk2kcG3Lug2/Q1SIEhwDpFM8egos01UxumpAkB8F8EtByMHuPD53E+FUA/oVBxqxHoJWy7X4ZL4PUxnspB/aQAZoSLLUx4895Ns81+wCVUIyJd+Uo5s0zM6pi9NAkEAtyDcpdPXH82ULkjzLj8wXjwnTqKKLw2NwkXxa66sB37uwylOKXK4Lhlwddv+K0KHlLpbEFm5cEzLIDMwrbaFYQJAANfLRLYzRgZ6ckTvsDqD9LbQ4GMSe98Z1x3JWCq0jep2hzK+5+p8L7YEK73vH8Nz1T/QnFYRdfc+HfRKd7WGig==";
		
		//下面是我们正式的公私钥
//		String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCkLOrP8QD5rSKN7w1+innKkqZogyDyhbP8TeOVgCvNQXW+Piy+D65HZ4ioDqQRn1Jzz6yk887F0B7Avc7ZDkzIR8TgAnc1Cni1L3ps31sOlYNd2h/SDi8HLHLGzMNkBJKYnrJEIGA6PTZf4WqBvhcXeddyjO1vWtJv8jt7jJVkmQIDAQAB";
//		String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKQs6s/xAPmtIo3vDX6KecqSpmiDIPKFs/xN45WAK81Bdb4+LL4PrkdniKgOpBGfUnPPrKTzzsXQHsC9ztkOTMhHxOACdzUKeLUvemzfWw6Vg13aH9IOLwcscsbMw2QEkpieskQgYDo9Nl/haoG+Fxd513KM7W9a0m/yO3uMlWSZAgMBAAECgYBgMpWQLHZW1hUWN4mfqQI+KXnqaVNn5+ITp4v/8K85wO59/65123hSDJ9tYc7PKJzGkVxm9xkknZM3esB2PMPN8mNcmmN6PcSEaonI6dZPtPJhvdxRig+5caFzac6djUr8Qr1nyJyFJ+dIKkpYqTqI9xkFxiww3Vsiui0NGHBEtQJBANkHkYtvwWXDAyK7SbfZD9731pmO8XtWz98Klr/bRkevfb50/Lxis0T6Bnd77Guc5GHFOF+lovuRHJsp0N+Il28CQQDBp79DgNS+819nqABniALdVIFS9w1KN9LQ4l7yjcoZwtlK9XUOUunIxiiXXFc7uSnPX4hjCg9HsGpZzA2p2QB3AkAHo8eQ0/9H2MSMfXFL4n03iRvpzelcQSMSI+pImKxxq5DOmomnDAZpgC+oFxmsdBpqAqpIW6lQF7ydDazHoaRLAkEAt3L0MbsiTj14fFZfA7SJmieKVxCn603UjIJVFYsSD7NQarz5hXcv9j0ZiME19eKjY8oJBmEqNmw9YxF1RBfIYwJAEIqrtilEyzgn4tLPWdTca57y7axVP6SMNIT2FwCcQa0CF4f4OUmp7Ttbuh2BrFJSBVolXSnBiz2qjBoieKb5Uw==";

		keyMap.put("privateKey", privateKey);
		keyMap.put("publicKey", publicKey);
		
		return keyMap;
	}

	public static void main(String[] args) throws Exception {
//		Map<String, Object> keyMap = RSACoder.initKey();
//		 String publicKey = RSACoder.getPublicKey(keyMap);
//		 String privateKey = RSACoder.getPrivateKey(keyMap);
		/*
		 * String privateKey=""; privateKey = privateKey+
		 * "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAOxMm0rf2pZf4wah";
		 * privateKey = privateKey+
		 * "k6TqCXTYMhFwS1VhGbE069ApZdDZEhm03ADgo0Y0PxFz6ZWlsBOZ7I8FI+IdAEoF";
		 * privateKey = privateKey+
		 * "qolDDM0/PWoENkrMmTkxJokD/rBrID6Z0l+OMyd0W8tuKd8tuhO6OIL2KojN+PAX";
		 * privateKey = privateKey+
		 * "JLyXAkDoPgGoqr7hV4bAiCXuT//FAgMBAAECgYAge5NpYoZY34uE0NdL90QfhMy1";
		 * privateKey = privateKey+
		 * "KvugG/39Jyo3bklpwT1EvWD02ex37RyyWH3GrYUb3K/xJSK+pX/xrP+5aLcsiCJP";
		 * 
		 * privateKey = privateKey+
		 * "TwBQUvtZaZHsgkS3+YXYE18SofVM/g3DHFCR0a2/A3rs2O+6RpE7NHbjjaeX8dxa";
		 * privateKey = privateKey+
		 * "bthdwhcigzP+BJuZ7QJBAPhhEbCpl1FqSv0xNILuMAoMKMK+2sYtLspfPwvlP3cg";
		 * privateKey = privateKey+
		 * "BtfJSD9WUthakIR9Au65EBOiNX64ORgKDlILA/RfPDcCQQDzjKdmOsv+7edFkRN7";
		 * privateKey = privateKey+
		 * "miRN7mN+ugIRFTQoN61t9MViyW24S5Yao1uLpBAhW5kiTNbhgEEIiSrQS8bZqOMa";
		 * privateKey = privateKey+
		 * "X73jAkB9jFtryB2zyp3DI+ceM5XWzlSgM+bT+6zGIKT2t1eAOJEc7CJ32YOg1w5r";
		 * 
		 * privateKey = privateKey+
		 * "2eDsmGD2+6w8tJQvgY3BjM3f7W+7AkEAsTf/91uBZRcn2m8ehio7kJmOvIANUPDk";
		 * privateKey = privateKey+
		 * "6v7Yq9IYbzGzLQ+TQf7sdkgDlMJaQ6M2GRCCQfvNs5PwNiG2X1KbkQJBAOaA5MYx";
		 * privateKey = privateKey+
		 * "nFe9pOqKF+8eP4MA2j1To1ERLFpVIxu8jH3vRM6CAVzoBz2sJiE90Kn1oRtt4iy1";
		 * privateKey = privateKey+"l3A0LBbJS+ZiMNs=";
		 * 
		 * String publicKey = ""; publicKey = publicKey+
		 * "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDsTJtK39qWX+MGoZOk6gl02DIR";
		 * publicKey = publicKey+
		 * "cEtVYRmxNOvQKWXQ2RIZtNwA4KNGND8Rc+mVpbATmeyPBSPiHQBKBaqJQwzNPz1q";
		 * publicKey = publicKey+
		 * "BDZKzJk5MSaJA/6wayA+mdJfjjMndFvLbinfLboTujiC9iqIzfjwFyS8lwJA6D4B";
		 * publicKey = publicKey+"qKq+4VeGwIgl7k//xQIDAQAB";
		 */
		// String
		// publicKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCzbpC0cZ4Gbgl51bL/WQTCbOYwuoi6zrQc0z0ey4BueH5YaTs2WsU5KE9qBVGUpa1sJzu8V8dRFSKv4ir93sgPKlaBlWUKWR9WVQ8RAGRR/nuiJ2aeMk0m+v0c4YxM8TMx1HhcZqy7DKf+DWbREQEHnnP/jfVkyH+WsmSWV7tH6QIDAQAB";
		// String
		// privateKey="MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALNukLRxngZuCXnVsv9ZBMJs5jC6iLrOtBzTPR7LgG54flhpOzZaxTkoT2oFUZSlrWwnO7xXx1EVIq/iKv3eyA8qVoGVZQpZH1ZVDxEAZFH+e6InZp4yTSb6/RzhjEzxMzHUeFxmrLsMp/4NZtERAQeec/+N9WTIf5ayZJZXu0fpAgMBAAECgYAxQGcd8JXkw+178MmaR5AD4ippM7p2NGjCBMWhH1DYgeCEWhYzvESohMUPn/gdqkBpEHfYIewG+Js11gng93bIJB3vIwv4maWzhmC5ICXP8dMhNFgNmT7zolp81+SF9q4sBOib+RMq/RsCtGD1NXsTshPYUk088nMdimBO+LuWlQJBAPXLovdHzVb4Ph3wBKxlsUqxUnGk8nLf1k6v3z5j7RHMS80AKl33QPHHkL1dI15A4L9PG9VQPubmS1cJA+U/4ZsCQQC64Zp68rUF7xCksiG7teDCxj2sNDO/lwcUpB4jjt4cvDs+JmXWzI8BneZw0QCsKFkea9p7upOBpy8SsmAu2UbLAkALZdD9xH+aG9LNkpa049ZPz18yU+LGPujyJyej/gA9Rgrxy6MrTk7dp1Jn5YzBTyLc3C8/OgRgsqcltw5OMCMnAkEAiVyH+rTo/9qPhi70go+WDDRAYpHgM4PkDXTwPjyl38MvXGhG1JJ4zrxsBCJrdx8VGjo2Vll2UIc9H2YDMow77wJAPXwWtzUG/O4Go3qdTtBy7U3+kn45pV7yiRZtH2z7k/TGZ6df6JhsdT2U/Iexzk+uJGSPupnfJVA1ZJRCXMZepg==";
		String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCP/Oo2NXDWV0nem6UQRk+0WExbaS+qwj48pvhzp2Tm8wSyu7GlIVIeBFY+dlRXx8Dpxyk/cURRbXxEvuIaMdLzTsAbdM63kwVuWND5aYbtZJn8hJWUf6bZJj7iOScb2ZNmdF28p4EPoIjocf/h9AvbH8lp6Z4gwSHbreF+YfjhewIDAQAB";
//		String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAI/86jY1cNZXSd6bpRBGT7RYTFtpL6rCPjym+HOnZObzBLK7saUhUh4EVj52VFfHwOnHKT9xRFFtfES+4hox0vNOwBt0zreTBW5Y0Plphu1kmfyElZR/ptkmPuI5JxvZk2Z0XbyngQ+giOhx/+H0C9sfyWnpniDBIdut4X5h+OF7AgMBAAECgYAXTCjpVIRJah49splmpLnIyHCyabaYvo0rayjQLcmZxMCB2vTHbzjGOpVpzeOfwGZqbRszdLRFKJ3u0aPr8RvcnDPFJAuG09v3Z9P2w9mznD3OD++mm40lfz2aPiY0vjIjUVnu1L2fQ9dM39jeCU3xFjnKrtnZ5hC0Q4FpnT9lwQJBAMjgR0Ekr/q2sYwRAWOC73LKf35OAfwC72orMVHPmActNkCu0euyoeBLiKtriMyCVlh0esi7rG0TNutO5qFm0IMCQQC3gDHgaDKtxk48NCxeEuBZGncwPrkpEOmIPfOjQETZqzKIJkUOXEk2kcG3Lug2/Q1SIEhwDpFM8egos01UxumpAkB8F8EtByMHuPD53E+FUA/oVBxqxHoJWy7X4ZL4PUxnspB/aQAZoSLLUx4895Ns81+wCVUIyJd+Uo5s0zM6pi9NAkEAtyDcpdPXH82ULkjzLj8wXjwnTqKKLw2NwkXxa66sB37uwylOKXK4Lhlwddv+K0KHlLpbEFm5cEzLIDMwrbaFYQJAANfLRLYzRgZ6ckTvsDqD9LbQ4GMSe98Z1x3JWCq0jep2hzK+5+p8L7YEK73vH8Nz1T/QnFYRdfc+HfRKd7WGig==";
		String privateKey = getKeys().get("privateKey");
		
		System.out.println("publicKey:");
		System.out.println(publicKey);
		System.out.println();
		System.out.println("privateKey:");
		System.out.println(privateKey);
		System.out.println();
		
		//String source = "outOrderId=10001&orderAmt=0.1&notifyUrl=http://192.168.1.63:8080/app_test/test&orderTime=20150625180339893";
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
