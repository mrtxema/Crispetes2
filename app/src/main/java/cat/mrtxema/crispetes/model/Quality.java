package cat.mrtxema.crispetes.model;

public class Quality {
    private final int level;
    private final String description;

    public Quality(int level, String description) {
        this.level = level;
        this.description = description;
    }

    public int getLevel() {
        return level;
    }

    public String getDescription() {
        return description;
    }

    public String getExtendedDescription(CharSequence qualityWord) {
        if (getDescription() != null) {
            return String.format("%s (%d)", getDescription(), getLevel());
        } else {
            return String.format("%s %d", qualityWord, getLevel());
        }
    }
}
