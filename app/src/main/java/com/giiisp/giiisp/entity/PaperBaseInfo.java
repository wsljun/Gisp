package com.giiisp.giiisp.entity;

/**
 * Created by Thinkpad on 2017/6/30.
 */

public class PaperBaseInfo {
    /**
     * id : 11
     * uid : 2
     * title : 帖子一...
     * type : 2
     * version : null
     * language : null
     * isDel : null
     * paperOrSummarize : null
     * isLocked : null
     * followedNum : 0
     * likedNum : null
     * commentNum : null
     * shareNum : null
     * readNum : 0
     * quizNum : 0
     * answerNum : null
     * status : 1
     * downloadNum : 0
     * createTime : 2016.06.28 16:24
     * updateTime : 2016.09.22 16:15
     * path : null
     * realName :
     * aAvatar : http://o9kg05vzs.bkt.clouddn.com/2016-08-09_DjxXPeSD.png
     * picNum : null
     * recordNum : null
     * isLiked : 0
     * isFollowed : 0
     * firstPic : {"rows":[{"id":17,"path":"http:7xtjaq.com2.z0.glb.clouddn.com/2016-06-28_OqO3Pk2a.png","order":1,"version":null,"language":null,"isCover":"1","isDel":null,"readNum":null,"shareNum":null,"commentNum":null,"likedNum":null,"followedNum":null,"createTime":null}],"total":1}
     */

    private int id;
    private int uid;
    private String title;
    private String type;
    private String version;
    private String language;
    private String isDel;
    private String paperOrSummarize;
    private String isLocked;
    private int followedNum;
    private String likedNum;
    private String commentNum;
    private String shareNum;
    private int readNum;
    private int quizNum;
    private String answerNum;
    private String status;
    private int downloadNum;
    private String createTime;
    private String updateTime;
    private String path;
    private String realName;
    private String aAvatar;
    private String picNum;
    private String recordNum;
    private String isLiked;
    private String isFollowed;
    private PaperInfoBean firstPic;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
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

    public String getLikedNum() {
        return likedNum;
    }

    public void setLikedNum(String likedNum) {
        this.likedNum = likedNum;
    }

    public String getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(String commentNum) {
        this.commentNum = commentNum;
    }

    public String getShareNum() {
        return shareNum;
    }

    public void setShareNum(String shareNum) {
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

    public String getAnswerNum() {
        return answerNum;
    }

    public void setAnswerNum(String answerNum) {
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
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

    public String getPicNum() {
        return picNum;
    }

    public void setPicNum(String picNum) {
        this.picNum = picNum;
    }

    public String getRecordNum() {
        return recordNum;
    }

    public void setRecordNum(String recordNum) {
        this.recordNum = recordNum;
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

    public PaperInfoBean getFirstPic() {
        return firstPic;
    }

    public void setFirstPic(PaperInfoBean firstPic) {
        this.firstPic = firstPic;
    }

}
