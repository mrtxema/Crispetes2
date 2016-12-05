package cat.mrtxema.crispetes.view.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import cat.mrtxema.crispetes.model.Link;
import cat.mrtxema.crispetes.model.LinkList;
import cat.mrtxema.crispetes.model.VideoSource;
import cat.mrtxema.crispetes.view.R;

public class LinkExpandableListAdapter extends ExpandableListAdapter<VideoSource,Link> {
    private final Context context;

    public LinkExpandableListAdapter(Context context, LinkList linkList) {
        super(context, linkList.getVideoSources(), linkList.getBeersByBrewer(), R.layout.group_videosource, R.layout.listitem_link);
        this.context = context;
    }

    @Override
    protected void initParentView(View view, VideoSource videoSource) {
        ((TextView) view.findViewById(R.id.txtVideoSource)).setText(videoSource.getName());
    }

    @Override
    protected void initChildView(View view, Link link) {
        ((TextView) view.findViewById(R.id.title)).setText(link.getServer());
        ((TextView) view.findViewById(R.id.sidenote)).setText(buildAudioSubtitlesLanguageDescription(link));
        ((TextView) view.findViewById(R.id.subtitle)).setText(buildVideoAudioQualityDescription(link));
    }

    private CharSequence buildAudioSubtitlesLanguageDescription(Link link) {
        StringBuffer result = new StringBuffer();
        if (link.getAudioLanguage() != null) {
            result.append(context.getText(R.string.audio));
            result.append(' ');
            result.append(link.getAudioLanguage().getDescription());
        }
        if (link.isSubtitles() && link.getSubtitleLanguage() != null) {
            if (result.length() > 0) {
                result.append(", ");
            }
            result.append(context.getText(R.string.subtitles));
            result.append(' ');
            result.append(link.getSubtitleLanguage().getDescription());
        }
        return result;
    }

    private CharSequence buildVideoAudioQualityDescription(Link link) {
        final CharSequence qualityWord = context.getText(R.string.quality);
        StringBuffer result = new StringBuffer();
        if (link.getVideoQuality() != null) {
            result.append(context.getText(R.string.video));
            result.append(' ');
            result.append(link.getVideoQuality().getExtendedDescription(qualityWord));
        }
        if (link.getAudioQuality() != null) {
            if (result.length() > 0) {
                result.append(", ");
            }
            result.append(context.getText(R.string.audio));
            result.append(' ');
            result.append(link.getAudioQuality().getExtendedDescription(qualityWord));
        }
        return result;
    }
}
