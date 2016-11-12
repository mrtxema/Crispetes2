package cat.mrtxema.crispetes.view;

import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.ImageButton;

import cat.mrtxema.crispetes.model.WatchStatus;
import cat.mrtxema.crispetes.service.FavoriteMovieServiceException;

import cat.mrtxema.crispetes.view.adapter.ViewPagerAdapter;
import cat.mrtxema.crispetes.view.util.ViewUtils;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.SupposeBackground;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import cat.mrtxema.crispetes.model.FavoriteMovie;
import cat.mrtxema.crispetes.model.MovieDetails;
import cat.mrtxema.crispetes.service.FavoriteMovieService;
import cat.mrtxema.crispetes.service.MovieService;
import cat.mrtxema.crispetes.service.MovieServiceException;

@EActivity(R.layout.activity_movie_detail)
public class MovieInfoActivity extends BaseActivity {
    @Bean
    MovieService movieService;
    @Bean
    FavoriteMovieService favoriteMovieService;

    @Extra
    FavoriteMovie favoriteMovie;
    private MovieDetails movieDetails;

    @ViewById(R.id.tabs)
    TabLayout tabLayout;
    @ViewById(R.id.viewpager)
    ViewPager viewPager;
    @ViewById(R.id.btnFavorite)
    ImageButton btnFavorite;
    @ViewById(R.id.btnSeen)
    ImageButton btnSeen;
    @ViewById(R.id.btnDelete)
    ImageButton btnDelete;


    @AfterViews
    protected void initViews() {
        setTitle(favoriteMovie.getMovie().getTitle());
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        initButtons();
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new MovieInfoDetailsFragment_(), getString(R.string.tab_datasheet));
        adapter.addFragment(new MovieInfoCastFragment_(), getString(R.string.tab_cast));
        adapter.addFragment(new MovieInfoVideosFragment_(), getString(R.string.tab_videos));
        viewPager.setAdapter(adapter);
    }

    @UiThread
    void initButtons() {
        ViewUtils.setViewVisibility(btnFavorite, favoriteMovie.getStatus() == WatchStatus.UNSAVED);
        ViewUtils.setViewVisibility(btnSeen, favoriteMovie.getStatus() == WatchStatus.PENDING);
        ViewUtils.setViewVisibility(btnDelete, favoriteMovie.getStatus() == WatchStatus.WATCHED);
    }

    @SupposeBackground
    void retrieveMovieDetails() {
        int tmdbId = favoriteMovie.getMovie().getTmdbId();
        try {
            movieDetails = movieService.getMovieDetails(tmdbId);
        } catch (MovieServiceException e) {
            Log.e(getClass().getSimpleName(), "Error retrieving movie details", e);
        }
    }

    @SupposeBackground
    MovieDetails getMovieDetails() {
        if (movieDetails == null) {
            retrieveMovieDetails();
        }
        return movieDetails;
    }

    @Click(R.id.btnFavorite)
    void onFavoriteClick() {
        saveFavoriteMovie();
    }

    @Click(R.id.btnSeen)
    void onSeenClick() {
        tagFavoriteMovieAsWatched();
    }

    @Click(R.id.btnDelete)
    void onDeleteClick() {
        deleteFavoriteMovie();
    }

    @Background
    void saveFavoriteMovie() {
        try {
            favoriteMovie = favoriteMovieService.save(favoriteMovie);
            initButtons();
            Snackbar.make(btnFavorite, R.string.movie_saved, Snackbar.LENGTH_LONG).show();
        } catch (FavoriteMovieServiceException e) {
            Log.e(getClass().getSimpleName(), "Error saving favorite movie", e);
        }
    }

    @Background
    void tagFavoriteMovieAsWatched() {
        try {
            favoriteMovie = favoriteMovieService.updateStatus(favoriteMovie, WatchStatus.WATCHED);
            initButtons();
            Snackbar.make(btnFavorite, R.string.movie_watched , Snackbar.LENGTH_LONG).show();
        } catch (FavoriteMovieServiceException e) {
            Log.e(getClass().getSimpleName(), "Error updating favorite movie", e);
        }
    }

    @Background
    void deleteFavoriteMovie() {
        try {
            favoriteMovie = favoriteMovieService.delete(favoriteMovie);
            initButtons();
            Snackbar.make(btnFavorite, R.string.movie_deleted, Snackbar.LENGTH_LONG).show();
        } catch (FavoriteMovieServiceException e) {
            Log.e(getClass().getSimpleName(), "Error updating favorite movie", e);
        }
    }
}
