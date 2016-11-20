package cat.mrtxema.crispetes.api.videos.model;

public class PluginErrorResponse {
    private String message;
    private String stackTrace;

    public String getMessage() {
        return message;
    }

    public PluginErrorResponse setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public PluginErrorResponse setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
        return this;
    }
}
