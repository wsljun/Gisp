package com.giiisp.giiisp.entity;

import java.util.List;

/**
 * Created by Thinkpad on 2017/7/4.
 */

public class HomeSearchEntity extends BaseEntity{

    /**
     * result : 1
     * paper : {"rows":[{"id":1,"title":"知·味","authors":{"rows":[{"id":null,"pbid":1,"author":"陈昭宇","email":""},{"id":null,"pbid":1,"author":"潘振伟","email":""},{"id":null,"pbid":1,"author":"邓稼先","email":""}],"total":3},"digest":null},{"id":2,"title":"物·语","authors":{"rows":[],"total":0},"digest":null},{"id":3,"title":"遇·见","authors":{"rows":[],"total":0},"digest":null}],"total":3}
     * code :
     * scholar : {"rows":[],"total":0}
     * info : 操作成功！
     */

    private PaperBean paper;
    private ScholarBean scholar;


    public PaperBean getPaper() {
        return paper;
    }

    public void setPaper(PaperBean paper) {
        this.paper = paper;
    }

    public ScholarBean getScholar() {
        return scholar;
    }

    public void setScholar(ScholarBean scholar) {
        this.scholar = scholar;
    }

    public static class PaperBean {
        /**
         * rows : [{"id":1,"title":"知·味","authors":{"rows":[{"id":null,"pbid":1,"author":"陈昭宇","email":""},{"id":null,"pbid":1,"author":"潘振伟","email":""},{"id":null,"pbid":1,"author":"邓稼先","email":""}],"total":3},"digest":null},{"id":2,"title":"物·语","authors":{"rows":[],"total":0},"digest":null},{"id":3,"title":"遇·见","authors":{"rows":[],"total":0},"digest":null}]
         * total : 3
         */

        private int total;
        private List<RowsBeanX> rows;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<RowsBeanX> getRows() {
            return rows;
        }

        public void setRows(List<RowsBeanX> rows) {
            this.rows = rows;
        }

        public static class RowsBeanX {
            /**
             * id : 1
             * title : 知·味
             * authors : {"rows":[{"id":null,"pbid":1,"author":"陈昭宇","email":""},{"id":null,"pbid":1,"author":"潘振伟","email":""},{"id":null,"pbid":1,"author":"邓稼先","email":""}],"total":3}
             * digest : null
             */

            private String id;
            private String title;
            private UserInfoEntity.SummarizeBean.AuthorsBeanX authors;
            private String digest;
            private String version;

            public String getVersion() {
                return version;
            }

            public void setVersion(String version) {
                this.version = version;
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

            public UserInfoEntity.SummarizeBean.AuthorsBeanX getAuthors() {
                return authors;
            }

            public void setAuthors(UserInfoEntity.SummarizeBean.AuthorsBeanX authors) {
                this.authors = authors;
            }

            public String getDigest() {
                return digest;
            }

            public void setDigest(String digest) {
                this.digest = digest;
            }

            public static class AuthorsBean {
                /**
                 * rows : [{"id":null,"pbid":1,"author":"陈昭宇","email":""},{"id":null,"pbid":1,"author":"潘振伟","email":""},{"id":null,"pbid":1,"author":"邓稼先","email":""}]
                 * total : 3
                 */

                private int total;
                private List<HomeSearchEntity.PaperBean.RowsBeanX.AuthorsBean.RowsBean> rows;

                public int getTotal() {
                    return total;
                }

                public void setTotal(int total) {
                    this.total = total;
                }

                public List<HomeSearchEntity.PaperBean.RowsBeanX.AuthorsBean.RowsBean> getRows() {
                    return rows;
                }

                public void setRows(List<HomeSearchEntity.PaperBean.RowsBeanX.AuthorsBean.RowsBean> rows) {
                    this.rows = rows;
                }

                public static class RowsBean {
                    /**
                     * id : null
                     * pbid : 1
                     * author : 陈昭宇
                     * email :
                     */

                    private int id;
                    private int pbid;
                    private String author;
                    private String email;

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
                }
            }
        }
    }

    public static class ScholarBean {
        /**
         * rows : [{"id":427,"realName":"黄永标","organization":null,"isFollowed":"0"},{"id":439,"realName":"黄永标1","organization":null,"isFollowed":"0"},{"id":440,"realName":"黄永标2","organization":null,"isFollowed":"0"},{"id":441,"realName":"黄永标3","organization":null,"isFollowed":"0"}]
         * total : 4
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
             * id : 427
             * realName : 黄永标
             * organization : null
             * isFollowed : 0
             */

            private String id;

            private String realName;
            private String organization;
            private String isFollowed;
            /**
             * avatar : http://wx.qlogo.cn/mmopen/kticE1XicCHRNYcgh58HHN75X7hazO5hqGJMu8iaVU0BaSeeQLhqu37K539MvQibrYZbntGTKI5QQ9tzwsolopk9AhgMtzHpziaok/0
             */

            private String avatar;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getRealName() {
                return realName;
            }

            public void setRealName(String realName) {
                this.realName = realName;
            }

            public String getOrganization() {
                return organization;
            }

            public void setOrganization(String organization) {
                this.organization = organization;
            }

            public String getIsFollowed() {
                return isFollowed;
            }

            public void setIsFollowed(String isFollowed) {
                this.isFollowed = isFollowed;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }
        }
    }
}
