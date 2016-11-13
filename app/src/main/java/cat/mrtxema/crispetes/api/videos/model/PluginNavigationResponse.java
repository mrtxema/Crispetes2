package cat.mrtxema.crispetes.api.videos.model;

public class PluginNavigationResponse {
    private String videoUrl;
    private PluginNavigationAction navigationAction;

    public String getVideoUrl() {
        return videoUrl;
    }

    public PluginNavigationResponse setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
        return this;
    }

    public PluginNavigationAction getNavigationAction() {
        return navigationAction;
    }

    public PluginNavigationResponse setNavigationAction(PluginNavigationAction navigationAction) {
        this.navigationAction = navigationAction;
        return this;
    }
}
