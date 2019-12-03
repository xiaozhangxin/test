package com.akan.wms.bean;

import java.io.Serializable;

public class LineListBean implements Serializable {


    /**
     * sale_return_line_id : 退回出单行
     * wh_id : 存储位置
     * item_id : 料品
     * wh_man_id : 仓管员
     * group_id : 收货部门
     * arrive_qty : 数量
     */

    private String sale_return_line_id;
    private String wh_id;
    private String item_id;
    private String wh_man_id;
    private String group_id;
    private String arrive_qty;

    public String getSale_return_line_id() {
        return sale_return_line_id;
    }

    public void setSale_return_line_id(String sale_return_line_id) {
        this.sale_return_line_id = sale_return_line_id;
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

    public String getWh_man_id() {
        return wh_man_id;
    }

    public void setWh_man_id(String wh_man_id) {
        this.wh_man_id = wh_man_id;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getArrive_qty() {
        return arrive_qty;
    }

    public void setArrive_qty(String arrive_qty) {
        this.arrive_qty = arrive_qty;
    }
}
