package com.zity.ydsp.bean;

/**
 * Created by luochao on 2017/9/18.
 * 用户
 */

public class User {

    /**
     * login_flag : 1
     * phone : 13709230323
     * username : user123
     * person_flag : 0个人  1 法人
     * name : 张三
     * personuuid : a9aeddb6-781a-4a9b-865c-33196c89200b
     * account_type : 2
     */

    private String login_flag;
    private String phone;
    private String username;
    private String person_flag;
    private String name;
    private String personuuid;
    private int account_type;

    public String getLogin_flag() {
        return login_flag;
    }

    public void setLogin_flag(String login_flag) {
        this.login_flag = login_flag;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPerson_flag() {
        return person_flag;
    }

    public void setPerson_flag(String person_flag) {
        this.person_flag = person_flag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPersonuuid() {
        return personuuid;
    }

    public void setPersonuuid(String personuuid) {
        this.personuuid = personuuid;
    }

    public int getAccount_type() {
        return account_type;
    }

    public void setAccount_type(int account_type) {
        this.account_type = account_type;
    }
}
