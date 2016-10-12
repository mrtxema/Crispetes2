package cat.mrtxema.crispetes.api.tmdb;

import java.util.List;

public class TmdbConfiguration {
    private TmdbImageConfiguration images;
    private List<String> change_keys;

    public TmdbImageConfiguration getImages() {
        return images;
    }

    public void setImages(TmdbImageConfiguration images) {
        this.images = images;
    }

    public List<String> getChange_keys() {
        return change_keys;
    }

    public void setChange_keys(List<String> change_keys) {
        this.change_keys = change_keys;
    }
}
