package com.allscore.wireless.dao;

import java.math.BigDecimal;
import java.util.Date;

public class Business {
    /**
     *主键ID
     */
    private String ID;

    /**
     *商家编号
     */
    private String MERCHANT_CODE;

    /**
     *用户表主键id
     */
    private String USER_ID;

    /**
     *微信开放平台唯一ID
     */
    private String UNIONID;

    /**
     *优惠券类型(00 :抵用券)
     */
    private String COUPON_TYPE;

    /**
     *优惠券金额
     */
    private BigDecimal AMOUNT;

    /**
     *优惠券状态(00:可使用; 01:已使用 02:已过期)
     */
    private String STATUS;

    /**
     *优惠券开始日期
     */
    private String START_DATE;

    /**
     *优惠券结束日期
     */
    private String END_DATE;
    
    /**
     * 有效期限
     */
    private String validDay;

    /**
     *创建时间
     */
    private String CREATE_TIME;

    /**
     *更新时间
     */
    private String UPDATE_TIME;
    
    /**
     * 会员卡号
     */
    private String MEMBER_CARD;
    /**
     * 商户名称
     */
    private String MERCHANT_NAME;
    
    /**
     * 手机号
     */
    private String MOBILE;

    /**
     *null
     */
    public String getID() {
        return ID;
    }

    /**
     *null
     */
    public void setID(String ID) {
        this.ID = ID == null ? null : ID.trim();
    }

    /**
     *null
     */
    public String getMERCHANT_CODE() {
        return MERCHANT_CODE;
    }

    /**
     *null
     */
    public void setMERCHANT_CODE(String MERCHANT_CODE) {
        this.MERCHANT_CODE = MERCHANT_CODE == null ? null : MERCHANT_CODE.trim();
    }

    /**
     *null
     */
    public String getUSER_ID() {
        return USER_ID;
    }

    /**
     *null
     */
    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID == null ? null : USER_ID.trim();
    }

    /**
     *null
     */
    public String getUNIONID() {
        return UNIONID;
    }

    /**
     *null
     */
    public void setUNIONID(String UNIONID) {
        this.UNIONID = UNIONID == null ? null : UNIONID.trim();
    }

    /**
     *null
     */
    public String getCOUPON_TYPE() {
        return COUPON_TYPE;
    }

    /**
     *null
     */
    public void setCOUPON_TYPE(String COUPON_TYPE) {
        this.COUPON_TYPE = COUPON_TYPE == null ? null : COUPON_TYPE.trim();
    }

    /**
     *null
     */
    public BigDecimal getAMOUNT() {
        return AMOUNT;
    }

    /**
     *null
     */
    public void setAMOUNT(BigDecimal AMOUNT) {
        this.AMOUNT = AMOUNT;
    }

    /**
     *null
     */
    public String getSTATUS() {
        return STATUS;
    }

    /**
     *null
     */
    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS == null ? null : STATUS.trim();
    }

    /**
     *null
     */
    public String getSTART_DATE() {
        return START_DATE;
    }

    /**
     *null
     */
    public void setSTART_DATE(String START_DATE) {
        this.START_DATE = START_DATE;
    }

    /**
     *null
     */
    public String getEND_DATE() {
        return END_DATE;
    }

    /**
     *null
     */
    public void setEND_DATE(String END_DATE) {
        this.END_DATE = END_DATE;
    }

    /**
     *null
     */
    public String getCREATE_TIME() {
        return CREATE_TIME;
    }

    /**
     *null
     */
    public void setCREATE_TIME(String CREATE_TIME) {
        this.CREATE_TIME = CREATE_TIME;
    }

    /**
     *null
     */
    public String getUPDATE_TIME() {
        return UPDATE_TIME;
    }

    /**
     *null
     */
    public void setUPDATE_TIME(String UPDATE_TIME) {
        this.UPDATE_TIME = UPDATE_TIME;
    }

	public String getMEMBER_CARD() {
		return MEMBER_CARD;
	}

	public void setMEMBER_CARD(String mEMBERCARD) {
		MEMBER_CARD = mEMBERCARD;
	}

	public String getMERCHANT_NAME() {
		return MERCHANT_NAME;
	}

	public void setMERCHANT_NAME(String mERCHANTNAME) {
		MERCHANT_NAME = mERCHANTNAME;
	}

	public String getMOBILE() {
		return MOBILE;
	}

	public void setMOBILE(String mOBILE) {
		MOBILE = mOBILE;
	}

	public String getValidDay() {
		return validDay;
	}

	public void setValidDay(String validDay) {
		this.validDay = validDay;
	}

	
    
}