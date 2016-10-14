package cat.mrtxema.crispetes.view.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {

    public static String formatDate(Date date, String format) {
        //TODO: cache formatters?
        return new SimpleDateFormat(format).format(date);
    }
}
