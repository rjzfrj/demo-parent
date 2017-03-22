package com.my.wallet.web.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class ServletUtils {
	public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
	
	@SuppressWarnings("unchecked")
	public static Map<String,String> getParasMap(HttpServletRequest request){
		Map<String, String> params = new HashMap<String, String>();
		Map<String,String[]> requestParams = request.getParameterMap();
		String name=null;
		String[] values=null;
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter
				.hasNext();) {
			name =  iter.next();
			values =  requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr
						+ values[i] : valueStr + values[i] + ",";
			}
			System.out.println(name+":"+valueStr);
			params.put(name, valueStr);
		}
		return params;
	}

}
