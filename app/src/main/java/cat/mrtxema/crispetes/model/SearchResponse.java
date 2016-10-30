package cat.mrtxema.crispetes.model;

import java.util.Collections;
import java.util.List;

public class SearchResponse<T> {
    private final int page;
    private final int totalResults;
    private final int totalPages;
    private final List<T> results;

    public SearchResponse(int page, int totalResults, int totalPages, List<T> results) {
        this.page = page;
        this.totalResults = totalResults;
        this.totalPages = totalPages;
        this.results = Collections.unmodifiableList(results);
    }

    public int getPage() {
        return page;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public List<T> getResults() {
        return results;
    }
}
