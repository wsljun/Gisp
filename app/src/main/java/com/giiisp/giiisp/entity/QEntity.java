package com.giiisp.giiisp.entity;

import java.util.List;

/**
 * Created by Thinkpad on 2017/6/20.
 */

public class QEntity {

    /**
     * result : 1
     * quiz : {"rows":[{"id":1,"pcid":1,"uid":255,"content":"这是一个严谨的APP","isDel":"0","createTime":"1970.01.01 00:00","answer":{"rows":[{"id":1,"qid":1,"uid":5,"answer":"这么没有","isRecord":"1","timing":"3.5","createTime":"19 Days","realName":"","avatar":"http://o9kg05vzs.bkt.clouddn.com/2016-09-24_KmjZYrXl.png","aSex":"1","pcid":null,"quiz":null},{"id":4,"qid":1,"uid":56,"answer":"这一特点非常好用","isRecord":"0","timing":"0","createTime":"47 Year","realName":"","avatar":"","aSex":"2","pcid":null,"quiz":null}],"total":2}}],"total":1}
     * code :
     * info : 操作成功！
     */

    private int result;
    private QuizBean quiz;
    private String code;
    private String info;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public QuizBean getQuiz() {
        return quiz;
    }

    public void setQuiz(QuizBean quiz) {
        this.quiz = quiz;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public static class QuizBean {
        /**
         * rows : [{"id":1,"pcid":1,"uid":255,"content":"这是一个严谨的APP","isDel":"0","createTime":"1970.01.01 00:00","answer":{"rows":[{"id":1,"qid":1,"uid":5,"answer":"这么没有","isRecord":"1","timing":"3.5","createTime":"19 Days","realName":"","avatar":"http://o9kg05vzs.bkt.clouddn.com/2016-09-24_KmjZYrXl.png","aSex":"1","pcid":null,"quiz":null},{"id":4,"qid":1,"uid":56,"answer":"这一特点非常好用","isRecord":"0","timing":"0","createTime":"47 Year","realName":"","avatar":"","aSex":"2","pcid":null,"quiz":null}],"total":2}}]
         * total : 1
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
             * pcid : 1
             * uid : 255
             * content : 这是一个严谨的APP
             * isDel : 0
             * createTime : 1970.01.01 00:00
             * answer : {"rows":[{"id":1,"qid":1,"uid":5,"answer":"这么没有","isRecord":"1","timing":"3.5","createTime":"19 Days","realName":"","avatar":"http://o9kg05vzs.bkt.clouddn.com/2016-09-24_KmjZYrXl.png","aSex":"1","pcid":null,"quiz":null},{"id":4,"qid":1,"uid":56,"answer":"这一特点非常好用","isRecord":"0","timing":"0","createTime":"47 Year","realName":"","avatar":"","aSex":"2","pcid":null,"quiz":null}],"total":2}
             */

            private int id;
            private int pcid;
            private int uid;
            private String content;
            private String isDel;
            private String createTime;
            private AnswerBean answer;

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

            public AnswerBean getAnswer() {
                return answer;
            }

            public void setAnswer(AnswerBean answer) {
                this.answer = answer;
            }

            public static class AnswerBean {
                /**
                 * rows : [{"id":1,"qid":1,"uid":5,"answer":"这么没有","isRecord":"1","timing":"3.5","createTime":"19 Days","realName":"","avatar":"http://o9kg05vzs.bkt.clouddn.com/2016-09-24_KmjZYrXl.png","aSex":"1","pcid":null,"quiz":null},{"id":4,"qid":1,"uid":56,"answer":"这一特点非常好用","isRecord":"0","timing":"0","createTime":"47 Year","realName":"","avatar":"","aSex":"2","pcid":null,"quiz":null}]
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
                     * id : 1
                     * qid : 1
                     * uid : 5
                     * answer : 这么没有
                     * isRecord : 1
                     * timing : 3.5
                     * createTime : 19 Days
                     * realName :
                     * avatar : http://o9kg05vzs.bkt.clouddn.com/2016-09-24_KmjZYrXl.png
                     * aSex : 1
                     * pcid : null
                     * quiz : null
                     */

                    private int id;
                    private int qid;
                    private int uid;
                    private String answer;
                    private String isRecord;
                    private String timing;
                    private String createTime;
                    private String realName;
                    private String avatar;
                    private String aSex;
                    private Object pcid;
                    private Object quiz;

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public int getQid() {
                        return qid;
                    }

                    public void setQid(int qid) {
                        this.qid = qid;
                    }

                    public int getUid() {
                        return uid;
                    }

                    public void setUid(int uid) {
                        this.uid = uid;
                    }

                    public String getAnswer() {
                        return answer;
                    }

                    public void setAnswer(String answer) {
                        this.answer = answer;
                    }

                    public String getIsRecord() {
                        return isRecord;
                    }

                    public void setIsRecord(String isRecord) {
                        this.isRecord = isRecord;
                    }

                    public String getTiming() {
                        return timing;
                    }

                    public void setTiming(String timing) {
                        this.timing = timing;
                    }

                    public String getCreateTime() {
                        return createTime;
                    }

                    public void setCreateTime(String createTime) {
                        this.createTime = createTime;
                    }

                    public String getRealName() {
                        return realName;
                    }

                    public void setRealName(String realName) {
                        this.realName = realName;
                    }

                    public String getAvatar() {
                        return avatar;
                    }

                    public void setAvatar(String avatar) {
                        this.avatar = avatar;
                    }

                    public String getASex() {
                        return aSex;
                    }

                    public void setASex(String aSex) {
                        this.aSex = aSex;
                    }

                    public Object getPcid() {
                        return pcid;
                    }

                    public void setPcid(Object pcid) {
                        this.pcid = pcid;
                    }

                    public Object getQuiz() {
                        return quiz;
                    }

                    public void setQuiz(Object quiz) {
                        this.quiz = quiz;
                    }
                }
            }
        }
    }
}
