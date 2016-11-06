package cat.mrtxema.crispetes.store;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

import cat.mrtxema.crispetes.model.FavoriteMovie;
import cat.mrtxema.crispetes.model.Movie;
import cat.mrtxema.crispetes.model.WatchStatus;

@DatabaseTable(tableName = "movies")
public class FavoriteMovieDto implements Dto<FavoriteMovie> {
    public static final String FIELD_ID = "id";
    public static final String FIELD_TMDB_ID = "tmdbId";
    public static final String FIELD_TITLE = "title";
    public static final String FIELD_RELEASE_DATE = "releaseDate";
    public static final String FIELD_OVERVIEW = "overview";
    public static final String FIELD_POSTER_URL = "posterUrl";
    public static final String FIELD_STATUS = "status";

    @DatabaseField(generatedId = true, canBeNull = false, columnName = FIELD_ID)
    private Integer id;
    @DatabaseField(canBeNull = false, columnName = FIELD_TMDB_ID, index = true)
    private int tmdbId;
    @DatabaseField(canBeNull = false, columnName = FIELD_TITLE)
    private String title;
    @DatabaseField(canBeNull = true, columnName = FIELD_RELEASE_DATE)
    private Date releaseDate;
    @DatabaseField(canBeNull = true, columnName = FIELD_OVERVIEW)
    private String overview;
    @DatabaseField(canBeNull = true, columnName = FIELD_POSTER_URL)
    private String posterUrl;
    @DatabaseField(canBeNull = false, columnName = FIELD_STATUS, index = true)
    private WatchStatus status;

    @Override
    public FavoriteMovieDto fromModel(FavoriteMovie favoriteMovie) {
        id = favoriteMovie.getId();
        tmdbId = favoriteMovie.getMovie().getTmdbId();
        title = favoriteMovie.getMovie().getTitle();
        releaseDate = favoriteMovie.getMovie().getReleaseDate();
        overview = favoriteMovie.getMovie().getOverview();
        posterUrl = favoriteMovie.getMovie().getPosterUrl();
        status = favoriteMovie.getStatus();
        return this;
    }

    @Override
    public FavoriteMovie toModel() {
        return new FavoriteMovie(
            id,
            new Movie(tmdbId, title, releaseDate, overview, posterUrl),
            status
        );
    }

    public Integer getId() {
        return id;
    }

    public FavoriteMovieDto setId(Integer id) {
        this.id = id;
        return this;
    }

    public int getTmdbId() {
        return tmdbId;
    }

    public FavoriteMovieDto setTmdbId(int tmdbId) {
        this.tmdbId = tmdbId;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public FavoriteMovieDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public FavoriteMovieDto setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public String getOverview() {
        return overview;
    }

    public FavoriteMovieDto setOverview(String overview) {
        this.overview = overview;
        return this;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public FavoriteMovieDto setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
        return this;
    }

    public WatchStatus getStatus() {
        return status;
    }

    public FavoriteMovieDto setStatus(WatchStatus status) {
        this.status = status;
        return this;
    }
}
