package com.ak.pt.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2019/5/23.
 */

public class FeedBackBean implements Serializable{


    /**
     * quality_id : 1
     * staff_id : 42
     * staff_name : 陈艳涛
     * department_name : 南方营销中心
     * quality_no : ZLFK2019052134279
     * group_no : 001001
     * customer_name : 温度传感器
     * customer_sex : 客户名称
     * customer_tel : 客户性别
     * customer_address : 客户电话
     * address_code : 
     * quality_name : 
     * quality_tel : 
     * fault_model : 
     * fault_no : 
     * product_no : 
     * quality_info : 
     * update_time : 2019-05-21 14:19:13
     * create_time : 2019-05-21 14:19:13
     * is_delete : 0
     * job_name_list : []
     * fileList : []
     * job_name : 营销总监
     * start_time : 
     * end_time : 
     * staff_uuid : 
     * group_parent_uuid : 
     */

    private String quality_id;
    private String staff_id;
    private String staff_name;
    private String department_name;
    private String quality_no;
    private String group_no;
    private String customer_name;
    private String customer_sex;
    private String customer_tel;
    private String customer_address;
    private String address_code;
    private String quality_name;
    private String quality_tel;
    private String fault_model;
    private String fault_no;
    private String product_no;
    private String quality_info;
    private String update_time;
    private String create_time;
    private String is_delete;
    private String job_name;
    private String start_time;
    private String end_time;
    private String address_info;
    private String staff_uuid;

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    private String group_parent_uuid;
    private String group_id;
    private List<FeedbackFileBean> fileList;

    public String getAddress_info() {
        return address_info;
    }

    public void setAddress_info(String address_info) {
        this.address_info = address_info;
    }

    public String getQuality_id() {
        return quality_id;
    }

    public void setQuality_id(String quality_id) {
        this.quality_id = quality_id;
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

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public String getQuality_no() {
        return quality_no;
    }

    public void setQuality_no(String quality_no) {
        this.quality_no = quality_no;
    }

    public String getGroup_no() {
        return group_no;
    }

    public void setGroup_no(String group_no) {
        this.group_no = group_no;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_sex() {
        return customer_sex;
    }

    public void setCustomer_sex(String customer_sex) {
        this.customer_sex = customer_sex;
    }

    public String getCustomer_tel() {
        return customer_tel;
    }

    public void setCustomer_tel(String customer_tel) {
        this.customer_tel = customer_tel;
    }

    public String getCustomer_address() {
        return customer_address;
    }

    public void setCustomer_address(String customer_address) {
        this.customer_address = customer_address;
    }

    public String getAddress_code() {
        return address_code;
    }

    public void setAddress_code(String address_code) {
        this.address_code = address_code;
    }

    public String getQuality_name() {
        return quality_name;
    }

    public void setQuality_name(String quality_name) {
        this.quality_name = quality_name;
    }

    public String getQuality_tel() {
        return quality_tel;
    }

    public void setQuality_tel(String quality_tel) {
        this.quality_tel = quality_tel;
    }

    public String getFault_model() {
        return fault_model;
    }

    public void setFault_model(String fault_model) {
        this.fault_model = fault_model;
    }

    public String getFault_no() {
        return fault_no;
    }

    public void setFault_no(String fault_no) {
        this.fault_no = fault_no;
    }

    public String getProduct_no() {
        return product_no;
    }

    public void setProduct_no(String product_no) {
        this.product_no = product_no;
    }

    public String getQuality_info() {
        return quality_info;
    }

    public void setQuality_info(String quality_info) {
        this.quality_info = quality_info;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(String is_delete) {
        this.is_delete = is_delete;
    }

    public String getJob_name() {
        return job_name;
    }

    public void setJob_name(String job_name) {
        this.job_name = job_name;
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


    public List<FeedbackFileBean> getFileList() {
        return fileList;
    }

    public void setFileList(List<FeedbackFileBean> fileList) {
        this.fileList = fileList;
    }
}
