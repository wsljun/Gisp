package com.giiisp.giiisp.entity;

import java.util.List;

/**
 * Created by Thinkpad on 2017/7/8.
 */

public class PaperDatailEntity extends BaseEntity{


    /**
     * paperBase : {"id":4494,"uid":443,"title":"集思谱商业计划书","type":"1","isDel":"0","paperOrSummarize":"2","isLocked":"0","followedNum":0,"likedNum":0,"commentNum":0,"shareNum":0,"readNum":532,"quizNum":2,"answerNum":0,"status":"1","downloadNum":0,"createTime":"2017.07.07 00:00","updateTime":"2017.07.07 00:00","path":"","realName":"陈昭宇","degree":"博士后","jobTitle":"","organization":"","orgEng":"","aAvatar":"http://oq2xwecq0.bkt.clouddn.com/ass_426_12_570.png","photoOne":{"rows":[{"id":4494,"readNum":532,"followedNum":0,"downloadNum":0,"quizNum":2,"firstPic":"http://oq2xwecq0.bkt.clouddn.com/ass_443_1_629.JPG","createTime":"2017.07.07 00:00","photos":{"rows":[{"id":10847,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_1_629.JPG","version":"0","order":1,"isCover":"1","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10848,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_2_674.JPG","version":"0","order":2,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10849,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_3_146.JPG","version":"0","order":3,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10850,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_4_765.JPG","version":"0","order":4,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10851,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_5_347.JPG","version":"0","order":5,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10852,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_6_549.JPG","version":"0","order":6,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10853,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_7_702.JPG","version":"0","order":7,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10854,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_8_873.JPG","version":"0","order":8,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10855,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_9_406.JPG","version":"0","order":9,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10856,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_10_956.JPG","version":"0","order":10,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10857,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_11_399.JPG","version":"0","order":11,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10858,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_12_146.JPG","version":"0","order":12,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10859,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_13_293.JPG","version":"0","order":13,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10860,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_14_276.JPG","version":"0","order":14,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10861,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_15_279.JPG","version":"0","order":15,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10862,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_16_405.JPG","version":"0","order":16,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10863,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_17_531.JPG","version":"0","order":17,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10864,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_18_308.JPG","version":"0","order":18,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10865,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_19_581.JPG","version":"0","order":19,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10866,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_20_566.JPG","version":"0","order":20,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10867,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_21_45.JPG","version":"0","order":21,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10868,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_22_492.JPG","version":"0","order":22,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10869,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_23_435.JPG","version":"0","order":23,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"}],"total":23},"recordOne":{"rows":[{"id":12,"pcid":10847,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_24_280..mp3.mp3","language":"0","size":0.3,"duration":24,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":13,"pcid":10848,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_25_40.mp3.mp3","language":"0","size":0.3,"duration":19,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":14,"pcid":10849,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_26_301.mp3.mp3","language":"0","size":1.1,"duration":74,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":15,"pcid":10850,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_27_673.mp3.mp3","language":"0","size":0.9,"duration":58,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":16,"pcid":10851,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_28_481.mp3.mp3","language":"0","size":0.9,"duration":59,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":17,"pcid":10852,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_29_797.mp3.mp3","language":"0","size":0.5,"duration":33,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":18,"pcid":10853,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_30_216.mp3.mp3","language":"0","size":1,"duration":63,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":19,"pcid":10854,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_31_118.mp3.mp3","language":"0","size":0.8,"duration":56,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":20,"pcid":10855,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_32_445.mp3.mp3","language":"0","size":0.8,"duration":51,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":21,"pcid":10856,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_33_951.mp3.mp3","language":"0","size":0.5,"duration":31,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":22,"pcid":10857,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_34_661.mp3.mp3","language":"0","size":0.3,"duration":23,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":23,"pcid":10858,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_35_82.mp3.mp3","language":"0","size":0.8,"duration":53,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":24,"pcid":10859,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_36_943.mp3.mp3","language":"0","size":0.5,"duration":35,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":25,"pcid":10860,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_37_787.mp3.mp3","language":"0","size":1.1,"duration":74,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":26,"pcid":10861,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_38_834.mp3.mp3","language":"0","size":0.4,"duration":28,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":27,"pcid":10862,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_39_681.mp3.mp3","language":"0","size":0.5,"duration":34,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":28,"pcid":10863,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_40_104.mp3.mp3","language":"0","size":1.4,"duration":94,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":29,"pcid":10864,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_41_623.mp3.mp3","language":"0","size":1,"duration":67,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":30,"pcid":10865,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_42_538.mp3.mp3","language":"0","size":0.3,"duration":20,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":31,"pcid":10866,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_43_367.mp3.mp3","language":"0","size":1.3,"duration":88,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":32,"pcid":10867,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_44_195.mp3.mp3","language":"0","size":1,"duration":68,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":33,"pcid":10868,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_45_539.mp3.mp3","language":"0","size":0.8,"duration":55,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":34,"pcid":10869,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_46_279.mp3.mp3","language":"0","size":0.1,"duration":7,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"}],"total":23},"recordTwo":{"rows":[],"total":0}}],"total":1}}
     */

    private PaperBaseBean paperBase;
    private String isFollowed ;

    public String getIsFollowed() {
        return isFollowed;
    }

    public void setIsFollowed(String isFollowed) {
        this.isFollowed = isFollowed;
    }

    public PaperBaseBean getPaperBase() {
        return paperBase;
    }

    public void setPaperBase(PaperBaseBean paperBase) {
        this.paperBase = paperBase;
    }

    public static class PaperBaseBean {
        /**
         * id : 4494
         * uid : 443
         * title : 集思谱商业计划书
         * type : 1
         * isDel : 0
         * paperOrSummarize : 2
         * isLocked : 0
         * followedNum : 0
         * likedNum : 0
         * commentNum : 0
         * shareNum : 0
         * readNum : 532
         * quizNum : 2
         * answerNum : 0
         * status : 1
         * downloadNum : 0
         * createTime : 2017.07.07 00:00
         * updateTime : 2017.07.07 00:00
         * path :
         * realName : 陈昭宇
         * degree : 博士后
         * jobTitle :
         * organization :
         * orgEng :
         * aAvatar : http://oq2xwecq0.bkt.clouddn.com/ass_426_12_570.png
         * photoOne : {"rows":[{"id":4494,"readNum":532,"followedNum":0,"downloadNum":0,"quizNum":2,"firstPic":"http://oq2xwecq0.bkt.clouddn.com/ass_443_1_629.JPG","createTime":"2017.07.07 00:00","photos":{"rows":[{"id":10847,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_1_629.JPG","version":"0","order":1,"isCover":"1","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10848,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_2_674.JPG","version":"0","order":2,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10849,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_3_146.JPG","version":"0","order":3,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10850,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_4_765.JPG","version":"0","order":4,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10851,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_5_347.JPG","version":"0","order":5,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10852,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_6_549.JPG","version":"0","order":6,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10853,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_7_702.JPG","version":"0","order":7,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10854,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_8_873.JPG","version":"0","order":8,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10855,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_9_406.JPG","version":"0","order":9,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10856,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_10_956.JPG","version":"0","order":10,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10857,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_11_399.JPG","version":"0","order":11,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10858,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_12_146.JPG","version":"0","order":12,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10859,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_13_293.JPG","version":"0","order":13,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10860,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_14_276.JPG","version":"0","order":14,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10861,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_15_279.JPG","version":"0","order":15,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10862,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_16_405.JPG","version":"0","order":16,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10863,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_17_531.JPG","version":"0","order":17,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10864,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_18_308.JPG","version":"0","order":18,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10865,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_19_581.JPG","version":"0","order":19,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10866,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_20_566.JPG","version":"0","order":20,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10867,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_21_45.JPG","version":"0","order":21,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10868,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_22_492.JPG","version":"0","order":22,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10869,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_23_435.JPG","version":"0","order":23,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"}],"total":23},"recordOne":{"rows":[{"id":12,"pcid":10847,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_24_280..mp3.mp3","language":"0","size":0.3,"duration":24,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":13,"pcid":10848,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_25_40.mp3.mp3","language":"0","size":0.3,"duration":19,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":14,"pcid":10849,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_26_301.mp3.mp3","language":"0","size":1.1,"duration":74,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":15,"pcid":10850,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_27_673.mp3.mp3","language":"0","size":0.9,"duration":58,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":16,"pcid":10851,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_28_481.mp3.mp3","language":"0","size":0.9,"duration":59,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":17,"pcid":10852,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_29_797.mp3.mp3","language":"0","size":0.5,"duration":33,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":18,"pcid":10853,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_30_216.mp3.mp3","language":"0","size":1,"duration":63,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":19,"pcid":10854,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_31_118.mp3.mp3","language":"0","size":0.8,"duration":56,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":20,"pcid":10855,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_32_445.mp3.mp3","language":"0","size":0.8,"duration":51,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":21,"pcid":10856,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_33_951.mp3.mp3","language":"0","size":0.5,"duration":31,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":22,"pcid":10857,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_34_661.mp3.mp3","language":"0","size":0.3,"duration":23,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":23,"pcid":10858,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_35_82.mp3.mp3","language":"0","size":0.8,"duration":53,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":24,"pcid":10859,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_36_943.mp3.mp3","language":"0","size":0.5,"duration":35,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":25,"pcid":10860,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_37_787.mp3.mp3","language":"0","size":1.1,"duration":74,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":26,"pcid":10861,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_38_834.mp3.mp3","language":"0","size":0.4,"duration":28,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":27,"pcid":10862,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_39_681.mp3.mp3","language":"0","size":0.5,"duration":34,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":28,"pcid":10863,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_40_104.mp3.mp3","language":"0","size":1.4,"duration":94,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":29,"pcid":10864,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_41_623.mp3.mp3","language":"0","size":1,"duration":67,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":30,"pcid":10865,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_42_538.mp3.mp3","language":"0","size":0.3,"duration":20,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":31,"pcid":10866,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_43_367.mp3.mp3","language":"0","size":1.3,"duration":88,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":32,"pcid":10867,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_44_195.mp3.mp3","language":"0","size":1,"duration":68,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":33,"pcid":10868,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_45_539.mp3.mp3","language":"0","size":0.8,"duration":55,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":34,"pcid":10869,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_46_279.mp3.mp3","language":"0","size":0.1,"duration":7,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"}],"total":23},"recordTwo":{"rows":[],"total":0}}],"total":1}
         */

        private String id;
        private String uid;
        private String title;
        private String type;
        private String isDel;
        private String paperOrSummarize;
        private String isLocked;
        private String followedNum;
        private String likedNum;
        private String commentNum;
        private String shareNum;
        private String readNum;
        private String quizNum;
        private String answerNum;
        private String status;
        private String downloadNum;
        private String createTime;
        private String updateTime;
        private String path;
        private String realName;
        private String degree;
        private String jobTitle;
        private String organization;
        private String orgEng;
        private String aAvatar;
        private PhotoOneBean photoOne;
        private PhotoOneBean photoTwo;
        private PhotoOneBean photoThree;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uidX) {
            this.uid = uidX;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getIsDel() {
            return isDel;
        }

        public void setIsDel(String isDel) {
            this.isDel = isDel;
        }

        public String getPaperOrSummarize() {
            return paperOrSummarize;
        }

        public void setPaperOrSummarize(String paperOrSummarize) {
            this.paperOrSummarize = paperOrSummarize;
        }

        public String getIsLocked() {
            return isLocked;
        }

        public void setIsLocked(String isLocked) {
            this.isLocked = isLocked;
        }

        public String getFollowedNum() {
            return followedNum;
        }

        public void setFollowedNum(String followedNum) {
            this.followedNum = followedNum;
        }

        public String getLikedNum() {
            return likedNum;
        }

        public void setLikedNum(String likedNum) {
            this.likedNum = likedNum;
        }

        public String getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(String commentNum) {
            this.commentNum = commentNum;
        }

        public String getShareNum() {
            return shareNum;
        }

        public void setShareNum(String shareNum) {
            this.shareNum = shareNum;
        }

        public String getReadNum() {
            return readNum;
        }

        public void setReadNum(String readNum) {
            this.readNum = readNum;
        }

        public String getQuizNum() {
            return quizNum;
        }

        public void setQuizNum(String quizNum) {
            this.quizNum = quizNum;
        }

        public String getAnswerNum() {
            return answerNum;
        }

        public void setAnswerNum(String answerNum) {
            this.answerNum = answerNum;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDownloadNum() {
            return downloadNum;
        }

        public void setDownloadNum(String downloadNum) {
            this.downloadNum = downloadNum;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public String getUpdateTime() {
            return updateTime;
        }

        public void setUpdateTime(String updateTime) {
            this.updateTime = updateTime;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getDegree() {
            return degree;
        }

        public void setDegree(String degree) {
            this.degree = degree;
        }

        public String getJobTitle() {
            return jobTitle;
        }

        public void setJobTitle(String jobTitle) {
            this.jobTitle = jobTitle;
        }

        public String getOrganization() {
            return organization;
        }

        public void setOrganization(String organization) {
            this.organization = organization;
        }

        public String getOrgEng() {
            return orgEng;
        }

        public void setOrgEng(String orgEng) {
            this.orgEng = orgEng;
        }

        public String getAAvatar() {
            return aAvatar;
        }

        public void setAAvatar(String aAvatar) {
            this.aAvatar = aAvatar;
        }

        public PhotoOneBean getPhotoOne() {
            return photoOne;
        }

        public PhotoOneBean getPhotoTwo() {
            return photoTwo;
        }

        public void setPhotoTwo(PhotoOneBean photoTwo) {
            this.photoTwo = photoTwo;
        }

        public PhotoOneBean getPhotoThree() {
            return photoThree;
        }

        public void setPhotoThree(PhotoOneBean photoThree) {
            this.photoThree = photoThree;
        }

        public void setPhotoOne(PhotoOneBean photoOne) {
            this.photoOne = photoOne;
        }

        public static class PhotoOneBean {
            /**
             * rows : [{"id":4494,"readNum":532,"followedNum":0,"downloadNum":0,"quizNum":2,"firstPic":"http://oq2xwecq0.bkt.clouddn.com/ass_443_1_629.JPG","createTime":"2017.07.07 00:00","photos":{"rows":[{"id":10847,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_1_629.JPG","version":"0","order":1,"isCover":"1","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10848,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_2_674.JPG","version":"0","order":2,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10849,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_3_146.JPG","version":"0","order":3,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10850,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_4_765.JPG","version":"0","order":4,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10851,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_5_347.JPG","version":"0","order":5,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10852,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_6_549.JPG","version":"0","order":6,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10853,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_7_702.JPG","version":"0","order":7,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10854,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_8_873.JPG","version":"0","order":8,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10855,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_9_406.JPG","version":"0","order":9,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10856,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_10_956.JPG","version":"0","order":10,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10857,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_11_399.JPG","version":"0","order":11,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10858,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_12_146.JPG","version":"0","order":12,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10859,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_13_293.JPG","version":"0","order":13,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10860,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_14_276.JPG","version":"0","order":14,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10861,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_15_279.JPG","version":"0","order":15,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10862,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_16_405.JPG","version":"0","order":16,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10863,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_17_531.JPG","version":"0","order":17,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10864,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_18_308.JPG","version":"0","order":18,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10865,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_19_581.JPG","version":"0","order":19,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10866,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_20_566.JPG","version":"0","order":20,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10867,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_21_45.JPG","version":"0","order":21,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10868,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_22_492.JPG","version":"0","order":22,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10869,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_23_435.JPG","version":"0","order":23,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"}],"total":23},"recordOne":{"rows":[{"id":12,"pcid":10847,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_24_280..mp3.mp3","language":"0","size":0.3,"duration":24,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":13,"pcid":10848,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_25_40.mp3.mp3","language":"0","size":0.3,"duration":19,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":14,"pcid":10849,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_26_301.mp3.mp3","language":"0","size":1.1,"duration":74,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":15,"pcid":10850,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_27_673.mp3.mp3","language":"0","size":0.9,"duration":58,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":16,"pcid":10851,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_28_481.mp3.mp3","language":"0","size":0.9,"duration":59,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":17,"pcid":10852,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_29_797.mp3.mp3","language":"0","size":0.5,"duration":33,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":18,"pcid":10853,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_30_216.mp3.mp3","language":"0","size":1,"duration":63,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":19,"pcid":10854,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_31_118.mp3.mp3","language":"0","size":0.8,"duration":56,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":20,"pcid":10855,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_32_445.mp3.mp3","language":"0","size":0.8,"duration":51,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":21,"pcid":10856,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_33_951.mp3.mp3","language":"0","size":0.5,"duration":31,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":22,"pcid":10857,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_34_661.mp3.mp3","language":"0","size":0.3,"duration":23,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":23,"pcid":10858,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_35_82.mp3.mp3","language":"0","size":0.8,"duration":53,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":24,"pcid":10859,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_36_943.mp3.mp3","language":"0","size":0.5,"duration":35,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":25,"pcid":10860,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_37_787.mp3.mp3","language":"0","size":1.1,"duration":74,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":26,"pcid":10861,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_38_834.mp3.mp3","language":"0","size":0.4,"duration":28,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":27,"pcid":10862,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_39_681.mp3.mp3","language":"0","size":0.5,"duration":34,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":28,"pcid":10863,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_40_104.mp3.mp3","language":"0","size":1.4,"duration":94,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":29,"pcid":10864,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_41_623.mp3.mp3","language":"0","size":1,"duration":67,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":30,"pcid":10865,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_42_538.mp3.mp3","language":"0","size":0.3,"duration":20,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":31,"pcid":10866,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_43_367.mp3.mp3","language":"0","size":1.3,"duration":88,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":32,"pcid":10867,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_44_195.mp3.mp3","language":"0","size":1,"duration":68,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":33,"pcid":10868,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_45_539.mp3.mp3","language":"0","size":0.8,"duration":55,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":34,"pcid":10869,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_46_279.mp3.mp3","language":"0","size":0.1,"duration":7,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"}],"total":23},"recordTwo":{"rows":[],"total":0}}]
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
                 * id : 4494
                 * readNum : 532
                 * followedNum : 0
                 * downloadNum : 0
                 * quizNum : 2
                 * firstPic : http://oq2xwecq0.bkt.clouddn.com/ass_443_1_629.JPG
                 * createTime : 2017.07.07 00:00
                 * photos : {"rows":[{"id":10847,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_1_629.JPG","version":"0","order":1,"isCover":"1","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10848,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_2_674.JPG","version":"0","order":2,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10849,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_3_146.JPG","version":"0","order":3,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10850,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_4_765.JPG","version":"0","order":4,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10851,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_5_347.JPG","version":"0","order":5,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10852,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_6_549.JPG","version":"0","order":6,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10853,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_7_702.JPG","version":"0","order":7,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10854,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_8_873.JPG","version":"0","order":8,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10855,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_9_406.JPG","version":"0","order":9,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10856,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_10_956.JPG","version":"0","order":10,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10857,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_11_399.JPG","version":"0","order":11,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10858,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_12_146.JPG","version":"0","order":12,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10859,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_13_293.JPG","version":"0","order":13,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10860,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_14_276.JPG","version":"0","order":14,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10861,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_15_279.JPG","version":"0","order":15,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10862,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_16_405.JPG","version":"0","order":16,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10863,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_17_531.JPG","version":"0","order":17,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10864,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_18_308.JPG","version":"0","order":18,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10865,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_19_581.JPG","version":"0","order":19,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10866,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_20_566.JPG","version":"0","order":20,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10867,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_21_45.JPG","version":"0","order":21,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10868,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_22_492.JPG","version":"0","order":22,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10869,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_23_435.JPG","version":"0","order":23,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"}],"total":23}
                 * recordOne : {"rows":[{"id":12,"pcid":10847,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_24_280..mp3.mp3","language":"0","size":0.3,"duration":24,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":13,"pcid":10848,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_25_40.mp3.mp3","language":"0","size":0.3,"duration":19,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":14,"pcid":10849,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_26_301.mp3.mp3","language":"0","size":1.1,"duration":74,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":15,"pcid":10850,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_27_673.mp3.mp3","language":"0","size":0.9,"duration":58,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":16,"pcid":10851,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_28_481.mp3.mp3","language":"0","size":0.9,"duration":59,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":17,"pcid":10852,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_29_797.mp3.mp3","language":"0","size":0.5,"duration":33,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":18,"pcid":10853,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_30_216.mp3.mp3","language":"0","size":1,"duration":63,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":19,"pcid":10854,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_31_118.mp3.mp3","language":"0","size":0.8,"duration":56,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":20,"pcid":10855,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_32_445.mp3.mp3","language":"0","size":0.8,"duration":51,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":21,"pcid":10856,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_33_951.mp3.mp3","language":"0","size":0.5,"duration":31,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":22,"pcid":10857,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_34_661.mp3.mp3","language":"0","size":0.3,"duration":23,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":23,"pcid":10858,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_35_82.mp3.mp3","language":"0","size":0.8,"duration":53,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":24,"pcid":10859,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_36_943.mp3.mp3","language":"0","size":0.5,"duration":35,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":25,"pcid":10860,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_37_787.mp3.mp3","language":"0","size":1.1,"duration":74,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":26,"pcid":10861,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_38_834.mp3.mp3","language":"0","size":0.4,"duration":28,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":27,"pcid":10862,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_39_681.mp3.mp3","language":"0","size":0.5,"duration":34,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":28,"pcid":10863,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_40_104.mp3.mp3","language":"0","size":1.4,"duration":94,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":29,"pcid":10864,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_41_623.mp3.mp3","language":"0","size":1,"duration":67,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":30,"pcid":10865,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_42_538.mp3.mp3","language":"0","size":0.3,"duration":20,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":31,"pcid":10866,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_43_367.mp3.mp3","language":"0","size":1.3,"duration":88,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":32,"pcid":10867,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_44_195.mp3.mp3","language":"0","size":1,"duration":68,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":33,"pcid":10868,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_45_539.mp3.mp3","language":"0","size":0.8,"duration":55,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":34,"pcid":10869,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_46_279.mp3.mp3","language":"0","size":0.1,"duration":7,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"}],"total":23}
                 * recordTwo : {"rows":[],"total":0}
                 */

                private String id;
                private String readNum;
                private String followedNum;
                private String downloadNum;
                private String quizNum;
                private String firstPic;
                private String isFollowed;
                private String createTime;
                private PhotosBean photos;
                private RecordOneBean recordOne;
                private RecordOneBean recordTwo;

                public String getIsFollowed() {
                    return isFollowed;
                }

                public void setIsFollowed(String isFollowed) {
                    this.isFollowed = isFollowed;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getReadNum() {
                    return readNum;
                }

                public void setReadNum(String readNum) {
                    this.readNum = readNum;
                }

                public String getFollowedNum() {
                    return followedNum;
                }

                public void setFollowedNum(String followedNum) {
                    this.followedNum = followedNum;
                }

                public String getDownloadNum() {
                    return downloadNum;
                }

                public void setDownloadNum(String downloadNum) {
                    this.downloadNum = downloadNum;
                }

                public String getQuizNum() {
                    return quizNum;
                }

                public void setQuizNum(String quizNum) {
                    this.quizNum = quizNum;
                }

                public String getFirstPic() {
                    return firstPic;
                }

                public void setFirstPic(String firstPic) {
                    this.firstPic = firstPic;
                }

                public String getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(String createTime) {
                    this.createTime = createTime;
                }

                public PhotosBean getPhotos() {
                    return photos;
                }

                public void setPhotos(PhotosBean photos) {
                    this.photos = photos;
                }

                public RecordOneBean getRecordOne() {
                    return recordOne;
                }

                public void setRecordOne(RecordOneBean recordOne) {
                    this.recordOne = recordOne;
                }

                public RecordOneBean getRecordTwo() {
                    return recordTwo;
                }

                public void setRecordTwo(RecordOneBean recordTwo) {
                    this.recordTwo = recordTwo;
                }

                public static class PhotosBean {
                    /**
                     * rows : [{"id":10847,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_1_629.JPG","version":"0","order":1,"isCover":"1","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10848,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_2_674.JPG","version":"0","order":2,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10849,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_3_146.JPG","version":"0","order":3,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10850,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_4_765.JPG","version":"0","order":4,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10851,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_5_347.JPG","version":"0","order":5,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10852,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_6_549.JPG","version":"0","order":6,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10853,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_7_702.JPG","version":"0","order":7,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10854,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_8_873.JPG","version":"0","order":8,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10855,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_9_406.JPG","version":"0","order":9,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10856,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_10_956.JPG","version":"0","order":10,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10857,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_11_399.JPG","version":"0","order":11,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10858,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_12_146.JPG","version":"0","order":12,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10859,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_13_293.JPG","version":"0","order":13,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10860,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_14_276.JPG","version":"0","order":14,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10861,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_15_279.JPG","version":"0","order":15,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10862,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_16_405.JPG","version":"0","order":16,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10863,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_17_531.JPG","version":"0","order":17,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10864,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_18_308.JPG","version":"0","order":18,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10865,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_19_581.JPG","version":"0","order":19,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10866,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_20_566.JPG","version":"0","order":20,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10867,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_21_45.JPG","version":"0","order":21,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10868,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_22_492.JPG","version":"0","order":22,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"},{"id":10869,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_23_435.JPG","version":"0","order":23,"isCover":"0","isDel":"0","readNum":0,"shareNum":0,"commentNum":0,"likedNum":0,"followedNum":0,"createTime":"1970.01.01 00:00"}]
                     * total : 23
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
                         * id : 10847
                         * path : http://oq2xwecq0.bkt.clouddn.com/ass_443_1_629.JPG
                         * version : 0
                         * order : 1
                         * isCover : 1
                         * isDel : 0
                         * readNum : 0
                         * shareNum : 0
                         * commentNum : 0
                         * likedNum : 0
                         * followedNum : 0
                         * createTime : 1970.01.01 00:00
                         */

                        private String id;
                        private String path;
                        private String version;
                        private String order;
                        private String isCover;
                        private String isDel;
                        private String readNum;
                        private String shareNum;
                        private String commentNum;
                        private String likedNum;
                        private String followedNum;
                        private String createTime;
                        private String type;

                        public String getType() {
                            return type;
                        }

                        public void setType(String type) {
                            this.type = type;
                        }

                        public String getId() {
                            return id;
                        }

                        public void setId(String id) {
                            this.id = id;
                        }

                        public String getPath() {
                            return path;
                        }

                        public void setPath(String path) {
                            this.path = path;
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

                        public String getIsCover() {
                            return isCover;
                        }

                        public void setIsCover(String isCover) {
                            this.isCover = isCover;
                        }

                        public String getIsDel() {
                            return isDel;
                        }

                        public void setIsDel(String isDel) {
                            this.isDel = isDel;
                        }

                        public String getReadNum() {
                            return readNum;
                        }

                        public void setReadNum(String readNum) {
                            this.readNum = readNum;
                        }

                        public String getShareNum() {
                            return shareNum;
                        }

                        public void setShareNum(String shareNum) {
                            this.shareNum = shareNum;
                        }

                        public String getCommentNum() {
                            return commentNum;
                        }

                        public void setCommentNum(String commentNum) {
                            this.commentNum = commentNum;
                        }

                        public String getLikedNum() {
                            return likedNum;
                        }

                        public void setLikedNum(String likedNum) {
                            this.likedNum = likedNum;
                        }

                        public String getFollowedNum() {
                            return followedNum;
                        }

                        public void setFollowedNum(String followedNum) {
                            this.followedNum = followedNum;
                        }

                        public String getCreateTime() {
                            return createTime;
                        }

                        public void setCreateTime(String createTime) {
                            this.createTime = createTime;
                        }
                    }
                }

                public static class RecordOneBean {
                    /**
                     * rows : [{"id":12,"pcid":10847,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_24_280..mp3.mp3","language":"0","size":0.3,"duration":24,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":13,"pcid":10848,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_25_40.mp3.mp3","language":"0","size":0.3,"duration":19,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":14,"pcid":10849,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_26_301.mp3.mp3","language":"0","size":1.1,"duration":74,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":15,"pcid":10850,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_27_673.mp3.mp3","language":"0","size":0.9,"duration":58,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":16,"pcid":10851,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_28_481.mp3.mp3","language":"0","size":0.9,"duration":59,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":17,"pcid":10852,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_29_797.mp3.mp3","language":"0","size":0.5,"duration":33,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":18,"pcid":10853,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_30_216.mp3.mp3","language":"0","size":1,"duration":63,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":19,"pcid":10854,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_31_118.mp3.mp3","language":"0","size":0.8,"duration":56,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":20,"pcid":10855,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_32_445.mp3.mp3","language":"0","size":0.8,"duration":51,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":21,"pcid":10856,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_33_951.mp3.mp3","language":"0","size":0.5,"duration":31,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":22,"pcid":10857,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_34_661.mp3.mp3","language":"0","size":0.3,"duration":23,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":23,"pcid":10858,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_35_82.mp3.mp3","language":"0","size":0.8,"duration":53,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":24,"pcid":10859,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_36_943.mp3.mp3","language":"0","size":0.5,"duration":35,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":25,"pcid":10860,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_37_787.mp3.mp3","language":"0","size":1.1,"duration":74,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":26,"pcid":10861,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_38_834.mp3.mp3","language":"0","size":0.4,"duration":28,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":27,"pcid":10862,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_39_681.mp3.mp3","language":"0","size":0.5,"duration":34,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":28,"pcid":10863,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_40_104.mp3.mp3","language":"0","size":1.4,"duration":94,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":29,"pcid":10864,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_41_623.mp3.mp3","language":"0","size":1,"duration":67,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":30,"pcid":10865,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_42_538.mp3.mp3","language":"0","size":0.3,"duration":20,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":31,"pcid":10866,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_43_367.mp3.mp3","language":"0","size":1.3,"duration":88,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":32,"pcid":10867,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_44_195.mp3.mp3","language":"0","size":1,"duration":68,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":33,"pcid":10868,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_45_539.mp3.mp3","language":"0","size":0.8,"duration":55,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"},{"id":34,"pcid":10869,"path":"http://oq2xwecq0.bkt.clouddn.com/ass_443_46_279.mp3.mp3","language":"0","size":0.1,"duration":7,"readNum":0,"followedNum":0,"downloadNum":0,"createTime":"2017.07.07 00:00"}]
                     * total : 23
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
                         * id : 12
                         * pcid : 10847
                         * path : http://oq2xwecq0.bkt.clouddn.com/ass_443_24_280..mp3.mp3
                         * language : 0
                         * size : 0.3
                         * duration : 24
                         * readNum : 0
                         * followedNum : 0
                         * downloadNum : 0
                         * createTime : 2017.07.07 00:00
                         */

                        private String id;
                        private String pcid;
                        private String path;
                        private String language;
                        private String size;
                        private String duration;
                        private String readNum;
                        private String followedNum;
                        private String downloadNum;
                        private String createTime;
                        private String type;

                        public String getType() {
                            return type;
                        }

                        public void setType(String type) {
                            this.type = type;
                        }

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

                        public String getPath() {
                            return path;
                        }

                        public void setPath(String path) {
                            this.path = path;
                        }

                        public String getLanguage() {
                            return language;
                        }

                        public void setLanguage(String language) {
                            this.language = language;
                        }

                        public String getSize() {
                            return size;
                        }

                        public void setSize(String size) {
                            this.size = size;
                        }

                        public String getDuration() {
                            return duration;
                        }

                        public void setDuration(String duration) {
                            this.duration = duration;
                        }

                        public String getReadNum() {
                            return readNum;
                        }

                        public void setReadNum(String readNum) {
                            this.readNum = readNum;
                        }

                        public String getFollowedNum() {
                            return followedNum;
                        }

                        public void setFollowedNum(String followedNum) {
                            this.followedNum = followedNum;
                        }

                        public String getDownloadNum() {
                            return downloadNum;
                        }

                        public void setDownloadNum(String downloadNum) {
                            this.downloadNum = downloadNum;
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
}
