package com.my.wallet.web.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 日期转换工具类
 * 
 * @author hetong
 * @version 1.0, 2007-09-01
 */
public final class DateUtils {
	
	private static Log logger = LogFactory.getLog(DateUtils.class);

	
	private static final TimeZone CHINA_TIMEZONE = TimeZone.getTimeZone("GMT+8");

	private static final SimpleDateFormat DATE_FORMAT_Month = new SimpleDateFormat("yyyy-MM");
	private static final SimpleDateFormat DATE_FORMAT_Day = new SimpleDateFormat("dd");
	
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	private static final SimpleDateFormat DATE_LINK_FORMAT = new SimpleDateFormat("yyyyMMdd");

	private static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final SimpleDateFormat TIME_ONLY_FORMAT = new SimpleDateFormat("HH:mm:ss");

	private static final SimpleDateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	private static final SimpleDateFormat DATETIMEHM_FORMAT = new SimpleDateFormat("yyyyMMddHHmm");
	private static final SimpleDateFormat DATETIMEHMS_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");
	private static final SimpleDateFormat DATETIMEHMSS_FORMAT = new SimpleDateFormat("yyyyMMddHHmmssS");

	private static final SimpleDateFormat CHINADATE_FORMAT = new SimpleDateFormat("yyyy年M月dd日");

	private static final SimpleDateFormat CHINADATE_MONTH_DATE_FORMAT = new SimpleDateFormat("M月dd日");
	private static final SimpleDateFormat CHINADATE_HOUR_MINUTE_FORMAT = new SimpleDateFormat("HH:mm");
	public static final String FORMAT_YEAR_MONTH = "yyyy-MM";// 年月份
	public static final String FORMAT_YEAR_MONTH_DAY = "yyyy-MM-dd";
	public static final String FORMAT_YEAR = "yyyy";// 年月份

	public static final String FORMAT_STARTDATE = "yyyy-MM-dd 00:00:00";// 开始日期
	public static final String FORMAT_ENDDATE = "yyyy-MM-dd 23:59:59";// 结束日期
	public static final String STRING_FORMAT_DATETIME =  "yyyy-MM-dd HH:mm:ss";
	public static final long MILLIS_PER_DAY = 1000*3600*24;
	private static final SimpleDateFormat CHINADATE_YEAR_MONTH_FORMAT = new SimpleDateFormat(FORMAT_YEAR_MONTH);
	private static final SimpleDateFormat CHINADATE_YEAR_MONTH_DAY_FORMAT = new SimpleDateFormat(FORMAT_YEAR_MONTH_DAY);
	private static final SimpleDateFormat CHINADATE_YEAR_FORMAT = new SimpleDateFormat(FORMAT_YEAR);

	private static Map<Integer, String> weekMap = Collections.synchronizedMap(new HashMap<Integer, String>());

	static {
		DATE_FORMAT.setTimeZone(CHINA_TIMEZONE);
		TIME_FORMAT.setTimeZone(CHINA_TIMEZONE);
		DATETIME_FORMAT.setTimeZone(CHINA_TIMEZONE);
		DATETIMEHM_FORMAT.setTimeZone(CHINA_TIMEZONE);
		CHINADATE_FORMAT.setTimeZone(CHINA_TIMEZONE);
		DATE_LINK_FORMAT.setTimeZone(CHINA_TIMEZONE);
		DATETIMEHMS_FORMAT.setTimeZone(CHINA_TIMEZONE);
		DATETIMEHMSS_FORMAT.setTimeZone(CHINA_TIMEZONE);
		CHINADATE_YEAR_MONTH_FORMAT.setTimeZone(CHINA_TIMEZONE);
		CHINADATE_YEAR_FORMAT.setTimeZone(CHINA_TIMEZONE);
	}
	
	static {
		weekMap.put(1, "星期一");
		weekMap.put(2, "星期二");
		weekMap.put(3, "星期三");
		weekMap.put(4, "星期四");
		weekMap.put(5, "星期五");
		weekMap.put(6, "星期六");
		weekMap.put(7, "星期日");
	}

	/**
	 * yyyy-mm-dd hh:mm
	 * 
	 * @param date
	 * @return
	 */
	public static String convertToDateTime(Date date) {
		return DATETIME_FORMAT.format(date);
	}

	
	/*
	 * public static final int TIME_OUT = WebXmlSessionTimeOutReader .SessionTimeOutReader();
	 */

	public static final int TIME_OUT = 30;

	/**
	 * ת�����ڵ���׼yyyy-MM-dd��ʽ.
	 * 
	 * @param date
	 * @return
	 */
	public static String convertToDate(Date date) {
		return DATE_FORMAT.format(date);
	}
	
	public static String convertToMonth(Date date) {
		return DATE_FORMAT_Month.format(date);
	}
	
	public static String convertToDay(Date date) {
		return DATE_FORMAT_Day.format(date);
	}
	
	public static String convertToLinkDate(Date date) {
		return DATE_LINK_FORMAT.format(date);
	}

	public static Date parseLinkDate(String dateStr) throws ParseException {
		return DATE_LINK_FORMAT.parse(dateStr);
	}
	/**
	 * ת�����ڵ���׼yyyy-MM-dd HH:mm:ss��ʽ.
	 * 
	 * @param date
	 * @return
	 */
	public static String convertToTime(Date date) {
		SimpleDateFormat tf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		tf.setTimeZone(CHINA_TIMEZONE);
		return tf.format(date);
	}

	public static String convertToTimeHM(Date date) {
		return DATETIMEHM_FORMAT.format(date);
	}

	public static String convertToTimeHMS(Date date) {
		return DATETIMEHMS_FORMAT.format(date);
	}
	
	public static String convertToTimeHMSS(Date date) {
		return DATETIMEHMSS_FORMAT.format(date);
	}

	public static String convertToOnlyTime(Date date) {
		return TIME_ONLY_FORMAT.format(date);
	}
	/**
	 *30/m timeout
	 * 
	 * @param lastLoginDate
	 * @return
	 * */
	public static boolean compare(Date lastLoginDate) {
		// 1000/s
		Date now = new Date();
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(now);
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(lastLoginDate);
		long tt = cal1.getTimeInMillis() - cal2.getTimeInMillis();
		long uu = tt / (1000 * 60);
		if ((tt / (1000 * 60)) >= TIME_OUT)
			return false;
		else
			return true;
	}

	public static String dayForWeek(Date pTime) {

		String chinaDate = CHINADATE_FORMAT.format(pTime);
		Calendar c = Calendar.getInstance();
		c.setTime(pTime);
		int dayForWeek = 0;
		if (c.get(Calendar.DAY_OF_WEEK) == 1) {
			dayForWeek = 7;
		} else {
			dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		}
		return chinaDate + " " + weekMap.get(dayForWeek);
	}

	/**
	 * 取下个月第一天
	 * 
	 * @return
	 */
	public static Date getNextMonthFirstDay() {
		Calendar c = Calendar.getInstance();
		logger. debug(c.getTime());
		c.set(Calendar.MONTH, c.get(Calendar.MONTH) + 1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		// logger. debug("下个月的第一天: " + c.getTime());
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		// logger. debug("下个月的第一天: " + c.getTime());
		return c.getTime();
	}

	/**
	 * 时间戳字符串转DATE类型
	 * 
	 * @param timestampString
	 * @return
	 */
	public static Date timeStamp2Date(String timestampString) {
		Long timestamp = Long.parseLong(timestampString) * 1000;
		return new java.util.Date(timestamp);
	}

	/**
	 * 比较两个date类型时间大小 前者大于后者返回值大于0 前者小于后者返回值小于0 前者等于后者返回值等于0
	 * 
	 * @param date
	 * @param date1
	 * @return
	 */
	public static int compareTo(Date date, Date date1) {
		String pattern = "yyyy-MM-dd HH:mm:ss";// 精确到分
		// String pattern="yyyy-MM-dd HH:mm:ss.ms";//精确到秒
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		String s = format.format(date);
		String s1 = format.format(date1);
		return s.compareTo(s1);
	}
	

	/**
	 * 年月转Date
	 * 
	 * @param date
	 * @return
	 * @throws ParseException 
	 */
	public static Date parseYearMonth(String dateStr) throws ParseException {
		Date d = CHINADATE_YEAR_MONTH_FORMAT.parse(dateStr);
		return d;
	}
	

	/**
	 * 年月日转Date
	 * 
	 * @param date
	 * @return
	 * @throws ParseException 
	 */
	public static Date parseYearMonthDay(String dateStr) throws ParseException {
		Date d = CHINADATE_YEAR_MONTH_DAY_FORMAT.parse(dateStr);
		return d;
	}
	
	/**
	 * 
	 * 年转Date
	 * @param dateStr
	 * @return
	 * @throws ParseException
	 */
	public static Date parseYear(String dateStr) throws ParseException {
		Date d = CHINADATE_YEAR_FORMAT.parse(dateStr);
		return d;
	}
	

	/**
	 * 下一年转Date
	 * @param statisticsDate
	 * @return
	 */
	public static Date parseNextYear(String dateStr)  throws ParseException {
		Date d = CHINADATE_YEAR_FORMAT.parse(dateStr);
		Calendar cal=Calendar.getInstance();
		cal.setTime(d);
		cal.set(Calendar.YEAR,cal.get(Calendar.YEAR)+1);
		return cal.getTime();
	}

	/**
	 * 格式化开始时间时间
	 * 
	 * @param date
	 * @return
	 */
	public static Date fromatStartDate(Date date) {
		SimpleDateFormat format = new SimpleDateFormat(FORMAT_STARTDATE);
		format.setTimeZone(CHINA_TIMEZONE);
		String s = format.format(date);
		Date d = null;
		try {
			logger. debug(s);
			// d = format.parse(s);
			SimpleDateFormat tf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			tf.setTimeZone(CHINA_TIMEZONE);
			d = tf.parse(s);
		} catch (ParseException e) {
			logger.error(e.getMessage(),e);
		}
		return d;
	}

	/**
	 * 格式化时间，转换成指定格式的时间 "yyyy-MM-dd HH:mm:ss"格式成"yyyy-MM-dd 23:59:59"
	 * 
	 * @param date
	 * @return
	 */
	public static Date fromatEndDate(Date date) {
		SimpleDateFormat format = new SimpleDateFormat(FORMAT_ENDDATE);
		format.setTimeZone(CHINA_TIMEZONE);
		String s = format.format(date);
		Date d = null;
		try {
			SimpleDateFormat tf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			tf.setTimeZone(CHINA_TIMEZONE);
			d = tf.parse(s);
		} catch (ParseException e) {
			logger.error(e.getMessage(),e);
		}
		return d;
	}

	public static Date StringToDate(String dateStr, String formatStr) {
		DateFormat dd = new SimpleDateFormat(formatStr);
		dd.setTimeZone(CHINA_TIMEZONE);
		Date date = null;
		try {
			date = dd.parse(dateStr);
		} catch (ParseException e) {
			logger.error(e.getMessage(),e);
		}
		return date;
	}

	/**
	 * 校验年月日格式是否为：yyyy-MM-dd
	 * 
	 * @param sDate
	 *          年月日字符串
	 * @return true-正确;false-失败
	 */
	public static boolean isValidYYmmddFormat(String sDate) {
		String datePattern1 = "\\d{4}-\\d{2}-\\d{2}";
		String datePattern2 = "^((\\d{2}(([02468][048])|([13579][26]))"
				+ "[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|"
				+ "(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?"
				+ "((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?("
				+ "(((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?"
				+ "((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
		if ((sDate != null)) {
			Pattern pattern = Pattern.compile(datePattern1);
			Matcher match = pattern.matcher(sDate);
			if (match.matches()) {
				pattern = Pattern.compile(datePattern2);
				match = pattern.matcher(sDate);
				return match.matches();
			} else {
				return false;
			}
		}
		return false;
	}

	/**
	 * 校验时分秒格式是否为：HH:mm:ss
	 * 
	 * @param sDate
	 *          时分秒字符串
	 * @return true-正确;false-失败
	 */
	public static boolean isValidHHmmssFormat(String sDate) {
		String regex = "((((0?[0-9])|([1-2][0-9]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))";
		if ((sDate != null)) {
			Pattern pattern = Pattern.compile(regex);
			Matcher match = pattern.matcher(sDate);
			return match.matches();
		}
		return false;
	}

	
	
	
	/**
	 * 检查字符串是否为合法的时间类型 yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static boolean checkIsValid(String dateString){
		boolean flg = false;
		DateFormat dd = new SimpleDateFormat(STRING_FORMAT_DATETIME);
		Date date = null;
		try {
			date = dd.parse(dateString);
			flg = true;
		} catch (ParseException e) {
			logger.error(e.getMessage(),e);
			flg = false;
		}
		return flg;
	}
	
	/**
	 * 判断当前时间是否在有效期
	 * @param startDateStr 开始时间 格式yyyy-MM-dd HH:mm:ss
	 * @param endDateStr 结束时间 格式yyyy-MM-dd HH:mm:ss
	 * @return 1:过期 0：未过期
	 */
	public static String isExceed(String startDateStr, String endDateStr) {
		String isExceed = "1";//1:过期 0：未过期
		DateFormat dd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dd.setTimeZone(CHINA_TIMEZONE);
		Date startDate = null;
		Date endDate = null;
		Date now = new Date();//当前时间
		try {
			startDate = dd.parse(startDateStr + " 00:00:00");
			endDate = dd.parse(endDateStr + " 23:59:59");
			if(compareTo(now,startDate)>0&&compareTo(endDate,now)>0){
				isExceed = "0";
			}
		} catch (ParseException e) {
			logger.error(e.getMessage(),e);
		}
		
		return isExceed;
	}
	/**
	 * 截止时间到当前时间还有几天
	 * @param endDateStr 截止时间 
	 * @return 截止时间和当前系统时间相差的天数 ， 0表示截止时间小于当前时间
	 */
	public static int daysBetweenEnddateAndNow(Date endDate) {
		int result = 0;
		try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
			sdf.setTimeZone(CHINA_TIMEZONE);
			Date sysDate = sdf.parse(sdf.format(new Date(System.currentTimeMillis())));
			endDate=sdf.parse(sdf.format(endDate));
			Calendar cal = Calendar.getInstance();    
	        cal.setTime(endDate);
	        long endTime = cal.getTimeInMillis();
	        cal.setTime(sysDate);
	        long sysTime = cal.getTimeInMillis();
			if(endTime >= sysTime){
	        long between_days=(endTime-sysTime)/MILLIS_PER_DAY;
	        result = Integer.parseInt(String.valueOf(between_days+1));
			}
		} catch (ParseException e) {
			logger.error(e.getMessage(),e);
		}
		return result;
	}
	
	public static String getTimeInterval(Date createTime, Date now) throws ParseException {
	     long millisecondInterval = now.getTime() - createTime.getTime();
	     
	     // 时间间隔小于1小时，返回相差分钟数
	     if (millisecondInterval < (60 * 60 * 1000)) {
	         // 时间间隔小于等于1分钟，返回 刚刚
             if( millisecondInterval < (1 * 60 * 1000)) {
                 return "刚刚";
             }
	    	 return String.valueOf(millisecondInterval / (60 * 1000)) + "分钟前";
	     // 时间间隔大于等于60分钟，小于1天，返回相差小时数	 
	     } else if (millisecondInterval >= (60 * 60 * 1000) && millisecondInterval < (24 * 60 * 60 * 1000)) {
	    	 return String.valueOf(millisecondInterval / (60 * 60 * 1000)) + "小时前";
		 // 时间间隔大于等于1天，小于等于7天，返回相差天数	 
	     } else if (millisecondInterval >= (24 * 60 * 60 * 1000) && millisecondInterval < (24 * 60 * 60 * 1000 * 7)) {
	    	 return String.valueOf(millisecondInterval / (24 * 60 * 60 * 1000)) + "天前";
	     } else {
	    	 return CHINADATE_MONTH_DATE_FORMAT.format(createTime);
	     }
	 }  	
	
    public static Calendar getMillisecond(long time) {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.setTimeZone(CHINA_TIMEZONE);
        calendar.setTimeInMillis(time);
        
        return calendar;
    }
	
    /**
     * 获取当前日期是星期几<br>
     * 
     * @param dt
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);

        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;

        return weekDays[w];
    }
    
	public static String convertToHourMinute(Date date) {
		return CHINADATE_HOUR_MINUTE_FORMAT.format(date);
	}
    
    /**
     * 根据传入的日期及格式化字符串返回对应字符串 date:new Date() partten:"yyyy-MM-dd"等
     * @param date
     * @param partten
     * @return
     */
    public static String convertDateToString(Date date, String partten) {
        SimpleDateFormat sdf = new SimpleDateFormat(partten);
        return sdf.format(date);
    }
    
	public static void main(String[] args) {
		try {
			logger. debug("ok");
			boolean a = checkIsValid("2013-05-15 25:23:20");
			logger. debug(a);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	}
	
    public static Calendar getChinaCalendar(long time) {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.setTimeZone(CHINA_TIMEZONE);
        calendar.setTimeInMillis(time);
        return calendar;
    }
    
    /**
	 * 判断两个日期的分钟差
	 */
    public static long getDiffByMinutes(Date endDate, Date startDate) {
    	long minutes = 0;
		try {
			SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			// 按照传入的格式生成一个SIMPLEDATEFORMATE对象
			Date d1 = TIME_FORMAT.parse(TIME_FORMAT.format(startDate));
			Date d2 = TIME_FORMAT.parse(TIME_FORMAT.format(endDate));
			long between = (d2.getTime() - d1.getTime()) / 1000;// 除以1000是为了转换成秒
			minutes = between % 3600 / 60;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return minutes;
    }
}

