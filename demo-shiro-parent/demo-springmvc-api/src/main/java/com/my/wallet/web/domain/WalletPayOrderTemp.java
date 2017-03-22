package com.my.wallet.web.domain;

import java.util.Date;

/******************************************
 * 
 * 交易订单临时实体类
 * 
 * @author HeTong
 * 
 ******************************************/

public class WalletPayOrderTemp implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/* 主键ID */
	private String id;

	/* 外部订单号 */
	private String outOrderId;

	/* 会员系统会员号 */
	private String userNo;

	/* 交易金额 */
	private String tranAmt;

	/* 商品描述 */
	private String remark;

	/* 商户号 */
	private String merchantId;

	/* 交易时间 */
	private Date createTime;

	/* 手机号 */
	private String mobile;

	/* 交易类型: 00: 消费 01:充值 */
	private String tranType;

	/* 交易来源: 00: 零钱 01:刷卡 */
	private String tranSource;
	
	/* 商品名称 */
	private String goodsName;
	
	/* 订单金额 */
	private String orderAmt;
	
	/* 条码或二维码数字 */
	private String scanCode;
	
	public String getScanCode() {
		return scanCode;
	}

	public void setScanCode(String scanCode) {
		this.scanCode = scanCode;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getOrderAmt() {
		return orderAmt;
	}

	public void setOrderAmt(String orderAmt) {
		this.orderAmt = orderAmt;
	}

	public String getTranType() {
		return tranType;
	}

	public void setTranType(String tranType) {
		this.tranType = tranType;
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

	public String getTranAmt() {
		return tranAmt;
	}

	public void setTranAmt(String tranAmt) {
		this.tranAmt = tranAmt;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
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
	
	public String getId() {
		return id;
	}
	

	public void setId(String id) {
		this.id = id;
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
		return "WalletPayOrderTemp [id=" + id + ", outOrderId=" + outOrderId + ", userNo=" + userNo + ", tranAmt=" + tranAmt + ", remark="
				+ remark + ", merchantId=" + merchantId + ", createTime=" + createTime + ", mobile=" + mobile + ", tranType=" + tranType
				+ ", tranSource=" + tranSource + ", goodsName=" + goodsName + ", orderAmt=" + orderAmt + ", scanCode=" + scanCode + "]";
	}
	
}