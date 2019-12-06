package com.akan.qf.bean;

import java.io.Serializable;

public  class StaffSignUnionBean implements Serializable{


    /**
     * union_id : 42
     * staff_id :
     * sign_id :
     * parent_id :
     * create_time :
     * sign_name : 北方1号
     */

    private int union_id;
    private String staff_id;
    private String sign_id;
    private String parent_id;
    private String create_time;
    private String sign_name;

    public int getUnion_id() {
        return union_id;
    }

    public void setUnion_id(int union_id) {
        this.union_id = union_id;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public String getSign_id() {
        return sign_id;
    }

    public void setSign_id(String sign_id) {
        this.sign_id = sign_id;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getSign_name() {
        return sign_name;
    }

    public void setSign_name(String sign_name) {
        this.sign_name = sign_name;
    }
}
