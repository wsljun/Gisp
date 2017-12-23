package com.giiisp.giiisp.entity;

import java.util.List;

/**
 * 领域 关键字 获取
 * Created by Thinkpad on 2017/6/9.
 */

public class AttentionEntity extends BaseEntity {
    /**
     * t : 1
     * flag : 1
     * pbType : [{"id":2,"uid":1,"content":"化学","flag":"1","isDel":"0","createTime":"1970.01.01 00:00"},{"id":1,"uid":1,"content":"天文学","flag":"1","isDel":"0","createTime":"1970.01.01 00:00"}]
     */

    private String t;
    private String flag;
    private List<PbTypeBean> pbType;

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public List<PbTypeBean> getPbType() {
        return pbType;
    }

    public void setPbType(List<PbTypeBean> pbType) {
        this.pbType = pbType;
    }
}
