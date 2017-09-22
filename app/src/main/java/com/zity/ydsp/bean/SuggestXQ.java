package com.zity.ydsp.bean;

/**
 * Created by luochao on 2017/9/21.
 * 建议详情
 */

public class SuggestXQ {

    /**
     * content : 建议意见-10,太慢了
     * title : 建议标题-10
     * phone : 13709230323
     * name : 张三
     * createdate : 2017-09-20
     * replydate : 2017-09-20
     * replycontent : 已收到,我们会尽快联系相关部门,加快处理.
     */

    private String content;
    private String title;
    private String phone;
    private String name;
    private String createdate;
    private String replydate;
    private String replycontent;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getReplydate() {
        return replydate;
    }

    public void setReplydate(String replydate) {
        this.replydate = replydate;
    }

    public String getReplycontent() {
        return replycontent;
    }

    public void setReplycontent(String replycontent) {
        this.replycontent = replycontent;
    }
}
