package com.my.wallet.web.domain.bean;

import java.util.List;
import java.util.Map;

/******************************************
 * 
 * 银行卡相关信息实体Bean 提供与终端APP或其他系统数据交互的银行实体信息
 * 
 * @author HeTong
 * 
 ******************************************/

public class BankInfo {

	/* 银行名称 */
	private String bankName;

	/* 银行编码 */
	private String bankCode;

	/* 卡片属性 */
	private List<Map<String, String>> cardAttrs;
	

	public List<Map<String, String>> getCardAttrs() {
		return cardAttrs;
	}

	public void setCardAttrs(List<Map<String, String>> cardAttrs) {
		this.cardAttrs = cardAttrs;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
}
