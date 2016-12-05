package cat.mrtxema.crispetes.model;

public class Language {
    private final String languageCode;
    private final String languageName;
    private final String countryCode;
    private final String countryName;

    public Language(String languageCode, String languageName, String countryCode, String countryName) {
        this.languageCode = languageCode;
        this.languageName = languageName;
        this.countryCode = countryCode;
        this.countryName = countryName;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public String getLanguageName() {
        return languageName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getDescription() {
        StringBuffer result = new StringBuffer(getText(getLanguageName(), getLanguageCode()));
        String country = getText(getCountryName(), getCountryCode());
        if (country != null) {
            if (result.length() > 0) {
                result.append(' ');
            }
            result.append('(');
            result.append(country);
            result.append(')');
        }
        return result.toString();
    }

    private String getText(String name, String code) {
        return name != null ? name : (code != null ? code : "");
    }
}
