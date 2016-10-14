package cat.mrtxema.crispetes.view;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
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
    TextView txtTitle;
    @ViewById
    TextView txtOverview;
    @ViewById
    TextView txtRelease;
    @ViewById
    ImageView imgPoster;

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
        txtTitle.setText(movieDetails.getTitle());
        txtOverview.setText(movieDetails.getOverview());
        txtRelease.setText(DateFormatter.formatDate(movieDetails.getReleaseDate(), "dd/MM/yyyy"));
        Picasso
            .with(this)
            .load(movieDetails.getPosterUrl())
            .placeholder(R.mipmap.ic_placeholder)
            .into(imgPoster);
    }

}
