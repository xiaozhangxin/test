package com.akan.qf.bean;

import java.io.Serializable;

public class CostTypeBean implements Serializable{

    /**
     * id : 1
     * name : 预估成本
     * is_delete : 0
     */

    private String id;
    private String name;
    private String is_delete;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(String is_delete) {
        this.is_delete = is_delete;
    }
}
