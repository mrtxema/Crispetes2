package cat.mrtxema.crispetes.api.videos.model;

public class PluginMovieLink {
    private String id;
    private String server;
    private PluginMovieLocale audioLanguage;
    private boolean subtitles;
    private PluginMovieLocale subtitleLanguage;
    private PluginMovieQuality videoQuality;
    private PluginMovieQuality audioQuality;

    public String getId() {
        return id;
    }

    public PluginMovieLink setId(String id) {
        this.id = id;
        return this;
    }

    public String getServer() {
        return server;
    }

    public PluginMovieLink setServer(String server) {
        this.server = server;
        return this;
    }

    public PluginMovieLocale getAudioLanguage() {
        return audioLanguage;
    }

    public PluginMovieLink setAudioLanguage(PluginMovieLocale audioLanguage) {
        this.audioLanguage = audioLanguage;
        return this;
    }

    public boolean isSubtitles() {
        return subtitles;
    }

    public PluginMovieLink setSubtitles(boolean subtitles) {
        this.subtitles = subtitles;
        return this;
    }

    public PluginMovieLocale getSubtitleLanguage() {
        return subtitleLanguage;
    }

    public PluginMovieLink setSubtitleLanguage(PluginMovieLocale subtitleLanguage) {
        this.subtitleLanguage = subtitleLanguage;
        return this;
    }

    public PluginMovieQuality getVideoQuality() {
        return videoQuality;
    }

    public PluginMovieLink setVideoQuality(PluginMovieQuality videoQuality) {
        this.videoQuality = videoQuality;
        return this;
    }

    public PluginMovieQuality getAudioQuality() {
        return audioQuality;
    }

    public PluginMovieLink setAudioQuality(PluginMovieQuality audioQuality) {
        this.audioQuality = audioQuality;
        return this;
    }
}
