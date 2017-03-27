package com.allscore.wireless.dao;

import java.util.Date;

public class RoleManage {
    /**
     *null
     */
    private String ID;

    /**
     *null
     */
    private String ROLE_NAME;

    /**
     *null
     */
    private String ROLE_ID;

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
    public String getROLE_NAME() {
        return ROLE_NAME;
    }

    /**
     *null
     */
    public void setROLE_NAME(String ROLE_NAME) {
        this.ROLE_NAME = ROLE_NAME == null ? null : ROLE_NAME.trim();
    }

    /**
     *null
     */
    public String getROLE_ID() {
        return ROLE_ID;
    }

    /**
     *null
     */
    public void setROLE_ID(String ROLE_ID) {
        this.ROLE_ID = ROLE_ID == null ? null : ROLE_ID.trim();
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