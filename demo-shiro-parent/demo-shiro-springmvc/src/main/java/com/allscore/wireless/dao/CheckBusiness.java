package com.allscore.wireless.dao;

import java.math.BigDecimal;
import java.util.Date;

public class CheckBusiness {
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
    private String BUSINESS_TYPE;

    /**
     *null
     */
    private String BUSINESS_CODE;

    /**
     *null
     */
    private String RESULT_CODE;

    /**
     *null
     */
    private String CARD_NUMBER;

    /**
     *null
     */
    private BigDecimal TRAN_AMT;

    /**
     *null
     */
    private BigDecimal POUNDAGE;

    /**
     *null
     */
    private String SERIAL;

    /**
     *null
     */
    private String BUSINESS_DATE;

    /**
     *null
     */
    private String BUSINESS_TIME;

    /**
     *null
     */
    private String SOURCE_SERIAL;

    /**
     *null
     */
    private String SETTLEMENT_DATE;

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
    private String CHECK_RESULT;

    /**
     *null
     */
    private String REVIEW_RESULT;

    /**
     *null
     */
    private BigDecimal ORDER_AMT;
    
    private BigDecimal sumTranAmt;
    
    private BigDecimal sumPound;
    
    private BigDecimal sumOrderAmt;
    
    private BigDecimal yhAmt;
    
    private String wetAmt;
    
    private String zfbAmt;
    
    private String cardAmt;
    
    private short wetNum;
    
    private short zfbNum;
    
    private short cardNum;
    
    private String MERCHANT_NAME;
    
    private String reason;

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
    public String getBUSINESS_CODE() {
        return BUSINESS_CODE;
    }

    /**
     *null
     */
    public void setBUSINESS_CODE(String BUSINESS_CODE) {
        this.BUSINESS_CODE = BUSINESS_CODE == null ? null : BUSINESS_CODE.trim();
    }

    /**
     *null
     */
    public String getRESULT_CODE() {
        return RESULT_CODE;
    }

    /**
     *null
     */
    public void setRESULT_CODE(String RESULT_CODE) {
        this.RESULT_CODE = RESULT_CODE == null ? null : RESULT_CODE.trim();
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
    public String getBUSINESS_DATE() {
        return BUSINESS_DATE;
    }

    /**
     *null
     */
    public void setBUSINESS_DATE(String BUSINESS_DATE) {
        this.BUSINESS_DATE = BUSINESS_DATE == null ? null : BUSINESS_DATE.trim();
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
        this.BUSINESS_TIME = BUSINESS_TIME == null ? null : BUSINESS_TIME.trim();
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
    public String getSETTLEMENT_DATE() {
        return SETTLEMENT_DATE;
    }

    /**
     *null
     */
    public void setSETTLEMENT_DATE(String SETTLEMENT_DATE) {
        this.SETTLEMENT_DATE = SETTLEMENT_DATE == null ? null : SETTLEMENT_DATE.trim();
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
    public String getCHECK_RESULT() {
        return CHECK_RESULT;
    }

    /**
     *null
     */
    public void setCHECK_RESULT(String CHECK_RESULT) {
        this.CHECK_RESULT = CHECK_RESULT == null ? null : CHECK_RESULT.trim();
    }

    /**
     *null
     */
    public String getREVIEW_RESULT() {
        return REVIEW_RESULT;
    }

    /**
     *null
     */
    public void setREVIEW_RESULT(String REVIEW_RESULT) {
        this.REVIEW_RESULT = REVIEW_RESULT == null ? null : REVIEW_RESULT.trim();
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

	public BigDecimal getSumTranAmt() {
		return sumTranAmt;
	}

	public void setSumTranAmt(BigDecimal sumTranAmt) {
		this.sumTranAmt = sumTranAmt;
	}

	public BigDecimal getSumPound() {
		return sumPound;
	}

	public void setSumPound(BigDecimal sumPound) {
		this.sumPound = sumPound;
	}

	public BigDecimal getSumOrderAmt() {
		return sumOrderAmt;
	}

	public void setSumOrderAmt(BigDecimal sumOrderAmt) {
		this.sumOrderAmt = sumOrderAmt;
	}

	public BigDecimal getYhAmt() {
		return yhAmt;
	}

	public void setYhAmt(BigDecimal yhAmt) {
		this.yhAmt = yhAmt;
	}

	public String getWetAmt() {
		return wetAmt;
	}

	public void setWetAmt(String wetAmt) {
		this.wetAmt = wetAmt;
	}

	public String getZfbAmt() {
		return zfbAmt;
	}

	public void setZfbAmt(String zfbAmt) {
		this.zfbAmt = zfbAmt;
	}

	public String getCardAmt() {
		return cardAmt;
	}

	public void setCardAmt(String cardAmt) {
		this.cardAmt = cardAmt;
	}

	public short getWetNum() {
		return wetNum;
	}

	public void setWetNum(short wetNum) {
		this.wetNum = wetNum;
	}

	public short getZfbNum() {
		return zfbNum;
	}

	public void setZfbNum(short zfbNum) {
		this.zfbNum = zfbNum;
	}

	public short getCardNum() {
		return cardNum;
	}

	public void setCardNum(short cardNum) {
		this.cardNum = cardNum;
	}

	public String getMERCHANT_NAME() {
		return MERCHANT_NAME;
	}

	public void setMERCHANT_NAME(String mERCHANTNAME) {
		this.MERCHANT_NAME = mERCHANTNAME == null ? null : mERCHANTNAME.trim();
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
    
	
    
}