package com.akan.wms.bean;

import java.io.Serializable;

public class PlanLineBean implements Serializable{


    /**
     * id : 1001901020011594
     * plan_id : 1001901020011592
     * item_id : 1001812260028862
     * plan_time : 2018-12-31 16:15:34
     * plan_qty : 9
     * send_qty : 6
     * customer_id : 1001812210099019
     * customer_name : 天津市凯津制冷设备安装工程有限公司（零售
     * status : 4
     * item_name : 过滤器球阀温压一体表
     * item_code : 21.24.706.16007
     * item_spec : M10×1
     * status_show : 自然关闭
     * default_wh_id : 1001701130074501
     * default_wh_name : 暖通新风产品仓
     */

    private String id;
    private String plan_id;
    private String item_id;
    private String plan_time;
    private int plan_qty;
    private int dis_qty;
    private String customer_id;
    private String customer_name;
    private String status;
    private String item_name;
    private String item_code;
    private int add_qty;
    private String  bar_code;//条码

    public int getAdd_qty() {
        return add_qty;
    }

    public void setAdd_qty(int add_qty) {
        this.add_qty = add_qty;
    }

    public String getBar_code() {
        return bar_code;
    }

    public void setBar_code(String bar_code) {
        this.bar_code = bar_code;
    }

    public int getDis_qty() {
        return dis_qty;
    }

    public void setDis_qty(int dis_qty) {
        this.dis_qty = dis_qty;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    private String item_spec;
    private String status_show;
    private String default_wh_id;
    private String default_wh_name;
    private  boolean isCheck;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(String plan_id) {
        this.plan_id = plan_id;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getPlan_time() {
        return plan_time;
    }

    public void setPlan_time(String plan_time) {
        this.plan_time = plan_time;
    }

    public double getPlan_qty() {
        return plan_qty;
    }

    public void setPlan_qty(int plan_qty) {
        this.plan_qty = plan_qty;
    }



    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_code() {
        return item_code;
    }

    public void setItem_code(String item_code) {
        this.item_code = item_code;
    }

    public String getItem_spec() {
        return item_spec;
    }

    public void setItem_spec(String item_spec) {
        this.item_spec = item_spec;
    }

    public String getStatus_show() {
        return status_show;
    }

    public void setStatus_show(String status_show) {
        this.status_show = status_show;
    }

    public String getDefault_wh_id() {
        return default_wh_id;
    }

    public void setDefault_wh_id(String default_wh_id) {
        this.default_wh_id = default_wh_id;
    }

    public String getDefault_wh_name() {
        return default_wh_name;
    }

    public void setDefault_wh_name(String default_wh_name) {
        this.default_wh_name = default_wh_name;
    }
}
