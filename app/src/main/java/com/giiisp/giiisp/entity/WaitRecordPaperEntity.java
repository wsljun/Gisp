package com.giiisp.giiisp.entity;

/**
 * Created by Thinkpad on 2017/7/4.
 */

public class WaitRecordPaperEntity extends BaseEntity{

    /**
     * WaitRecordPaper : {"rows":[{"id":4456,"uid":256,"title":"知·味","type":null,"version":"0","language":"0","isDel":null,"paperOrSummarize":null,"isLocked":null,"followedNum":null,"likedNum":null,"commentNum":null,"shareNum":null,"readNum":null,"quizNum":null,"answerNum":null,"status":null,"downloadNum":null,"createTime":"2017.01.10 11:22","updateTime":"2017.01.10 11:22","path":null,"realName":"","aAvatar":"http://tva1.sinaimg.cn/crop.0.0.749.749.180/92a00969jw8f1bom4es0vj20ku0ktt9b.jpg","picNum":1,"recordNum":0,"firstPic":{"rows":[],"total":1}},{"id":4457,"uid":256,"title":"物·语","type":null,"version":"0","language":"0","isDel":null,"paperOrSummarize":null,"isLocked":null,"followedNum":null,"likedNum":null,"commentNum":null,"shareNum":null,"readNum":null,"quizNum":null,"answerNum":null,"status":null,"downloadNum":null,"createTime":"2017.01.10 11:22","updateTime":"2017.01.10 11:22","path":null,"realName":"","aAvatar":"http://tva1.sinaimg.cn/crop.0.0.749.749.180/92a00969jw8f1bom4es0vj20ku0ktt9b.jpg","picNum":1,"recordNum":0,"firstPic":{"rows":[],"total":1}},{"id":4458,"uid":256,"title":"遇·见","type":null,"version":"0","language":"0","isDel":null,"paperOrSummarize":null,"isLocked":null,"followedNum":null,"likedNum":null,"commentNum":null,"shareNum":null,"readNum":null,"quizNum":null,"answerNum":null,"status":null,"downloadNum":null,"createTime":"2017.01.10 11:22","updateTime":"2017.01.10 11:22","path":null,"realName":"","aAvatar":"http://tva1.sinaimg.cn/crop.0.0.749.749.180/92a00969jw8f1bom4es0vj20ku0ktt9b.jpg","picNum":1,"recordNum":0,"firstPic":{"rows":[],"total":1}}],"total":3}
     * result : 1
     * code :
     * info : 操作成功！
     */

    private SubscribeEntity.PageInfoBean WaitRecordPaper;


    public SubscribeEntity.PageInfoBean getWaitRecordPaper() {
        return WaitRecordPaper;
    }

    public void setWaitRecordPaper(SubscribeEntity.PageInfoBean WaitRecordPaper) {
        this.WaitRecordPaper = WaitRecordPaper;
    }


}
