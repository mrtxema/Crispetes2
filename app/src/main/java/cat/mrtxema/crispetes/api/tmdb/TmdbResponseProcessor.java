package cat.mrtxema.crispetes.api.tmdb;

import java.io.IOException;
import cat.mrtxema.crispetes.api.tmdb.model.TmdbErrorResponse;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;

public class TmdbResponseProcessor {
    private final Converter<ResponseBody, TmdbErrorResponse> errorConverter;

    public TmdbResponseProcessor(Converter<ResponseBody, TmdbErrorResponse> errorConverter) {
        this.errorConverter = errorConverter;
    }

    public <T> T process(Response<T> response) throws TmdbServiceException, TmdbServiceRemoteException {
        if (response.isSuccessful()) {
            return response.body();
        } else {
            try {
                TmdbErrorResponse errorResponse = errorConverter.convert(response.errorBody());
                throw new TmdbServiceRemoteException(errorResponse.getCode(), errorResponse.getMessage());
            } catch (IOException e) {
                throw new TmdbServiceException("Error processing error response", e);
            }
        }
    }
}
