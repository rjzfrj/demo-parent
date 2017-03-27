package com.allscore.wireless.dao;

import java.util.Date;

public class MsgTemplate {
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
    private String PUBLIC_ID;

    /**
     *null
     */
    private String TEMPLATE_ID;

    /**
     *null
     */
    private String TEMPLATE_TPYE;

    /**
     *null
     */
    private String REMARK;

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
    public String getPUBLIC_ID() {
        return PUBLIC_ID;
    }

    /**
     *null
     */
    public void setPUBLIC_ID(String PUBLIC_ID) {
        this.PUBLIC_ID = PUBLIC_ID == null ? null : PUBLIC_ID.trim();
    }

    /**
     *null
     */
    public String getTEMPLATE_ID() {
        return TEMPLATE_ID;
    }

    /**
     *null
     */
    public void setTEMPLATE_ID(String TEMPLATE_ID) {
        this.TEMPLATE_ID = TEMPLATE_ID == null ? null : TEMPLATE_ID.trim();
    }

    /**
     *null
     */
    public String getTEMPLATE_TPYE() {
        return TEMPLATE_TPYE;
    }

    /**
     *null
     */
    public void setTEMPLATE_TPYE(String TEMPLATE_TPYE) {
        this.TEMPLATE_TPYE = TEMPLATE_TPYE == null ? null : TEMPLATE_TPYE.trim();
    }

    /**
     *null
     */
    public String getREMARK() {
        return REMARK;
    }

    /**
     *null
     */
    public void setREMARK(String REMARK) {
        this.REMARK = REMARK == null ? null : REMARK.trim();
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
}