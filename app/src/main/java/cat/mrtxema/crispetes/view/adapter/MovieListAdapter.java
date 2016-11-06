package cat.mrtxema.crispetes.view.adapter;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import cat.mrtxema.crispetes.model.Movie;

public class MovieListAdapter extends GenericMovieListAdapter<Movie> {

    public MovieListAdapter(Context context, List<Movie> movies) {
        super(context, new ArrayList<>(movies));
    }

    @Override
    protected Movie extractMovie(Movie movie) {
        return movie;
    }
}
