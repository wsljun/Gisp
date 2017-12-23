package com.giiisp.giiisp.entity;

import java.util.List;

/**
 * Created by Thinkpad on 2017/6/26.
 */

public class RecommendScholarEntity extends BaseEntity{

    /**
     * rScholar : [{"id":255,"realName":"","avatar":"http://o9kg05vzs.bkt.clouddn.com/2017-02-04_1486204581406.jpg"},{"id":3,"realName":"","avatar":"http://o9kg05vzs.bkt.clouddn.com/2016-10-13_2bOcStgA.png"},{"id":269,"realName":"","avatar":"http://o9kg05vzs.bkt.clouddn.com/2017-03-10_1489129948409.jpg"}]
     * result : 1
     * code :
     * info : 操作成功！
     */

    private List<MyScholarBean> rScholar;

    public List<MyScholarBean> getRScholar() {
        return rScholar;
    }

    public void setRScholar(List<MyScholarBean> rScholar) {
        this.rScholar = rScholar;
    }

}
