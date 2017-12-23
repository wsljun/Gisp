package com.giiisp.giiisp.entity;

import java.util.List;

/**
 * Created by Thinkpad on 2017/7/12.
 */

public class HomeEntity extends BaseEntity {

    /**
     * total : 1
     * pageInfo : [{"id":1,"summarize":{"rows":[{"id":4494,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_1_629.JPG","title":"集思谱商业计划书","version":"0"},{"id":27,"path":"http:7xtjaq.com2.z0.glb.clouddn.com/2016-06-28_qOo6o9RQ.png","title":"小羊肖恩","version":"0"}],"total":2},"paper":{"rows":[{"id":4495,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_442_1_752.png","title":"HOW FAR IS QUASAR UV/OPTICAL VARIABILITY FROM A DAMPED RANDOM WALK AT LOW FREQUENCY?","version":"0"},{"id":11,"path":"http:7xtjaq.com2.z0.glb.clouddn.com/2016-06-28_OqO3Pk2a.png","title":"帖子一...","version":"0"},{"id":25,"path":"http:7xtjaq.com2.z0.glb.clouddn.com/2016-06-28_9keJxJV4.png","title":"萌猫","version":"0"},{"id":8,"path":"http:7xtjaq.com2.z0.glb.clouddn.com/2016-06-28_mIf0QqJV.png","title":"六六六","version":"0"},{"id":1,"path":"http://o9kg05vzs.bkt.clouddn.com/2016-07-14_kqMS2gzR.png","title":"知·味","version":"0"}],"total":7},"activity":{"rows":[{"id":1,"uid":1,"path":"http://o9kg05vzs.bkt.clouddn.com/ass_199_1895_813.jpg","explain":"超级大礼包，注册就送精美礼品一份！","link":"https://tieba.baidu.com/index.html","createTime":"1970.01.01 00:00"},{"id":2,"uid":255,"path":"http://o9kg05vzs.bkt.clouddn.com/ass_199_1896_415.jpg","explain":"FAST射电望远镜今日正式运行，上传论文即有机会获取纪念品一份！","link":"https://ai.taobao.com/?pid=mm_12811289_2424861_70682665","createTime":"1970.01.01 00:00"}],"total":2}}]
     */

    private int total;
    private List<PageInfoBean> pageInfo;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<PageInfoBean> getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(List<PageInfoBean> pageInfo) {
        this.pageInfo = pageInfo;
    }

    public static class PageInfoBean {
        /**
         * id : 1
         * summarize : {"rows":[{"id":4494,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_1_629.JPG","title":"集思谱商业计划书","version":"0"},{"id":27,"path":"http:7xtjaq.com2.z0.glb.clouddn.com/2016-06-28_qOo6o9RQ.png","title":"小羊肖恩","version":"0"}],"total":2}
         * paper : {"rows":[{"id":4495,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_442_1_752.png","title":"HOW FAR IS QUASAR UV/OPTICAL VARIABILITY FROM A DAMPED RANDOM WALK AT LOW FREQUENCY?","version":"0"},{"id":11,"path":"http:7xtjaq.com2.z0.glb.clouddn.com/2016-06-28_OqO3Pk2a.png","title":"帖子一...","version":"0"},{"id":25,"path":"http:7xtjaq.com2.z0.glb.clouddn.com/2016-06-28_9keJxJV4.png","title":"萌猫","version":"0"},{"id":8,"path":"http:7xtjaq.com2.z0.glb.clouddn.com/2016-06-28_mIf0QqJV.png","title":"六六六","version":"0"},{"id":1,"path":"http://o9kg05vzs.bkt.clouddn.com/2016-07-14_kqMS2gzR.png","title":"知·味","version":"0"}],"total":7}
         * activity : {"rows":[{"id":1,"uid":1,"path":"http://o9kg05vzs.bkt.clouddn.com/ass_199_1895_813.jpg","explain":"超级大礼包，注册就送精美礼品一份！","link":"https://tieba.baidu.com/index.html","createTime":"1970.01.01 00:00"},{"id":2,"uid":255,"path":"http://o9kg05vzs.bkt.clouddn.com/ass_199_1896_415.jpg","explain":"FAST射电望远镜今日正式运行，上传论文即有机会获取纪念品一份！","link":"https://ai.taobao.com/?pid=mm_12811289_2424861_70682665","createTime":"1970.01.01 00:00"}],"total":2}
         */

        private String id;
        private PaperBean summarize;
        private PaperBean paper;
        private ActivityBean activity;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public PaperBean getSummarize() {
            return summarize;
        }

        public void setSummarize(PaperBean summarize) {
            this.summarize = summarize;
        }

        public PaperBean getPaper() {
            return paper;
        }

        public void setPaper(PaperBean paper) {
            this.paper = paper;
        }

        public ActivityBean getActivity() {
            return activity;
        }

        public void setActivity(ActivityBean activity) {
            this.activity = activity;
        }

        public static class SummarizeBean {
            /**
             * rows : [{"id":4494,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_1_629.JPG","title":"集思谱商业计划书","version":"0"},{"id":27,"path":"http:7xtjaq.com2.z0.glb.clouddn.com/2016-06-28_qOo6o9RQ.png","title":"小羊肖恩","version":"0"}]
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
                 * id : 4494
                 * path : http://oq2xwecq0.bkt.clouddn.com/ass_443_1_629.JPG
                 * title : 集思谱商业计划书
                 * version : 0
                 */

                private String id;
                private String path;
                private String title;
                private String version;

                public String getPassword() {
                    return password;
                }

                public void setPassword(String password) {
                    this.password = password;
                }

                public String getIsEncrypt() {
                    return isEncrypt;
                }

                public void setIsEncrypt(String isEncrypt) {
                    this.isEncrypt = isEncrypt;
                }

                private String password;
                private String isEncrypt;

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

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getVersion() {
                    return version;
                }

                public void setVersion(String version) {
                    this.version = version;
                }
            }
        }

        public static class PaperBean {
            /**
             * rows : [{"id":4495,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_442_1_752.png","title":"HOW FAR IS QUASAR UV/OPTICAL VARIABILITY FROM A DAMPED RANDOM WALK AT LOW FREQUENCY?","version":"0"},{"id":11,"path":"http:7xtjaq.com2.z0.glb.clouddn.com/2016-06-28_OqO3Pk2a.png","title":"帖子一...","version":"0"},{"id":25,"path":"http:7xtjaq.com2.z0.glb.clouddn.com/2016-06-28_9keJxJV4.png","title":"萌猫","version":"0"},{"id":8,"path":"http:7xtjaq.com2.z0.glb.clouddn.com/2016-06-28_mIf0QqJV.png","title":"六六六","version":"0"},{"id":1,"path":"http://o9kg05vzs.bkt.clouddn.com/2016-07-14_kqMS2gzR.png","title":"知·味","version":"0"}]
             * total : 7
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
                 * id : 4495
                 * path : http://oq2xwecq0.bkt.clouddn.com/ass_442_1_752.png
                 * title : HOW FAR IS QUASAR UV/OPTICAL VARIABILITY FROM A DAMPED RANDOM WALK AT LOW FREQUENCY?
                 * version : 0
                 */

                private String id;
                private String path;
                private String title;
                private String version;

                private String password;
                private String isEncrypt;


                public String getPassword() {
                    return password;
                }

                public void setPassword(String password) {
                    this.password = password;
                }

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

                public String getPath() {
                    return path;
                }

                public void setPath(String path) {
                    this.path = path;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getVersion() {
                    return version;
                }

                public void setVersion(String version) {
                    this.version = version;
                }
            }
        }

        public static class ActivityBean {
            /**
             * rows : [{"id":1,"uid":1,"path":"http://o9kg05vzs.bkt.clouddn.com/ass_199_1895_813.jpg","explain":"超级大礼包，注册就送精美礼品一份！","link":"https://tieba.baidu.com/index.html","createTime":"1970.01.01 00:00"},{"id":2,"uid":255,"path":"http://o9kg05vzs.bkt.clouddn.com/ass_199_1896_415.jpg","explain":"FAST射电望远镜今日正式运行，上传论文即有机会获取纪念品一份！","link":"https://ai.taobao.com/?pid=mm_12811289_2424861_70682665","createTime":"1970.01.01 00:00"}]
             * total : 2
             */

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

            public static class RowsBeanXX {
                /**
                 * id : 1
                 * uid : 1
                 * path : http://o9kg05vzs.bkt.clouddn.com/ass_199_1895_813.jpg
                 * explain : 超级大礼包，注册就送精美礼品一份！
                 * link : https://tieba.baidu.com/index.html
                 * createTime : 1970.01.01 00:00
                 */

                private String id;
                private String uid;
                private String path;
                private String explain;
                private String link;
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

                public String getPath() {
                    return path;
                }

                public void setPath(String path) {
                    this.path = path;
                }

                public String getExplain() {
                    return explain;
                }

                public void setExplain(String explain) {
                    this.explain = explain;
                }

                public String getLink() {
                    return link;
                }

                public void setLink(String link) {
                    this.link = link;
                }

                public String getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(String createTime) {
                    this.createTime = createTime;
                }
            }
        }


    }
}
