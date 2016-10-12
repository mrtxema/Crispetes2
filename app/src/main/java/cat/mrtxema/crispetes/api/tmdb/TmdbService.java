package cat.mrtxema.crispetes.api.tmdb;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TmdbService {

    //https://api.themoviedb.org/3/configuration?api_key=40f0a0620e8baddd6576e3fe2626406e
    @GET("configuration")
    Call<TmdbConfiguration> getConfiguration(@Query("api_key") String apiKey);

    //https://api.themoviedb.org/3/search/movie?api_key=40f0a0620e8baddd6576e3fe2626406e&language=es-ES&query=hotel&page=1
    @GET("search/movie")
    Call<SearchMoviesResponse> searchMovies(@Query("api_key") String apiKey, @Query("language") String language, @Query("query") String query, @Query("page") int page);

    //Posters
    //http://image.tmdb.org/t/p/w185/1MgFfbyiNpgOP096pjDkVANVYBH.jpg

    //https://api.themoviedb.org/3/movie/76492?api_key=40f0a0620e8baddd6576e3fe2626406e&language=es-ES&append_to_response=credits
    @GET("movie/{id}?append_to_response=credits")
    Call<TmdbMovieDetails> getMovieDetails(@Path("id") int movieId, @Query("api_key") String apiKey, @Query("language") String language);

    //https://api.themoviedb.org/3/movie/76492/videos?api_key=40f0a0620e8baddd6576e3fe2626406e&language=es-ES
    //Trailers
    //https://www.youtube.com/watch?v=mZ7wb2SmDMI
}
