package cat.mrtxema.crispetes.view.util;

import android.view.View;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.aakira.expandablelayout.ExpandableLinearLayout;

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

    public static void setAdapter(ExpandableLinearLayout layout, Adapter adapter) {
        if (adapter.getCount() > 0) {
            layout.removeAllViews();
            for (int i = 0; i < adapter.getCount(); i++) {
                layout.addView(adapter.getView(i, null, layout));
            }
            layout.initLayout();
        }
    }

    public static void showOrHide(View viewToHide, String value) {
        viewToHide.setVisibility(isEmpty(value) ? View.GONE : View.VISIBLE);
    }
}
