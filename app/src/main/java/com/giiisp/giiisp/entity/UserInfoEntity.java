package com.giiisp.giiisp.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/12/23.
 */

public class UserInfoEntity extends BaseEntity {

    /**
     * userInfo : {"realName":"陈昭宇","school":"中国科学院","sex":"1","domain":"天文大数据挖掘","mobile":"11111222222","degree":"博士后","id":443,"position":"","avatar":"http://oq2xwecq0.bkt.clouddn.com/ass_426_12_570.png","email":"","isVIP":"2"}
     * paper : {"id":0,"title":"","authors":{"rows":[],"total":0},"digest":"","version":""}
     * num : {"uid":443,"followedNum":0,"followNum":0,"summarizeNum":0,"paperNum":0}
     * summarize : {"id":4494,"title":"集思谱商业计划书","authors":{"rows":[{"id":4,"pbid":4494,"author":"陈昭宇","email":"","isGiiisp":"0"},{"id":5,"pbid":4494,"author":"马伟明","email":"","isGiiisp":"0"},{"id":6,"pbid":4494,"author":"潘建伟","email":"","isGiiisp":"0"}],"total":3},"digest":"","version":"0"}
     * authen : {"major":"","organization":"","position":"","department":""}
     * isFollowed : 0
     * introduction : [{"id":4,"uid":443,"school":"中国科学院","timeStart":"2011","timeEnd":"","major":"天文大数据挖掘","degree":"博士后","eduBackground":"博士后研究生","isDel":"0","createTime":"2017.07.15 10:42"},{"id":3,"uid":443,"school":"北京大学","timeStart":"2009","timeEnd":"2011","major":"天体物理","degree":"博士后","eduBackground":"博士后研究生","isDel":"0","createTime":"2017.07.15 10:42"},{"id":2,"uid":443,"school":"中国科学院研究生院","timeStart":"2004","timeEnd":"2009","major":"天体物理","degree":"博士","eduBackground":"博士研究生","isDel":"0","createTime":"2017.07.15 10:42"},{"id":1,"uid":443,"school":"湖南大学","timeStart":"1997","timeEnd":"2004","major":"土木工程","degree":"硕士","eduBackground":"硕士研究生","isDel":"0","createTime":"2017.07.15 10:42"}]
     */

    private UserInfoBean userInfo;
    private SummarizeBean paper;
    private NumBean num;
    private SummarizeBean summarize;
    private AuthenBean authen;
    private String isFollowed; // 0 已关注 1 未关注
    private List<IntroductionBean> introduction;

    public UserInfoBean getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfoBean userInfo) {
        this.userInfo = userInfo;
    }

    public SummarizeBean getPaper() {
        return paper;
    }

    public void setPaper(SummarizeBean paper) {
        this.paper = paper;
    }

    public NumBean getNum() {
        return num;
    }

    public void setNum(NumBean num) {
        this.num = num;
    }

    public SummarizeBean getSummarize() {
        return summarize;
    }

    public void setSummarize(SummarizeBean summarize) {
        this.summarize = summarize;
    }

    public AuthenBean getAuthen() {
        return authen;
    }

    public void setAuthen(AuthenBean authen) {
        this.authen = authen;
    }

    public String getIsFollowed() {
        return isFollowed;
    }

    public void setIsFollowed(String isFollowed) {
        this.isFollowed = isFollowed;
    }

    public List<IntroductionBean> getIntroduction() {
        return introduction;
    }

    public void setIntroduction(List<IntroductionBean> introduction) {
        this.introduction = introduction;
    }

    public static class UserInfoBean {
        /**
         * realName : 陈昭宇
         * school : 中国科学院
         * sex : 1
         * domain : 天文大数据挖掘
         * mobile : 11111222222
         * degree : 博士后
         * id : 443
         * position :
         * avatar : http://oq2xwecq0.bkt.clouddn.com/ass_426_12_570.png
         * email :
         * isVIP : 2
         */

        private String realName;
        private String school;
        private int sex;
        private String domain;
        private String mobile;
        private String degree;
        private String id;
        private String position;
        private String avatar;
        private String email;
        private String isVIP;
        private String web;
        private String emailauthen ;

        public String getEmailauthen() {
            return emailauthen;
        }

        public void setEmailauthen(String emailauthen) {
            this.emailauthen = emailauthen;
        }



        private boolean isCheck = false;

        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }

        public String getWeb() {
            return web;
        }

        public void setWeb(String web) {
            this.web = web;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getSchool() {
            return school;
        }

        public void setSchool(String school) {
            this.school = school;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getDomain() {
            return domain;
        }

        public void setDomain(String domain) {
            this.domain = domain;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getDegree() {
            return degree;
        }

        public void setDegree(String degree) {
            this.degree = degree;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getIsVIP() {
            return isVIP;
        }

        public void setIsVIP(String isVIP) {
            this.isVIP = isVIP;
        }
    }


    public static class NumBean {
        /**
         * uid : 443
         * followedNum : 0
         * followNum : 0
         * summarizeNum : 0
         * paperNum : 0
         */

        private String uid;
        private String followedNum;
        private String followNum;
        private String summarizeNum;
        private String paperNum;

        public String getUid() {
            return uid;
        }

        public void setUid(String uidX) {
            this.uid = uidX;
        }

        public String getFollowedNum() {
            return followedNum;
        }

        public void setFollowedNum(String followedNum) {
            this.followedNum = followedNum;
        }

        public String getFollowNum() {
            return followNum;
        }

        public void setFollowNum(String followNum) {
            this.followNum = followNum;
        }

        public String getSummarizeNum() {
            return summarizeNum;
        }

        public void setSummarizeNum(String summarizeNum) {
            this.summarizeNum = summarizeNum;
        }

        public String getPaperNum() {
            return paperNum;
        }

        public void setPaperNum(String paperNum) {
            this.paperNum = paperNum;
        }
    }

    public static class SummarizeBean {
        /**
         * id : 4494
         * title : 集思谱商业计划书
         * authors : {"rows":[{"id":4,"pbid":4494,"author":"陈昭宇","email":"","isGiiisp":"0"},{"id":5,"pbid":4494,"author":"马伟明","email":"","isGiiisp":"0"},{"id":6,"pbid":4494,"author":"潘建伟","email":"","isGiiisp":"0"}],"total":3}
         * digest :
         * version : 0
         */

        private String id;
        private String title;
        private AuthorsBeanX authors;
        private String digest;
        private String version;
        private String isEncrypt;

        public String getIsEncrypt() {
            return isEncrypt;
        }

        public void setIsEncrypt(String isEncrypt) {
            this.isEncrypt = isEncrypt;
        }
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public AuthorsBeanX getAuthors() {
            return authors;
        }

        public void setAuthors(AuthorsBeanX authors) {
            this.authors = authors;
        }

        public String getDigest() {
            return digest;
        }

        public void setDigest(String digest) {
            this.digest = digest;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public static class AuthorsBeanX {
            /**
             * rows : [{"id":4,"pbid":4494,"author":"陈昭宇","email":"","isGiiisp":"0"},{"id":5,"pbid":4494,"author":"马伟明","email":"","isGiiisp":"0"},{"id":6,"pbid":4494,"author":"潘建伟","email":"","isGiiisp":"0"}]
             * total : 3
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
                 * id : 4
                 * pbid : 4494
                 * author : 陈昭宇
                 * email :
                 * isGiiisp : 0
                 */

                private String id;
                private String pbid;
                private String author;
                private String email;
                private String isGiiisp;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getPbid() {
                    return pbid;
                }

                public void setPbid(String pbid) {
                    this.pbid = pbid;
                }

                public String getAuthor() {
                    return author;
                }

                public void setAuthor(String author) {
                    this.author = author;
                }

                public String getEmail() {
                    return email;
                }

                public void setEmail(String email) {
                    this.email = email;
                }

                public String getIsGiiisp() {
                    return isGiiisp;
                }

                public void setIsGiiisp(String isGiiisp) {
                    this.isGiiisp = isGiiisp;
                }
            }
        }
    }

    public static class AuthenBean {
        /**
         * major :
         * organization :
         * position :
         * department :
         */

        private String major;
        private String organization;
        private String position;
        private String department;

        public String getMajor() {
            return major;
        }

        public void setMajor(String major) {
            this.major = major;
        }

        public String getOrganization() {
            return organization;
        }

        public void setOrganization(String organization) {
            this.organization = organization;
        }

        public String getPosition() {
            return position;
        }

        public void setPosition(String position) {
            this.position = position;
        }

        public String getDepartment() {
            return department;
        }

        public void setDepartment(String department) {
            this.department = department;
        }
    }

    public static class IntroductionBean implements Serializable {
        /**
         * id : 4
         * uid : 443
         * school : 中国科学院
         * timeStart : 2011
         * timeEnd :
         * major : 天文大数据挖掘
         * degree : 博士后
         * eduBackground : 博士后研究生
         * isDel : 0
         * createTime : 2017.07.15 10:42
         */

        private String id;
        private String uid;
        private String school;
        private String timeStart;
        private String timeEnd;
        private String major;
        private String degree;
        private String eduBackground;
        private String isDel;
        private String createTime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uidX) {
            this.uid = uidX;
        }

        public String getSchool() {
            return school;
        }

        public void setSchool(String school) {
            this.school = school;
        }

        public String getTimeStart() {
            return timeStart;
        }

        public void setTimeStart(String timeStart) {
            this.timeStart = timeStart;
        }

        public String getTimeEnd() {
            return timeEnd;
        }

        public void setTimeEnd(String timeEnd) {
            this.timeEnd = timeEnd;
        }

        public String getMajor() {
            return major;
        }

        public void setMajor(String major) {
            this.major = major;
        }

        public String getDegree() {
            return degree;
        }

        public void setDegree(String degree) {
            this.degree = degree;
        }

        public String getEduBackground() {
            return eduBackground;
        }

        public void setEduBackground(String eduBackground) {
            this.eduBackground = eduBackground;
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
}