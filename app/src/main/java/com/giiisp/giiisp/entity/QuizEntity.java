package com.giiisp.giiisp.entity;

/**
 * Created by Thinkpad on 2017/6/26.
 */

public class QuizEntity extends BaseEntity{

    /**
     * result : 1
     * quiz : {"rows":[{"id":4,"pcid":1,"uid":1,"content":"这是什么？","firstQuiz":"0","isDel":"0","createTime":"1970.01.01 00:00","answer":{"rows":[{"id":10,"qid":4,"uid":1,"answer":"这是一个非常好的app","isRecord":"0","timing":"0","createTime":"47 Year","realName":"","avatar":"http://o9kg05vzs.bkt.clouddn.com/2016-11-01_qG6akpfJ.png","aSex":"2","pcid":null,"quiz":null}],"total":1}}],"total":1}
     * code :
     * info : 操作成功！
     */

    private QuizBean quiz;

    public QuizBean getQuiz() {
        return quiz;
    }

    public void setQuiz(QuizBean quiz) {
        this.quiz = quiz;
    }
}
