package cat.mrtxema.crispetes.model;

public class Link {
    private final String id;
    private final String server;
    private final Language audioLanguage;
    private final boolean subtitles;
    private final Language subtitleLanguage;
    private final Quality videoQuality;
    private final Quality audioQuality;

    public Link(String id, String server, Language audioLanguage, boolean subtitles, Language subtitleLanguage, Quality videoQuality, Quality audioQuality) {
        this.id = id;
        this.server = server;
        this.audioLanguage = audioLanguage;
        this.subtitles = subtitles;
        this.subtitleLanguage = subtitleLanguage;
        this.videoQuality = videoQuality;
        this.audioQuality = audioQuality;
    }

    public static LinkBuilder builder() {
        return new LinkBuilder();
    }

    public String getId() {
        return id;
    }

    public String getServer() {
        return server;
    }

    public Language getAudioLanguage() {
        return audioLanguage;
    }

    public boolean isSubtitles() {
        return subtitles;
    }

    public Language getSubtitleLanguage() {
        return subtitleLanguage;
    }

    public Quality getVideoQuality() {
        return videoQuality;
    }

    public Quality getAudioQuality() {
        return audioQuality;
    }
}
