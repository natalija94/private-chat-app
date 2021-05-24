package chat.rs.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.FormatStyle;
import java.util.Calendar;

public class DateUtil {

    private DateUtil() {
    }

    public static String convertToDateString(LocalDateTime localDateTime) {
        return DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).format(localDateTime);
    }

    public static LocalDateTime convertToLocalDateTimeNow() {
        return new Timestamp(Calendar.getInstance().getTimeInMillis()).toLocalDateTime();
    }
}
