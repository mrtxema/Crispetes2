package cat.mrtxema.crispetes.model;

import java.util.Map;

public class VideoSourceBuilder {
    private Integer id;
    private String baseUrl;
    private String code;
    private String name;
    private boolean supportsMovies;
    private boolean supportsTvShows;
    private Map<String,String> credentials;
    private int order;

    public VideoSource build() {
        return new VideoSource(this);
    }

    public Integer getId() {
        return id;
    }

    public VideoSourceBuilder setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public VideoSourceBuilder setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    public String getCode() {
        return code;
    }

    public VideoSourceBuilder setCode(String code) {
        this.code = code;
        return this;
    }

    public String getName() {
        return name;
    }

    public VideoSourceBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public boolean isSupportsMovies() {
        return supportsMovies;
    }

    public VideoSourceBuilder setSupportsMovies(boolean supportsMovies) {
        this.supportsMovies = supportsMovies;
        return this;
    }

    public boolean isSupportsTvShows() {
        return supportsTvShows;
    }

    public VideoSourceBuilder setSupportsTvShows(boolean supportsTvShows) {
        this.supportsTvShows = supportsTvShows;
        return this;
    }

    public Map<String, String> getCredentials() {
        return credentials;
    }

    public VideoSourceBuilder setCredentials(Map<String, String> credentials) {
        this.credentials = credentials;
        return this;
    }

    public int getOrder() {
        return order;
    }

    public VideoSourceBuilder setOrder(int order) {
        this.order = order;
        return this;
    }
}
