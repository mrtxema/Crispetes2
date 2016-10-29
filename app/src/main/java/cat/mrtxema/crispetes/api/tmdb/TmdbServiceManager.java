package cat.mrtxema.crispetes.api.tmdb;

import android.util.Log;

import org.androidannotations.annotations.EBean;
import java.io.IOException;
import cat.mrtxema.crispetes.api.JsonParserFactory;
import cat.mrtxema.crispetes.util.StringXor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@EBean
public class TmdbServiceManager {
    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final String API_KEY = new StringXor().decode("Z0QUWQ9Xbl1CNkwQCAoDPFlHZEIXWggCallAZUBCXws=");
    private final TmdbService tmdbService = initTmdbService();
    private TmdbConfiguration tmdbConfiguration;

    private TmdbService initTmdbService() {
        //Log.w(getClass().getSimpleName(), "API KEY xor: " + new StringXor().encode("40f0a0620e8baddd6576e3fe2626406e"));
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(JsonParserFactory.getJsonParser()))
                .build()
                .create(TmdbService.class);
    }

    public TmdbConfiguration getConfiguration() throws TmdbServiceException {
        if (tmdbConfiguration == null) {
            initConfiguration();
        }
        return tmdbConfiguration;
    }

    private synchronized void initConfiguration() throws TmdbServiceException {
        if (tmdbConfiguration == null) {
            try {
                tmdbConfiguration = tmdbService.getConfiguration(API_KEY).execute().body();
            } catch (IOException e) {
                throw new TmdbServiceException("Error retrieving configuration", e);
            }
        }
    }

    public SearchMoviesResponse searchMovies(String title, String language, int page) throws TmdbServiceException {
        try {
            return tmdbService.searchMovies(API_KEY, language, title, page).execute().body();
        } catch (IOException e) {
            throw new TmdbServiceException("Error searching movies", e);
        }
    }

    public TmdbMovieDetails getMovieDetails(int movieId, String language) throws TmdbServiceException {
        try {
            return tmdbService.getMovieDetails(movieId, API_KEY, language).execute().body();
        } catch (IOException e) {
            throw new TmdbServiceException("Error retrieving movie details", e);
        }
    }
}
