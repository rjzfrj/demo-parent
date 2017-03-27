package com.allscore.wireless.dao;

import java.util.Date;

public class TokenInfo {
    /**
     *null
     */
    private String ID;

    /**
     *null
     */
    private String PUBLIC_ID;

    /**
     *null
     */
    private String ACCESS_TOKEN;

    /**
     *null
     */
    private Short EXPIRES_IN;

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
    private String APPID;

    /**
     *null
     */
    private String APPSECRET;

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
    public String getACCESS_TOKEN() {
        return ACCESS_TOKEN;
    }

    /**
     *null
     */
    public void setACCESS_TOKEN(String ACCESS_TOKEN) {
        this.ACCESS_TOKEN = ACCESS_TOKEN == null ? null : ACCESS_TOKEN.trim();
    }

    /**
     *null
     */
    public Short getEXPIRES_IN() {
        return EXPIRES_IN;
    }

    /**
     *null
     */
    public void setEXPIRES_IN(Short EXPIRES_IN) {
        this.EXPIRES_IN = EXPIRES_IN;
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
    public String getAPPID() {
        return APPID;
    }

    /**
     *null
     */
    public void setAPPID(String APPID) {
        this.APPID = APPID == null ? null : APPID.trim();
    }

    /**
     *null
     */
    public String getAPPSECRET() {
        return APPSECRET;
    }

    /**
     *null
     */
    public void setAPPSECRET(String APPSECRET) {
        this.APPSECRET = APPSECRET == null ? null : APPSECRET.trim();
    }
}