package com.akan.wms.bean;

import java.io.Serializable;

public class OperatorStaffBean implements Serializable {


    /**
     * staff_name : 石磊
     * staff_account : 石磊
     * staff_id : 4
     */

    private String staff_name;
    private String staff_account;
    private String staff_id;

    public String getStaff_name() {
        return staff_name;
    }

    public void setStaff_name(String staff_name) {
        this.staff_name = staff_name;
    }

    public String getStaff_account() {
        return staff_account;
    }

    public void setStaff_account(String staff_account) {
        this.staff_account = staff_account;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }
}
