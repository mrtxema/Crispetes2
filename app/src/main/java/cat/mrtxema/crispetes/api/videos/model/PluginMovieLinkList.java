package cat.mrtxema.crispetes.api.videos.model;

import java.util.List;

public class PluginMovieLinkList {
    private List<PluginMovieLink> links;

    public List<PluginMovieLink> getLinks() {
        return links;
    }

    public PluginMovieLinkList setLinks(List<PluginMovieLink> links) {
        this.links = links;
        return this;
    }
}
