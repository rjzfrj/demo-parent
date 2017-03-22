package com.my.wallet.web.domain;

import java.util.Date;

/******************************************
 * 
 * 用户操作计数表实体类
 * 
 * @author HeTong
 * 
 ******************************************/

public class WalletOperateCount implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/* 主键ID */
	private String id;
	
	/* 商户号 */
	private String merchantId;
	
	/* 会员系统会员号 */
	private String userNo;
	
	/* 计数类型 */
	private String type;
	
	/* 计数次数 */
	private Integer operateCount;

	/* 最后计数时间 */
	private Date countTime;
	
	
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getOperateCount() {
		return operateCount;
	}

	public void setOperateCount(Integer operateCount) {
		this.operateCount = operateCount;
	}

	public Date getCountTime() {
		return countTime;
	}

	public void setCountTime(Date countTime) {
		this.countTime = countTime;
	}
	
}
