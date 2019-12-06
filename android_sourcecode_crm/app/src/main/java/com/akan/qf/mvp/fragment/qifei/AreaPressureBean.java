package com.akan.qf.mvp.fragment.qifei;

import java.io.Serializable;

/**
 * Created by admin on 2018/11/23.
 */

public class AreaPressureBean implements Serializable{

    protected String fullArea; // 区域全名
    protected String count; // 试压总量

    public String getFullArea() {
        return fullArea;
    }

    public void setFullArea(String fullArea) {
        this.fullArea = fullArea;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
