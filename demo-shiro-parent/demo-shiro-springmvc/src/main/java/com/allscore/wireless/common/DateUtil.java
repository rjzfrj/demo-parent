package com.allscore.wireless.common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


 public class DateUtil
 {
   public static final String DATE_LONG_STD = "yyyy-MM-dd";
   public static final String TIME_LONG_STD = "HH:mm:ss";
   public static final String DATE_LONG = "yyyyMMdd";
   public static final String DATE_TIME_LONG = "yyyyMMddHHmmss";
   private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
 
   public static String getFormatDate() {
     Date date = new Date();
     DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     return df.format(date);
   }
 
   public static synchronized String getCurrentFormatDate(String dateFormat)
   {
     return getSpecifyFormatDate(new Date(), dateFormat);
   }

   public static synchronized String getSpecifyFormatDate(Date specifyDate, String dateFormat)
   {
    simpleDateFormat.applyPattern(dateFormat);
    return simpleDateFormat.format(specifyDate);
   }
   
   public static long getDateSub(String strDate,String endDate) throws Exception{
	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	 java.util.Date beginDate= sdf.parse(strDate);    
	 java.util.Date jsDate= sdf.parse(endDate);    
	 long day=(jsDate.getTime()-beginDate.getTime())/(24*60*60*1000);
	 return day;
   }
   
   public static String getAddDate(String strDate,String days) throws Exception{
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		 java.util.Date beginDate= sdf.parse(strDate);    
		 Calendar rightNow = Calendar.getInstance();
	     rightNow.setTime(beginDate);
	     rightNow.add(Calendar.DAY_OF_YEAR,Integer.valueOf(days));
		 return sdf.format(rightNow.getTime());
  }
 }
