package cat.mrtxema.crispetes.view.util;

import android.view.View;
import android.widget.TextView;

public class ViewUtils {
    public static void setTextOrHideParent(TextView textView, String s) {
        setTextOrHideView(textView, s, View.class.cast(textView.getParent()));
    }

    public static void setTextOrHide(TextView textView, String s) {
        setTextOrHideView(textView, s, textView);
    }

    public static void setTextOrHideView(TextView textView, String content, View viewToHide) {
        if (isEmpty(content)) {
            viewToHide.setVisibility(View.GONE);
        } else {
            textView.setText(content);
            viewToHide.setVisibility(View.VISIBLE);
        }
    }

    private static boolean isEmpty(String s) {
        return s == null || s.isEmpty();
    }
}
