package cat.mrtxema.crispetes.model;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

@Parcel(Parcel.Serialization.BEAN)
public class FavoriteMovie {
    private final Integer id;
    private final Movie movie;
    private final WatchStatus status;

    public FavoriteMovie(Movie movie) {
        this(null, movie, WatchStatus.UNSAVED);
    }

    @ParcelConstructor
    public FavoriteMovie(Integer id, Movie movie, WatchStatus status) {
        this.id = id;
        this.movie = movie;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public Movie getMovie() {
        return movie;
    }

    public WatchStatus getStatus() {
        return status;
    }
}
