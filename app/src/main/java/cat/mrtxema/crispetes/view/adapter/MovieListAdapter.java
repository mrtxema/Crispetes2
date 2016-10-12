package cat.mrtxema.crispetes.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cat.mrtxema.crispetes.view.R;
import cat.mrtxema.crispetes.model.Movie;

public class MovieListAdapter extends ArrayAdapter<Movie> {

    public MovieListAdapter(Context context, List<Movie> movies) {
        super(context, R.layout.listitem_movie, movies);
    }

    public View getView(int position, View v, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.listitem_movie, null, true);
        Movie movie = getItem(position);

        ((TextView) view.findViewById(R.id.txtTitle)).setText(movie.getTitle());
        ((TextView) view.findViewById(R.id.txtOverview)).setText(movie.getOverview());
        ((TextView) view.findViewById(R.id.txtRelease)).setText(formatDate(movie.getReleaseDate(), "(yyyy)"));
        Picasso.with(getContext()).load(movie.getPosterUrl()).into((ImageView) view.findViewById(R.id.imgPoster));

        return view;
    }

    private String formatDate(Date releaseDate, String format) {
        //TODO: cache formatters?
        return new SimpleDateFormat(format).format(releaseDate);
    }
}
