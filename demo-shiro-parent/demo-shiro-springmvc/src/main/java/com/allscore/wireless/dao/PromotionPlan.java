package com.allscore.wireless.dao;

import java.math.BigDecimal;
import java.util.Date;

public class PromotionPlan {
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
    private String TYPE;

    /**
     *null
     */
    private String RULE;

    /**
     *null
     */
    private String REWARD;

    /**
     *null
     */
    private String START_TIME;

    /**
     *null
     */
    private String VALID_DATE;

    /**
     *null
     */
    private String STATUS;

    /**
     *null
     */
    private Date CREATE_TIME;

    /**
     *null
     */
    private Date UPDATE_TIME;

    /**
     *null
     */
    private BigDecimal CONSUME_MIN;

    /**
     *null
     */
    private BigDecimal CONSUME_MAX;
	
	private String MERCHANT_NAME;

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
    public String getTYPE() {
        return TYPE;
    }

    /**
     *null
     */
    public void setTYPE(String TYPE) {
        this.TYPE = TYPE == null ? null : TYPE.trim();
    }

    /**
     *null
     */
    public String getRULE() {
        return RULE;
    }

    /**
     *null
     */
    public void setRULE(String RULE) {
        this.RULE = RULE == null ? null : RULE.trim();
    }

    /**
     *null
     */
    public String getREWARD() {
        return REWARD;
    }

    /**
     *null
     */
    public void setREWARD(String REWARD) {
        this.REWARD = REWARD == null ? null : REWARD.trim();
    }

    /**
     *null
     */
    public String getSTART_TIME() {
        return START_TIME;
    }

    /**
     *null
     */
    public void setSTART_TIME(String START_TIME) {
        this.START_TIME = START_TIME == null ? null : START_TIME.trim();
    }

    /**
     *null
     */
    public String getVALID_DATE() {
        return VALID_DATE;
    }

    /**
     *null
     */
    public void setVALID_DATE(String VALID_DATE) {
        this.VALID_DATE = VALID_DATE == null ? null : VALID_DATE.trim();
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

    /**
     *null
     */
    public BigDecimal getCONSUME_MIN() {
        return CONSUME_MIN;
    }

    /**
     *null
     */
    public void setCONSUME_MIN(BigDecimal CONSUME_MIN) {
        this.CONSUME_MIN = CONSUME_MIN;
    }

    /**
     *null
     */
    public BigDecimal getCONSUME_MAX() {
        return CONSUME_MAX;
    }

    /**
     *null
     */
    public void setCONSUME_MAX(BigDecimal CONSUME_MAX) {
        this.CONSUME_MAX = CONSUME_MAX;
    }
	
	public String getMERCHANT_NAME() {
		return MERCHANT_NAME;
	}

	public void setMERCHANT_NAME(String mERCHANTNAME) {
		this.MERCHANT_NAME = mERCHANTNAME == null ? null : mERCHANTNAME.trim();
	}
}