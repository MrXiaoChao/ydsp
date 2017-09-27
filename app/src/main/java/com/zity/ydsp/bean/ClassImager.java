package com.zity.ydsp.bean;

import java.util.List;

/**
 * Created by luochao on 2017/9/27.
 * 获取所有部门
 */

public class ClassImager {

    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * orgId : ff8080815ac8512a015af08a1d7a0035
         * name : 民政局
         */

        private String orgId;
        private String name;

        public String getOrgId() {
            return orgId;
        }

        public void setOrgId(String orgId) {
            this.orgId = orgId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
