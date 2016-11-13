package cat.mrtxema.crispetes.api.videos.model;

public class PluginNavigationAction {
    private String uri;
    private String postData;

    public String getUri() {
        return uri;
    }

    public PluginNavigationAction setUri(String uri) {
        this.uri = uri;
        return this;
    }

    public String getPostData() {
        return postData;
    }

    public PluginNavigationAction setPostData(String postData) {
        this.postData = postData;
        return this;
    }
}
