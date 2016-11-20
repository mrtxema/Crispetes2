package cat.mrtxema.crispetes.api.videos;

import java.io.IOException;
import cat.mrtxema.crispetes.api.videos.model.PluginErrorResponse;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

public class ResponseProcessor {
    private final Converter<ResponseBody, PluginErrorResponse> errorConverter;

    public ResponseProcessor(Converter<ResponseBody, PluginErrorResponse> errorConverter) {
        this.errorConverter = errorConverter;
    }

    public <T> T process(Response<T> response) throws PluginVideoServiceException {
        if (response.isSuccessful()) {
            return response.body();
        } else {
            try {
                PluginErrorResponse errorResponse = errorConverter.convert(response.errorBody());
                if (response.code() == 401) {
                    throw new PluginUnauthorizedException(errorResponse.getMessage());
                }
                throw new PluginVideoServiceException(errorResponse.getMessage());
            } catch (IOException e) {
                throw new PluginVideoServiceException("Error processing error response", e);
            }
        }
    }
}
