package com.giiisp.giiisp.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Thinkpad on 2017/7/4.
 */

public class SubscribeEntity extends BaseEntity {


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


        private int total;
        private List<RowsBeanXXXXX> rows;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<RowsBeanXXXXX> getRows() {
            return rows;
        }

        public void setRows(List<RowsBeanXXXXX> rows) {
            this.rows = rows;
        }

        public static class RowsBeanXXXXX {


            private String id;
            private String uid;
            private String title;
            private String type;
            private String isDel;
            private String paperOrSummarize;
            private String isLocked;
            private String followedNum;
            private String likedNum;
            private String commentNum;
            private String shareNum;
            private String readNum;
            private String quizNum;
            private String answerNum;
            private String status;
            private String downloadNum;
            private String createTime;
            private String updateTime;
            private String path;
            private String realName;
            private String degree;
            private String jobTitle;
            private String organization;
            private String orgEng;
            private String aAvatar;
            private String isLiked;
            private String isFollowed;
            private AntistopsBean antistops;
            private LiteraturesBean literatures;
            private AuthorsBean authors;
            private PhotoOneBean photoOne;
            private PhotoOneBean photoTwo;
            private PhotoOneBean photoThree;
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

            public String getUid() {
                return uid;
            }

            public void setUid(String uidX) {
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

            public String getFollowedNum() {
                return followedNum;
            }

            public void setFollowedNum(String followedNum) {
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

            public String getReadNum() {
                return readNum;
            }

            public void setReadNum(String readNum) {
                this.readNum = readNum;
            }

            public String getQuizNum() {
                return quizNum;
            }

            public void setQuizNum(String quizNum) {
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

            public String getDownloadNum() {
                return downloadNum;
            }

            public void setDownloadNum(String downloadNum) {
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

            public String getDegree() {
                return degree;
            }

            public void setDegree(String degree) {
                this.degree = degree;
            }

            public String getJobTitle() {
                return jobTitle;
            }

            public void setJobTitle(String jobTitle) {
                this.jobTitle = jobTitle;
            }

            public String getOrganization() {
                return organization;
            }

            public void setOrganization(String organization) {
                this.organization = organization;
            }

            public String getOrgEng() {
                return orgEng;
            }

            public void setOrgEng(String orgEng) {
                this.orgEng = orgEng;
            }

            public String getAAvatar() {
                return aAvatar;
            }

            public void setAAvatar(String aAvatar) {
                this.aAvatar = aAvatar;
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

            public AntistopsBean getAntistops() {
                return antistops;
            }

            public void setAntistops(AntistopsBean antistops) {
                this.antistops = antistops;
            }

            public LiteraturesBean getLiteratures() {
                return literatures;
            }

            public void setLiteratures(LiteraturesBean literatures) {
                this.literatures = literatures;
            }

            public AuthorsBean getAuthors() {
                return authors;
            }

            public void setAuthors(AuthorsBean authors) {
                this.authors = authors;
            }

            public PhotoOneBean getPhotoOne() {
                return photoOne;
            }

            public void setPhotoOne(PhotoOneBean photoOne) {
                this.photoOne = photoOne;
            }

            public PhotoOneBean getPhotoTwo() {
                return photoTwo;
            }

            public void setPhotoTwo(PhotoOneBean photoTwo) {
                this.photoTwo = photoTwo;
            }

            public PhotoOneBean getPhotoThree() {
                return photoThree;
            }

            public void setPhotoThree(PhotoOneBean photoThree) {
                this.photoThree = photoThree;
            }

            public static class AntistopsBean {

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
                     * id : 10
                     * pbid : 4494
                     * antistop : 推广模式
                     * type : 1
                     * createTime : 2017.07.13 00:00
                     */

                    private String id;
                    private String pbid;
                    private String antistop;
                    private String type;
                    private String createTime;

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

                    public String getAntistop() {
                        return antistop;
                    }

                    public void setAntistop(String antistop) {
                        this.antistop = antistop;
                    }

                    public String getType() {
                        return type;
                    }

                    public void setType(String type) {
                        this.type = type;
                    }

                    public String getCreateTime() {
                        return createTime;
                    }

                    public void setCreateTime(String createTime) {
                        this.createTime = createTime;
                    }
                }
            }

            public static class LiteraturesBean {

                private int total;
                private List<?> rows;

                public int getTotal() {
                    return total;
                }

                public void setTotal(int total) {
                    this.total = total;
                }

                public List<?> getRows() {
                    return rows;
                }

                public void setRows(List<?> rows) {
                    this.rows = rows;
                }
            }

            public static class AuthorsBean {

                private int total;
                private List<UserInfoEntity.SummarizeBean.AuthorsBeanX.RowsBean> rows;

                public int getTotal() {
                    return total;
                }

                public void setTotal(int total) {
                    this.total = total;
                }

                public List<UserInfoEntity.SummarizeBean.AuthorsBeanX.RowsBean> getRows() {
                    return rows;
                }

                public void setRows(List<UserInfoEntity.SummarizeBean.AuthorsBeanX.RowsBean> rows) {
                    this.rows = rows;
                }

                public static class RowsBeanX {
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

            public static class PhotoOneBean {


                private int total;
                private List<RowsBeanXXXX> rows;

                public int getTotal() {
                    return total;
                }

                public void setTotal(int total) {
                    this.total = total;
                }

                public List<RowsBeanXXXX> getRows() {
                    return rows;
                }

                public void setRows(List<RowsBeanXXXX> rows) {
                    this.rows = rows;
                }

                public static class RowsBeanXXXX {


                    private String id;
                    private String readNum;
                    private String followedNum;
                    private String downloadNum;
                    private String quizNum;
                    private String firstPic;
                    private String createTime;
                    private PhotosBean photos;
                    private RecordOneBean recordOne;
                    private RecordOneBean recordTwo;

                    /**
                     * isFollowed : 1
                     */

                    private String isFollowed;
                    /**
                     * status : 1
                     */

                    private String status;


                    public String getId() {
                        return id;
                    }

                    public void setId(String id) {
                        this.id = id;
                    }

                    public String getReadNum() {
                        return readNum;
                    }

                    public void setReadNum(String readNum) {
                        this.readNum = readNum;
                    }

                    public String getFollowedNum() {
                        return followedNum;
                    }

                    public void setFollowedNum(String followedNum) {
                        this.followedNum = followedNum;
                    }

                    public String getDownloadNum() {
                        return downloadNum;
                    }

                    public void setDownloadNum(String downloadNum) {
                        this.downloadNum = downloadNum;
                    }

                    public String getQuizNum() {
                        return quizNum;
                    }

                    public void setQuizNum(String quizNum) {
                        this.quizNum = quizNum;
                    }

                    public String getFirstPic() {
                        return firstPic;
                    }

                    public void setFirstPic(String firstPic) {
                        this.firstPic = firstPic;
                    }

                    public String getCreateTime() {
                        return createTime;
                    }

                    public void setCreateTime(String createTime) {
                        this.createTime = createTime;
                    }

                    public PhotosBean getPhotos() {
                        return photos;
                    }

                    public void setPhotos(PhotosBean photos) {
                        this.photos = photos;
                    }

                    public RecordOneBean getRecordOne() {
                        return recordOne;
                    }

                    public void setRecordOne(RecordOneBean recordOne) {
                        this.recordOne = recordOne;
                    }

                    public RecordOneBean getRecordTwo() {
                        return recordTwo;
                    }

                    public void setRecordTwo(RecordOneBean recordTwo) {
                        this.recordTwo = recordTwo;
                    }

                    public String getIsFollowed() {
                        return isFollowed;
                    }

                    public void setIsFollowed(String isFollowed) {
                        this.isFollowed = isFollowed;
                    }

                    public String getStatus() {
                        return status;
                    }

                    public void setStatus(String status) {
                        this.status = status;
                    }

                    public static class PhotosBean {

                        private int total;
                        private List<RowsBeanXX> rows;

                        public int getTotal() {
                            return total;
                        }

                        public void setTotal(int total) {
                            this.total = total;
                        }

                        public List<RowsBeanXX> getRows() {
                            return rows;
                        }

                        public void setRows(List<RowsBeanXX> rows) {
                            this.rows = rows;
                        }

                        public static class RowsBeanXX implements Parcelable {
                            /**
                             * id : 10847
                             * path : http://oq2xwecq0.bkt.clouddn.com/ass_443_1_629.JPG
                             * version : 0
                             * order : 1
                             * isCover : 1
                             * isDel : 0
                             * readNum : 0
                             * shareNum : 0
                             * commentNum : 0
                             * likedNum : 0
                             * followedNum : 0
                             * createTime : 1970.01.01 00:00
                             */

                            private String id;
                            private String path;
                            private String version;
                            private String order;
                            private String isCover;
                            private String isDel;
                            private String readNum;
                            private String shareNum;
                            private String commentNum;
                            private String likedNum;
                            private String followedNum;
                            private String createTime;

                            protected RowsBeanXX(Parcel in) {
                                id = in.readString();
                                path = in.readString();
                                version = in.readString();
                                order = in.readString();
                                isCover = in.readString();
                                isDel = in.readString();
                                readNum = in.readString();
                                shareNum = in.readString();
                                commentNum = in.readString();
                                likedNum = in.readString();
                                followedNum = in.readString();
                                createTime = in.readString();
                            }

                            public static final Creator<RowsBeanXX> CREATOR = new Creator<RowsBeanXX>() {
                                @Override
                                public RowsBeanXX createFromParcel(Parcel in) {
                                    return new RowsBeanXX(in);
                                }

                                @Override
                                public RowsBeanXX[] newArray(int size) {
                                    return new RowsBeanXX[size];
                                }
                            };

                            public String getId() {
                                return id;
                            }

                            public void setId(String id) {
                                this.id = id;
                            }

                            public String getPath() {
                                return path;
                            }

                            public void setPath(String path) {
                                this.path = path;
                            }

                            public String getVersion() {
                                return version;
                            }

                            public void setVersion(String version) {
                                this.version = version;
                            }

                            public String getOrder() {
                                return order;
                            }

                            public void setOrder(String order) {
                                this.order = order;
                            }

                            public String getIsCover() {
                                return isCover;
                            }

                            public void setIsCover(String isCover) {
                                this.isCover = isCover;
                            }

                            public String getIsDel() {
                                return isDel;
                            }

                            public void setIsDel(String isDel) {
                                this.isDel = isDel;
                            }

                            public String getReadNum() {
                                return readNum;
                            }

                            public void setReadNum(String readNum) {
                                this.readNum = readNum;
                            }

                            public String getShareNum() {
                                return shareNum;
                            }

                            public void setShareNum(String shareNum) {
                                this.shareNum = shareNum;
                            }

                            public String getCommentNum() {
                                return commentNum;
                            }

                            public void setCommentNum(String commentNum) {
                                this.commentNum = commentNum;
                            }

                            public String getLikedNum() {
                                return likedNum;
                            }

                            public void setLikedNum(String likedNum) {
                                this.likedNum = likedNum;
                            }

                            public String getFollowedNum() {
                                return followedNum;
                            }

                            public void setFollowedNum(String followedNum) {
                                this.followedNum = followedNum;
                            }

                            public String getCreateTime() {
                                return createTime;
                            }

                            public void setCreateTime(String createTime) {
                                this.createTime = createTime;
                            }

                            @Override
                            public int describeContents() {
                                return 0;
                            }

                            @Override
                            public void writeToParcel(Parcel parcel, int i) {
                                parcel.writeString(id);
                                parcel.writeString(path);
                                parcel.writeString(version);
                                parcel.writeString(order);
                                parcel.writeString(isCover);
                                parcel.writeString(isDel);
                                parcel.writeString(readNum);
                                parcel.writeString(shareNum);
                                parcel.writeString(commentNum);
                                parcel.writeString(likedNum);
                                parcel.writeString(followedNum);
                                parcel.writeString(createTime);
                            }
                        }
                    }

                    public static class RecordOneBean {


                        private int total;
                        private List<RowsBeanXXX> rows;

                        public int getTotal() {
                            return total;
                        }

                        public void setTotal(int total) {
                            this.total = total;
                        }

                        public List<RowsBeanXXX> getRows() {
                            return rows;
                        }

                        public void setRows(List<RowsBeanXXX> rows) {
                            this.rows = rows;
                        }

                        public static class RowsBeanXXX implements Parcelable {
                            /**
                             * id : 12
                             * pcid : 10847
                             * path : http://oq2xwecq0.bkt.clouddn.com/ass_443_24_280..mp3.mp3
                             * language : 0
                             * size : 0.3
                             * duration : 24
                             * readNum : 0
                             * followedNum : 0
                             * downloadNum : 0
                             * createTime : 2017.07.07 00:00
                             */

                            private String id;
                            private String pcid;
                            private String path;
                            private String language;
                            private String size;
                            private String duration;
                            private String readNum;
                            private String followedNum;
                            private String downloadNum;
                            private String createTime;

                            protected RowsBeanXXX(Parcel in) {
                                id = in.readString();
                                pcid = in.readString();
                                path = in.readString();
                                language = in.readString();
                                size = in.readString();
                                duration = in.readString();
                                readNum = in.readString();
                                followedNum = in.readString();
                                downloadNum = in.readString();
                                createTime = in.readString();
                            }

                            public static final Creator<RowsBeanXXX> CREATOR = new Creator<RowsBeanXXX>() {
                                @Override
                                public RowsBeanXXX createFromParcel(Parcel in) {
                                    return new RowsBeanXXX(in);
                                }

                                @Override
                                public RowsBeanXXX[] newArray(int size) {
                                    return new RowsBeanXXX[size];
                                }
                            };

                            public String getId() {
                                return id;
                            }

                            public void setId(String id) {
                                this.id = id;
                            }

                            public String getPcid() {
                                return pcid;
                            }

                            public void setPcid(String pcid) {
                                this.pcid = pcid;
                            }

                            public String getPath() {
                                return path;
                            }

                            public void setPath(String path) {
                                this.path = path;
                            }

                            public String getLanguage() {
                                return language;
                            }

                            public void setLanguage(String language) {
                                this.language = language;
                            }

                            public String getSize() {
                                return size;
                            }

                            public void setSize(String size) {
                                this.size = size;
                            }

                            public String getDuration() {
                                return duration;
                            }

                            public void setDuration(String duration) {
                                this.duration = duration;
                            }

                            public String getReadNum() {
                                return readNum;
                            }

                            public void setReadNum(String readNum) {
                                this.readNum = readNum;
                            }

                            public String getFollowedNum() {
                                return followedNum;
                            }

                            public void setFollowedNum(String followedNum) {
                                this.followedNum = followedNum;
                            }

                            public String getDownloadNum() {
                                return downloadNum;
                            }

                            public void setDownloadNum(String downloadNum) {
                                this.downloadNum = downloadNum;
                            }

                            public String getCreateTime() {
                                return createTime;
                            }

                            public void setCreateTime(String createTime) {
                                this.createTime = createTime;
                            }

                            @Override
                            public int describeContents() {
                                return 0;
                            }

                            @Override
                            public void writeToParcel(Parcel parcel, int i) {
                                parcel.writeString(id);
                                parcel.writeString(pcid);
                                parcel.writeString(path);
                                parcel.writeString(language);
                                parcel.writeString(size);
                                parcel.writeString(duration);
                                parcel.writeString(readNum);
                                parcel.writeString(followedNum);
                                parcel.writeString(downloadNum);
                                parcel.writeString(createTime);
                            }
                        }
                    }


                }
            }

        }
    }
}
