package com.allscore.wireless.common;

import org.apache.commons.codec.digest.DigestUtils;

public class TripleDES extends DES {

	// 双倍长计算密钥长度
	public static String DESDouble(String source, String key, int mode) {
		String first = DES_2(source.substring(0, 16), key, mode);
		String second = DES_2(source.substring(16), key, mode);
		return first + second;
	}

	public static String getMacValue(String macKey) {
		//java中为什么不支持"0"*32这种写法？
		String data = DESDouble("00000000000000000000000000000000", macKey, 0);
		return data.substring(0, 6);
	}

	/*public static String getMac(String macKey, String data) {
		String lk = macKey.substring(0, 16);
		String rk = macKey.substring(16);
		String second_result = MacUtil.MAC_ASC(lk, "0000000000000000", data);// ANSI-X9.9-MAC计算mac
		System.out.println("第一步结果:" + second_result);
		String triple_result = DES_1(second_result, rk, 1);// 1为解密，第三部结果
		System.out.println("第二步结果:" + triple_result);
		String fourth_result = DES_1(triple_result, lk, 0);// 0为加密，使用左16位密钥
		System.out.println("第三步结果:" + fourth_result);
		return fourth_result;
	}*/
	
	public static String md5(String text) {
		try {
			return DigestUtils.md5Hex(text); 
		} catch (Exception ex) {
			ex.printStackTrace();
			ex.printStackTrace();
		}
		return null;
	}
	
	public static String tlk = PropertyUtil.getProperty("tlk");
	
	public static String getDesKey() {
		try {
			return md5(Long.toString(System.currentTimeMillis())).toUpperCase();
		} catch (Exception ex) {
			ex.printStackTrace();
			ex.printStackTrace();
		}
		return null;
	}
	
	public static String tmk(String tmkPlainText){
		//String tmkPlainText = getDesKey();
		String tmk = DESDouble(tmkPlainText,tlk, 0);
		return tmk;
		
	}
	
	public static String pik(String tmkPlainText){
		String pikPlainText = getDesKey();
		String pik = DESDouble(pikPlainText,tmkPlainText, 0);
		return pik;
		
	}
	
	public static String mak(String tmkPlainText){
		String makPlainText = getDesKey();
		String mak = DESDouble(makPlainText,tmkPlainText, 0);
		return mak;
		
	}
	
	public static String trk(String tmkPlainText){
		String trkPlainText = getDesKey();
		String trk = DESDouble(trkPlainText,tmkPlainText, 0);
		return trk;
		
	}
	
	public static void main(String[] args) {
		try {
			String tmkPlainText = getDesKey();
			String tmk = DESDouble(tmkPlainText,tlk, 0);
			//System.out.println("tmkPlainText:"+tmkPlainText);
			System.out.println("tmk:"+tmk);
			String pikPlainText = getDesKey();
			String pik = DESDouble(pikPlainText,tmkPlainText, 0);
			//System.out.println("pikPlainText:"+pikPlainText);
			System.out.println("pik:"+pik);
			String makPlainText = getDesKey();
			String mak = DESDouble(makPlainText,tmkPlainText, 0);
			//System.out.println("makPlainText:"+makPlainText);
			System.out.println("mak:"+mak);	
			String trkPlainText = getDesKey();
			String trk = DESDouble(trkPlainText,tmkPlainText, 0);
			//System.out.println("trkPlainText:"+trkPlainText);
			System.out.println("trk:"+trk);	
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
