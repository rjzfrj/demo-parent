package com.allscore.wireless.common;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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
	 * 格式化数值
	 * @param str
	 * @return
	 */
	public static String formatDecimal(Object obj) {
		if(obj == null)
    		return "";
		DecimalFormat df = new DecimalFormat("#,##0.00");
		String result = df.format(obj);
		return result;
	}
	
	/***
	 * @param input
	 *            : 银行卡号,例如"6225880137706868"
	 * @return
	 */
	public static String formMemberCard(String input) {
		String result = input.replaceAll("([\\d]{4})(?=\\d)", "$1 ");
		return result;
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
    
    /**
     * 校验字符串是否是手机号
     * @param args
     * @return
     */
    public static boolean checkMobile(String args) {
    	boolean flag = false;
    	String pattern = "^(13[0-9]|15[01]|153|15[6-9]|180|18[23]|18[5-9])\\d{8}$";
    	flag = args.matches(pattern);
    	return flag;
    }
    
	/**
	 * 校验字符串是否是数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}
	
	/**
	 * 测试方法.
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
//		Map<String,Object> param = new HashMap<String,Object>();
//		param.put("openid", "OPENID");
//		param.put("template_id", "MNYQrufeLT0NvOKeW3B-hD_N0jEh4REs-J3ALPOrKS8");
//		param.put("url", "http://weixin.qq.com/download");
//		param.put("memberCard", "880001000001");
//		param.put("balance", "100.09");
//		String createDate = "2016-01-04 11:07:01";
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date date = sdf.parse(createDate);
//	    Calendar cl = Calendar.getInstance();
//	    cl.setTime(date);
//	    cl.add(Calendar.DATE,24);
//	    String temp = "";
//	    temp = sdf.format(cl.getTime());
//	    System.out.println(temp);
		
		String userStr = "{\"total\":3,\"count\":3,\"data\":{\"openid\": [\"oUdh9uDrVgpV8d7ny8s1ngp8q71Y\",\"oUdh9uHC6k_L_r4gGuNzEmy7S3vg\",\"oUdh9uJ8_z562gUa29G3CA-ncJ4w\"]},\"next_openid\":\"oUdh9uHGYlRkR7VOh3qVHNaFQ_w8\"}";
		JSONObject jsonObject = JSONObject.fromObject(userStr);
		int count = jsonObject.getInt("count");
		System.out.println("===GetUserUtil==initUsers====count======" + count);
		JSONObject jsonData = JSONObject.fromObject(jsonObject.getString("data"));
		JSONArray jsonArray = jsonData.getJSONArray("openid");
		for (int i=0; i<jsonArray.size(); i++) {
			System.out.println("===GetUserUtil==initUsers====openid======" + (i+1) + "====" + jsonArray.get(i));
		}
	}
}
