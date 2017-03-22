package com.my.wallet.web.domain.bean;


public class WalletNotifyParam implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private String retCode; // 状态码
	private String retMsg; // 信息
	private String orderId; // 订单系统订单号
	private String outOrderId; // 外部订单号
	private String orderAmt; // 订单交易金额
	private String orderTime; // 订单时间
	private String merchantId; // 商户号
	
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public String getRetCode() {
		return retCode;
	}
	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}
	public String getRetMsg() {
		return retMsg;
	}
	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOutOrderId() {
		return outOrderId;
	}
	public void setOutOrderId(String outOrderId) {
		this.outOrderId = outOrderId;
	}
	public String getOrderAmt() {
		return orderAmt;
	}
	public void setOrderAmt(String orderAmt) {
		this.orderAmt = orderAmt;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	@Override
	public String toString()
	{
		return "WalletNotifyParam [retCode=" + retCode + ", retMsg=" + retMsg + ", orderId=" + orderId + ", outOrderId=" + outOrderId
				+ ", orderAmt=" + orderAmt + ", orderTime=" + orderTime + ", merchantId=" + merchantId + "]";
	}
	
	
	
}
