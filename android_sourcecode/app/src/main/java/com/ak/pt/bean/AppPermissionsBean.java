package com.ak.pt.bean;

import java.io.Serializable;

public class AppPermissionsBean implements Serializable{


    /**
     * app_id : 32
     * role_id : 20
     * menu_id : 79
     * app_operation :
     * app_data : 2
     * app_department : 322,323,324,325,326,327,328,329,330,331,332,333,334,335,336,337,338,339,427,428,429,430,431,432,433,434,435,436,437,438,439,440,441,442,443,444,445,446,447,448,449,450,451,452,453,454,455,456,457,458,459,460,461,462,463,464,465,466,467,468,469,470,471,472,473,474,475,476,477,478
     * create_time :
     * staff_id :
     * module_url :
     */

    private String app_id;
    private String role_id;
    private String menu_id; //  moduleId
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
