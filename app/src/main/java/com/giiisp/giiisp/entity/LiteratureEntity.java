package com.giiisp.giiisp.entity;

import java.util.List;

/**
 * Created by Thinkpad on 2017/7/3.
 */

public class LiteratureEntity extends BaseEntity{


    /**
     * total : 2
     * literatureInfo : [{"id":1,"pbid":4494,"title":"集思谱商业计划书","content":"[1]陈金梅.氟石膏生产早强快硬水泥的试验研究(D).西安：西安建筑科学大学，2000","author":"","digest":"","theUrl":"","total":3,"createTime":"1970.01.01 00:00","authors":[{"id":4,"pbid":4494,"author":"陈昭宇","email":"","isGiiisp":"0"},{"id":5,"pbid":4494,"author":"马伟明","email":"","isGiiisp":"0"},{"id":6,"pbid":4494,"author":"潘建伟","email":"","isGiiisp":"0"}]},{"id":2,"pbid":4494,"title":"集思谱商业计划书","content":"5 科技报告著录格式 \n［序号］作者.题名［Ｒ］.报告题名及编号，出版年 例: \n［７］Kyungmoon Nho. Automatic landing system design using fuzzy logic[R].AIAA-98-4484,1998","author":"","digest":"","theUrl":"","total":3,"createTime":"1970.01.01 00:00","authors":[{"id":4,"pbid":4494,"author":"陈昭宇","email":"","isGiiisp":"0"},{"id":5,"pbid":4494,"author":"马伟明","email":"","isGiiisp":"0"},{"id":6,"pbid":4494,"author":"潘建伟","email":"","isGiiisp":"0"}]}]
     */

    private int total;
    private List<LiteratureInfoBean> literatureInfo;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<LiteratureInfoBean> getLiteratureInfo() {
        return literatureInfo;
    }

    public void setLiteratureInfo(List<LiteratureInfoBean> literatureInfo) {
        this.literatureInfo = literatureInfo;
    }

    public static class LiteratureInfoBean {
        /**
         * id : 1
         * pbid : 4494
         * title : 集思谱商业计划书
         * content : [1]陈金梅.氟石膏生产早强快硬水泥的试验研究(D).西安：西安建筑科学大学，2000
         * author :
         * digest :
         * theUrl :
         * total : 3
         * createTime : 1970.01.01 00:00
         * authors : [{"id":4,"pbid":4494,"author":"陈昭宇","email":"","isGiiisp":"0"},{"id":5,"pbid":4494,"author":"马伟明","email":"","isGiiisp":"0"},{"id":6,"pbid":4494,"author":"潘建伟","email":"","isGiiisp":"0"}]
         */

        private String id;
        private String pbid;
        private String title;
        private String content;
        private String author;
        private String digest;
        private String theUrl;
        private int total;
        private String createTime;
        private List<UserInfoEntity.SummarizeBean.AuthorsBeanX.RowsBean> authors;

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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getDigest() {
            return digest;
        }

        public void setDigest(String digest) {
            this.digest = digest;
        }

        public String getTheUrl() {
            return theUrl;
        }

        public void setTheUrl(String theUrl) {
            this.theUrl = theUrl;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public List<UserInfoEntity.SummarizeBean.AuthorsBeanX.RowsBean> getAuthors() {
            return authors;
        }

        public void setAuthors(List<UserInfoEntity.SummarizeBean.AuthorsBeanX.RowsBean> authors) {
            this.authors = authors;
        }

        public static class AuthorsBean {
            /**
             * id : 4
             * pbid : 4494
             * author : 陈昭宇
             * email :
             * isGiiisp : 0
             */

            private int id;
            private int pbid;
            private String author;
            private String email;
            private String isGiiisp;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getPbid() {
                return pbid;
            }

            public void setPbid(int pbid) {
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
