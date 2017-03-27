package com.allscore.wireless.dao;

import java.math.BigDecimal;
import java.util.Date;

public class Coupon {
    /**
     *null
     */
    private String ID;

    /**
     *null
     */
    private String MERCHANT_CODE;

    /**
     *null
     */
    private String USER_ID;

    /**
     *null
     */
    private String UNIONID;

    /**
     *null
     */
    private String COUPON_TYPE;

    /**
     *null
     */
    private BigDecimal AMOUNT;

    /**
     *null
     */
    private String STATUS;

    /**
     *null
     */
    private Date START_DATE;

    /**
     *null
     */
    private Date END_DATE;

    /**
     *null
     */
    private Date CREATE_TIME;

    /**
     *null
     */
    private Date UPDATE_TIME;
    
    /**
     * 会员卡号
     */
    private String MEMBER_CARD;
    /**
     * 商户名称
     */
    private String MERCHANT_NAME;
    
    private int useCopSum;
    
    private int validCopSum;
    
    private int inValidCopSum;

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
    public Date getSTART_DATE() {
        return START_DATE;
    }

    /**
     *null
     */
    public void setSTART_DATE(Date START_DATE) {
        this.START_DATE = START_DATE;
    }

    /**
     *null
     */
    public Date getEND_DATE() {
        return END_DATE;
    }

    /**
     *null
     */
    public void setEND_DATE(Date END_DATE) {
        this.END_DATE = END_DATE;
    }

    /**
     *null
     */
    public Date getCREATE_TIME() {
        return CREATE_TIME;
    }

    /**
     *null
     */
    public void setCREATE_TIME(Date CREATE_TIME) {
        this.CREATE_TIME = CREATE_TIME;
    }

    /**
     *null
     */
    public Date getUPDATE_TIME() {
        return UPDATE_TIME;
    }

    /**
     *null
     */
    public void setUPDATE_TIME(Date UPDATE_TIME) {
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

	public int getUseCopSum() {
		return useCopSum;
	}

	public void setUseCopSum(int useCopSum) {
		this.useCopSum = useCopSum;
	}

	public int getValidCopSum() {
		return validCopSum;
	}

	public void setValidCopSum(int validCopSum) {
		this.validCopSum = validCopSum;
	}

	public int getInValidCopSum() {
		return inValidCopSum;
	}

	public void setInValidCopSum(int inValidCopSum) {
		this.inValidCopSum = inValidCopSum;
	}

	
    
}