import java.io.Serializable;

public class ProfileData implements Serializable {
    String profile;

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getProfile() {

        return profile;
    }

    public ProfileData(String profile) {

        this.profile = profile;
    }
}
