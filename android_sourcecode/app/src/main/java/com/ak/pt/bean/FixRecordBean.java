package com.ak.pt.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2019/5/22.
 */

public class FixRecordBean implements Serializable{


    public String getAddress_info() {
        return address_info;
    }

    public void setAddress_info(String address_info) {
        this.address_info = address_info;
    }

    /**
     * repair_id : 19
     * staff_id : 4
     * staff_name : 石磊
     * department_name : 北方营销中心
     * customer_name : 扣扣
     * customer_sex : 先生
     * customer_tel : 52233
     * customer_address : 上海市-上海市-浦东新区
     * install_name : 天空
     * install_tel : 屠龙记
     * service_shop : 考虑考虑
     * product_fault : 
     * product_model : 前置过滤器系列CW-A1
     * product_no : 太可怜了
     * fault_model : 不工作、不出水
     * fault_no : 空咯莫
     * repair_plan : 他扣扣
     * change_name : 水箱
     * create_time : 2019-05-23 14:32:09
     * update_time : 2019-05-23 14:32:09
     * is_delete : 0
     * group_no : 001002
     * repair_no : WXJL2019052357658
     * address_code : 200000
     * repair_time : 2019-05-23
     * job_name_list : []
     * fileList : []
     * job_name : 经理
     * start_time : 
     * end_time : 
     * staff_uuid : 
     * group_parent_uuid : 
     */

    private String repair_id;
    private String address_info;
    private String staff_id;
    private String staff_name;
    private String department_name;
    private String customer_name;
    private String customer_sex;
    private String customer_tel;
    private String customer_address;
    private String install_name;
    private String install_tel;
    private String service_shop;
    private String product_fault;
    private String product_model;
    private String product_no;
    private String fault_model;
    private String fault_no;
    private String repair_plan;
    private String change_name;
    private String create_time;
    private String update_time;
    private String is_delete;
    private String group_no;
    private String repair_no;

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    private String address_code;
    private String repair_time;
    private String job_name;
    private String start_time;
    private String end_time;
    private String staff_uuid;
    private String group_id;
    private String group_parent_uuid;
    private List<?> job_name_list;
    private List<FixFileBean> fileList;

    public String getRepair_id() {
        return repair_id;
    }

    public void setRepair_id(String repair_id) {
        this.repair_id = repair_id;
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

    public String getInstall_name() {
        return install_name;
    }

    public void setInstall_name(String install_name) {
        this.install_name = install_name;
    }

    public String getInstall_tel() {
        return install_tel;
    }

    public void setInstall_tel(String install_tel) {
        this.install_tel = install_tel;
    }

    public String getService_shop() {
        return service_shop;
    }

    public void setService_shop(String service_shop) {
        this.service_shop = service_shop;
    }

    public String getProduct_fault() {
        return product_fault;
    }

    public void setProduct_fault(String product_fault) {
        this.product_fault = product_fault;
    }

    public String getProduct_model() {
        return product_model;
    }

    public void setProduct_model(String product_model) {
        this.product_model = product_model;
    }

    public String getProduct_no() {
        return product_no;
    }

    public void setProduct_no(String product_no) {
        this.product_no = product_no;
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

    public String getRepair_plan() {
        return repair_plan;
    }

    public void setRepair_plan(String repair_plan) {
        this.repair_plan = repair_plan;
    }

    public String getChange_name() {
        return change_name;
    }

    public void setChange_name(String change_name) {
        this.change_name = change_name;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(String is_delete) {
        this.is_delete = is_delete;
    }

    public String getGroup_no() {
        return group_no;
    }

    public void setGroup_no(String group_no) {
        this.group_no = group_no;
    }

    public String getRepair_no() {
        return repair_no;
    }

    public void setRepair_no(String repair_no) {
        this.repair_no = repair_no;
    }

    public String getAddress_code() {
        return address_code;
    }

    public void setAddress_code(String address_code) {
        this.address_code = address_code;
    }

    public String getRepair_time() {
        return repair_time;
    }

    public void setRepair_time(String repair_time) {
        this.repair_time = repair_time;
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

    public List<?> getJob_name_list() {
        return job_name_list;
    }

    public void setJob_name_list(List<?> job_name_list) {
        this.job_name_list = job_name_list;
    }

    public List<FixFileBean> getFileList() {
        return fileList;
    }

    public void setFileList(List<FixFileBean> fileList) {
        this.fileList = fileList;
    }
}
