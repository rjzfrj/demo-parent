package com.allscore.wireless.response;

public class Rep<T> {

	private String retCode;
	
	private String retMesg;
	
	private T t;
	
	Rep(T t) {
		this.t=t;
	}

	public String getRetCode() {
		return retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	public String getRetMesg() {
		return retMesg;
	}

	public void setRetMesg(String retMesg) {
		this.retMesg = retMesg;
	}

}
