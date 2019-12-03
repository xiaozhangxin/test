package com.akan.wms.bean;

import java.io.Serializable;

public class CompleteDecLinesBean implements Serializable {
    /**
     * complete_qty : 10
     * pro_id : 1001812260140003
     * mac_code : mac_code
     * qualified_qty : 10
     * item_id : 1001812181054564
     * operator_id : 132
     * class_team_id : 1001601100177743
     * mod_code : ZJ-7507-PR-L45°20-002
     * wh_id : 1001812260140003
     * eve_weight : 12
     * speed : 14.4
     * mac_pre_hours : 14
     * labor_hours : 14
     * mac_hours : 14.4
     * scrap_weight : 14.4
     * mod_weight : 14.4
     */

    private String complete_qty;
    private String id;
    private String pro_id;
    private String mac_code;
    private String qualified_qty;
    private String item_id;
    private String operator_id;
    private String class_team_id;
    private String class_team_name;
    private String mod_code;
    private String wh_id;
    private String eve_weight;
    private String speed;
    private String mac_pre_hours;
    private String labor_hours;
    private String mac_hours;
    private String ele_weight ;//每只重量
    private String scrap_qty ;//报废数量
    private String mod_outline_weight ;
    private String mod_inserts_weight ;
    private String bush_weight ;
    private String mod_amount ;
    private String ele_amount ;
    private String scrap_weight;
    private String mod_weight;

    public String getClass_team_name() {
        return class_team_name;
    }

    public void setClass_team_name(String class_team_name) {
        this.class_team_name = class_team_name;
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

    public String getScrap_qty() {
        return scrap_qty;
    }

    public void setScrap_qty(String scrap_qty) {
        this.scrap_qty = scrap_qty;
    }

    public String getEle_weight() {
        return ele_weight;
    }

    public void setEle_weight(String ele_weight) {
        this.ele_weight = ele_weight;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getComplete_qty() {
        return complete_qty;
    }

    public void setComplete_qty(String complete_qty) {
        this.complete_qty = complete_qty;
    }

    public String getPro_id() {
        return pro_id;
    }

    public void setPro_id(String pro_id) {
        this.pro_id = pro_id;
    }

    public String getMac_code() {
        return mac_code;
    }

    public void setMac_code(String mac_code) {
        this.mac_code = mac_code;
    }

    public String getQualified_qty() {
        return qualified_qty;
    }

    public void setQualified_qty(String qualified_qty) {
        this.qualified_qty = qualified_qty;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getOperator_id() {
        return operator_id;
    }

    public void setOperator_id(String operator_id) {
        this.operator_id = operator_id;
    }

    public String getClass_team_id() {
        return class_team_id;
    }

    public void setClass_team_id(String class_team_id) {
        this.class_team_id = class_team_id;
    }

    public String getMod_code() {
        return mod_code;
    }

    public void setMod_code(String mod_code) {
        this.mod_code = mod_code;
    }

    public String getWh_id() {
        return wh_id;
    }

    public void setWh_id(String wh_id) {
        this.wh_id = wh_id;
    }

    public String getEve_weight() {
        return eve_weight;
    }

    public void setEve_weight(String eve_weight) {
        this.eve_weight = eve_weight;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getMac_pre_hours() {
        return mac_pre_hours;
    }

    public void setMac_pre_hours(String mac_pre_hours) {
        this.mac_pre_hours = mac_pre_hours;
    }

    public String getLabor_hours() {
        return labor_hours;
    }

    public void setLabor_hours(String labor_hours) {
        this.labor_hours = labor_hours;
    }

    public String getMac_hours() {
        return mac_hours;
    }

    public void setMac_hours(String mac_hours) {
        this.mac_hours = mac_hours;
    }

    public String getScrap_weight() {
        return scrap_weight;
    }

    public void setScrap_weight(String scrap_weight) {
        this.scrap_weight = scrap_weight;
    }

    public String getMod_weight() {
        return mod_weight;
    }

    public void setMod_weight(String mod_weight) {
        this.mod_weight = mod_weight;
    }
}