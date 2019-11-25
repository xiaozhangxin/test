package com.ak.pt.bean;

/**
 * Created by admin on 2019/1/16.
 */

public class WorkerBean {

    public WorkerBean(String staff_id, String staff_name, String phone) {
        this.staff_id = staff_id;
        this.staff_name = staff_name;
        this.phone = phone;
    }

    private String phone;
    private String staff_name;
    private String staff_id;


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStaff_name() {
        return staff_name;
    }

    public void setStaff_name(String staff_name) {
        this.staff_name = staff_name;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }
}
