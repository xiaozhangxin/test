package com.akan.wms.bean;

import java.io.Serializable;

/**
 * Created by sh-lx on 2017/5/31.
 */

public class UserBean implements Serializable{


    /**
     * staff_id : 6003
     * token_staff_id :
     * staff_token : 17f909f1-8214-4d1b-9628-10be9f576617
     * app_token : 5a9a36e8-13ab-47fe-8af6-82c6a9e95566
     * pc_token :
     * staff_password :
     * old_password :
     * head_img : /images/others/logo.png
     * staff_no :
     * staff_account : AK128
     * staff_name : 文云会
     * staff_type : 0
     * sex : 男
     * birthday :
     * company_id :
     * company_name :
     * department_id : 6386
     * group_parent_uuid :
     * department_no : 003
     * department_name : 爱康企业集团（上海）
     * simple_department_name :
     * job_id :
     * job_name :
     * tel :
     * phone :
     * fax :
     * contact_address :
     * home_address :
     * note :
     * create_time : 2019-07-02 19:57:50
     * module_role_ids : 1
     * module_role_names :
     * id_card :
     * is_disable : 0
     * is_delete : 0
     * parent_id :
     * parent_staff_name :
     * parent_head_img :
     * staff_uuid :
     * parent_uuid :
     * is_departure : 0
     * native_place :
     * school_level :
     * professional :
     * we_chat :
     * is_all_data : 0
     * hire_date :
     * obtainment_date :
     * departure_date :
     * social_security :
     * is_can_export : 0
     * app_sign :
     * code_id :
     * staff_module :
     * staff_give : 0
     * pc_sign :
     * is_second_group_level :
     * systemRoleBeans : []
     * sign : login
     * job_name_list : []
     * start_time :
     * end_time :
     * fileName :
     * u9_code : AK128
     * u9_id : 1001512260166988
     * org_id : 1001512200010027
     */

    private String staff_id;
    private String token_staff_id;
    private String staff_token;
    private String app_token;
    private String pc_token;
    private String staff_password;
    private String old_password;
    private String head_img;
    private String staff_no;
    private String staff_account;
    private String staff_name;
    private String staff_type;
    private String sex;
    private String birthday;
    private String company_id;
    private String company_name;
    private String department_id;
    private String group_parent_uuid;
    private String department_no;
    private String department_name;
    private String simple_department_name;
    private String job_id;
    private String job_name;
    private String tel;
    private String phone;
    private String fax;
    private String contact_address;
    private String home_address;
    private String note;
    private String create_time;
    private String module_role_ids;
    private String module_role_names;
    private String id_card;
    private String is_disable;
    private String is_delete;
    private String parent_id;
    private String parent_staff_name;
    private String parent_head_img;
    private String staff_uuid;
    private String parent_uuid;
    private String is_departure;
    private String native_place;
    private String school_level;
    private String professional;
    private String we_chat;
    private String is_all_data;
    private String hire_date;
    private String obtainment_date;
    private String departure_date;
    private String social_security;
    private String is_can_export;
    private String app_sign;
    private String code_id;
    private String staff_module;
    private String staff_give;
    private String pc_sign;
    private String is_second_group_level;
    private String sign;
    private String start_time;
    private String end_time;
    private String fileName;
    private String u9_code;
    private String u9_id;
    private String org_id;
    private String org_name;
      private boolean isNow;


    public boolean isNow() {
        return isNow;
    }

    public void setNow(boolean now) {
        isNow = now;
    }

    public String getOrg_name() {
        return org_name;
    }

    public void setOrg_name(String org_name) {
        this.org_name = org_name;
    }
    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public String getToken_staff_id() {
        return token_staff_id;
    }

    public void setToken_staff_id(String token_staff_id) {
        this.token_staff_id = token_staff_id;
    }

    public String getStaff_token() {
        return staff_token;
    }

    public void setStaff_token(String staff_token) {
        this.staff_token = staff_token;
    }

    public String getApp_token() {
        return app_token;
    }

    public void setApp_token(String app_token) {
        this.app_token = app_token;
    }

    public String getPc_token() {
        return pc_token;
    }

    public void setPc_token(String pc_token) {
        this.pc_token = pc_token;
    }

    public String getStaff_password() {
        return staff_password;
    }

    public void setStaff_password(String staff_password) {
        this.staff_password = staff_password;
    }

    public String getOld_password() {
        return old_password;
    }

    public void setOld_password(String old_password) {
        this.old_password = old_password;
    }

    public String getHead_img() {
        return head_img;
    }

    public void setHead_img(String head_img) {
        this.head_img = head_img;
    }

    public String getStaff_no() {
        return staff_no;
    }

    public void setStaff_no(String staff_no) {
        this.staff_no = staff_no;
    }

    public String getStaff_account() {
        return staff_account;
    }

    public void setStaff_account(String staff_account) {
        this.staff_account = staff_account;
    }

    public String getStaff_name() {
        return staff_name;
    }

    public void setStaff_name(String staff_name) {
        this.staff_name = staff_name;
    }

    public String getStaff_type() {
        return staff_type;
    }

    public void setStaff_type(String staff_type) {
        this.staff_type = staff_type;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getCompany_id() {
        return company_id;
    }

    public void setCompany_id(String company_id) {
        this.company_id = company_id;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(String department_id) {
        this.department_id = department_id;
    }

    public String getGroup_parent_uuid() {
        return group_parent_uuid;
    }

    public void setGroup_parent_uuid(String group_parent_uuid) {
        this.group_parent_uuid = group_parent_uuid;
    }

    public String getDepartment_no() {
        return department_no;
    }

    public void setDepartment_no(String department_no) {
        this.department_no = department_no;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public String getSimple_department_name() {
        return simple_department_name;
    }

    public void setSimple_department_name(String simple_department_name) {
        this.simple_department_name = simple_department_name;
    }

    public String getJob_id() {
        return job_id;
    }

    public void setJob_id(String job_id) {
        this.job_id = job_id;
    }

    public String getJob_name() {
        return job_name;
    }

    public void setJob_name(String job_name) {
        this.job_name = job_name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getContact_address() {
        return contact_address;
    }

    public void setContact_address(String contact_address) {
        this.contact_address = contact_address;
    }

    public String getHome_address() {
        return home_address;
    }

    public void setHome_address(String home_address) {
        this.home_address = home_address;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getModule_role_ids() {
        return module_role_ids;
    }

    public void setModule_role_ids(String module_role_ids) {
        this.module_role_ids = module_role_ids;
    }

    public String getModule_role_names() {
        return module_role_names;
    }

    public void setModule_role_names(String module_role_names) {
        this.module_role_names = module_role_names;
    }

    public String getId_card() {
        return id_card;
    }

    public void setId_card(String id_card) {
        this.id_card = id_card;
    }

    public String getIs_disable() {
        return is_disable;
    }

    public void setIs_disable(String is_disable) {
        this.is_disable = is_disable;
    }

    public String getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(String is_delete) {
        this.is_delete = is_delete;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getParent_staff_name() {
        return parent_staff_name;
    }

    public void setParent_staff_name(String parent_staff_name) {
        this.parent_staff_name = parent_staff_name;
    }

    public String getParent_head_img() {
        return parent_head_img;
    }

    public void setParent_head_img(String parent_head_img) {
        this.parent_head_img = parent_head_img;
    }

    public String getStaff_uuid() {
        return staff_uuid;
    }

    public void setStaff_uuid(String staff_uuid) {
        this.staff_uuid = staff_uuid;
    }

    public String getParent_uuid() {
        return parent_uuid;
    }

    public void setParent_uuid(String parent_uuid) {
        this.parent_uuid = parent_uuid;
    }

    public String getIs_departure() {
        return is_departure;
    }

    public void setIs_departure(String is_departure) {
        this.is_departure = is_departure;
    }

    public String getNative_place() {
        return native_place;
    }

    public void setNative_place(String native_place) {
        this.native_place = native_place;
    }

    public String getSchool_level() {
        return school_level;
    }

    public void setSchool_level(String school_level) {
        this.school_level = school_level;
    }

    public String getProfessional() {
        return professional;
    }

    public void setProfessional(String professional) {
        this.professional = professional;
    }

    public String getWe_chat() {
        return we_chat;
    }

    public void setWe_chat(String we_chat) {
        this.we_chat = we_chat;
    }

    public String getIs_all_data() {
        return is_all_data;
    }

    public void setIs_all_data(String is_all_data) {
        this.is_all_data = is_all_data;
    }

    public String getHire_date() {
        return hire_date;
    }

    public void setHire_date(String hire_date) {
        this.hire_date = hire_date;
    }

    public String getObtainment_date() {
        return obtainment_date;
    }

    public void setObtainment_date(String obtainment_date) {
        this.obtainment_date = obtainment_date;
    }

    public String getDeparture_date() {
        return departure_date;
    }

    public void setDeparture_date(String departure_date) {
        this.departure_date = departure_date;
    }

    public String getSocial_security() {
        return social_security;
    }

    public void setSocial_security(String social_security) {
        this.social_security = social_security;
    }

    public String getIs_can_export() {
        return is_can_export;
    }

    public void setIs_can_export(String is_can_export) {
        this.is_can_export = is_can_export;
    }

    public String getApp_sign() {
        return app_sign;
    }

    public void setApp_sign(String app_sign) {
        this.app_sign = app_sign;
    }

    public String getCode_id() {
        return code_id;
    }

    public void setCode_id(String code_id) {
        this.code_id = code_id;
    }

    public String getStaff_module() {
        return staff_module;
    }

    public void setStaff_module(String staff_module) {
        this.staff_module = staff_module;
    }

    public String getStaff_give() {
        return staff_give;
    }

    public void setStaff_give(String staff_give) {
        this.staff_give = staff_give;
    }

    public String getPc_sign() {
        return pc_sign;
    }

    public void setPc_sign(String pc_sign) {
        this.pc_sign = pc_sign;
    }

    public String getIs_second_group_level() {
        return is_second_group_level;
    }

    public void setIs_second_group_level(String is_second_group_level) {
        this.is_second_group_level = is_second_group_level;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getU9_code() {
        return u9_code;
    }

    public void setU9_code(String u9_code) {
        this.u9_code = u9_code;
    }

    public String getU9_id() {
        return u9_id;
    }

    public void setU9_id(String u9_id) {
        this.u9_id = u9_id;
    }

    public String getOrg_id() {
        return org_id;
    }

    public void setOrg_id(String org_id) {
        this.org_id = org_id;
    }


}