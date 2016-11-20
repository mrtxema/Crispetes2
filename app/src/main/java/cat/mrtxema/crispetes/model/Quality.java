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
}
