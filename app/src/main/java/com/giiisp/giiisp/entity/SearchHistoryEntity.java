package com.giiisp.giiisp.entity;

import java.util.List;

/**
 * Created by Thinkpad on 2017/7/4.
 */

public class SearchHistoryEntity extends BaseEntity{

    /**
     * result : 1
     * otherSearch : {"rows":[{"id":3,"uid":442,"content":"引","createTime":null}],"total":2}
     * code :
     * mySearch : {"rows":[{"id":2,"uid":1,"content":"引","createTime":null},{"id":1,"uid":1,"content":"悟空","createTime":null}],"total":2}
     * info : 操作成功！
     */

    private OtherSearchBean otherSearch;
    private MySearchBean mySearch;

    public OtherSearchBean getOtherSearch() {
        return otherSearch;
    }

    public void setOtherSearch(OtherSearchBean otherSearch) {
        this.otherSearch = otherSearch;
    }

    public MySearchBean getMySearch() {
        return mySearch;
    }

    public void setMySearch(MySearchBean mySearch) {
        this.mySearch = mySearch;
    }


    public static class OtherSearchBean {
        /**
         * rows : [{"id":3,"uid":442,"content":"引","createTime":null}]
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
             * id : 3
             * uid : 442
             * content : 引
             * createTime : null
             */

            private String id;
            private String uid;
            private String content;
            private Object createTime;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public Object getCreateTime() {
                return createTime;
            }

            public void setCreateTime(Object createTime) {
                this.createTime = createTime;
            }
        }
    }

    public static class MySearchBean {
        /**
         * rows : [{"id":2,"uid":1,"content":"引","createTime":null},{"id":1,"uid":1,"content":"悟空","createTime":null}]
         * total : 2
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
             * id : 2
             * uid : 1
             * content : 引
             * createTime : null
             */

            private String id;
            private String uid;
            private String content;
            private Object createTime;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public Object getCreateTime() {
                return createTime;
            }

            public void setCreateTime(Object createTime) {
                this.createTime = createTime;
            }
        }
    }
}
