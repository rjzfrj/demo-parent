package com.my.wallet.web.domain;

import java.math.BigDecimal;
import java.util.Date;

/******************************************
 * 
 * 交易订单实体类
 * 
 * @author HeTong
 * 
 ******************************************/

public class WalletPayOrder implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/* 主键ID，订单号 */
	private String orderId;

	/* 外部订单号 */
	private String outOrderId;

	/* 会员系统会员号 */
	private String userNo;

	/* 交易金额 */
	private BigDecimal tranAmt;

	/* 商品描述 */
	private String remark;

	/* 回调地址 */
	private String notifyUrl;

	/* 商户号 */
	private String merchantId;

	/* 交易时间 */
	private Date tranTime;

	/* 银行卡号 */
	private String cardNo;

	/* 手机号 */
	private String mobile;

	/* 通知状态:00:未发送通知(默认)；01：已通知；02：发送通知反馈失败 */
	private String notifyStatus;

	/* 发送次数，默认0次，上限7次 */
	private Integer notifyCnt;

	/* 交易状态:00: 订单处理中；01: 订单支付成功；02: 订单支付失败 */
	private String tranStatus;

	/* 交易类型: 00: 消费 01:充值 */
	private String tranType;

	/* 交易来源: 00: 零钱 01:刷卡 */
	private String tranSource;
	
	/* 商品名称 */
	private String goodsName;
	
	/* 订单金额 */
	private BigDecimal orderAmt;
	
	/* 错误原因 */
	private String errorCause;
	
	
	/*
	 * 金融基础返回编号这个可以用再接收到消息后来查找到订单然后更新状态
	 */
	private String tranNo;
	
	
	/*
	 * 转账对方账号
	 */
	private String otherUserNo;
	
	/** 收取用户的手续费*/
	private BigDecimal feeAmt;
	/** 费率*/
	private BigDecimal flatFee;
	/** 结算金额*/
	private BigDecimal settlementAmount;
	/** 是否结算 0 否 1 是*/
	private String isSettle;
	/** 已结算标志 0 未结算 1 已结算*/
	private String settleFlag;
	
	private String chnOrderNo;  //新增渠道交易流水号
	
	public String getErrorCause() {
		return errorCause;
	}

	public void setErrorCause(String errorCause) {
		this.errorCause = errorCause;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}


	public String getTranType() {
		return tranType;
	}

	public void setTranType(String tranType) {
		this.tranType = tranType;
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

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}


	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public Date getTranTime() {
		return tranTime;
	}

	public void setTranTime(Date tranTime) {
		this.tranTime = tranTime;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getNotifyStatus() {
		return notifyStatus;
	}

	public void setNotifyStatus(String notifyStatus) {
		this.notifyStatus = notifyStatus;
	}

	public Integer getNotifyCnt() {
		return notifyCnt;
	}

	public void setNotifyCnt(Integer notifyCnt) {
		this.notifyCnt = notifyCnt;
	}

	public String getTranStatus() {
		return tranStatus;
	}

	public void setTranStatus(String tranStatus) {
		this.tranStatus = tranStatus;
	}

	public String getTranSource() {
		return tranSource;
	}

	public void setTranSource(String tranSource) {
		this.tranSource = tranSource;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTranNo()
	{
		return tranNo;
	}

	public void setTranNo(String tranNo)
	{
		this.tranNo = tranNo;
	}

	public String getOtherUserNo()
	{
		return otherUserNo;
	}

	public void setOtherUserNo(String otherUserNo)
	{
		this.otherUserNo = otherUserNo;
	}

	
	public BigDecimal getTranAmt()
	{
		return tranAmt;
	}

	public void setTranAmt(BigDecimal tranAmt)
	{
		this.tranAmt = tranAmt;
	}

	public BigDecimal getOrderAmt()
	{
		return orderAmt;
	}

	public void setOrderAmt(BigDecimal orderAmt)
	{
		this.orderAmt = orderAmt;
	}
	

	public BigDecimal getFeeAmt()
	{
		return feeAmt;
	}

	public void setFeeAmt(BigDecimal feeAmt)
	{
		this.feeAmt = feeAmt;
	}

	public BigDecimal getFlatFee()
	{
		return flatFee;
	}

	public void setFlatFee(BigDecimal flatFee)
	{
		this.flatFee = flatFee;
	}

	public BigDecimal getSettlementAmount()
	{
		return settlementAmount;
	}

	public void setSettlementAmount(BigDecimal settlementAmount)
	{
		this.settlementAmount = settlementAmount;
	}

	public String getIsSettle()
	{
		return isSettle;
	}

	public void setIsSettle(String isSettle)
	{
		this.isSettle = isSettle;
	}

	public String getSettleFlag()
	{
		return settleFlag;
	}

	public void setSettleFlag(String settleFlag)
	{
		this.settleFlag = settleFlag;
	}
	

	public String getChnOrderNo() {
		return chnOrderNo;
	}

	public void setChnOrderNo(String chnOrderNo) {
		this.chnOrderNo = chnOrderNo;
	}

	@Override
	public String toString()
	{
		return "WalletPayOrder [orderId=" + orderId + ", outOrderId=" + outOrderId + ", userNo=" + userNo + ", tranAmt=" + tranAmt
				+ ", remark=" + remark + ", notifyUrl=" + notifyUrl + ", merchantId=" + merchantId + ", tranTime=" + tranTime + ", cardNo="
				+ cardNo + ", mobile=" + mobile + ", notifyStatus=" + notifyStatus + ", notifyCnt=" + notifyCnt + ", tranStatus="
				+ tranStatus + ", tranType=" + tranType + ", tranSource=" + tranSource + ", goodsName=" + goodsName + ", orderAmt="
				+ orderAmt + ", errorCause=" + errorCause + ", tranNo=" + tranNo + ", otherUserNo=" + otherUserNo + ", feeAmt=" + feeAmt
				+ ", flatFee=" + flatFee + ", settlementAmount=" + settlementAmount + ", isSettle=" + isSettle + ", settleFlag="
				+ settleFlag + "]";
	}

	

	
	

}