package com.giiisp.giiisp.entity;

/**
 * Created by Thinkpad on 2017/8/2.
 */

public class UpDateAppEntity extends BaseEntity {
    /**
     * appInfo : {"packageName":"com.giiisp.giiisp","apkUrl":"com.giiisp.giiisp","versionCode":100001,"versionName":"1.0.1","shortDesc":"","detailDesc":"1.修复XXXXXXXXXX\n2.修复XXXXXXXXXX\n3.修复XXXXXXXXXX\n4.修复XXXXXXXXXX\n5.修复XXXXXXXXXX\n6.修复XXXXXXXXXX","apkSize":"6.6MB"}
     */

    private AppInfoBean appInfo;

    public AppInfoBean getAppInfo() {
        return appInfo;
    }

    public void setAppInfo(AppInfoBean appInfo) {
        this.appInfo = appInfo;
    }

    public static class AppInfoBean {
        /**
         * packageName : com.giiisp.giiisp
         * apkUrl : com.giiisp.giiisp
         * versionCode : 100001
         * versionName : 1.0.1
         * shortDesc :
         * detailDesc : 1.修复XXXXXXXXXX
         2.修复XXXXXXXXXX
         3.修复XXXXXXXXXX
         4.修复XXXXXXXXXX
         5.修复XXXXXXXXXX
         6.修复XXXXXXXXXX
         * apkSize : 6.6MB
         */

        private String packageName;
        private String apkUrl;
        private int versionCode;
        private String versionName;
        private String shortDesc;
        private String detailDesc;
        private String apkSize;

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        public String getApkUrl() {
            return apkUrl;
        }

        public void setApkUrl(String apkUrl) {
            this.apkUrl = apkUrl;
        }

        public int getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(int versionCode) {
            this.versionCode = versionCode;
        }

        public String getVersionName() {
            return versionName;
        }

        public void setVersionName(String versionName) {
            this.versionName = versionName;
        }

        public String getShortDesc() {
            return shortDesc;
        }

        public void setShortDesc(String shortDesc) {
            this.shortDesc = shortDesc;
        }

        public String getDetailDesc() {
            return detailDesc;
        }

        public void setDetailDesc(String detailDesc) {
            this.detailDesc = detailDesc;
        }

        public String getApkSize() {
            return apkSize;
        }

        public void setApkSize(String apkSize) {
            this.apkSize = apkSize;
        }
    }
   /* private AppInfo appInfo;

    public AppInfo getAppInfo() {
        return appInfo;
    }

    public void setAppInfo(AppInfo appInfo) {
        this.appInfo = appInfo;
    }

    public class AppInfo {
        private String detailDesc;                     //详情描述
        private String shortDesc;                      //简短描述
        private String packageName;                    //软件包名
        private int versionCode;                       //版本号
        private String versionName;                    //app版本名称
        private String apkUrl;                         //下载地址
        private String apkSize;                         //apk大小

        public String getApkSize() {
            return apkSize;
        }

        public void setApkSize(String apkSize) {
            this.apkSize = apkSize;
        }

        public String getDetailDesc() {
            return detailDesc;
        }

        public void setDetailDesc(String detailDesc) {
            this.detailDesc = detailDesc;
        }

        public String getShortDesc() {
            return shortDesc;
        }

        public void setShortDesc(String shortDesc) {
            this.shortDesc = shortDesc;
        }

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        public int getVersionCode() {
            return versionCode;
        }

        public void setVersionCode(int versionCode) {
            this.versionCode = versionCode;
        }

        public String getVersionName() {
            return versionName;
        }

        public void setVersionName(String versionName) {
            this.versionName = versionName;
        }

        public String getApkUrl() {
            return apkUrl;
        }

        public void setApkUrl(String apkUrl) {
            this.apkUrl = apkUrl;
        }
    }*/

}
