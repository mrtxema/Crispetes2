package cat.mrtxema.crispetes.view;

import android.support.v4.app.Fragment;
import android.widget.ImageView;
import android.widget.TextView;
import cat.mrtxema.crispetes.model.MovieDetails;
import cat.mrtxema.crispetes.view.util.DateFormatter;
import cat.mrtxema.crispetes.view.util.StringFormatter;
import cat.mrtxema.crispetes.view.util.ViewUtils;
import com.squareup.picasso.Picasso;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_movie_detail)
public class MovieInfoDetailsFragment extends Fragment {
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


    @AfterViews
    void initViews() {
        retrieveMovieDetails();
    }

    @Background
    void retrieveMovieDetails() {
        movieDetails = MovieInfoActivity.class.cast(getActivity()).getMovieDetails();
        showMovieDetails();
    }

    @UiThread
    void showMovieDetails() {
        Picasso.with(getContext()).load(movieDetails.getPosterUrl()).placeholder(R.mipmap.ic_placeholder).into(imgPoster);
        Picasso.with(getContext()).load(movieDetails.getBackdropUrl()).placeholder(R.mipmap.ic_placeholder).into(imgBackdrop);
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
    }

    private boolean isNull(Integer n) {
        return n == null || n == 0;
    }

    @Click(R.id.imgPoster)
    void onPosterClick() {
        ImageActivity_.intent(this).url(movieDetails.getPosterUrl()).start();
    }

    @Click(R.id.imgBackdrop)
    void onBackdropClick() {
        ImageActivity_.intent(this).url(movieDetails.getBackdropUrl()).start();
    }
}
