package cat.mrtxema.crispetes.view;

import android.support.design.widget.TabLayout;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import java.util.List;
import cat.mrtxema.crispetes.model.WatchStatus;
import cat.mrtxema.crispetes.service.FavoriteMovieServiceException;
import cat.mrtxema.crispetes.model.FavoriteMovie;
import cat.mrtxema.crispetes.service.FavoriteMovieService;
import cat.mrtxema.crispetes.view.adapter.FavoriteMovieListAdapter;
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
    @Bean
    FavoriteMovieService favoriteMovieService;
    @ViewById(R.id.lstFavoriteMovies)
    ListView lstFavoriteMovies;
    @ViewById(R.id.txtNoResults)
    TextView txtNoResults;
    @ViewById(R.id.tabs)
    TabLayout tabLayout;
    private FavoriteMovieListAdapter movieListAdapter;

    @Override
    protected boolean isMainActivity() {
        return true;
    }

    @AfterViews
    void initTabs() {
        tabLayout.addTab(tabLayout.newTab().setText(R.string.pending).setTag(WatchStatus.PENDING));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.watched).setTag(WatchStatus.WATCHED));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                retrieveFavoriteMovies();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        retrieveFavoriteMovies();
    }

    @Background
    void retrieveFavoriteMovies() {
        try {
            WatchStatus status = WatchStatus.class.cast(tabLayout.getTabAt(tabLayout.getSelectedTabPosition()).getTag());
            List<FavoriteMovie> favoriteMovies = favoriteMovieService.retrieveByStatus(status);
            showFavoriteMovies(favoriteMovies);
        } catch (FavoriteMovieServiceException e) {
            Log.e(getClass().getSimpleName(), "Error retrieving favorite movies", e);
        }
    }

    @UiThread
    void showFavoriteMovies(List<FavoriteMovie> favoriteMovies) {
        movieListAdapter = new FavoriteMovieListAdapter(this, favoriteMovies);
        lstFavoriteMovies.setAdapter(movieListAdapter);
        setViewVisibility(lstFavoriteMovies, txtNoResults, !favoriteMovies.isEmpty());
    }

    @Click(R.id.btnAdd)
    void onClickAdd() {
        SearchMovieActivity_.intent(this).start();
    }

    @ItemClick(R.id.lstFavoriteMovies)
    void onMovieClick(FavoriteMovie favoriteMovie) {
        MovieInfoActivity_.intent(this).favoriteMovie(favoriteMovie).start();
    }
}
