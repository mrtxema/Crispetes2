package cat.mrtxema.crispetes.api.videos.model;

import java.util.List;

public class PluginVideoSourceDescriptor {
    private String code;
    private String name;
    private String description;
    private boolean supportsMovies;
    private boolean supportsTvShows;
    private String registrationUrl;
    private List<String> loginParameters;

    public String getCode() {
        return code;
    }

    public PluginVideoSourceDescriptor setCode(String code) {
        this.code = code;
        return this;
    }

    public String getName() {
        return name;
    }

    public PluginVideoSourceDescriptor setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public PluginVideoSourceDescriptor setDescription(String description) {
        this.description = description;
        return this;
    }

    public boolean isSupportsMovies() {
        return supportsMovies;
    }

    public PluginVideoSourceDescriptor setSupportsMovies(boolean supportsMovies) {
        this.supportsMovies = supportsMovies;
        return this;
    }

    public boolean isSupportsTvShows() {
        return supportsTvShows;
    }

    public PluginVideoSourceDescriptor setSupportsTvShows(boolean supportsTvShows) {
        this.supportsTvShows = supportsTvShows;
        return this;
    }

    public String getRegistrationUrl() {
        return registrationUrl;
    }

    public PluginVideoSourceDescriptor setRegistrationUrl(String registrationUrl) {
        this.registrationUrl = registrationUrl;
        return this;
    }

    public List<String> getLoginParameters() {
        return loginParameters;
    }

    public PluginVideoSourceDescriptor setLoginParameters(List<String> loginParameters) {
        this.loginParameters = loginParameters;
        return this;
    }
}
