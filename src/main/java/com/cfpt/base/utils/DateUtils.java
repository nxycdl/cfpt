package com.cfpt.base.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	public static MySimpleDateFormat YMD = new MySimpleDateFormat("yyyyMMdd");

	public static MySimpleDateFormat YYYY = new MySimpleDateFormat("yyyy");

	public static MySimpleDateFormat sdf = new MySimpleDateFormat(
			"yyyyMMddHHmmss");

	public static MySimpleDateFormat Y2NO_Mill_FORMAT = new MySimpleDateFormat(
			"yyyyMMddHHmmssSSS");

	public static final MySimpleDateFormat YMD_CN = new MySimpleDateFormat(
			"yyyy年MM月dd日");
	public static MySimpleDateFormat Times = new MySimpleDateFormat("HH:mm:ss");
	public static final MySimpleDateFormat YYMD_FORMAT = new MySimpleDateFormat(
			"yyMMdd");

	public static MySimpleDateFormat Y2DTIME_FORMAT = new MySimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	public static MySimpleDateFormat Y2CH_FORMAT = new MySimpleDateFormat(
			"yyyy年MM月dd日 HH时mm分ss秒");

	public static MySimpleDateFormat Y2NO_FORMAT = new MySimpleDateFormat(
			"yyyyMMddHHmmss");

	public static MySimpleDateFormat Y2D_FORMAT = new MySimpleDateFormat(
			"yyyyMMdd");

	public static MySimpleDateFormat Y2T_FORMAT = new MySimpleDateFormat(
			"HHmmss");

	public static MySimpleDateFormat YMD_FORMAT = new MySimpleDateFormat(
			"yyyy-MM-dd");

	public static final MySimpleDateFormat Y2SPECIAL_FORMAT = new MySimpleDateFormat(
			"yyyy.MM.dd");

	public static String getSystemDateOfString() {
		Calendar calendar = Calendar.getInstance();
		return sdf.format(calendar.getTime());
	}

	public static Date getSystemDate() {
		Calendar calendar = Calendar.getInstance();
		return calendar.getTime();
	}

	public static Date getDateyyymmdd(String date) {
		try {
			return YMD.parse(date);
		} catch (ParseException e) {
			System.out.print("yyymmdd日期格式不正确!");
		}
		return getSystemDate();
	}

	public static Date getDate(String date) {
		try {
			return (date == null || "".equals(date)) ? null
					: ((date.length() == 8 || date.length() == 10) ? YMD
							.parse(date) : sdf.parse(date));
		} catch (ParseException e) {
			System.out.print("日期格式不正确!");
		}
		return getSystemDate();
	}

	public static Date truncDateTime(Date date) {
		try {
			return YMD_FORMAT.parse(YMD_FORMAT.format(date));
		} catch (ParseException e) {
			System.out.print("日期格式不正确!");
			return date;
		}
	}

	/**
	 * 日期增加天数获得新的日期
	 * 
	 * @param oldDate
	 * @param intDay
	 * @return
	 */
	public final static Date getDateAdd(Date oldDate, int intDay) {
		Calendar calendar = Calendar.getInstance();// 实例化calendar对象
		calendar.setTime(oldDate);// 设置calendar对象的时间属性
		calendar.add(Calendar.DATE, intDay);// 对天数进行增加
		return calendar.getTime();// 得到calendar对象的时间
	}

	public final static Date getDateAddTime(Date oldDate, Date oldTime) {
		Calendar calendar1 = Calendar.getInstance();// 实例化calendar对象
		Calendar calendar2 = Calendar.getInstance();// 实例化calendar对象
		calendar1.setTime(oldDate);
		calendar2.setTime(oldTime);
		calendar1.set(Calendar.HOUR, calendar2.get(Calendar.HOUR));
		calendar1.set(Calendar.MINUTE, calendar2.get(Calendar.MINUTE));
		calendar1.set(Calendar.SECOND, calendar2.get(Calendar.SECOND));
		return calendar1.getTime();
	}

	/**
	 * 日期相减
	 * 
	 * @param date
	 *            日期
	 * @param date1
	 *            日期
	 * @return 返回相减后的日期
	 */
	public static int diffDate(Date date, Date date1) {
		return (int) ((date.getTime() - date1.getTime()) / (24 * 3600 * 1000));
	}

	/**
	 * 返回相差秒数;
	 * 
	 * @param date
	 * @param date1
	 * @return
	 */
	public static int diffSencond(Date date, Date date1) {
		return (int) (date.getTime() - date1.getTime()) / 1000;
	}

	/**
	 * 取得当前日期增加的月数后月份的第一天 比如，当前日期为2009-06-25日，得到增加一个月后日期是7月份的1号 Date date =
	 * BaseUtil.getMonthAdd(date,1);
	 * 
	 * @param date
	 *            当前日期
	 * @param month
	 *            月数
	 * @return
	 */
	public static Date getMonthAdd(String date, int month) {
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(YMD.parse(date));
		} catch (ParseException e) {
		}
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.add(Calendar.MONTH, month);

		return calendar.getTime();
	}

	/**
	 * 取得当前月份属于第几季度和季度包含的开始月份和结束月份
	 * 
	 * @param month
	 * @return
	 */
	static public String[] getQuarterOfMonth(String month) {
		String[] ret = new String[2];

		String firstQuarter = "01,02,03";
		String secondQuarter = "04,05,06";
		String thirdQuarter = "07,08,09";
		String fourthQuarter = "10,11,12";

		if (firstQuarter.indexOf(month) >= 0) {
			ret = new String[] { "01", "03", "第一季度" };
		}
		if (secondQuarter.indexOf(month) >= 0) {
			ret = new String[] { "04", "06", "第二季度" };
		}
		if (thirdQuarter.indexOf(month) >= 0) {
			ret = new String[] { "07", "09", "第三季度" };
		}
		if (fourthQuarter.indexOf(month) >= 0) {
			ret = new String[] { "10", "12", "第四季度" };
		}

		return ret;
	}

	/**
	 * 获取指定日期是星期几
	 * 
	 * @param date
	 * @return
	 */
	static public String getDayOfWeek(Date date) {
		String[] week = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return week[calendar.get(Calendar.DAY_OF_WEEK) - 1];
	}

	/**
	 * 得到两个日期之间天数
	 * 
	 * @deprecated
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws ParseException
	 */
	public static int getDays2(String startDate, String endDate)
			throws ParseException {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(YMD_FORMAT.parse(startDate));
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(YMD_FORMAT.parse(endDate));

		int days = calendar1.get(Calendar.DAY_OF_YEAR)
				- calendar2.get(Calendar.DAY_OF_YEAR) + 1;

		return days;
	}

	/**
	 * 添加计算两日期间的天数(包括头尾日期) 要求输入格式为实例如: 2007-09-25 如果格式输入有误将反回空字符串
	 */
	public static String countDaysBetweenTwoData(String stratDataStr,
			String endDataStr) {
		String countDays = "";

		String t1 = stratDataStr.replace('-', '/');
		String t2 = endDataStr.replace('-', '/');

		try {
			Date dt1 = new Date(t1);
			Date dt2 = new Date(t2);
			long l = dt1.getTime() - dt2.getTime();

			long countDay = l / 60 / 60 / 1000 / 24;
			countDays = String.valueOf(countDay + 1);
		} catch (Exception e) {
			return "";
		}
		return countDays;
	}

	/**
	 * 得到两个日期之间天数(此格式：yyyy-MM-dd)，不带时分秒
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int getDays(String startDate, String endDate) {
		try {
			return (int) ((YMD_FORMAT.parse(endDate).getTime() - YMD_FORMAT
					.parse(startDate).getTime()) / (24 * 3600 * 1000)) + 1;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 得到两个日期之间的月份
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 * @throws ParseException
	 */
	public static int getMonths(String startDate, String endDate)
			throws ParseException {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(YMD_FORMAT.parse(startDate));
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(YMD_FORMAT.parse(endDate));

		int months = calendar1.get(Calendar.MONTH)
				- calendar2.get(Calendar.MONTH) + 1;
		return months;
	}

	/**
	 * 判断date1在date2之前
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 * @throws ParseException
	 */
	public static boolean beforDate(String date1, String date2)
			throws ParseException {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(sdf.parse(date1));
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(sdf.parse(date2));

		return calendar1.before(calendar2);
	}

	/**
	 * 判断date1在date2之前
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 * @throws ParseException
	 */
	public static boolean beforDate(Date date1, Date date2)
			throws ParseException {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(date1);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(date2);

		return calendar1.before(calendar2);
	}

	/**
	 * 判断date1在date2之后
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 * @throws ParseException
	 */
	public static boolean afterDate(String date1, String date2)
			throws ParseException {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(sdf.parse(date1));
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(sdf.parse(date2));

		return calendar1.after(calendar2);
	}

	/**
	 * 判断date1在date2之后
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 * @throws ParseException
	 */
	public static boolean afterDate(Date date1, Date date2)
			throws ParseException {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(date1);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(date2);

		return calendar1.after(calendar2);
	}

	/**
	 * 将String[]转为以某个分隔的字符，如“,”串起来的字符串。
	 * 
	 * @param strArrayIn
	 *            传入的字符数组。
	 * @param strSeparator
	 *            分隔的字符。
	 */
	public static String parseArrayToString(String[] strArrayIn,
			String strSeparator) {
		if (strArrayIn == null) {
			return "";
		}
		StringBuffer sbArray = new StringBuffer();
		for (int i = 0; i < strArrayIn.length; i++) {
			sbArray.append(strArrayIn[i]);
			sbArray.append(strSeparator);
		}
		int iEnd = sbArray.length() - strSeparator.length();
		return sbArray.substring(0, iEnd);
	}

	private static final int DEF_DIV_SCALE = 10;

	/**
	 * @author HANS
	 * @param currentDate
	 *            指定日期
	 * @return 返回指定日期月份的最后一天的日期
	 */
	@SuppressWarnings("deprecation")
	public final static Date lastDayOfMonth(Date currentDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);
		final int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		Date lastDate = calendar.getTime();
		lastDate.setDate(lastDay);
		return lastDate;
	}

	/**
	 * @author HANS
	 * @param currentDate
	 *            指定日期
	 * @return 返回指定日期年份的最后一天的日期
	 */
	public final static Date lastDayOfYear(Date currentDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);
		calendar.set(Calendar.MONTH, 11);
		calendar.set(Calendar.DAY_OF_MONTH, 31);
		return calendar.getTime();
	}

	public final static Date FormatDateTime(Date date) {
		Date newDate = null;
		try {
			newDate = new MySimpleDateFormat("yyyy-MM-dd").parse(YMD
					.format(date));
			return newDate;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return newDate;
	}

	public final static String getStringMonthAdd(String oldMonth, int intMonth) {
		// 获取前一个月；算法：先将参数incomeMonth中的年月拆分开来，然后将月份 -1，最后再组合成年月形式；特殊(跨年)：如果 月份-1
		// = 0 则年份也要 -1
		int currYear = Integer.parseInt(oldMonth.substring(0, 4));
		int currMonth = Integer.parseInt(oldMonth.substring(4, 6));
		int lastMonth = currMonth + intMonth;
		int lastYear = currYear;
		if (lastMonth == 0) {
			lastMonth = 12;
			lastYear = lastYear - 1;
		}
		String imcomeLastMonth = "";
		if (lastMonth < 10) {
			imcomeLastMonth = lastYear + "0" + lastMonth;
		} else {
			imcomeLastMonth = lastYear + "" + lastMonth;
		}
		return imcomeLastMonth;
	}

	public static int getAge(Date birthDay) throws Exception {
		Calendar cal = Calendar.getInstance();

		if (cal.before(birthDay)) {
			throw new IllegalArgumentException("生日日期格式不合法!");
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
				// monthNow==monthBirth
				if (dayOfMonthNow < dayOfMonthBirth) {
					age--;
				} else {
					// do nothing
				}
			} else {
				// monthNow>monthBirth
				age--;
			}
		} else {
			// monthNow<monthBirth
			// donothing
		}

		return age;
	}

	public static String getYymmddDateOfString() {
		Calendar calendar = Calendar.getInstance();
		return YYMD_FORMAT.format(calendar.getTime());
	}

	// 判断连个日期是否相等（只比较年月日)
	public static boolean isSameDay(Date d1, Date d2) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(d1);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(d2);
		if (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR)
				&& c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH)
				&& c1.get(Calendar.DAY_OF_MONTH) == c2
						.get(Calendar.DAY_OF_MONTH)) {
			return true;
		}
		return false;
	}

	/**
	 * 日期转换为字符串
	 * 
	 * @param Date
	 *            date：需要转换的日期
	 * @param String
	 *            format：转换格式
	 * @return String
	 * @throws
	 */
	public static String dateToStr(Date date, String format) {
		MySimpleDateFormat dateFormat = new MySimpleDateFormat(format);
		return dateFormat.format(date);
	}

	/**
	 * 时间和小时的相加减处理. number:为正数是相加,为负数是相减
	 */
	public static Date addDateByType(Date d, String type, int number) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(d);
		if (type.equals("YEAR"))
			calendar.add(Calendar.YEAR, number);
		if (type.equals("MONTH"))
			calendar.add(Calendar.MONTH, number);
		if (type.equals("DAY"))
			calendar.add(Calendar.DAY_OF_MONTH, number);
		if (type.equals("HOUR"))
			calendar.add(Calendar.HOUR_OF_DAY, number);
		Date date = calendar.getTime();
		return date;
	}

	/**
	 * 获取指定日期的前一天
	 * 
	 * @param specifiedDay
	 * @return
	 */
	public static Date getSpecifiedDayBefore(String specifiedDay) {// 可以用new
																	// Date().toLocalString()传递参数
		Calendar c = Calendar.getInstance();
		Date date = null;
		try {
			date = new SimpleDateFormat("yy-MM-dd").parse(specifiedDay);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setTime(date);
		int day = c.get(Calendar.DATE);
		c.set(Calendar.DATE, day - 1);
		return c.getTime();
	}
	
	
	/**
	 * 日期增加年获得新的日期
	 * @param oldDate
	 * @param intYear
	 * @return
	 */
	public final static Date getDateAddYear(Date oldDate, int intYear) {
		Calendar calendar = Calendar.getInstance();// 实例化calendar对象
		calendar.setTime(oldDate);// 设置calendar对象的时间属性
		calendar.add(Calendar.YEAR, intYear);// 对年进行增加
		return calendar.getTime();// 得到calendar对象的时间
	}
	
	/** 
     * 获取某年第一天日期 
     * @param year 年份 
     * @return Date 
     */  
    public static Date getYearFirst(int year){  
        Calendar calendar = Calendar.getInstance();  
        calendar.clear();  
        calendar.set(Calendar.YEAR, year);  
        Date currYearFirst = calendar.getTime();  
        return currYearFirst;  
    }  
      
    /** 
     * 获取某年最后一天日期 
     * @param year 年份 
     * @return Date 
     */  
    public static Date getYearLast(int year){  
        Calendar calendar = Calendar.getInstance();  
        calendar.clear();  
        calendar.set(Calendar.YEAR, year);  
        calendar.roll(Calendar.DAY_OF_YEAR, -1);  
        Date currYearLast = calendar.getTime();  
          
        return currYearLast;  
    }  
  
}
