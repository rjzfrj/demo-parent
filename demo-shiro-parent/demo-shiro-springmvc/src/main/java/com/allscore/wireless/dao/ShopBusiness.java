package com.allscore.wireless.dao;

import java.math.BigDecimal;
import java.util.Date;

public class ShopBusiness {
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
    private BigDecimal POUNDAGE;

    /**
     *null
     */
    private String SERIAL;
    
    /**
     *null
     */
    private String SOURCE_SERIAL;

    /**
     *null
     */
    private String BUSINESS_RESULT;

    /**
     *null
     */
    private String STATUS;

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

    /**
     *null
     */
    private String MERCHANT_NAME;
    

    /**
     *null
     */
    private String BATCH_NUMBER;
    

    /**
     *null
     */
    private String OPERATOR;
    

    /**
     *null
     */
    private BigDecimal AMT;
    

    /**
     *null
     */
    private int NUM;
    

    /**
     *null
     */
    private BigDecimal SUMAMT;
    

    /**
     *null
     */
    private int SUMNUM;
    
    private BigDecimal sulAmt;
    
    private int sulCnt;
    
    private BigDecimal failAmt;
    
    private int failCnt;
    
    private BigDecimal sumTx;
    
    private BigDecimal sumCz;
    
    private BigDecimal sumXf;
    
    private BigDecimal sumTk;
    
    private BigDecimal sumPe;
    
    
    private String CHN_ORDER_NO;  //渠道交易流水号 

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

    /**
     *null
     */
    public String getMERCHANT_NAME() {
        return MERCHANT_NAME;
    }

    /**
     *null
     */
    public void setMERCHANT_NAME(String MERCHANT_NAME) {
        this.MERCHANT_NAME = MERCHANT_NAME == null ? null : MERCHANT_NAME.trim();
    }

	public String getBATCH_NUMBER() {
		return BATCH_NUMBER;
	}

	public void setBATCH_NUMBER(String bATCHID) {
		this.BATCH_NUMBER = bATCHID == null ? null : bATCHID.trim();
	}

	public String getOPERATOR() {
		return OPERATOR;
	}

	public void setOPERATOR(String oPERATOR) {
		this.OPERATOR = oPERATOR == null ? null : oPERATOR.trim();
	}

	public BigDecimal getAMT() {
		return AMT;
	}

	public void setAMT(BigDecimal aMT) {
		AMT = aMT;
	}

	public int getNUM() {
		return NUM;
	}

	public void setNUM(int nUM) {
		NUM = nUM;
	}

	public BigDecimal getSUMAMT() {
		return SUMAMT;
	}

	public void setSUMAMT(BigDecimal sUMAMT) {
		SUMAMT = sUMAMT;
	}

	public int getSUMNUM() {
		return SUMNUM;
	}

	public void setSUMNUM(int sUMNUM) {
		SUMNUM = sUMNUM;
	}

	public BigDecimal getSulAmt() {
		return sulAmt;
	}

	public void setSulAmt(BigDecimal sulAmt) {
		this.sulAmt = sulAmt;
	}

	public int getSulCnt() {
		return sulCnt;
	}

	public void setSulCnt(int sulCnt) {
		this.sulCnt = sulCnt;
	}

	public BigDecimal getFailAmt() {
		return failAmt;
	}

	public void setFailAmt(BigDecimal failAmt) {
		this.failAmt = failAmt;
	}

	public int getFailCnt() {
		return failCnt;
	}

	public void setFailCnt(int failCnt) {
		this.failCnt = failCnt;
	}

	public BigDecimal getSumTx() {
		return sumTx;
	}

	public void setSumTx(BigDecimal sumTx) {
		this.sumTx = sumTx;
	}

	public BigDecimal getSumCz() {
		return sumCz;
	}

	public void setSumCz(BigDecimal sumCz) {
		this.sumCz = sumCz;
	}

	public BigDecimal getSumXf() {
		return sumXf;
	}

	public void setSumXf(BigDecimal sumXf) {
		this.sumXf = sumXf;
	}

	public BigDecimal getSumTk() {
		return sumTk;
	}

	public void setSumTk(BigDecimal sumTk) {
		this.sumTk = sumTk;
	}

	public BigDecimal getSumPe() {
		return sumPe;
	}

	public void setSumPe(BigDecimal sumPe) {
		this.sumPe = sumPe;
	}

	public String getCHN_ORDER_NO() {
		return CHN_ORDER_NO;
	}

	public void setCHN_ORDER_NO(String cHN_ORDER_NO) {
		CHN_ORDER_NO = cHN_ORDER_NO;
	}
	
	
    
	
    
}