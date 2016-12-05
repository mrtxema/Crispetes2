package cat.mrtxema.crispetes.view;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.List;
import cat.mrtxema.crispetes.model.VideoSource;
import cat.mrtxema.crispetes.model.VideoSourceDescriptor;
import cat.mrtxema.crispetes.service.VideoService;
import cat.mrtxema.crispetes.service.VideoSourceService;
import cat.mrtxema.crispetes.service.VideoSourceServiceException;
import cat.mrtxema.crispetes.view.adapter.VideoSourceListAdapter;
import cat.mrtxema.crispetes.view.util.ViewUtils;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_videsource_list)
public class VideoSourceListActivity extends BaseActivity {
    @Bean
    VideoSourceService videoSourceService;
    @Bean
    VideoService videoService;

    @ViewById(R.id.loadingPanel)
    View loadingView;
    @ViewById(R.id.layoutNoSources)
    ViewGroup layoutNoSources;
    @ViewById(R.id.layoutSources)
    ViewGroup layoutSources;
    @ViewById(R.id.lstVideoSources)
    ListView lstVideoSources;


    @AfterViews
    void initViews() {
        retrieveVideoSources();
        ViewUtils.linkifyTextViewsByTag(layoutNoSources, "linkify");
    }

    @Background
    void retrieveVideoSources() {
        try {
            List<VideoSource> sources = videoSourceService.retrieveAll();
            showVideoSources(sources);
        } catch(VideoSourceServiceException e) {
            Log.e(getClass().getSimpleName(), e.getMessage(), e);
        }
    }

    @UiThread
    void showVideoSources(List<VideoSource> sources) {
        ViewUtils.setViewVisibility(layoutSources, layoutNoSources, !sources.isEmpty());
        lstVideoSources.setAdapter(new VideoSourceListAdapter(this, sources));
    }

    @ItemClick(R.id.lstVideoSources)
    void onVideoSourceClick(VideoSource videoSource) {
        ViewUtils.setViewVisibility(loadingView, true);
        retrieveVideoSourceInfoAndEdit(videoSource.getBaseUrl(), videoSource);
    }

    @Click({R.id.btnAddSource, R.id.floatBtnAddSource})
    void addSourceClick() {
        new UrlInputDialog(this, new DialogCallback() {
            @Override
            public void onResult(String inputText, boolean wasCancelled) {
                if (!wasCancelled) {
                    ViewUtils.setViewVisibility(loadingView, true);
                    retrieveVideoSourceInfoAndEdit(inputText, null);
                }
            }
        }).show();
    }

    @Background
    void retrieveVideoSourceInfoAndEdit(String url, VideoSource videoSource) {
        try {
            showVideoSourceActivity(videoService.getVideoSourceInfo(url), videoSource);
        } catch (Exception e) {
            Log.e(getClass().getSimpleName(), e.getMessage(), e);
            showPluginError();
        }
    }

    @UiThread
    void showVideoSourceActivity(VideoSourceDescriptor videoSourceDescriptor, VideoSource videoSource) {
        ViewUtils.setViewVisibility(loadingView, false);
        VideoSourceActivity_
                .intent(VideoSourceListActivity.this)
                .videoSourceDescriptor(videoSourceDescriptor)
                .videoSource(videoSource)
                .start();
    }

    @UiThread
    void showPluginError() {
        ViewUtils.setViewVisibility(loadingView, false);
        ViewUtils.showSnackBar(this, R.string.unreachable_plugin);
    }
}
