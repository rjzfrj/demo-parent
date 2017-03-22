package com.my.wallet.web.controller;

import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import com.my.wallet.web.utils.ContentTypes;
import com.my.wallet.web.utils.HttpHeaders;
import com.my.wallet.web.utils.ZipUtils;

/******************************************
 * 
 * Controller的基类 
 * 提供共通的变量的定义以及统一的JSON数据格式交互
 * 
 * @author HeTong
 * 
 ******************************************/

public abstract class BaseController {

	protected String error_message = "{\"code\":\"201\", \"msg\":\"服务器连接异常，请稍后重试!\"}";

	/**
	 * 接口统一回写方法
	 * 
	 * @param data
	 * @throws Exception
	 */
	public void writeResult(HttpServletResponse response, String data)
			throws Exception {
		response.setContentType(ContentTypes.TEXT_JSON_UTF8);
		response.setHeader(HttpHeaders.CACHE_CONTROL, "no-cache");
		byte[] output = ZipUtils.compress(data.getBytes("UTF-8"));
		response.setHeader("Content-Encoding", "gzip");
		response.setCharacterEncoding("UTF-8");
		response.setContentLength(output.length);
		OutputStream out = response.getOutputStream();
		out.write(output);
		out.flush();
		out.close();
	}
}
