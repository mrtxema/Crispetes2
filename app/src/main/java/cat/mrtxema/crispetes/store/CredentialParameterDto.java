package cat.mrtxema.crispetes.store;

import java.util.Map;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "credentials")
public class CredentialParameterDto {
    public static final String FIELD_ID = "id";
    public static final String FIELD_VIDEO_SOURCE_ID = "video_source_id";
    public static final String FIELD_KEY = "key";
    public static final String FIELD_VALUE = "value";

    @DatabaseField(generatedId = true, canBeNull = false, columnName = FIELD_ID)
    private int id;
    @DatabaseField(canBeNull = false, columnName = FIELD_VIDEO_SOURCE_ID, foreign = true)
    private VideoSourceDto videoSource;
    @DatabaseField(canBeNull = false, columnName = FIELD_KEY)
    private String key;
    @DatabaseField(canBeNull = false, columnName = FIELD_VALUE)
    private String value;

    public int getId() {
        return id;
    }

    public CredentialParameterDto setId(int id) {
        this.id = id;
        return this;
    }

    public VideoSourceDto getVideoSource() {
        return videoSource;
    }

    public CredentialParameterDto setVideoSource(VideoSourceDto videoSource) {
        this.videoSource = videoSource;
        return this;
    }

    public String getKey() {
        return key;
    }

    public CredentialParameterDto setKey(String key) {
        this.key = key;
        return this;
    }

    public String getValue() {
        return value;
    }

    public CredentialParameterDto setValue(String value) {
        this.value = value;
        return this;
    }
}
