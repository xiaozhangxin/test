package com.akan.wms.bean;

import java.io.Serializable;

public class WarnTypeBean implements Serializable{


    /**
     * inventory_type_name : 成品入库
     * inventory_type : 3
     */

    private String inventory_type_name;
    private String inventory_type;

    public String getInventory_type_name() {
        return inventory_type_name;
    }

    public void setInventory_type_name(String inventory_type_name) {
        this.inventory_type_name = inventory_type_name;
    }

    public String getInventory_type() {
        return inventory_type;
    }

    public void setInventory_type(String inventory_type) {
        this.inventory_type = inventory_type;
    }
}
