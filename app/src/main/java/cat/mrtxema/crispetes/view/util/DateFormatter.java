package cat.mrtxema.crispetes.view.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {
    public static final String FULL_DATE_FORMAT = "dd/MM/yyyy";
    public static final String YEAR_FORMAT = "(yyyy)";

    public static String formatDate(Date date, String format) {
        if (date == null) {
            return null;
        }
        //TODO: cache formatters?
        return new SimpleDateFormat(format).format(date);
    }
}
