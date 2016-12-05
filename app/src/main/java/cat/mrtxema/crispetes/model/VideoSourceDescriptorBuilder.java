package cat.mrtxema.crispetes.model;

import java.util.List;

public class VideoSourceDescriptorBuilder {
    private String baseUrl;
    private String code;
    private String name;
    private String description;
    private boolean supportsMovies;
    private boolean supportsTvShows;
    private String registrationUrl;
    private List<LoginParameter> loginParameters;

    public VideoSourceDescriptor build() {
        return new VideoSourceDescriptor(this);
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public VideoSourceDescriptorBuilder setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    public String getCode() {
        return code;
    }

    public VideoSourceDescriptorBuilder setCode(String code) {
        this.code = code;
        return this;
    }

    public String getName() {
        return name;
    }

    public VideoSourceDescriptorBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public VideoSourceDescriptorBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public boolean isSupportsMovies() {
        return supportsMovies;
    }

    public VideoSourceDescriptorBuilder setSupportsMovies(boolean supportsMovies) {
        this.supportsMovies = supportsMovies;
        return this;
    }

    public boolean isSupportsTvShows() {
        return supportsTvShows;
    }

    public VideoSourceDescriptorBuilder setSupportsTvShows(boolean supportsTvShows) {
        this.supportsTvShows = supportsTvShows;
        return this;
    }

    public String getRegistrationUrl() {
        return registrationUrl;
    }

    public VideoSourceDescriptorBuilder setRegistrationUrl(String registrationUrl) {
        this.registrationUrl = registrationUrl;
        return this;
    }

    public List<LoginParameter> getLoginParameters() {
        return loginParameters;
    }

    public VideoSourceDescriptorBuilder setLoginParameters(List<LoginParameter> loginParameters) {
        this.loginParameters = loginParameters;
        return this;
    }
}
