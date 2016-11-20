package cat.mrtxema.crispetes.store;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import cat.mrtxema.crispetes.model.VideoSource;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "video_source")
public class VideoSourceDto implements Dto<VideoSource> {
    public static final String FIELD_ID = "id";
    public static final String FIELD_BASE_URL = "base_url";
    public static final String FIELD_CODE = "code";
    public static final String FIELD_NAME = "name";
    public static final String FIELD_SUPPORTS_MOVIES = "suuports_movies";
    public static final String FIELD_SUPPORTS_TVSHOWS = "supports_tvshows";

    @DatabaseField(generatedId = true, canBeNull = false, columnName = FIELD_ID)
    private Integer id;
    @DatabaseField(canBeNull = false, columnName = FIELD_BASE_URL)
    private String baseUrl;
    @DatabaseField(canBeNull = false, columnName = FIELD_CODE)
    private String code;
    @DatabaseField(canBeNull = false, columnName = FIELD_NAME)
    private String name;
    @DatabaseField(canBeNull = false, columnName = FIELD_SUPPORTS_MOVIES)
    private boolean supportsMovies;
    @DatabaseField(canBeNull = false, columnName = FIELD_SUPPORTS_TVSHOWS)
    private boolean supportsTvShows;
    @ForeignCollectionField(eager = true)
    Collection<CredentialParameterDto> credentials;

    @Override
    public VideoSourceDto fromModel(VideoSource videoSource) {
        id = videoSource.getId();
        baseUrl = videoSource.getBaseUrl();
        code = videoSource.getCode();
        name = videoSource.getName();
        supportsMovies = videoSource.isSupportsMovies();
        supportsTvShows = videoSource.isSupportsTvShows();
        credentials = convertCredentialsFromModel(videoSource.getCredentials());
        return this;
    }

    @Override
    public VideoSource toModel() {
        return new VideoSource(id, baseUrl, code, name, supportsMovies, supportsTvShows, convertCredentialsToModel(credentials));
    }

    private Collection<CredentialParameterDto> convertCredentialsFromModel(Map<String, String> credentials) {
        List<CredentialParameterDto> credentialParameterDtos = new ArrayList<>();
        for (Map.Entry<String,String> entry : credentials.entrySet()) {
            credentialParameterDtos.add(new CredentialParameterDto()
                    .setVideoSource(this)
                    .setKey(entry.getKey())
                    .setValue(entry.getValue())
            );
        }
        return credentialParameterDtos;
    }

    private Map<String, String> convertCredentialsToModel(Collection<CredentialParameterDto> credentialParameterDtos) {
        Map<String,String> credentials = new HashMap<>();
        for (CredentialParameterDto credentialParameterDto : credentialParameterDtos) {
            credentials.put(credentialParameterDto.getKey(), credentialParameterDto.getValue());
        }
        return credentials;
    }

    public Integer getId() {
        return id;
    }

    public VideoSourceDto setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public VideoSourceDto setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    public String getCode() {
        return code;
    }

    public VideoSourceDto setCode(String code) {
        this.code = code;
        return this;
    }

    public String getName() {
        return name;
    }

    public VideoSourceDto setName(String name) {
        this.name = name;
        return this;
    }

    public boolean isSupportsMovies() {
        return supportsMovies;
    }

    public VideoSourceDto setSupportsMovies(boolean supportsMovies) {
        this.supportsMovies = supportsMovies;
        return this;
    }

    public boolean isSupportsTvShows() {
        return supportsTvShows;
    }

    public VideoSourceDto setSupportsTvShows(boolean supportsTvShows) {
        this.supportsTvShows = supportsTvShows;
        return this;
    }

    public Collection<CredentialParameterDto> getCredentials() {
        return credentials;
    }

    public VideoSourceDto setCredentials(Collection<CredentialParameterDto> credentials) {
        this.credentials = credentials;
        return this;
    }
}
