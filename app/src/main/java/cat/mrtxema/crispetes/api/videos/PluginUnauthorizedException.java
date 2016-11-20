package cat.mrtxema.crispetes.api.videos;

public class PluginUnauthorizedException extends PluginVideoServiceException {

    public PluginUnauthorizedException(String message) {
        super(message);
    }

    @Override
    public boolean isAuthenticationError() {
        return true;
    }
}
