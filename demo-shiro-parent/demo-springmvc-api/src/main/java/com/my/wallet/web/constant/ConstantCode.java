package com.my.wallet.web.constant;

/******************************************
 * 
 * 常量CODE类 用于记录系统中每个CODE编码的含义
 * 
 * @author HeTong
 * 
 ******************************************/

public class ConstantCode {
	
	public static final String SUCCESS = "success";
	public static final String FAIL = "fail";

	/* 通用状态码，所有请求商银信金融基础接口返回的成功状态 */
	public static final String RETURN_CODE_SUCCESS = "0000";
	/* 条码或者二维码开头数字 */
	public static final String SCAN_CODE_NUM_START = "1309";
	/* 是否设置过支付密码 未设置过00 */
	public static final String C_SET_PAY_PWD_STATUS_00 = "00";
	/* 是否设置过支付密码 设置过01 */
	public static final String C_SET_PAY_PWD_STATUS_01 = "01";
	/* 用户状态：00：有效 */
	public static final String C_ACCOUT_STATUS_VALID = "00";
	/* 用户状态：01：无效 */
	public static final String C_ACCOUT_STATUS_NOVALID = "01";
	/* 绑卡状态：00：未绑定过卡 */
	public static final String C_NOT_BIND_CARD = "00";
	/* 绑卡状态：01：已绑定过卡 */
	public static final String C_BIND_CARD = "01";
	/* 证件类型：01：身份证 */
	public static final String C_TYPE_ID_CARD = "01";
	/* 证件类型：02：护照 */
	public static final String C_TYPE_PASSPORT = "02";
	/* 证件类型：99：其他 */
	public static final String C_TYPE_OTHER = "99";
	/* 银行卡绑定处理状态：01：已申请 */
	public static final String C_BIND_CARD_TRANSTATUS_01 = "01";
	/* 银行卡绑定处理状态：02：成功 */
	public static final String C_BIND_CARD_TRANSTATUS_02 = "02";
	/* 银行卡绑定处理状态：03：失败 */
	public static final String C_BIND_CARD_TRANSTATUS_03 = "03";
	/* 无效验证码 */
	public static final String C_INVALID_VERIFY_CODE = "90";
    /* 交易类型：00： 消费*/
    public static final String PAY_ORDER_TRANTYPE_00 = "00";
    public static final String PAY_ORDER_TRANTYPE_00_DESC = "消费";
    /* 交易类型：01：充值 */
    public static final String PAY_ORDER_TRANTYPE_01 = "01";
    public static final String PAY_ORDER_TRANTYPE_01_DESC = "充值";
    /*提现 */
    public static final String PAY_ORDER_TRANTYPE_02 = "02";
    public static final String PAY_ORDER_TRANTYPE_02_DESC = "提现";
    /* 转出*/
    public static final String PAY_ORDER_TRANTYPE_03 = "03";
    public static final String PAY_ORDER_TRANTYPE_03_DESC = "转出";
    /*转入*/
    public static final String PAY_ORDER_TRANTYPE_04 = "04";
    public static final String PAY_ORDER_TRANTYPE_04_DESC = "转入";
    
  

    /* 分页默认一页数量 */
    public static final int    DEFAULT_PAGE_SIZE = 10;
    /* 卡属性: 01 借记卡 */
    public static final String C_CARD_ATTR_CODE_01 = "01";
    /* 卡属性: 02 贷记卡 */
    public static final String C_CARD_ATTR_CODE_02 = "02";
    /* 交易来源: 00: 零钱 01:刷卡*/
    public static final String PAY_ORDER_TRANSOURCE_00 = "00";
    public static final String PAY_ORDER_TRANSOURCE_00_DESC = "零钱";
    public static final String PAY_ORDER_TRANSOURCE_01 = "01";
    public static final String PAY_ORDER_TRANSOURCE_01_DESC = "银行卡";
    /* 调用订单系统source赋值LYF_APP */
    public static final String CENTER_ORDER_SOURCE = "05";
    /* 回调接口返回的错误状态 */
    public static final String CALLBACK_RET_CD_ERR = "9999";
    public static final String ERROR = "error";
    // 金融基础返回消息通知中绑卡类型
    public static final String BANK_BIND_TRAN_TYPE = "0701";
    // 订单系统中的订单状态 01消费 03充值
    public static final String C_ORDER_TYPE = "01";
    // 订单系统中的订单子状态
    public static final String C_ORDER_SUB_TYPE = "0101";
    
}
