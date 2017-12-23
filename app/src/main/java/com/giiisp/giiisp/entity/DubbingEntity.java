package com.giiisp.giiisp.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by Thinkpad on 2017/4/25.
 */

public class DubbingEntity implements MultiItemEntity {
    private int itemType;
    private String dubbingUrl;
    private String language;
    private String time;
    private String duration;

    @Override
    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public String getDubbingUrl() {
        return dubbingUrl;
    }

    public void setDubbingUrl(String dubbingUrl) {
        this.dubbingUrl = dubbingUrl;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
