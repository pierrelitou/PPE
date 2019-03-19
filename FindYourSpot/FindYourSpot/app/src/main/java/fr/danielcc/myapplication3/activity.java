package fr.danielcc.myapplication3;

public class activity {

    private float lat;
    private float lng;
    private String title;
    private String desc;
    private String date;

    public activity(float lat, float lng, String title, String desc, String date) {
        this.lat = lat;
        this.lng = lng;
        this.title = title;
        this.desc = desc;
        this.date = date;
    }

    public float getLat() {
        return lat;
    }

    public float getLng() {
        return lng;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getDate() {
        return date;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setDate(String date) {
        this.date = date;
    }
}