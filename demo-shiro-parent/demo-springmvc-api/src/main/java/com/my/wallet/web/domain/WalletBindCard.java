package com.my.wallet.web.domain;

import java.util.Date;

/******************************************
 * 
 * 用户绑定银行卡实体类
 * 
 * @author HeTong
 * 
 ******************************************/

public class WalletBindCard implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;

	/* 主键ID */
	private String id;
	
	/* 商户号 */
	private String merchantId;
	
	/* 会员系统会员号 */
	private String userNo;
	
	/* 银行卡号 */
	private String cardNo;

	/* 银行编码 */
	private String bankCode;
	
	/* 手机号 */
	private String mobile;
	
	/* 卡属性:01 借记卡 02 信用卡 */
	private String cardAttr; // 
	
	/* 是否默认卡: 00:不是默认 01:默认 */
	private String defaultStatus; // 信用卡有效期
	
	/* 银行名称 */
	private String bankName;
	
	/* 创建时间 */
	private Date createTime;
	
	/* 协议号 */
	private String agreeNo;
	
	/* 流水号，根据流水号通知 */
	private String tranNo;
	
	/* 处理状态 01 已申请 02 成功 03 失败 */
	private String tranStatus;
	
	/* 卡有效期 */
	private String validDate;
	
	/* CVV2 */
	private String cvv2;
	
	/* 身份证号 */
	private String idCard;
	
	/* 错误原因 */
	private String errorCause;
	
	/**
	 * 持卡人姓名
	 */
	private String acctName;
	
	public String getErrorCause() {
		return errorCause;
	}

	public void setErrorCause(String errorCause) {
		this.errorCause = errorCause;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getCvv2() {
		return cvv2;
	}

	public void setCvv2(String cvv2) {
		this.cvv2 = cvv2;
	}

	public String getValidDate() {
		return validDate;
	}

	public void setValidDate(String validDate) {
		this.validDate = validDate;
	}

	public String getTranStatus() {
		return tranStatus;
	}

	public void setTranStatus(String tranStatus) {
		this.tranStatus = tranStatus;
	}

	public String getTranNo() {
		return tranNo;
	}

	public void setTranNo(String tranNo) {
		this.tranNo = tranNo;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCardAttr() {
		return cardAttr;
	}

	public void setCardAttr(String cardAttr) {
		this.cardAttr = cardAttr;
	}

	public String getDefaultStatus() {
		return defaultStatus;
	}

	public void setDefaultStatus(String defaultStatus) {
		this.defaultStatus = defaultStatus;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getAgreeNo() {
		return agreeNo;
	}

	public void setAgreeNo(String agreeNo) {
		this.agreeNo = agreeNo;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	

	public String getAcctName()
	{
		return acctName;
	}

	public void setAcctName(String acctName)
	{
		this.acctName = acctName;
	}

	@Override
	public String toString()
	{
		return "WalletBindCard [id=" + id + ", merchantId=" + merchantId + ", userNo=" + userNo + ", cardNo=" + cardNo + ", bankCode="
				+ bankCode + ", mobile=" + mobile + ", cardAttr=" + cardAttr + ", defaultStatus=" + defaultStatus + ", bankName="
				+ bankName + ", createTime=" + createTime + ", agreeNo=" + agreeNo + ", tranNo=" + tranNo + ", tranStatus=" + tranStatus
				+ ", validDate=" + validDate + ", cvv2=" + cvv2 + ", idCard=" + idCard + ", errorCause=" + errorCause + ", acctName="
				+ acctName + "]";
	}

	
	

	
}