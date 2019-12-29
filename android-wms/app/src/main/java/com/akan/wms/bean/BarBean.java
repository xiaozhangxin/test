package com.akan.wms.bean;

import java.io.Serializable;

public class BarBean implements Serializable{


    /**
     * bar_code : 00.01.011.0002
     * org_id : 1001512200010027
     * item_id : 1001812180044580
     * item_code : 00.01.011.0002
     * item_name : PPR主料—绿色混配料
     * item_spec :
     * item_sku : 公斤
     * item_type : 1
     * qty : 10
     * source : 0
     * create_time : 2019-07-12 16:36:55
     */

    private String bar_code;
    private String org_id;

    public int getOld_num() {
        return old_num;
    }

    public void setOld_num(int old_num) {
        this.old_num = old_num;
    }

    private String item_id;
    private String item_code;
    private String item_name;
    private String item_spec;
    private String item_sku;
    private String item_type;
    private int qty;
    private String source;
    private String create_time;
    private int old_num;
    private int max_num;

    public int getMax_num() {
        return max_num;
    }

    public void setMax_num(int max_num) {
        this.max_num = max_num;
    }

    public String getBar_code() {
        return bar_code;
    }

    public void setBar_code(String bar_code) {
        this.bar_code = bar_code;
    }

    public String getOrg_id() {
        return org_id;
    }

    public void setOrg_id(String org_id) {
        this.org_id = org_id;
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

    public String getItem_sku() {
        return item_sku;
    }

    public void setItem_sku(String item_sku) {
        this.item_sku = item_sku;
    }

    public String getItem_type() {
        return item_type;
    }

    public void setItem_type(String item_type) {
        this.item_type = item_type;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}
