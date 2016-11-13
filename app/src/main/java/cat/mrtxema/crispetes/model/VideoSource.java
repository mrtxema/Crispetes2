package cat.mrtxema.crispetes.model;

import java.util.Collections;
import java.util.Map;

public class VideoSource {
    private final Integer id;
    private final String baseUrl;
    private final String code;
    private final String name;
    private final Map<String,String> credentials;

    public VideoSource(String baseUrl, String code, String name, Map<String, String> credentials) {
        this(null, baseUrl, code, name, credentials);
    }

    public VideoSource(Integer id, String baseUrl, String code, String name, Map<String, String> credentials) {
        this.id = id;
        this.baseUrl = baseUrl;
        this.code = code;
        this.name = name;
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

    public Map<String, String> getCredentials() {
        return credentials;
    }
}
