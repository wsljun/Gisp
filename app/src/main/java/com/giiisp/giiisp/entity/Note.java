package com.giiisp.giiisp.entity;


import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Thinkpad on 2017/6/23.
 */
@Entity
public class Note {
    @Id
    private String id;
    private String paperId;
    private String path;
    private String order;
    private String isCover;
    private String isDel;
    private String readNum;
    private String shareNum;
    private String isLocked;
    private String commentNum;
    private String likedNum;
    private String followedNum;
    private String createTime;
    private int playPosition;
    private int songsSize;
    private String time;
    private double size;
    private int duration;
    private String title;
    private String language;
    private String version;
    private String versions = "";
    //    @Convert(converter = NoteTypeConverter.class, columnType = String.class)
    private String type;
    @Generated(hash = 78002934)
    public Note(String id, String paperId, String path, String order,
            String isCover, String isDel, String readNum, String shareNum,
            String isLocked, String commentNum, String likedNum, String followedNum,
            String createTime, int playPosition, int songsSize, String time,
            double size, int duration, String title, String language,
            String version, String versions, String type) {
        this.id = id;
        this.paperId = paperId;
        this.path = path;
        this.order = order;
        this.isCover = isCover;
        this.isDel = isDel;
        this.readNum = readNum;
        this.shareNum = shareNum;
        this.isLocked = isLocked;
        this.commentNum = commentNum;
        this.likedNum = likedNum;
        this.followedNum = followedNum;
        this.createTime = createTime;
        this.playPosition = playPosition;
        this.songsSize = songsSize;
        this.time = time;
        this.size = size;
        this.duration = duration;
        this.title = title;
        this.language = language;
        this.version = version;
        this.versions = versions;
        this.type = type;
    }
    @Generated(hash = 1272611929)
    public Note() {
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPaperId() {
        return this.paperId;
    }
    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }
    public String getPath() {
        return this.path;
    }
    public void setPath(String path) {
        this.path = path;
    }
    public String getOrder() {
        return this.order;
    }
    public void setOrder(String order) {
        this.order = order;
    }
    public String getIsCover() {
        return this.isCover;
    }
    public void setIsCover(String isCover) {
        this.isCover = isCover;
    }
    public String getIsDel() {
        return this.isDel;
    }
    public void setIsDel(String isDel) {
        this.isDel = isDel;
    }
    public String getReadNum() {
        return this.readNum;
    }
    public void setReadNum(String readNum) {
        this.readNum = readNum;
    }
    public String getShareNum() {
        return this.shareNum;
    }
    public void setShareNum(String shareNum) {
        this.shareNum = shareNum;
    }
    public String getIsLocked() {
        return this.isLocked;
    }
    public void setIsLocked(String isLocked) {
        this.isLocked = isLocked;
    }
    public String getCommentNum() {
        return this.commentNum;
    }
    public void setCommentNum(String commentNum) {
        this.commentNum = commentNum;
    }
    public String getLikedNum() {
        return this.likedNum;
    }
    public void setLikedNum(String likedNum) {
        this.likedNum = likedNum;
    }
    public String getFollowedNum() {
        return this.followedNum;
    }
    public void setFollowedNum(String followedNum) {
        this.followedNum = followedNum;
    }
    public String getCreateTime() {
        return this.createTime;
    }
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
    public int getPlayPosition() {
        return this.playPosition;
    }
    public void setPlayPosition(int playPosition) {
        this.playPosition = playPosition;
    }
    public int getSongsSize() {
        return this.songsSize;
    }
    public void setSongsSize(int songsSize) {
        this.songsSize = songsSize;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public double getSize() {
        return this.size;
    }
    public void setSize(double size) {
        this.size = size;
    }
    public int getDuration() {
        return this.duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getLanguage() {
        return this.language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public String getVersion() {
        return this.version;
    }
    public void setVersion(String version) {
        this.version = version;
    }
    public String getVersions() {
        return this.versions;
    }
    public void setVersions(String versions) {
        this.versions = versions;
    }
    public String getType() {
        return this.type;
    }
    public void setType(String type) {
        this.type = type;
    }


}
