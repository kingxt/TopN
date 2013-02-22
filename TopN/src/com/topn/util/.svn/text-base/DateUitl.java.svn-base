package com.topn.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * 创建人 KingXt 创建时间：2011-3-27 下午07:56:31 格式化日期输出
 */
public class DateUitl {

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat sdfMM = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm");
	private static SimpleDateFormat year = new SimpleDateFormat("yyyy");
	
	//格式化今天
	private static SimpleDateFormat todayFormat = new SimpleDateFormat("HH:mm");
	
	//格式化今天
	private static SimpleDateFormat yestodayFormat = new SimpleDateFormat("昨天  HH:mm");
	
	//格式化前天
	private static SimpleDateFormat qianFormat = new SimpleDateFormat("前天  HH:mm");

	/**
	 * 将日期格式化成一个字符串
	 * yyyy-MM-dd
	 * @param date
	 * @return
	 */
	public static String dateTOString(Date date) {
		if (null == date) {
			return "";
		}
		return sdf.format(date);
	}

	/**
	 * 格式化日期  yyyy-MM-dd HH:mm
	 * @param date
	 * @return
	 */
	public static String dataTOMMString(Date date) {
		if (null == date) {
			return "";
		}
		return sdfMM.format(date);
	}

	/**
	 * 截取一个时间字符串的年份
	 * 
	 * @param date
	 * @return
	 */
	public static String yearOfDate(String date) {
		int index = date.indexOf("-");
		if (0 > index)
			return "";
		return date.substring(0, index);
	}

	/**
	 * 截取一个时间的月份
	 * 
	 * @param date
	 * @return
	 */
	public static String monthOfDate(String date) {
		int index1 = date.indexOf("-");
		int index2 = date.lastIndexOf("-");
		if (0 > index1)
			return "";
		return date.substring(index1 + 1, index2);
	}

	/**
	 * 获取指定日期的 当天
	 * 
	 * @param date
	 * @return
	 */
	public static String dayOfDate(String date) {
		int index = date.lastIndexOf("-");
		if (0 > index)
			return "";
		return date.substring(index + 1, date.length());
	}

	/**
	 * 根据日期 本地化显示 例如 今天 9:00
	 * 
	 * @param date
	 * @return
	 */
	public static String localDateShow(Date date) {
		if(date == null){
			return "";
		}
		if(isToday(date)){
			return todayFormat.format(date);
		}else if(isYesterday(date)){
			return yestodayFormat.format(date);
		}else if(isDayBeforeYesterday(date)){
			return qianFormat.format(date);
		}else{
			return sdfMM.format(date);
		}
	}
	
	/**
	 * 判断一个日期是否是今天
	 * @param a
	 */
	public static boolean isToday(Date a) {
		Calendar c = Calendar.getInstance();
		Date today = c.getTime();
		return sdf.format(a).equals(sdf.format(today));
	}

	/**
	 * 判断一个日期是否是昨天
	 * @param a
	 */
	public static boolean isYesterday(Date a) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DATE, c.get(Calendar.DATE) - 1);
		Date today = c.getTime();
		return sdf.format(a).equals(sdf.format(today));
	}
	
	
	
	
	/**
	 * 判断一个日期是否是前天
	 * @param a
	 */
	public static boolean isDayBeforeYesterday(Date a) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DATE, c.get(Calendar.DATE) - 2);
		Date today = c.getTime();
		return sdf.format(a).equals(sdf.format(today));
	}

	/**
	 * 获取今年
	 * 
	 * @return
	 */
	public static String getThisYear() {
		return year.format(new Date());
	}
	
	/**
	 * 判断一个日期距离现在多少天
	 * @param a
	 * @return
	 */
	public static int deltaNumDay2Now(Date a){
		long t = (new Date().getTime()  -  a.getTime());
		int delta = (int)t/(1000*24*60*60);
		return delta;
	}
	
	public static void main(String[] args) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.DATE, c.get(Calendar.DATE) - 2);
		Date today = c.getTime();
		System.out.println(deltaNumDay2Now(today));
	}
	
	/**
	 * 判断两个日期的大小
	 * @param a
	 * @param b
	 * @return 如果a 大约b 则返回1 否则返回0
	 */
	public static int isALarge(String a, String b){
		Date begin = null;
		Date end = null;
		try {
			begin = sdf.parse(a);
			end = sdf.parse(b);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long tt = begin.getTime() - end.getTime();
		if(tt > 0){
			return 1;
		}else{
			return -1;
		}
	}
}
