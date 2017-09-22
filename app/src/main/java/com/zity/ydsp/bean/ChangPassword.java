package com.zity.ydsp.bean;

/**
 * Created by luochao on 2017/9/21.
 * 修改密码
 */

public class ChangPassword {
    public String getUpdate_flag() {
        return update_flag;
    }

    public void setUpdate_flag(String update_flag) {
        this.update_flag = update_flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private String update_flag;
    private String msg;
}
