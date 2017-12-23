package com.giiisp.giiisp.entity;

import java.util.List;

/**
 * Created by Thinkpad on 2017/7/13.
 */

public class QAEntity extends BaseEntity{

    /**
     * quizInfo : {"rows":[{"pcid":10847,"qid":14,"uid":255,"realName":"傲","aAvatar":"http://o9kg05vzs.bkt.clouddn.com/2016-06-30_Plb0h07j.png","createTime":"2017.07.13 11:41","answer":{"rows":[{"id":11,"qid":14,"uid":443,"realName":"陈昭宇","content":"量子通信是指利用量子纠缠效应进行信息传递的一种新型的通讯方式。量子通讯是近二十年发展起来的新型交叉学科，是量子论和信息论相结合的新的研究领域。量子通信主要涉及：量子密码通信、量子远程传态和量子密集编码等，近来这门学科已逐步从理论走向实验，并向实用化发展。高效安全的信息传输日益受到人们的关注。基于量子力学的基本原理，并因此成为国际上量子物理和信息科学的研究热点。","isRecord":"0","timing":"0","isDel":"0","createTime":"2017.07.13 12:01"}],"total":1},"quiz":{"rows":[{"id":14,"pcid":10847,"uid":255,"content":"什么事量子通信？","firstQuiz":"0","isDel":"0","createTime":"2017.07.13 11:41"}],"total":1},"quizTwo":{"rows":[{"id":15,"pcid":10847,"uid":255,"content":"谢谢您的解答","firstQuiz":"1","isDel":"0","createTime":"2017.07.13 13:01"}],"total":1},"answerTwo":{"rows":[{"id":12,"qid":15,"uid":443,"realName":"陈昭宇","content":"http://oq2xwecq0.bkt.clouddn.com/ass_443_44_195.mp3.mp3","isRecord":"1","timing":"68","isDel":"0","createTime":"2017.07.13 13:01"}],"total":1}}],"total":1}
     */

    private QuizInfoBean quizInfo;
    private int  aid;

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public QuizInfoBean getQuizInfo() {
        return quizInfo;
    }

    public void setQuizInfo(QuizInfoBean quizInfo) {
        this.quizInfo = quizInfo;
    }

    public static class QuizInfoBean {
        /**
         * rows : [{"pcid":10847,"qid":14,"uid":255,"realName":"傲","aAvatar":"http://o9kg05vzs.bkt.clouddn.com/2016-06-30_Plb0h07j.png","createTime":"2017.07.13 11:41","answer":{"rows":[{"id":11,"qid":14,"uid":443,"realName":"陈昭宇","content":"量子通信是指利用量子纠缠效应进行信息传递的一种新型的通讯方式。量子通讯是近二十年发展起来的新型交叉学科，是量子论和信息论相结合的新的研究领域。量子通信主要涉及：量子密码通信、量子远程传态和量子密集编码等，近来这门学科已逐步从理论走向实验，并向实用化发展。高效安全的信息传输日益受到人们的关注。基于量子力学的基本原理，并因此成为国际上量子物理和信息科学的研究热点。","isRecord":"0","timing":"0","isDel":"0","createTime":"2017.07.13 12:01"}],"total":1},"quiz":{"rows":[{"id":14,"pcid":10847,"uid":255,"content":"什么事量子通信？","firstQuiz":"0","isDel":"0","createTime":"2017.07.13 11:41"}],"total":1},"quizTwo":{"rows":[{"id":15,"pcid":10847,"uid":255,"content":"谢谢您的解答","firstQuiz":"1","isDel":"0","createTime":"2017.07.13 13:01"}],"total":1},"answerTwo":{"rows":[{"id":12,"qid":15,"uid":443,"realName":"陈昭宇","content":"http://oq2xwecq0.bkt.clouddn.com/ass_443_44_195.mp3.mp3","isRecord":"1","timing":"68","isDel":"0","createTime":"2017.07.13 13:01"}],"total":1}}]
         * total : 1
         */

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
            /**
             * pcid : 10847
             * qid : 14
             * uid : 255
             * realName : 傲
             * aAvatar : http://o9kg05vzs.bkt.clouddn.com/2016-06-30_Plb0h07j.png
             * createTime : 2017.07.13 11:41
             * answer : {"rows":[{"id":11,"qid":14,"uid":443,"realName":"陈昭宇","content":"量子通信是指利用量子纠缠效应进行信息传递的一种新型的通讯方式。量子通讯是近二十年发展起来的新型交叉学科，是量子论和信息论相结合的新的研究领域。量子通信主要涉及：量子密码通信、量子远程传态和量子密集编码等，近来这门学科已逐步从理论走向实验，并向实用化发展。高效安全的信息传输日益受到人们的关注。基于量子力学的基本原理，并因此成为国际上量子物理和信息科学的研究热点。","isRecord":"0","timing":"0","isDel":"0","createTime":"2017.07.13 12:01"}],"total":1}
             * quiz : {"rows":[{"id":14,"pcid":10847,"uid":255,"content":"什么事量子通信？","firstQuiz":"0","isDel":"0","createTime":"2017.07.13 11:41"}],"total":1}
             * quizTwo : {"rows":[{"id":15,"pcid":10847,"uid":255,"content":"谢谢您的解答","firstQuiz":"1","isDel":"0","createTime":"2017.07.13 13:01"}],"total":1}
             * answerTwo : {"rows":[{"id":12,"qid":15,"uid":443,"realName":"陈昭宇","content":"http://oq2xwecq0.bkt.clouddn.com/ass_443_44_195.mp3.mp3","isRecord":"1","timing":"68","isDel":"0","createTime":"2017.07.13 13:01"}],"total":1}
             */

            private String pcid;
            private String qid;
            private String uid;
            private String realName;
            private String aAvatar;
            private String createTime;
            private AnswerBean answer;
            private QuizBean quiz;
            private QuizBean quizTwo;
            private AnswerBean answerTwo;

            public String getPcid() {
                return pcid;
            }

            public void setPcid(String pcid) {
                this.pcid = pcid;
            }

            public String getQid() {
                return qid;
            }

            public void setQid(String qid) {
                this.qid = qid;
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uidX) {
                this.uid = uidX;
            }

            public String getRealName() {
                return realName;
            }

            public void setRealName(String realName) {
                this.realName = realName;
            }

            public String getAAvatar() {
                return aAvatar;
            }

            public void setAAvatar(String aAvatar) {
                this.aAvatar = aAvatar;
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

            public QuizBean getQuiz() {
                return quiz;
            }

            public void setQuiz(QuizBean quiz) {
                this.quiz = quiz;
            }

            public QuizBean getQuizTwo() {
                return quizTwo;
            }

            public void setQuizTwo(QuizBean quizTwo) {
                this.quizTwo = quizTwo;
            }

            public AnswerBean getAnswerTwo() {
                return answerTwo;
            }

            public void setAnswerTwo(AnswerBean answerTwo) {
                this.answerTwo = answerTwo;
            }

            public static class AnswerBean {
                /**
                 * rows : [{"id":11,"qid":14,"uid":443,"realName":"陈昭宇","content":"量子通信是指利用量子纠缠效应进行信息传递的一种新型的通讯方式。量子通讯是近二十年发展起来的新型交叉学科，是量子论和信息论相结合的新的研究领域。量子通信主要涉及：量子密码通信、量子远程传态和量子密集编码等，近来这门学科已逐步从理论走向实验，并向实用化发展。高效安全的信息传输日益受到人们的关注。基于量子力学的基本原理，并因此成为国际上量子物理和信息科学的研究热点。","isRecord":"0","timing":"0","isDel":"0","createTime":"2017.07.13 12:01"}]
                 * total : 1
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
                     * id : 11
                     * qid : 14
                     * uid : 443
                     * realName : 陈昭宇
                     * content : 量子通信是指利用量子纠缠效应进行信息传递的一种新型的通讯方式。量子通讯是近二十年发展起来的新型交叉学科，是量子论和信息论相结合的新的研究领域。量子通信主要涉及：量子密码通信、量子远程传态和量子密集编码等，近来这门学科已逐步从理论走向实验，并向实用化发展。高效安全的信息传输日益受到人们的关注。基于量子力学的基本原理，并因此成为国际上量子物理和信息科学的研究热点。
                     * isRecord : 0
                     * timing : 0
                     * isDel : 0
                     * createTime : 2017.07.13 12:01
                     */

                    private int id;
                    private int qid;
                    private int uid;
                    private String realName;
                    private String content;
                    private String isRecord;
                    private String record;
                    private String timing;
                    private String isDel;
                    private String createTime;

                    public String getRecord() {
                        return record;
                    }

                    public void setRecord(String record) {
                        this.record = record;
                    }

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

                    public String getRealName() {
                        return realName;
                    }

                    public void setRealName(String realName) {
                        this.realName = realName;
                    }

                    public String getContent() {
                        return content;
                    }

                    public void setContent(String content) {
                        this.content = content;
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

            public static class QuizBean {
                /**
                 * rows : [{"id":14,"pcid":10847,"uid":255,"content":"什么事量子通信？","firstQuiz":"0","isDel":"0","createTime":"2017.07.13 11:41"}]
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
                     * id : 14
                     * pcid : 10847
                     * uid : 255
                     * content : 什么事量子通信？
                     * firstQuiz : 0
                     * isDel : 0
                     * createTime : 2017.07.13 11:41
                     */

                    private String id;
                    private String pcid;
                    private String uid;
                    private String content;
                    private String firstQuiz;
                    private String isDel;
                    private String createTime;

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

                    public String getUid() {
                        return uid;
                    }

                    public void setUid(String uidX) {
                        this.uid = uidX;
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

            public static class QuizTwoBean {
                /**
                 * rows : [{"id":15,"pcid":10847,"uid":255,"content":"谢谢您的解答","firstQuiz":"1","isDel":"0","createTime":"2017.07.13 13:01"}]
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
                     * id : 15
                     * pcid : 10847
                     * uid : 255
                     * content : 谢谢您的解答
                     * firstQuiz : 1
                     * isDel : 0
                     * createTime : 2017.07.13 13:01
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

                    public void setUidX(int uidX) {
                        this.uid = uidX;
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

            public static class AnswerTwoBean {
                /**
                 * rows : [{"id":12,"qid":15,"uid":443,"realName":"陈昭宇","content":"http://oq2xwecq0.bkt.clouddn.com/ass_443_44_195.mp3.mp3","isRecord":"1","timing":"68","isDel":"0","createTime":"2017.07.13 13:01"}]
                 * total : 1
                 */

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

                public static class RowsBeanXXX {
                    /**
                     * id : 12
                     * qid : 15
                     * uid : 443
                     * realName : 陈昭宇
                     * content : http://oq2xwecq0.bkt.clouddn.com/ass_443_44_195.mp3.mp3
                     * isRecord : 1
                     * timing : 68
                     * isDel : 0
                     * createTime : 2017.07.13 13:01
                     */

                    private int id;
                    private int qid;
                    private int uid;
                    private String realName;
                    private String content;
                    private String isRecord;
                    private String timing;
                    private String isDel;
                    private String createTime;

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

                    public void setUid(int uidX) {
                        this.uid = uidX;
                    }

                    public String getRealName() {
                        return realName;
                    }

                    public void setRealName(String realName) {
                        this.realName = realName;
                    }

                    public String getContent() {
                        return content;
                    }

                    public void setContent(String content) {
                        this.content = content;
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
        }
    }
}
