package cat.mrtxema.crispetes.api.tmdb;

import com.google.gson.annotations.SerializedName;

public abstract class TmdbCreditItem extends BasicTmdbObject {
    @SerializedName("credit_id")
    private String creditId;
    @SerializedName("profile_path")
    private String profilePath;

    public String getCreditId() {
        return creditId;
    }

    public void setCreditId(String creditId) {
        this.creditId = creditId;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }
}
