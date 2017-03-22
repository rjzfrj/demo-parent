package com.my.wallet.web.domain.bean;

import java.io.Serializable;
import java.util.Date;

public class WalletNotifyResult implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String retCode;
	private String retMsg;
	private String tranStatus;  //订单状态
	private String orderId;  //本地订单号
	private	String outOrderId; //外部订单ID
	private String tranType; //支付方式
	private String goodsName;  //商品名称
	private Date tranTime;	//交易时间
	
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
	public String getOrderId()
	{
		return orderId;
	}
	public void setOrderId(String orderId)
	{
		this.orderId = orderId;
	}
	public String getOutOrderId()
	{
		return outOrderId;
	}
	public void setOutOrderId(String outOrderId)
	{
		this.outOrderId = outOrderId;
	}
	public String getTranType()
	{
		return tranType;
	}
	public void setTranType(String tranType)
	{
		this.tranType = tranType;
	}
	public String getGoodsName()
	{
		return goodsName;
	}
	public void setGoodsName(String goodsName)
	{
		this.goodsName = goodsName;
	}
	
	public Date getTranTime()
	{
		return tranTime;
	}
	public void setTranTime(Date tranTime)
	{
		this.tranTime = tranTime;
	}
	
	public String getTranStatus()
	{
		return tranStatus;
	}
	public void setTranStatus(String tranStatus)
	{
		this.tranStatus = tranStatus;
	}
	
	@Override
	public String toString()
	{
		return "WalletNotifyResult [retCode=" + retCode + ", retMsg=" + retMsg + ", tranStatus=" + tranStatus + ", orderId=" + orderId
				+ ", outOrderId=" + outOrderId + ", tranType=" + tranType + ", goodsName=" + goodsName + ", tranTime=" + tranTime + "]";
	}
	
	
	
	

	
}
