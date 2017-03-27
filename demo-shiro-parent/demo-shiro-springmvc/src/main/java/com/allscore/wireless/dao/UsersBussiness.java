package com.allscore.wireless.dao;

import java.math.BigDecimal;
import java.util.Date;

public class UsersBussiness {
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
    private BigDecimal TRAN_AMT;

    /**
     *null
     */
    private String BUSINESS_TYPE;

    /**
     *null
     */
    private BigDecimal ORDER_AMT;
    
    /**
     *null
     */
    private BigDecimal FACT_AMT;

    /**
     *null
     */
    private BigDecimal POUNDAGE;

    /**
     *null
     */
    private String BUSINESS_SOURCE;

    /**
     *null
     */
    private String SERIAL;

    /**
     *null
     */
    private String BUSINESS_RESULT;

    /**
     *null
     */
    private String POS_SN;

    /**
     *null
     */
    private Long INTEGRAL;

    /**
     *null
     */
    private String SOURCE_SERIAL;

    /**
     *null
     */
    private String STATUS;

    /**
     *null
     */
    private String CARD_NUMBER;

    /**
     *null
     */
    private String BANK_NAME;

    /**
     *null
     */
    private Short SERVICE_SCORE;

    /**
     *null
     */
    private Short ENVIRONMENT_SCORE;

    /**
     *null
     */
    private Short PRICE_SCORE;

    /**
     *null
     */
    private String BUSINESS_TIME;

    /**
     *null
     */
    private Date CREATE_TIME;

    /**
     *null
     */
    private Date UPDATE_TIME;
    
    private String MEMBER_CARD;
    
    private String MERCHANT_NAME;
    
    private BigDecimal YHJE;
    
    private String MOBILE;
    
    private BigDecimal sumTranAmt;
    
    private BigDecimal sumOrderAmt;
    
    private BigDecimal sumPoundage;
    
    private String SIGN_IMAGE;

    public String getMEMBER_CARD() {
		return MEMBER_CARD;
	}

	public void setMEMBER_CARD(String mEMBERCARD) {
		this.MEMBER_CARD = mEMBERCARD == null ? null : mEMBERCARD.trim();
	}

	public String getMERCHANT_NAME() {
		return MERCHANT_NAME;
	}

	public void setMERCHANT_NAME(String mERCHANTNAME) {
		this.MERCHANT_NAME = mERCHANTNAME == null ? null : mERCHANTNAME.trim();
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
    public BigDecimal getTRAN_AMT() {
        return TRAN_AMT;
    }

    /**
     *null
     */
    public void setTRAN_AMT(BigDecimal TRAN_AMT) {
        this.TRAN_AMT = TRAN_AMT;
    }

    /**
     *null
     */
    public String getBUSINESS_TYPE() {
        return BUSINESS_TYPE;
    }

    /**
     *null
     */
    public void setBUSINESS_TYPE(String BUSINESS_TYPE) {
        this.BUSINESS_TYPE = BUSINESS_TYPE == null ? null : BUSINESS_TYPE.trim();
    }

    /**
     *null
     */
    public BigDecimal getORDER_AMT() {
        return ORDER_AMT;
    }

    /**
     *null
     */
    public void setORDER_AMT(BigDecimal ORDER_AMT) {
        this.ORDER_AMT = ORDER_AMT;
    }

    /**
     *null
     */
    public BigDecimal getPOUNDAGE() {
        return POUNDAGE;
    }

    /**
     *null
     */
    public void setPOUNDAGE(BigDecimal POUNDAGE) {
        this.POUNDAGE = POUNDAGE;
    }

    /**
     *null
     */
    public String getBUSINESS_SOURCE() {
        return BUSINESS_SOURCE;
    }

    /**
     *null
     */
    public void setBUSINESS_SOURCE(String BUSINESS_SOURCE) {
        this.BUSINESS_SOURCE = BUSINESS_SOURCE == null ? null : BUSINESS_SOURCE.trim();
    }

    /**
     *null
     */
    public String getSERIAL() {
        return SERIAL;
    }

    /**
     *null
     */
    public void setSERIAL(String SERIAL) {
        this.SERIAL = SERIAL == null ? null : SERIAL.trim();
    }

    /**
     *null
     */
    public String getBUSINESS_RESULT() {
        return BUSINESS_RESULT;
    }

    /**
     *null
     */
    public void setBUSINESS_RESULT(String BUSINESS_RESULT) {
        this.BUSINESS_RESULT = BUSINESS_RESULT == null ? null : BUSINESS_RESULT.trim();
    }

    /**
     *null
     */
    public String getPOS_SN() {
        return POS_SN;
    }

    /**
     *null
     */
    public void setPOS_SN(String POS_SN) {
        this.POS_SN = POS_SN == null ? null : POS_SN.trim();
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
    public String getSOURCE_SERIAL() {
        return SOURCE_SERIAL;
    }

    /**
     *null
     */
    public void setSOURCE_SERIAL(String SOURCE_SERIAL) {
        this.SOURCE_SERIAL = SOURCE_SERIAL == null ? null : SOURCE_SERIAL.trim();
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
    public String getCARD_NUMBER() {
        return CARD_NUMBER;
    }

    /**
     *null
     */
    public void setCARD_NUMBER(String CARD_NUMBER) {
        this.CARD_NUMBER = CARD_NUMBER == null ? null : CARD_NUMBER.trim();
    }

    /**
     *null
     */
    public String getBANK_NAME() {
        return BANK_NAME;
    }

    /**
     *null
     */
    public void setBANK_NAME(String BANK_NAME) {
        this.BANK_NAME = BANK_NAME == null ? null : BANK_NAME.trim();
    }

    /**
     *null
     */
    public Short getSERVICE_SCORE() {
        return SERVICE_SCORE;
    }

    /**
     *null
     */
    public void setSERVICE_SCORE(Short SERVICE_SCORE) {
        this.SERVICE_SCORE = SERVICE_SCORE;
    }

    /**
     *null
     */
    public Short getENVIRONMENT_SCORE() {
        return ENVIRONMENT_SCORE;
    }

    /**
     *null
     */
    public void setENVIRONMENT_SCORE(Short ENVIRONMENT_SCORE) {
        this.ENVIRONMENT_SCORE = ENVIRONMENT_SCORE;
    }

    /**
     *null
     */
    public Short getPRICE_SCORE() {
        return PRICE_SCORE;
    }

    /**
     *null
     */
    public void setPRICE_SCORE(Short PRICE_SCORE) {
        this.PRICE_SCORE = PRICE_SCORE;
    }

    /**
     *null
     */
    public String getBUSINESS_TIME() {
        return BUSINESS_TIME;
    }

    /**
     *null
     */
    public void setBUSINESS_TIME(String BUSINESS_TIME) {
        this.BUSINESS_TIME = BUSINESS_TIME;
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

	public BigDecimal getYHJE() {
		return YHJE;
	}

	public void setYHJE(BigDecimal yHJE) {
		YHJE = yHJE;
	}

	public String getMOBILE() {
		return MOBILE;
	}

	public void setMOBILE(String mOBILE) {
		this.MOBILE = mOBILE == null ? null : mOBILE.trim();
	}

	public BigDecimal getSumTranAmt() {
		return sumTranAmt;
	}

	public void setSumTranAmt(BigDecimal sumTranAmt) {
		this.sumTranAmt = sumTranAmt;
	}

	public BigDecimal getSumOrderAmt() {
		return sumOrderAmt;
	}

	public void setSumOrderAmt(BigDecimal sumOrderAmt) {
		this.sumOrderAmt = sumOrderAmt;
	}

	public BigDecimal getSumPoundage() {
		return sumPoundage;
	}

	public void setSumPoundage(BigDecimal sumPoundage) {
		this.sumPoundage = sumPoundage;
	}

	public BigDecimal getFACT_AMT() {
		return FACT_AMT;
	}

	public void setFACT_AMT(BigDecimal fACTAMT) {
		FACT_AMT = fACTAMT;
	}

	public String getSIGN_IMAGE() {
		return SIGN_IMAGE;
	}

	public void setSIGN_IMAGE(String sIGNIMAGE) {
		SIGN_IMAGE = sIGNIMAGE;
	}
    
	
}