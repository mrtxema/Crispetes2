package cat.mrtxema.crispetes.model;

public class Crew {
    private final String name;
    private final String job;
    private final String profileImageUrl;

    public Crew(String name, String job, String profileImageUrl) {
        this.name = name;
        this.job = job;
        this.profileImageUrl = profileImageUrl;
    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    @Override
    public String toString() {
        return "Crew{" +
                "name='" + name + '\'' +
                ", job='" + job + '\'' +
                '}';
    }
}
