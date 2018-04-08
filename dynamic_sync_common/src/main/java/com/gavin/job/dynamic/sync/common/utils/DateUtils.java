package com.gavin.job.dynamic.sync.common.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @ClassName DateUtils
 * @Description 日期工具类
 */
public class DateUtils {

	/**
	 * yyyy-MM-dd
	 */
	public final static String YYYY_MM_DD_PATTERN = "yyyy-MM-dd";

	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public final static String YYYY_MM_DD_HH_MM_SS_PATTERN = "yyyy-MM-dd HH:mm:ss";

	
	/**
	 */
	public final static String HH_MM_PATTERN = "HH:mm";
	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public final static String YYYY_MM_DD_HH_MM_SS_SSS_PATTERN2 = "yyyy-MM-dd HH:mm:ss:SSS";

	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	public final static String YYYY_MM_DD_HH_MM_PATTERN = "yyyy-MM-dd HH:mm";

	/**
	 * yyyyMMddHHmmsssss
	 */
	public final static String YYYY_MM_DD_HH_MM_SS_SSS_PATTERN = "yyyyMMddHHmmsssss";

	private static String DEFAULT_DATE_PATTERN = YYYY_MM_DD_PATTERN;

	private static final int MONTH_PER_YEAR = 12;

	/**
	 * yyyyMM
	 */
	public final static String YYYY_MM_PATTERN = "yyyyMM";
	
	public final static String YYYY_MM_PATTERN2 = "yyyy-MM";

	/**
	 * yyyy
	 */
	public static final String YYYY_PATTERN = "yyyy";

	/**
	 * 时，分，秒标识
	 */
	private static final int HOUR_PER_DAY = 24;

	private static final int MINUTE_PER_HOUR = 60;

	private static final int SECOND_PER_MINUTE = 60;

	private static final int TILLSECOND_PER_SECOND = 1000;

	/**
	 * 当前时间转换为字符串。<br/>
	 */
	public static String today2String() {
		return date2String(new Date(), DEFAULT_DATE_PATTERN);
	}

	/**
	 * 当天转换为字符串。<br/>
	 * 
	 * @param pattern
	 *            格式类型
	 * @return String
	 */
	public static String today2String(String pattern) {
		return date2String(new Date(), pattern);
	}

	/**
	 * 日期转换为字符串。<br/>
	 * 
	 * @param date
	 *            日期
	 * @return String
	 */
	public static String date2String(Date date) {
		return date2String(date, YYYY_MM_DD_PATTERN);
	}

	/**
	 * 时间转换为字符串。<br/>
	 * 
	 * @param date
	 *            日期
	 * @return String
	 */
	public static String time2String(Date date) {
		return date2String(date, YYYY_MM_DD_HH_MM_SS_PATTERN);
	}

	/**
	 * 日期转换为字符串。<br/>
	 * 
	 * @param date
	 *            日期
	 * @param pattern
	 *            转换格式
	 * @throws String
	 */
	public static String date2String(Date date, String pattern) {
		if (date != null) {
			return (new SimpleDateFormat(pattern)).format(date);
		} else {
			return "";
		}
	}

	/**
	 * 字符串转换为日期。<br/>
	 * 
	 * @param source
	 *            日期字符串
	 * @return Date
	 * @throws ParseException
	 */
	public static Date string2Date(String source) {
		return string2Date(source, DEFAULT_DATE_PATTERN);
	}

	/**
	 * 字符串转换为日期。<br/>
	 * 
	 * @param source
	 *            日期字符串
	 * @param pattern
	 *            转换格式字符串
	 * @return Date
	 * @throws ParseException
	 */
	public static Date string2Date(String source, String pattern) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		dateFormat.setLenient(false);
		Date date = null;
		try {
			date = dateFormat.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 获取指定年份和月份对应的天数
	 * 
	 * @param year
	 *            指定的年份
	 * @param month
	 *            指定的月份
	 * @return int 返回天数
	 */
	public static int getDaysInMonth(int year, int month) {
		if ((month == 1) || (month == 3) || (month == 5) || (month == 7)
				|| (month == 8) || (month == 10) || (month == 12)) {
			return 31;
		} else if ((month == 4) || (month == 6) || (month == 9)
				|| (month == 11)) {
			return 30;
		} else {
			if (((year % 4) == 0) && ((year % 100) != 0) || ((year % 400) == 0)) {
				return 29;
			} else {
				return 28;
			}
		}
	}

	/**
	 * 根据日期获取星期
	 * 
	 * @param strdate
	 * @return
	 */
	public static String getWeekDayByDate(String strdate) {
		final String dayNames[] = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五",
				"星期六" };
		SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Date date = new Date();
		try {
			date = sdfInput.parse(strdate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		calendar.setTime(date);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayOfWeek < 0)
			dayOfWeek = 0;
		return dayNames[dayOfWeek];
	}

	/**
	 * 调整日期。<br/>
	 * 
	 * @param date
	 *            日期
	 * @param amountTime
	 *            调整时间
	 * @return Date
	 */
	public static Date adjust(Date date, long amountTime) {
		return new Date(date.getTime() + amountTime);
	}

	/**
	 * 对日期进行格式化
	 * 
	 * @param date
	 *            日期
	 * @param sf
	 *            日期格式
	 * @return 字符串
	 */
	public static String formatDate(Date date, String sf) {
		if (date == null)
			return "";
		SimpleDateFormat dateformat = new SimpleDateFormat(sf);
		return dateformat.format(date);
	}

	/**
	 * 对日期进行格式化
	 * 
	 * @param date
	 *            日期
	 * @param sf
	 *            日期格式
	 * @return 字符串
	 */
	public static String formatTimestamp(Timestamp date, String sf) {
		if (date == null)
			return "";
		SimpleDateFormat dateformat = new SimpleDateFormat(sf);
		return dateformat.format(date);
	}

	public static String formatTimestamp(Timestamp date) {
		if (date == null)
			return "";
		SimpleDateFormat dateformat = new SimpleDateFormat(
				YYYY_MM_DD_HH_MM_SS_PATTERN);
		return dateformat.format(date);
	}

	/**
	 * 返回当前日期时间字符串<br>
	 * 默认格式:yyyy-mm-dd hh:mm:ss
	 * 
	 * @return String 返回当前字符串型日期时间
	 */
	public static String getCurrentTime() {
		String returnStr = null;
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		returnStr = f.format(date);
		return returnStr;
	}

	/**
	 * 返回指定毫秒数的时间戳<br>
	 */
	public static Timestamp getTimeStamp(long timestamp) {
		return new Timestamp(timestamp);
	}

	/**
	 * 返回自定义格式的当前日期时间字符串
	 * 
	 * @param format
	 *            格式规则
	 * @return String 返回当前字符串型日期时间
	 */
	public static String getCurrentTime(String format) {
		String returnStr = null;
		SimpleDateFormat f = new SimpleDateFormat(format);
		Date date = new Date();
		returnStr = f.format(date);
		return returnStr;
	}
	
	/**
	 * 返回当前字符串型日期
	 * 
	 * @return String 返回的字符串型日期
	 */
	public static String getCurDate() {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = simpledateformat.format(calendar.getTime());
		return strDate;
	}

	/**
	 * @param currentTime
	 *            计算的日期
	 * @param type
	 *            偏移的类别
	 * @param iQuantity
	 *            偏移数量
	 * @return 偏移后的时间
	 */
	public static Date getDateChangeTime(Date currentTime, String type,
			int iQuantity) {
		int year = Integer.parseInt(formatDate(currentTime, "yyyy"));
		int month = Integer.parseInt(formatDate(currentTime, "MM"));
		// 月份修正
		month = month - 1;
		int day = Integer.parseInt(formatDate(currentTime, "dd"));
		int hour = Integer.parseInt(formatDate(currentTime, "HH"));
		int mi = Integer.parseInt(formatDate(currentTime, "mm"));
		int ss = Integer.parseInt(formatDate(currentTime, "ss"));
		GregorianCalendar gc = new GregorianCalendar(year, month, day, hour,
				mi, ss);
		// 月份修正
		// gc.add(GregorianCalendar.MONTH, -1);
		if (type.equalsIgnoreCase("y")) {
			gc.add(GregorianCalendar.YEAR, iQuantity);
		} else if (type.equalsIgnoreCase("m")) {
			gc.add(GregorianCalendar.MONTH, iQuantity);
		} else if (type.equalsIgnoreCase("d")) {
			gc.add(GregorianCalendar.DATE, iQuantity);
		} else if (type.equalsIgnoreCase("h")) {
			gc.add(GregorianCalendar.HOUR, iQuantity);
		} else if (type.equalsIgnoreCase("mi")) {
			gc.add(GregorianCalendar.MINUTE, iQuantity);
		} else if (type.equalsIgnoreCase("s")) {
			gc.add(GregorianCalendar.SECOND, iQuantity);
		}
		return gc.getTime();
	}

	/**
	 * @Title isDateBefore
	 * @Description 判断时间date1是否在时间date2之前 时间格式 2013-1-21
	 * @param date1
	 * @param date2
	 * @return
	 * @return boolean
	 */
	public static boolean isDateBefore(String date1, String date2) {
		try {
			return DateUtils.string2Date(date1).before(
					DateUtils.string2Date(date2));
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 取月末前n天的时间
	 * 
	 * @param date
	 *            日期
	 * @return date
	 */
	public static Date getMonthEnd(Date date, int n) {
		int year = Integer.parseInt(formatDate(date, "yyyy"));
		int month = Integer.parseInt(formatDate(date, "MM"));
		int day = Integer.parseInt(formatDate(date, "dd"));

		GregorianCalendar calendar = new GregorianCalendar(year, month - 1,
				day, 0, 0, 0);
		int monthLength = calendar.getActualMaximum(Calendar.DAY_OF_MONTH) - n;
		String newDateStr = formatDate(date, "yyyy") + "-"
				+ formatDate(date, "MM") + "-";
		if (monthLength < 10)
			newDateStr += "0" + monthLength;
		else
			newDateStr += "" + monthLength;
		return string2Date(newDateStr);
	}

	/**
	 * @Title convertToLastMonth
	 * @Description 日期切换至上个月的倒数i天
	 * @param date
	 * @param i
	 * @return
	 * @return Date
	 */
	public static Date convertToLastMonth(Date date, int i) {
		// 将当前日期转换成本月的第一天，防止本月的某天在上个月中不存在的情况发生
		String datestr = date2String(date, "yyyy-MM") + "-01";
		Date newdate = DateUtils.string2Date(datestr, "yyyy-MM-dd");
		return DateUtils.getMonthEnd(
				DateUtils.getDateChangeTime(newdate, "m", -1), i);
	}

	/**
	 * 字符串转换时间戳
	 * 
	 * @param str
	 * @return
	 */
	public static Timestamp str2Timestamp(String str) {
		Date date = string2Date(str, YYYY_MM_DD_HH_MM_SS_PATTERN);
		return new Timestamp(date.getTime());
	}

	/**
	 * 字符串转换时间戳
	 * 
	 * @param str
	 * @return
	 */
	public static Timestamp str2Timestamp(String str, String pattern) {
		Date date = string2Date(str, pattern);
		return new Timestamp(date.getTime());
	}

	/**
	 * 时间戳转换字符串
	 * 
	 * @param str
	 * @return
	 */
	public static String timestamp2Str(Timestamp timestamp) {
		return timestamp != null ? new SimpleDateFormat(
				YYYY_MM_DD_HH_MM_SS_PATTERN).format(timestamp) : "";
	}


	/**
	 * 时间戳转换字符串
	 * 
	 * @param str
	 * @return
	 */
	public static String timestamp2Str(Timestamp timestamp,String pattern) {
		return timestamp != null ? new SimpleDateFormat(
				pattern).format(timestamp) : "";
	}
	
	/**
	 * 获取当前的时间戳
	 * 
	 * @param
	 * @return
	 * @throws
	 */
	public static Timestamp getCurrentTimestamp(String pattern) {
		String str = getCurrentTime(pattern);
		return Timestamp.valueOf(str);

	}

	/**
	 * 获取当前的时间戳
	 * 
	 * @param
	 * @return
	 * @throws
	 */
	public static Timestamp getCurrentTimestamp() {
		return getCurrentTimestamp(YYYY_MM_DD_HH_MM_SS_PATTERN);
	}

	/**
	 * 获取本月第一天开始的时间戳
	 * 
	 * @param
	 * @return
	 * @throws
	 */
	public static Timestamp getCurrentMonthFirstDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH,
				calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		String firstdayStr = date2String(calendar.getTime(), YYYY_MM_DD_PATTERN)
				+ " 00:00:01";
		return str2Timestamp(firstdayStr);
	}

	/**
	 * 根据所给的起止时间来计算间隔的天数
	 * 
	 * @param startDate
	 *            起始时间
	 * @param endDate
	 *            结束时间
	 * @return int 返回间隔天数
	 */
	public static int getIntervalDays(Date startDate, Date endDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		int checkday = 0;
		// 开始结束相差天数
		try {
			checkday = (int) ((formatter.parse(DateUtils.date2String(endDate))
					.getTime() - formatter.parse(
					DateUtils.date2String(startDate)).getTime()) / (1000 * 24 * 60 * 60));
		} catch (ParseException e) {

		}
		return checkday;
	}

	/**
	 * 根据所给的起止时间来计算间隔的月数
	 * 
	 * @param startDate
	 *            起始时间
	 * @param endDate
	 *            结束时间
	 * @return int 返回间隔月数
	 */
	public static int getIntervalMonths(Date startDate, Date endDate) {
		Calendar end = Calendar.getInstance();
		end.setTime(endDate);

		Calendar start = Calendar.getInstance();
		start.setTime(startDate);

		int month = (end.get(Calendar.YEAR) - start.get(Calendar.YEAR))
				* MONTH_PER_YEAR
				+ (end.get(Calendar.MONTH) + 1 - (start.get(Calendar.MONTH) + 1));

		return month;
	}

	/**
	 * 根据所给的起止时间来计算间隔的年数
	 * 
	 * @param startDate
	 *            起始时间
	 * @param endDate
	 *            结束时间
	 * @return int 返回间隔年数
	 */
	public static int getIntervalYears(Date startDate, Date endDate) {
		return getIntervalMonths(startDate, endDate) / 12;
	}

	/**
	 * 获取当前的日期
	 * 
	 * @param
	 * @return
	 * @throws
	 */
	public static Date today() {
		Calendar calendar = Calendar.getInstance();
		return calendar.getTime();
	}

	/**
	 * java.sql.TimeStamp与java.util.Date之间的转换
	 * 
	 * @param
	 * @return
	 * @throws
	 */
	public static Date timestamp2Date(Timestamp timestamp) {
		String timeStr = new SimpleDateFormat(YYYY_MM_DD_PATTERN)
				.format(timestamp);
		return string2Date(timeStr);
	}

	/**
	 * java.sql.TimeStamp与java.util.Date之间的转换
	 * 
	 * @param
	 * @return
	 * @throws
	 */
	public static Date timestamp2Date(Timestamp timestamp, String pattern) {
		if (GeneralUtils.isNullOrZeroLenght(pattern)) {
			pattern = YYYY_MM_DD_PATTERN;
		}
		String timeStr = new SimpleDateFormat(pattern).format(timestamp);
		return string2Date(timeStr);
	}

	/**
	 * 调整日期。<br/>
	 * 
	 * @param date
	 *            日期
	 * @param amountTime
	 *            调整时间
	 * @return Date
	 */
	public static Timestamp adjust(Timestamp timestamp, long amountTime) {
		long time = new Timestamp(timestamp.getTime() + amountTime).getTime();
		Date date = new Date(time);
		return Timestamp.valueOf(formatDate(date, YYYY_MM_DD_HH_MM_SS_PATTERN));
	}

	/**
	 * 跨年调整时间
	 * 
	 * @param
	 * @return
	 * @throws
	 */
	public static Timestamp adjustByYear(Timestamp timestamp, int yearInterval) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(timestamp2Date(timestamp, YYYY_MM_DD_HH_MM_SS_PATTERN));
		cal.add(Calendar.YEAR, yearInterval);
		return date2TimeStamp(cal.getTime(), YYYY_MM_DD_HH_MM_SS_PATTERN);
	}

	/**
	 * 跨月调整时间
	 * 
	 * @param
	 * @return
	 * @throws
	 */
	public static Timestamp adjustByMonth(Timestamp timestamp, int interval) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(timestamp2Date(timestamp, YYYY_MM_DD_HH_MM_SS_PATTERN));
		cal.add(Calendar.MONTH, interval);
		cal.add(Calendar.DATE, -1);
		return date2TimeStamp(cal.getTime(), YYYY_MM_DD_HH_MM_SS_PATTERN);
	}

	/**
	 * 跨天调整时间
	 * 
	 * @param
	 * @return
	 * @throws
	 */
	public static Timestamp adjustByDay(Timestamp timestamp, int interval) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(timestamp2Date(timestamp, YYYY_MM_DD_HH_MM_SS_PATTERN));
		cal.add(Calendar.DATE, interval);
		return date2TimeStamp(cal.getTime(), YYYY_MM_DD_HH_MM_SS_PATTERN);
	}

	/**
	 * 跨天调整时间
	 * 
	 * @param
	 * @return
	 * @throws
	 */
	public static Date adjustByDay(Date date, int interval) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, interval);
		return cal.getTime();
	}

	/**
	 * 添加月数，最后日期会减去一天 比如：开始日期：2013-09-01， 则添加一个月的结束日期为2013-09-30
	 * 
	 * @param
	 * @return
	 * @throws
	 */
	public static Date addMonth(Date date, int interval) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, interval);
		
		return cal.getTime();
	}

	public static Date addYear(Date date, int interval) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, interval);
		return cal.getTime();
	}

	public static Timestamp date2TimeStamp(Date date, String pattern) {
		if (date == null) {
			return null;
		}
		if (pattern == null) {
			pattern = YYYY_MM_DD_HH_MM_SS_PATTERN;
		}
		return Timestamp.valueOf(date2String(date, pattern));
	}

	/**
	 * 获取上个月初
	 * 
	 * @param
	 * @return
	 * @throws
	 */
	public static Date getLastMonthStart(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, -1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 1);
		return cal.getTime();
	}

	/**
	 * 获取上个月末
	 * 
	 * @param
	 * @return
	 * @throws
	 */
	public static Date getLastMonthEnd(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, -1);
		cal.set(Calendar.DAY_OF_MONTH,
				cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, cal.getMaximum(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE, cal.getMaximum(Calendar.MINUTE));
		cal.set(Calendar.SECOND, cal.getMaximum(Calendar.SECOND));
		return cal.getTime();
	}

	/**
	 * 获取该天的最后时刻
	 * 
	 * @param
	 * @return
	 * @throws
	 */
	public static Date getDayEnd(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, cal.getMaximum(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE, cal.getMaximum(Calendar.MINUTE));
		cal.set(Calendar.SECOND, cal.getMaximum(Calendar.SECOND));
		return cal.getTime();
	}

	/**
	 * 获得本月的第一天
	 * 
	 * @param
	 * @return
	 * @throws
	 */
	public static Date getFirstDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, cal.getMinimum(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE, cal.getMinimum(Calendar.MINUTE));
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}

	
	/**
	 * 获得本年的第一天
	 * 
	 * @param
	 * @return
	 * @throws
	 */
	public static Date getFirstMonthOfYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.MONTH, cal.getMinimum(Calendar.MONTH));
		cal.set(Calendar.DAY_OF_MONTH, cal.getMinimum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, cal.getMinimum(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE, cal.getMinimum(Calendar.MINUTE));
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}
	

	/**
	 * 获得本年的最后一月
	 * 
	 * @param
	 * @return
	 * @throws
	 */
	public static Date getLastMonthOfYear(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.MONTH, cal.getMaximum(Calendar.MONTH));
		cal.set(Calendar.DAY_OF_MONTH, cal.getMaximum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, cal.getMaximum(Calendar.HOUR_OF_DAY));
		cal.set(Calendar.MINUTE, cal.getMaximum(Calendar.MINUTE));
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}
	/**
	 * 获得本月的最后一天
	 * 
	 * @param
	 * @return
	 * @throws
	 */
	public static Date getLastDayOfMonth(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), maxDay, 23,
				59, 59);
		return cal.getTime();
	}

	public static Date getMaxDate(String pattern) {
		return string2Date("2999-12-31", pattern);
	}

	public static Date getMinDate(String pattern) {
		return string2Date("1700-01-01", pattern);
	}

	/**
	 * 获取当前的日期
	 * 
	 * @param
	 * @return
	 * @throws
	 */
	public static Date today(String pattern) {
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		return string2Date(formatDate(date, pattern));
	}

	public static int getMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.MONTH);
	}

	public static boolean isSameMonth(Date date1, Date date2) {
		String _date1 = date2String(date1, YYYY_MM_PATTERN);
		String _date2 = date2String(date2, YYYY_MM_PATTERN);
		return _date1.equals(_date2);
	}

	public static boolean isDateBeforeOrEqual(Date date1, Date date2) {
		return date1.before(date2) || date1.equals(date2);
	}

	/**
	 * 获取当前月的天数
	 * 
	 * @param
	 * @return
	 * @throws
	 */
	public static int getDaysInMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 获取当前日期到月底的剩余天数
	 * 
	 * @param
	 * @return
	 * @throws
	 */
	public static int getLeftDaysInMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return getDaysInMonth(date) - calendar.get(Calendar.DAY_OF_MONTH) + 1;
	}

	public static int getDayOfMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	public static Timestamp createStartTime(Date start) {
		return str2Timestamp(formatDate(start, YYYY_MM_DD_PATTERN)
				+ " 00:00:00");
	}

	public static Timestamp createEndTime(Date end) {
		return str2Timestamp(formatDate(end, YYYY_MM_DD_PATTERN) + " 23:59:59");
	}

	public static int getDaysOfYear() {
		Calendar c = GregorianCalendar.getInstance();
		return c.getActualMaximum(Calendar.DAY_OF_YEAR);
	}

	public static boolean isDateType(String source) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(source);
		} catch (ParseException e) {
			return false;
		}
		return true;
	}

	public static Date getNextMonthStart(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal.getTime();
	}

	/**
	 * 获得当前日期和指定日期之间相差的分钟数
	 * 
	 * @param subtrahendDate
	 *            减数日期
	 * @return
	 * @throws
	 */
	public static int minusMinuteByNow(Timestamp subtrahendDate) {
		Date nowDate = getCurrentTimestamp();

		Long day = (nowDate.getTime() - subtrahendDate.getTime())
				/ (TILLSECOND_PER_SECOND * SECOND_PER_MINUTE);

		return day.intValue();
	}

	/**
	 * 获得当前日期和指定日期之间相差的天数
	 * 
	 * @param subtrahendDate
	 *            减数日期
	 * @return
	 * @throws
	 */
	public static int minusDayByNow(Timestamp subtrahendDate) {
		Timestamp nowDate = getCurrentTimestamp();

		Long day = (nowDate.getTime() - subtrahendDate.getTime())
				/ (HOUR_PER_DAY * MINUTE_PER_HOUR * SECOND_PER_MINUTE * TILLSECOND_PER_SECOND);

		return day.intValue();
	}

	/**
	 * 获得当前日期和指定日期之间相差的天数
	 * 
	 * @param subtrahendDate
	 *            减数日期
	 * @return
	 * @throws
	 */
	public static int minusDay(Timestamp time1, Timestamp time2) {

		Long day = (time1.getTime() - time2.getTime())
				/ (HOUR_PER_DAY * MINUTE_PER_HOUR * SECOND_PER_MINUTE * TILLSECOND_PER_SECOND);

		return day.intValue();
	}

	/**
	 * 获得当前日期和指定日期之间相差的天数
	 * 
	 * @param subtrahendDate
	 *            减数日期
	 * @return
	 * @throws
	 */
	public static int minusHour(Timestamp time1, Timestamp time2) {

		Long hour = (time1.getTime() - time2.getTime())
				/ (MINUTE_PER_HOUR * SECOND_PER_MINUTE * TILLSECOND_PER_SECOND);

		return hour.intValue();
	}

	
	/**
	 * 获得过去某一年的日志
	 * 
	 * @param subtrahendDate
	 *            减数日期
	 * @return
	 * @throws
	 */
	public static String minusByYear(String pattern, int interval) {

		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Calendar c = Calendar.getInstance();

		c.setTime(new Date());
		c.add(Calendar.YEAR, -interval);
		Date y = c.getTime();
		String year = format.format(y);
		return year;

	}

	/**
	 * 获得过去某一天的日志
	 * 
	 * @param subtrahendDate
	 *            减数日期
	 * @return
	 * @throws
	 */
	public static String minusByDay(String pattern, int interval) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Calendar c = Calendar.getInstance();

		c.setTime(new Date());
		c.add(Calendar.DATE, -interval);
		Date d = c.getTime();
		String day = format.format(d);
		return day;
	}
	
	/**
	 * 获得过去某月的日期
	 * 
	 * @param subtrahendDate
	 *            减数日期
	 * @return
	 * @throws
	 */
	public static String minusByMonth(String pattern, int interval) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Calendar c = Calendar.getInstance();

		c.setTime(new Date());
		c.add(Calendar.MONTH, -interval);
		Date d = c.getTime();
		String day = format.format(d);
		return day;
	}
	
	/**
	 * 获得过去某一天的日志
	 * 
	 * @param subtrahendDate
	 *            减数日期
	 * @return
	 * @throws
	 */
	public static String minusByHour(String pattern, int interval) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Calendar c = Calendar.getInstance();

		c.setTime(new Date());
		c.add(Calendar.HOUR, -interval);
		Date d = c.getTime();
		String day = format.format(d);
		return day;
	}
	
	/**
	 * 获得过去某一天的日志
	 * 
	 * @param subtrahendDate
	 *            减数日期
	 * @return
	 * @throws
	 */
	public static String addByHour(Date dateStart, String pattern,int interval) {
		
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		Calendar c = Calendar.getInstance();

		c.setTime(dateStart);
		c.add(Calendar.HOUR, interval);
		Date d = c.getTime();
		String day = format.format(d);
		return day;
	}
	
	/**
	 * 获得过去某一天的日志
	 * 
	 * @param subtrahendDate
	 *            减数日期
	 * @return
	 * @throws
	 */
	public static Date addByMonth(Timestamp date,int interval) {

		Calendar c = Calendar.getInstance();
		
		c.setTime(timestamp2Date(date));
		c.add(Calendar.MONTH, interval);
		Date d = c.getTime();
		
		return d;

	}
	

	/**
	 * 获得过去某一天的日志
	 * 
	 * @param subtrahendDate
	 *            减数日期
	 * @return
	 * @throws
	 */
	public static Date addByDay(Timestamp date,int interval) {

		Calendar c = Calendar.getInstance();
		
		c.setTime(timestamp2Date(date));
		c.add(Calendar.DATE, interval);
		Date d = c.getTime();
		
		return d;

	}


	/**
	 * 获得当前日期和指定日期之间相差的小时数
	 * 
	 * @param subtrahendDate
	 *            减数日期
	 * @return
	 * @throws
	 */
	public static int minusHour(long time1, long time2) {

		Long day = (time1 - time2)
				/ (MINUTE_PER_HOUR * SECOND_PER_MINUTE * TILLSECOND_PER_SECOND);

		return day.intValue();
	}

	/**
	 * 获得当前日期和指定日期之间相差的毫秒数
	 * 
	 * @param subtrahendDate
	 *            减数日期
	 * @return
	 * @throws
	 */
	public static long minusNow(long subtrahendDate) {

		long value = getCurrentTimestamp().getTime() - subtrahendDate;

		return value;
	}

	  /** 
     * 根据日期获得星期 
     * @param date 
     * @return 
     */ 
	public static String getWeekOfDate(Date date) { 
	  String[] weekDaysName = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" }; 
//	  String[] weekDaysCode = { "0", "1", "2", "3", "4", "5", "6" }; 
	  Calendar calendar = Calendar.getInstance(); 
	  calendar.setTime(date); 
	  int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1; 
	  return weekDaysName[intWeek]; 
	} 
	
	public static void main(String[] args) {
		System.out.println(DateUtils
				.getCurrentTime(DateUtils.YYYY_MM_DD_HH_MM_SS_SSS_PATTERN2));
		
		System.out.println(getFirstDayOfMonth(new Date()));
		
		System.out.println(getLastDayOfMonth(new Date()));

		System.out.println(getFirstMonthOfYear(new Date()));
		
		System.out.println(getLastMonthOfYear(new Date()));
		
		
	}

	public static boolean isToday(Date date) {
		String dateStr = DateUtils.date2String(date);
		String today = DateUtils.date2String(new Date());
		if (today.equals(dateStr)) {
			return true;
		}
		return false;
	}
}
