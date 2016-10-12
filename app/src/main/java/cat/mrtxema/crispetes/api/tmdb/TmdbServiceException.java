package cat.mrtxema.crispetes.api.tmdb;

import cat.mrtxema.crispetes.api.ExternalServiceException;

public class TmdbServiceException extends ExternalServiceException {

    public TmdbServiceException(String msg, Throwable parent) {
        super(msg, parent);
    }
}
