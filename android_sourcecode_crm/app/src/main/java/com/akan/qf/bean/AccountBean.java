package com.akan.qf.bean;

/**
 * Created by admin on 2019/4/3.
 */

public class AccountBean {
    private String name;
    private String account;
    private String pwd;
    private String avatar;
    private boolean isNow;

    public boolean isNow() {
        return isNow;
    }

    public void setNow(boolean now) {
        isNow = now;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
