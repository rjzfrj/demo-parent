package com.my.wallet.web.constant;

/******************************************
 * 
 * 常量键值对类 系统中的编码与提示的关系类
 * 
 * @author HeTong
 * 
 ******************************************/

public class ConstantKeyMap {
	
	/*
	 * 订单状态
	 */
	public static enum OrderStatus{
		/* 交易状态：00： 订单处理中*/
		ORDER_PROCESSING("00","订单处理中"),
		/* 交易状态：01：订单支付成功 */
		ORDER_PAY_SUC("01","订单支付成功"), 
		/* 交易状态：02：订单支付失败 */
		ORDER_PAY_FAIL("02","订单支付失败");
		
		private final String key;
		private final String value;
		
		public String getKey() {
			return key;
		}

		public String getValue() {
			return value;
		}

		OrderStatus(String key,String value) {
			this.key = key;
			this.value= value;
		}
	}
	
	/*
	 * 交易类型
	 */
	public static enum TranType{
		/* 交易类型：00： 消费 */
		TRAN_CONSUME("00","消费"),
		/* 交易类型：01：充值 */
		ORDER_PAY_FAIL("01","充值"); 
		
		private final String key;
		private final String value;
		
		public String getKey() {
			return key;
		}

		public String getValue() {
			return value;
		}

		TranType(String key,String value) {
			this.key = key;
			this.value= value;
		}
	}
	
	/*
	 * 扫码生成的数字码状态
	 */
	public static enum ScanCodeStatus{
		/* 数字码状态：00： 未使用 */
		CODE_NOT_USED("00","未使用"),
		/* 数字码状态：01： 已使用 */
		CODE_HAS_USED("01","已使用"),
		/* 数字码状态：02：已过期 */
		ORDER_EXPIRED("02","已过期");
		
		
		private final String key;
		private final String value;
		
		public String getKey() {
			return key;
		}

		public String getValue() {
			return value;
		}

		ScanCodeStatus(String key,String value) {
			this.key = key;
			this.value= value;
		}
	}
	
	
	public static enum BusinessType{
		/* 业务类型：00：付款 */
		confirmPay("00","付款"),
		/* 业务类型：01：充值 */
		recharge("01","充值"),
		/* 业务类型：02：提现 */
		withdraw("02","提现"),
		/* 业务类型：03：转账 */
		transfer("03","转账"),
		/* 业务类型：04：解绑 */
		unwrap("04","解绑"),
		/* 业务类型：05：绑卡 */
		bind("05","绑卡");
		
		//00:消费 01 充值02提现03转出04转入
		
		private final String key;
		private final String value;
		
		public String getKey() {
			return key;
		}

		public String getValue() {
			return value;
		}

		BusinessType(String key,String value) {
			this.key = key;
			this.value= value;
		}
	}
}
