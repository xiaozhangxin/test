package com.ak.pt.bean;

import java.io.Serializable;

/**
 * Created by admin on 2019/5/29.
 */

public class PeopleRecordListBean implements Serializable {

    /**
     * record_id : 9
     * add_id : 
     * staff_id : 163
     * record_name : 水工
     * record_remark : 
     * record_state : wait_audit
     * record_create_time : 2019-05-29 16:57:55
     * record_state_show : 发出申请
     */

    private String record_id;
    private String add_id;
    private String staff_id;
    private String record_name;
    private String record_remark;
    private String record_state;
    private String record_create_time;
    private String record_state_show;

    public String getRecord_id() {
        return record_id;
    }

    public void setRecord_id(String record_id) {
        this.record_id = record_id;
    }

    public String getAdd_id() {
        return add_id;
    }

    public void setAdd_id(String add_id) {
        this.add_id = add_id;
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
