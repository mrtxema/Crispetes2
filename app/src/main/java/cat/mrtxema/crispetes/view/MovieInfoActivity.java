package cat.mrtxema.crispetes.view;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import cat.mrtxema.crispetes.model.Crew;
import cat.mrtxema.crispetes.model.CrewJob;
import cat.mrtxema.crispetes.model.MovieDetails;
import cat.mrtxema.crispetes.service.MovieService;
import cat.mrtxema.crispetes.service.MovieServiceException;
import cat.mrtxema.crispetes.view.adapter.CastListAdapter;
import cat.mrtxema.crispetes.view.util.DateFormatter;
import cat.mrtxema.crispetes.view.util.StringFormatter;
import cat.mrtxema.crispetes.view.util.ViewUtils;

@EActivity(R.layout.activity_movie_detail)
public class MovieInfoActivity extends BaseActivity {
    @Bean
    MovieService movieService;
    @Extra
    Integer movieId;

    @ViewById
    ImageView imgPoster;
    @ViewById
    ImageView imgBackdrop;
    @ViewById
    TextView txtTitle;
    @ViewById
    TextView txtRelease;
    @ViewById
    TextView txtTagline;
    @ViewById
    TextView txtOverview;
    @ViewById
    TextView txtHomepage;
    @ViewById
    TextView txtGenres;
    @ViewById
    TextView txtOriginalTitle;
    @ViewById
    TextView txtScoreValue;
    @ViewById
    TextView txtStatus;
    @ViewById
    TextView txtProductionCompany;
    @ViewById
    TextView txtDirector;
    @ViewById
    ListView lstCast;


    @AfterViews
    protected void initViews() {
        retrieveMovieDetails(movieId);
    }

    @Background
    void retrieveMovieDetails(int movieId) {
        try {
            MovieDetails movieDetails = movieService.getMovieDetails(movieId);
            showMovieDetails(movieDetails);
        } catch (MovieServiceException e) {
            Log.e(getClass().getSimpleName(), "Error retrieving movie details", e);
        }
    }

    @UiThread
    void showMovieDetails(MovieDetails movieDetails) {
        Picasso.with(this).load(movieDetails.getPosterUrl()).placeholder(R.mipmap.ic_placeholder).into(imgPoster);
        Picasso.with(this).load(movieDetails.getBackdropUrl()).placeholder(R.mipmap.ic_placeholder).into(imgBackdrop);
        txtTitle.setText(movieDetails.getTitle());
        txtRelease.setText(DateFormatter.formatDate(movieDetails.getReleaseDate(), DateFormatter.FULL_DATE_FORMAT));
        txtStatus.setText(StringFormatter.addParenthesis(movieDetails.getStatus()));
        ViewUtils.setTextOrHide(txtOverview, movieDetails.getOverview());
        ViewUtils.setTextOrHide(txtTagline, movieDetails.getTagline());
        ViewUtils.setTextOrHide(txtHomepage, movieDetails.getHomepage());
        ViewUtils.setTextOrHideParent(txtGenres, StringFormatter.joinList(movieDetails.getGenres(), StringFormatter.COMMA_SEPARATOR));
        ViewUtils.setTextOrHideParent(txtOriginalTitle, movieDetails.getOriginalTitle());
        ViewUtils.setTextOrHideParent(txtScoreValue, movieDetails.getVoteAverage() == null ? null : getString(
                R.string.scoreValue,
                movieDetails.getVoteAverage(),
                movieDetails.getVoteCount()
        ));
        ViewUtils.setTextOrHideParent(txtProductionCompany, movieDetails.getProductionCompanies().isEmpty() ? null : getString(
                R.string.productionCompanyValue,
                StringFormatter.joinList(movieDetails.getProductionCompanies(), StringFormatter.COMMA_SEPARATOR),
                StringFormatter.joinList(movieDetails.getProductionCountries(), StringFormatter.COMMA_SEPARATOR)
         ));
        Crew director = movieDetails.getCrewItem(CrewJob.DIRECTOR);
        ViewUtils.setTextOrHideParent(txtDirector, director == null ? null : director.getName());
        lstCast.setAdapter(new CastListAdapter(this, movieDetails.getCastList()));
    }
}
