package seongyunism.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Convertor {
	
	public static String toConvertTimeFromUnixTime(long unixTime) {
		
		Date date = new Date(unixTime * 1000L); // *1000 is to convert seconds to milliseconds
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd, HH:mm"); // the format of your date
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+9")); // give a timezone reference for formating (see comment at the bottom
		return sdf.format(date);

	}
}
