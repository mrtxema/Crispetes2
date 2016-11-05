package cat.mrtxema.crispetes.view;

import android.widget.ListView;
import android.widget.TextView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ItemClick;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import cat.mrtxema.crispetes.model.FavoriteMovie;
import cat.mrtxema.crispetes.model.Movie;
import cat.mrtxema.crispetes.service.FavoriteMovieService;
import cat.mrtxema.crispetes.service.converter.Converter;
import cat.mrtxema.crispetes.service.converter.ListConverter;
import cat.mrtxema.crispetes.view.adapter.MovieListAdapter;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {
    @Bean
    FavoriteMovieService favoriteMovieService;
    @ViewById(R.id.lstFavoriteMovies)
    ListView lstFavoriteMovies;
    @ViewById(R.id.txtNoResults)
    TextView txtNoResults;
    private MovieListAdapter movieListAdapter;

    protected boolean isMainActivity() {
        return true;
    }

    protected void onResume() {
        super.onResume();
        retrieveFavoriteMovies();
    }

    @Background
    void retrieveFavoriteMovies() {
        List<FavoriteMovie> favoriteMovies = favoriteMovieService.retrieveAll();
        List<Movie> movies = new ListConverter<>(new Converter<FavoriteMovie, Movie>() {
            @Override
            public Movie convert(FavoriteMovie favoriteMovie) {
                return favoriteMovie.getMovie();
            }
        }).convert(favoriteMovies);
        showFavoriteMovies(movies);
    }

    @UiThread
    void showFavoriteMovies(List<Movie> movies) {
        movieListAdapter = new MovieListAdapter(this, movies);
        lstFavoriteMovies.setAdapter(movieListAdapter);
        setViewVisibility(lstFavoriteMovies, txtNoResults, !movies.isEmpty());
    }

    @Click(R.id.btnAdd)
    void onClickAdd() {
        SearchMovieActivity_.intent(this).start();
    }

    @ItemClick(R.id.lstFavoriteMovies)
    void onMovieClick(Movie movie) {
        MovieInfoActivity_.intent(this).movie(movie).start();
    }
}
