package com.ak.pt.bean;

import java.io.Serializable;

/**
 * Created by admin on 2019/4/16.
 */

public class DepartmentBean  implements Serializable {
    private String name;
    private String uuid;
    private String id;

    public DepartmentBean(String name, String uuid) {
        this.name = name;
        this.uuid = uuid;
    }
     public DepartmentBean(String name, String id,String uuid) {
        this.name = name;
        this.id = id;
        this.uuid = uuid;
    }


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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }


}
