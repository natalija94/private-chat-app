package chat.rs.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtil {

    private DateUtil() {
    }

    public static String convertToDateString(LocalDateTime localDateTime) {
        //todo
        return null;
    }

    public static LocalDateTime convertToLocalDateTime(Date date) {
        if (date == null) {
            return null;
        }
        //todo

        return null;
    }

    public static LocalDateTime convertToLocalDateTime(String date) {
        if (date == null) {
            return null;
        }
        //todo check
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(Constants.DATE_PATTERN);
        return LocalDateTime.parse(date.replace("T", " ").replace("Z", ""), timeFormatter);
    }
}
