package cat.mrtxema.crispetes.api.videos;

import java.util.Locale;
import cat.mrtxema.crispetes.api.videos.model.PluginVideoLinkList;
import cat.mrtxema.crispetes.api.videos.model.PluginNavigationRequest;
import cat.mrtxema.crispetes.api.videos.model.PluginNavigationResponse;
import cat.mrtxema.crispetes.api.videos.model.PluginVideoUrl;

public class PluginVideoServiceSession {
    private final PluginVideoServiceClient client;
    private final String token;

    public PluginVideoServiceSession(PluginVideoServiceClient client, String token) {
        this.client = client;
        this.token = token;
    }

    private String getLanguageCode(Locale deviceLocale) {
        return deviceLocale.toString();
    }

    public PluginVideoLinkList getMovieLinks(int tmdbId, String imdbId, Locale locale) throws PluginVideoServiceException {
        return client.processResponse(client.getService().getMovieLinks(tmdbId, token, imdbId, getLanguageCode(locale)));
    }

    public PluginVideoLinkList getEpisodeLinks(int tmdbId, int season, int episode, String imdbId, Locale locale) throws PluginVideoServiceException {
        return client.processResponse(client.getService().getEpisodeLinks(tmdbId, season, episode, token, imdbId, getLanguageCode(locale)));
    }

    public PluginVideoUrl getVideoUrl(int tmdbId, String imdbId, String linkId) throws PluginVideoServiceException {
        return client.processResponse(client.getService().getVideoUrl(linkId, token));
    }

    public PluginNavigationResponse navigate(PluginNavigationRequest navigationRequest) throws PluginVideoServiceException {
        return client.processResponse(client.getService().navigate(navigationRequest));
    }
}
