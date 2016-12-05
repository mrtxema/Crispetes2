package cat.mrtxema.crispetes.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

@Parcel(Parcel.Serialization.BEAN)
public class VideoSource {
    private final Integer id;
    private final String baseUrl;
    private final String code;
    private final String name;
    private final boolean supportsMovies;
    private final boolean supportsTvShows;
    private final Map<String,String> credentials;
    private final int order;

    public static VideoSourceBuilder builder() {
        return new VideoSourceBuilder();
    }

    public VideoSource(VideoSourceBuilder builder) {
        this.id = builder.getId();
        this.baseUrl = builder.getBaseUrl();
        this.code = builder.getCode();
        this.name = builder.getName();
        this.supportsMovies = builder.isSupportsMovies();
        this.supportsTvShows = builder.isSupportsTvShows();
        this.credentials = builder.getCredentials() != null ?
                Collections.unmodifiableMap(new HashMap<>(builder.getCredentials())) :
                Collections.<String, String>emptyMap();
        this.order = builder.getOrder();
    }

    @ParcelConstructor
    VideoSource(Integer id, String baseUrl, String code, String name, boolean supportsMovies,
                boolean supportsTvShows, Map<String,String> credentials, int order) {
        this.id = id;
        this.baseUrl = baseUrl;
        this.code = code;
        this.name = name;
        this.supportsMovies = supportsMovies;
        this.supportsTvShows = supportsTvShows;
        this.credentials = credentials != null ?
                Collections.unmodifiableMap(new HashMap<>(credentials)) :
                Collections.<String, String>emptyMap();
        this.order = order;
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

    public int getOrder() {
        return order;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VideoSource that = (VideoSource) o;

        if (supportsMovies != that.supportsMovies) return false;
        if (supportsTvShows != that.supportsTvShows) return false;
        if (order != that.order) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (baseUrl != null ? !baseUrl.equals(that.baseUrl) : that.baseUrl != null) return false;
        if (code != null ? !code.equals(that.code) : that.code != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return credentials != null ? credentials.equals(that.credentials) : that.credentials == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (baseUrl != null ? baseUrl.hashCode() : 0);
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (supportsMovies ? 1 : 0);
        result = 31 * result + (supportsTvShows ? 1 : 0);
        result = 31 * result + (credentials != null ? credentials.hashCode() : 0);
        result = 31 * result + order;
        return result;
    }
}
