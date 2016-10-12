package cat.mrtxema.crispetes.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class MovieDetails {
    private final Integer id;
    private final String imdbId;
    private final String title;
    private final Date releaseDate;
    private final String tagline;
    private final String overview;
    private final boolean adult;
    private final String posterUrl;
    private final String backdropUrl;
    private final Integer budget;
    private final String homepage;
    private final String originalLanguage;
    private final String originalTitle;
    private final BigDecimal popularity;
    private final List<String> productionCompanies;
    private final List<String> productionCountries;
    private final Integer revenue;
    private final Integer runtime;
    private final List<String> spokenLanguages;
    private final String status;
    private final BigDecimal voteAverage;
    private final Integer voteCount;
    private final List<Cast> cast;
    private final List<Crew> crew;

    public MovieDetails(MovieDetailsBuilder builder) {
        id = builder.id;
        imdbId = builder.imdbId;
        title = builder.title;
        releaseDate = builder.releaseDate;
        tagline = builder.tagline;
        overview = builder.overview;
        adult = builder.adult;
        posterUrl = builder.posterUrl;
        backdropUrl = builder.backdropUrl;
        budget = builder.budget;
        homepage = builder.homepage;
        originalLanguage = builder.originalLanguage;
        originalTitle = builder.originalTitle;
        popularity = builder.popularity;
        productionCompanies = builder.productionCompanies;
        productionCountries = builder.productionCountries;
        revenue = builder.revenue;
        runtime = builder.runtime;
        spokenLanguages = builder.spokenLanguages;
        status = builder.status;
        voteAverage = builder.voteAverage;
        voteCount = builder.voteCount;
        cast = builder.cast;
        crew = builder.crew;
    }

    public Integer getId() {
        return id;
    }

    public String getImdbId() {
        return imdbId;
    }

    public String getTitle() {
        return title;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public String getTagline() {
        return tagline;
    }

    public String getOverview() {
        return overview;
    }

    public boolean isAdult() {
        return adult;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public String getBackdropUrl() {
        return backdropUrl;
    }

    public Integer getBudget() {
        return budget;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public BigDecimal getPopularity() {
        return popularity;
    }

    public List<String> getProductionCompanies() {
        return productionCompanies;
    }

    public List<String> getProductionCountries() {
        return productionCountries;
    }

    public Integer getRevenue() {
        return revenue;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public List<String> getSpokenLanguages() {
        return spokenLanguages;
    }

    public String getStatus() {
        return status;
    }

    public BigDecimal getVoteAverage() {
        return voteAverage;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public List<Cast> getCast() {
        return cast;
    }

    public List<Crew> getCrew() {
        return crew;
    }

    @Override
    public String toString() {
        return "MovieDetails{" +
                "id=" + id +
                ", imdbId='" + imdbId + '\'' +
                ", title='" + title + '\'' +
                ", releaseDate=" + releaseDate +
                ", tagline='" + tagline + '\'' +
                ", overview='" + overview + '\'' +
                ", adult=" + adult +
                ", posterUrl='" + posterUrl + '\'' +
                ", backdropUrl='" + backdropUrl + '\'' +
                ", budget=" + budget +
                ", homepage='" + homepage + '\'' +
                ", originalLanguage='" + originalLanguage + '\'' +
                ", originalTitle='" + originalTitle + '\'' +
                ", popularity=" + popularity +
                ", productionCompanies=" + productionCompanies +
                ", productionCountries=" + productionCountries +
                ", revenue=" + revenue +
                ", runtime=" + runtime +
                ", spokenLanguages=" + spokenLanguages +
                ", status='" + status + '\'' +
                ", voteAverage=" + voteAverage +
                ", voteCount=" + voteCount +
                ", cast=" + cast +
                ", crew=" + crew +
                '}';
    }
}
