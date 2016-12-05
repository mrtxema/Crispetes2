package cat.mrtxema.crispetes.api.videos;

import java.util.Locale;
import cat.mrtxema.crispetes.api.videos.model.PluginLoginRequest;
import cat.mrtxema.crispetes.api.videos.model.PluginLoginResponse;
import cat.mrtxema.crispetes.api.videos.model.PluginVideoSourceDescriptor;
import cat.mrtxema.crispetes.model.VideoSource;
import org.androidannotations.annotations.EBean;
import retrofit2.Call;

@EBean
public class PluginVideoServiceManager {

    public PluginVideoSourceDescriptor getVideoSourceInfo(String baseUrl, Locale locale) throws PluginVideoServiceException {
        PluginVideoServiceClient client = new PluginVideoServiceClient(baseUrl);
        return client.processResponse(client.getService().getVideoSourceInfo(locale.toString()));
    }

    public PluginVideoServiceSession getVideoServiceSession(VideoSource videoSource) throws PluginVideoServiceException {
        PluginVideoServiceClient client = new PluginVideoServiceClient(videoSource.getBaseUrl());
        Call<PluginLoginResponse> response = client.getService().login(new PluginLoginRequest().setParameters(videoSource.getCredentials()));
        PluginLoginResponse loginResponse = client.processResponse(response);
        return new PluginVideoServiceSession(client, loginResponse.getToken());
    }
}
