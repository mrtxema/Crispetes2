package cat.mrtxema.crispetes.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LinkList {
    private final List<Link> links;
    private final Map<VideoSource,List<Link>> linksBySource;

    public LinkList(List<Link> links) {
        this.links = links;
        linksBySource = new LinkedHashMap<>();
        for (Link link : links) {
            VideoSource videoSource = link.getVideoSource();
            if (!linksBySource.containsKey(videoSource)) {
                linksBySource.put(videoSource, new ArrayList<Link>());
            }
            linksBySource.get(videoSource).add(link);
        }
    }

    public List<VideoSource> getVideoSources() {
        return new ArrayList<>(linksBySource.keySet());
    }

    public Map<VideoSource,List<Link>> getBeersByBrewer() {
        return Collections.unmodifiableMap(linksBySource);
    }

    public List<Link> getLinks() {
        return Collections.unmodifiableList(links);
    }

    public boolean isEmpty() {
        return linksBySource.isEmpty();
    }
}
