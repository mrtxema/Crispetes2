package cat.mrtxema.crispetes.api.tmdb.model;

import java.util.List;

public class TmdbCredits {
    private List<TmdbCast> cast;
    private List<TmdbCrew> crew;

    public List<TmdbCast> getCast() {
        return cast;
    }

    public void setCast(List<TmdbCast> cast) {
        this.cast = cast;
    }

    public List<TmdbCrew> getCrew() {
        return crew;
    }

    public void setCrew(List<TmdbCrew> crew) {
        this.crew = crew;
    }
}
