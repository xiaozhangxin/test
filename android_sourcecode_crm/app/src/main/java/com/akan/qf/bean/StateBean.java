package com.akan.qf.bean;

/**
 * Created by admin on 2019/4/1.
 */

public class StateBean {

    private String name;
    private String state;
    private boolean check;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

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

    public StateBean(String name, String state, boolean check) {
        this.name = name;
        this.state = state;
        this.check = check;
    }
}
