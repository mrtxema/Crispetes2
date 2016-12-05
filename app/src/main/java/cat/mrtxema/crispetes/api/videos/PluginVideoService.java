package cat.mrtxema.crispetes.api.videos;

import cat.mrtxema.crispetes.api.videos.model.PluginLoginRequest;
import cat.mrtxema.crispetes.api.videos.model.PluginLoginResponse;
import cat.mrtxema.crispetes.api.videos.model.PluginVideoLinkList;
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
    Call<PluginVideoSourceDescriptor> getVideoSourceInfo(@Query("language") String language);

    @POST("login")
    Call<PluginLoginResponse> login(@Body PluginLoginRequest loginRequest);

    @GET("movie/{tmdbId}")
    Call<PluginVideoLinkList> getMovieLinks(@Path("tmdbId") int tmdbId, @Query("token") String token, @Query("imdbId") String imdbId, @Query("language") String language);

    @GET("tvshow/{tmdbId}/{season}/{episode}")
    Call<PluginVideoLinkList> getEpisodeLinks(
            @Path("tmdbId") int tmdbId, @Path("season") int season, @Path("episode") int episode,
            @Query("token") String token, @Query("imdbId") String imdbId, @Query("language") String language);

    @GET("link/{linkId}")
    Call<PluginVideoUrl> getVideoUrl(@Path("linkId") String linkId, @Query("token") String token);

    @POST("navigate")
    Call<PluginNavigationResponse> navigate(@Body PluginNavigationRequest navigationRequest);
}
