package cat.mrtxema.crispetes.view;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import java.util.List;
import cat.mrtxema.crispetes.model.FavoriteMovie;
import cat.mrtxema.crispetes.model.WatchStatus;
import cat.mrtxema.crispetes.service.FavoriteMovieService;
import cat.mrtxema.crispetes.service.FavoriteMovieServiceException;
import cat.mrtxema.crispetes.view.adapter.FavoriteMovieListAdapter;
import cat.mrtxema.crispetes.view.util.ViewUtils;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_main)
public class FavoriteMovieFragment extends Fragment {
    private WatchStatus watchStatus;
    @Bean
    FavoriteMovieService favoriteMovieService;
    @ViewById(R.id.lstFavoriteMovies)
    ListView lstFavoriteMovies;
    @ViewById(R.id.txtNoResults)
    TextView txtNoResults;
    private FavoriteMovieListAdapter movieListAdapter;

    @Override
    public void onResume() {
        super.onResume();
        retrieveFavoriteMovies();
    }

    @Background
    void retrieveFavoriteMovies() {
        try {
            WatchStatus status = retrieveWatchStatus();
            List<FavoriteMovie> favoriteMovies = favoriteMovieService.retrieveByStatus(status);
            showFavoriteMovies(favoriteMovies);
        } catch (FavoriteMovieServiceException e) {
            Log.e(getClass().getSimpleName(), "Error retrieving favorite movies", e);
        }
    }

    private WatchStatus retrieveWatchStatus() {
        return WatchStatus.valueOf(getArguments().getString("watchStatus"));
    }

    @UiThread
    void showFavoriteMovies(List<FavoriteMovie> favoriteMovies) {
        movieListAdapter = new FavoriteMovieListAdapter(getContext(), favoriteMovies);
        lstFavoriteMovies.setAdapter(movieListAdapter);
        ViewUtils.setViewVisibility(lstFavoriteMovies, txtNoResults, !favoriteMovies.isEmpty());
    }

    @ItemClick(R.id.lstFavoriteMovies)
    void onMovieClick(FavoriteMovie favoriteMovie) {
        MovieInfoActivity_.intent(this).favoriteMovie(favoriteMovie).start();
    }
}
