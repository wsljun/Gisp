package com.giiisp.giiisp.entity;

import java.util.List;

/**
 * Created by Thinkpad on 2017/6/30.
 */

public class PaperPhotoBaen {
    private int total;
    private List<PaperPhotoinfo> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<PaperPhotoinfo> getRows() {
        return rows;
    }

    public void setRows(List<PaperPhotoinfo> rows) {
        this.rows = rows;
    }
}
