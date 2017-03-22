package com.my.wallet.web.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.my.wallet.web.service.HomeService;
import com.my.wallet.web.utils.ConverTool;
import com.zy.spi.pay.sean.WxPay;

/******************************************
 * 
 * 钱包主页服务接口实现类 
 * 提供钱包主页相关接口的业务处理
 * 
 * @author HeTong
 * 
 ******************************************/

@Service
public class HomeServiceImpl  implements HomeService {
	// 设置日志调用类
	Logger logger = Logger.getLogger(HomeServiceImpl.class);
	/**
	 * 进入到钱包首页，如果是第一次进入，则会自动创建一个钱包支付账户，如果已经创建过支付账户，则直接进入
	 * 
	 * @param request
	 *            请求参数
	 * @param response
	 *            请求应答
	 */
	@Override
	public String walletHome(HttpServletRequest request,
			HttpServletResponse response) {
		String result = "";
		try {
			// ******************** 获得参数部分 ******************** //
			String userNo = request.getHeader("userNo"); // 会员系统会员号
			String merchantId = request.getHeader("merchantId"); // 商户号
			Map<String, String> map = new HashMap<String, String>();
			map.put("change", "0.01"); // 用户零钱
			map.put("bindStatus", "1"); // 绑定过卡的状态
			map.put("accountStatus", "2"); // 用户状态
			map.put("userNo", userNo); //
			map.put("merchantId", merchantId); // 
			StringBuffer sbf = ConverTool.conver(map, null);
			// 把JSON格式的字符串设置到返回字符串中
			result = sbf.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	@Override
	public String Pay(WxPay wxPay) {
		String result = "";
		StringBuffer sbf;
		try {
			sbf = ConverTool.conver(wxPay, null);
			result = sbf.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 把JSON格式的字符串设置到返回字符串中
		
		return result;
	}


}
