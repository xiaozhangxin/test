package com.ak.pt.bean;

import java.io.Serializable;
import java.util.List;

public class QuitJobBean implements Serializable{


    /**
     * quit_id : 26
     * quit_no : 
     * staff_id : 47
     * group_id : 429
     * large_area_id : 427
     * region_id : 428
     * shop_id : 429
     * next_audit_id : 185
     * is_delete : 
     * quit_state : refuse
     * staff_audit : 
     * create_time : 2019-10-18 17:29:53
     * edit_time : 
     * recordList : [{"record_id":"","quit_id":26,"staff_id":47,"staff_name":"试压工lv1","record_state":"wait_audit","record_remark":"","create_time":"2019-10-18 17:29:53","record_state_show":"发出申请"},{"record_id":"","quit_id":26,"staff_id":185,"staff_name":"专员lv1","record_state":"unAuditing","record_remark":"","create_time":"2019-10-18 17:29:53","record_state_show":"审核中"}]
     * job_name_list : []
     * staff_name : 试压工lv1
     * group_name : 东海县李正楠
     * large_area_name : 苏皖大区
     * region_name : 苏北区域
     * shop_name : 东海县李正楠
     * remark : 
     * is_audit : 
     * quit_state_show : 审核拒绝
     * job_name : 
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
     * wyinfoList : [{"info_id":88,"quit_id":26,"staff_id":44,"info_sign":0,"staff_name":"校长十","tel":"16666666666","create_time":"2019-10-18 17:29:53"}]
     * syinfoList : [{"info_id":87,"quit_id":26,"staff_id":295,"info_sign":1,"staff_name":"周经销","tel":"18438552938","create_time":"2019-10-18 17:29:53"}]
     */

    private String quit_id;
    private String quit_no;
    private String staff_id;
    private String group_id;
    private String large_area_id;
    private String region_id;
    private String shop_id;
    private String next_audit_id;
    private String is_delete;
    private String quit_state;
    private String staff_audit;
    private String create_time;
    private String edit_time;
    private String staff_name;
    private String group_name;
    private String large_area_name;
    private String region_name;
    private String shop_name;
    private String remark;
    private String is_audit;
    private String quit_state_show;
    private String job_name;
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
    private List<LeavePerpleBean> wyinfoList;
    private List<LeavePerpleBean> syinfoList;

    public String getQuit_id() {
        return quit_id;
    }

    public void setQuit_id(String quit_id) {
        this.quit_id = quit_id;
    }

    public String getQuit_no() {
        return quit_no;
    }

    public void setQuit_no(String quit_no) {
        this.quit_no = quit_no;
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

    public String getNext_audit_id() {
        return next_audit_id;
    }

    public void setNext_audit_id(String next_audit_id) {
        this.next_audit_id = next_audit_id;
    }

    public String getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(String is_delete) {
        this.is_delete = is_delete;
    }

    public String getQuit_state() {
        return quit_state;
    }

    public void setQuit_state(String quit_state) {
        this.quit_state = quit_state;
    }

    public String getStaff_audit() {
        return staff_audit;
    }

    public void setStaff_audit(String staff_audit) {
        this.staff_audit = staff_audit;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getEdit_time() {
        return edit_time;
    }

    public void setEdit_time(String edit_time) {
        this.edit_time = edit_time;
    }

    public String getStaff_name() {
        return staff_name;
    }

    public void setStaff_name(String staff_name) {
        this.staff_name = staff_name;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
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

    public String getQuit_state_show() {
        return quit_state_show;
    }

    public void setQuit_state_show(String quit_state_show) {
        this.quit_state_show = quit_state_show;
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



    public List<LeavePerpleBean> getWyinfoList() {
        return wyinfoList;
    }

    public void setWyinfoList(List<LeavePerpleBean> wyinfoList) {
        this.wyinfoList = wyinfoList;
    }

    public List<LeavePerpleBean> getSyinfoList() {
        return syinfoList;
    }

    public void setSyinfoList(List<LeavePerpleBean> syinfoList) {
        this.syinfoList = syinfoList;
    }





}
