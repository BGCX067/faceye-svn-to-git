package com.faceye.core.util.helper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;

public class DateUtil {
	public static String getFirstDayOfWeek(Date date) {
		Calendar cal = Calendar.getInstance(Locale.CHINA);
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return parseDate(cal.getTime(), null);
	}

	public static String getLastDayOfWeek(Date date) {
		Calendar cal = Calendar.getInstance(Locale.CHINA);
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return parseDate(cal.getTime(), null);
	}

	public static String getFirstDayOfMonth(Date date) {
		// Calendar calendar = new GregorianCalendar();
		Calendar calendar = Calendar.getInstance(Locale.CHINA);
		calendar.setTime(date);
		calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
		return parseDate(calendar.getTime(), null);
	}

	public static String getLastDayOfMonty(Date date) {
		// Calendar calendar = new GregorianCalendar();
		Calendar calendar = Calendar.getInstance(Locale.CHINA);
		calendar.setTime(date);
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		return parseDate(calendar.getTime(), null);

	}

	// public static String getFirstDayOfYear(Date date) {
	// Calendar calendar = Calendar.getInstance(Locale.CHINA);
	// calendar.setTime(date);
	// calendar.set(Calendar.DATE,Calendar.DAY_OF_YEAR);
	//		
	// }

	public static String parseDate(Date date, String patt) {
		if (StringUtils.isEmpty(patt)) {
//			patt = "yyyy-MM-dd";
			patt="yyyy-MM-dd hh:mm:ss";
		}
;		SimpleDateFormat sdf = new SimpleDateFormat(patt);
		return sdf.format(date);
	}

	public static List<Date> getAllDates(Date start, Date end) {
		Calendar calendar1 = new GregorianCalendar();
		calendar1.setTime(start);
		Calendar calendar2 = new GregorianCalendar();
		calendar2.setTime(end);
		List<Date> list = new ArrayList<Date>();
		list.add(start);
		while (calendar1.compareTo(calendar2) < 0) {
			calendar1.add(Calendar.DATE, 1);
			list.add(calendar1.getTime());
		}
		return list;
	}

	public static List<Date> getMonths(Date start, Date end) {
		Calendar calendar1 = new GregorianCalendar();
		calendar1.setTime(start);
		Calendar calendar2 = new GregorianCalendar();
		calendar2.setTime(end);
		List<Date> list = new ArrayList<Date>();
		while (calendar1.compareTo(calendar2) < 0) {
			list.add(calendar1.getTime());
			calendar1.add(Calendar.MONTH, 1);
		}
		list.add(calendar1.getTime());
		return list;
	}

	/**
	 * 取得日期所在周的第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstWeekDay(Date date) {
		initCalendar(date);
		gc.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return gc.getTime();
	}

	/** */
	/**
	 * 取得日期所在周的最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastWeekDay(Date date) {
		initCalendar(date);
		gc.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return gc.getTime();
	}

	/** */
	/**
	 * 取得日期所在月的最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstMonthDay(Date date) {
		initCalendar(date);
		int dayOfMonth = gc.get(Calendar.DAY_OF_MONTH);
		gc.add(Calendar.DAY_OF_MONTH, 1 - dayOfMonth);
		return gc.getTime();
	}

	/** */
	/**
	 * 取得日期所在月的最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastMonthDay(Date date) {
		initCalendar(date);
		int dayOfMonth = gc.get(Calendar.DAY_OF_MONTH);
		int maxDaysOfMonth = gc.getActualMaximum(Calendar.DAY_OF_MONTH);
		gc.add(Calendar.DAY_OF_MONTH, maxDaysOfMonth - dayOfMonth);
		return gc.getTime();
	}
	

	/***************************************************************************
	 * 取得日期所在旬的最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstTenDaysDay(Date date) {
		initCalendar(date);
		int dayOfMonth = gc.get(Calendar.DAY_OF_MONTH);
		if (dayOfMonth <= 10) {
			gc.set(Calendar.DAY_OF_MONTH, 1);
		} else if (dayOfMonth > 20) {
			gc.set(Calendar.DAY_OF_MONTH, 21);
		} else {
			gc.set(Calendar.DAY_OF_MONTH, 11);
		}
		return gc.getTime();
	}

	/** */
	/**
	 * 取得日期所在旬的最后一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastTenDaysDay(Date date) {
		initCalendar(date);
		int dayOfMonth = gc.get(Calendar.DAY_OF_MONTH);
		if (dayOfMonth <= 10) {
			gc.set(Calendar.DAY_OF_MONTH, 10);
		} else if (dayOfMonth > 20) {
			gc.set(Calendar.DAY_OF_MONTH, gc
					.getActualMaximum(Calendar.DAY_OF_MONTH));
		} else {
			gc.set(Calendar.DAY_OF_MONTH, 19);
		}
		return gc.getTime();
	}

	/**
	 * 取得一年的第一天
	 * @param date
	 */
	public static Date getFirstDayOfYear(Date date){
		initCalendar(date);
		gc.set(Calendar.YEAR, Calendar.getInstance().YEAR);
		gc.set(Calendar.MONTH,Calendar.getInstance().JANUARY);
		Date firstDayOfMonth=getFirstMonthDay(gc.getTime());
		return firstDayOfMonth;
	}
	/**
	 * 取得一年的最后一天
	 * @param date
	 */
	public static Date getLastDayOfYear(Date date){
		initCalendar(date);
		gc.set(Calendar.YEAR, Calendar.getInstance().YEAR);
		gc.set(Calendar.MONTH,Calendar.getInstance().DECEMBER);
		Date firstDayOfMonth=getFirstMonthDay(gc.getTime());
		return firstDayOfMonth;
	}
	private static void initCalendar(Date date) {
		if (date == null) {
			throw new IllegalArgumentException("argument date must be not null");
		}

		gc.clear();
		gc.setTime(date);
	}

	private static GregorianCalendar gc = null;
	static {
		gc = new GregorianCalendar(Locale.CHINA);
		gc.setLenient(true);
		gc.setFirstDayOfWeek(Calendar.MONDAY);
	}

}
