package com.my.wallet.web.utils;

public class ConstantUtil {
	
	public static String C_MODEL_API = "api";
	public static String C_MODEL_WEB = "web";
	public static int C_MAX_NOTIFY = 7;
	public static String C_CDOE_PROCESSING = "500X";
	
	public static enum RetInfo {
		OPERATION_COMPLETED("0000","操作完成"),
		DATA_NOEXITS("1066","记录不存在"),
		OPERATION_FAIL("9999","操作失败"),
		TIMEOUT_TREATED("500X","正在处理您的支付请求，请稍候查看支付结果"),
		REQUIRED_PARAMETER_MISSING("1001","缺少必选的参数"),
		ILLEGAL_ARGUMENT("1002","非法参数"),
		SERVICE_UNAVAILABLE("1003","服务不可用"),
		SERVICE_BUSY("1004","服务繁忙"),
		PERSIST_ERROR("1005","数据层操作异常"),
		NO_PERMISSION("1006","无此权限"),
		CHANNEL_ABNORMAL("1007","渠道异常"),
		TIMEOUT_UNTREATED("1008","超时未处理"),
		LACK_OF_BALANCE("1009","余额不足"),
		PASSWORD_ERROR("1010","密码错误"),
		ORDER_NOT_EXIST("1011","订单不存在"),
		REPEAT_ORDER_NO("1012","订单号重复"),
		ACCOUNT_NOT_EXIST("1013","账号不存在"),
		ACCOUNT_STATE_ABNORMAL("1014","账号状态异常"),
		ACCOUNT_EXIST("1015","账号已存在"),
		REMOTE_ERROR("1016","远程调用异常"),
		BEYOND_ATTEMPTS("1017","超出尝试次数"),
		UNAUTHORIZED_ACCESS("1018","非法访问"),
		SIGN_AGRMT_NOT_EXIST("1019","原签约信息不存在"),
		CAPI_ACCOUNT_NOT_EXIST("1020","未找到用户资金账户"),
		FTP_ACCESS_FAIL("1021","FTP服务访问失败"),
		PAY_FAIL("8888","支付失败"),
		CARD_ALREADY_UNSIGN("1022","该卡片已解约"),
		CARD_ALREADY_SIGN("1023","该卡片已签约"),
		CARD_NOEXIST_SIGN("1024","不存在该协议编号"),
		ORDER_NOT_MOBILE("1025","创建订单失败,无法获取用户手机号"),
		ORDER_DUPLICATE_OUTID("1026","创建订单失败,外部订单号重复"),
		ORDER_TREAD_ERROR("1027","创建订单失败,交易系统异常"),
		PAY_SIGN_ERROR("1028","验证签名错误"),
		
		CANCEL_FAIL("1110","撤单失败"),
		
		QUERYBANK_FAIL("2001","获取支持银行信息处理异常"),
		TRANS_ERROR("2002","业务交易处理类不存在"),
		TRANS_RESULT_ERROR("2003","交易结果处理异常"),
		TRANS_QUERYRESULT_ERROR("2004","获取交易处理结果处理异常"),
		
		QUERYSIGNEDBANKCARD_FAIL("3001","获取已签约银行卡信息处理异常"),
		
		QUICKPAY_FAIL("4001","支付处理异常"),
		VERIFY_NOEXISTS("4002","验证码已失效"),
		VERIFY_FAIL("4003","验证码不正确"),
		VERIFY_TIMEOUT("4004","验证码已超时失效"),
		MERCHANTID_VAILD("4004","无效的商户号"),
		
		SENDVAILDNUMBER_FAIL("5001","发送验证码处理异常"),
		MOBILE_FAIL("5002","对不起,该手机号不是您预留的手机号"),
		
		EXPORTFILES_FAIL("8001","导出对帐文件失败"),
		EXPORTFILES_SUCC("8002","导出队长文件成功"),
		
		UNKNOWN_ERROR("9999","未知错误");
		private final String value;
		private final String desc;
		public String getValue() {
			return value;
		}
		
		public String getDesc() {
			return desc;
		}

		RetInfo(String value,String desc) {
			this.value = value;
			this.desc= desc;
		}
	}
	
	public static enum OrderStatus{
		ORDER_PROCESSING("00","处理中"),
		ORDER_PAY_SUC("01","支付成功"),
		ORDER_PAY_FAIL("02","支付失败");
		
		private final String value;
		private final String desc;
		public String getValue() {
			return value;
		}
		
		public String getDesc() {
			return desc;
		}

		OrderStatus(String value,String desc) {
			this.value = value;
			this.desc= desc;
		}
	}
	
	public static enum PayStatus{
		PAY_SUC("0001","支付成功"),
		PAY_FAIL("0002","支付失败"),
		INVALID_MERCHANTID("0003","无效的商户号"),
		NO_BIND_CARD("0004","没有找到绑定的银行卡信息"),
        PAY_PROGRESSING("0005","支付操作处理中"),
        CONSUME_ORDER("0006","创建统一订单失败");
		
		private final String value;
		private final String desc;
		public String getValue() {
			return value;
		}
		
		public String getDesc() {
			return desc;
		}

		PayStatus(String value,String desc) {
			this.value = value;
			this.desc= desc;
		}
	}
	
}
