package cat.mrtxema.crispetes.api.tmdb.model;

import com.google.gson.annotations.SerializedName;

public class TmdbErrorResponse {
    @SerializedName("status_code")
    private int code;
    @SerializedName("status_message")
    private String message;

    public int getCode() {
        return code;
    }

    public TmdbErrorResponse setCode(int code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public TmdbErrorResponse setMessage(String message) {
        this.message = message;
        return this;
    }
}
