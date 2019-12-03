package com.akan.wms.bean;

public class WarnBean {


    /**
     * id : 6
     * inventory_type : CGRK
     * inventory_type_name : 采购入库
     * org_id : 1001512200010027
     * wh_id : 1001512260168339
     * wh_name : 内销成品仓-PPR管材
     * item_id : 1001812180399575
     * item_code :
     * item_name : 白色PPR冷水管S4[BM]
     * item_spec :
     * in_out_type : 0
     * qty : 123
     * create_time : 2019-07-31 16:29:57
     * is_delete :
     */

    private int id;
    private String inventory_type;
    private String inventory_type_name;
    private String org_id;
    private String wh_id;
    private String wh_name;
    private String item_id;
    private String item_code;
    private String item_name;
    private String item_spec;
    private int in_out_type;
    private int qty;
    private String create_time;
    private String is_delete;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInventory_type() {
        return inventory_type;
    }

    public void setInventory_type(String inventory_type) {
        this.inventory_type = inventory_type;
    }

    public String getInventory_type_name() {
        return inventory_type_name;
    }

    public void setInventory_type_name(String inventory_type_name) {
        this.inventory_type_name = inventory_type_name;
    }


    public String getWh_name() {
        return wh_name;
    }

    public void setWh_name(String wh_name) {
        this.wh_name = wh_name;
    }

    public String getOrg_id() {
        return org_id;
    }

    public void setOrg_id(String org_id) {
        this.org_id = org_id;
    }

    public String getWh_id() {
        return wh_id;
    }

    public void setWh_id(String wh_id) {
        this.wh_id = wh_id;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getItem_code() {
        return item_code;
    }

    public void setItem_code(String item_code) {
        this.item_code = item_code;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_spec() {
        return item_spec;
    }

    public void setItem_spec(String item_spec) {
        this.item_spec = item_spec;
    }

    public int getIn_out_type() {
        return in_out_type;
    }

    public void setIn_out_type(int in_out_type) {
        this.in_out_type = in_out_type;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(String is_delete) {
        this.is_delete = is_delete;
    }
}
