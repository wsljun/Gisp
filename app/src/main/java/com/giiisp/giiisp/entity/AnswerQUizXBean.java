package com.giiisp.giiisp.entity;

/**
 * Created by Thinkpad on 2017/6/26.
 */

public class AnswerQUizXBean {

    /**
     * id : 19
     * pcid : 10847
     * pbid : 4494
     * uid : 443
     * content : hhdhhdhd
     * firstQuiz : 1
     * version : 0
     * order : 1
     * isDel : 0
     * createTime : 2017.07.24 14:06
     * answer : {"rows":[{"id":14,"qid":19,"pbid":4494,"uid":443,"answer":"hdjjd","record":"","isRecord":"","version":"0","order":"1","timing":"","createTime":"3 Hours","realName":"陈昭宇","avatar":"http://oq2xwecq0.bkt.clouddn.com/ass_426_12_570.png","aSex":"1","pcid":10847,"quiz":"hhdhhdhd"}],"total":1}
     */

    private String id;
    private String pcid;
    private String pbid;
    private String uid;
    private String content;
    private String firstQuiz;
    private String version;
    private String order;
    private String isDel;
    private String createTime;
    private AnswerQUizBean answer;

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

    public String getPbid() {
        return pbid;
    }

    public void setPbid(String pbid) {
        this.pbid = pbid;
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

    public String getFirstQuiz() {
        return firstQuiz;
    }

    public void setFirstQuiz(String firstQuiz) {
        this.firstQuiz = firstQuiz;
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

    public AnswerQUizBean getAnswer() {
        return answer;
    }

    public void setAnswer(AnswerQUizBean answer) {
        this.answer = answer;
    }

}
