package com.allscore.wireless.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class UserBase {
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
    private String LOGIN_PWD;

    /**
     *null
     */
    private BigDecimal BALANCE;

    /**
     *null
     */
    private String STATUS;

    /**
     *null
     */
    private String CREATE_TIME;

    /**
     *null
     */
    private String UPDATE_TIME;

    /**
     *null
     */
    private String ROLE_NAME;

    /**
     *null
     */
    private String ROLE_DEC;

    /**
     *null
     */
    private String MOBILE;

    /**
     *null
     */
    private String EMAIL;

    /**
     *null
     */
    private String NAME;

    /**
     *null
     */
    private String ROLE_ID;
    
    private String MENU_NAME;
    
    private List<RoleManage> list;
    
    private List<Menu> listMenu;
    
    private String MERCHANT_CODE;
    private String SESSIONID;
	
    public List<Menu> getListMenu() {
		return listMenu;
	}

	public void setListMenu(List<Menu> listMenu) {
		this.listMenu = listMenu;
	}

	public List<RoleManage> getList() {
		return list;
	}

	public void setList(List<RoleManage> list) {
		this.list = list;
	}
    

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
    public String getLOGIN_PWD() {
        return LOGIN_PWD;
    }

    /**
     *null
     */
    public void setLOGIN_PWD(String LOGIN_PWD) {
        this.LOGIN_PWD = LOGIN_PWD == null ? null : LOGIN_PWD.trim();
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
    public String getROLE_DEC() {
        return ROLE_DEC;
    }

    /**
     *null
     */
    public void setROLE_DEC(String ROLE_DEC) {
        this.ROLE_DEC = ROLE_DEC == null ? null : ROLE_DEC.trim();
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
    public String getEMAIL() {
        return EMAIL;
    }

    /**
     *null
     */
    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL == null ? null : EMAIL.trim();
    }

    /**
     *null
     */
    public String getNAME() {
        return NAME;
    }

    /**
     *null
     */
    public void setNAME(String NAME) {
        this.NAME = NAME == null ? null : NAME.trim();
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

	public String getMENU_NAME() {
		return MENU_NAME;
	}

	public void setMENU_NAME(String mENUNAME) {
		this.MENU_NAME = mENUNAME == null ? null : mENUNAME.trim();
	}

	public String getMERCHANT_CODE() {
		return MERCHANT_CODE;
	}

	public void setMERCHANT_CODE(String mERCHANTCODE) {
		MERCHANT_CODE = mERCHANTCODE;
	}
	
	 /**
     *null
     */
    public String getSESSIONID() {
        return SESSIONID;
    }

    /**
     *null
     */
    public void setSESSIONID(String SESSIONID) {
        this.SESSIONID = SESSIONID == null ? null : SESSIONID.trim();
    }
}