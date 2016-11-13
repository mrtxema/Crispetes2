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

    @DatabaseField(generatedId = true, canBeNull = false, columnName = FIELD_ID)
    private Integer id;
    @DatabaseField(canBeNull = false, columnName = FIELD_BASE_URL)
    private String baseUrl;
    @DatabaseField(canBeNull = false, columnName = FIELD_CODE)
    private String code;
    @DatabaseField(canBeNull = false, columnName = FIELD_NAME)
    private String name;
    @ForeignCollectionField(eager = true)
    Collection<CredentialParameterDto> credentials;

    @Override
    public VideoSourceDto fromModel(VideoSource videoSource) {
        id = videoSource.getId();
        baseUrl = videoSource.getBaseUrl();
        code = videoSource.getCode();
        name = videoSource.getName();
        credentials = convertCredentialsFromModel(videoSource.getCredentials());
        return this;
    }

    @Override
    public VideoSource toModel() {
        return new VideoSource(id, baseUrl, code, name, convertCredentialsToModel(credentials));
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

    public Collection<CredentialParameterDto> getCredentials() {
        return credentials;
    }

    public VideoSourceDto setCredentials(Collection<CredentialParameterDto> credentials) {
        this.credentials = credentials;
        return this;
    }
}
