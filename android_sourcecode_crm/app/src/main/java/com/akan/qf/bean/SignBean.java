package com.akan.qf.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2018/7/2.
 */

public class SignBean implements Serializable {


    /**
     * sign_id : 41
     * staff_id : 4
     * staff_name : 石磊
     * sign_content : 急急急
     * sign_image :
     * create_time : 2018-10-17 15:30:33
     * sign_address : 上海市静安区江场路1131号靠近中环协信天地
     * department_name : 爱康企业集团（上海）有限公司/南方营销中心
     * job_name : 文员
     * sign_number : 25
     * start_time :
     * end_time :
     * staff_uuid :
     * group_parent_uuid :
     */
    private List<StaffSignUnionBean> staffSignUnionList;

    public List<StaffSignUnionBean> getStaffSignUnionList() {
        return staffSignUnionList;
    }

    public void setStaffSignUnionList(List<StaffSignUnionBean> staffSignUnionList) {
        this.staffSignUnionList = staffSignUnionList;
    }

    private String sign_id;
    private String staff_id;
    private String staff_name;
    private String sign_content;
    private String sign_image;
    private String create_time;
    private String sign_address;
    private String department_name;
    private String job_name;
    private String sign_number;
    private String start_time;
    private String head_img;
    private String end_time;
    private String staff_uuid;
    private String group_parent_uuid;

    public String getHead_img() {
        return head_img;
    }

    public void setHead_img(String head_img) {
        this.head_img = head_img;
    }

    public String getSign_id() {
        return sign_id;
    }

    public void setSign_id(String sign_id) {
        this.sign_id = sign_id;
    }

    public String getSign_number() {
        return sign_number;
    }

    public void setSign_number(String sign_number) {
        this.sign_number = sign_number;
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

    public String getSign_content() {
        return sign_content;
    }

    public void setSign_content(String sign_content) {
        this.sign_content = sign_content;
    }

    public String getSign_image() {
        return sign_image;
    }

    public void setSign_image(String sign_image) {
        this.sign_image = sign_image;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getSign_address() {
        return sign_address;
    }

    public void setSign_address(String sign_address) {
        this.sign_address = sign_address;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
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
}
