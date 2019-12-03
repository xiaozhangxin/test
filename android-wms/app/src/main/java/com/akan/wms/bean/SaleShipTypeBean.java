package com.akan.wms.bean;

import java.io.Serializable;

public class SaleShipTypeBean implements Serializable{


    /**
     * id : 1001512205190401
     * code : SM1
     * name : 正常销售
     * org_id : 1001512200010027
     * update_time : 2019-04-28 08:29:08
     */

    private String id;
    private String code;
    private String name;
    private String org_id;
    private String update_time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getOrg_id() {
        return org_id;
    }

    public void setOrg_id(String org_id) {
        this.org_id = org_id;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }
}
