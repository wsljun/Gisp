package com.giiisp.giiisp.entity;

/**
 * Entity基类
 * Created by Thinkpad on 2017/5/3.
 */

public class BaseEntity {

    private int result;
    private String code;
    private String info;
    private String uid;
    private String firstUser ;

    public String getNewUser() {
        return firstUser;
    }

    public void setNewUser(String newUser) {
        this.firstUser = newUser;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "result=" + result +
                ", code='" + code + '\'' +
                ", info='" + info + '\'' +
                ", uid='" + uid + '\'' +
                ", firstUser='" + firstUser + '\'' +
                '}';
    }
}
