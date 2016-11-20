package cat.mrtxema.crispetes.model;

import java.util.Collections;
import java.util.Map;

public class VideoSource {
    private final Integer id;
    private final String baseUrl;
    private final String code;
    private final String name;
    private final boolean supportsMovies;
    private final boolean supportsTvShows;
    private final Map<String,String> credentials;

    public VideoSource(String baseUrl, String code, String name, boolean supportsMovies, boolean supportsTvShows, Map<String, String> credentials) {
        this(null, baseUrl, code, name, supportsMovies, supportsTvShows, credentials);
    }

    public VideoSource(Integer id, String baseUrl, String code, String name, boolean supportsMovies, boolean supportsTvShows, Map<String, String> credentials) {
        this.id = id;
        this.baseUrl = baseUrl;
        this.code = code;
        this.name = name;
        this.supportsMovies = supportsMovies;
        this.supportsTvShows = supportsTvShows;
        this.credentials = Collections.unmodifiableMap(credentials);
    }

    public Integer getId() {
        return id;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public boolean isSupportsMovies() {
        return supportsMovies;
    }

    public boolean isSupportsTvShows() {
        return supportsTvShows;
    }

    public Map<String, String> getCredentials() {
        return credentials;
    }
}
