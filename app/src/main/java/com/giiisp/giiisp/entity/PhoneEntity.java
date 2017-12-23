package com.giiisp.giiisp.entity;

/**
 * Created by Thinkpad on 2017/5/3.
 */

public class PhoneEntity extends BaseEntity{
    private int isMobileExist;
    private String mobile;
    private String sex;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getIsMobileExist() {
        return isMobileExist;
    }

    public void setIsMobileExist(int isMobileExist) {
        this.isMobileExist = isMobileExist;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
