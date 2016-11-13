package cat.mrtxema.crispetes.api.videos.model;

public class PluginMovieQuality {
    private int level;
    private String description;

    public int getLevel() {
        return level;
    }

    public PluginMovieQuality setLevel(int level) {
        this.level = level;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public PluginMovieQuality setDescription(String description) {
        this.description = description;
        return this;
    }
}
