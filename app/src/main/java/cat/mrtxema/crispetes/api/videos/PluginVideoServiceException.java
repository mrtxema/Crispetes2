package cat.mrtxema.crispetes.api.videos;

import cat.mrtxema.crispetes.api.ExternalServiceException;

public class PluginVideoServiceException extends ExternalServiceException {

    public PluginVideoServiceException(String msg) {
        super(msg);
    }

    public PluginVideoServiceException(String msg, Throwable parent) {
        super(msg, parent);
    }

    public boolean isAuthenticationError() {
        return false;
    }
}
