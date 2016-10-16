package cat.mrtxema.crispetes.view.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import java.util.List;

public abstract class BaseListAdapter<T> extends ArrayAdapter<T> {
    private final @LayoutRes int resource;

    public BaseListAdapter(Context context, @LayoutRes int resource, @NonNull List<T> items) {
        super(context, resource, items);
        this.resource = resource;
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        View view = LayoutInflater.from(getContext()).inflate(this.resource, null, true);
        populateView(view, getItem(position));
        return view;
    }

    protected abstract void populateView(View view, T item);
}
