package cat.mrtxema.crispetes.view;

import android.support.design.widget.Snackbar;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.res.StringRes;

import java.util.List;

import cat.mrtxema.crispetes.model.Movie;
import cat.mrtxema.crispetes.service.MovieService;
import cat.mrtxema.crispetes.service.MovieServiceException;
import cat.mrtxema.crispetes.view.adapter.MovieListAdapter;

@EActivity(R.layout.activity_search)
public class SearchMovieActivity extends BaseActivity {
    @Bean
    MovieService movieService;
    @ViewById(R.id.txtMovie)
    EditText txtMovie;
    @ViewById(R.id.lstMovies)
    ListView lstMovies;
    @StringRes(R.string.noresults)
    String noResults;

    @Click(R.id.btnSearchMovie)
    void onSearchButtonClick() {
        hideKeyboard();
        performMovieSearch(txtMovie.getText().toString());
    }

    @Background
    void performMovieSearch(String title) {
        try {
            List<Movie> movies = movieService.search(title);
            showMovies(movies);
        } catch (MovieServiceException e) {
            Log.e(getClass().getSimpleName(), "Error searching movies", e);
        }
    }

    @UiThread
    void showMovies(List<Movie> movies) {
        lstMovies.setAdapter(new MovieListAdapter(this, movies));
        if (movies.isEmpty()) {
            Snackbar.make(lstMovies, noResults, Snackbar.LENGTH_LONG).setAction("TODO", null).show();
        }

    }

    @ItemClick(R.id.lstMovies)
    void onMovieClick(Movie movie) {
        MovieInfoActivity_.intent(this).movieId(movie.getId()).start();
    }
}
