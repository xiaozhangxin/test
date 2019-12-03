package com.akan.wms.bean;

import java.io.Serializable;

public class MiscShipDocTypeBean implements Serializable{


    /**
     * id : 1001512205410201
     * code : ZF1
     * org_id : 1001512200010027
     * name : 生产材料领用（直接材料）
     * short_name : 001
     * description : 各生产车间领用的原材料（限于生产用主要原料，如PPR主料、色母、PVC原料、PERT原料、嵌件等
     * disable_date : 9999-12-31 00:00:00
     * is_effective : 1
     */

    private long id;
    private String code;
    private long org_id;
    private String name;
    private String short_name;
    private String description;
    private String disable_date;
    private String is_effective;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public long getOrg_id() {
        return org_id;
    }

    public void setOrg_id(long org_id) {
        this.org_id = org_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisable_date() {
        return disable_date;
    }

    public void setDisable_date(String disable_date) {
        this.disable_date = disable_date;
    }

    public String getIs_effective() {
        return is_effective;
    }

    public void setIs_effective(String is_effective) {
        this.is_effective = is_effective;
    }
}
