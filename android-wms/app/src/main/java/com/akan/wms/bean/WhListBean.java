package com.akan.wms.bean;

import java.io.Serializable;

public class WhListBean implements Serializable{


    /**
     * warehouse_id : 1001512260168343
     * warehouse_name : 材料仓
     * warehouse_code : 11
     * org_id : {}
     * is_effective : 0
     */

    private String warehouse_id;
    private String warehouse_name;
    private String warehouse_code;
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



    public String getIs_effective() {
        return is_effective;
    }

    public void setIs_effective(String is_effective) {
        this.is_effective = is_effective;
    }


}
