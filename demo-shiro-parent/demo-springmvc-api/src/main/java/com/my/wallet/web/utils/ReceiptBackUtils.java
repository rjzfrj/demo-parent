package com.my.wallet.web.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

public class ReceiptBackUtils {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ReceiptBackUtils.class);

	public static void backToFrontUrl(String  frontUrl,String sysOrderNo,String merchOrderNo,String merchantNo,String amount,String retCode,String retMsg,HttpServletResponse response){
		if(StringUtils.isEmpty(frontUrl) || StringUtils.isEmpty(sysOrderNo)){
			logger.info("调用url为空，无法跳转");
			return;
		}
		String url=frontUrl;
		if(!StringUtils.isEmpty(sysOrderNo)){
			url=url+"?sysOrderNo="+sysOrderNo;
		}
		if(!StringUtils.isEmpty(merchOrderNo)){
			url=url+"&merchOrderNo="+merchOrderNo;
		}		
		if(!StringUtils.isEmpty(merchantNo)){
			url=url+"&merchantId="+merchantNo;
		}		
		if(!StringUtils.isEmpty(amount)){
			url=url+"&amount="+amount;
		}
		if(!StringUtils.isEmpty(retCode)){
			url=url+"&retCode="+retCode;
		}
		if(!StringUtils.isEmpty(retMsg)){
			url=url+"&retMsg="+retMsg;
		}
		try {
			if(logger.isInfoEnabled()){
				logger.info("return metchatn url:"+url);
			}
			response.sendRedirect(url);
		} catch (IOException e) {
			logger.error("backSucceed(AgentRecInfo, HttpServletResponse)", e); //$NON-NLS-1$

		}
	}
}
