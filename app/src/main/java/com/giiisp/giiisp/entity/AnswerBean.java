package com.giiisp.giiisp.entity;

import java.util.List;

/**
 * 回答bean
 * Created by Thinkpad on 2017/6/26.
 */

public class AnswerBean {
    /**
     * rows : [{"id":2,"qid":2,"uid":1,"answer":"这是一款严谨正经的app","isRecord":"0","timing":null,"createTime":"47 Year","realName":"","avatar":"http://wx.qlogo.cn/mmopen/ajNVdqHZLLDrR8Fl7rg039INjabnYMvxiasckG0cQBibYOtxn7EUaUibjagtklT20Y4h5eibd7ib6ib9pvVwlFZicegKh1uGfTkpkE86NichGibDEuc0/0","aSex":"1","pcid":1,"quiz":"这是什么？"},{"id":6,"qid":2,"uid":1,"answer":"这个回答太过深奥，无法显示","isRecord":"0","timing":null,"createTime":"7 Days","realName":"","avatar":"http://wx.qlogo.cn/mmopen/ajNVdqHZLLDrR8Fl7rg039INjabnYMvxiasckG0cQBibYOtxn7EUaUibjagtklT20Y4h5eibd7ib6ib9pvVwlFZicegKh1uGfTkpkE86NichGibDEuc0/0","aSex":"1","pcid":1,"quiz":"这是什么？"}]
     * total : 2
     */

    private int total;
    private List<AnswerQuizRowsBean> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<AnswerQuizRowsBean> getRows() {
        return rows;
    }

    public void setRows(List<AnswerQuizRowsBean> rows) {
        this.rows = rows;
    }


}
