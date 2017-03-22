package com.my.wallet.web.domain;

import java.util.Date;

/******************************************
 * 
 * 用户短信验证码实体类
 * 
 * @author HeTong
 * 
 ******************************************/

public class WalletVerifyCode implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/* 主键ID */
	private String id;
	
	/* 商户号 */
	private String merchantId;
	
	/* 会员系统会员号 */
	private String userNo;
	
	/* 手机号 */
	private String mobile;
	
	/* 短信验证码 */
	private String verifyCode;
	
	/* 创建时间 */
	private Date createTime;
	

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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString()
	{
		return "WalletVerifyCode [id=" + id + ", merchantId=" + merchantId + ", userNo=" + userNo + ", mobile=" + mobile + ", verifyCode="
				+ verifyCode + ", createTime=" + createTime + "]";
	}
	
	
	
}
