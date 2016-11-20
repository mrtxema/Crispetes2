package cat.mrtxema.crispetes.service;

import cat.mrtxema.crispetes.api.tmdb.TmdbServiceRemoteException;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EBean;

import java.util.List;
import java.util.Locale;

import cat.mrtxema.crispetes.api.tmdb.model.SearchMoviesResponse;
import cat.mrtxema.crispetes.api.tmdb.model.TmdbCast;
import cat.mrtxema.crispetes.api.tmdb.model.TmdbCompany;
import cat.mrtxema.crispetes.api.tmdb.model.TmdbConfiguration;
import cat.mrtxema.crispetes.api.tmdb.model.TmdbCountry;
import cat.mrtxema.crispetes.api.tmdb.model.TmdbCrew;
import cat.mrtxema.crispetes.api.tmdb.model.TmdbGenre;
import cat.mrtxema.crispetes.api.tmdb.model.TmdbLanguage;
import cat.mrtxema.crispetes.api.tmdb.model.TmdbMovie;
import cat.mrtxema.crispetes.api.tmdb.model.TmdbMovieDetails;
import cat.mrtxema.crispetes.api.tmdb.TmdbServiceException;
import cat.mrtxema.crispetes.api.tmdb.TmdbServiceManager;
import cat.mrtxema.crispetes.model.Cast;
import cat.mrtxema.crispetes.model.Crew;
import cat.mrtxema.crispetes.model.Movie;
import cat.mrtxema.crispetes.model.MovieDetails;
import cat.mrtxema.crispetes.model.SearchResponse;
import cat.mrtxema.crispetes.service.converter.Converter;
import cat.mrtxema.crispetes.service.converter.ListConverter;

@EBean
public class MovieService {
    private static final Locale DEVICE_LOCALE = Locale.getDefault();
    @Bean
    TmdbServiceManager tmdbServiceManager;

    public SearchResponse<Movie> search(String title, int page) throws MovieServiceException {
        try {
            SearchMoviesResponse response = tmdbServiceManager.searchMovies(title, DEVICE_LOCALE, page);
            List<Movie> movies = new ListConverter<>(new MovieConverter(tmdbServiceManager.getConfiguration())).convert(response.getResults());
            return new SearchResponse<>(response.getPage(), response.getTotalResults(), response.getTotalPages(), movies);
        } catch (TmdbServiceException e) {
            throw new MovieServiceException(e.getMessage(), e);
        } catch (TmdbServiceRemoteException e) {
            throw new MovieServiceException(e.getMessage(), e);
        }
    }

    public MovieDetails getMovieDetails(int id) throws MovieServiceException {
        try {
            TmdbMovieDetails apiMovieDetails = tmdbServiceManager.getMovieDetails(id, DEVICE_LOCALE);
            return new MovieDetailsConverter(tmdbServiceManager.getConfiguration()).convert(apiMovieDetails);
        } catch (TmdbServiceException e) {
            throw new MovieServiceException(e.getMessage(), e);
        } catch (TmdbServiceRemoteException e) {
            throw new MovieServiceException(e.getMessage(), e);
        }
    }

    private static String buildImageUrl(TmdbConfiguration configuration, String size, String path) {
        if (path == null || path.isEmpty()) {
            return null;
        } else {
            return configuration.getImages().getBaseUrl() + size + path;
        }
    }

    private static String buildPosterSearchResultUrl(TmdbConfiguration configuration, String posterPath) {
        return buildImageUrl(configuration, configuration.getImages().getPosterSizes().get(2), posterPath);
    }

    private static String buildPosterUrl(TmdbConfiguration configuration, String posterPath) {
        return buildImageUrl(configuration, configuration.getImages().getPosterSizes().get(3), posterPath);
    }

    private static String buildBackdropUrl(TmdbConfiguration configuration, String backdropPath) {
        return buildImageUrl(configuration, configuration.getImages().getBackdropSizes().get(2), backdropPath);
    }

    private static String buildProfileImageUrl(TmdbConfiguration configuration, String profilePath) {
        return buildImageUrl(configuration, configuration.getImages().getProfileSizes().get(2), profilePath);
    }

    private static class MovieConverter implements Converter<TmdbMovie,Movie> {
        private final TmdbConfiguration tmdbConfiguration;

        public MovieConverter(TmdbConfiguration tmdbConfiguration) {
            this.tmdbConfiguration = tmdbConfiguration;
        }

        @Override
        public Movie convert(TmdbMovie apiMovie) {
            return new Movie(apiMovie.getId(), apiMovie.getTitle(), apiMovie.getReleaseDate(), apiMovie.getOverview(),
                    buildPosterSearchResultUrl(tmdbConfiguration, apiMovie.getPosterPath()));
        }
    }

    private static class MovieDetailsConverter implements Converter<TmdbMovieDetails,MovieDetails> {
        private final TmdbConfiguration tmdbConfiguration;

        public MovieDetailsConverter(TmdbConfiguration tmdbConfiguration) {
            this.tmdbConfiguration = tmdbConfiguration;
        }

        @Override
        public MovieDetails convert(TmdbMovieDetails apiMovieDetails) {
            return MovieDetails.builder()
                    .setTmdbId(apiMovieDetails.getId())
                    .setImdbId(apiMovieDetails.getImdbId())
                    .setTitle(apiMovieDetails.getTitle())
                    .setReleaseDate(apiMovieDetails.getReleaseDate())
                    .setTagline(apiMovieDetails.getTagline())
                    .setOverview(apiMovieDetails.getOverview())
                    .setAdult(apiMovieDetails.isAdult())
                    .setPosterUrl(buildPosterUrl(tmdbConfiguration, apiMovieDetails.getPosterPath()))
                    .setBackdropUrl(buildBackdropUrl(tmdbConfiguration, apiMovieDetails.getBackdropPath()))
                    .setBudget(apiMovieDetails.getBudget())
                    .setGenres(new ListConverter<>(new GenreConverter()).convert(apiMovieDetails.getGenres()))
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

    private static class GenreConverter implements Converter<TmdbGenre,String> {
        @Override
        public String convert(TmdbGenre tmdbGenre) {
            return tmdbGenre.getName();
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
