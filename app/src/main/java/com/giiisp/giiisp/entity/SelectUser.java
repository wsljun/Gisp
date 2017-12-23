package com.giiisp.giiisp.entity;

import java.util.List;

/**
 * Created by Administrator on 2017/12/21.
 */

public class SelectUser extends BaseEntity {
    private List<UserInfoEntity.UserInfoBean> userInfo;

    public List<UserInfoEntity.UserInfoBean> getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(List<UserInfoEntity.UserInfoBean> userInfo) {
        this.userInfo = userInfo;
    }
}
