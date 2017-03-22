package com.my.wallet.web.security;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.my.wallet.web.utils.ContentTypes;

public class Sign {

	@SuppressWarnings("unchecked")
	public static String getReqParaStr(HttpServletRequest request, String method) {
		StringBuffer sbfParams = new StringBuffer();
		Map<String, String[]> requestParams = request.getParameterMap();
		String name = null;
//		String[] values = null;
		
		String bankCode = "";  // 银行编码
		String cardNo = "";
		String idCard = "";
		String mobile = "";
		String fullName = "";
		String merchantName = "";
		String subject = "";
		String validCode = "";
		String notifyUrl = "";
		String transAmt = "";
		String merchantId = "";
		String cvv2 = "";
		String validDate = "";
		String cardAttr = "";
		String outAcctId = "";
		String outOrderId = "";
		String goodsUrl = "";
		String orderId = "";
		String orderTime = "";
		String sysId = "";
		
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter
				.hasNext();) {
			name = iter.next();
//			values = requestParams.get(name);
			
			if ("sign".equals(name)) {
				continue; // 排除掉签名
			}
			
			if ("mobile".equals(name)) {
				mobile = AESCrypt.decryptBase64(request.getParameter("mobile")); // 手机号
			} else if ("cardNo".equals(name)) {
				cardNo = AESCrypt.decryptBase64(request.getParameter("cardNo")); // 卡号
			} else if ("idCard".equals(name)) {
				idCard = AESCrypt.decryptBase64(request.getParameter("idCard")); // 身份证号
			} else if ("fullName".equals(name)) {
				try {
					fullName = java.net.URLDecoder.decode(request.getParameter("fullName"), ContentTypes.UTF8_ENCODING);
				} catch (UnsupportedEncodingException e) {
				}
			} else if ("merchantName".equals(name)) {
				try {
					merchantName = java.net.URLDecoder.decode(request.getParameter("merchantName"), ContentTypes.UTF8_ENCODING);
				} catch (UnsupportedEncodingException e) {
				}
			} else if ("subject".equals(name)) {
				try {
					subject = java.net.URLDecoder.decode(request.getParameter("subject"), ContentTypes.UTF8_ENCODING);
				} catch (UnsupportedEncodingException e) {
				}
			} else if ("validCode".equals(name)) {
				validCode = AESCrypt.decryptBase64(request.getParameter("validCode")); // 短信验证码
			} else if ("notifyUrl".equals(name)) {
				try {
					notifyUrl = java.net.URLDecoder.decode(request.getParameter("notifyUrl"), ContentTypes.UTF8_ENCODING); // 回调地址
				} catch (UnsupportedEncodingException e) {
				} 
			} else if ("transAmt".equals(name)) {
				transAmt = request.getParameter("transAmt");
			} else if ("bankCode".equals(name)) {
				bankCode = request.getParameter("bankCode");
			} else if ("merchantId".equals(name)) {
				merchantId = request.getParameter("merchantId");
			} else if ("cvv2".equals(name)) {
				cvv2 = request.getParameter("cvv2");
			} else if ("validDate".equals(name)) {
				validDate = request.getParameter("validDate");
			} else if ("cardAttr".equals(name)) {
				cardAttr = request.getParameter("cardAttr");
			} else if ("outAcctId".equals(name)) {
				outAcctId = request.getParameter("outAcctId");
			} else if ("outOrderId".equals(name)) {
				outOrderId = request.getParameter("outOrderId");
			} else if ("goodsUrl".equals(name)) {
				goodsUrl = request.getParameter("goodsUrl");
			} else if ("orderId".equals(name)) {
				orderId = request.getParameter("orderId");
			} else if ("orderTime".equals(name)) {
				orderTime = request.getParameter("orderTime");
			} else if ("outOrderId".equals(name)) {
				outOrderId = request.getParameter("outOrderId");
			} else if ("sysId".equals(name)) {
				sysId = request.getParameter("sysId");
			}
		}
		
		// *************************** 以下方法是为了解决终端传递过来参数顺序不对，导致签名数据源对不上的问题 *************************** //
		if ("pay".equals(method)) { // 如果是支付的方法
			sbfParams.append("orderId=");
			sbfParams.append(orderId);
			sbfParams.append("&merchantId=");
			sbfParams.append(merchantId);
			sbfParams.append("&transAmt=");
			sbfParams.append(transAmt);
			sbfParams.append("&notifyUrl=");
			sbfParams.append(notifyUrl);
			sbfParams.append("&validCode=");
			sbfParams.append(validCode);
			sbfParams.append("&orderTime=");
			sbfParams.append(orderTime);
			sbfParams.append("&outOrderId=");
			sbfParams.append(outOrderId);
			sbfParams.append("&bankCode=");
			sbfParams.append(bankCode);
		} else if ("sendValidCode".equals(method)) {
			sbfParams.append("bankCode=");
			sbfParams.append(bankCode);
			sbfParams.append("&transAmt=");
			sbfParams.append(transAmt);
			sbfParams.append("&cardNo=");
			sbfParams.append(cardNo);
			sbfParams.append("&mobile=");
			sbfParams.append(mobile);
			sbfParams.append("&merchantId=");
			sbfParams.append(merchantId);
			sbfParams.append("&cvv2=");
			sbfParams.append(cvv2);
			sbfParams.append("&idCard=");
			sbfParams.append(idCard);
			sbfParams.append("&fullName=");
			sbfParams.append(fullName);
			sbfParams.append("&validDate=");
			sbfParams.append(validDate);
			sbfParams.append("&merchantName=");
			sbfParams.append(merchantName);
			sbfParams.append("&cardAttr=");
			sbfParams.append(cardAttr);
//			sbfParams.append("&outAcctId=");
//			sbfParams.append(outAcctId);
			sbfParams.append("&outOrderId=");
			sbfParams.append(outOrderId);
			sbfParams.append("&subject=");
			sbfParams.append(subject);
			sbfParams.append("&goodsUrl=");
			sbfParams.append(goodsUrl);
			sbfParams.append("&notifyUrl=");
			sbfParams.append(notifyUrl);
			sbfParams.append("&sysId=");
			sbfParams.append(sysId);
		}
		// *************************** 以下方法是为了解决终端传递过来参数顺序不对，导致签名数据源对不上的问题 *************************** //
		
		String reqParaStr = sbfParams.toString();
		
		return reqParaStr;
	}
}
