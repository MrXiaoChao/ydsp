package com.zity.ydsp.bean;

/**
 * Created by luochao on 2017/9/21.
 * 提交建议成功
 */

public class SuggestSuccess {

    /**
     * save_flag : true
     * msg : 保存成功
     */

    private String save_flag;
    private String msg;

    public String getSave_flag() {
        return save_flag;
    }

    public void setSave_flag(String save_flag) {
        this.save_flag = save_flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
