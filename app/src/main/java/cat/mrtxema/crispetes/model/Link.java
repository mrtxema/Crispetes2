package cat.mrtxema.crispetes.model;

public class Link {
    private final VideoSource videoSource;
    private final String id;
    private final String server;
    private final Language audioLanguage;
    private final boolean subtitles;
    private final Language subtitleLanguage;
    private final Quality videoQuality;
    private final Quality audioQuality;

    public Link(LinkBuilder builder) {
        this.videoSource = builder.getVideoSource();
        this.id = builder.getId();
        this.server = builder.getServer();
        this.audioLanguage = builder.getAudioLanguage();
        this.subtitles = builder.isSubtitles();
        this.subtitleLanguage = builder.getSubtitleLanguage();
        this.videoQuality = builder.getVideoQuality();
        this.audioQuality = builder.getAudioQuality();
    }

    public static LinkBuilder builder() {
        return new LinkBuilder();
    }

    public VideoSource getVideoSource() {
        return videoSource;
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
