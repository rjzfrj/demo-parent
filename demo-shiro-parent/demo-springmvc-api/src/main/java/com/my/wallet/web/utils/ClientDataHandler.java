package com.my.wallet.web.utils;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * 客户端数据处理器
 *
 */
public class ClientDataHandler {
	public static final String MESSAGE_SUCCESS = "操作成功";
	public static final String MESSAGE_LOGIN_FAIL = "用户未登录";
	
	/**
	 * 操作成功
	 */
	public static final String CODE_SUCCESS = "200";//成功状态码
	
	/**
	 * 操作失败
	 */
	public static final String CODE_FAIL_201 = "201";//失败状态码
	
	
	/**
	 * 处理中
	 */
	public static final String CODE_FAIL_202 = "202";//处理中
	

	/**
	 * 缺少必要参数
	 */
	public static final String CODE_FAIL_203 = "203";//未登录
	
	/**
	 * 操作失败/缺少参数
	 */
	public static final String CODE_FAIL_301 = "301";//失败状态码,操作失败/缺少参数

	/**
	 * 重复登录
	 */
	public static final String CODE_FAIL_501 = "501";//重复登录

	/**
	 * POS密钥已签到
	 */
	public static final String CODE_FAIL_500 = "500X";//POS密钥已签到
    
	/**
	 * 获取成功时默认返回结果
	 * @param message
	 * 		消息内容
	 * @return
	 */
	public static String getSuccessResult(String message){
		return getResult(CODE_SUCCESS, message);
	}
	
	/**
	 * 获取成功时默认返回结果
	 * @param message
	 * 		消息内容
	 * @return
	 */
	public static String getHandleResult(String message){
		return getResult(CODE_FAIL_500, message);
	}
	
	/**
	 * 获取成功时默认返回结果
	 * @param message
	 * 		消息内容
	 * @param body
	 * 		消息体
	 * @return
	 */
	public static String getSuccessResult(String message,String body){
		if(StringUtils.isEmpty(message)) message = MESSAGE_SUCCESS;
		return getResult(CODE_SUCCESS, message ,body);
	}
	
	/**
	 * 获取成功时默认返回结果
	 * @return
	 */
	public static String getSuccessResult(){
		return getSuccessResult("操作成功");
	}
	
	/**
	 * 获取失败时默认返回结果
	 * @param message
	 * 		消息内容
	 * @param body
	 * 		消息体
	 * @return
	 */
	public static String getFailResult(String message){
		return getResult(CODE_FAIL_201, message);
	}
	
	/**
	 * 获取失败时默认返回结果
	 * @param message
	 * 		消息内容
	 * @param body
	 * 		消息体
	 * @return
	 */
	public static String getFail203Result(String message){
		return getResult(CODE_FAIL_203,message);
	}
	
	
	/**
	 * 获取失败时默认返回结果
	 * @param message
	 * 		消息内容
	 * @return
	 */
	public static String getFailResult(String message,String body){
		return getResult(CODE_FAIL_201, message ,body);
	}
	
	/**
	 * 获取失败时默认返回结果(操作失败)
	 * @return
	 */
	public static String getFailResult(){
		return getFailResult("操作失败");
	}
	
	/**
	 * 获取失败时默认返回结果（缺失参数）
	 * @return
	 */
	public static String getFailResultWithMissParam(String param){
		if (param == null || "".equals(param)) {
			param = "缺少参数或参数不正确";
		}
		return getResult(CODE_FAIL_202, param);
	}
	
	/**
	 * 获取返回结果
	 * @param status
	 * 		状态码
	 * @param message
	 * 		消息内容
	 * @return
	 */
	public static String getResult(String status,String message){
		//return getDomResult(status,message).toString();
		JSONObject result = new JSONObject();
		result.put("code", status);
		result.put("msg", message);
		return result.toString();
	}
	
	/**
	 * 获取返回结果
	 * @param status
	 * 		状态码
	 * @param error
	 *      错误码
	 * @param message
	 * 		消息内容
	 * @return
	 */
	public static String getFailResult(String status, String error, String message){
		//return getDomResult(status,message).toString();
		JSONObject result = new JSONObject();
		result.put("code", status);
		result.put("msg", message);
		result.put("error", error);
		return result.toString();
	}
	
	/**
	 * 获取返回结果
	 * @param status
	 * 		状态码
	 * @param message
	 * 		消息内容
	 * @param body
	 * 		结果内容
	 * @return
	 */
	public static String getResult(String status,String message,String body){
		JSONObject result = new JSONObject();
		result.put("code", status);
		result.put("msg", message);
		String resultStr = result.toString();
		StringBuffer buffer = new StringBuffer(resultStr.substring(0, resultStr.length()-1));
		buffer.append(",\"result\":");
		if(StringUtils.isNotBlank(body)){
			buffer.append(body);
		}else{
			buffer.append("{}");
		}
		buffer.append("}");
		return buffer.toString();
	}

	
	/**
	 * 获取返回结果
	 * @param status
	 * 		状态码
	 * @param message
	 * 		消息内容
	 * @return
	 */
	public static Document getDomResult(String status,String message){
		Document document = DocumentHelper.createDocument();
		Element rootELement = DocumentHelper.createElement("result");//根节点
		document.setRootElement(rootELement);
		Element statusElement = rootELement.addElement("code");
		statusElement.addText(status);
		Element messageElement = rootELement.addElement("msg");
		messageElement.addText(message);
		return document;
	}
	
	/**
	 * 获取返回结果
	 * @param status
	 * 		状态码
	 * @param message
	 * 		消息内容
	 * @param body
	 * 		结果内容
	 * @return
	 */
	public static Document getDomResult(String status,String message,Element body){
		Document document = getDomResult(status,message);
		document.add(body);
		return document;
	}
}