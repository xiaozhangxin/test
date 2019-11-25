package com.ak.pt.bean;

import java.io.Serializable;

public class PressureInfoListBean implements Serializable{
    /**
     * info_id : 342
     * apply_id : 101
     * info_name : 123
     * info_tel : 123
     * type : pressure
     * create_time : 2019-10-16 17:14:47
     */

    private String info_id;
    private String apply_id;
    private String info_name;
    private String info_tel;
    private String type;
    private String create_time;

    public String getInfo_id() {
        return info_id;
    }

    public void setInfo_id(String info_id) {
        this.info_id = info_id;
    }

    public String getApply_id() {
        return apply_id;
    }

    public void setApply_id(String apply_id) {
        this.apply_id = apply_id;
    }

    public String getInfo_name() {
        return info_name;
    }

    public void setInfo_name(String info_name) {
        this.info_name = info_name;
    }

    public String getInfo_tel() {
        return info_tel;
    }

    public void setInfo_tel(String info_tel) {
        this.info_tel = info_tel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}
