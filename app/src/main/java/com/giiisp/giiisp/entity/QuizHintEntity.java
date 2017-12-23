package com.giiisp.giiisp.entity;

import java.util.List;

/**
 * Created by Thinkpad on 2017/7/4.
 */

public class QuizHintEntity extends BaseEntity {

    /**
     * total : 1
     * result : 1
     * quizHintList : [{"id":1,"pcid":1,"uid":255,"content":"这是一个严谨的APP","firstQuiz":"0","isDel":"0","createTime":"1970.01.01 00:00"}]
     * code :
     * info : 操作成功！
     */

    private List<QuizHintListBean> quizHintList;


    public List<QuizHintListBean> getQuizHintList() {
        return quizHintList;
    }

    public void setQuizHintList(List<QuizHintListBean> quizHintList) {
        this.quizHintList = quizHintList;
    }

    public static class QuizHintListBean {
        /**
         * id : 1
         * pcid : 1
         * uid : 255
         * content : 这是一个严谨的APP
         * firstQuiz : 0
         * isDel : 0
         * createTime : 1970.01.01 00:00
         */

        private int id;
        private int pcid;
        private int uid;
        private String content;
        private String firstQuiz;
        private String isDel;
        private String createTime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPcid() {
            return pcid;
        }

        public void setPcid(int pcid) {
            this.pcid = pcid;
        }

        public int getUid() {
            return uid;
        }

        public void setUid(int uid) {
            this.uid = uid;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getFirstQuiz() {
            return firstQuiz;
        }

        public void setFirstQuiz(String firstQuiz) {
            this.firstQuiz = firstQuiz;
        }

        public String getIsDel() {
            return isDel;
        }

        public void setIsDel(String isDel) {
            this.isDel = isDel;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }
    }
}
