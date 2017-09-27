package com.zity.ydsp.bean;

import java.util.List;

/**
 * Created by luochao on 2017/9/26.
 * 个人办事
 */

public class Grbs {

    /**
     * count : 2
     * list : [{"orgname":"园林局","name":"ces213","sxid":"f9aa3a3e5ea84b80015eb6b81aac00db","type":"2","online":"2"},{"orgname":"工商局","name":"农村五保户供养申请","sxid":"402880085ea77191015ea829abfb0024","type":"1","online":"1"}]
     */

    private int count;
    private List<ListBean> list;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * orgname : 园林局
         * name : ces213
         * sxid : f9aa3a3e5ea84b80015eb6b81aac00db
         * type : 2
         * online : 2
         */

        private String orgname;
        private String name;
        private String sxid;
        private String type;
        private String online;

        public String getOrgname() {
            return orgname;
        }

        public void setOrgname(String orgname) {
            this.orgname = orgname;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSxid() {
            return sxid;
        }

        public void setSxid(String sxid) {
            this.sxid = sxid;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getOnline() {
            return online;
        }

        public void setOnline(String online) {
            this.online = online;
        }
    }
}
