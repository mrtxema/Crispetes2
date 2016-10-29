package cat.mrtxema.crispetes.view;

import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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

import cat.mrtxema.crispetes.model.MovieDetails;
import cat.mrtxema.crispetes.service.MovieService;
import cat.mrtxema.crispetes.service.MovieServiceException;
import cat.mrtxema.crispetes.view.adapter.CastListAdapter;
import cat.mrtxema.crispetes.view.adapter.CrewListAdapter;
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
    TextView txtRuntime;
    @ViewById
    TextView txtProductionCompany;
    @ViewById
    TextView txtProductionCountry;
    @ViewById
    ExpandableLinearLayout layoutCast;
    @ViewById
    ExpandableLinearLayout layoutCrew;


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
        ViewUtils.setTextOrHideParent(txtRelease, DateFormatter.formatDate(movieDetails.getReleaseDate(), DateFormatter.FULL_DATE_FORMAT));
        ViewUtils.setTextOrHideParent(txtRuntime, isNull(movieDetails.getRuntime()) ? null : getString(R.string.n_minutes, movieDetails.getRuntime()));
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
        ViewUtils.setTextOrHideParent(txtProductionCompany, StringFormatter.joinList(movieDetails.getProductionCompanies(), StringFormatter.COMMA_SEPARATOR));
        ViewUtils.setTextOrHideParent(txtProductionCountry, StringFormatter.joinList(movieDetails.getProductionCountries(), StringFormatter.COMMA_SEPARATOR));
        ViewUtils.setAdapter(layoutCast, new CastListAdapter(this, movieDetails.getCastList()));
        ViewUtils.setAdapter(layoutCrew, new CrewListAdapter(this, movieDetails.getCrewList()));
    }

    private boolean isNull(Integer n) {
        return n == null || n == 0;
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
