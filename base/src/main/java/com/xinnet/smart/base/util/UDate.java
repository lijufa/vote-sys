package com.xinnet.smart.base.util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.xinnet.smart.base.ITime;

/**
 * 日期工具
 * @author sungang
 *
 */
public class UDate {
	static final long ONE_HOUR = 1000 * 3600;
	static final Logger logger = LoggerFactory.getLogger(UDate.class);

	public final static String format(Date date) {
		if (date == null)
			return null;
		return new SimpleDateFormat(ITime.yyyy_MM_dd_HH_mm_ss_SSS).format(date);
	}

	public final static Date parse(String dateStr) {
		if (dateStr == null)
			return null;
		try {
			return new SimpleDateFormat(ITime.yyyy_MM_dd_HH_mm_ss_SSS).parse(dateStr);
		} catch (ParseException e) {
			logger.error(e.toString());
			return null;
		}
	}

	public final static Date parseDate(String dateStr) {
		if (dateStr == null)
			return null;
		try {
			return new SimpleDateFormat(ITime.yyyy_MM_dd_HH_mm_ss).parse(dateStr);
		} catch (ParseException e) {
			logger.error(e.toString());
			return null;
		}
	}

	public final static Date nextHour(Date time) {
		if (time == null) {
			return null;
		}
		return new Date(time.getTime() + 3600000L);
	}

	public final static int getMonth(Date time) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(time);
		return calendar.get(Calendar.MONTH);
	}

	public final static int getYear(Date time) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(time);
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * 求最近时间点
	 * @author meitao
	 * @date Sep 13, 2015 7:32:51 AM
	 * @param source
	 * @param target
	 * @return Date
	 */
	public final static Date nearByMonth(Date source, Date target) {
		if (source == null) {
			return null;
		}
		if (target == null) {
			return null;
		}
		long between = source.getTime() - target.getTime();
		if (between == 0) {
			return target;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(source);
		calendar.set(Calendar.YEAR, getYear(target));
		calendar.set(Calendar.MONTH, getMonth(target));
		if (between > 0) {
			//源时间在目标时间之后
			if (calendar.getTimeInMillis() < target.getTime()) {
				//源时间偏移后跑到目标时间前面了，需要加一个月
				calendar.add(Calendar.MONTH, 1);
			}
		} else {
			//源时间在目标时间之前
			if (calendar.getTimeInMillis() > target.getTime()) {
				//源时间偏移后跑到目标时间后面了，需要减一个月
				calendar.add(Calendar.MONTH, -1);
			}
		}
		return calendar.getTime();
	}

	/**
	 * 计算到期时间，天数不足时为对应月份最后一天
	 * @author meitao
	 * @date Sep 1, 2015 12:44:53 PM
	 * @param time
	 * @param month
	 * @return Date
	 */
	public final static Date expire(Date time, Integer months) {
		if (months == null || months <= 0) {
			return null;
		} else if (months > 0) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(time);
			calendar.add(Calendar.MONTH, months.intValue());
			return calendar.getTime();
		} else {
			return time;
		}
	}

	public final static Date date28(Date time) {
		if (time == null) {
			return null;
		} else {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(time);
			calendar.set(Calendar.DATE, 28);
			calendar.set(Calendar.HOUR, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.add(Calendar.DATE, 1);
			return calendar.getTime();
		}
	}

	/**
	 * 偏移月份，返回Date对象
	 * @author meitao
	 * @date Apr 30, 2015 1:26:15 PM
	 * @param begin
	 * @param month
	 * @return Date
	 */
	public static Date offsetMonth(Date begin, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(begin);
		calendar.add(Calendar.MONTH, month);
		return calendar.getTime();
	}

	public static Date offsetDate(Date begin, int date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(begin);
		calendar.add(Calendar.DATE, date);
		return calendar.getTime();
	}

	public static String getFormatDate(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		return dateFormat.format(date);
	}

	public static String getFormatDateMonthDay(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd");
		return dateFormat.format(date);
	}

	public static String getDelHostEmailDate(Date date) {
		if (date == null) {
			return "";
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int d = cal.get(Calendar.DATE);
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		StringBuilder builder = new StringBuilder();
		builder.append(year).append("年");
		if (month < 10)
			builder.append("0");
		builder.append(month).append("月");
		if (d < 10)
			builder.append("0");
		builder.append(d).append("日   ");
		if (hour < 10)
			builder.append("0");
		builder.append(hour).append(":");
		if (minute < 10)
			builder.append("0");
		builder.append(minute).append(":");
		if (second < 10)
			builder.append("0");
		builder.append(second);
		return builder.toString();
	}

	public static String getFormatDateYearToSecond(Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateFormat.format(date);
	}

	public static Date getNimbulaDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		//设置为北京时间
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}

	public static String formatDate(Date date, String formatter) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatter);
		return sdf.format(date);
	}

	/**
	* date 日期加上，或减去几天
	* @param date
	* @param dateNum
	* @return
	*/
	public static Date getDateInDayAgo(Date date, int dateNum) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, dateNum);
		return cal.getTime();
	}

	public static Timestamp getTimestamp(Date date) {
		String s = formatDate(date, "yyyy-MM-dd HH:mm:ss");
		return Timestamp.valueOf(s);
	}

	public static Date getOpzooncloudDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		//设置为北京时间
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * 获取当前时间加上指定小时后的时间
	 * @author xiaojuan
	 * @date 2015年4月9日 下午3:16:44
	 * @param date
	 * @param hours
	 * @return Date
	 */
	public static Date getHoursFlow(Date date, int hours) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR_OF_DAY, hours);//24小时制 
		//cal.add(Calendar.HOUR, x);12小时制 
		date = cal.getTime();
		return date;
	}

	/**
	 * 获取当前时间加上指定月的时间
	 * @author xiaojuan
	 * @date 2015年4月9日 下午3:16:44
	 * @param date
	 * @param hours
	 * @return Date
	 */
	public static Date getMonthsFlow(Date date, int month) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, month);//24小时制 
		date = cal.getTime();
		return date;
	}
}