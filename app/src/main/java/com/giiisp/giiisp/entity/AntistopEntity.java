package com.giiisp.giiisp.entity;

import java.util.List;

/**
 * 关键字Entity
 * Created by Thinkpad on 2017/7/14.
 */

public class AntistopEntity extends BaseEntity{

    /**
     * antistopInfo : {"rows":[{"pbid":4494,"science":{"rows":[{"id":10,"pbid":4494,"antistop":"推广模式","type":"1","createTime":"2017.07.13 00:00"},{"id":11,"pbid":4494,"antistop":"共享营销","type":"1","createTime":"2017.07.13 00:00"},{"id":12,"pbid":4494,"antistop":"常规竞争问答","type":"1","createTime":"2017.07.13 00:00"}],"total":3},"maths":{"rows":[],"total":0},"data":{"rows":[{"id":13,"pbid":4494,"antistop":"市场层级","type":"3","createTime":"2017.07.13 00:00"},{"id":14,"pbid":4494,"antistop":"市场单元","type":"3","createTime":"2017.07.13 00:00"},{"id":15,"pbid":4494,"antistop":"市场规模","type":"3","createTime":"2017.07.13 00:00"}],"total":3}}],"total":1}
     */

    private AntistopInfoBean antistopInfo;

    public AntistopInfoBean getAntistopInfo() {
        return antistopInfo;
    }

    public void setAntistopInfo(AntistopInfoBean antistopInfo) {
        this.antistopInfo = antistopInfo;
    }

    public static class AntistopInfoBean {
        /**
         * rows : [{"pbid":4494,"science":{"rows":[{"id":10,"pbid":4494,"antistop":"推广模式","type":"1","createTime":"2017.07.13 00:00"},{"id":11,"pbid":4494,"antistop":"共享营销","type":"1","createTime":"2017.07.13 00:00"},{"id":12,"pbid":4494,"antistop":"常规竞争问答","type":"1","createTime":"2017.07.13 00:00"}],"total":3},"maths":{"rows":[],"total":0},"data":{"rows":[{"id":13,"pbid":4494,"antistop":"市场层级","type":"3","createTime":"2017.07.13 00:00"},{"id":14,"pbid":4494,"antistop":"市场单元","type":"3","createTime":"2017.07.13 00:00"},{"id":15,"pbid":4494,"antistop":"市场规模","type":"3","createTime":"2017.07.13 00:00"}],"total":3}}]
         * total : 1
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
             * pbid : 4494
             * science : {"rows":[{"id":10,"pbid":4494,"antistop":"推广模式","type":"1","createTime":"2017.07.13 00:00"},{"id":11,"pbid":4494,"antistop":"共享营销","type":"1","createTime":"2017.07.13 00:00"},{"id":12,"pbid":4494,"antistop":"常规竞争问答","type":"1","createTime":"2017.07.13 00:00"}],"total":3}
             * maths : {"rows":[],"total":0}
             * data : {"rows":[{"id":13,"pbid":4494,"antistop":"市场层级","type":"3","createTime":"2017.07.13 00:00"},{"id":14,"pbid":4494,"antistop":"市场单元","type":"3","createTime":"2017.07.13 00:00"},{"id":15,"pbid":4494,"antistop":"市场规模","type":"3","createTime":"2017.07.13 00:00"}],"total":3}
             */

            private String pbid;
            private DataBean science;
            private DataBean maths;
            private DataBean data;

            public String getPbid() {
                return pbid;
            }

            public void setPbid(String pbid) {
                this.pbid = pbid;
            }

            public DataBean getScience() {
                return science;
            }

            public void setScience(DataBean science) {
                this.science = science;
            }

            public DataBean getMaths() {
                return maths;
            }

            public void setMaths(DataBean maths) {
                this.maths = maths;
            }

            public DataBean getData() {
                return data;
            }

            public void setData(DataBean data) {
                this.data = data;
            }


            public static class DataBean {
                /**
                 * rows : [{"id":13,"pbid":4494,"antistop":"市场层级","type":"3","createTime":"2017.07.13 00:00"},{"id":14,"pbid":4494,"antistop":"市场单元","type":"3","createTime":"2017.07.13 00:00"},{"id":15,"pbid":4494,"antistop":"市场规模","type":"3","createTime":"2017.07.13 00:00"}]
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
                     * id : 13
                     * pbid : 4494
                     * antistop : 市场层级
                     * type : 3
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
        }
    }
}
