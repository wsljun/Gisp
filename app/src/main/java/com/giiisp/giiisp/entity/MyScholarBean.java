package com.giiisp.giiisp.entity;

/**
 * Created by Thinkpad on 2017/6/26.
 */

public class MyScholarBean {
    /**
     * oid : 5
     * realName :
     * avatar : http://o9kg05vzs.bkt.clouddn.com/2016-11-01_qG6akpfJ.png
     */
    private String id;
    private String oid;
    private String realName;
    private String avatar;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
