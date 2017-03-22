package com.my.wallet.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.wallet.web.service.HomeService;
import com.my.wallet.web.utils.ClientDataHandler;
import com.zy.spi.pay.sean.WxPay;

/******************************************
 * 
 * 统一接口类 提供与终端APP或其他系统数据交互的入口方法
 * 
 * @author HeTong
 * 
 ******************************************/

@Controller
@RequestMapping("/api")
public class ApiController extends BaseController {

	// 设置日志调用类
	Logger logger = Logger.getLogger(ApiController.class);


	/* 钱包主页接口类 */
	@Autowired
	private HomeService homeService;
	
	

	/**
	 * 01 钱包首页接口
	 * 
	 * @param request
	 *            请求参数
	 * @param response
	 *            请求应答
	 */
	@RequestMapping(value = "/home", produces = "application/json")
	@ResponseBody
	public void walletHome(HttpServletRequest request,
			HttpServletResponse response) {
		// 返回的JSON字符串
		String result = "";
		try {
			// 调用钱包首页服务类进入钱包首页
			String retData = homeService.walletHome(request, response);
			// 设置JSON格式的返回内容字符串
			result = ClientDataHandler.getSuccessResult("", retData);
		} catch (Exception e) {
			e.printStackTrace();
			// 如果出异常了，就统一给终端APP返回一个异常错误信息
			result = error_message;
		} finally {
			try {
				// 把接口数据写回给终端APP
				writeResult(response, result);
			} catch (Exception e) {
			}
		}
	}
	
	@RequestMapping(value = "/pay", produces = "application/json;charset=utf-8")
	@ResponseBody
	public void pay(@Valid WxPay wxPay,BindingResult result,HttpServletRequest request,
			HttpServletResponse response) {
		// 返回的JSON字符串
		String retmessage = "";
		try {
			
			  if (result.hasErrors()) {
		            if (logger.isInfoEnabled()) {
		                logger.info("payOnline >>> valid params err !!! >>>"
		                        + result.getFieldError().getField() + " "
		                        + result.getFieldError().getDefaultMessage());
		            }
		            // 返回错误信息提示页面
		            retmessage = ClientDataHandler.getResult("001",result.getFieldError().getDefaultMessage());
		            writeResult(response, retmessage);
		        }
			 retmessage = homeService.Pay(wxPay);
			// 设置JSON格式的返回内容字符串
		} catch (Exception e) {
			e.printStackTrace();
			// 如果出异常了，就统一给终端APP返回一个异常错误信息
			retmessage = error_message;
		} finally {
			try {
				// 把接口数据写回给终端APP
				writeResult(response, retmessage);
			} catch (Exception e) {
			}
		}
	}


		

}
