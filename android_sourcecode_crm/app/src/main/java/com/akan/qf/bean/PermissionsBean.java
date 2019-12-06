package com.akan.qf.bean;

import java.io.Serializable;

public class PermissionsBean implements Serializable{
    /**
     * app_id : 112
     * role_id : 12
     * menu_id :
     * app_operation : 0,1,2,3,4,5,6,7,8,9
     * app_data : 0
     * app_department :
     * create_time :
     * staff_id :
     * module_url :
     */

    private String app_id;
    private String role_id;
    private String menu_id;
    private String app_operation;
    private String app_data;
    private String app_department;
    private String create_time;
    private String staff_id;
    private String module_url;

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(String menu_id) {
        this.menu_id = menu_id;
    }

    public String getApp_operation() {
        return app_operation;
    }

    public void setApp_operation(String app_operation) {
        this.app_operation = app_operation;
    }

    public String getApp_data() {
        return app_data;
    }

    public void setApp_data(String app_data) {
        this.app_data = app_data;
    }

    public String getApp_department() {
        return app_department;
    }

    public void setApp_department(String app_department) {
        this.app_department = app_department;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public String getModule_url() {
        return module_url;
    }

    public void setModule_url(String module_url) {
        this.module_url = module_url;
    }
}