package com.zy.spi.pay.sean;

import org.hibernate.validator.constraints.NotEmpty;

public class WxPay {
	
	@NotEmpty(message = "INVALID_merchantOrderId")
	private String merchantOrderId;
	@NotEmpty(message = "INVALID_merchantOrderTime")
	private String merchantOrderTime;
	@NotEmpty(message = "INVALID_mch_id")
	private String mch_id ;
	@NotEmpty(message = "INVALID_transAmt")
	private String transAmt ;
	private String remark  ;
	@NotEmpty(message = "INVALID_notifyUrl")
	private String notifyUrl ;
	
	public String getMerchantOrderId() {
		return merchantOrderId;
	}
	public void setMerchantOrderId(String merchantOrderId) {
		this.merchantOrderId = merchantOrderId;
	}
	public String getMerchantOrderTime() {
		return merchantOrderTime;
	}
	public void setMerchantOrderTime(String merchantOrderTime) {
		this.merchantOrderTime = merchantOrderTime;
	}
	public String getMch_id() {
		return mch_id;
	}
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	public String getTransAmt() {
		return transAmt;
	}
	public void setTransAmt(String transAmt) {
		this.transAmt = transAmt;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getNotifyUrl() {
		return notifyUrl;
	}
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	
	
}
