package com.my.wallet.web.constant;

/******************************************
 * 
 * 常量MESSAGE类 用于记录系统返回给终端的消息
 * 
 * @author HeTong
 * 
 ******************************************/

public interface ContantMessage {

	/* 缺少商户号 */
	public static String ERR_MISS_MERCHANTID = "缺少商户号";
	/* 缺少订单号 */
	public static String ERR_INVALID_ORDERID = "缺少订单号";
	/* 无效的商户号 */
	public static String ERR_INVALID_MERCHANTID = "无效的商户号";
	/* 获得卡绑定信息 */
	public static String ERR_BIND_CARD_CODE = "获得绑定卡信息失败";
	/* 验证码已发送 */
	public static String SUCC_SEND_VALID_CODE = "验证码已发送";
	/* 支付成功 */
	public static String SUCC_PAY_MSG = "支付成功";
	/* 支付失败 */
	public static String FAIL_PAY_MSG = "支付失败";
	/* 签名失败 */
	public static String FAIL_SIGN_MSG = "签名失败";
	/* 发短信内容 */
	
	public static String C_SMS_MSG = "动态验证码{0}，该验证码(1分钟内有效)，请勿泄露。";
	public static String  C_SMS_MSG_TAIL= "【商银信】";
	//public static String C_SMS_MSG = "动态验证码{0}，该验证码请不要分享给其他人。【天涯钱包】【商银信】";
	/* 绑卡成功 */
	public static String C_BIND_CARD_SUCCESS = "绑卡成功";
	/* 绑卡失败 */
	public static String C_BIND_CARD_FAIL = "绑卡失败";
	/* 绑卡失败 */
	public static String C_BIND_CARD_ERROR = "网络忙请求金融基础失败!";
	/* 绑卡处理中 */
	public static String C_BIND_CARD_PROCESSING = "绑卡业务正在处理中";
	/* 无效的银行卡号 */
	public static String C_INVALID_CARD_NO = "无效的银行卡号";
	/* 无效的验证码 */
	public static String C_INVALID_VERIFY_CODE = "无效的验证码";
	/* 支付密码设置成功 */
	public static String C_SET_PAY_PWD_SUCCESS = "支付密码设置成功";
	/* 支付密码设置失败 */
	public static String C_SET_PAY_PWD_FAIL = "支付密码设置失败";
	/* 解绑成功 */
	public static String C_RELEASE_BOUND_SUCCESS = "解绑成功";
	/* 解绑失败 */
	public static String C_RELEASE_BOUND_FAIL = "解绑失败";
	/* 绑卡异常消息01 */
	public static String C_CALL_BACK_BIND_CARD_01 = "不能根据条件tran_no:{0}确定唯一记录";
	/* 绑卡异常消息02 */
	public static String C_CALL_BACK_BIND_CARD_02 = "不能根据条件tran_no:{0}取得相应记录";
    /* 充值成功 */
    public static String SUCC_RECHARGE_MSG = "充值成功";
    /* 充值失败 */
    public static String FAIL_RECHARGE_MSG = "充值失败";
    /* 充值正在处理中 */
    public static String PROC_RECHARGE_MSG = "充值业务正在处理中";
    /* 无效的订单号 */
    public static String INVALID_ORDERID = "无效的订单号";
    /* 扫码生成订单失败 */
    public static String C_CREATE_TEMP_ORDER_ERROR = "扫码生成订单失败";
    /* 扫码生成订单成功 */
    public static String C_CREATE_TEMP_ORDER_SUCCESS = "扫码生成订单成功";
    /* 没有找到相关的订单 */
    public static String C_NO_FIND_TEMP_ORDER = "没有找到相关的订单";
    /* 没有找到绑定的银行卡信息 */
    public static String C_NO_FIND_BIND_CARD = "没有找到绑定的银行卡信息";
    /* 扫码消费成功 */
    public static String C_SUCC_PAY_MSG = "扫码消费成功";
    /* 扫码消费失败 */
    public static String C_FAIL_PAY_MSG = "扫码消费失败";
    /* 无效的条码或二维码 */
	public static String C_INVALID_SCAN_CODE = "无效的条码或二维码";
	/* 零钱余额不足 */
	public static String C_NOT_ENOUGH_MONEY = "零钱余额不足";
	/* 密码次数累计提示 */
	public static String C_05_201_1 = "支付密码错误! 已输入错误{0}次!";
	/* 输入错误的支付密码次数，暂停登录时间 */
	public static String C_05_201_2 = "该支付密码正在冻结中，请稍后再试!";
	/* 支付密码不正确 */
	public static String C_05_201_3 = "支付密码不正确,{0}次后将被冻结";
	/* 错误次数达到上限，返回冻结提示 */
	public static String C_05_201_4 = "错误次数已达{0}次，将被冻结{1}分钟";
	/* 支付密码未设置，请去设置支付密码 */
	public static String C_05_201_5 = "支付密码未设置，请去设置支付密码";
	/* 忘记密码时，填写的信息错误 */
	public static String C_06_201_1 = "{0}错误";
	/* 该卡号已绑定，请不要重复绑定 */
	public static String C_06_203_1 = "该卡号已绑定，请不要重复绑定";
	/* 绑卡数量已达上限 */
	public static String C_06_201_2 = "绑卡数量已达上限";
	/* 该卡号绑定业务正在处理中,请等待结果 */
	public static String C_06_202_1 = "该卡号绑定业务正在处理中,请等待结果";
	/* 验证码已过期 */
	public static String C_06_201_3 = "验证码已过期";
	
	
	 /* 提现成功 */
    public static String SUCC_WITHDRAW_MSG = "提现成功!";
    /* 提现失败 */
    public static String FAIL_WITHDRAW_MSG = "提现失败!";
    /*提现处理中*/
    public static String PROC_WITHDRAW_MSG = "提现处理中!";
    
    /*确认付款回调地址不能为空*/
    public static String CONFIRM_PAY_NOTIFY_URL_MSG = "确认付款回调地址不能为空!";
    /*确认付款成功*/
    public static String SUCC_CONFIRM_PAY_MSG = "确认付款成功!";
    /*确认付款失败*/
    public static String FAIL_CONFIRM_PAY_MSG = "确认付款失败!";
    
    
    /*转账成功*/
    public static String SUCC_TRANSFER_BALANCE_MSG = "转账成功!";
    /*转账失败*/
    public static String FAIL_TRANSFER_BALANCE_MSG = "转账失败!";
    
    /*下单成功*/
    public static String SUCC_CREATE_ORDER_MSG = "下单成功!";
    /*下单成功*/
    public static String FAIL_CREATE_ORDER_MSG = "下单失败!";
	
}
