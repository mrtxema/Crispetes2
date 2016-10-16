package cat.mrtxema.crispetes.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class MovieDetailsBuilder {
    Integer id;
    String imdbId;
    String title;
    Date releaseDate;
    String tagline;
    String overview;
    boolean adult;
    String posterUrl;
    String backdropUrl;
    Integer budget;
    List<String> genres;
    String homepage;
    String originalLanguage;
    String originalTitle;
    BigDecimal popularity;
    List<String> productionCompanies;
    List<String> productionCountries;
    Integer revenue;
    Integer runtime;
    List<String> spokenLanguages;
    String status;
    BigDecimal voteAverage;
    Integer voteCount;
    List<Cast> cast;
    List<Crew> crew;

    public MovieDetailsBuilder setId(Integer id) {
        this.id = id;
        return this;
    }

    public MovieDetailsBuilder setImdbId(String imdbId) {
        this.imdbId = imdbId;
        return this;
    }

    public MovieDetailsBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public MovieDetailsBuilder setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    public MovieDetailsBuilder setTagline(String tagline) {
        this.tagline = tagline;
        return this;
    }

    public MovieDetailsBuilder setOverview(String overview) {
        this.overview = overview;
        return this;
    }

    public MovieDetailsBuilder setAdult(boolean adult) {
        this.adult = adult;
        return this;
    }

    public MovieDetailsBuilder setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
        return this;
    }

    public MovieDetailsBuilder setBackdropUrl(String backdropUrl) {
        this.backdropUrl = backdropUrl;
        return this;
    }

    public MovieDetailsBuilder setBudget(Integer budget) {
        this.budget = budget;
        return this;
    }

    public MovieDetailsBuilder setGenres(List<String> genres) {
        this.genres = genres;
        return this;
    }

    public MovieDetailsBuilder setHomepage(String homepage) {
        this.homepage = homepage;
        return this;
    }

    public MovieDetailsBuilder setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
        return this;
    }

    public MovieDetailsBuilder setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
        return this;
    }

    public MovieDetailsBuilder setPopularity(BigDecimal popularity) {
        this.popularity = popularity;
        return this;
    }

    public MovieDetailsBuilder setProductionCompanies(List<String> productionCompanies) {
        this.productionCompanies = productionCompanies;
        return this;
    }

    public MovieDetailsBuilder setProductionCountries(List<String> productionCountries) {
        this.productionCountries = productionCountries;
        return this;
    }

    public MovieDetailsBuilder setRevenue(Integer revenue) {
        this.revenue = revenue;
        return this;
    }

    public MovieDetailsBuilder setRuntime(Integer runtime) {
        this.runtime = runtime;
        return this;
    }

    public MovieDetailsBuilder setSpokenLanguages(List<String> spokenLanguages) {
        this.spokenLanguages = spokenLanguages;
        return this;
    }

    public MovieDetailsBuilder setStatus(String status) {
        this.status = status;
        return this;
    }

    public MovieDetailsBuilder setVoteAverage(BigDecimal voteAverage) {
        this.voteAverage = voteAverage;
        return this;
    }

    public MovieDetailsBuilder setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
        return this;
    }

    public MovieDetailsBuilder setCast(List<Cast> cast) {
        this.cast = cast;
        return this;
    }

    public MovieDetailsBuilder setCrew(List<Crew> crew) {
        this.crew = crew;
        return this;
    }

    public MovieDetails build() {
        return new MovieDetails(this);
    }
}
