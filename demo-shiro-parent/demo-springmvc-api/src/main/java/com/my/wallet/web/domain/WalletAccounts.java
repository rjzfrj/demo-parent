package com.my.wallet.web.domain;

import java.math.BigDecimal;
import java.util.Date;

/******************************************
 * 
 * 用户支付账户表实体类
 * 
 * @author HeTong
 * 
 ******************************************/

public class WalletAccounts implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/* 主键ID */
	private String id;
	
	/* 商户号 */
	private String merchantId;
	
	/* 会员系统会员号 */
	private String userNo;
	
	/* 支付密码 */
	private String payPassword;
	

	/* 零钱 */
	private BigDecimal balance;
	/* 手机号 */
	private String mobile;
	
	/* 用户银行卡绑定状态 00：未绑定过卡；01：已绑定过卡 */
	private String bindStatus;
	
	/* 用户账户状态 00：有效；01：无效 */
	private String accountStatus;
	
	/* 创建时间 */
	private Date createTime;
	
	/* 用户姓名 */
	private String userName;
	
	/* 密钥文本 */
	private String keyText;
	
	/* 商户名称 */
	private String merchantName;
	
	/* 身份证号 */
	private String idCard;
	
	/* 冻结金额 */
	private BigDecimal freezeBalamt;
	
	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getKeyText() {
		return keyText;
	}

	public void setKeyText(String keyText) {
		this.keyText = keyText;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getPayPassword() {
		return payPassword;
	}

	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getBindStatus() {
		return bindStatus;
	}

	public void setBindStatus(String bindStatus) {
		this.bindStatus = bindStatus;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getFreezeBalamt()
	{
		return freezeBalamt;
	}

	public void setFreezeBalamt(BigDecimal freezeBalamt)
	{
		this.freezeBalamt = freezeBalamt;
	}

	@Override
	public String toString()
	{
		return "WalletAccounts [id=" + id + ", merchantId=" + merchantId + ", userNo=" + userNo + ", payPassword=" + payPassword
				+ ", balance=" + balance + ", mobile=" + mobile + ", bindStatus=" + bindStatus + ", accountStatus=" + accountStatus
				+ ", createTime=" + createTime + ", userName=" + userName + ", keyText=" + keyText + ", merchantName=" + merchantName
				+ ", idCard=" + idCard + ", freezeBalamt=" + freezeBalamt + "]";
	}

	
	
}
