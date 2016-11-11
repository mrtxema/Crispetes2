package cat.mrtxema.crispetes.view;

import android.support.v4.app.Fragment;
import cat.mrtxema.crispetes.model.MovieDetails;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;

@EFragment(R.layout.fragment_movie_videos)
public class MovieInfoVideosFragment extends Fragment {
    private MovieDetails movieDetails;

    @AfterViews
    void initViews() {
        retrieveMovieDetails();
    }

    @Background
    void retrieveMovieDetails() {
        movieDetails = MovieInfoActivity.class.cast(getActivity()).getMovieDetails();
    }
}
