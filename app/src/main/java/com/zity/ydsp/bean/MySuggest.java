package com.zity.ydsp.bean;

import java.util.List;

/**
 * Created by luochao on 2017/9/20.
 * 我的建议
 */

public class MySuggest {

    /**
     * count : 12
     * list : [{"title":"建议标题-10","isreplay":"当前状态: 已答复","jianyi_id":"e12bc739-42e5-412d-b742-dffb124c4481","createdate":"2017-09-20"},{"title":"建议标题009","isreplay":"当前状态: 已答复","jianyi_id":"5259491e-3c08-42d3-b7b2-87cb1332fd06","createdate":"2017-09-20"},{"title":"建议标题008","isreplay":"当前状态: 未答复","jianyi_id":"e6c909c4-1385-40f8-b82f-11de2d05e7e5","createdate":"2017-09-20"},{"title":"建议标题007","isreplay":"当前状态: 未答复","jianyi_id":"af4ff96d-cd5f-4b97-ae6b-a3b10565d1f1","createdate":"2017-09-20"},{"title":"建议标题006","isreplay":"当前状态: 未答复","jianyi_id":"a1da99b1-433f-4168-9051-159cc969ab35","createdate":"2017-09-20"},{"title":"建议标题005","isreplay":"当前状态: 未答复","jianyi_id":"9c2a353b-01a0-4530-824d-27b4a8cb1de2","createdate":"2017-09-20"},{"title":"建议标题004","isreplay":"当前状态: 未答复","jianyi_id":"9b5d5f3b-36f4-4a09-a8fb-57f8cca2d458","createdate":"2017-09-20"},{"title":"建议标题003","isreplay":"当前状态: 未答复","jianyi_id":"4c96ed11-c862-4f7b-89b1-a27be2e2e84f","createdate":"2017-09-20"},{"title":"建议标题002","isreplay":"当前状态: 未答复","jianyi_id":"818e219f-87e3-451a-9081-cb8bfaa25612","createdate":"2017-09-20"},{"title":"建议标题001","isreplay":"当前状态: 未答复","jianyi_id":"a34aca88-c6bf-4ef6-9cda-bc4fa3bed513","createdate":"2017-09-20"},{"title":"办事指南缺失","isreplay":"当前状态: 已答复","jianyi_id":"097fee16-12a3-4f4f-80be-935d22b7cef2","createdate":"2017-09-10"},{"title":"建议标题001","isreplay":"当前状态: 未答复","jianyi_id":"1957c1be-f1e8-4f9e-b66b-ee7ed3c1a563","createdate":"2017-09-05"}]
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
         * title : 建议标题-10
         * isreplay : 当前状态: 已答复
         * jianyi_id : e12bc739-42e5-412d-b742-dffb124c4481
         * createdate : 2017-09-20
         */

        private String title;
        private String isreplay;
        private String jianyi_id;
        private String createdate;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getIsreplay() {
            return isreplay;
        }

        public void setIsreplay(String isreplay) {
            this.isreplay = isreplay;
        }

        public String getJianyi_id() {
            return jianyi_id;
        }

        public void setJianyi_id(String jianyi_id) {
            this.jianyi_id = jianyi_id;
        }

        public String getCreatedate() {
            return createdate;
        }

        public void setCreatedate(String createdate) {
            this.createdate = createdate;
        }
    }
}
