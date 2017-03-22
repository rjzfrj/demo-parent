package com.my.wallet.web.domain;

import java.util.Date;

/******************************************
 * 
 * 用户数字码对应表实体类
 * 
 * @author HeTong
 * 
 ******************************************/

public class WalletScanCodes implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/* 主键ID */
	private String id;
	
	/* 商户号 */
	private String merchantId;
	
	/* 会员系统会员号 */
	private String userNo;
	
	/* 数字码 */
	private String scanCode;
	
	/* 有效状态：00：未使用;01：已使用;02：已过期 */
	private String status;
	
	/* 创建时间 */
	private Date createTime;
	
	/* 绑定银行卡ID */
	private String bindCardId;
	

	public String getBindCardId() {
		return bindCardId;
	}

	public void setBindCardId(String bindCardId) {
		this.bindCardId = bindCardId;
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

	public String getScanCode() {
		return scanCode;
	}

	public void setScanCode(String scanCode) {
		this.scanCode = scanCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
