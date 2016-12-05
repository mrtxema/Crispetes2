package cat.mrtxema.crispetes.model;

import org.parceler.Parcel;
import org.parceler.ParcelConstructor;

@Parcel(Parcel.Serialization.BEAN)
public class LoginParameter {
    private final String key;
    private final String label;
    private final boolean password;

    @ParcelConstructor
    public LoginParameter(String key, String label, boolean password) {
        this.key = key;
        this.label = label;
        this.password = password;
    }

    public String getKey() {
        return key;
    }

    public String getLabel() {
        return label;
    }

    public boolean isPassword() {
        return password;
    }
}
