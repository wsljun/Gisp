package com.giiisp.giiisp.entity;

/**
 * Created by Thinkpad on 2017/6/30.
 */

public class PaperEntity extends BaseEntity{

    /**
     * total : 1
     * result : 1
     * pageInfo : {"rows":[{"id":11,"uid":2,"title":"帖子一...","type":"2","version":null,"language":null,"isDel":null,"paperOrSummarize":null,"isLocked":null,"followedNum":0,"likedNum":null,"commentNum":null,"shareNum":null,"readNum":0,"quizNum":0,"answerNum":null,"status":"1","downloadNum":0,"createTime":"2016.06.28 16:24","updateTime":"2016.09.22 16:15","path":null,"realName":"","aAvatar":"http://o9kg05vzs.bkt.clouddn.com/2016-08-09_DjxXPeSD.png","picNum":null,"recordNum":null,"isLiked":"0","isFollowed":"0","firstPic":{"rows":[{"id":17,"path":"http:7xtjaq.com2.z0.glb.clouddn.com/2016-06-28_OqO3Pk2a.png","order":1,"version":null,"language":null,"isCover":"1","isDel":null,"readNum":null,"shareNum":null,"commentNum":null,"likedNum":null,"followedNum":null,"createTime":null}],"total":1}}],"total":1}
     * code :
     * info : 操作成功！
     */
/*
* {"result":1,"code":"","antistopInfo":{"rows":[{"pbid":4494,"science":{"rows":[{"id":10,"pbid":4494,"antistop":"推广模式","type":"1","createTime":"2017.07.13 00:00"},{"id":11,"pbid":4494,"antistop":"共享营销","type":"1","createTime":"2017.07.13 00:00"},{"id":12,"pbid":4494,"antistop":"常规竞争问答","type":"1","createTime":"2017.07.13 00:00"}],"total":3},"maths":{"rows":[{"id":16,"pbid":4494,"antistop":"数学标签","type":"2","createTime":"1970.01.01 00:00"}],"total":1},"data":{"rows":[{"id":13,"pbid":4494,"antistop":"市场层级","type":"3","createTime":"2017.07.13 00:00"},{"id":14,"pbid":4494,"antistop":"市场单元","type":"3","createTime":"2017.07.13 00:00"},{"id":15,"pbid":4494,"antistop":"市场规模","type":"3","createTime":"2017.07.13 00:00"}],"total":3}}],"total":1},"info":"操作成功！"}

* */
    private int total;
    private PaperBaseInfo pageInfo;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public PaperBaseInfo getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PaperBaseInfo pageInfo) {
        this.pageInfo = pageInfo;
    }



}
