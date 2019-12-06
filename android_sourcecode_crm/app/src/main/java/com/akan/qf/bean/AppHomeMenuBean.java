package com.akan.qf.bean;

import java.io.Serializable;

public class AppHomeMenuBean implements Serializable {


    /**
     * menu_id : 72
     * menu_title : 客户合同
     * menu_group : 0
     * menu_type : 1
     * menu_key : ContractApply
     * menu_url :
     * parent_id : 47
     * menu_icon :
     * sort : 1
     * create_time :
     * operation_list : [{"label":"新增","value":"0"},{"label":"编辑","value":"1"},{"label":"删除","value":"2"},{"label":"审核","value":"3"},{"label":"导出","value":"4"},{"label":"退审","value":"5"},{"label":"作废","value":"6"}]
     * data_list : [{"label":"仅自己","value":"0"},{"label":"本部门和下级部门","value":"1"},{"label":"选择部门","value":"2"}]
     * is_disable :
     * appHomeMenuBeans : []
     * appPermissionsBeans : {"app_id":112,"role_id":12,"menu_id":"","app_operation":"0,1,2,3,4,5,6,7,8,9","app_data":"0","app_department":"","create_time":"","staff_id":"","module_url":""}
     */

    private String menu_id;
    private String menu_title;
    private String menu_type;
    private String menu_key;
    private PermissionsBean appPermissionsBeans;

    public PermissionsBean getAppPermissionsBeans() {
        return appPermissionsBeans;
    }

    public String getMenu_id() {
        return menu_id;
    }

    public void setMenu_id(String menu_id) {
        this.menu_id = menu_id;
    }

    public String getMenu_title() {
        return menu_title;
    }

    public void setMenu_title(String menu_title) {
        this.menu_title = menu_title;
    }



    public String getMenu_type() {
        return menu_type;
    }

    public void setMenu_type(String menu_type) {
        this.menu_type = menu_type;
    }

    public String getMenu_key() {
        return menu_key;
    }

    public void setMenu_key(String menu_key) {
        this.menu_key = menu_key;
    }


    public void setAppPermissionsBeans(PermissionsBean appPermissionsBeans) {
        this.appPermissionsBeans = appPermissionsBeans;
    }



}
