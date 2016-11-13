package cat.mrtxema.crispetes.api.videos;

import cat.mrtxema.crispetes.api.ExternalServiceException;

public class PluginVideoServiceRemoteException extends ExternalServiceException {
    private final int errorCode;

    public PluginVideoServiceRemoteException(int errorCode, String msg) {
        super(msg);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }
}
