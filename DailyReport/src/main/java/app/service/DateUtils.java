package app.service;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateUtils{
	public static Date getToday() {
		LocalDate localDate = LocalDate.now(); 
		Date today = Date.from(localDate.atStartOfDay(ZoneId.of("Asia/Ho_Chi_Minh")).toInstant());
		return today;  
	}
}