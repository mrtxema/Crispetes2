package cat.mrtxema.crispetes.api.videos;

import java.io.IOException;
import java.lang.annotation.Annotation;
import cat.mrtxema.crispetes.api.JsonParserFactory;
import cat.mrtxema.crispetes.api.videos.model.PluginLoginRequest;
import cat.mrtxema.crispetes.api.videos.model.PluginLoginResponse;
import cat.mrtxema.crispetes.api.videos.model.PluginErrorResponse;
import cat.mrtxema.crispetes.model.VideoSource;
import okhttp3.ResponseBody;
import org.androidannotations.annotations.EBean;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@EBean
public class PluginVideoServiceManager {

    public PluginVideoServiceSession getVideoServiceSession(VideoSource videoSource) throws PluginVideoServiceException, PluginVideoServiceRemoteException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(videoSource.getBaseUrl())
                .addConverterFactory(GsonConverterFactory.create(JsonParserFactory.getJsonParser()))
                .build();
        PluginVideoService pluginVideoService = retrofit.create(PluginVideoService.class);
        Converter<ResponseBody, PluginErrorResponse> errorConverter = retrofit.responseBodyConverter(PluginErrorResponse.class, new Annotation[0]);
        ResponseProcessor responseProcessor = new ResponseProcessor(errorConverter);
        try {
            Response<PluginLoginResponse> response = pluginVideoService.login(new PluginLoginRequest().setParameters(videoSource.getCredentials())).execute();
            PluginLoginResponse loginResponse = responseProcessor.process(response);
            return new PluginVideoServiceSession(pluginVideoService, responseProcessor, loginResponse.getToken());
        } catch (IOException e) {
            throw new PluginVideoServiceException("Error login into " + videoSource.getCode(), e);
        }
    }
}
