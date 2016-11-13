package cat.mrtxema.crispetes.api.videos.model;

public class PluginVideoUrl {
    private String url;
    private PluginNavigationAction navigationAction;

    public String getUrl() {
        return url;
    }

    public PluginVideoUrl setUrl(String url) {
        this.url = url;
        return this;
    }

    public PluginNavigationAction getNavigationAction() {
        return navigationAction;
    }

    public PluginVideoUrl setNavigationAction(PluginNavigationAction navigationAction) {
        this.navigationAction = navigationAction;
        return this;
    }
}
