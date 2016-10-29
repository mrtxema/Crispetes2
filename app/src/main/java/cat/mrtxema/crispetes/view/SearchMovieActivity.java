package cat.mrtxema.crispetes.view;

import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.EditorAction;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.Collections;
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
    @ViewById(R.id.noResults)
    TextView noResultsMsg;

    @EditorAction(R.id.txtMovie)
    boolean onSearchClick(int actionId) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            hideKeyboard();
            performMovieSearch(txtMovie.getText().toString());
            return true;
        }
        return false;
    }

    @Click(R.id.btnClearSearch)
    void onClearSearch() {
        txtMovie.setText("");
        showMovies(Collections.<Movie>emptyList(), false);
    }

    @Background
    void performMovieSearch(String title) {
        try {
            List<Movie> movies = movieService.search(title);
            showMovies(movies, true);
        } catch (MovieServiceException e) {
            Log.e(getClass().getSimpleName(), "Error searching movies", e);
        }
    }

    @UiThread
    void showMovies(List<Movie> movies, boolean searchPerformed) {
        lstMovies.setAdapter(new MovieListAdapter(this, movies));
        if (searchPerformed && movies.isEmpty()) {
            noResultsMsg.setVisibility(View.VISIBLE);
        } else {
            noResultsMsg.setVisibility(View.GONE);
        }
    }

    @ItemClick(R.id.lstMovies)
    void onMovieClick(Movie movie) {
        MovieInfoActivity_.intent(this).movieId(movie.getId()).start();
    }
}
