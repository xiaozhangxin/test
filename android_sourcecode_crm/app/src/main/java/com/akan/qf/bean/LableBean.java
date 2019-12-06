package com.akan.qf.bean;

import java.io.Serializable;
import java.util.List;

public class LableBean implements Serializable{


    /**
     * is_app :
     * data_sign :
     * group_ids :
     * module_id :
     * group_uuid :
     * operation :
     * group_id :
     * staff_uuid :
     * staff_id : 1110
     * staff_sign :
     * sign_id : 19
     * staff_name : 李志伟
     * sign_name : 北方1号
     * is_delete : 0
     * create_time : 2019-09-23 16:04:06
     * update_time : 2019-09-23 17:39:48
     */

    private String is_app;
    private String data_sign;
    private String group_ids;
    private String module_id;
    private String group_uuid;
    private String operation;
    private String group_id;
    private String staff_uuid;
    private String staff_id;
    private String staff_sign;
    private String sign_id;


    private String staff_name;
    private List<StaffSignListBean> staffSignList;

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    private String sign_name;
    private String is_delete;
    private String create_time;
    private String update_time;
    private boolean check;

    public String getIs_app() {
        return is_app;
    }

    public void setIs_app(String is_app) {
        this.is_app = is_app;
    }

    public String getData_sign() {
        return data_sign;
    }

    public void setData_sign(String data_sign) {
        this.data_sign = data_sign;
    }

    public String getGroup_ids() {
        return group_ids;
    }

    public void setGroup_ids(String group_ids) {
        this.group_ids = group_ids;
    }

    public String getModule_id() {
        return module_id;
    }

    public void setModule_id(String module_id) {
        this.module_id = module_id;
    }

    public String getGroup_uuid() {
        return group_uuid;
    }

    public void setGroup_uuid(String group_uuid) {
        this.group_uuid = group_uuid;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getStaff_uuid() {
        return staff_uuid;
    }

    public void setStaff_uuid(String staff_uuid) {
        this.staff_uuid = staff_uuid;
    }


    public String getStaff_sign() {
        return staff_sign;
    }

    public void setStaff_sign(String staff_sign) {
        this.staff_sign = staff_sign;
    }



    public String getStaff_name() {
        return staff_name;
    }

    public void setStaff_name(String staff_name) {
        this.staff_name = staff_name;
    }

    public String getSign_name() {
        return sign_name;
    }

    public void setSign_name(String sign_name) {
        this.sign_name = sign_name;
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

    public String getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(String is_delete) {
        this.is_delete = is_delete;
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

    public List<StaffSignListBean> getStaffSignList() {
        return staffSignList;
    }

    public void setStaffSignList(List<StaffSignListBean> staffSignList) {
        this.staffSignList = staffSignList;
    }

}
