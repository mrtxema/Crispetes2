package cat.mrtxema.crispetes.view.util;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Adapter;
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

    public static void setAdapter(ViewGroup layout, Adapter adapter) {
        if (adapter.getCount() > 0) {
            layout.removeAllViews();
            for (int i = 0; i < adapter.getCount(); i++) {
                layout.addView(adapter.getView(i, null, layout));
            }
        }
    }

    public static void showOrHide(View viewToHide, String value) {
        viewToHide.setVisibility(isEmpty(value) ? View.GONE : View.VISIBLE);
    }

    public static void setViewVisibility(View primaryView, boolean visible) {
        primaryView.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    public static void setViewVisibility(View primaryView, View alternativeView, boolean showPrimary) {
        setViewVisibility(primaryView, showPrimary);
        setViewVisibility(alternativeView, !showPrimary);
    }
}
