package com.akan.wms.bean;

import java.io.Serializable;

public class GoodsBean implements Serializable {
    private String name;
    private String detail;
    private String num;

    public GoodsBean(String name, String detail, String num) {
        this.name = name;
        this.detail = detail;
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
