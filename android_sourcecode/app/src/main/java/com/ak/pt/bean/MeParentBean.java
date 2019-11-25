package com.ak.pt.bean;

import java.io.Serializable;

/**
 * Created by admin on 2019/5/29.
 */

public class MeParentBean implements Serializable {

    public MeParentBean(String staff_name, String staff_id, String head_img, String job_name) {
        this.staff_name = staff_name;
        this.staff_id = staff_id;
        this.head_img = head_img;
        this.job_name = job_name;
    }

    /**
     * staff_name : 皖北区域经理
     * staff_id : 29
     */


    private String staff_name;
    private String job_name;
    private String staff_id;
    private String head_img;
    private String staff_give;
    private String staff_module;
    private String department_name;

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public String getStaff_module() {
        return staff_module;
    }

    public void setStaff_module(String staff_module) {
        this.staff_module = staff_module;
    }

    public String getStaff_give() {
        return staff_give;
    }

    public void setStaff_give(String staff_give) {
        this.staff_give = staff_give;
    }

    public String getJob_name() {
        return job_name;
    }

    public void setJob_name(String job_name) {
        this.job_name = job_name;
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

    public String getHead_img() {
        return head_img;
    }

    public void setHead_img(String head_img) {
        this.head_img = head_img;
    }
}
