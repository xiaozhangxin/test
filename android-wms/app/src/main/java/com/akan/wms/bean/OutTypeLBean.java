package com.akan.wms.bean;

import java.io.Serializable;

public class OutTypeLBean implements Serializable{


    /**
     * id : 1001512205410401
     * org_id : 1001512200010027
     * code : TransOut001
     * name : 组织间调拨
     */

    private long id;
    private long org_id;
    private String code;
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrg_id() {
        return org_id;
    }

    public void setOrg_id(long org_id) {
        this.org_id = org_id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
