package cat.mrtxema.crispetes.service;

import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.List;

import cat.mrtxema.crispetes.api.tmdb.TmdbCast;
import cat.mrtxema.crispetes.api.tmdb.TmdbCompany;
import cat.mrtxema.crispetes.api.tmdb.TmdbConfiguration;
import cat.mrtxema.crispetes.api.tmdb.TmdbCountry;
import cat.mrtxema.crispetes.api.tmdb.TmdbCrew;
import cat.mrtxema.crispetes.api.tmdb.TmdbLanguage;
import cat.mrtxema.crispetes.api.tmdb.TmdbMovie;
import cat.mrtxema.crispetes.api.tmdb.TmdbMovieDetails;
import cat.mrtxema.crispetes.api.tmdb.TmdbServiceException;
import cat.mrtxema.crispetes.api.tmdb.TmdbServiceManager;
import cat.mrtxema.crispetes.model.Cast;
import cat.mrtxema.crispetes.model.Crew;
import cat.mrtxema.crispetes.model.Movie;
import cat.mrtxema.crispetes.model.MovieDetails;
import cat.mrtxema.crispetes.model.MovieDetailsBuilder;
import cat.mrtxema.crispetes.service.converter.Converter;
import cat.mrtxema.crispetes.service.converter.ListConverter;

@EBean
public class MovieService {
    private static final String DEFAULT_LANGUAGE = "es-ES";
    @Bean
    TmdbServiceManager tmdbServiceManager;

    public List<Movie> search(String title) throws MovieServiceException {
        try {
            List<TmdbMovie> apiMovies = tmdbServiceManager.searchMovies(title, DEFAULT_LANGUAGE, 1).getResults();
            return new ListConverter<>(new MovieConverter(tmdbServiceManager.getConfiguration())).convert(apiMovies);
        } catch (TmdbServiceException e) {
            throw new MovieServiceException(e.getMessage(), e);
        }
    }

    public MovieDetails getMovieDetails(int id) throws MovieServiceException {
        try {
            TmdbMovieDetails apiMovieDetails = tmdbServiceManager.getMovieDetails(id, DEFAULT_LANGUAGE);
            return new MovieDetailsConverter(tmdbServiceManager.getConfiguration()).convert(apiMovieDetails);
        } catch (TmdbServiceException e) {
            throw new MovieServiceException(e.getMessage(), e);
        }
    }

    private static String buildPosterUrl(TmdbConfiguration configuration, String posterPath) {
        return configuration.getImages().getBaseUrl() + configuration.getImages().getPosterSizes().get(2) + posterPath;
    }

    private static String buildBackdropUrl(TmdbConfiguration configuration, String backdropPath) {
        return configuration.getImages().getBaseUrl() + configuration.getImages().getBackdropSizes().get(2) + backdropPath;
    }

    private static String buildProfileImageUrl(TmdbConfiguration configuration, String profilePath) {
        return configuration.getImages().getBaseUrl() + configuration.getImages().getProfileSizes().get(2) + profilePath;
    }

    private static class MovieConverter implements Converter<TmdbMovie,Movie> {
        private final TmdbConfiguration tmdbConfiguration;

        public MovieConverter(TmdbConfiguration tmdbConfiguration) {
            this.tmdbConfiguration = tmdbConfiguration;
        }

        @Override
        public Movie convert(TmdbMovie apiMovie) {
            return new Movie(apiMovie.getId(), apiMovie.getTitle(), apiMovie.getReleaseDate(), apiMovie.getOverview(),
                    buildPosterUrl(tmdbConfiguration, apiMovie.getPosterPath()));
        }
    }

    private static class MovieDetailsConverter implements Converter<TmdbMovieDetails,MovieDetails> {
        private final TmdbConfiguration tmdbConfiguration;

        public MovieDetailsConverter(TmdbConfiguration tmdbConfiguration) {
            this.tmdbConfiguration = tmdbConfiguration;
        }

        @Override
        public MovieDetails convert(TmdbMovieDetails apiMovieDetails) {
            return new MovieDetailsBuilder()
                    .setId(apiMovieDetails.getId())
                    .setImdbId(apiMovieDetails.getImdbId())
                    .setTitle(apiMovieDetails.getTitle())
                    .setReleaseDate(apiMovieDetails.getReleaseDate())
                    .setTagline(apiMovieDetails.getTagline())
                    .setOverview(apiMovieDetails.getOverview())
                    .setAdult(apiMovieDetails.isAdult())
                    .setPosterUrl(buildPosterUrl(tmdbConfiguration, apiMovieDetails.getPosterPath()))
                    .setBackdropUrl(buildBackdropUrl(tmdbConfiguration, apiMovieDetails.getBackdropPath()))
                    .setBudget(apiMovieDetails.getBudget())
                    .setHomepage(apiMovieDetails.getHomepage())
                    .setOriginalLanguage(apiMovieDetails.getOriginalLanguage())
                    .setOriginalTitle(apiMovieDetails.getOriginalTitle())
                    .setPopularity(apiMovieDetails.getPopularity())
                    .setProductionCompanies(new ListConverter<>(new CompanyConverter()).convert(apiMovieDetails.getProductionCompanies()))
                    .setProductionCountries(new ListConverter<>(new CountryConverter()).convert(apiMovieDetails.getProductionCountries()))
                    .setRevenue(apiMovieDetails.getRevenue())
                    .setRuntime(apiMovieDetails.getRuntime())
                    .setSpokenLanguages(new ListConverter<>(new LanguageConverter()).convert(apiMovieDetails.getSpokenLanguages()))
                    .setStatus(apiMovieDetails.getStatus())
                    .setVoteAverage(apiMovieDetails.getVoteAverage())
                    .setVoteCount(apiMovieDetails.getVoteCount())
                    .setCast(new ListConverter<>(new CastConverter(tmdbConfiguration)).convert(apiMovieDetails.getCredits().getCast()))
                    .setCrew(new ListConverter<>(new CrewConverter(tmdbConfiguration)).convert(apiMovieDetails.getCredits().getCrew()))
                    .build();
        }
    }

    private static class CompanyConverter implements Converter<TmdbCompany,String> {
        @Override
        public String convert(TmdbCompany company) {
            return company.getName();
        }
    }

    private static class CountryConverter implements Converter<TmdbCountry,String> {
        @Override
        public String convert(TmdbCountry country) {
            return country.getIsoCode();
        }
    }

    private static class LanguageConverter implements Converter<TmdbLanguage,String> {
        @Override
        public String convert(TmdbLanguage language) {
            return language.getIsoCode();
        }
    }

    private static class CastConverter implements Converter<TmdbCast,Cast> {
        private final TmdbConfiguration tmdbConfiguration;

        public CastConverter(TmdbConfiguration tmdbConfiguration) {
            this.tmdbConfiguration = tmdbConfiguration;
        }

        @Override
        public Cast convert(TmdbCast cast) {
            return new Cast(cast.getName(), cast.getCharacter(), cast.getOrder(), buildProfileImageUrl(tmdbConfiguration, cast.getProfilePath()));
        }
    }

    private static class CrewConverter implements Converter<TmdbCrew,Crew> {
        private final TmdbConfiguration tmdbConfiguration;

        public CrewConverter(TmdbConfiguration tmdbConfiguration) {
            this.tmdbConfiguration = tmdbConfiguration;
        }

        @Override
        public Crew convert(TmdbCrew crew) {
            return new Crew(crew.getName(), crew.getJob(), buildProfileImageUrl(tmdbConfiguration, crew.getProfilePath()));
        }
    }
}
