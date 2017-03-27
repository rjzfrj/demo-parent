package com.allscore.wireless.controller;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.allscore.wireless.common.PropertyUtil;

@Controller
@RequestMapping("/wxMenu")
public class WxMenuUtil {

	/**
	 * 生产菜单
	 * @param appid
	 * @param appsecret
	 * @param publicId
	 * @returnnet.sf.ezmorph.Morpher
	 */
	@RequestMapping(value = "/createMenu")
	@ResponseBody
	public String createMenu(HttpServletRequest request) {
		String appid = request.getParameter("appid");
		String appsecret = request.getParameter("appsecret");
		String publicId = request.getParameter("public_id");
		String result = "";
		try {
			System.out.println("======WxMenuUtil====createMenu======publicId=====" + publicId);
			System.out.println("======WxMenuUtil====createMenu======appid=====" + appid);
			System.out.println("======WxMenuUtil====createMenu======appsecret=====" + appsecret);

			String accessToken = "";

			// 微信公众号系统TOKEN请求URL
			String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token";

			StringBuffer paramUrl = new StringBuffer("");
			paramUrl.append("appid=");
			paramUrl.append(appid);
			paramUrl.append("&secret=");
			paramUrl.append(appsecret);
			paramUrl.append("&grant_type=client_credential");

			// 获得公众号运行使用的TOKEN
			String tokenStr = getResult(tokenUrl, paramUrl.toString());
			System.out.println("======WxMenuUtil====createMenu======tokenStr=====" + tokenStr);

			if (tokenStr != null) {
				JSONObject jsonStr = JSONObject.fromObject(tokenStr);
				accessToken = jsonStr.getString("access_token");
				System.out.println("======WxMenuUtil====createMenu======accessToken=====" + accessToken);
			}

			// 微信公众号系统TOKEN请求URL
			String createMenuUrl = "https://api.weixin.qq.com/cgi-bin/menu/create";

			StringBuffer paramMenu = new StringBuffer(createMenuUrl);
			paramMenu.append("?access_token=");
			paramMenu.append(accessToken);
			String menuUrl = paramMenu.toString();
			String menuBodyStr = getMenuBodyStr(appid);

			System.out.println("======WxMenuUtil====createMenu======menuUrl=====" + menuUrl);
			System.out.println("======WxMenuUtil====createMenu======menuBodyStr=====" + menuBodyStr);

			URL url = new URL(menuUrl);
			HttpURLConnection http = (HttpURLConnection) url.openConnection();

			http.setRequestMethod("POST");
			http.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			http.connect();
			OutputStream os = http.getOutputStream();
			os.write(menuBodyStr.getBytes("UTF-8"));// 传入参数
			os.flush();
			os.close();

			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String message = new String(jsonBytes, "UTF-8");
			System.out.println("返回信息" + message);
			System.out.println("======WxMenuUtil====createMenu======retrun Message=====" + message);
			/*json格式返回结果：
			正确：{"errcode":0,"errmsg":"ok"}
			错误：{"errcode":40018,"errmsg":"invalid button name size"}*/
			result = message;
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		return result;
	}
	@RequestMapping(value = "/deleteMenu")
	@ResponseBody
	public String deleteMenu(HttpServletRequest request) {
		String appid = request.getParameter("appid");
		String appsecret = request.getParameter("appsecret");
		String publicId = request.getParameter("public_id");
		
		String result = "";
		try {
			System.out.println("======WxMenuUtil====deleteMenu======publicId=====" + publicId);
			System.out.println("======WxMenuUtil====deleteMenu======appid=====" + appid);
			System.out.println("======WxMenuUtil====deleteMenu======appsecret=====" + appsecret);

			String accessToken = "";

			// 微信公众号系统TOKEN请求URL
			String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token";

			StringBuffer paramUrl = new StringBuffer("");
			paramUrl.append("appid=");
			paramUrl.append(appid);
			paramUrl.append("&secret=");
			paramUrl.append(appsecret);
			paramUrl.append("&grant_type=client_credential");

			// 获得公众号运行使用的TOKEN
			String tokenStr = getResult(tokenUrl, paramUrl.toString());
			System.out.println("======WxMenuUtil====deleteMenu======tokenStr=====" + tokenStr);

			if (tokenStr != null) {
				JSONObject jsonStr = JSONObject.fromObject(tokenStr);
				accessToken = jsonStr.getString("access_token");
				System.out.println("======WxMenuUtil====deleteMenu======accessToken=====" + accessToken);
			}

			// 微信公众号系统TOKEN请求URL
			String deleteMenuUrl = "https://api.weixin.qq.com/cgi-bin/menu/delete";

			StringBuffer paramMenu = new StringBuffer(deleteMenuUrl);
			paramMenu.append("?access_token=");
			paramMenu.append(accessToken);
			String menuUrl = paramMenu.toString();
			String menuBodyStr = "";

			System.out.println("======WxMenuUtil====deleteMenu======menuUrl=====" + menuUrl);

			URL url = new URL(menuUrl);
			HttpURLConnection http = (HttpURLConnection) url.openConnection();

			http.setRequestMethod("POST");
			http.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			http.connect();
			OutputStream os = http.getOutputStream();
			os.write(menuBodyStr.getBytes("UTF-8"));// 传入参数
			os.flush();
			os.close();

			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String message = new String(jsonBytes, "UTF-8");
			System.out.println("======WxMenuUtil====deleteMenu======retrun Message=====" + message);
			/*json格式返回结果：
			正确：{"errcode":0,"errmsg":"ok"}
			错误：{"errcode":40018,"errmsg":"invalid button name size"}*/
			result = message;
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		return result;
	}
	
	/*
	 * 返回自定义菜单的内容
	 */
	private String getMenuBodyStr(String appId) {

		StringBuffer sbfMenu = new StringBuffer();
		
		// 此处的URL应该是当前系统访问的地址:http://wxtest.allscore.com/shopPlus/,必须要是微信端的访问地址
		String baseUrl = StringUtils.trim(PropertyUtil
				.getProperty("wx.server_address"));
        System.out.println("微信端的访问地址:"+baseUrl);
		String redirectUri1 = new StringBuffer().append(baseUrl)
				.append("user/myIdCard?appId=").append(appId).toString();
		String redirectUri2 = new StringBuffer().append(baseUrl)
				.append("user/myMemberCard?appId=").append(appId).toString();
		String redirectUri3 = new StringBuffer().append(baseUrl)
				.append("pay/tranDetailsFromWx?appId=").append(appId)
				.toString();
		String redirectUri4 = new StringBuffer().append(baseUrl)
				.append("coupon/myCoupon?appId=").append(appId).toString();
		String redirectUri5 = new StringBuffer().append(baseUrl)
				.append("pay/recharge?appId=").append(appId).toString();

		sbfMenu.append("{\"button\":[{\"type\":\"view\",\"name\":\"我的身份\",\"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=");
		sbfMenu.append(appId);
		sbfMenu.append("&redirect_uri=");
		sbfMenu.append(redirectUri1);
		sbfMenu.append("&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect\"},{\"name\":\"我的会员卡\",\"sub_button\":[{\"type\":\"view\",\"name\":\"我的会员卡\",\"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=");
		sbfMenu.append(appId);
		sbfMenu.append("&redirect_uri=");
		sbfMenu.append(redirectUri2);
		sbfMenu.append("&response_type=code&scope=snsapi_userinfo&state=2#wechat_redirect\"},{\"type\":\"view\",\"name\":\"交易明细\",\"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=");
		sbfMenu.append(appId);
		sbfMenu.append("&redirect_uri=");
		sbfMenu.append(redirectUri3);
		sbfMenu.append("&response_type=code&scope=snsapi_userinfo&state=3#wechat_redirect\"},{\"type\":\"view\",\"name\":\"优惠券\",\"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=");
		sbfMenu.append(appId);
		sbfMenu.append("&redirect_uri=");
		sbfMenu.append(redirectUri4);
		sbfMenu.append("&response_type=code&scope=snsapi_userinfo&state=4#wechat_redirect\"},{\"type\":\"view\",\"name\":\"会员卡充值\",\"url\":\"https://open.weixin.qq.com/connect/oauth2/authorize?appid=");
		sbfMenu.append(appId);
		sbfMenu.append("&redirect_uri=");
		sbfMenu.append(redirectUri5);
		sbfMenu.append("&response_type=code&scope=snsapi_userinfo&state=5#wechat_redirect\"}]}]}");

		String result = sbfMenu.toString();
		System.out.println("======WxMenuUtil====getMenuBodyStr====result===" + result);

		return result;
	}
	
	/** 连接到服务器并获取数据 */
	private static String getResult(String urlStr, String content) {
		URL url = null;
		HttpURLConnection connection = null;
		try {
			url = new URL(urlStr);
			connection = (HttpURLConnection) url.openConnection();

			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.connect();
			DataOutputStream out = new DataOutputStream(
					connection.getOutputStream());
			out.write(content.getBytes("utf-8"));
			out.flush();
			out.close();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "utf-8"));
			StringBuffer buffer = new StringBuffer();
			String line = "";
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			reader.close();
			return buffer.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				connection.disconnect();
			}
		}
		return null;
	}
}
