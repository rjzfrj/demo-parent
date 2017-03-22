package com.my.wallet.web.exception;

public class TransException extends RuntimeException {

	private static final long serialVersionUID = 7863438540808666379L;
	private String errorCode;
	private String errorMsg;
	
	
	public String getErrorCode()
    {
        return errorCode;
    }
    public void setErrorCode(String errorCode)
    {
        this.errorCode = errorCode;
    }
    public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public TransException(String errorCode, String errorMsg) {
		super();
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
	
	@Override
	public String toString() {
		return "TransException [errorCode=" + errorCode + ", errorMsg="
				+ errorMsg + "]";
	}
	@Override
	public synchronized Throwable fillInStackTrace()
	{
		
		return null;
	}

}
