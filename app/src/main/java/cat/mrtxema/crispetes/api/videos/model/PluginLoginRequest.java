package cat.mrtxema.crispetes.api.videos.model;

import java.util.Map;

public class PluginLoginRequest {
    private Map<String,String> parameters;

    public Map<String, String> getParameters() {
        return parameters;
    }

    public PluginLoginRequest setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
        return this;
    }
}
