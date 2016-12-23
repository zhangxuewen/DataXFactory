package com.hz.dxf.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * 日期格式化实现
 * 
 * @author Blossom
 * @time 2016年8月29日
 */
public class DateFormatUtil {
	
	private DateFormatUtil(){}

	public final static String FORMAT_SHORT = "yyyy-MM-dd";

	public final static String FORMAT_MIDDLE = "yyyy-MM-dd HH:mm";

	public final static String FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";

	public final static String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";

	public final static String FORMAT_SHORT_CN = "yyyy年MM月dd";

	public final static String FORMAT_MONTH_CN = "yyyy年MM月dd日  HH时mm分";

	public final static String FORMAT_LONG_CN = "yyyy年MM月dd日  HH时mm分ss秒";

	public final static String FORMAT_FULL_CN = "yyyy年MM月dd日  HH时mm分ss秒SSS毫秒";

	public final static String FORMAT_YYYY = "yyyy";

	public final static String FORMAT_MM = "MM";

	public final static String FORMAT_DD = "dd";

	/**
	 * 根据预设格式返回当前日期
	 * 
	 * @author Blossom
	 * @time 2016年8月29日
	 * @return_type String
	 * @return
	 */
	public static String getNow() {
		return format(new Date());
	}

	/**
	 * 根据用户格式返回当前日期
	 * 
	 * @author Blossom
	 * @time 2016年8月29日
	 * @return_type String
	 * @param format
	 *            日期格式
	 * @return
	 */
	public static String getNow(String format) {
		return format(new Date(), format);
	}

	/**
	 * 使用预设格式格式化日期
	 * 
	 * @author Blossom
	 * @time 2016年8月29日
	 * @return_type String
	 * @param date
	 *            日期
	 * @return
	 */
	public static String format(Date date) {
		return format(date, FORMAT_LONG);
	}

	/**
	 * 使用用户格式格式化日期
	 * 
	 * @author Blossom
	 * @time 2016年8月29日
	 * @return_type String
	 * @param date
	 *            日期
	 * @param pattern
	 *            日期格式
	 * @return
	 */
	public static String format(Date date, String pattern) {
		if (date != null) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			return simpleDateFormat.format(date);
		}
		return null;
	}

	/**
	 * 使用预设格式提取字符串日期
	 * 
	 * @author Blossom
	 * @time 2016年8月29日
	 * @return_type Date
	 * @param strDate
	 *            日期字符串
	 * @return
	 */
	public static Date parse(String strDate) {
		return parse(strDate, FORMAT_LONG);
	}

	/**
	 * 使用用户格式提取字符串日期
	 * 
	 * @author Blossom
	 * @time 2016年8月29日
	 * @return_type Date
	 * @param strDate
	 *            日期字符串
	 * @param pattern
	 *            日期格式
	 * @return
	 */
	public static Date parse(String strDate, String pattern) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		try {
			return simpleDateFormat.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 在日期上面增加整数年
	 * @author Blossom
	 * @time 2016年8月29日
	 * @return_type Date
	 * @param date
	 * 			日期
	 * @param number
	 * 			增加的年数
	 * @return
	 */
	public static Date addYear(Date date,int number){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, number);
		return calendar.getTime();
	}

	/**
	 * 在日期上面增加整数个月
	 * 
	 * @author Blossom
	 * @time 2016年8月29日
	 * @return_type Date
	 * @param date
	 *            日期
	 * @param number
	 *            增加的月数
	 * @return
	 */
	public static Date addMonth(Date date, int number) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, number);
		return calendar.getTime();
	}

	/**
	 * 在日期上面增加天数
	 * 
	 * @author Blossom
	 * @time 2016年8月29日
	 * @return_type Date
	 * @param date
	 *            日期
	 * @param number
	 *            增加的天数
	 * @return
	 */
	public static Date addDay(Date date, int number) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, number);
		return calendar.getTime();
	}

	/**
	 * 获取时间戳
	 * 
	 * @author Blossom
	 * @time 2016年8月29日
	 * @return_type String
	 * @return
	 */
	public static String getTimeString() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_FULL);
		Calendar calendar = Calendar.getInstance();
		return simpleDateFormat.format(calendar.getTime());
	}

	/**
	 * 获取日期年份
	 * 
	 * @author Blossom
	 * @time 2016年8月29日
	 * @return_type String
	 * @param date
	 *            日期
	 * @return
	 */
	public static String getYear(Date date) {
		/*
		 * SimpleDateFormat simpleDateFormat = new
		 * SimpleDateFormat(FORMAT_YYYY); return simpleDateFormat.format(date);
		 */
		return format(date, FORMAT_YYYY);
	}

	/**
	 * 获取当前年份
	 * 
	 * @author Blossom
	 * @time 2016年8月29日
	 * @return_type String
	 * @return
	 */
	public static String getNowYear() {
		return getYear(new Date());
	}

	/**
	 * 获取月份
	 * 
	 * @author Blossom
	 * @time 2016年8月29日
	 * @return_type String
	 * @param date
	 *            日期
	 * @return
	 */
	public static String getMonth(Date date) {
		/*
		 * SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_MM);
		 * return simpleDateFormat.format(date);
		 */
		return format(date, FORMAT_MM);
	}

	/**
	 * 获取当前月份
	 * 
	 * @author Blossom
	 * @time 2016年8月29日
	 * @return_type String
	 * @return
	 */
	public static String getNowMonth() {
		return getMonth(new Date());
	}

	/**
	 * 获取日期中的日
	 * 
	 * @author Blossom
	 * @time 2016年8月29日
	 * @return_type String
	 * @param date
	 *            日期
	 * @return
	 */
	public static String getDay(Date date) {
		/*
		 * SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_DD);
		 * return simpleDateFormat.format(date);
		 */
		return format(date, FORMAT_DD);
	}

	/**
	 * 获取当前日期当中的日
	 * 
	 * @author Blossom
	 * @time 2016年8月29日
	 * @return_type String
	 * @return
	 */
	public static String getNowDay() {
		return getDay(new Date());
	}

	/**
	 * 按照默认格式的字符串距离今天的天数
	 * 
	 * @author Blossom
	 * @time 2016年8月29日
	 * @return_type int
	 * @param date
	 *            日期字符串
	 * @return
	 */
	public static int countDays(String date) {
		long longCurrTime = Calendar.getInstance().getTime().getTime();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(parse(date));
		long longTime = calendar.getTime().getTime();

		return (int) (longCurrTime - longTime) / 1000 / 3600 / 24;
	}

	/**
	 * 按照指定格式返回距离的天数
	 * 
	 * @author Blossom
	 * @time 2016年8月29日
	 * @return_type int
	 * @param date
	 *            日期字符串
	 * @param format
	 *            日期格式字符串
	 * @return
	 */
	public static int countDays(String date, String format) {
		long longCurrTime = Calendar.getInstance().getTime().getTime();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(parse(date, format));
		long longTime = calendar.getTime().getTime();

		return (int) (longCurrTime - longTime) / 1000 / 3600 / 24;
	}

	/**
	 * 将sql时间对象转换成字符串
	 * 
	 * @author Blossom
	 * @time 2016年8月29日
	 * @return_type String
	 * @param timestamp
	 *            sql时间
	 * @param format
	 *            日期格式
	 * @return
	 */
	public static String timestampToString(Timestamp timestamp, String format) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.format(timestamp);
	}

	/**
	 * 将Date类型转换为Timestamp类型
	 * 
	 * @author Blossom
	 * @time 2016年8月29日
	 * @return_type Timestamp
	 * @param date
	 *            日期
	 * @return
	 */
	public static Timestamp dateToTimestamp(Date date) {
		if (date == null) {
			return null;
		}
		return new Timestamp(date.getTime());
	}

	/**
	 * 获取时间的时间戳
	 * 
	 * @author Blossom
	 * @time 2016年8月29日
	 * @return_type long
	 * @param dateStr
	 *            日期字符串
	 * @param format
	 *            日期格式
	 * @return
	 */
	public static long getTimeStamp(String dateStr, String format) {
		Date date = parse(dateStr, format);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getTimeInMillis();
	}

	/**
	 * 指定时间距离当前时间的中文信息
	 * 
	 * @author Blossom
	 * @time 2016年8月29日
	 * @return_type String
	 * @param date
	 *            日期
	 * @return
	 */
	public static String getLnow(Date date) {
		long longTime = getTimeStamp(date + "", FORMAT_FULL_CN);
		Calendar calendar = Calendar.getInstance();
		long longCurrTime = calendar.getTimeInMillis();
		long longDiffTime = longCurrTime - longTime;
		String message = "";
		if (longDiffTime / 1000 < 60) {
			message = "1分钟以内";
		} else if (longDiffTime / 1000 / 60 < 60) {
			message = longDiffTime / 1000 + "分钟前";
		} else if (longDiffTime / 1000 / 60 / 60 < 24) {
			message = longDiffTime / 1000 / 60 + "小时前";
		} else {
			message = longDiffTime / 1000 / 60 / 60 / 24 + "天前";
		}

		return message;
	}

	/**
	 * 下一周的日期
	 * @author Blossom
	 * @time 2016年8月29日
	 * @return_type Date
	 * @param date
	 * @return
	 */
	public static Date nextWeek(Date date){
		return addDay(date, 7);
	}
	
	/**
	 * 获取本周日的日期
	 * @author Blossom
	 * @time 2016年8月29日
	 * @return_type Date
	 * @param date
	 * @return
	 */
	public static Date getSunday(Date date){
		return addDay(date, 6);
	}
	
	/**
	 * 获取下一个月的日期
	 * @author Blossom
	 * @time 2016年8月29日
	 * @return_type Date
	 * @param date
	 * @return
	 */
	public static Date nextMonth(Date date){
		return addMonth(date, 1);
	}
	
	/**
	 * 获取下一年的信息
	 * @author Blossom
	 * @time 2016年8月29日
	 * @return_type Date
	 * @param date
	 * @return
	 */
	public static Date nextYear(Date date){
		return addYear(date, 1);
	}
	
	/**
	 * 获取日期为该月的第几周
	 * @author Blossom
	 * @time 2016年8月29日
	 * @return_type int
	 * @param date
	 * 			日期
	 * @return
	 */
	public static int getWeekInMonthNum(Date date){
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		return calendar.get(Calendar.WEEK_OF_MONTH);
	}
	
	/**
	 * 获取指定日期的本周第一天
	 * @author Blossom
	 * @time 2016年8月29日
	 * @return_type Date
	 * @param date
	 * 			指定日期
	 * @return
	 */
	public static Date firstDayOfWeek(Date date){
		int intYear = Integer.parseInt(getYear(date));
		int intMonth = Integer.parseInt(getMonth(date));
		int intDay = Integer.parseInt(getDay(date));
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, intYear);
		calendar.set(Calendar.MONTH, intMonth -1);
		calendar.set(Calendar.DAY_OF_MONTH, intDay);
		calendar.getTime();
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		
		return calendar.getTime();
	}
	
	/**
	 * 获取指定日期的本周的最后一天
	 * @author Blossom
	 * @time 2016年8月29日
	 * @return_type Date
	 * @param date
	 * @return
	 */
	public static Date lastDayOfWeek(Date date){
		int intYear = Integer.parseInt(getYear(date));
		int intMonth = Integer.parseInt(getMonth(date));
		int intDay = Integer.parseInt(getDay(date));
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, intYear);
		calendar.set(Calendar.MONTH, intMonth -1);
		calendar.set(Calendar.DAY_OF_MONTH, intDay);
		calendar.getTime();
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		
		return getSunday(calendar.getTime());
	}
	
	/**
	 * 获取本月的第一天
	 * @author Blossom
	 * @time 2016年8月29日
	 * @return_type String
	 * @param date
	 * 			本月日期
	 * @return
	 */
	public static String firstDayOfMonth(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, 1);
		calendar.roll(Calendar.DATE, -1);
		
		//当月的第一天
		calendar.set(GregorianCalendar.DAY_OF_MONTH, 1);
		Date benginTime = calendar.getTime();
		
		return format(benginTime, FORMAT_FULL);
	}
	
	/**
	 * 获取本月的最后一天
	 * @author Blossom
	 * @time 2016年8月29日
	 * @return_type String
	 * @param date
	 * @return
	 */
	public static String lastDayOfMonth(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DATE, 1);
		calendar.set(Calendar.DATE, -1);
		Date endTime = calendar.getTime();
		return format(endTime,FORMAT_FULL);
	}
	
	/**
	 * 获取当前星期数字
	 * @author Blossom
	 * @time 2016年8月29日
	 * @return_type int
	 * @param date
	 * @return
	 */
	public static int getNowWeekNum(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int weekDay = calendar.get(Calendar.DAY_OF_WEEK);
		return weekDay==1?7:weekDay - 1;
	}
	
	/**
	 * 根据星期和时刻获取当前周的相应时间日期
	 * @author Blossom
	 * @time 2016年8月29日
	 * @return_type Date
	 * @param week
	 * @param time
	 * @return
	 */
	public static Date getNowWeekDate(int week,int time){
		int intMondayPlus = geteMondayPlus() +(week - 1);
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, intMondayPlus);
		Date date = currentDate.getTime();
		String strDate = format(date, FORMAT_MIDDLE);
		if (time < 10) {
			strDate += ("0" +time +":00:00");
		}else {
			strDate += (" "+time+":00:00");
		}
		return parse(strDate, FORMAT_FULL);
	}

	/**
	 * 获得当前日期与本周一相差的天数
	 * @author Blossom
	 * @time 2016年8月29日
	 * @return_type int
	 * @return
	 */
	private static int geteMondayPlus() {
		Calendar calendar = Calendar.getInstance();
		int intDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		if (intDayOfWeek == 1) {
			return -6;
		}else{
			return 2-intDayOfWeek;
		}
	}
	
	/**
	 * 计算两个日期相差的天数
	 * @author Blossom
	 * @time 2016年8月29日
	 * @return_type long
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static long differ(Date startDate,Date endDate){
		long longDiff = (endDate.getTime()-startDate.getTime())/86400000;
		return Math.abs(longDiff);
	}
	
	
	
}
