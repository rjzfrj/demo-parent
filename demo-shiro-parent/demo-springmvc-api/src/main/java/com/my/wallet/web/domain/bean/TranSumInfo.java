package com.my.wallet.web.domain.bean;


public class TranSumInfo {
    
    private String userNo;
    
    private String merchantId;
    
    private String amtSum;
    
    private String month;
    
    private String content;
    
    private String time;
    
    private String money;
    
    private String status;
    
    private String startDate;
    
    private String endDate;
    
    private String orderId;
    
    private String isSum;

    public String getAmtSum()
    {
        return amtSum;
    }

    public void setAmtSum(String amtSum)
    {
        this.amtSum = amtSum;
    }

    public String getMonth()
    {
        return month;
    }

    public void setMonth(String month)
    {
        this.month = month;
    }

    public String getStartDate()
    {
        return startDate;
    }

    public void setStartDate(String startDate)
    {
        this.startDate = startDate;
    }

    public String getEndDate()
    {
        return endDate;
    }

    public void setEndDate(String endDate)
    {
        this.endDate = endDate;
    }

    public String getUserNo()
    {
        return userNo;
    }

    public void setUserNo(String userNo)
    {
        this.userNo = userNo;
    }

    public String getMerchantId()
    {
        return merchantId;
    }

    public void setMerchantId(String merchantId)
    {
        this.merchantId = merchantId;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getTime()
    {
        return time;
    }

    public void setTime(String time)
    {
        this.time = time;
    }

    public String getMoney()
    {
        return money;
    }

    public void setMoney(String money)
    {
        this.money = money;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getOrderId()
    {
        return orderId;
    }

    public void setOrderId(String orderId)
    {
        this.orderId = orderId;
    }

    public String getIsSum()
    {
        return isSum;
    }

    public void setIsSum(String isSum)
    {
        this.isSum = isSum;
    }
}
