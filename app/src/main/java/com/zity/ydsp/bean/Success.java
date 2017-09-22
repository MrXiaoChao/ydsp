package com.zity.ydsp.bean;

/**
 * Created by luochao on 2017/9/18.
 * 所有成功实体类
 */

public class Success {

    /**
     * flag : false
     * msg : 用户名已存在
     */

    private String flag;
    private String msg;

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
