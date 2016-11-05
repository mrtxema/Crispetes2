package cat.mrtxema.crispetes.view;

import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
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
import cat.mrtxema.crispetes.model.SearchResponse;
import cat.mrtxema.crispetes.service.MovieService;
import cat.mrtxema.crispetes.service.MovieServiceException;
import cat.mrtxema.crispetes.view.adapter.EndlessScrollListener;
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
    private MovieListAdapter movieListAdapter;
    private MovieScrollListener scrollListener;
    private String searchString;
    private int totalPages;

    private class MovieScrollListener extends EndlessScrollListener {
        public MovieScrollListener() {
            super(5, 0);
        }

        @Override
        public boolean onLoadMore(int page, int totalItemsCount) {
            if (page > 1 && page <= totalPages) {
                paginateSearch(page);
                return true;
            }
            return false;
        }
    }

    @AfterViews
    protected void bindScrollListener() {
        scrollListener = new MovieScrollListener();
        lstMovies.setOnScrollListener(scrollListener);
    }

    @EditorAction(R.id.txtMovie)
    boolean onSearchClick(int actionId) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            final String searchString = txtMovie.getText().toString().trim();
            if (!searchString.isEmpty()) {
                hideKeyboard();
                performMovieSearch(searchString);
            }
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
            searchString = title;
            SearchResponse<Movie> response = movieService.search(searchString, 1);
            totalPages = response.getTotalPages();
            showMovies(response.getResults(), true);
        } catch (MovieServiceException e) {
            Log.e(getClass().getSimpleName(), "Error searching movies", e);
        }
    }

    @UiThread
    void showMovies(List<Movie> movies, boolean searchPerformed) {
        movieListAdapter = new MovieListAdapter(this, movies);
        lstMovies.setAdapter(movieListAdapter);
        scrollListener.resetState();
        if (searchPerformed && movies.isEmpty()) {
            noResultsMsg.setVisibility(View.VISIBLE);
        } else {
            noResultsMsg.setVisibility(View.GONE);
        }
    }

    @Background
    void paginateSearch(int page) {
        try {
            SearchResponse<Movie> response = movieService.search(searchString, page);
            totalPages = response.getTotalPages();
            appendMovies(response.getResults());
        } catch (MovieServiceException e) {
            Log.e(getClass().getSimpleName(), "Error searching movies", e);
        }
    }

    @UiThread
    void appendMovies(List<Movie> movies) {
        if (!movies.isEmpty()) {
            movieListAdapter.addAll(movies);
        }
    }

    @ItemClick(R.id.lstMovies)
    void onMovieClick(Movie movie) {
        MovieInfoActivity_.intent(this).movie(movie).start();
    }
}
