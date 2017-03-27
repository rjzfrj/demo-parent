package com.allscore.wireless.dao;

import java.math.BigDecimal;
import java.util.Date;

public class FundPool {
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
    private BigDecimal CURR_MOENY;

    /**
     *null
     */
    private BigDecimal DAY_MOENY;

    /**
     *null
     */
    private BigDecimal FROZEN_MOENY;

    /**
     *null
     */
    private BigDecimal VALID_MOENY;

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
    public BigDecimal getCURR_MOENY() {
        return CURR_MOENY;
    }

    /**
     *null
     */
    public void setCURR_MOENY(BigDecimal CURR_MOENY) {
        this.CURR_MOENY = CURR_MOENY;
    }

    /**
     *null
     */
    public BigDecimal getDAY_MOENY() {
        return DAY_MOENY;
    }

    /**
     *null
     */
    public void setDAY_MOENY(BigDecimal DAY_MOENY) {
        this.DAY_MOENY = DAY_MOENY;
    }

    /**
     *null
     */
    public BigDecimal getFROZEN_MOENY() {
        return FROZEN_MOENY;
    }

    /**
     *null
     */
    public void setFROZEN_MOENY(BigDecimal FROZEN_MOENY) {
        this.FROZEN_MOENY = FROZEN_MOENY;
    }

    /**
     *null
     */
    public BigDecimal getVALID_MOENY() {
        return VALID_MOENY;
    }

    /**
     *null
     */
    public void setVALID_MOENY(BigDecimal VALID_MOENY) {
        this.VALID_MOENY = VALID_MOENY;
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
}