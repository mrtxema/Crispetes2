package cat.mrtxema.crispetes.api.tmdb;

import cat.mrtxema.crispetes.api.tmdb.model.SearchMoviesResponse;
import cat.mrtxema.crispetes.api.tmdb.model.TmdbConfiguration;
import cat.mrtxema.crispetes.api.tmdb.model.TmdbMovieDetails;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TmdbService {

    @GET("configuration")
    Call<TmdbConfiguration> getConfiguration(@Query("api_key") String apiKey);

    @GET("search/movie")
    Call<SearchMoviesResponse> searchMovies(@Query("api_key") String apiKey, @Query("language") String language, @Query("query") String query, @Query("page") int page);

    @GET("movie/{id}?append_to_response=credits")
    Call<TmdbMovieDetails> getMovieDetails(@Path("id") int movieId, @Query("api_key") String apiKey, @Query("language") String language);
}
