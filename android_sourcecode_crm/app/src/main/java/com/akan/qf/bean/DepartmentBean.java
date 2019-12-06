package com.akan.qf.bean;

import java.io.Serializable;

/**
 * Created by admin on 2019/4/1.
 */

public class DepartmentBean implements Serializable{
private String name;
    private String uuid;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public DepartmentBean(String name, String uuid) {
        this.name = name;
        this.uuid = uuid;
    }
}
