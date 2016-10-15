package cat.mrtxema.crispetes.view;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import cat.mrtxema.crispetes.model.Cast;
import cat.mrtxema.crispetes.model.Crew;
import cat.mrtxema.crispetes.model.MovieDetails;
import cat.mrtxema.crispetes.service.MovieService;
import cat.mrtxema.crispetes.service.MovieServiceException;
import cat.mrtxema.crispetes.view.util.DateFormatter;

@EActivity(R.layout.activity_movie_detail)
@OptionsMenu(R.menu.menu_sample)
public class MovieInfoActivity extends AppCompatActivity {
    @Bean
    MovieService movieService;
    @ViewById(R.id.toolbar)
    Toolbar toolbar;
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
    TextView txtOriginalLanguage;
    @ViewById
    TextView txtOriginalTitle;
    @ViewById
    TextView txtVoteCount;
    @ViewById
    TextView txtVoteAverage;
    @ViewById
    TextView txtStatus;
    @ViewById
    TextView txtProductionCountries;
    @ViewById
    TextView txtProductionCompanies;
    @ViewById
    ListView lstCast;
    @ViewById
    ListView lstCrew;


    @AfterViews
    protected void initViews() {
        setSupportActionBar(toolbar);
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
        txtOverview.setText(movieDetails.getOverview());
        txtRelease.setText(DateFormatter.formatDate(movieDetails.getReleaseDate(), "dd/MM/yyyy"));
        txtTagline.setText(movieDetails.getTagline());
        txtHomepage.setText(movieDetails.getHomepage());
        txtOriginalLanguage.setText(movieDetails.getOriginalLanguage());
        txtOriginalTitle.setText(movieDetails.getOriginalTitle());
        txtVoteCount.setText(movieDetails.getVoteCount().toString());
        txtVoteAverage.setText(movieDetails.getVoteAverage().toString());
        txtStatus.setText(movieDetails.getStatus());
        txtProductionCountries.setText(formatList(movieDetails.getProductionCountries()));
        txtProductionCompanies.setText(formatList(movieDetails.getProductionCompanies()));
        lstCast.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, movieDetails.getCast()));
        lstCrew.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, movieDetails.getCrew()));
    }

    private String formatList(List<String> stringList) {
        return stringList.toString();
    }

}
