package com.my.wallet.web.interceptor;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.my.wallet.web.security.RSACoder;
import com.my.wallet.web.utils.ClientDataHandler;
import com.my.wallet.web.utils.ContentTypes;
import com.my.wallet.web.utils.HttpHeaders;
import com.my.wallet.web.utils.ZipUtils;

public class WalletNotifyInterceptor extends HandlerInterceptorAdapter {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(WalletNotifyInterceptor.class);
	/* 网关查询服务 */
//	@Autowired
//	protected ISaler iSalerService;
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String accessLink = request.getRequestURI().replace(
				request.getContextPath() + "/", "");
		logger.info("---------访问主机地址:" + request.getRemoteHost()
				+ "--访问功能链接地址:" + accessLink);
		String staticSources=".*resources/images/bank/icon/.*";
//		if(accessLink.matches(staticSources)){
//			return true;
//		}else{
//			// 写请求参数日志
//			String resParas = getReqParaStr(request);
//			//logger.info("getReqParaStr请求参数:" + resParas);
//			String getReqHeaderStr=getReqHeaderStr(request);
//			//logger.info("getReqHeaderStr请求参数:" + getReqHeaderStr);
//			Map<String, String[]> param = request.getParameterMap();
//			String merchantId =request.getHeader("merchantId");
//			String userNo =request.getHeader("userNo");
//			String timestamp =request.getHeader("timestamp");
//			String token =request.getHeader("token");
//			String sign = request.getHeader("sign");
//			if (StringUtils.isBlank(sign)) {
//				// 设置JSON格式的返回内容字符串
//				String refuseStr = ClientDataHandler.getFailResult("签名值为空请求非法!");
//				writeResult(response, refuseStr);
//				return false;
//			}
//			if (StringUtils.isBlank(merchantId)) {
//				// 设置JSON格式的返回内容字符串
//				String refuseStr = ClientDataHandler.getFailResult("商户号不能为空!");
//				writeResult(response, refuseStr);
//				return false;
//			}
//			if (StringUtils.isBlank(userNo)) {
//				// 设置JSON格式的返回内容字符串
//				String refuseStr = ClientDataHandler.getFailResult("用户编号不能为空!");
//				writeResult(response, refuseStr);
//				return false;
//			}
//			Map<String, String> sParaTemp = new HashMap<String, String>();
//			sParaTemp.put("userNo", userNo);
//			sParaTemp.put("merchantId", merchantId);
//			sParaTemp.put("timestamp", timestamp);
//			sParaTemp.put("token", token);
//			
//			TreeMap<String, String> headMap = new TreeMap<String, String>();
//			headMap.put("userNo", userNo);
//			headMap.put("merchantId", merchantId);
//	//		/* 验证签名信息 */
//			boolean check=false;
//			
//			String merchPublicKey="";
//			try
//			{
//				SalerInfoReqParams sirp = new SalerInfoReqParams();
//				sirp.setMerchantId(merchantId);
//			// 调用校验接口
//				SalerInfo salerInfo = iSalerService.getSalerInfo(sirp);
//				String aa=salerInfo.getSecurityLevel();
//				//salerInfo.getSelfPublicKey()
//				merchPublicKey=salerInfo.getMerchPublicKey();
//			} catch (Exception e)
//			{
//				String refuseStr = ClientDataHandler.getFailResult("请求非法，商户号未经授权或者不正确!");
//				writeResult(response, refuseStr);
//				return false;
//			}
//			//低安全级别只延签头部信息签名
//			if("001015120100443".equalsIgnoreCase(merchantId)){
//				return true;
//				//String data = RSACoder.createLinkString(sParaTemp);
//				//check=RSACoder.verify(data, salerInfo.getMerchPublicKey(), sign);
//			//高安全级别全部
//			}else{ 
//				TreeMap map=buildParam(param,headMap);
//				check= checkSign(map, sign,merchPublicKey );
//			}
//			
//			if (!check) {
//				// 设置JSON格式的返回内容字符串
//				String refuseStr = ClientDataHandler.getFailResult("请求非法，该请求被拒绝!");
//				writeResult(response, refuseStr);
//				return false;
//			}
//		}
		return true;
	}
	
	
	private TreeMap buildParam(Map<String, String[]> param ,Map<String, String> sParaTemp ) {
		TreeMap<String, String> treeMap = new TreeMap();
		try {
			String sign = null;
		
			for (Map.Entry<String, String[]> entry : param.entrySet()) {
				if ("sign".equals(entry.getKey())){
					sign = entry.getValue()[0];
				}else{
					treeMap.put(entry.getKey(), entry.getValue()[0]);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		treeMap.putAll(sParaTemp);
		return treeMap;
	}
	


	
	
	/**
	  * <p>排序且验证全部参数</p>
	  * @param param
	  * @param sign
	  * @param publicKey
	  * @return
	  * @author lzf
	  * @date 2015年12月30日 下午5:42:56
	 */
	private boolean checkSign(TreeMap<String, String> param, String sign, String publicKey) {
		try {
			String data = RSACoder.createLinkString(param);
			boolean ckeck=RSACoder.verify(data, publicKey, sign);
			logger.info("===》请求参数串："+data);
//			logger.info("===》用户公钥："+publicKey);
//			logger.info("===》签名："+sign);
			if (ckeck)
				return true;
			else
				return false;
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
	
	/*
	 * 获得请求的参数值   
	 */
	private String getReqParaStr(HttpServletRequest request) {
		StringBuilder sb = new StringBuilder();
		@SuppressWarnings("rawtypes")
		Enumeration enu = request.getParameterNames();
		String paraName = null;
		while (enu.hasMoreElements()) {
			paraName = (String) enu.nextElement();
			sb.append(paraName).append("=")
					.append(request.getParameter(paraName)).append("\r\n");

		}
		return sb.toString();
	}
	
	/*
	 * 获得请求Header参数值
	 */
	private String getReqHeaderStr(HttpServletRequest request) {
		StringBuilder sb = new StringBuilder();
		@SuppressWarnings("rawtypes")
		Enumeration enu = request.getHeaderNames();
		String headerName = null;
		while (enu.hasMoreElements()) {
			headerName = (String) enu.nextElement();
			sb.append(headerName).append("=")
					.append(request.getHeader(headerName)).append("\r\n");

		}
		return sb.toString();
	}
	
	/*
	 * 获得签名字符串
	 */
	private String getSignStr(HttpServletRequest request) {
		String result = "";
		List<String> lstHeaderNames = new ArrayList<String>();

		StringBuilder sb = new StringBuilder();
		@SuppressWarnings("rawtypes")
		Enumeration enu = request.getHeaderNames();
		while (enu.hasMoreElements()) {
			String headerName = (String) enu.nextElement();
			if ("sign".equals(headerName)) {
				continue;
			}
			lstHeaderNames.add(headerName);
		}
		// 对参数名称进行排序
		Collections.sort(lstHeaderNames);

		// 获得排序后的参数值
		for (String headerName : lstHeaderNames) {
			sb.append(headerName).append("=")
					.append(request.getHeader(headerName)).append("&");
		}
		result = sb.toString().substring(0, sb.length() - 1);
		logger.info("请求签名数据源:" + result);
		
		return result;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
	
	/**
	 * 接口统一回写方法
	 * 
	 * @param data
	 * @throws Exception
	 */
	private void writeResult(HttpServletResponse response, String data)
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
