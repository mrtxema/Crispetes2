package cat.mrtxema.crispetes.view;

import android.support.v4.app.Fragment;
import android.widget.LinearLayout;
import cat.mrtxema.crispetes.model.MovieDetails;
import cat.mrtxema.crispetes.view.adapter.CastListAdapter;
import cat.mrtxema.crispetes.view.adapter.CrewListAdapter;
import cat.mrtxema.crispetes.view.util.ViewUtils;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EFragment(R.layout.fragment_movie_cast)
public class MovieInfoCastFragment extends Fragment {
    private MovieDetails movieDetails;

    @ViewById(R.id.layoutCast)
    LinearLayout layoutCast;
    @ViewById(R.id.layoutCrew)
    LinearLayout layoutCrew;


    @AfterViews
    void initViews() {
        retrieveMovieDetails();
    }

    @Background
    void retrieveMovieDetails() {
        movieDetails = MovieInfoActivity.class.cast(getActivity()).getMovieDetails();
        showMovieDetails();
    }

    @UiThread
    void showMovieDetails() {
        ViewUtils.setAdapter(layoutCast, new CastListAdapter(getContext(), movieDetails.getCastList()));
        ViewUtils.setAdapter(layoutCrew, new CrewListAdapter(getContext(), movieDetails.getCrewList()));
    }
}
