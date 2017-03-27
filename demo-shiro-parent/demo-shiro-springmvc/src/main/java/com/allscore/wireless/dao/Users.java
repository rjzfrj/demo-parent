package com.allscore.wireless.dao;

import java.math.BigDecimal;
import java.util.Date;

public class Users {
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
    private String MEMBER_CARD;

    /**
     *null
     */
    private String MOBILE;

    /**
     *null
     */
    private String TOKEN;

    /**
     *null
     */
    private String OPENID;

    /**
     *null
     */
    private byte[] QR_CODE;

    /**
     *null
     */
    private String UNIONID;

    /**
     *null
     */
    private Long INTEGRAL;

    /**
     *null
     */
    private String STATUS;

    /**
     *null
     */
    private BigDecimal BALANCE;

    /**
     *null
     */
    private int COUPON_NUMBER;

    /**
     *null
     */
    private String CREATE_TIME;

    /**
     *null
     */
    private Date UPDATE_TIME;

    /**
     *null
     */
    private String MERCHANT_NAME;
    
    private BigDecimal sumBal;
    
    private BigDecimal sumInt;
    
    private String cnt;
    
    private int useCopSum;
    
    private int validCopSum;
    
    private int inValidCopSum;
    
    private String PUBLIC_ID;

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
    public String getMEMBER_CARD() {
        return MEMBER_CARD;
    }

    /**
     *null
     */
    public void setMEMBER_CARD(String MEMBER_CARD) {
        this.MEMBER_CARD = MEMBER_CARD == null ? null : MEMBER_CARD.trim();
    }

    /**
     *null
     */
    public String getMOBILE() {
        return MOBILE;
    }

    /**
     *null
     */
    public void setMOBILE(String MOBILE) {
        this.MOBILE = MOBILE == null ? null : MOBILE.trim();
    }

    /**
     *null
     */
    public String getTOKEN() {
        return TOKEN;
    }

    /**
     *null
     */
    public void setTOKEN(String TOKEN) {
        this.TOKEN = TOKEN == null ? null : TOKEN.trim();
    }

    /**
     *null
     */
    public String getOPENID() {
        return OPENID;
    }

    /**
     *null
     */
    public void setOPENID(String OPENID) {
        this.OPENID = OPENID == null ? null : OPENID.trim();
    }

    /**
     *null
     */
    public byte[] getQR_CODE() {
        return QR_CODE;
    }

    /**
     *null
     */
    public void setQR_CODE(byte[] QR_CODE) {
        this.QR_CODE = QR_CODE;
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
    public Long getINTEGRAL() {
        return INTEGRAL;
    }

    /**
     *null
     */
    public void setINTEGRAL(Long INTEGRAL) {
        this.INTEGRAL = INTEGRAL;
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
    public BigDecimal getBALANCE() {
        return BALANCE;
    }

    /**
     *null
     */
    public void setBALANCE(BigDecimal BALANCE) {
        this.BALANCE = BALANCE;
    }

    /**
     *null
     */
    public int getCOUPON_NUMBER() {
        return COUPON_NUMBER;
    }

    /**
     *null
     */
    public void setCOUPON_NUMBER(int COUPON_NUMBER) {
        this.COUPON_NUMBER = COUPON_NUMBER;
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
    public Date getUPDATE_TIME() {
        return UPDATE_TIME;
    }

    /**
     *null
     */
    public void setUPDATE_TIME(Date UPDATE_TIME) {
        this.UPDATE_TIME = UPDATE_TIME;
    }

    /**
     *null
     */
    public String getMERCHANT_NAME() {
        return MERCHANT_NAME;
    }
    
	/**
     *null
     */
    public void setMERCHANT_NAME(String MERCHANT_NAME) {
        this.MERCHANT_NAME = MERCHANT_NAME == null ? null : MERCHANT_NAME.trim();
    }

    public BigDecimal getSumBal() {
		return sumBal;
	}

	public void setSumBal(BigDecimal sumBal) {
		this.sumBal = sumBal;
	}

	public BigDecimal getSumInt() {
		return sumInt;
	}

	public void setSumInt(BigDecimal sumInt) {
		this.sumInt = sumInt;
	}

	public String getCnt() {
		return cnt;
	}

	public void setCnt(String cnt) {
		this.cnt = cnt == null ? null : cnt.trim();
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

	public String getPUBLIC_ID() {
		return PUBLIC_ID;
	}

	public void setPUBLIC_ID(String pUBLICID) {
		this.PUBLIC_ID = pUBLICID;
	}
    
	
}