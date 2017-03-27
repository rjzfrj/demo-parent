package com.allscore.wireless.dao;

import java.util.Date;

public class Menu {
    /**
     *null
     */
    private String ID;

    /**
     *null
     */
    private String LOGIN_NAME;

    /**
     *null
     */
    private String ROLE_ID;

    /**
     *null
     */
    private String MENU_NAME;

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
    public String getLOGIN_NAME() {
        return LOGIN_NAME;
    }

    /**
     *null
     */
    public void setLOGIN_NAME(String LOGIN_NAME) {
        this.LOGIN_NAME = LOGIN_NAME == null ? null : LOGIN_NAME.trim();
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
    public String getMENU_NAME() {
        return MENU_NAME;
    }

    /**
     *null
     */
    public void setMENU_NAME(String MENU_NAME) {
        this.MENU_NAME = MENU_NAME == null ? null : MENU_NAME.trim();
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