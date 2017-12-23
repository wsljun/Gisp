package com.giiisp.giiisp.entity;

import java.util.List;

/**
 * Created by Thinkpad on 2017/6/30.
 */

public class PaperInfoBean {
    private int total;
    private List<PaperPhotoBaen> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<PaperPhotoBaen> getRows() {
        return rows;
    }

    public void setRows(List<PaperPhotoBaen> rows) {
        this.rows = rows;
    }
}
