package cat.mrtxema.crispetes.view.util;

import java.util.List;

public class StringFormatter {
    public static final String COMMA_SEPARATOR = ", ";
    public static final String PARENTHESIS_PATTERN = "(%s)";

    public static String addParenthesis(String s) {
        return String.format(PARENTHESIS_PATTERN, s);
    }

    public static String joinList(List<String> stringList, String separator) {
        StringBuffer result = new StringBuffer();
        if (!stringList.isEmpty()) {
            result.append(stringList.get(0));
            for (String item : stringList.subList(1, stringList.size())) {
                result.append(separator).append(item);
            }
        }
        return result.toString();
    }
}
