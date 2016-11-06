package cat.mrtxema.crispetes.view;

import android.support.design.widget.Snackbar;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import cat.mrtxema.crispetes.model.WatchStatus;
import cat.mrtxema.crispetes.service.FavoriteMovieServiceException;
import com.github.aakira.expandablelayout.ExpandableLinearLayout;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import cat.mrtxema.crispetes.model.FavoriteMovie;
import cat.mrtxema.crispetes.model.MovieDetails;
import cat.mrtxema.crispetes.service.FavoriteMovieService;
import cat.mrtxema.crispetes.service.MovieService;
import cat.mrtxema.crispetes.service.MovieServiceException;
import cat.mrtxema.crispetes.view.adapter.CastListAdapter;
import cat.mrtxema.crispetes.view.adapter.CrewListAdapter;
import cat.mrtxema.crispetes.view.util.DateFormatter;
import cat.mrtxema.crispetes.view.util.StringFormatter;
import cat.mrtxema.crispetes.view.util.ViewUtils;
import org.androidannotations.annotations.res.StringRes;

@EActivity(R.layout.activity_movie_detail)
public class MovieInfoActivity extends BaseActivity {
    @Bean
    MovieService movieService;
    @Bean
    FavoriteMovieService favoriteMovieService;
    @Extra
    FavoriteMovie favoriteMovie;
    private MovieDetails movieDetails;

    @ViewById(R.id.imgPoster)
    ImageView imgPoster;
    @ViewById(R.id.imgBackdrop)
    ImageView imgBackdrop;
    @ViewById(R.id.txtTitle)
    TextView txtTitle;
    @ViewById(R.id.txtRelease)
    TextView txtRelease;
    @ViewById(R.id.txtTagline)
    TextView txtTagline;
    @ViewById(R.id.txtOverview)
    TextView txtOverview;
    @ViewById(R.id.txtHomepage)
    TextView txtHomepage;
    @ViewById(R.id.txtGenres)
    TextView txtGenres;
    @ViewById(R.id.txtOriginalTitle)
    TextView txtOriginalTitle;
    @ViewById(R.id.txtScoreValue)
    TextView txtScoreValue;
    @ViewById(R.id.txtRuntime)
    TextView txtRuntime;
    @ViewById(R.id.txtProductionCompany)
    TextView txtProductionCompany;
    @ViewById(R.id.txtProductionCountry)
    TextView txtProductionCountry;
    @ViewById(R.id.layoutCast)
    ExpandableLinearLayout layoutCast;
    @ViewById(R.id.layoutCrew)
    ExpandableLinearLayout layoutCrew;
    @ViewById(R.id.btnFavorite)
    ImageButton btnFavorite;
    @ViewById(R.id.btnSeen)
    ImageButton btnSeen;
    @ViewById(R.id.btnDelete)
    ImageButton btnDelete;

    @AfterViews
    protected void initViews() {
        retrieveMovieDetails(favoriteMovie.getMovie().getTmdbId());
        initButtons();
    }

    @UiThread
    void initButtons() {
        setViewVisibility(btnFavorite, favoriteMovie.getStatus() == WatchStatus.UNSAVED);
        setViewVisibility(btnSeen, favoriteMovie.getStatus() == WatchStatus.PENDING);
        setViewVisibility(btnDelete, favoriteMovie.getStatus() == WatchStatus.WATCHED);
    }

    @Background
    void retrieveMovieDetails(int tmdbId) {
        try {
            movieDetails = movieService.getMovieDetails(tmdbId);
            showMovieDetails(movieDetails);
        } catch (MovieServiceException e) {
            Log.e(getClass().getSimpleName(), "Error retrieving movie details", e);
        }
    }

    @UiThread
    void showMovieDetails(MovieDetails movieDetails) {
        Picasso.with(this).load(movieDetails.getPosterUrl()).placeholder(R.mipmap.ic_placeholder).into(imgPoster);
        Picasso.with(this).load(movieDetails.getBackdropUrl()).placeholder(R.mipmap.ic_placeholder).into(imgBackdrop);
        ViewUtils.showOrHide(imgBackdrop, movieDetails.getBackdropUrl());
        txtTitle.setText(movieDetails.getTitle());
        ViewUtils.setTextOrHideParent(txtRelease, DateFormatter.formatDate(movieDetails.getReleaseDate(), DateFormatter.FULL_DATE_FORMAT));
        ViewUtils.setTextOrHideParent(txtRuntime, isNull(movieDetails.getRuntime()) ? null : getString(R.string.n_minutes, movieDetails.getRuntime()));
        ViewUtils.setTextOrHideParent(txtOverview, movieDetails.getOverview());
        ViewUtils.setTextOrHide(txtTagline, movieDetails.getTagline());
        ViewUtils.setTextOrHide(txtHomepage, movieDetails.getHomepage());
        ViewUtils.setTextOrHideParent(txtGenres, StringFormatter.joinList(movieDetails.getGenres(), StringFormatter.COMMA_SEPARATOR));
        ViewUtils.setTextOrHideParent(txtOriginalTitle, movieDetails.getOriginalTitle());
        ViewUtils.setTextOrHideParent(txtScoreValue, isNull(movieDetails.getVoteCount()) ? null : getString(
                R.string.scoreValue,
                movieDetails.getVoteAverage(),
                movieDetails.getVoteCount()
        ));
        ViewUtils.setTextOrHideParent(txtProductionCompany, StringFormatter.joinList(movieDetails.getProductionCompanies(), StringFormatter.COMMA_SEPARATOR));
        ViewUtils.setTextOrHideParent(txtProductionCountry, StringFormatter.joinList(movieDetails.getProductionCountries(), StringFormatter.COMMA_SEPARATOR));
        ViewUtils.setAdapter(layoutCast, new CastListAdapter(this, movieDetails.getCastList()));
        ViewUtils.setAdapter(layoutCrew, new CrewListAdapter(this, movieDetails.getCrewList()));
    }

    private boolean isNull(Integer n) {
        return n == null || n == 0;
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

    @Click(R.id.imgPoster)
    void onPosterClick() {
        ImageActivity_.intent(this).url(movieDetails.getPosterUrl()).start();
    }

    @Click(R.id.imgBackdrop)
    void onBackdropClick() {
        ImageActivity_.intent(this).url(movieDetails.getBackdropUrl()).start();
    }

    @Click(R.id.btnCast)
    void onCastClick(Button btn) {
        int drawable = layoutCast.isExpanded() ? android.R.drawable.arrow_down_float : android.R.drawable.arrow_up_float;
        btn.setCompoundDrawablesWithIntrinsicBounds(0, 0, drawable, 0);
        layoutCast.toggle();
    }

    @Click(R.id.btnCrew)
    void onCrewClick(Button btn) {
        int drawable = layoutCrew.isExpanded() ? android.R.drawable.arrow_down_float : android.R.drawable.arrow_up_float;
        btn.setCompoundDrawablesWithIntrinsicBounds(0, 0, drawable, 0);
        layoutCrew.toggle();
    }
}
