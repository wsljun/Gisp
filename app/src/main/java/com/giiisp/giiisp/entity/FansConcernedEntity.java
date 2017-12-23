package com.giiisp.giiisp.entity;

import java.util.List;

/**
 * Created by Thinkpad on 2017/7/5.
 */

public class FansConcernedEntity extends BaseEntity{

    /**
     * result : 1
     * pageInfo : {"rows":[{"id":43,"name":null,"realName":"","nickName":null,"mobile":null,"pwd":null,"sex":"1","email":null,"token":null,"avatar":"http://o9kg05vzs.bkt.clouddn.com/2016-07-19_CMkyzH6m.png","major":null,"organization":null,"position":null,"self_introduction":null,"isVIP":"0","signature":"上善若水，立信如木","cityCode":null,"createTime":null,"updateTime":null,"isLocked":null},{"id":1,"name":null,"realName":"","nickName":null,"mobile":null,"pwd":null,"sex":"2","email":null,"token":null,"avatar":"http://o9kg05vzs.bkt.clouddn.com/2016-11-01_qG6akpfJ.png","major":null,"organization":null,"position":null,"self_introduction":null,"isVIP":"2","signature":"吾乃化育是也！","cityCode":null,"createTime":null,"updateTime":null,"isLocked":null}],"total":2}
     * code :
     * info : 操作成功！
     */

    private PageInfoBean pageInfo;



    public PageInfoBean getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfoBean pageInfo) {
        this.pageInfo = pageInfo;
    }


    public static class PageInfoBean {
        /**
         * rows : [{"id":43,"name":null,"realName":"","nickName":null,"mobile":null,"pwd":null,"sex":"1","email":null,"token":null,"avatar":"http://o9kg05vzs.bkt.clouddn.com/2016-07-19_CMkyzH6m.png","major":null,"organization":null,"position":null,"self_introduction":null,"isVIP":"0","signature":"上善若水，立信如木","cityCode":null,"createTime":null,"updateTime":null,"isLocked":null},{"id":1,"name":null,"realName":"","nickName":null,"mobile":null,"pwd":null,"sex":"2","email":null,"token":null,"avatar":"http://o9kg05vzs.bkt.clouddn.com/2016-11-01_qG6akpfJ.png","major":null,"organization":null,"position":null,"self_introduction":null,"isVIP":"2","signature":"吾乃化育是也！","cityCode":null,"createTime":null,"updateTime":null,"isLocked":null}]
         * total : 2
         */

        private int total;
        private List<RowsBean> rows;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<RowsBean> getRows() {
            return rows;
        }

        public void setRows(List<RowsBean> rows) {
            this.rows = rows;
        }

        public static class RowsBean {

            /**
             * uid : 255
             * realName : 傲
             * sex : 1
             * avatar : http://o9kg05vzs.bkt.clouddn.com/2017-02-04_1486204581406.jpg
             * signature :
             * isVIP : 3
             * organization :
             * major :
             * position :
             */

            private String uid;
            private String realName;
            private String sex;
            private String avatar;
            private String signature;
            private String isVIP;
            private String organization;
            private String major;
            private String position;

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getRealName() {
                return realName;
            }

            public void setRealName(String realName) {
                this.realName = realName;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getSignature() {
                return signature;
            }

            public void setSignature(String signature) {
                this.signature = signature;
            }

            public String getIsVIP() {
                return isVIP;
            }

            public void setIsVIP(String isVIP) {
                this.isVIP = isVIP;
            }

            public String getOrganization() {
                return organization;
            }

            public void setOrganization(String organization) {
                this.organization = organization;
            }

            public String getMajor() {
                return major;
            }

            public void setMajor(String major) {
                this.major = major;
            }

            public String getPosition() {
                return position;
            }

            public void setPosition(String position) {
                this.position = position;
            }
        }
    }
}
