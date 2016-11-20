package cat.mrtxema.crispetes.model;

public class LinkBuilder {
    private String id;
    private String server;
    private Language audioLanguage;
    private boolean subtitles;
    private Language subtitleLanguage;
    private Quality videoQuality;
    private Quality audioQuality;

    public Link build() {
        return new Link(id, server, audioLanguage, subtitles, subtitleLanguage, videoQuality, audioQuality);
    }

    public String getId() {
        return id;
    }

    public LinkBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public String getServer() {
        return server;
    }

    public LinkBuilder setServer(String server) {
        this.server = server;
        return this;
    }

    public Language getAudioLanguage() {
        return audioLanguage;
    }

    public LinkBuilder setAudioLanguage(Language audioLanguage) {
        this.audioLanguage = audioLanguage;
        return this;
    }

    public boolean isSubtitles() {
        return subtitles;
    }

    public LinkBuilder setSubtitles(boolean subtitles) {
        this.subtitles = subtitles;
        return this;
    }

    public Language getSubtitleLanguage() {
        return subtitleLanguage;
    }

    public LinkBuilder setSubtitleLanguage(Language subtitleLanguage) {
        this.subtitleLanguage = subtitleLanguage;
        return this;
    }

    public Quality getVideoQuality() {
        return videoQuality;
    }

    public LinkBuilder setVideoQuality(Quality videoQuality) {
        this.videoQuality = videoQuality;
        return this;
    }

    public Quality getAudioQuality() {
        return audioQuality;
    }

    public LinkBuilder setAudioQuality(Quality audioQuality) {
        this.audioQuality = audioQuality;
        return this;
    }
}
