package cat.mrtxema.crispetes.view.util;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.text.util.Linkify;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Adapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

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

    public static List<View> getViewsByTag(ViewGroup root, String tag){
        assert tag != null;
        List<View> views = new ArrayList<>();
        for (int i = 0; i < root.getChildCount(); i++) {
            final View child = root.getChildAt(i);
            if (child instanceof ViewGroup) {
                views.addAll(getViewsByTag(ViewGroup.class.cast(child), tag));
            }
            if (tag.equals(child.getTag())) {
                views.add(child);
            }
        }
        return views;
    }

    public static void linkifyTextViewsByTag(ViewGroup viewGroup, String tag) {
        for (View view : ViewUtils.getViewsByTag(viewGroup, tag)) {
            Linkify.addLinks(TextView.class.cast(view), Linkify.WEB_URLS);
        }
    }

    public static void showSnackBar(Activity activity, @StringRes int resId) {
        Snackbar.make(activity.findViewById(android.R.id.content), resId, Snackbar.LENGTH_LONG).show();
    }
}
