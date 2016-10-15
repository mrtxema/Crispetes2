package cat.mrtxema.crispetes.model;

public class Cast {
    private final String name;
    private final String character;
    private final Integer order;
    private final String profileImageUrl;

    public Cast(String name, String character, Integer order, String profileImageUrl) {
        this.name = name;
        this.character = character;
        this.order = order;
        this.profileImageUrl = profileImageUrl;
    }

    public String getName() {
        return name;
    }

    public String getCharacter() {
        return character;
    }

    public Integer getOrder() {
        return order;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    @Override
    public String toString() {
        return "Cast{" +
                "name='" + name + '\'' +
                ", character='" + character + '\'' +
                '}';
    }
}
