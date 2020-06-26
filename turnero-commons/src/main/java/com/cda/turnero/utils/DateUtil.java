package com.cda.turnero.utils;

import java.text.ParseException;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalTime;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
	

	public static String getFormat(Date fecha) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(fecha);
	}
	public static String getFormatHour(Date fecha) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		return sdf.format(fecha);
	}
	public static Date sumarDiaAFecha(Date fechaOriginal, int cantidad) {
		Calendar calFrom = new GregorianCalendar();

		calFrom.setTime(fechaOriginal);
		calFrom.add(Calendar.DATE, cantidad);
		return calFrom.getTime();
	}

	public static Date getDateFromString(String fecha) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		try {
			return sdf.parse(fecha);
		} catch (ParseException e) {
			return null;
		}
	}
	public static int getHourByDate(Date date) {
		DateTime dt = new DateTime(date);
		
		return dt.getHourOfDay();
	}
}
