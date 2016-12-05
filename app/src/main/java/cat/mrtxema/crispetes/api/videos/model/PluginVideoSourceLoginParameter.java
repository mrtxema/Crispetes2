package cat.mrtxema.crispetes.api.videos.model;

public class PluginVideoSourceLoginParameter {
    private String key;
    private String label;
    private boolean password;

    public String getKey() {
        return key;
    }

    public PluginVideoSourceLoginParameter setKey(String key) {
        this.key = key;
        return this;
    }

    public String getLabel() {
        return label;
    }

    public PluginVideoSourceLoginParameter setLabel(String label) {
        this.label = label;
        return this;
    }

    public boolean isPassword() {
        return password;
    }

    public PluginVideoSourceLoginParameter setPassword(boolean password) {
        this.password = password;
        return this;
    }
}
