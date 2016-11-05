package cat.mrtxema.crispetes.model;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;
import java.util.Date;

@Parcel(Parcel.Serialization.BEAN)
public class Movie {
    private final int tmdbId;
    private final String title;
    private final Date releaseDate;
    private final String overview;
    private final String posterUrl;

    @ParcelConstructor
    public Movie(int tmdbId, String title, Date releaseDate, String overview, String posterUrl) {
        this.tmdbId = tmdbId;
        this.title = title;
        this.releaseDate = releaseDate;
        this.overview = overview;
        this.posterUrl = posterUrl;
    }

    public int getTmdbId() {
        return tmdbId;
    }

    public String getTitle() {
        return title;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "tmdbId=" + tmdbId +
                ", title='" + title + '\'' +
                ", releaseDate=" + releaseDate +
                ", overview='" + overview + '\'' +
                ", posterUrl='" + posterUrl + '\'' +
                '}';
    }
}
