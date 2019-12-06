package com.akan.qf.bean;

/**
 * Created by admin on 2019/4/1.
 */

public class PostBean {
    public PostBean(String name, boolean check) {
        this.name = name;
        this.check = check;
    }

    private String name;
    private boolean check;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }
}
