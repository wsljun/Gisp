package com.giiisp.giiisp.entity;

/**
 * Created by MASAILA on 16/5/13.
 */
public class Song {

    private String path;
    private int duration;
    private long durations;
    private int position;
    private String photoPath="";
    private String  title="";

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    private String type;

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getDurations() {
        return durations;
    }

    public void setDurations(long durations) {
        this.durations = durations;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }


    public String getPath() {
        return path;
    }

    public Song() {
    }

    public Song(String path) {
        this.path = path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
