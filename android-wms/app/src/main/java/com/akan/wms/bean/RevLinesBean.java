package com.akan.wms.bean;

import java.io.Serializable;

public class RevLinesBean implements Serializable{

    /**
     * id : 1001906048501785
     * sale_rcv_id : 1001906048501743
     * line_no : 210
     * item_id : 1001812181664248
     * wh_id : 1001512260168575
     * wh_man_id : 1001712281051538
     * dep_id : 1001712280796505
     * arrive_qty : 640
     * item_code : 11.53.342.4010001
     * item_name : 蓝色八角线盒
     * item_spec :
     * group_name : 仓储部
     * wh_name : 内销成品仓-PVC管件
     * wh_man_name : 周先君
     */

    private long id;
    private long sale_rcv_id;
    private int line_no;
    private long item_id;
    private long wh_id;
    private long wh_man_id;
    private long dep_id;
    private int arrive_qty;
    private String item_code;
    private String item_name;
    private String item_spec;
    private String group_name;
    private String wh_name;
    private String wh_man_name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSale_rcv_id() {
        return sale_rcv_id;
    }

    public void setSale_rcv_id(long sale_rcv_id) {
        this.sale_rcv_id = sale_rcv_id;
    }

    public int getLine_no() {
        return line_no;
    }

    public void setLine_no(int line_no) {
        this.line_no = line_no;
    }

    public long getItem_id() {
        return item_id;
    }

    public void setItem_id(long item_id) {
        this.item_id = item_id;
    }

    public long getWh_id() {
        return wh_id;
    }

    public void setWh_id(long wh_id) {
        this.wh_id = wh_id;
    }

    public long getWh_man_id() {
        return wh_man_id;
    }

    public void setWh_man_id(long wh_man_id) {
        this.wh_man_id = wh_man_id;
    }

    public long getDep_id() {
        return dep_id;
    }

    public void setDep_id(long dep_id) {
        this.dep_id = dep_id;
    }

    public int getArrive_qty() {
        return arrive_qty;
    }

    public void setArrive_qty(int arrive_qty) {
        this.arrive_qty = arrive_qty;
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

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getWh_name() {
        return wh_name;
    }

    public void setWh_name(String wh_name) {
        this.wh_name = wh_name;
    }

    public String getWh_man_name() {
        return wh_man_name;
    }

    public void setWh_man_name(String wh_man_name) {
        this.wh_man_name = wh_man_name;
    }
}
