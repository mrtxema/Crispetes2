package cat.mrtxema.crispetes.api.videos.model;

public class PluginMovieLocale {
    private String languageCode;
    private String languageName;
    private String countryCode;
    private String countryName;

    public String getLanguageCode() {
        return languageCode;
    }

    public PluginMovieLocale setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
        return this;
    }

    public String getLanguageName() {
        return languageName;
    }

    public PluginMovieLocale setLanguageName(String languageName) {
        this.languageName = languageName;
        return this;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public PluginMovieLocale setCountryCode(String countryCode) {
        this.countryCode = countryCode;
        return this;
    }

    public String getCountryName() {
        return countryName;
    }

    public PluginMovieLocale setCountryName(String countryName) {
        this.countryName = countryName;
        return this;
    }
}
