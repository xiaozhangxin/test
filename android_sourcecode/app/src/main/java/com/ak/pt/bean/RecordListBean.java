package com.ak.pt.bean;


import java.io.Serializable;

public class RecordListBean implements Serializable{
    /**
     * record_id : 265
     * apply_id : 101
     * staff_id : 80
     * record_name : 文员lv1
     * record_remark :
     * record_state : wait_audit
     * record_create_time : 2019-10-16 17:14:47
     * record_state_show : 发出申请
     */

    private String record_id;
    private String apply_id;
    private String staff_id;
    private String record_name;

    private String record_remark;
    private String record_state;
    private String record_create_time;
    private String record_state_show;

    private String staff_name;
    private String create_time;

    public String getStaff_name() {
        return staff_name;
    }

    public void setStaff_name(String staff_name) {
        this.staff_name = staff_name;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getRecord_id() {
        return record_id;
    }

    public void setRecord_id(String record_id) {
        this.record_id = record_id;
    }

    public String getApply_id() {
        return apply_id;
    }

    public void setApply_id(String apply_id) {
        this.apply_id = apply_id;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public String getRecord_name() {
        return record_name;
    }

    public void setRecord_name(String record_name) {
        this.record_name = record_name;
    }

    public String getRecord_remark() {
        return record_remark;
    }

    public void setRecord_remark(String record_remark) {
        this.record_remark = record_remark;
    }

    public String getRecord_state() {
        return record_state;
    }

    public void setRecord_state(String record_state) {
        this.record_state = record_state;
    }

    public String getRecord_create_time() {
        return record_create_time;
    }

    public void setRecord_create_time(String record_create_time) {
        this.record_create_time = record_create_time;
    }

    public String getRecord_state_show() {
        return record_state_show;
    }

    public void setRecord_state_show(String record_state_show) {
        this.record_state_show = record_state_show;
    }
}
