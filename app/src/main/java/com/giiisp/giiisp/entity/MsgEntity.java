package com.giiisp.giiisp.entity;

import java.util.List;

/**
 * Created by Thinkpad on 2017/7/5.
 */

public class MsgEntity extends BaseEntity{

    /**
     * pageInfo : {"rows":[{"pid":674,"msg":" 赞了您的论文：知·味","comment":null,"sort":3,"type":"2","status":null,"aid":16,"aSex":null,"realName":"互动消息","aAvatar":"http://o9kg05vzs.bkt.clouddn.com/2016-07-14_Kz5Ewj6W.png","msgTime":"2017.06.16 17:39"},{"pid":673,"msg":" 关注了您","comment":null,"sort":4,"type":"2","status":null,"aid":255,"aSex":null,"realName":"互动消息","aAvatar":"http://o9kg05vzs.bkt.clouddn.com/2016-07-14_Kz5Ewj6W.png","msgTime":"2017.06.16 17:06"},{"pid":672,"msg":" 赞了您的论文：知·味","comment":null,"sort":3,"type":"2","status":null,"aid":255,"aSex":null,"realName":"互动消息","aAvatar":"http://o9kg05vzs.bkt.clouddn.com/2016-07-14_Kz5Ewj6W.png","msgTime":"2017.06.16 16:58"},{"pid":671,"msg":"收藏了您的论文《知·味》的PPT单页0","comment":null,"sort":5,"type":"2","status":null,"aid":255,"aSex":null,"realName":"互动消息","aAvatar":"http://o9kg05vzs.bkt.clouddn.com/2016-07-14_Kz5Ewj6W.png","msgTime":"2017.06.16 16:57"},{"pid":670,"msg":"收藏了您的论文:知·味","comment":null,"sort":4,"type":"2","status":null,"aid":255,"aSex":null,"realName":"互动消息","aAvatar":"http://o9kg05vzs.bkt.clouddn.com/2016-07-14_Kz5Ewj6W.png","msgTime":"2017.06.16 16:57"},{"pid":669,"msg":" 赞了您的论文：知·味","comment":null,"sort":3,"type":"2","status":null,"aid":43,"aSex":null,"realName":"互动消息","aAvatar":"http://o9kg05vzs.bkt.clouddn.com/2016-07-14_Kz5Ewj6W.png","msgTime":"2017.06.16 15:45"},{"pid":668,"msg":" 赞了您的论文：知·味","comment":null,"sort":3,"type":"2","status":null,"aid":43,"aSex":null,"realName":"互动消息","aAvatar":"http://o9kg05vzs.bkt.clouddn.com/2016-07-14_Kz5Ewj6W.png","msgTime":"2017.06.16 15:34"},{"pid":667,"msg":" 赞了您的论文：知·味","comment":null,"sort":3,"type":"2","status":null,"aid":43,"aSex":null,"realName":"互动消息","aAvatar":"http://o9kg05vzs.bkt.clouddn.com/2016-07-14_Kz5Ewj6W.png","msgTime":"2017.06.16 15:20"},{"pid":666,"msg":" 赞了您的论文：知·味","comment":null,"sort":3,"type":"2","status":null,"aid":43,"aSex":null,"realName":"互动消息","aAvatar":"http://o9kg05vzs.bkt.clouddn.com/2016-07-14_Kz5Ewj6W.png","msgTime":"2017.06.16 15:06"},{"pid":665,"msg":" 赞了您的论文：知·味","comment":null,"sort":3,"type":"2","status":null,"aid":43,"aSex":null,"realName":"互动消息","aAvatar":"http://o9kg05vzs.bkt.clouddn.com/2016-07-14_Kz5Ewj6W.png","msgTime":"2017.06.16 14:45"}],"total":25}
     */

    private PageInfoBean pageInfo;

    public PageInfoBean getPageInfo() {
        return pageInfo;
    }

    public void setPageInfo(PageInfoBean pageInfo) {
        this.pageInfo = pageInfo;
    }

    public static class PageInfoBean {
        /**
         * rows : [{"pid":674,"msg":" 赞了您的论文：知·味","comment":null,"sort":3,"type":"2","status":null,"aid":16,"aSex":null,"realName":"互动消息","aAvatar":"http://o9kg05vzs.bkt.clouddn.com/2016-07-14_Kz5Ewj6W.png","msgTime":"2017.06.16 17:39"},{"pid":673,"msg":" 关注了您","comment":null,"sort":4,"type":"2","status":null,"aid":255,"aSex":null,"realName":"互动消息","aAvatar":"http://o9kg05vzs.bkt.clouddn.com/2016-07-14_Kz5Ewj6W.png","msgTime":"2017.06.16 17:06"},{"pid":672,"msg":" 赞了您的论文：知·味","comment":null,"sort":3,"type":"2","status":null,"aid":255,"aSex":null,"realName":"互动消息","aAvatar":"http://o9kg05vzs.bkt.clouddn.com/2016-07-14_Kz5Ewj6W.png","msgTime":"2017.06.16 16:58"},{"pid":671,"msg":"收藏了您的论文《知·味》的PPT单页0","comment":null,"sort":5,"type":"2","status":null,"aid":255,"aSex":null,"realName":"互动消息","aAvatar":"http://o9kg05vzs.bkt.clouddn.com/2016-07-14_Kz5Ewj6W.png","msgTime":"2017.06.16 16:57"},{"pid":670,"msg":"收藏了您的论文:知·味","comment":null,"sort":4,"type":"2","status":null,"aid":255,"aSex":null,"realName":"互动消息","aAvatar":"http://o9kg05vzs.bkt.clouddn.com/2016-07-14_Kz5Ewj6W.png","msgTime":"2017.06.16 16:57"},{"pid":669,"msg":" 赞了您的论文：知·味","comment":null,"sort":3,"type":"2","status":null,"aid":43,"aSex":null,"realName":"互动消息","aAvatar":"http://o9kg05vzs.bkt.clouddn.com/2016-07-14_Kz5Ewj6W.png","msgTime":"2017.06.16 15:45"},{"pid":668,"msg":" 赞了您的论文：知·味","comment":null,"sort":3,"type":"2","status":null,"aid":43,"aSex":null,"realName":"互动消息","aAvatar":"http://o9kg05vzs.bkt.clouddn.com/2016-07-14_Kz5Ewj6W.png","msgTime":"2017.06.16 15:34"},{"pid":667,"msg":" 赞了您的论文：知·味","comment":null,"sort":3,"type":"2","status":null,"aid":43,"aSex":null,"realName":"互动消息","aAvatar":"http://o9kg05vzs.bkt.clouddn.com/2016-07-14_Kz5Ewj6W.png","msgTime":"2017.06.16 15:20"},{"pid":666,"msg":" 赞了您的论文：知·味","comment":null,"sort":3,"type":"2","status":null,"aid":43,"aSex":null,"realName":"互动消息","aAvatar":"http://o9kg05vzs.bkt.clouddn.com/2016-07-14_Kz5Ewj6W.png","msgTime":"2017.06.16 15:06"},{"pid":665,"msg":" 赞了您的论文：知·味","comment":null,"sort":3,"type":"2","status":null,"aid":43,"aSex":null,"realName":"互动消息","aAvatar":"http://o9kg05vzs.bkt.clouddn.com/2016-07-14_Kz5Ewj6W.png","msgTime":"2017.06.16 14:45"}]
         * total : 25
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
             * pid : 674
             * msg :  赞了您的论文：知·味
             * comment : null
             * sort : 3
             * type : 2
             * status : null
             * aid : 16
             * aSex : null
             * realName : 互动消息
             * aAvatar : http://o9kg05vzs.bkt.clouddn.com/2016-07-14_Kz5Ewj6W.png
             * msgTime : 2017.06.16 17:39
             */

            private String pid;
            private String msg;
            private String comment;
            private int sort;
            private String type;
            private int status;
            private String aid;
            private String aSex;
            private String realName;
            private String aAvatar;
            private String msgTime;

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            public String getMsg() {
                return msg;
            }

            public void setMsg(String msg) {
                this.msg = msg;
            }

            public String getComment() {
                return comment;
            }

            public void setComment(String comment) {
                this.comment = comment;
            }

            public int getSort() {
                return sort;
            }

            public void setSort(int sort) {
                this.sort = sort;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getAid() {
                return aid;
            }

            public void setAid(String aid) {
                this.aid = aid;
            }

            public String getASex() {
                return aSex;
            }

            public void setASex(String aSex) {
                this.aSex = aSex;
            }

            public String getRealName() {
                return realName;
            }

            public void setRealName(String realName) {
                this.realName = realName;
            }

            public String getAAvatar() {
                return aAvatar;
            }

            public void setAAvatar(String aAvatar) {
                this.aAvatar = aAvatar;
            }

            public String getMsgTime() {
                return msgTime;
            }

            public void setMsgTime(String msgTime) {
                this.msgTime = msgTime;
            }
        }
    }
}
