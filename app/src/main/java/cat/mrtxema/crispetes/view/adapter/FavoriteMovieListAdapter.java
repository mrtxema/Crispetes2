package cat.mrtxema.crispetes.view.adapter;

import android.content.Context;
import java.util.List;
import cat.mrtxema.crispetes.model.FavoriteMovie;
import cat.mrtxema.crispetes.model.Movie;

public class FavoriteMovieListAdapter extends GenericMovieListAdapter<FavoriteMovie> {

    public FavoriteMovieListAdapter(Context context, List<FavoriteMovie> movies) {
        super(context, movies);
    }

    @Override
    protected Movie extractMovie(FavoriteMovie favoriteMovie) {
        return favoriteMovie.getMovie();
    }
}
