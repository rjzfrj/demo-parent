package com.my.wallet.web.domain.bean;

/**
 * 订单请求参数
 * 
 * @author zhouqg
 * 
 */
public class PayOrderReqParam {

	/**
	 * 用户id
	 */
	private String userNo;

	/**
	 * 商户号
	 */
	private String merchantId;
	
	private String merchantName;

	/**
	 * 商户id
	 */
	private String outOrderId;

	/**
	 * 交易金额
	 */
	private String transAmt;

	/**
	 * 描述
	 */
	private String desc;

	/**
	 * 绑定银行卡id
	 */
	private String cardId;

	/**
	 * 回调地址
	 */
	private String notifyUrl;

	/**
	 * 交易类型
	 */
	private String tranType;

	/**
	 * 交易来源
	 */
	private String tranSource;

	/**
	 * 备注
	 */
	private String remark;

	/**
	 * 订单类型
	 */
	private String orderType;

	/**
	 * 订单子类型
	 */
	private String orderSubType;
	
	/**
	 * 手机号
	 */
	private String mobile;
	
	/**
	 * 协议号
	 */
	private String agreeNo;
	
	/**
	 * 卡片属性
	 */
	private String cardAttr;
	
	/**
	 * 商品名称
	 */
	private String goodsName;
	
	/**
	 * 交易金额
	 */
	private String orderAmt;
	
	/**
	 * 卡号
	 */
	private String cardNo;
	
	
	/**
	 * 对方账户转账用其他业务null
	 */
	private String otherUserNo;
	
	
	
	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getOrderAmt() {
		return orderAmt;
	}

	public void setOrderAmt(String orderAmt) {
		this.orderAmt = orderAmt;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getCardAttr() {
		return cardAttr;
	}

	public void setCardAttr(String cardAttr) {
		this.cardAttr = cardAttr;
	}

	public String getAgreeNo() {
		return agreeNo;
	}

	public void setAgreeNo(String agreeNo) {
		this.agreeNo = agreeNo;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getOutOrderId() {
		return outOrderId;
	}

	public void setOutOrderId(String outOrderId) {
		this.outOrderId = outOrderId;
	}

	public String getTransAmt() {
		return transAmt;
	}

	public void setTransAmt(String transAmt) {
		this.transAmt = transAmt;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getTranType() {
		return tranType;
	}

	public void setTranType(String tranType) {
		this.tranType = tranType;
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

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getOrderSubType() {
		return orderSubType;
	}

	public void setOrderSubType(String orderSubType) {
		this.orderSubType = orderSubType;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getMerchantName()
	{
		return merchantName;
	}

	public void setMerchantName(String merchantName)
	{
		this.merchantName = merchantName;
	}

	public String getOtherUserNo()
	{
		return otherUserNo;
	}

	public void setOtherUserNo(String otherUserNo)
	{
		this.otherUserNo = otherUserNo;
	}

	
	
	

}
