package cat.mrtxema.crispetes.model;

import java.util.Date;

public class Movie {
    private final int id;
    private final String title;
    private final Date releaseDate;
    private final String overview;
    private final String posterUrl;

    public Movie(int id, String title, Date releaseDate, String overview, String posterUrl) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.overview = overview;
        this.posterUrl = posterUrl;
    }

    public int getId() {
        return id;
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
                "id=" + id +
                ", title='" + title + '\'' +
                ", releaseDate=" + releaseDate +
                ", overview='" + overview + '\'' +
                ", posterUrl='" + posterUrl + '\'' +
                '}';
    }
}
