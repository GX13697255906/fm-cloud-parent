package com.gx.cloud.common.util;

import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public class DateUtils {
	public static final String PATTERN_SHORT_CHINESE = "yyyy年MM月dd日";
	public static final String PATTERN_SIMPLE_DATE = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
	public static final String PATTERN_SIMPLE_MONTH = "[0-9]{4}-[0-9]{2}";
	
	
	public static boolean isSimpleDate(String date){
		return Pattern.compile(PATTERN_SIMPLE_DATE).matcher(date).matches();
	}
	
	public static boolean isSimpleMonth(String month){
		return Pattern.compile(PATTERN_SIMPLE_MONTH).matcher(month).matches();
	}
	
	public static Date formatDate(String av_string) {
		SimpleDateFormat lv_sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return lv_sdf.parse(av_string);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String dateToString(Date av_date) {
		SimpleDateFormat lv_sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return lv_sdf.format(av_date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static String dateToString(Date av_date,String formate) {
		SimpleDateFormat lv_sdf = new SimpleDateFormat(formate);
		try {
			return lv_sdf.format(av_date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String dayOfWeek() {
		final String dayNames[] = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五",
				"星期六" };
		Calendar calendar = Calendar.getInstance();
		Date date = new Date();
		calendar.setTime(date);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayOfWeek < 0)
			dayOfWeek = 0;
		return dayNames[dayOfWeek];
	}

	public static String formatLongDate(Date av_date) {
		SimpleDateFormat lv_sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return lv_sdf.format(av_date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String formatYearMonth(Date av_date) {
		SimpleDateFormat lv_sdf = new SimpleDateFormat("yyyy-MM");
		try {
			return lv_sdf.format(av_date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date formatDate(String av_string, String format) {
		if (null == av_string || null == format) {
			return null;
		}
		SimpleDateFormat lv_sdf = new SimpleDateFormat(format);
		try {
			return lv_sdf.parse(av_string);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 时间格式
	 * 
	 * @param date
	 *            格式化前的时间
	 * @param format
	 *            需要转换的格式
	 * @return 格式化后的时间
	 */
	public static String format(Date date, String format) {
		if (date == null) {
			return null;
		}

		return new SimpleDateFormat(format).format(date);
	}

	
	/**
	 * 时间格式
	 * 
	 * @param dateStr
	 *            格式化前的时间
	 * @param format
	 *            需要转换的格式
	 * @return 格式化后的时间
	 * @throws ParseException
	 */
	public static Date formatToDate(String dateStr, String format)
			throws ParseException {
		if (dateStr == null) {
			return null;
		}
		DateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.parse(dateStr);
	}
	
	public static Date DateAddDay(Date av_date, int av_days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(av_date);
		calendar.add(Calendar.DATE, av_days);
		return calendar.getTime();
	}

	/**
	 * add by gonghaigang 获得明天的起始时刻
	 * 
	 * @param date
	 * @return
	 */
	public static Date getTheNextDate(Date date) {
		if (date == null)
			return null;
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date);
		Calendar c2 = Calendar.getInstance();
		c2.set(c1.get(Calendar.YEAR), c1.get(Calendar.MONTH), c1
				.get(Calendar.DATE), 0, 0, 0);
		long millis = c2.getTimeInMillis();
		millis = millis - millis % 1000 + 86400000;
		c2.setTimeInMillis(millis);
		return c2.getTime();
	}

	/**
	 * 
	 * 获得今天的结束时刻时刻为23:59:59
	 * 
	 * @author liufosheng
	 * @param date
	 * @return
	 */
	public static Date getTheTodayEndDate(Date date) {
		if (date == null)
			return null;
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date);
		c1.set(Calendar.HOUR_OF_DAY, 23);
		c1.set(Calendar.MINUTE, 59);
		c1.set(Calendar.SECOND, 59);
		return c1.getTime();
	}

	/**
	 * 
	 * 获得今天的结束时刻时刻为23:59:59
	 * 
	 * @author liufosheng
	 * @param date
	 * @return
	 */
	public static Date getTheTodayStartDate(Date date) {
		if (date == null)
			return null;
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date);
		c1.set(Calendar.HOUR_OF_DAY, 0);
		c1.set(Calendar.MINUTE, 0);
		c1.set(Calendar.SECOND, 0);
		return c1.getTime();
	}

	/**
	 * @param d
	 * @return
	 */
	public static Date skipDateTime(Date d, int skipDay) {
		if (d == null)
			return null;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		calendar.add(Calendar.DATE, skipDay);
		return calendar.getTime();
	}

	/**
     */
	public static String skipDateTime(String timeStr, int skipDay) {
		String pattern = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat lv_sdf = new SimpleDateFormat(pattern);
		try {
			Date d = lv_sdf.parse(timeStr);
			Date date = skipDateTime(d, skipDay);
			return lv_sdf.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
     */
	public static String skipDate(String dateStr, int skipDay) {
		String pattern = "yyyy-MM-dd";
		SimpleDateFormat lv_sdf = new SimpleDateFormat(pattern);
		try {
			Date d = lv_sdf.parse(dateStr);
			Date date = skipDateTime(d, skipDay);
			return lv_sdf.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static boolean betweenTime(Date av_d, String av_startTime,
			String av_endTime) {
		SimpleDateFormat lv_sdf = new SimpleDateFormat("yyyy-MM-dd");
		if (null == av_d)
			return false;
		try {
			String lv_today = lv_sdf.format(new Date());

			Date lv_start = formatDate(lv_today + " " + av_startTime);
			Date lv_end = formatDate(lv_today + " " + av_endTime);
			if (av_d.after(lv_start) && av_d.before(lv_end))
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static String patternDate(Date date, String pattern) {
		SimpleDateFormat lv_sdf = new SimpleDateFormat(pattern);
		return lv_sdf.format(date);
	}

	/**
	 * 根据当前月份得到前12个月份
	 * @return
	 */
	public static List<String> getBefore12Month() {
		List<String> allMonth = new ArrayList<String>();
		for (int i = 0; i < 12; i++) {
			Calendar ca = Calendar.getInstance();
			ca.setTime(new Date());
			ca.add(Calendar.MONTH, -i); // 月份减1
			Date lastMonth = ca.getTime();
			SimpleDateFormat sf = new SimpleDateFormat("yyyyMM");
			allMonth.add(sf.format(lastMonth));
		}
		return allMonth;
	}

	/**
	 * 获取两个日期相差的天数
	 * 
	 * @title getBetweenDay
	 * @author xiehongdong
	 * @description
	 * @param startDate
	 * @param endDate
	 * @return
	 * @return long
	 */
	public static long getBetweenDay(Date startDate, Date endDate) {
		if (startDate == null || endDate == null)
			return 0;
		Calendar start = Calendar.getInstance();
		start.setTime(startDate);
		start.set(Calendar.SECOND, 0);
		start.set(Calendar.MILLISECOND, 0);
		Calendar end = Calendar.getInstance();
		end.setTime(endDate);
		end.set(Calendar.SECOND, 0);
		end.set(Calendar.MILLISECOND, 0);
		long l = Math.abs(end.getTimeInMillis() - start.getTimeInMillis());
		long dateMillins = 86400000l;// 24 * 60 * 60 * 1000l;
		long d = l / dateMillins;
		return d;
	}
	
	/**
	 * 获取两个日期相差的月份
	 * @author leah.pi
	 * @Date 2018年12月19日下午6:25:25
	 * @param startDate 
	 * @param endDate
	 * @return
	 */
	public static int getBetweenMonth(Date startDate, Date endDate) {
		if (startDate == null || endDate == null)
			return 0;
		if(endDate.compareTo(startDate)<1){
			return 0;
		}
		Calendar start = Calendar.getInstance();
		start.setTime(startDate);
		Calendar end = Calendar.getInstance();
		end.setTime(endDate);
		Integer year=(end.get(Calendar.YEAR)-start.get(Calendar.YEAR))*12;
		Integer month=end.get(Calendar.MONTH)-start.get(Calendar.MONTH);
		return Math.abs(month + year);
	}
	
	/**
	 * 获取两个日期相差的年份
	 * @author leah.pi
	 * @Date 2018年12月19日下午6:25:25
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int getBetweenYear(Date startDate, Date endDate) {
		Integer month=getBetweenMonth(startDate, endDate);
		return month<=0?month:month/12;
	}
	
	public static Calendar getDayEndTime(Calendar c){
		if(c == null)
			c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.MILLISECOND, 0);
		return c;
	}
	
	/**
	 * 对日期范围做校验，通过条件：1.每个日期的起始值小于等于结束值；2.各个范围之间不能有交叉
	 * @param dateRanges
	 * @return true-校验通过；false-校验通过
	 * @throws Exception
	 */
	public static boolean checkIfHasOverlappingAmongDateRanges(List<Date[]> dateRanges) throws Exception{
		if(StringUtils.isEmpty(dateRanges) || dateRanges.isEmpty())
			return true;
		List<Date[]> passedDateRanges = new ArrayList<Date[]>();
		for(Date[] dateRange : dateRanges){
			if(dateRange.length == 0 || dateRange.length > 2)
				throw new Exception("参数中每个数组代表一个日期范围，所以数组的长度不能等于0且不能大于2");
			Date start = dateRange[0];
			Date end = dateRange.length > 1 ? dateRange[1] : dateRange[0];
			if(end.compareTo(start) < 0)
				return false;
			if(passedDateRanges.isEmpty()){
				passedDateRanges.add(dateRange);
			}else{
				for(Date[] passedDateRange : passedDateRanges){
					Date pStart = passedDateRange[0];
					Date pEnd = passedDateRange.length > 1 ? passedDateRange[1] : passedDateRange[0];
					if((start.compareTo(pStart) >= 0 && start.compareTo(pEnd) <= 0)
							|| (end.compareTo(pStart) >= 0 && end.compareTo(pEnd) <= 0)
							|| (start.compareTo(pStart) < 0 && end.compareTo(pEnd) > 0))
						return false;
				}
				passedDateRanges.add(dateRange);
			}
		}
		return true;
	}
	/**
	 * 对日期范围做校验，通过条件：1.每个范围的起始值小于等于结束值；2.各个范围之间不能有交叉
	 * @param dateRanges
	 * @return true-校验通过；false-校验通过
	 * @throws Exception
	 */
	public static boolean checkIfHasOverlappingAmongDateRanges(Date[]... dateRanges) throws Exception{
		return checkIfHasOverlappingAmongDateRanges(Arrays.asList(dateRanges));
	}
	/**
	 * 日期字符串需符合所传日期格式patternStr，以下以数字代表日期
	 * 参数格式："1~3,4,5~6"，即可以是单个数值，但不能连续出现多个"~"（如："1~2~3"这样是不行的）；
	 * 功能：对数值范围做校验，通过条件：1.每个范围的起始值小于等于结束值；2.各个范围之间不能有交叉；
	 * @param dateRangesStr
	 * @return true-校验通过；false-校验通过
	 * @throws Exception
	 */
	public static boolean checkIfHasOverlappingAmongDateRanges(String dateRangesStr, String patternStr)
			throws Exception{
		if(!StringUtils.hasText(dateRangesStr))
			return true;
		List<Date[]> dateRanges = new ArrayList<Date[]>();
		for(String rangeStr : dateRangesStr.split(",")){
			String[] rangeArr = rangeStr.trim().split("~");
			if(!StringUtils.hasText(rangeStr) || rangeArr.length > 2)
				throw new Exception("日期范围只能是单个值或以\"~\"做连接的范围的形式，不能是空字符串或出现多个\"~\"");
			Date[] range = new Date[]{DateFormatUtil.strToDate(rangeArr[0], patternStr),
					DateFormatUtil.strToDate(rangeArr.length > 1 ? rangeArr[1] : rangeArr[0], patternStr)};
			dateRanges.add(range);
		}
		
		return checkIfHasOverlappingAmongDateRanges(dateRanges);
	}
	/**
	 * 日期字符串需符合所传日期格式"yyyy-MM-dd"，以下以数字代表日期
	 * 参数格式："1~3,4,5~6"，即可以是单个数值，但不能连续出现多个"~"（如："1~2~3"这样是不行的）；
	 * 功能：对数值范围做校验，通过条件：1.每个范围的起始值小于等于结束值；2.各个范围之间不能有交叉；
	 * 注：若范围的边界值相等不认为是范围交叉，即1~2,2~3不认为是交叉，但1~2,1~2认为是交叉
	 * @param dateRangesStr
	 * @return true-校验通过；false-校验通过
	 * @throws Exception
	 */
	public static boolean checkIfHasOverlappingAmongDateRanges(String dateRangesStr)
			throws Exception{
		if(!StringUtils.hasText(dateRangesStr))
			return true;
		List<Date[]> dateRanges = new ArrayList<Date[]>();
		for(String rangeStr : dateRangesStr.split(",")){
			String[] rangeArr = rangeStr.trim().split("~");
			if(!StringUtils.hasText(rangeStr) || rangeArr.length > 2)
				throw new Exception("日期范围只能是单个值或以\"~\"做连接的范围的形式，不能是空字符串或出现多个\"~\"");
			Date[] range = new Date[]{DateFormatUtil.strToDate(rangeArr[0]),
					DateFormatUtil.strToDate(rangeArr.length > 1 ? rangeArr[1] : rangeArr[0])};
			dateRanges.add(range);
		}
		
		return checkIfHasOverlappingAmongDateRanges(dateRanges);
	}

	public static void main(String[] args) {
		System.out.println(DateAddDay(new Date(), -365));
		// System.out.println(betweenTime(new Date(),"21:30:00","22:00:00"));
		System.out.println(formatLongDate(new Date()));
		System.out.println(formatLongDate(DateAddDay(new Date(), -365)));

		Calendar c2 = Calendar.getInstance();
		// c2.add(Calendar.DATE, 1);
		c2.set(Calendar.HOUR, 6);
		c2.set(Calendar.AM_PM, 0);
		System.out.println(c2.get(Calendar.YEAR));
		System.out.println(c2.get(Calendar.MONTH));
		System.out.println(c2.get(Calendar.DATE));
		System.out.println(c2.get(Calendar.AM));
		System.out.println(c2.get(Calendar.HOUR));
		System.out.println(c2.get(Calendar.MINUTE));
		System.out.println(c2.get(Calendar.SECOND));

		// System.out.println(dateToString(c2.getTime()));
		// c2.set(c1.get(Calendar.YEAR), c1.get(Calendar.MONTH),
		// c1.get(Calendar.DATE), 0, 0, 0);

		System.out.println(24 * 60 * 60 * 1000);

		// Calendar cal = Calendar.getInstance();
		// cal.add(Calendar.DATE, -1);
		// System.out.println(getBetweenDay(cal.getTime(), new Date()));
	}
	public static Date getMaxDateByMonth(String date){
		if(StringUtils.isEmpty(date)){
			return null;
		}
		Calendar calendar  = Calendar.getInstance();
		if(date.indexOf("-")>0){
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			calendar.setTime(DateUtils.formatDate(date,"yyyy-MM"));
			calendar.set(Calendar.DATE,calendar.getActualMaximum(Calendar.DATE));
			return DateUtils.formatDate(format.format(calendar.getTime()),"yyyy-MM-dd");
		}else{
			DateFormat format = new SimpleDateFormat("yyyyMMdd");
			calendar.setTime(DateUtils.formatDate(date,"yyyyMM"));
			calendar.set(Calendar.DATE,calendar.getActualMaximum(Calendar.DATE));
			return DateUtils.formatDate(format.format(calendar.getTime()),"yyyyMMdd");
		}
		
	}
	//获取系统上个月
	public static String getBeforeMonth(){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -1);
		return dateToString(calendar.getTime(),"yyyy-MM");
	}
}
