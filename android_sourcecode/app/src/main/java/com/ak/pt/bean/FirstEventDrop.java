package com.ak.pt.bean;

/**
 * Created by admin on 2019/4/17.
 */

public class FirstEventDrop {
    public FirstEventDrop(String type, PressureDropBean pressureDropBean) {
        this.type = type;
        this.pressureDropBean = pressureDropBean;
    }

    private String type;

    private PressureDropBean pressureDropBean;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public PressureDropBean getPressureDropBean() {
        return pressureDropBean;
    }

    public void setPressureDropBean(PressureDropBean pressureDropBean) {
        this.pressureDropBean = pressureDropBean;
    }
}
