package com.framework.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

/**
 * @author sorata
 * @date 2018/5/20 0020 下午 6:16
 * @since jdk 1.8
 */
public final class XFDateUtils {


	public static LocalDateTime toLocalDateTime(Date date) {
		return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
	}

	public static Date toDate(LocalDateTime dateTime) {
		return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	public static Date toDate(LocalDate localDate, int hour, int minute, int second) {
		return Date.from(localDate.atTime(hour, minute, second).atZone(ZoneId.systemDefault()).toInstant());
	}

	public static String format(LocalDateTime dateTime, String pattern) {
		return dateTime.format(DateTimeFormatter.ofPattern(pattern));
	}

	public static String format(LocalDate date, String pattern) {
		return date.format(DateTimeFormatter.ofPattern(pattern));
	}

	public static String format(LocalTime localTime, String pattern) {
		return localTime.format(DateTimeFormatter.ofPattern(pattern));
	}

	public static LocalDateTime newDay() {
		return LocalDateTime.now().plusDays(1).withHour(0).withMinute(0).withSecond(0).withNano(0);
	}


	public static long getPeriodSecond(Date start, Date end) {
		return toLocalDateTime(start).until(toLocalDateTime(end), ChronoUnit.SECONDS);
	}

	public static long getPeriodDay(Date start, Date end) {
		return toLocalDateTime(start).until(toLocalDateTime(end), ChronoUnit.DAYS);
	}


	public static String toText(long second) {
		if (second >= 3600) {
			return String.format("%d小时", second / 3600);
		} else if (second >= 60) {
			return String.format("%d分钟", second / 60);
		} else {
			return String.format("%d秒", second);
		}
	}


	public static String toWeekday(Integer day, Integer model) {
		String result = "日期错了";
		switch (day) {
			case 0:
				result = "一";
				break;
			case 1:
				result = "一";
				break;
			case 2:
				result = "二";
				break;
			case 3:
				result = "三";
				break;
			case 4:
				result = "四";
				break;
			case 5:
				result = "五";
				break;
			case 6:
				result = "六";
				break;
			case 7:
				result = "日";
				break;
			default:
				return result;
		}

		if (model == 1) {
			result = "周" + result;
		} else {
			result = "星期" + result;
		}

		return result;
	}


}
