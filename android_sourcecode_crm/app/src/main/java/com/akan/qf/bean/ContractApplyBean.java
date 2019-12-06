package com.akan.qf.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2018/11/28.
 */

public class ContractApplyBean implements Serializable {

    private List<StaffSignUnionBean> staffSignUnionList;

    public List<StaffSignUnionBean> getStaffSignUnionList() {
        return staffSignUnionList;
    }

    public void setStaffSignUnionList(List<StaffSignUnionBean> staffSignUnionList) {
        this.staffSignUnionList = staffSignUnionList;
    }
    /**
     * apply_id : 30
     * group_id : 15
     * apply_no : KHHT2019031152707
     * staff_id : 4
     * staff_name : 石磊
     * staff_department : 南方营销中心
     * apply_remark : 
     * apply_name : 兰陵王
     * customer_no : vbhhh
     * apply_department : 浙江大区
     * apply_tel : 13947387558
     * apply_address : 沟沟壑壑
     * apply_sale : 天空
     * apply_info : 天空之城
     * apply_rebate : 我摸着
     * apply_fare : To猫
     * apply_cost : sgbb755689999
     * apply_is_delete : 0
     * deadline_time : 天空套
     * apply_create_time : 2019-03-11
     * staff_uuid : 
     * group_parent_uuid : 
     * group_uuid : 
     * start_time : 
     * end_time : 
     */
    public String getJob_name() {
        return job_name;
    }

    public void setJob_name(String job_name) {
        this.job_name = job_name;
    }

    private String job_name;
    private String apply_id;
    private String group_id;
    private String apply_no;
    private String staff_id;
    private String staff_name;
    private String staff_department;
    private String apply_remark;
    private String apply_name;
    private String customer_no;
    private String apply_department;
    private String apply_tel;
    private String apply_address;
    private String apply_sale;
    private String apply_info;
    private String apply_rebate;
    private String apply_fare;
    private String apply_cost;
    private String apply_is_delete;
    private String deadline_time;
    private String apply_create_time;
    private String staff_uuid;
    private String group_parent_uuid;
    private String group_uuid;
    private String start_time;
    private String end_time;
    private List<FileNewBean> fileList;

    public List<FileNewBean> getFileList() {
        return fileList;
    }

    public void setFileList(List<FileNewBean> fileList) {
        this.fileList = fileList;
    }

    public String getApply_id() {
        return apply_id;
    }

    public void setApply_id(String apply_id) {
        this.apply_id = apply_id;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getApply_no() {
        return apply_no;
    }

    public void setApply_no(String apply_no) {
        this.apply_no = apply_no;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public String getStaff_name() {
        return staff_name;
    }

    public void setStaff_name(String staff_name) {
        this.staff_name = staff_name;
    }

    public String getStaff_department() {
        return staff_department;
    }

    public void setStaff_department(String staff_department) {
        this.staff_department = staff_department;
    }

    public String getApply_remark() {
        return apply_remark;
    }

    public void setApply_remark(String apply_remark) {
        this.apply_remark = apply_remark;
    }

    public String getApply_name() {
        return apply_name;
    }

    public void setApply_name(String apply_name) {
        this.apply_name = apply_name;
    }

    public String getCustomer_no() {
        return customer_no;
    }

    public void setCustomer_no(String customer_no) {
        this.customer_no = customer_no;
    }

    public String getApply_department() {
        return apply_department;
    }

    public void setApply_department(String apply_department) {
        this.apply_department = apply_department;
    }

    public String getApply_tel() {
        return apply_tel;
    }

    public void setApply_tel(String apply_tel) {
        this.apply_tel = apply_tel;
    }

    public String getApply_address() {
        return apply_address;
    }

    public void setApply_address(String apply_address) {
        this.apply_address = apply_address;
    }

    public String getApply_sale() {
        return apply_sale;
    }

    public void setApply_sale(String apply_sale) {
        this.apply_sale = apply_sale;
    }

    public String getApply_info() {
        return apply_info;
    }

    public void setApply_info(String apply_info) {
        this.apply_info = apply_info;
    }

    public String getApply_rebate() {
        return apply_rebate;
    }

    public void setApply_rebate(String apply_rebate) {
        this.apply_rebate = apply_rebate;
    }

    public String getApply_fare() {
        return apply_fare;
    }

    public void setApply_fare(String apply_fare) {
        this.apply_fare = apply_fare;
    }

    public String getApply_cost() {
        return apply_cost;
    }

    public void setApply_cost(String apply_cost) {
        this.apply_cost = apply_cost;
    }

    public String getApply_is_delete() {
        return apply_is_delete;
    }

    public void setApply_is_delete(String apply_is_delete) {
        this.apply_is_delete = apply_is_delete;
    }

    public String getDeadline_time() {
        return deadline_time;
    }

    public void setDeadline_time(String deadline_time) {
        this.deadline_time = deadline_time;
    }

    public String getApply_create_time() {
        return apply_create_time;
    }

    public void setApply_create_time(String apply_create_time) {
        this.apply_create_time = apply_create_time;
    }

    public String getStaff_uuid() {
        return staff_uuid;
    }

    public void setStaff_uuid(String staff_uuid) {
        this.staff_uuid = staff_uuid;
    }

    public String getGroup_parent_uuid() {
        return group_parent_uuid;
    }

    public void setGroup_parent_uuid(String group_parent_uuid) {
        this.group_parent_uuid = group_parent_uuid;
    }

    public String getGroup_uuid() {
        return group_uuid;
    }

    public void setGroup_uuid(String group_uuid) {
        this.group_uuid = group_uuid;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }
}
