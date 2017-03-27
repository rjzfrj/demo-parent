package com.allscore.wireless.controller;

import java.util.Date;
import java.util.UUID;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.allscore.wireless.common.DateUtil;
import com.allscore.wireless.common.UtilXml;
import com.allscore.wireless.common.Utils;
import com.allscore.wireless.dao.Users;
import com.allscore.wireless.dao.UsersMapper;

@Controller
@RequestMapping("/synchro")
public class GetUserUtil {

	/* 会员信息DAO */
	@Autowired
	private UsersMapper userDao;
	
	/**
	 * 获得访问access_token
	 * 
	 * @param appId
	 * @param appSecert
	 * @return
	 */
	private String getToken(String appId, String appSecret) {
		String access_token = "";
		try {
			System.out.println("======GetUserUtil====getToken======appid=====" + appId);
			System.out.println("======GetUserUtil====getToken======appsecret=====" + appSecret);
			
			// 微信公众号系统TOKEN请求URL
			String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token";
			
			StringBuffer paramUrl = new StringBuffer("");
			paramUrl.append("grant_type=client_credential");
			paramUrl.append("&appid=");
			paramUrl.append(appId);
			paramUrl.append("&secret=");
			paramUrl.append(appSecret);
			
			String tokenStr = UtilXml.getResult(tokenUrl, paramUrl.toString());
			if (tokenStr != null) {
				System.out.println("======GetUserUtil====getToken======tokenStr=====" + tokenStr);
				JSONObject jsonStr = JSONObject.fromObject(tokenStr);
				access_token = jsonStr.getString("access_token"); // access_token
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return access_token;
	}
	

}
