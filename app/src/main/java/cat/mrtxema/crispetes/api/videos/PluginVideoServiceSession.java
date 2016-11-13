package cat.mrtxema.crispetes.api.videos;

import java.io.IOException;
import cat.mrtxema.crispetes.api.videos.model.PluginMovieLinkList;
import cat.mrtxema.crispetes.api.videos.model.PluginNavigationRequest;
import cat.mrtxema.crispetes.api.videos.model.PluginNavigationResponse;
import cat.mrtxema.crispetes.api.videos.model.PluginVideoSourceDescriptor;
import cat.mrtxema.crispetes.api.videos.model.PluginVideoUrl;

public class PluginVideoServiceSession {
    private final PluginVideoService pluginVideoService;
    private final ResponseProcessor responseProcessor;
    private final String token;

    public PluginVideoServiceSession(PluginVideoService pluginVideoService, ResponseProcessor responseProcessor, String token) {
        this.pluginVideoService = pluginVideoService;
        this.responseProcessor = responseProcessor;
        this.token = token;
    }

    public PluginVideoSourceDescriptor getVideoSourceInfo() throws PluginVideoServiceException, PluginVideoServiceRemoteException {
        try {
            return responseProcessor.process(pluginVideoService.getVideoSourceInfo().execute());
        } catch (IOException e) {
            throw new PluginVideoServiceException("Error retrieving video source info", e);
        }
    }

    public PluginMovieLinkList getMovieLinks(int tmdbId, String imdbId, String language) throws PluginVideoServiceException, PluginVideoServiceRemoteException {
        try {
            return responseProcessor.process(pluginVideoService.getMovieLinks(tmdbId, token, imdbId, language).execute());
        } catch (IOException e) {
            throw new PluginVideoServiceException("Error retrieving movie links", e);
        }
    }

    public PluginVideoUrl getVideoUrl(int tmdbId, String imdbId, String linkId) throws PluginVideoServiceException, PluginVideoServiceRemoteException {
        try {
            return responseProcessor.process(pluginVideoService.getVideoUrl(tmdbId, linkId, token, imdbId).execute());
        } catch (IOException e) {
            throw new PluginVideoServiceException("Error retrieving video url", e);
        }
    }

    public PluginNavigationResponse navigate(PluginNavigationRequest navigationRequest) throws PluginVideoServiceException, PluginVideoServiceRemoteException {
        try {
            return responseProcessor.process(pluginVideoService.navigate(navigationRequest).execute());
        } catch (IOException e) {
            throw new PluginVideoServiceException("Error navigating to video", e);
        }
    }
}
