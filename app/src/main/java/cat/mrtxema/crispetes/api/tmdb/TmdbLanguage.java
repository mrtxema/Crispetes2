package cat.mrtxema.crispetes.api.tmdb;

import com.google.gson.annotations.SerializedName;

public class TmdbLanguage {
    @SerializedName("iso_639_1")
    private String isoCode;
    private String name;

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
