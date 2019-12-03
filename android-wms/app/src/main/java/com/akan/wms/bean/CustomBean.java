package com.akan.wms.bean;

import java.io.Serializable;

public class CustomBean implements Serializable{


    /**
     * cus_id : 1001812180039115
     * cus_name : 六安营销中心
     * cus_code : 120011
     * cus_org : 1001512200010027
     * cus_is_effective : 1
     * cus_type_id : 1001601021373030
     * tag : 1
     */

    private long cus_id;
    private String cus_name;
    private String cus_code;
    private long cus_org;
    private int cus_is_effective;
    private String cus_type_id;
    private String tag;

    public long getCus_id() {
        return cus_id;
    }

    public void setCus_id(long cus_id) {
        this.cus_id = cus_id;
    }

    public String getCus_name() {
        return cus_name;
    }

    public void setCus_name(String cus_name) {
        this.cus_name = cus_name;
    }

    public String getCus_code() {
        return cus_code;
    }

    public void setCus_code(String cus_code) {
        this.cus_code = cus_code;
    }

    public long getCus_org() {
        return cus_org;
    }

    public void setCus_org(long cus_org) {
        this.cus_org = cus_org;
    }

    public int getCus_is_effective() {
        return cus_is_effective;
    }

    public void setCus_is_effective(int cus_is_effective) {
        this.cus_is_effective = cus_is_effective;
    }

    public String getCus_type_id() {
        return cus_type_id;
    }

    public void setCus_type_id(String cus_type_id) {
        this.cus_type_id = cus_type_id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
