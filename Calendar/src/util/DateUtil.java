package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    private static final String DAY_FORMAT = "dd-MM-yyyy";
    private static final String DAY_AND_MONTH_FORMAT = "dd-MM";
    private static final String DAY_OF_WEEK_FORMAT = "EE";
    private static final String DAY_IN_MONTH_FORMAT = "dd";

    public static long getDayEpoch(Date date) throws ParseException {
        SimpleDateFormat dayFormat = new SimpleDateFormat(DAY_FORMAT);
        return dayFormat.parse(dayFormat.format(date)).getTime();
    }

    public static String getWeekDay(Date date) {
        return new SimpleDateFormat(DAY_OF_WEEK_FORMAT).format(date);
    }

    public static int getDayInMonth(Date date) {
        return Integer.parseInt(new SimpleDateFormat(DAY_IN_MONTH_FORMAT).format(date));
    }

    public static String getDayAndMonth(Date date) {
        return new SimpleDateFormat(DAY_AND_MONTH_FORMAT).format(date);
    }
}
