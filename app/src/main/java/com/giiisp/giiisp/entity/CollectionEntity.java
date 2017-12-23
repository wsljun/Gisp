package com.giiisp.giiisp.entity;

import java.util.List;

/**
 * 好像没用到
 * Created by Thinkpad on 2017/7/3.
 */

public class CollectionEntity extends BaseEntity{

    /**
     * total : 1
     * pageInfo : {"rows":[{"id":4494,"uid":443,"title":"集思谱商业计划书","type":"1","version":null,"language":null,"isDel":"0","paperOrSummarize":"2","isLocked":"0","followedNum":0,"likedNum":0,"commentNum":0,"shareNum":0,"readNum":255,"quizNum":0,"answerNum":0,"status":"1","downloadNum":0,"createTime":"2017.07.07 00:00","updateTime":"2017.07.07 00:00","path":null,"realName":"陈昭宇","aAvatar":"http://wx.qlogo.cn/mmopen/4dAyDLy0gH7SQcjLMiaSuFGxBq1aw3nBB10cSHpNoA1m0C0yxiaEaORLTcFcm8pLdshQqTCGVar7syLJS3tBcub6FVeBzo648x/0","picNum":null,"recordNum":null,"firstPic":"http://oq2xwecq0.bkt.clouddn.com/ass_443_1_629.JPG","isLiked":"0","isFollowed":"1"}],"total":1}
     */

    private int total;
    private PageInfoBean pageInfo;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public PageInfoBean getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfoBean pageInfo) {
        this.pageInfo = pageInfo;
    }

    public static class PageInfoBean {
        /**
         * rows : [{"id":4494,"uid":443,"title":"集思谱商业计划书","type":"1","version":null,"language":null,"isDel":"0","paperOrSummarize":"2","isLocked":"0","followedNum":0,"likedNum":0,"commentNum":0,"shareNum":0,"readNum":255,"quizNum":0,"answerNum":0,"status":"1","downloadNum":0,"createTime":"2017.07.07 00:00","updateTime":"2017.07.07 00:00","path":null,"realName":"陈昭宇","aAvatar":"http://wx.qlogo.cn/mmopen/4dAyDLy0gH7SQcjLMiaSuFGxBq1aw3nBB10cSHpNoA1m0C0yxiaEaORLTcFcm8pLdshQqTCGVar7syLJS3tBcub6FVeBzo648x/0","picNum":null,"recordNum":null,"firstPic":"http://oq2xwecq0.bkt.clouddn.com/ass_443_1_629.JPG","isLiked":"0","isFollowed":"1"}]
         * total : 1
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
             * id : 4494
             * uid : 443
             * title : 集思谱商业计划书
             * type : 1
             * version : null
             * language : null
             * isDel : 0
             * paperOrSummarize : 2
             * isLocked : 0
             * followedNum : 0
             * likedNum : 0
             * commentNum : 0
             * shareNum : 0
             * readNum : 255
             * quizNum : 0
             * answerNum : 0
             * status : 1
             * downloadNum : 0
             * createTime : 2017.07.07 00:00
             * updateTime : 2017.07.07 00:00
             * path : null
             * realName : 陈昭宇
             * aAvatar : http://wx.qlogo.cn/mmopen/4dAyDLy0gH7SQcjLMiaSuFGxBq1aw3nBB10cSHpNoA1m0C0yxiaEaORLTcFcm8pLdshQqTCGVar7syLJS3tBcub6FVeBzo648x/0
             * picNum : null
             * recordNum : null
             * firstPic : http://oq2xwecq0.bkt.clouddn.com/ass_443_1_629.JPG
             * isLiked : 0
             * isFollowed : 1
             */

            private int id;
            private int uid;
            private String title;
            private String type;
            private Object version;
            private Object language;
            private String isDel;
            private String paperOrSummarize;
            private String isLocked;
            private int followedNum;
            private int likedNum;
            private int commentNum;
            private int shareNum;
            private int readNum;
            private int quizNum;
            private int answerNum;
            private String status;
            private int downloadNum;
            private String createTime;
            private String updateTime;
            private Object path;
            private String realName;
            private String aAvatar;
            private Object picNum;
            private Object recordNum;
            private String firstPic;
            private String isLiked;
            private String isFollowed;

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

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public Object getVersion() {
                return version;
            }

            public void setVersion(Object version) {
                this.version = version;
            }

            public Object getLanguage() {
                return language;
            }

            public void setLanguage(Object language) {
                this.language = language;
            }

            public String getIsDel() {
                return isDel;
            }

            public void setIsDel(String isDel) {
                this.isDel = isDel;
            }

            public String getPaperOrSummarize() {
                return paperOrSummarize;
            }

            public void setPaperOrSummarize(String paperOrSummarize) {
                this.paperOrSummarize = paperOrSummarize;
            }

            public String getIsLocked() {
                return isLocked;
            }

            public void setIsLocked(String isLocked) {
                this.isLocked = isLocked;
            }

            public int getFollowedNum() {
                return followedNum;
            }

            public void setFollowedNum(int followedNum) {
                this.followedNum = followedNum;
            }

            public int getLikedNum() {
                return likedNum;
            }

            public void setLikedNum(int likedNum) {
                this.likedNum = likedNum;
            }

            public int getCommentNum() {
                return commentNum;
            }

            public void setCommentNum(int commentNum) {
                this.commentNum = commentNum;
            }

            public int getShareNum() {
                return shareNum;
            }

            public void setShareNum(int shareNum) {
                this.shareNum = shareNum;
            }

            public int getReadNum() {
                return readNum;
            }

            public void setReadNum(int readNum) {
                this.readNum = readNum;
            }

            public int getQuizNum() {
                return quizNum;
            }

            public void setQuizNum(int quizNum) {
                this.quizNum = quizNum;
            }

            public int getAnswerNum() {
                return answerNum;
            }

            public void setAnswerNum(int answerNum) {
                this.answerNum = answerNum;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public int getDownloadNum() {
                return downloadNum;
            }

            public void setDownloadNum(int downloadNum) {
                this.downloadNum = downloadNum;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public Object getPath() {
                return path;
            }

            public void setPath(Object path) {
                this.path = path;
            }

            public String getRealName() {
                return realName;
            }

            public void setRealName(String realName) {
                this.realName = realName;
            }

            public String getAAvatar() {
                return aAvatar;
            }

            public void setAAvatar(String aAvatar) {
                this.aAvatar = aAvatar;
            }

            public Object getPicNum() {
                return picNum;
            }

            public void setPicNum(Object picNum) {
                this.picNum = picNum;
            }

            public Object getRecordNum() {
                return recordNum;
            }

            public void setRecordNum(Object recordNum) {
                this.recordNum = recordNum;
            }

            public String getFirstPic() {
                return firstPic;
            }

            public void setFirstPic(String firstPic) {
                this.firstPic = firstPic;
            }

            public String getIsLiked() {
                return isLiked;
            }

            public void setIsLiked(String isLiked) {
                this.isLiked = isLiked;
            }

            public String getIsFollowed() {
                return isFollowed;
            }

            public void setIsFollowed(String isFollowed) {
                this.isFollowed = isFollowed;
            }
        }
    }
}
