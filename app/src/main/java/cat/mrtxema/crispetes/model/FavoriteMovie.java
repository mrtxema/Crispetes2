package cat.mrtxema.crispetes.model;

public class FavoriteMovie {
    private final Integer id;
    private final Movie movie;
    private final WatchStatus status;

    public FavoriteMovie(Movie movie) {
        this(null, movie, WatchStatus.PENDING);
    }

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
