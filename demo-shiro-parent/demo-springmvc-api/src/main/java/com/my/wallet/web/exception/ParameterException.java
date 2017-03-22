package com.my.wallet.web.exception;

public class ParameterException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    private String parameterName;

    public ParameterException(String parameterName, String messarge)
    {
        super(messarge);
        this.parameterName = parameterName;
    }

    public String getParameterName()
    {
        return parameterName;
    }

    public void setParameterName(String parameterName)
    {
        this.parameterName = parameterName;
    }

}