package cat.mrtxema.crispetes.api.tmdb;

import cat.mrtxema.crispetes.api.ExternalServiceException;

public class TmdbServiceRemoteException extends ExternalServiceException {
    private final int errorCode;

    public TmdbServiceRemoteException(int errorCode, String msg) {
        super(msg);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
