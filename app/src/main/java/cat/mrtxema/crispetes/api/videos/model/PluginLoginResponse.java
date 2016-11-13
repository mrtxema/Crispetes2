package cat.mrtxema.crispetes.api.videos.model;

public class PluginLoginResponse {
    private String token;

    public String getToken() {
        return token;
    }

    public PluginLoginResponse setToken(String token) {
        this.token = token;
        return this;
    }
}
