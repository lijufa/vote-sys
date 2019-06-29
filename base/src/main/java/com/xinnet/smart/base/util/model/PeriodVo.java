package com.xinnet.smart.base.util.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.xinnet.smart.base.ITime;
import com.xinnet.smart.base.util.UDate;
import com.xinnet.smart.base.util.UNumber;

/**
 * 时段
 * @author meitao
 * @date Sep 3, 2015 4:48:02 AM
 */
public class PeriodVo {
	//log
	public static final Logger logger = LoggerFactory.getLogger(PeriodVo.class);
	//开始时间
	private Date begin;
	//结束时间
	private Date end;

	public static final PeriodVo parseMonth(Integer month) {
		return PeriodVo.parseMonth(new Date(), month);
	}

	public static final PeriodVo parseMonth(Date begin, Integer months) {
		if (months == null || months == 0) {
			return PeriodVo.hour();
		} else {
			return PeriodVo.parse(begin, UDate.expire(begin, months));
		}
	}

	public static final PeriodVo hour() {
		PeriodVo ret = new PeriodVo();
		ret.setBegin(new Date());
		ret.setEnd(UDate.nextHour(ret.getBegin()));
		return ret;
	}

	public static final PeriodVo parse(String begin, String end) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return PeriodVo.parse(sdf.parse(begin), sdf.parse(end));
	}

	public static final PeriodVo parse(Date begin, Date end) {
		PeriodVo ret = new PeriodVo();
		ret.setBegin(begin);
		ret.setEnd(end);
		return ret;
	}

	public final Double percent() {
		return this.percent(this.months());
	}

	/**
	 * 算百分比（时长相对套餐）
	 * @author meitao
	 * @date Sep 4, 2015 1:45:51 AM
	 * @param priceMonths 产品价格对应的月份 0为按秒计费，1-36为指定的月份
	 * @return Double
	 */
	public final double percent(final Integer priceMonths) {
		int pm = (priceMonths == null ? 0 : priceMonths.intValue());
		if (pm == 0) {//小时
			return UNumber.divByDouble(this.between(), ITime.MS_PER_HOUR);
		} else {//包年包月
			int m = this.months();
			if (m == 0) {//不足28天，算出秒数比例
				return UNumber.divByDouble(this.between(), ITime.MIN_MS_PER_MONTH * pm);
			} else {
				long fullTime = this.end.getTime() - UDate.offsetMonth(this.end, -pm).getTime();//结束时间减去开始时间，购买时长
				//				long fullTime=pm*ITime.MIN_MS_PER_MONTH;
				logger.debug("pm:" + pm);
				logger.debug("end:" + this.end.getTime());
				logger.debug("start:" + UDate.offsetMonth(this.end, -pm).getTime());
				logger.debug("结束时间减去开始时间，购买时长:" + fullTime);
				return UNumber.divByDouble(this.between(), fullTime);
			}
		}
	}

	/**
	 * 云主机变更业务的计算比例
	 * 算百分比（时长相对套餐）
	 * @author meitao
	 * @date Sep 4, 2015 1:45:51 AM
	 * @param priceMonths 产品价格对应的月份 0为按秒计费，1-36为指定的月份
	 * @return Double
	 */
	public final double reconfigPercent(final Integer priceMonths) {
		int pm = (priceMonths == null ? 0 : priceMonths.intValue());
		if (pm == 0) {//小时
			return UNumber.divByDouble(this.between(), ITime.MS_PER_HOUR);
		} else {//包年包月
			long fullTime = this.end.getTime() - UDate.offsetMonth(this.end, -pm).getTime();//结束时间减去开始时间，购买时长
			return UNumber.divByDouble(this.between(), fullTime);
		}
	}

	/**
	 * 算百分比（时长相对套餐）
	 * @author meitao
	 * @date Sep 4, 2015 1:45:51 AM
	 * @param priceMonths 产品价格对应的月份 0为按秒计费，1-36为指定的月份
	 * @return Double
	 */
	public final double configPercent(final Integer priceMonths) {
		int pm = (priceMonths == null ? 0 : priceMonths.intValue());
		if (pm == 0) {//小时
			return UNumber.divByDouble(this.between(), ITime.MS_PER_HOUR);
		} else {//包年包月
			int m = this.months();
			if (m == 0) {//不足28天，算出秒数比例
				return UNumber.divByDouble(this.betweenSeconds(), ITime.MIN_MS_PER_MONTH * pm);
			} else {
				//				long fullTime = this.end.getTime() - UDate.offsetMonth(this.end, -pm).getTime();//结束时间减去开始时间，购买时长
				long fullTime = pm * ITime.MIN_MS_PER_MONTH / 1000;
				logger.debug("pm:" + pm);
				logger.debug("end:" + this.end.getTime());
				logger.debug("start:" + UDate.offsetMonth(this.end, -pm).getTime());
				logger.debug("结束时间减去开始时间，购买时长:" + fullTime);
				return UNumber.divByDouble(this.betweenSeconds(), fullTime);
			}
		}
	}

	/**
	 * 算套餐类型：月数（无符号）
	 * @author meitao
	 * @date Sep 4, 2015 1:45:38 AM
	 * @return Integer
	 */
	public final int monthsUnsiged() {
		return Math.abs(this.months());
	}

	private final int months() {
		//开始结束时间的毫秒数
		long between = Math.abs(between());
		//long dt = Math.abs(between);
		//如果差值大于一年
		if (between >= ITime.MIN_MS_PER_YEAR) {
			return (int) (between * 12 / ITime.MIN_MS_PER_YEAR); //先乘12再除才能得到正确的月份，否则可以将参与运算的数变为float
		}
		if (between >= ITime.MIN_MS_PER_TOW_MONTH) {
			return (int) (between * 2 / ITime.MIN_MS_PER_TOW_MONTH);
		}
		if (between >= ITime.MIN_MS_PER_MONTH) {
			return 1;
		}
		return 0;
	}

	/**
	 * 计算时间差（带符号）
	 * @author meitao
	 * @date Aug 31, 2015 9:47:49 PM
	 * @return long
	 */
	public final long between() {
		if (this.begin == null || this.end == null) {
			return 0;
		}
		return this.end.getTime() - this.begin.getTime();
	}

	/**
	 * 计算时间差（带符号,单位:秒）
	 * @author meitao
	 * @date Aug 31, 2015 9:47:49 PM
	 * @return long
	 */
	public final long betweenSeconds() {
		if (this.begin == null || this.end == null) {
			return 0;
		}
		return (this.end.getTime() - this.begin.getTime()) / 1000;
	}

	public final void setBegin(java.util.Date begin) {
		this.begin = begin;
	}

	public final Date getBegin() {
		return this.begin;
	}

	public final Date getEnd() {
		return this.end;
	}

	public final void setEnd(java.util.Date end) {
		this.end = end;
	}

	static final Logger getLogger() {
		return PeriodVo.logger;
	}
}
