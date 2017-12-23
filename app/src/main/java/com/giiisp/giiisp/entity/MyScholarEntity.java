package com.giiisp.giiisp.entity;

import java.util.List;

/**
 * Created by Thinkpad on 2017/6/26.
 */

public class MyScholarEntity extends BaseEntity {
    /**
     * myScholar : {"rows":[{"oid":255,"realName":"黄永标","avatar":"http://oq2xwecq0.bkt.clouddn.com/ass_426_12_570.png","organization":"","followed":"0"},{"oid":1,"realName":"黄永标","avatar":"http://oq2xwecq0.bkt.clouddn.com/ass_426_12_570.png","organization":"","followed":"0"},{"oid":427,"realName":"黄永标","avatar":"http://oq2xwecq0.bkt.clouddn.com/ass_426_12_570.png","organization":"","followed":"0"},{"oid":443,"realName":"黄永标","avatar":"http://oq2xwecq0.bkt.clouddn.com/ass_426_12_570.png","organization":"","followed":"0"}],"total":4}
     */

    private MyScholarBeanX myScholar;

    public MyScholarBeanX getMyScholar() {
        return myScholar;
    }

    public void setMyScholar(MyScholarBeanX myScholar) {
        this.myScholar = myScholar;
    }

    public static class MyScholarBeanX {
        /**
         * rows : [{"oid":255,"realName":"黄永标","avatar":"http://oq2xwecq0.bkt.clouddn.com/ass_426_12_570.png","organization":"","followed":"0"},{"oid":1,"realName":"黄永标","avatar":"http://oq2xwecq0.bkt.clouddn.com/ass_426_12_570.png","organization":"","followed":"0"},{"oid":427,"realName":"黄永标","avatar":"http://oq2xwecq0.bkt.clouddn.com/ass_426_12_570.png","organization":"","followed":"0"},{"oid":443,"realName":"黄永标","avatar":"http://oq2xwecq0.bkt.clouddn.com/ass_426_12_570.png","organization":"","followed":"0"}]
         * total : 4
         */

        private int total;
        private List<MyScholarBean> rows;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<MyScholarBean> getRows() {
            return rows;
        }

        public void setRows(List<MyScholarBean> rows) {
            this.rows = rows;
        }


    }



}
