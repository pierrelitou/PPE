package fr.danielcc.findyourspot;

public class Activity {

    private float lat;
    private float lng;
    private String title;
    private String desc;
    private int event;
    private String color;


    public Activity(float lat, float lng, String title, String desc,int event) {
        this.lat = lat;
        this.lng = lng;
        this.title = title;
        this.desc = desc;
        this.event = event;
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

    public int getEvent() {
        return event;
    }

}
