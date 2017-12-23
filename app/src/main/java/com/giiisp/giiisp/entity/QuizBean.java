package com.giiisp.giiisp.entity;

import java.util.List;

/**
 * Created by Thinkpad on 2017/6/26.
 */

public class QuizBean {
    private int total;
    private List<AnswerQUizXBean> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<AnswerQUizXBean> getRows() {
        return rows;
    }

}
