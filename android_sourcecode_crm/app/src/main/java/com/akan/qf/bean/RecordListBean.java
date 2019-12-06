package com.akan.qf.bean;

import java.io.Serializable;

/**
 * Created by admin on 2018/11/9.
 */

public class RecordListBean implements Serializable{
    /**
     * record_id : 35
     * support_id : 14
     * staff_id : 52
     * record_name : 大哥
     * record_department : 爱康企业集团（上海）有限公司/南方营销中心/苏皖区域
     * record_remark : 备注哈哈
     * record_state : wait_audit
     * record_create_time : 2018-11-09 12:03:02
     * record_state_show : 发出申请
     */

    private String record_id;
    private String support_id;
    private String staff_id;
    private String record_name;
    private String record_department;
    private String record_remark;
    private String record_state;
    private String record_create_time;
    private String record_state_show;
    private String apply_id;
    private String cc_person_name;

    public String getCc_person_name() {
        return cc_person_name;
    }

    public void setCc_person_name(String cc_person_name) {
        this.cc_person_name = cc_person_name;
    }

    public String getSupport_id() {
        return support_id;
    }

    public void setSupport_id(String support_id) {
        this.support_id = support_id;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public String getApply_id() {
        return apply_id;
    }

    public void setApply_id(String apply_id) {
        this.apply_id = apply_id;
    }

    public String getRecord_id() {
        return record_id;
    }

    public void setRecord_id(String record_id) {
        this.record_id = record_id;
    }

    public String getRecord_name() {
        return record_name;
    }

    public void setRecord_name(String record_name) {
        this.record_name = record_name;
    }

    public String getRecord_department() {
        return record_department;
    }

    public void setRecord_department(String record_department) {
        this.record_department = record_department;
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