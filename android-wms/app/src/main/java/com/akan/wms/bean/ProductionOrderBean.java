package com.akan.wms.bean;

import java.io.Serializable;
import java.util.List;

public class ProductionOrderBean implements Serializable {


    /**
     * id : 1001812260140003
     * org_id : 1001512200010027
     * complete_dec_line_id :
     * doc_no : 101-AKWO-201812-00056
     * doc_type_id : 1001512205750002
     * doc_type_name : 普通订单
     * business_type : 0
     * status : 2
     * item_id : 1001812181054564
     * plan_start_time : 2018-12-26 08:00:00
     * plan_end_time : 2018-12-26 16:18:49
     * group_id : 6645
     * u9_department_id :
     * pro_qty : 1000
     * complete_qty : 369
     * wh_qty : 367
     * is_valid : 0
     * modify_time : 2019-07-08 16:56:47
     * group_name : 包装组装班组
     * item_code : 11.15.248.3025
     * item_name : 绿色双活接PPR铜球阀
     * item_sku : 只
     * item_spec : Φ25
     * status_name : 开工
     */

    private String id;
    private String org_id;
    private String complete_dec_line_id;
    private String doc_no;
    private String doc_type_id;
    private String doc_type_name;
    private String business_type;
    private String status;
    private String item_id;
    private String plan_start_time;
    private String plan_end_time;
    private String group_id;
    private String u9_department_id;
    private double complete_qty;
    private int apply_qty;//完工数量
    private List<BarBean> barList;

    public List<BarBean> getBarList() {
        return barList;
    }

    public void setBarList(List<BarBean> barList) {
        this.barList = barList;
    }

    public int getApply_qty() {
        return apply_qty;
    }

    public void setApply_qty(int apply_qty) {
        this.apply_qty = apply_qty;
    }

    private int qualified_qty;
    private int scrap_qty;
    private String scrap_weight;
    private String eve_weight;


    private int is_valid;
    private int send_qty;
    private String modify_time;
    private String group_name;
    private String item_code;
    private String item_name;
    private String item_sku;
    private String item_spec;
    private String status_name;
    private String operator_id;
    private String operator_Name;
    private String mod_code;//模具编码
    private String mod_name;//模具名称
    private String class_team_name;
    private String class_team_id;
    private String wh_id;
    private String wh_name;
    private String mac_code;

    private String speed;
    private String labor_hours;
    private String mac_pre_hours;
    private String mac_hours;
    private String mod_weight;
    private String ele_weight;
    private String mod_outline_weight;
    private String mod_inserts_weight;
    private String bush_weight;
    private String mod_amount;
    private String ele_amount;
    private String bar_code;
    private String u9_department_name;

    public String getU9_department_name() {
        return u9_department_name;
    }

    public void setU9_department_name(String u9_department_name) {
        this.u9_department_name = u9_department_name;
    }

    public String getBar_code() {
        return bar_code;
    }

    public void setBar_code(String bar_code) {
        this.bar_code = bar_code;
    }

    public int getSend_qty() {
        return send_qty;
    }

    public void setSend_qty(int send_qty) {
        this.send_qty = send_qty;
    }


    public String getBush_weight() {
        return bush_weight;
    }

    public void setBush_weight(String bush_weight) {
        this.bush_weight = bush_weight;
    }

    public String getMod_amount() {
        return mod_amount;
    }

    public void setMod_amount(String mod_amount) {
        this.mod_amount = mod_amount;
    }

    public String getEle_amount() {
        return ele_amount;
    }

    public void setEle_amount(String ele_amount) {
        this.ele_amount = ele_amount;
    }

    public String getMod_weight() {
        return mod_weight;
    }

    public void setMod_weight(String mod_weight) {
        this.mod_weight = mod_weight;
    }

    public String getMod_outline_weight() {
        return mod_outline_weight;
    }

    public void setMod_outline_weight(String mod_outline_weight) {
        this.mod_outline_weight = mod_outline_weight;
    }

    public String getMod_inserts_weight() {
        return mod_inserts_weight;
    }

    public void setMod_inserts_weight(String mod_inserts_weight) {
        this.mod_inserts_weight = mod_inserts_weight;
    }

    public String getLabor_hours() {
        return labor_hours;
    }

    public void setLabor_hours(String labor_hours) {
        this.labor_hours = labor_hours;
    }

    public String getMac_pre_hours() {
        return mac_pre_hours;
    }

    public void setMac_pre_hours(String mac_pre_hours) {
        this.mac_pre_hours = mac_pre_hours;
    }

    public String getMac_hours() {
        return mac_hours;
    }

    public void setMac_hours(String mac_hours) {
        this.mac_hours = mac_hours;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getEve_weight() {
        return eve_weight;
    }

    public void setEve_weight(String eve_weight) {
        this.eve_weight = eve_weight;
    }

    public int getQualified_qty() {
        return qualified_qty;
    }

    public void setQualified_qty(int qualified_qty) {
        this.qualified_qty = qualified_qty;
    }

    public int getScrap_qty() {
        return scrap_qty;
    }

    public void setScrap_qty(int scrap_qty) {
        this.scrap_qty = scrap_qty;
    }

    public String getScrap_weight() {
        return scrap_weight;
    }

    public void setScrap_weight(String scrap_weight) {
        this.scrap_weight = scrap_weight;
    }
    public String getEle_weight() {
        return ele_weight;
    }

    public void setEle_weight(String ele_weight) {
        this.ele_weight = ele_weight;
    }

    public String getMac_code() {
        return mac_code;
    }

    public void setMac_code(String mac_code) {
        this.mac_code = mac_code;
    }

    public String getMod_name() {
        return mod_name;
    }

    public void setMod_name(String mod_name) {
        this.mod_name = mod_name;
    }

    public String getOperator_id() {
        return operator_id;
    }

    public void setOperator_id(String operator_id) {
        this.operator_id = operator_id;
    }

    public String getOperator_Name() {
        return operator_Name;
    }

    public void setOperator_Name(String operator_Name) {
        this.operator_Name = operator_Name;
    }

    public String getMod_code() {
        return mod_code;
    }

    public void setMod_code(String mod_code) {
        this.mod_code = mod_code;
    }

    public String getClass_team_name() {
        return class_team_name;
    }

    public void setClass_team_name(String class_team_name) {
        this.class_team_name = class_team_name;
    }

    public String getClass_team_id() {
        return class_team_id;
    }

    public void setClass_team_id(String class_team_id) {
        this.class_team_id = class_team_id;
    }

    public String getWh_id() {
        return wh_id;
    }

    public void setWh_id(String wh_id) {
        this.wh_id = wh_id;
    }

    public String getWh_name() {
        return wh_name;
    }

    public void setWh_name(String wh_name) {
        this.wh_name = wh_name;
    }



    public String getComplete_dec_line_id() {
        return complete_dec_line_id;
    }

    public void setComplete_dec_line_id(String complete_dec_line_id) {
        this.complete_dec_line_id = complete_dec_line_id;
    }

    public String getDoc_no() {
        return doc_no;
    }

    public void setDoc_no(String doc_no) {
        this.doc_no = doc_no;
    }


    public String getDoc_type_name() {
        return doc_type_name;
    }

    public void setDoc_type_name(String doc_type_name) {
        this.doc_type_name = doc_type_name;
    }



    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getPlan_start_time() {
        return plan_start_time;
    }

    public void setPlan_start_time(String plan_start_time) {
        this.plan_start_time = plan_start_time;
    }

    public String getPlan_end_time() {
        return plan_end_time;
    }

    public void setPlan_end_time(String plan_end_time) {
        this.plan_end_time = plan_end_time;
    }


    public String getU9_department_id() {
        return u9_department_id;
    }

    public void setU9_department_id(String u9_department_id) {
        this.u9_department_id = u9_department_id;
    }

    public String getId() {
        return id;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public void setComplete_qty(double complete_qty) {
        this.complete_qty = complete_qty;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrg_id() {
        return org_id;
    }

    public void setOrg_id(String org_id) {
        this.org_id = org_id;
    }

    public String getDoc_type_id() {
        return doc_type_id;
    }

    public void setDoc_type_id(String doc_type_id) {
        this.doc_type_id = doc_type_id;
    }

    public String getBusiness_type() {
        return business_type;
    }

    public void setBusiness_type(String business_type) {
        this.business_type = business_type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getComplete_qty() {
        return complete_qty;
    }

    public void setComplete_qty(int complete_qty) {
        this.complete_qty = complete_qty;
    }



    public int getIs_valid() {
        return is_valid;
    }

    public void setIs_valid(int is_valid) {
        this.is_valid = is_valid;
    }

    public String getModify_time() {
        return modify_time;
    }

    public void setModify_time(String modify_time) {
        this.modify_time = modify_time;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
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

    public String getItem_sku() {
        return item_sku;
    }

    public void setItem_sku(String item_sku) {
        this.item_sku = item_sku;
    }

    public String getItem_spec() {
        return item_spec;
    }

    public void setItem_spec(String item_spec) {
        this.item_spec = item_spec;
    }

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }
}
