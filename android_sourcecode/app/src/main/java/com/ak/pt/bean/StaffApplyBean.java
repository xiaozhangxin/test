package com.ak.pt.bean;

import java.io.Serializable;
import java.util.List;

public class StaffApplyBean implements Serializable {


    /**
     * apply_id : 101
     * apply_no : RZSQ2019101601861
     * staff_id : 80
     * group_id : 429
     * department_name : 东海县李正楠
     * group_no : 001001007001001
     * large_area_id : 427
     * region_id : 427
     * shop_id : 429
     * contact_name : 123
     * contact_tel : 123
     * person_num :
     * pressure_num :
     * photo_tool :
     * vehicle :
     * next_audit_id : 266
     * apply_state : wait_audit
     * is_delete : 0
     * update_time : 2019-10-16 17:14:47
     * create_time : 2019-10-16 17:14:47
     * staff_audit : ,80,266,
     * recordList : [{"record_id":265,"apply_id":101,"staff_id":80,"record_name":"文员lv1","record_remark":"","record_state":"wait_audit","record_create_time":"2019-10-16 17:14:47","record_state_show":"发出申请"},{"record_id":266,"apply_id":101,"staff_id":266,"record_name":"周专员","record_remark":"","record_state":"unAuditing","record_create_time":"","record_state_show":"审核中"}]
     * clerkInfoList : [{"info_id":343,"apply_id":101,"info_name":"111","info_tel":"111","type":"clerk","create_time":"2019-10-16 17:14:47"}]
     * pressureInfoList : [{"info_id":342,"apply_id":101,"info_name":"123","info_tel":"123","type":"pressure","create_time":"2019-10-16 17:14:47"}]
     * job_name_list : []
     * staff_name : 文员lv1
     * remark :
     * is_audit :
     * apply_state_show : 未审核
     * job_name : 文员
     * large_area_name : 苏皖大区
     * region_name : 苏皖大区
     * shop_name : 东海县李正楠
     * start_time :
     * end_time :
     * staff_uuid :
     * group_parent_uuid :
     * all_name :
     * is_app :
     * data_sign :
     * group_ids :
     * module_id :
     * group_uuid :
     * operation :
     */

    private String apply_id;
    private String apply_no;
    private String staff_id;
    private String group_id;
    private String department_name;
    private String group_no;
    private String large_area_id;
    private String region_id;
    private String shop_id;
    private String contact_name;
    private String contact_tel;
    private String person_num;
    private String pressure_num;
    private String photo_tool;
    private String vehicle;
    private String next_audit_id;
    private String apply_state;
    private String is_delete;
    private String update_time;
    private String create_time;
    private String staff_audit;
    private String staff_name;
    private String remark;
    private String is_audit;
    private String apply_state_show;
    private String job_name;
    private String large_area_name;
    private String region_name;
    private String shop_name;
    private String start_time;
    private String end_time;
    private String staff_uuid;
    private String group_parent_uuid;
    private String all_name;
    private String is_app;
    private String data_sign;
    private String group_ids;
    private String module_id;
    private String group_uuid;
    private String operation;
    private List<RecordListBean> recordList;
    private List<ClerkInfoListBean> clerkInfoList;
    private List<ClerkInfoListBean> pressureInfoList;

    public String getApply_id() {
        return apply_id;
    }

    public void setApply_id(String apply_id) {
        this.apply_id = apply_id;
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

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }

    public String getGroup_no() {
        return group_no;
    }

    public void setGroup_no(String group_no) {
        this.group_no = group_no;
    }

    public String getLarge_area_id() {
        return large_area_id;
    }

    public void setLarge_area_id(String large_area_id) {
        this.large_area_id = large_area_id;
    }

    public String getRegion_id() {
        return region_id;
    }

    public void setRegion_id(String region_id) {
        this.region_id = region_id;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getContact_tel() {
        return contact_tel;
    }

    public void setContact_tel(String contact_tel) {
        this.contact_tel = contact_tel;
    }

    public String getPerson_num() {
        return person_num;
    }

    public void setPerson_num(String person_num) {
        this.person_num = person_num;
    }

    public String getPressure_num() {
        return pressure_num;
    }

    public void setPressure_num(String pressure_num) {
        this.pressure_num = pressure_num;
    }

    public String getPhoto_tool() {
        return photo_tool;
    }

    public void setPhoto_tool(String photo_tool) {
        this.photo_tool = photo_tool;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getNext_audit_id() {
        return next_audit_id;
    }

    public void setNext_audit_id(String next_audit_id) {
        this.next_audit_id = next_audit_id;
    }

    public String getApply_state() {
        return apply_state;
    }

    public void setApply_state(String apply_state) {
        this.apply_state = apply_state;
    }

    public String getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(String is_delete) {
        this.is_delete = is_delete;
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

    public String getStaff_audit() {
        return staff_audit;
    }

    public void setStaff_audit(String staff_audit) {
        this.staff_audit = staff_audit;
    }

    public String getStaff_name() {
        return staff_name;
    }

    public void setStaff_name(String staff_name) {
        this.staff_name = staff_name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIs_audit() {
        return is_audit;
    }

    public void setIs_audit(String is_audit) {
        this.is_audit = is_audit;
    }

    public String getApply_state_show() {
        return apply_state_show;
    }

    public void setApply_state_show(String apply_state_show) {
        this.apply_state_show = apply_state_show;
    }

    public String getJob_name() {
        return job_name;
    }

    public void setJob_name(String job_name) {
        this.job_name = job_name;
    }

    public String getLarge_area_name() {
        return large_area_name;
    }

    public void setLarge_area_name(String large_area_name) {
        this.large_area_name = large_area_name;
    }

    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }

    public String getShop_name() {
        return shop_name;
    }

    public void setShop_name(String shop_name) {
        this.shop_name = shop_name;
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

    public String getAll_name() {
        return all_name;
    }

    public void setAll_name(String all_name) {
        this.all_name = all_name;
    }

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

    public List<RecordListBean> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<RecordListBean> recordList) {
        this.recordList = recordList;
    }

    public List<ClerkInfoListBean> getClerkInfoList() {
        return clerkInfoList;
    }

    public void setClerkInfoList(List<ClerkInfoListBean> clerkInfoList) {
        this.clerkInfoList = clerkInfoList;
    }

    public List<ClerkInfoListBean> getPressureInfoList() {
        return pressureInfoList;
    }

    public void setPressureInfoList(List<ClerkInfoListBean> pressureInfoList) {
        this.pressureInfoList = pressureInfoList;
    }



}