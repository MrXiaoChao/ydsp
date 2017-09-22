package com.zity.ydsp.bean;

import java.util.List;

/**
 * Created by luochao on 2017/9/18.
 * 首页图标
 */

public class HomePageImageUrl {


    private List<ListBean> list;

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * name : 户籍身份
         * i_d : 2
         * url : http://121.42.186.190:8081/activiti3/upload/户籍身份@2x20170915133754.png
         */

        private String name;
        private String i_d;
        private String url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getI_d() {
            return i_d;
        }

        public void setI_d(String i_d) {
            this.i_d = i_d;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
