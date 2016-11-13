package cat.mrtxema.crispetes.api.tmdb.model;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class TmdbMovieDetails {
    private Integer id;
    @SerializedName("imdb_id")
    private String imdbId;
    private String title;
    @SerializedName("release_date")
    private Date releaseDate;
    private String tagline;
    private String overview;
    private boolean adult;
    @SerializedName("poster_path")
    private String posterPath;
    @SerializedName("backdrop_path")
    private String backdropPath;
    @SerializedName("belongs_to_collection")
    private TmdbCollection belongsToCollection;
    private Integer budget;
    private List<TmdbGenre> genres;
    private String homepage;
    @SerializedName("original_language")
    private String originalLanguage;
    @SerializedName("original_title")
    private String originalTitle;
    private BigDecimal popularity;
    @SerializedName("production_companies")
    private List<TmdbCompany> productionCompanies;
    @SerializedName("production_countries")
    private List<TmdbCountry> productionCountries;
    private Integer revenue;
    private Integer runtime;
    @SerializedName("spoken_languages")
    private List<TmdbLanguage> spokenLanguages;
    private String status;
    private boolean video;
    @SerializedName("vote_average")
    private BigDecimal voteAverage;
    @SerializedName("vote_count")
    private Integer voteCount;
    private TmdbCredits credits;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getTagline() {
        return tagline;
    }

    public void setTagline(String tagline) {
        this.tagline = tagline;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public TmdbCollection getBelongsToCollection() {
        return belongsToCollection;
    }

    public void setBelongsToCollection(TmdbCollection belongsToCollection) {
        this.belongsToCollection = belongsToCollection;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public List<TmdbGenre> getGenres() {
        return genres;
    }

    public void setGenres(List<TmdbGenre> genres) {
        this.genres = genres;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public BigDecimal getPopularity() {
        return popularity;
    }

    public void setPopularity(BigDecimal popularity) {
        this.popularity = popularity;
    }

    public List<TmdbCompany> getProductionCompanies() {
        return productionCompanies;
    }

    public void setProductionCompanies(List<TmdbCompany> productionCompanies) {
        this.productionCompanies = productionCompanies;
    }

    public List<TmdbCountry> getProductionCountries() {
        return productionCountries;
    }

    public void setProductionCountries(List<TmdbCountry> productionCountries) {
        this.productionCountries = productionCountries;
    }

    public Integer getRevenue() {
        return revenue;
    }

    public void setRevenue(Integer revenue) {
        this.revenue = revenue;
    }

    public Integer getRuntime() {
        return runtime;
    }

    public void setRuntime(Integer runtime) {
        this.runtime = runtime;
    }

    public List<TmdbLanguage> getSpokenLanguages() {
        return spokenLanguages;
    }

    public void setSpokenLanguages(List<TmdbLanguage> spokenLanguages) {
        this.spokenLanguages = spokenLanguages;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public BigDecimal getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(BigDecimal voteAverage) {
        this.voteAverage = voteAverage;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public TmdbCredits getCredits() {
        return credits;
    }

    public void setCredits(TmdbCredits credits) {
        this.credits = credits;
    }
}
