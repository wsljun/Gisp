package com.giiisp.giiisp.utils;

/**
 * Created by Android on 2016/12/1.
 */

public class PageId {

    public static final int PAGE_BASE_ID = 100;

    public static final int PAGE_AD = PAGE_BASE_ID * 1; //AD页面
    public static final int PAGE_LOGINPAGE = PAGE_BASE_ID * 2; //登录界面
    public static final int PAGE_MINE = PAGE_BASE_ID * 3; //我的页面

    public static class PageLogin {
        public static final int PAGE_HINTLOGIN = 0; //提示登录
        public static final int PAGE_LOGIN = 1; //登录
        public static final int PAGE_ENTERPHONE = 2; //手机号输入
        public static final int PAGE_ENROLL = 3; //注册
        public static final int PAGE_UPDATE_PWD = 4; //忘记密码
        public static final int PAGE_FIND_PWD = 5; //忘记密码
        public static final int PAGE_USER_AGREEMENT = 6; //忘记密码

    }
    public static class PageMobileCodeType {
        public static final int USER_ENROLL = 1; //用户注册
        public static final int CHANGE_PASSWORD = 2; //修改密码
        public static final int CHANGE_MOBILE = 3; //修改手机号
    }

}
