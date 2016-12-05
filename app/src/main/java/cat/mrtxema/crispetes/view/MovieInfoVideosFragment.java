package cat.mrtxema.crispetes.view;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import cat.mrtxema.crispetes.model.Link;
import cat.mrtxema.crispetes.model.LinkList;
import cat.mrtxema.crispetes.model.MovieDetails;
import cat.mrtxema.crispetes.model.VideoSource;
import cat.mrtxema.crispetes.model.VideoSourceDescriptor;
import cat.mrtxema.crispetes.service.VideoService;
import cat.mrtxema.crispetes.service.VideoServiceException;
import cat.mrtxema.crispetes.service.VideoSourceService;
import cat.mrtxema.crispetes.service.VideoSourceServiceException;
import cat.mrtxema.crispetes.view.adapter.LinkExpandableListAdapter;
import cat.mrtxema.crispetes.view.util.ViewUtils;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.SupposeBackground;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_movie_videos)
public class MovieInfoVideosFragment extends Fragment {
    @ViewById(R.id.loadingPanel)
    View loadingView;
    @ViewById(R.id.layoutNoSources)
    ViewGroup layoutNoSources;
    @ViewById(R.id.layoutSources)
    ViewGroup layoutSources;
    @ViewById(R.id.lstVideos)
    ExpandableListView lstVideos;

    @Bean
    VideoSourceService videoSourceService;
    @Bean
    VideoService videoService;

    private MovieDetails movieDetails;
    private List<VideoSource> videoSources;
    private LinkExpandableListAdapter adapter;


    @AfterViews
    void initViews() {
        ViewUtils.setViewVisibility(loadingView, true);
        lstVideos.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Link link = adapter.getChild(groupPosition, childPosition);
                //BeerDetailActivity_.intent(MovieInfoVideosFragment.this).beerInfo(beer).start();
                Toast.makeText(getContext(), String.format("link %d (%s)", link.getId(), link.getServer()), Toast.LENGTH_LONG);
                return true;
            }
        });
        retrieveData();
        ViewUtils.linkifyTextViewsByTag(layoutNoSources, "linkify");
    }

    @Background
    void retrieveData() {
        retrieveMovieDetails();
        retrieveVideos();
    }

    @SupposeBackground
    void retrieveMovieDetails() {
        movieDetails = MovieInfoActivity.class.cast(getActivity()).getMovieDetails();
    }

    @SupposeBackground
    void retrieveVideos() {
        try {
            videoSources = videoSourceService.retrieveAll();
        } catch (VideoSourceServiceException e) {
            Log.e(getClass().getSimpleName(), e.getMessage(), e);
        }
        List<Link> links = new ArrayList<>();
        for (VideoSource videoSource : videoSources) {
            try {
                links.addAll(videoService.getMovieLinks(movieDetails, videoSource));
            } catch (VideoServiceException e) {
                Log.e(getClass().getSimpleName(), e.getMessage(), e);
            }
        }
        LinkList linkList = new LinkList(links);
        showLinks(linkList);
        setViewsVisibility(!videoSources.isEmpty());
    }

    @UiThread
    void setViewsVisibility(boolean showPrimary) {
        ViewUtils.setViewVisibility(layoutSources, layoutNoSources, showPrimary);
        ViewUtils.setViewVisibility(loadingView, false);
    }

    @UiThread
    void showLinks(LinkList linkList) {
        adapter = new LinkExpandableListAdapter(getContext(), linkList);
        lstVideos.setAdapter(adapter);
    }

    @Click(R.id.btnAddSource)
    void addSourceClick() {
        new UrlInputDialog(getContext(), new DialogCallback() {
            @Override
            public void onResult(String inputText, boolean wasCancelled) {
                if (!wasCancelled) {
                    retrieveVideoSourceInfo(inputText);
                }
            }
        }).show();
    }

    @Background
    void retrieveVideoSourceInfo(String url) {
        try {
            showVideoSourceActivity(videoService.getVideoSourceInfo(url));
        } catch (Exception e) {
            Log.e(getClass().getSimpleName(), e.getMessage(), e);
            ViewUtils.showSnackBar(getActivity(), R.string.unreachable_plugin);
        }
    }

    @UiThread
    void showVideoSourceActivity(VideoSourceDescriptor videoSourceDescriptor) {
        VideoSourceActivity_.intent(MovieInfoVideosFragment.this).videoSourceDescriptor(videoSourceDescriptor).start();
    }
}
