package cat.mrtxema.crispetes.api.tmdb;

import cat.mrtxema.crispetes.api.tmdb.model.TmdbErrorResponse;
import cat.mrtxema.crispetes.api.tmdb.model.SearchMoviesResponse;
import cat.mrtxema.crispetes.api.tmdb.model.TmdbConfiguration;
import cat.mrtxema.crispetes.api.tmdb.model.TmdbMovieDetails;
import okhttp3.ResponseBody;
import org.androidannotations.annotations.EBean;
import java.io.IOException;
import java.lang.annotation.Annotation;
import cat.mrtxema.crispetes.api.JsonParserFactory;
import cat.mrtxema.crispetes.util.StringXor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@EBean
public class TmdbServiceManager {
    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static final String API_KEY = new StringXor().decode("Z0QUWQ9Xbl1CNkwQCAoDPFlHZEIXWggCallAZUBCXws=");
    private TmdbService tmdbService;
    private TmdbConfiguration tmdbConfiguration;
    private TmdbResponseProcessor responseProcessor;

    public TmdbServiceManager() {
        initTmdbService();
    }

    private void initTmdbService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(JsonParserFactory.getJsonParser()))
                .build();
        tmdbService = retrofit.create(TmdbService.class);
        Converter<ResponseBody, TmdbErrorResponse> errorConverter  = retrofit.responseBodyConverter(TmdbErrorResponse.class, new Annotation[0]);
        responseProcessor = new TmdbResponseProcessor(errorConverter);
    }

    public TmdbConfiguration getConfiguration() throws TmdbServiceException, TmdbServiceRemoteException {
        if (tmdbConfiguration == null) {
            initConfiguration();
        }
        return tmdbConfiguration;
    }

    private synchronized void initConfiguration() throws TmdbServiceException, TmdbServiceRemoteException {
        if (tmdbConfiguration == null) {
            try {
                tmdbConfiguration = responseProcessor.process(tmdbService.getConfiguration(API_KEY).execute());
            } catch (IOException e) {
                throw new TmdbServiceException("Error retrieving configuration", e);
            }
        }
    }

    public SearchMoviesResponse searchMovies(String title, String language, int page) throws TmdbServiceException, TmdbServiceRemoteException {
        try {
            return responseProcessor.process(tmdbService.searchMovies(API_KEY, language, title, page).execute());
        } catch (IOException e) {
            throw new TmdbServiceException("Error searching movies", e);
        }
    }

    public TmdbMovieDetails getMovieDetails(int movieId, String language) throws TmdbServiceException, TmdbServiceRemoteException {
        try {
            return responseProcessor.process(tmdbService.getMovieDetails(movieId, API_KEY, language).execute());
        } catch (IOException e) {
            throw new TmdbServiceException("Error retrieving movie details", e);
        }
    }
}
