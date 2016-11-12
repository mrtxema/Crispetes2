package cat.mrtxema.crispetes.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import java.util.List;
import cat.mrtxema.crispetes.model.WatchStatus;
import cat.mrtxema.crispetes.service.FavoriteMovieServiceException;
import cat.mrtxema.crispetes.model.FavoriteMovie;
import cat.mrtxema.crispetes.service.FavoriteMovieService;
import cat.mrtxema.crispetes.view.adapter.FavoriteMovieListAdapter;
import cat.mrtxema.crispetes.view.adapter.ViewPagerAdapter;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {
    @ViewById(R.id.tabs)
    TabLayout tabLayout;
    @ViewById(R.id.viewpager)
    ViewPager viewPager;

    @Override
    protected boolean isMainActivity() {
        return true;
    }

    @AfterViews
    void initTabs() {
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(buildFavoriteMovieFragment_(WatchStatus.PENDING), getString(R.string.pending));
        adapter.addFragment(buildFavoriteMovieFragment_(WatchStatus.WATCHED), getString(R.string.watched));
        viewPager.setAdapter(adapter);
    }

    private Fragment buildFavoriteMovieFragment_(WatchStatus watchStatus) {
        Fragment result = new FavoriteMovieFragment_();
        Bundle arguments = new Bundle();
        arguments.putString("watchStatus", watchStatus.name());
        result.setArguments(arguments);
        return result;
    }

    @Click(R.id.btnAdd)
    void onClickAdd() {
        SearchMovieActivity_.intent(this).start();
    }
}
