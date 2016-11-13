package cat.mrtxema.crispetes.api.videos.model;

public class PluginNavigationRequest {
    private PluginNavigationAction navigationAction;
    private String serverResponse;

    public PluginNavigationAction getNavigationAction() {
        return navigationAction;
    }

    public PluginNavigationRequest setNavigationAction(PluginNavigationAction navigationAction) {
        this.navigationAction = navigationAction;
        return this;
    }

    public String getServerResponse() {
        return serverResponse;
    }

    public PluginNavigationRequest setServerResponse(String serverResponse) {
        this.serverResponse = serverResponse;
        return this;
    }
}
