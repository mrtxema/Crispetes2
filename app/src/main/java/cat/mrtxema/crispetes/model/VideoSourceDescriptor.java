package cat.mrtxema.crispetes.model;

import java.util.Collections;
import java.util.List;
import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

@Parcel(Parcel.Serialization.BEAN)
public class VideoSourceDescriptor {
    private final String baseUrl;
    private final String code;
    private final String name;
    private final String description;
    private final boolean supportsMovies;
    private final boolean supportsTvShows;
    private final String registrationUrl;
    private final List<LoginParameter> loginParameters;

    public VideoSourceDescriptor(VideoSourceDescriptorBuilder builder) {
        baseUrl = builder.getBaseUrl();
        code = builder.getCode();
        name = builder.getName();
        description = builder.getDescription();
        supportsMovies = builder.isSupportsMovies();
        supportsTvShows = builder.isSupportsTvShows();
        registrationUrl = builder.getRegistrationUrl();
        loginParameters = Collections.unmodifiableList(builder.getLoginParameters());
    }

    @ParcelConstructor
    VideoSourceDescriptor(String baseUrl, String code, String name, String description, boolean supportsMovies,
                          boolean supportsTvShows, String registrationUrl, List<LoginParameter> loginParameters) {
        this.baseUrl = baseUrl;
        this.code = code;
        this.name = name;
        this.description = description;
        this.supportsMovies = supportsMovies;
        this.supportsTvShows = supportsTvShows;
        this.registrationUrl = registrationUrl;
        this.loginParameters = Collections.unmodifiableList(loginParameters);
    }

    public static VideoSourceDescriptorBuilder builder() {
        return new VideoSourceDescriptorBuilder();
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

    public String getDescription() {
        return description;
    }

    public boolean isSupportsMovies() {
        return supportsMovies;
    }

    public boolean isSupportsTvShows() {
        return supportsTvShows;
    }

    public String getRegistrationUrl() {
        return registrationUrl;
    }

    public List<LoginParameter> getLoginParameters() {
        return loginParameters;
    }
}
