package com.my.wallet.web.security;

import java.util.HashMap;
import java.util.Map;

public class MainTest {

	
	public static void main(String[] args) throws Exception {
		//公私钥生产可以参考工具说明
		String publicKey="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCOexXZRYv739xMOFbjnlVvi2JuT8wV5EqwmuLeJKqYe1j4eRj+KD0TjJzNfnkrCHxEh6a6z9ZkRpLhyt6KNcwwe9fAYMbSzAx9QBftVfKGJWBBAk2I+1ce3adRbix+qpUL7rl2ZLDvrLvACHYmsvUu1Y8pW0YXjsVtzfcxquRC5wIDAQAB";
		String privateKey="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAI57FdlFi/vf3Ew4VuOeVW+LYm5PzBXkSrCa4t4kqph7WPh5GP4oPROMnM1+eSsIfESHprrP1mRGkuHK3oo1zDB718BgxtLMDH1AF+1V8oYlYEECTYj7Vx7dp1FuLH6qlQvuuXZksO+su8AIdiay9S7VjylbRheOxW3N9zGq5ELnAgMBAAECgYBmzM3tzqz6VQFIiRm/7vxrl6C0QKTaYrW1CHuJleD0VYLS++SZdATlNv3nkknwT5YBh/I3NT/VZ8oQWWQJSlmAwCcDJPUbXR3pUmOVTcMXxFW+Om9Mbz0p1fFIsCNxz7OGumw3Gc7yz3j8xXHJ6OENJvjolvrbRlUBLLhMDcCLCQJBAPn2/HyZTgotNoNRkSTJ2okcryjyKtJ+jqPHlL2UwRkDdKZyNW50XR1iAQyxZafvWbVc1j70aWBbk7//aMKifvsCQQCR67+hdF6dV7Jzyh0Z3wbLtZRkhTf0or4b5WhSTVpKrR5jcixGYSVfvFgWpVyl5TEEYRUHdOLY88qztt3zfNgFAkEA4lcaM3zh5GciWHBwfB5nSwZ1OR9nVSkR4g2jirL60rqFUv5RkY2+jw+iIas0gIdD9Ox2d5T7IixqMk2Ev9FCJwJBAIHY9MdpVCBTP3sF6WtyKQvw4sGoQiixANa2B2TlbGlzL81yda+TFrzgTsqoD4+YKUwqT5o5CvvfxQqyuULIXzkCQFwZLt1zbtuiR3tAkdrvFs6LzacDA5PMlX8cYsXN4vt1Zwha+hlG/Q3Ey7KpwtMgab7nMNf8nYdXKdTeYE3Ld14=";
		
		System.out.println("---------------私钥签名过程------------------");
		/**比如说请求参数有这么几个 merchantId=001015102001357&userNo=22&timestamp=123*/
		//第一步想将参数放到Map中
		Map<String, String> sParaTemp = new HashMap<String, String>();
		sParaTemp.put("userNo", "22");
		sParaTemp.put("merchantId", "001015102001357");
		sParaTemp.put("timestamp", String.valueOf(System.currentTimeMillis()));
		 //准备好代签名的排完序的参数字符串
		String sinaTempStr=RSACoder.createLinkString(sParaTemp); 
		/**第二部开始签名*/
		String signstr=RSACoder.sign(sinaTempStr,privateKey);   
		System.out.println("签名原串："+sinaTempStr);
		System.out.println("签名串："+signstr);
		System.out.println();
		
		System.out.println("---------------公钥校验签名------------------");
		System.out.println("签名原串："+sinaTempStr);
		System.out.println("签名串："+signstr);
		
		System.out.println("验签结果："+RSACoder.verify(sinaTempStr, publicKey, signstr));
		System.out.println();
		
	}
}
