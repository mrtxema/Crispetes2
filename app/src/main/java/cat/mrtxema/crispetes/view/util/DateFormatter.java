package cat.mrtxema.crispetes.view.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DateFormatter {
    public static final String FULL_DATE_FORMAT = "dd/MM/yyyy";
    public static final String YEAR_FORMAT = "(yyyy)";
    private static final Map<String,DateFormat> formatters = new HashMap<>();

    public static String formatDate(Date date, String format) {
        if (date == null) {
            return null;
        }
        return getDateFormat(format).format(date);
    }

    private static DateFormat getDateFormat(String format) {
        DateFormat result = formatters.get(format);
        return result == null ? createDateFormat(format) : result;
    }

    private static synchronized DateFormat createDateFormat(String format) {
        DateFormat result = formatters.get(format);
        if (result == null) {
            result = new SimpleDateFormat(format);
            formatters.put(format, result);
        }
        return result;
    }
}
