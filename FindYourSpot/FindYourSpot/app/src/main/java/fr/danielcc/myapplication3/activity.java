package fr.danielcc.myapplication3;

public class activity {
    private String nameActivity;
    private String location;
    private String description;
    private int lgt;
    private int lat;

    public activity(String nameActivity, String location, String description, int lgt, int lat) {
        this.nameActivity = nameActivity;
        this.location = location;
        this.description = description;
        this.lgt = lgt;
        this.lat = lat;
    }

    public String getNameActivity() {
        return nameActivity;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public int getLgt() {
        return lgt;
    }

    public int getLat() {
        return lat;
    }

    public void setNameActivity(String nameActivity) {
        this.nameActivity = nameActivity;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLgt(int lgt) {
        this.lgt = lgt;
    }

    public void setLat(int lat) {
        this.lat = lat;
    }
}
