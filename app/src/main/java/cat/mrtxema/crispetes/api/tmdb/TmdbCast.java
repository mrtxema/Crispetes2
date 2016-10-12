package cat.mrtxema.crispetes.api.tmdb;

import com.google.gson.annotations.SerializedName;

public class TmdbCast extends TmdbCreditItem {
    @SerializedName("cast_id")
    private Integer castId;
    private String character;
    private Integer order;

    public Integer getCastId() {
        return castId;
    }

    public void setCastId(Integer castId) {
        this.castId = castId;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
