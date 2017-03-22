package com.my.wallet.web.domain.bean;

/******************************************
 * 
 * 银行卡属性实体Bean 
 * 提供与终端APP或其他系统数据交互的银行实体信息
 * 
 * @author HeTong
 * 
 ******************************************/

public class CardAttr {

	/* 卡属性01-借记卡 */
	private String cardAttr01;
	
	/* 卡属性02-信用卡 */
	private String cardAttr02;
	
	public String getCardAttr01() {
		return cardAttr01;
	}

	public void setCardAttr01(String cardAttr01) {
		this.cardAttr01 = cardAttr01;
	}

	public String getCardAttr02() {
		return cardAttr02;
	}

	public void setCardAttr02(String cardAttr02) {
		this.cardAttr02 = cardAttr02;
	}
	
}
