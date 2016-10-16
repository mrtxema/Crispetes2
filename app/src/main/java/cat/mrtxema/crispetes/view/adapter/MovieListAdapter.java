package cat.mrtxema.crispetes.view.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;
import cat.mrtxema.crispetes.view.R;
import cat.mrtxema.crispetes.model.Movie;
import cat.mrtxema.crispetes.view.util.DateFormatter;

public class MovieListAdapter extends BaseListAdapter<Movie> {

    public MovieListAdapter(Context context, List<Movie> movies) {
        super(context, R.layout.listitem_movie, movies);
    }

    @Override
    protected void populateView(View view, Movie movie) {
        ((TextView) view.findViewById(R.id.txtTitle)).setText(movie.getTitle());
        ((TextView) view.findViewById(R.id.txtOverview)).setText(movie.getOverview());
        ((TextView) view.findViewById(R.id.txtRelease)).setText(DateFormatter.formatDate(movie.getReleaseDate(), DateFormatter.YEAR_FORMAT));
        ImageView imgPoster = (ImageView) view.findViewById(R.id.imgPoster);
        Picasso.with(getContext()).load(movie.getPosterUrl()).placeholder(R.mipmap.ic_placeholder).into(imgPoster);
    }
}
