package cat.mrtxema.crispetes.view.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import java.util.List;
import cat.mrtxema.crispetes.model.VideoSource;
import cat.mrtxema.crispetes.view.R;

public class VideoSourceListAdapter extends BaseListAdapter<VideoSource> {

    public VideoSourceListAdapter(Context context, List<VideoSource> videoSourceList) {
        super(context, R.layout.listitem_simple, videoSourceList);
    }

    @Override
    protected void populateView(View view, VideoSource item) {
        TextView.class.cast(view.findViewById(R.id.txt)).setText(item.getName());
    }
}
