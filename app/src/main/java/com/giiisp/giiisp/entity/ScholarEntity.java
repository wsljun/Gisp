package com.giiisp.giiisp.entity;

import java.util.List;

/**
 *
 * Created by Thinkpad on 2017/7/6.
 */

public class ScholarEntity extends BaseEntity{
    /**
     * result : 1
     * myScholar : [{"oid":5,"realName":"","avatar":"http://o9kg05vzs.bkt.clouddn.com/2016-11-01_qG6akpfJ.png"},{"oid":255,"realName":"","avatar":"http://o9kg05vzs.bkt.clouddn.com/2016-11-01_qG6akpfJ.png"},{"oid":15,"realName":"","avatar":"http://o9kg05vzs.bkt.clouddn.com/2016-11-01_qG6akpfJ.png"}]
     * code :
     * info : 操作成功！
     */

    private List<MyScholarBean> scholar;
    /**
     * total : 7
     */

    private int total;

    public List<MyScholarBean> getScholar() {
        return scholar;
    }

    public void setScholar(List<MyScholarBean> scholar) {
        this.scholar = scholar;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
