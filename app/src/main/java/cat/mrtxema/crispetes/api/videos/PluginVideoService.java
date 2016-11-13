package cat.mrtxema.crispetes.api.videos;

import cat.mrtxema.crispetes.api.videos.model.PluginLoginRequest;
import cat.mrtxema.crispetes.api.videos.model.PluginLoginResponse;
import cat.mrtxema.crispetes.api.videos.model.PluginMovieLinkList;
import cat.mrtxema.crispetes.api.videos.model.PluginNavigationRequest;
import cat.mrtxema.crispetes.api.videos.model.PluginNavigationResponse;
import cat.mrtxema.crispetes.api.videos.model.PluginVideoSourceDescriptor;
import cat.mrtxema.crispetes.api.videos.model.PluginVideoUrl;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PluginVideoService {

    @GET
    Call<PluginVideoSourceDescriptor> getVideoSourceInfo();

    @POST("login")
    Call<PluginLoginResponse> login(@Body PluginLoginRequest loginRequest);

    @GET("movie/{tmdbId}")
    Call<PluginMovieLinkList> getMovieLinks(@Path("tmdbId") int tmdbId, @Query("token") String token, @Query("imdbId") String imdbId, @Query("language") String language);

    @GET("movie/{tmdbId}/{linkId}")
    Call<PluginVideoUrl> getVideoUrl(@Path("tmdbId") int tmdbId, @Path("linkId") String linkId, @Query("token") String token, @Query("imdbId") String imdbId);

    @POST("navigate")
    Call<PluginNavigationResponse> navigate(@Body PluginNavigationRequest navigationRequest);
}
