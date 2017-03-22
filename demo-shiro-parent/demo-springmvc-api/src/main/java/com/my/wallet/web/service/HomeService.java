package com.my.wallet.web.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zy.spi.pay.sean.WxPay;

/******************************************
 * 
 * 钱包主页服务接口 
 * 提供钱包主页相关接口的调用入口，包含账户相关内容
 * 
 * @author HeTong
 * 
 ******************************************/
public interface HomeService {

	/**
	 * 进入到钱包首页，如果是第一次进入，则会根据用户ID自动创建一个钱包支付账户，如果已经创建过支付账户，则直接进入
	 * 
	 * @param request
	 *            请求参数
	 * @param response
	 *            请求应答
	 */
	public String walletHome(HttpServletRequest request,
			HttpServletResponse response);
	
	public String Pay(WxPay wxPay);
	
	
}
