package com.akan.wms.bean;

import java.io.Serializable;

public class WareHouseBean implements Serializable{


    /**
     * warehouse_id : 1001512260168336
     * warehouse_name : 上海-湖南成品虚拟仓
     * warehouse_code : 1802
     * org_id : 1001512200010027
     * is_effective : 1
     */

    private String warehouse_id;
    private String warehouse_name;
    private String warehouse_code;
    private String org_id;
    private String is_effective;

    public String getWarehouse_id() {
        return warehouse_id;
    }

    public void setWarehouse_id(String warehouse_id) {
        this.warehouse_id = warehouse_id;
    }

    public String getWarehouse_name() {
        return warehouse_name;
    }

    public void setWarehouse_name(String warehouse_name) {
        this.warehouse_name = warehouse_name;
    }

    public String getWarehouse_code() {
        return warehouse_code;
    }

    public void setWarehouse_code(String warehouse_code) {
        this.warehouse_code = warehouse_code;
    }

    public String getOrg_id() {
        return org_id;
    }

    public void setOrg_id(String org_id) {
        this.org_id = org_id;
    }

    public String getIs_effective() {
        return is_effective;
    }

    public void setIs_effective(String is_effective) {
        this.is_effective = is_effective;
    }
}
