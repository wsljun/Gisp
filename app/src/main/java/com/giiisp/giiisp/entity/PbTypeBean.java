package com.giiisp.giiisp.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Thinkpad on 2017/6/20.
 */

public class PbTypeBean{

    /**
     * id : 2
     * uid : 1
     * content : 化学
     * flag : 1
     * isDel : 0
     * createTime : 1970.01.01 00:00
     */

    private int id;
    private int uid;
    private String content;
    private String flag;
    private String isDel;
    private String createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uidX) {
        this.uid = uidX;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getIsDel() {
        return isDel;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
