package com.akan.wms.bean;

import java.io.Serializable;

public class WhBean implements Serializable{


    /**
     * warehouse_name : 内销成品仓-PPR管件
     * wh_id : 1001512260168424
     */

    private String warehouse_name;
    private String wh_id;

    public String getWarehouse_name() {
        return warehouse_name;
    }

    public void setWarehouse_name(String warehouse_name) {
        this.warehouse_name = warehouse_name;
    }

    public String getWh_id() {
        return wh_id;
    }

    public void setWh_id(String wh_id) {
        this.wh_id = wh_id;
    }
}
