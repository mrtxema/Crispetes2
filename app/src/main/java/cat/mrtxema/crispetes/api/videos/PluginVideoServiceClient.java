package cat.mrtxema.crispetes.api.videos;

import java.io.IOException;
import java.lang.annotation.Annotation;
import cat.mrtxema.crispetes.api.JsonParserFactory;
import cat.mrtxema.crispetes.api.videos.model.PluginErrorResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PluginVideoServiceClient {
    private final PluginVideoService pluginVideoService;
    private final ResponseProcessor responseProcessor;

    public PluginVideoServiceClient(String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(JsonParserFactory.getJsonParser()))
                .build();
        pluginVideoService = retrofit.create(PluginVideoService.class);
        Converter<ResponseBody, PluginErrorResponse> errorConverter = retrofit.responseBodyConverter(PluginErrorResponse.class, new Annotation[0]);
        responseProcessor = new ResponseProcessor(errorConverter);
    }

    public PluginVideoService getService() {
        return pluginVideoService;
    }

    public <T> T processResponse(Call<T> call) throws PluginVideoServiceException {
        try {
            return responseProcessor.process(call.execute());
        } catch (IOException e) {
            throw new PluginVideoServiceException("Error calling video source", e);
        }
    }
}
