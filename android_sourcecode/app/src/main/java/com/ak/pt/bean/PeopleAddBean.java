package com.ak.pt.bean;

import java.io.Serializable;

/**
 * Created by admin on 2019/5/29.
 */

public class PeopleAddBean implements Serializable{
    public PeopleAddBean(String type) {
        this.type = type;
    }

    /**
     * info_name : 试压工
     * info_tel : 电话
     * type : pressure
     */
    private String info_id;
    private String add_id;
    private String create_time;
    private String info_name;
    private String info_tel;
    private String type;

    public String getInfo_id() {
        return info_id;
    }

    public void setInfo_id(String info_id) {
        this.info_id = info_id;
    }

    public String getAdd_id() {
        return add_id;
    }

    public void setAdd_id(String add_id) {
        this.add_id = add_id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
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
}
