package co.edu.konradlorenz.excolnet.Utils;

public class Users {
    private String displayName;
    private String photoUrl;

    public Users(String displayName, String photoUrl) {
        this.displayName = displayName;
        this.photoUrl = photoUrl;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}


