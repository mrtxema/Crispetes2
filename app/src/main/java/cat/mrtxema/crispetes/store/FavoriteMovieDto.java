package cat.mrtxema.crispetes.store;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

import cat.mrtxema.crispetes.model.FavoriteMovie;
import cat.mrtxema.crispetes.model.Movie;
import cat.mrtxema.crispetes.model.WatchStatus;

@DatabaseTable(tableName = "movies")
public class FavoriteMovieDto implements Dto<FavoriteMovie> {
    @DatabaseField(generatedId = true)
    private Integer id;
    @DatabaseField
    private int tmdbId;
    @DatabaseField
    private String title;
    @DatabaseField
    private Date releaseDate;
    @DatabaseField
    private String overview;
    @DatabaseField
    private String posterUrl;
    @DatabaseField
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
}
