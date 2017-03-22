package com.my.wallet.web.utils;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;

/**
 * 日常开发工具类 
 *
 */
public class Utils {

	/**036
     * 对象转换成字符串
     * @param obj
     * @return
     */
    public static String obj2str(Object obj){
    	if(obj == null)
    		return "";
    	return obj.toString().trim();
    }
    
	public static String getLocalIP() {
		InetAddress addr = null;
		try {
			addr = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		byte[] ipAddr = addr.getAddress();
		String ipAddrStr = "";
		for (int i = 0; i < ipAddr.length; i++) {
			if (i > 0) {
				ipAddrStr += ".";
			}
			ipAddrStr += ipAddr[i] & 0xFF;
		}
		// System.out.println(ipAddrStr);
		return ipAddrStr;
	}
	
	/**
	 * 获得指定长度的随机数
	 */
	public static String getRandom(int length) {
		if (length > 0) {
			return new BigDecimal((Math.random() * 9 + 1))
					.multiply(new BigDecimal("10").pow(length - 1))
					.toBigInteger().toString();
		}
		return null;
	}
	/**
	  * <p>此方法参数6位随机数性能好与上面的方法1万次调用耗时12毫秒左右上面方法耗时210毫秒左右</p>
	  * @param length
	  * @return
	  * @author lzf
	  * @date 2016年1月28日 下午4:11:17
	 */
	public static String getRandoms(int length){
		if (length > 0) {
        return String.valueOf((int)((Math.random()*9+1)*Math.pow(10,length-1)));
		}else{
			return "";
		}
	}
	
	/**
     * 格式化显示的银行卡号(格式为:************3256)
     */
    public static String formatBankCard(String cardNo) {
    	String card_no = cardNo.replaceAll("(?<=\\d{0})\\d(?=\\d{4})", "*");
    	return card_no;
    }
    
   
    
    /**
      * <p>手机号格式化成(138****5487)</p>
      * @param mobile
      * @return
      * @author lzf
      * @date 2016年1月12日 下午3:58:55
     */
    public static String formatMobile(String mobile){
    	String rmobile="";
    	if(StringUtils.isNotBlank(mobile) && mobile.length()==11){
    		//rmobile = mobile.substring(0,mobile.length()-(mobile.substring(3)).length())+"****"+mobile.substring(7);
    		rmobile=mobile.replaceAll("(?<=\\d{3})\\d(?=\\d{3})", "*");
    	}
    	return rmobile;
    	
    }
  
   /**
  * <p>格式化身份证</p>
  * @param idCard
  * @return
  * @author lzf
  * @date 2016年1月12日 下午4:06:28
 */
public static String formatIdCard(String idCard){
   	String ridCard="";
   	if(StringUtils.isNotBlank(idCard) && idCard.length()==18){
   		//ridCard = idCard.substring(0,idCard.length()-(idCard.substring(3)).length())+"****"+idCard.substring(7);
   		ridCard=idCard.replaceAll("(?<=\\d{3})\\d(?=\\d{2})", "*");
   	}
   	return ridCard;
   	
   }
    
    /**
     * 随机获得字符串
     * @param length
     * @return
     */
    public static String getRandomString(int length) {
        String base = "abcdefghijklmABCDEFGHIJKLMNOPQRSTUVWXYZnopqrstuvwxyz0123456789";
        Random random = new Random();     
        StringBuffer sb = new StringBuffer();     
        for (int i = 0; i < length; i++) {     
            int number = random.nextInt(base.length());     
            sb.append(base.charAt(number));     
        }     
        return sb.toString();
     }
    
    public static String ascii2native(String ascii) {  
	    int n = ascii.length() / 6;  
	    StringBuilder sb = new StringBuilder(n);  
	    for (int i = 0, j = 2; i < n; i++, j += 6) {  
	        String code = ascii.substring(j, j + 4);  
	        char ch = (char) Integer.parseInt(code, 16);  
	        sb.append(ch);  
	    }  
	    return sb.toString();
	}
    
    public static String getCurrentTimeMillis(){
    	long currentTime = System.currentTimeMillis();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		Date date = new Date(currentTime);
		return formatter.format(date);
    }
	
	/**
	 * 测试方法.
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		System.out.println(Utils.formatIdCard("130726198602146911"));
//		String randomStr = getRandomString(16);
//		System.out.println("randomStr=================>" + randomStr);
//		
//		String text = "1234567890";
//		AESCrypt aesCrypt = new AESCrypt(randomStr);
//		String encryptText = aesCrypt.encryptBase64(text);
//		
//		System.out.println("encryptText=================>" + encryptText);
//		
//		String decryptText = aesCrypt.decryptBase64(encryptText);
//		System.out.println("decryptText=================>" + decryptText);
//		String[] strs = new String[]{"appVer", "merchantId", "token", "appOs", "notify", "outOrderTime", "outOrderId"};
//		Arrays.sort(strs);
//		for(String str : strs) {
//		    System.out.println(str);
//		}
//		List<String> list = new ArrayList<String>();
//		list.add("aaa");
//		list.add("lll");
//		list.add("bbb");
//		list.add("www");
//		list.add("ppp");
//		
//		Collections.sort(list);
//		
//		for (String str : list) {
//			System.out.println(str);
//		}
		long start=System.currentTimeMillis();
		
		for (int i = 0; i < 10000; i++)
		{
			getRandoms(6);
		}
		System.out.println("耗时："+(System.currentTimeMillis()-start));
	}
}
