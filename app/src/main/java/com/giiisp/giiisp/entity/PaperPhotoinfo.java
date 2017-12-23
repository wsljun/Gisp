package com.giiisp.giiisp.entity;

/**
 * Created by Thinkpad on 2017/6/30.
 */

public class PaperPhotoinfo {
    /**
     * id : 17
     * path : http:7xtjaq.com2.z0.glb.clouddn.com/2016-06-28_OqO3Pk2a.png
     * order : 1
     * version : null
     * language : null
     * isCover : 1
     * isDel : null
     * readNum : null
     * shareNum : null
     * commentNum : null
     * likedNum : null
     * followedNum : null
     * createTime : null
     */

    private int id;
    private String path;
    private int order;
    private String version;
    private String language;
    private String isCover;
    private String isDel;
    private String readNum;
    private String shareNum;
    private String commentNum;
    private String likedNum;
    private String followedNum;
    private String createTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public Object getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Object getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getIsCover() {
        return isCover;
    }

    public void setIsCover(String isCover) {
        this.isCover = isCover;
    }

    public Object getIsDel() {
        return isDel;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel;
    }

    public Object getReadNum() {
        return readNum;
    }

    public void setReadNum(String readNum) {
        this.readNum = readNum;
    }

    public Object getShareNum() {
        return shareNum;
    }

    public void setShareNum(String shareNum) {
        this.shareNum = shareNum;
    }

    public Object getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(String commentNum) {
        this.commentNum = commentNum;
    }

    public Object getLikedNum() {
        return likedNum;
    }

    public void setLikedNum(String likedNum) {
        this.likedNum = likedNum;
    }

    public Object getFollowedNum() {
        return followedNum;
    }

    public void setFollowedNum(String followedNum) {
        this.followedNum = followedNum;
    }

    public Object getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}