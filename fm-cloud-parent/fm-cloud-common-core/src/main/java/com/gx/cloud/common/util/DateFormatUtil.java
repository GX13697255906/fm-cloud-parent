package com.gx.cloud.common.util;

import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DateFormatUtil {
	public static final String SYS_YEAR = "sysYear";
	public static final String SYS_MONTH = "sysMonth";
	public static final String SYS_MONTH_NAME = "sysMonthName";

	public static List yearList;
	public static List monthList;
	public static List uploadYearList;

	/**
	 * 设置年份
	 */
	public static List getSysYear() {
		if (null == yearList) {
			yearList = new ArrayList();
			Date date = new Date();
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			calendar.add(Calendar.YEAR, -1);
			for (int i = 0; i < 3; i++) {
				String year = Integer.toString(calendar.get(1));
				Map map = new java.util.HashMap();
				map.put(SYS_YEAR, year);
				yearList.add(map);
				calendar.add(Calendar.YEAR, 1);
			}
		}
		return yearList;
	}

	/**
	 * 设置月份
	 */
	public static List getSysMonth() {
		if (null == monthList) {
			monthList = new ArrayList();
			for (int i = 1; i < 13; i++) {
				String month;
				if (i < 10) {
					month = "0" + Integer.toString(i);
				} else {
					month = Integer.toString(i);
				}
				Map map = new java.util.HashMap();
				map.put(SYS_MONTH, month);
				map.put(SYS_MONTH_NAME, i + "月");
				monthList.add(map);
			}
		}
		return monthList;
	}

	/**
	 * 取得当天日期
	 * 
	 * @return String (格式'YYYY-MM-DD')
	 */
	public static String getCurrentDate() {
		Date date = new Date();
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		String current = calendar2String(calendar);

		return current;
	}

	/**
	 * 日期转换成字符串
	 */
	public static String calendar2String(Calendar calendar) {
		String string = calendar.get(1)
				+ "-"
				+ (calendar.get(2) + 1 < 10 ? "0" + (calendar.get(2) + 1) : ""
						+ (calendar.get(2) + 1))
				+ "-"
				+ (calendar.get(Calendar.DAY_OF_MONTH) < 10 ? "0"
						+ calendar.get(Calendar.DAY_OF_MONTH) : ""
						+ calendar.get(Calendar.DAY_OF_MONTH));
		return string;
	}

	/**
	 * 日期格式验证:YYYY-MM-DD
	 */
	public static boolean formatDate(String str) {
		Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}");
		boolean matcher = pattern.matcher(str).find();
		return matcher;
	}

	/**
	 * 查询年份是否存在,如不默认为当前年份
	 */
	public static String getCurrentYear(String workYear) {
		if (workYear != null && workYear.trim().length() > 0) {
			return workYear;
		} else {
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(new Date());
			String year = calendar.get(1) + "";
			return year;
		}
	}

	/**
	 * 查询月份是否存在,如不默认为当前月份
	 */
	public static String getCurrentMonth(String workMonth) {
		if (workMonth != null && workMonth.trim().length() > 0) {
			return workMonth;
		} else {
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(new Date());
			if (calendar.get(2) < 9) {
				return "0" + (calendar.get(2) + 1);
			} else {
				return (calendar.get(2) + 1) + "";
			}
		}
	}

	/**
	 * 判断日期格式
	 */
	public static boolean isValidDate2(String sDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/M/d");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-M-d");
		SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy/MM/dd");
		boolean isValid = false;
		try {
			sdf.parse(sDate);
			isValid = true;
		} catch (ParseException e) {
			isValid = false;
		}
		if (!isValid) {
			try {
				sdf2.parse(sDate);
				isValid = true;
			} catch (ParseException e) {
				isValid = false;
			}
		}
		if (!isValid) {
			try {
				sdf3.parse(sDate);
				isValid = true;
			} catch (ParseException e) {
				isValid = false;
			}
		}
		if (!isValid) {
			try {
				sdf4.parse(sDate);
				isValid = true;
			} catch (ParseException e) {
				isValid = false;
			}
		}
		return isValid;
	}

	/**
	 * 判断日期格式
	 */
	public static boolean isValidDate(String sDate, String format, String spe) {
		if (StringUtils.isEmpty(sDate)) {
			return false;
		}
		String datePattern1 = "\\d{4}" + spe + "(\\d{2}|\\d{1})" + spe
				+ "(\\d{2}|\\d{1})";
		Pattern pattern = Pattern.compile(datePattern1);
		Matcher match = pattern.matcher(sDate);
		if (!match.matches()) {

			return false;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		boolean isValid = false;
		try {
			sdf.parse(sDate);
			isValid = true;
		} catch (ParseException e) {
			isValid = false;
		}
		return isValid;
	}

	/**
	 * 判断字符串是否是有效的日期， 格式"yyyy-MM-dd,yyyy-MM-d,yyyy-M-dd,yyyy-M-d
	 * "yyyy/MM/dd,yyyy/MM/d,yyyy/M/dd,yyyy/M/d"
	 * 
	 * @param date
	 *            日期字符串
	 * @return 是则返回true，否则返回false
	 */
	public static boolean isValidDate(String date) {
		if ((date == null) || (date.length() < 8)) {
			return false;
		}
		try {
			boolean result = false;
			SimpleDateFormat formatter;
			char dateSpace = date.charAt(4);
			String format[];
			if ((dateSpace == '-') || (dateSpace == '/')) {
				format = new String[4];
				String strDateSpace = Character.toString(dateSpace);
				format[0] = "yyyy" + strDateSpace + "MM" + strDateSpace + "dd";
				format[1] = "yyyy" + strDateSpace + "MM" + strDateSpace + "d";
				format[2] = "yyyy" + strDateSpace + "M" + strDateSpace + "dd";
				format[3] = "yyyy" + strDateSpace + "M" + strDateSpace + "d";
			} else {
				return false;
			}

			for (int i = 0; i < format.length; i++) {
				String aFormat = format[i];
				formatter = new SimpleDateFormat(aFormat);
				formatter.setLenient(false);
				String tmp = formatter.format(formatter.parse(date));
				if (date.equals(tmp)) {
					result = true;
					break;
				}
			}
			return result;
		} catch (ParseException e) {
			return false;
		}
	}

	/**
	 * 判断字符串是否是有效的日期，格式"yyyy-MM-dd HH:mm:ss"
	 * 
	 * @param date
	 *            日期字符串
	 * @return 是则返回true，否则返回false
	 */
	public static boolean isValidTime(String date) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			formatter.setLenient(false);
			formatter.parse(date);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	/**
	 * 转换字符串为日期，格式"yyyy-MM-dd"
	 * 
	 * @param date
	 *            日期字符串,格式为"yyyy-MM-dd"
	 * @return 返回格式化的日期
	 */
	public static Date strToDate(String date) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		formatter.setLenient(false);
		return formatter.parse(date);
	}

	public static Date strToDate(String date, String patternStr)
			throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat(patternStr);
		formatter.setLenient(false);
		return formatter.parse(date);
	}

	/**
	 * 转换字符串为日期，格式"yyyy-MM-dd"
	 * 
	 * @param date
	 *            日期字符串,格式为"yyyyMMdd"
	 * @return 返回格式化的日期
	 */
	public static Date strToFormateDate(String date) throws ParseException {
		String str = date.substring(0, 4) + "-" + date.substring(4, 6) + "-"
				+ date.substring(6, 8);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		formatter.setLenient(false);
		return formatter.parse(str);
	}

	/**
	 * 转换字符串为日期，格式"yyyy-MM-dd HH:mm:ss"
	 * 
	 * @param date
	 *            日期字符串
	 * @return 返回格式化的日期
	 */
	public static Date strToTime(String date) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		formatter.setLenient(false);
		return formatter.parse(date);
	}

	/**
	 * 转换日期为字符串，格式"yyyy-MM-dd"
	 * 
	 * @param date
	 *            日期
	 * @return 返回格式化的日期字符串
	 */
	public static String dateToStr(Date date) {
		if (date == null)
			return null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(date);
	}

	public static String dateToStr(Date date, String formatStr) {
		if (date == null)
			return null;
		SimpleDateFormat formatter = new SimpleDateFormat(formatStr);
		return formatter.format(date);
	}

	/**
	 * 转换日期为字符串，格式"yyyyMMdd"
	 * 
	 * @param date
	 * @return
	 */

	public static String dateToStrNoBlank(Date date) {
		if (date == null)
			return null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		return formatter.format(date);
	}

	/**
	 * 转换日期为字符串，格式"yyyyMMdd"
	 * 
	 * @param date
	 * @return
	 */

	public static String dateToSimpleStr(Date date) {
		String str = "";
		String simpleStr = "";
		if (date == null)
			return null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		str = formatter.format(date);
		simpleStr = str.substring(2, 4) + str.substring(4, 6)
				+ str.substring(6, 8);
		return simpleStr;
	}

	/**
	 * 转换日期为字符串，格式"yyyy-MM-dd HH:mm:ss"
	 * 
	 * @param date
	 *            日期
	 * @return 返回格式化的日期字符串
	 */
	public static String timeToStr(Date date) {
		if (date == null)
			return null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(date);
	}

	/**
	 * 取得现在的日期，格式"yyyy-MM-dd HH:mm:ss"
	 * 
	 * @return 返回格式化的日期字符串
	 */
	public static String getNow() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date Now = new Date();
		return formatter.format(Now);
	}

	/**
	 * 取得现在的日期，格式"yyyy-MM-dd"
	 * 
	 * @return 返回格式化的日期字符串
	 */
	public static String getDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date Now = new Date();
		return formatter.format(Now);
	}

	/**
	 * 取得现在的时间，格式"HH:mm:ss"
	 * 
	 * @return 返回格式化的时间字符串
	 */
	public static String getTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		Date Now = new Date();
		return formatter.format(Now);
	}

	/**
	 * 取得年份，格式"yyyy"
	 * 
	 * @return 返回当前年份
	 */
	public static int getYear() {
		Date Now = new Date();
		return getYear(Now);
	}

	/**
	 * 取得当年年初日期，格式"yyyy-mm-dd"
	 * 
	 * @return 返回当年年初日期
	 */
	public static String getBeginDate() {
		String date = String.valueOf(getYear()) + "-" + "01" + "-" + "01";
		return date;
	}

	/**
	 * 取得日期的年份，格式"yyyy"
	 * 
	 * @param date
	 *            日期
	 * @return 日期的年份
	 */
	public static int getYear(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		return Integer.parseInt(formatter.format(date));
	}

	/**
	 * 取得月份
	 * 
	 * @return 返回当前月份
	 */
	public static int getMonth() {
		Date Now = new Date();
		return getMonth(Now);
	}

	/**
	 * 取得日期的月份
	 * 
	 * @param date
	 *            日期
	 * @return 日期的月份
	 */
	public static int getMonth(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("M");
		return Integer.parseInt(formatter.format(date));
	}

	/**
	 * 取得今天的日期数
	 * 
	 * @return 返回今天的日期数
	 */
	public static int getDay() {
		Date Now = new Date();
		return getDay(Now);
	}

	/**
	 * 取得日期的天数
	 * 
	 * @param date
	 *            日期
	 * @return 日期的天数
	 */
	public static int getDay(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("d");
		return Integer.parseInt(formatter.format(date));
	}

	/**
	 * 获得与某日期相隔几天的日期
	 * 
	 * @param date
	 *            指定的日期
	 * @param addCount
	 *            相隔的天数
	 * @return 返回的日期
	 */
	public static Date addDay(Date date, int addCount) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(strToDate(dateToStr(date)));
		calendar.add(Calendar.DATE, addCount);
		return calendar.getTime();
	}

	/**
	 * 获得与某日期相隔几月的日期
	 * 
	 * @param date
	 *            指定的日期
	 * @param addCount
	 *            相隔的月数
	 * @return 返回的日期
	 */
	public static Date addMonth(Date date, int addCount) throws ParseException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(strToDate(dateToStr(date)));
		calendar.add(Calendar.MONTH, addCount);
		return calendar.getTime();
	}

	/**
	 * 获得与某日期相隔几月的月份
	 * 
	 * @param date
	 *            指定的日期
	 * @param addCount
	 *            相隔的月数
	 * @return 返回的月份
	 */
	public static String getAddMonth(Date date, int addCount) {
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(strToDate(dateToStr(date)));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		calendar.add(Calendar.MONTH, addCount);
		if (calendar.get(2) < 9) {
			return "0" + (calendar.get(2) + 1);
		} else {
			return (calendar.get(2) + 1) + "";
		}
	}

	/**
	 * 获取年龄
	 * 
	 * @param birthDay
	 *            生日
	 * @return
	 * @throws ParseException
	 */
	public static int getAgeByBirthday(Date birthDay) throws ParseException {
		// Date birthDay=DateFormatUtil.strToDate(birthday);
		Calendar cal = Calendar.getInstance();
		if (cal.before(birthDay)) {
			return 0;
		}
		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH);
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
		cal.setTime(birthDay);

		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH);
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

		int age = yearNow - yearBirth;

		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				if (dayOfMonthNow < dayOfMonthBirth) {
					age--;
				}
			} else {
				age--;
			}
		}
		return age;
	}

	public static String getBirthdayByAge(Integer age) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dt = new Date();
		Calendar rightNow = Calendar.getInstance();
		rightNow.setTime(dt);
		rightNow.add(Calendar.YEAR, -age);
		return sdf.format(rightNow.getTime());
	}
	/**
	 * 两个时间的时间差，如果两个时间差为24小时才计算
	 * @param d1 (yyyy-MM-dd hh:mm:ss)
	 * @param d2 (yyyy-MM-dd hh:mm:ss)
	 * @return x天x小时
	 */
	public static String getTwoDatesInterval(Date d1,Date d2){
		//两个时间的毫秒差
		long diff = d2.getTime()-d1.getTime();
		if(diff <= 48*3600*1000 && diff > 0){
			long diffSeconds = diff / 1000 % 60;
	        long diffMinutes = diff / (60 * 1000) % 60;
	        long diffHours = diff / (60 * 60 * 1000) % 24;
	        long diffDays = diff / (24 * 60 * 60 * 1000);
			return diffDays+"天"+diffHours+"小时";
		}
		return "";
		
	}
	/**
	 * 两个时间的时间差，如果两个时间差为24小时才计算
	 * @param d1 (yyyy-MM-dd hh:mm:ss)
	 * @param d2 (yyyy-MM-dd hh:mm:ss)
	 * @return x天x小时
	 */
	public static String getTwoDatesInterval(Long d1,Date d2){
		//两个时间的毫秒差
		long diff = d2.getTime()-d1;
		//diff <= 48*3600*1000 && 
		if(diff > 0){
			long diffSeconds = diff / 1000 % 60;
	        long diffMinutes = diff / (60 * 1000) % 60;
	        long diffHours = diff / (60 * 60 * 1000) % 24;
	        long diffDays = diff / (24 * 60 * 60 * 1000);
			return diffDays+"天"+diffHours+"小时";
		}
		return "";
		
	}
	
	/**
	* @Description:获取当前月的最后一天
	 */
	public static int getLastDay(){
		Calendar cal = Calendar.getInstance();
		//设置年份
		cal.set(Calendar.YEAR,getYear());
		//设置月份
		cal.set(Calendar.MONTH, getMonth()-1);
		//获取某月最大天数
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		return lastDay;
	}
	
	/**
	 * 取得毫秒级最大日期 9999-12-31 23:59:59:999
	 * @return
	 * @throws ParseException
	 */
	public static Date getMaxDate() throws ParseException{
		return new Date(strToDate("9999-12-31 23:59:59", "yyyy-MM-dd HH:mm:ss").getTime() + 999L);
	}
	
	/**
	 * @Title: strMonthCalculate
	 * @Description: 字符串格式的月份计算 
	 * @param dateStr 格式必须是yyyy-MM格式
	 * @param months 月数
	 * @return 计算后的月份
	 * @author leo
	 * @throws ParseException 
	 */
	public static String strMonthCalculate(String dateStr, int months) throws ParseException{
		return strMonthCalculate(dateStr, months, "yyyy-MM");
	}
	
	/**
	 * @Title: strMonthCalculate
	 * @Description: 字符串格式的月份计算 
	 * @param dateStr 日期
	 * @param months 月数
	 * @param patternStr 日期格式字符串，例如"yyyy-MM"
	 * @return 计算后的月份
	 * @author leo
	 * @throws ParseException 
	 */
	public static String strMonthCalculate(String dateStr, int months, String patternStr) throws ParseException{
		Date date =  DateFormatUtil.strToDate(dateStr, patternStr);
		Integer year = DateFormatUtil.getYear(date);
		Integer month = DateFormatUtil.getMonth(date);
		if(month + months < 1) {
			year = year - 1 + (month + months) / 12;
			month = 12 + (month + months) % 12;
		} else if(month + months > 12){
			year = year + (month + months) / 12;
			month = (month + months) % 12;
		} else {
			month += months;
		}
		String tDate = year + "-" + (month < 10 ? "0" + month : month);
		if(year < 0)
			tDate = "0000-00";
		return tDate;
	}
	
	public static void main(String[] args) {
		try {
//			System.out.println(strToDate("2016-08","yyyy-MM"));
//			System.out.println("1920-09".matches("\\d{4}-\\d{2}"));
			System.out.println(strMonthCalculate("2017-01", -24));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
