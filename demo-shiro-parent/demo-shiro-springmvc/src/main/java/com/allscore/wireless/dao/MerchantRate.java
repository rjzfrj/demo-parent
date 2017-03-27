package com.allscore.wireless.dao;

import java.math.BigDecimal;
import java.util.Date;

public class MerchantRate {
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
    private String CHN_CODE;

    /**
     *null
     */
    private BigDecimal RATE;

    /**
     *null
     */
    private Date CREATE_DATE;

    /**
     *null
     */
    private Date UPDATE_DATE;

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
    public String getCHN_CODE() {
        return CHN_CODE;
    }

    /**
     *null
     */
    public void setCHN_CODE(String CHN_CODE) {
        this.CHN_CODE = CHN_CODE == null ? null : CHN_CODE.trim();
    }

    /**
     *null
     */
    public BigDecimal getRATE() {
        return RATE;
    }

    /**
     *null
     */
    public void setRATE(BigDecimal RATE) {
        this.RATE = RATE;
    }

    /**
     *null
     */
    public Date getCREATE_DATE() {
        return CREATE_DATE;
    }

    /**
     *null
     */
    public void setCREATE_DATE(Date CREATE_DATE) {
        this.CREATE_DATE = CREATE_DATE;
    }

    /**
     *null
     */
    public Date getUPDATE_DATE() {
        return UPDATE_DATE;
    }

    /**
     *null
     */
    public void setUPDATE_DATE(Date UPDATE_DATE) {
        this.UPDATE_DATE = UPDATE_DATE;
    }
}