package com.akan.wms.bean;

import java.io.Serializable;

public class SupplierBean implements Serializable{


    /**
     * id : 1001812180473172
     * code : 99001
     * name : 日本三井 P5050N
     * org : 1001512200010027
     * category :
     * is_effective : 1
     * tag : 1
     */

    private long id;
    private String code;
    private String name;
    private long org;
    private String category;
    private int is_effective;
    private String tag;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getOrg() {
        return org;
    }

    public void setOrg(long org) {
        this.org = org;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getIs_effective() {
        return is_effective;
    }

    public void setIs_effective(int is_effective) {
        this.is_effective = is_effective;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
