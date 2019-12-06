package com.akan.qf.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2018/11/16.
 */

public class VisitorBean implements Serializable{

    private List<StaffSignUnionBean> staffSignUnionList;

    public List<StaffSignUnionBean> getStaffSignUnionList() {
        return staffSignUnionList;
    }

    public void setStaffSignUnionList(List<StaffSignUnionBean> staffSignUnionList) {
        this.staffSignUnionList = staffSignUnionList;
    }
    public String getNext_audit_staff_name() {
        return next_audit_staff_name;
    }

    public void setNext_audit_staff_name(String next_audit_staff_name) {
        this.next_audit_staff_name = next_audit_staff_name;
    }

    public String getNext_audit_staff_head_img() {
        return next_audit_staff_head_img;
    }

    public void setNext_audit_staff_head_img(String next_audit_staff_head_img) {
        this.next_audit_staff_head_img = next_audit_staff_head_img;
    }
    public String getJob_name() {
        return job_name;
    }

    public void setJob_name(String job_name) {
        this.job_name = job_name;
    }
    private String staff_sign_name;
    private String job_name;
    private String next_audit_staff_name;
    private String next_audit_staff_head_img;

    public String getStaff_sign_name() {
        return staff_sign_name;
    }

    public void setStaff_sign_name(String staff_sign_name) {
        this.staff_sign_name = staff_sign_name;
    }

    /**
     * apply_id : 6
     * apply_no : KFD2018111686159
     * staff_id : 54
     * staff_name : 小弟
     * staff_department : 爱康企业集团（上海）有限公司/南方营销中心/苏皖区域/皖北区域
     * apply_department : 爱康企业集团（上海）有限公司/南方营销中心/苏皖区域/皖北区域
     * apply_name :
     * apply_number : 10
     * apply_result :
     * apply_led : use
     * apply_text : 欢迎各位光临
     * apply_area : 一楼展厅,生产车间,实验室,仓库,
     * apply_need : 需要一个解说员
     * apply_car : use
     * apply_home : 6楼会议室一（小）,6楼会议室二（小）,6楼圆桌会议室,
     * apply_goods : 笔记本,投影仪,话筒,白板、白板笔、白报纸,茶水,矿泉水,水果,茶歇,宣传册,
     * apply_other : 暂无
     * apply_food : 外部安排就餐
     * apply_room : 公司不安排住宿
     * apply_sign : 石磊
     * apply_remark : 请尽快审核
     * next_staff_id : 52
     * record_no : 0
     * apply_state : wait_audit
     * apply_is_delete : 0
     * apply_time : 2018-11-16 00:00:00
     * visit_time : 2018-11-17 00:00:00
     * apply_update_time : 2018-11-16 11:49:08
     * apply_create_time : 2018-11-16 11:49:08
     * recordList : []
     * apply_led_show :
     * apply_area_show :
     * apply_car_show :
     * apply_home_show :
     * apply_goods_show :
     * apply_food_show :
     * apply_room_show :
     * apply_state_show : 待审核
     * start_time :
     * end_time :
     * staff_uuid :
     * group_parent_uuid :
     * group_uuid :
     * is_egis :
     */

    private String apply_id;
    private String apply_no;
    private String staff_id;
    private String staff_name;
    private String staff_department;
    private String apply_department;
    private String apply_name;
    private String apply_number;
    private String apply_result;
    private String apply_led;
    private String apply_text;
    private String apply_area;
    private String apply_need;
    private String apply_car;
    private String apply_home;
    private String apply_goods;
    private String apply_other;
    private String apply_food;
    private String apply_room;
    private String apply_sign;
    private String apply_remark;
    private String next_staff_id;
    private String record_no;
    private String apply_state;
    private String apply_is_delete;
    private String apply_time;
    private String visit_time;
    private String apply_update_time;
    private String apply_create_time;
    private String apply_led_show;
    private String apply_area_show;
    private String apply_car_show;
    private String apply_home_show;
    private String apply_goods_show;
    private String apply_food_show;
    private String apply_room_show;
    private String apply_state_show;
    private String start_time;
    private String end_time;
    private String staff_uuid;
    private String group_parent_uuid;
    private String group_uuid;
    private String is_egis;
    private List<RecordListBean> recordList;


    public String getApply_no() {
        return apply_no;
    }

    public void setApply_no(String apply_no) {
        this.apply_no = apply_no;
    }


    public String getStaff_name() {
        return staff_name;
    }

    public void setStaff_name(String staff_name) {
        this.staff_name = staff_name;
    }

    public String getStaff_department() {
        return staff_department;
    }

    public void setStaff_department(String staff_department) {
        this.staff_department = staff_department;
    }

    public String getApply_department() {
        return apply_department;
    }

    public void setApply_department(String apply_department) {
        this.apply_department = apply_department;
    }

    public String getApply_name() {
        return apply_name;
    }

    public void setApply_name(String apply_name) {
        this.apply_name = apply_name;
    }

    public String getApply_number() {
        return apply_number;
    }

    public void setApply_number(String apply_number) {
        this.apply_number = apply_number;
    }

    public String getApply_result() {
        return apply_result;
    }

    public void setApply_result(String apply_result) {
        this.apply_result = apply_result;
    }

    public String getApply_led() {
        return apply_led;
    }

    public void setApply_led(String apply_led) {
        this.apply_led = apply_led;
    }

    public String getApply_text() {
        return apply_text;
    }

    public void setApply_text(String apply_text) {
        this.apply_text = apply_text;
    }

    public String getApply_area() {
        return apply_area;
    }

    public void setApply_area(String apply_area) {
        this.apply_area = apply_area;
    }

    public String getApply_need() {
        return apply_need;
    }

    public void setApply_need(String apply_need) {
        this.apply_need = apply_need;
    }

    public String getApply_car() {
        return apply_car;
    }

    public void setApply_car(String apply_car) {
        this.apply_car = apply_car;
    }

    public String getApply_home() {
        return apply_home;
    }

    public void setApply_home(String apply_home) {
        this.apply_home = apply_home;
    }

    public String getApply_goods() {
        return apply_goods;
    }

    public void setApply_goods(String apply_goods) {
        this.apply_goods = apply_goods;
    }

    public String getApply_other() {
        return apply_other;
    }

    public void setApply_other(String apply_other) {
        this.apply_other = apply_other;
    }

    public String getApply_food() {
        return apply_food;
    }

    public void setApply_food(String apply_food) {
        this.apply_food = apply_food;
    }

    public String getApply_room() {
        return apply_room;
    }

    public void setApply_room(String apply_room) {
        this.apply_room = apply_room;
    }

    public String getApply_sign() {
        return apply_sign;
    }

    public void setApply_sign(String apply_sign) {
        this.apply_sign = apply_sign;
    }

    public String getApply_remark() {
        return apply_remark;
    }

    public void setApply_remark(String apply_remark) {
        this.apply_remark = apply_remark;
    }

    public String getApply_state() {
        return apply_state;
    }

    public void setApply_state(String apply_state) {
        this.apply_state = apply_state;
    }

    public String getApply_is_delete() {
        return apply_is_delete;
    }

    public void setApply_is_delete(String apply_is_delete) {
        this.apply_is_delete = apply_is_delete;
    }

    public String getApply_time() {
        return apply_time;
    }

    public void setApply_time(String apply_time) {
        this.apply_time = apply_time;
    }

    public String getVisit_time() {
        return visit_time;
    }

    public void setVisit_time(String visit_time) {
        this.visit_time = visit_time;
    }

    public String getApply_update_time() {
        return apply_update_time;
    }

    public void setApply_update_time(String apply_update_time) {
        this.apply_update_time = apply_update_time;
    }

    public String getApply_create_time() {
        return apply_create_time;
    }

    public void setApply_create_time(String apply_create_time) {
        this.apply_create_time = apply_create_time;
    }

    public String getApply_led_show() {
        return apply_led_show;
    }

    public void setApply_led_show(String apply_led_show) {
        this.apply_led_show = apply_led_show;
    }

    public String getApply_area_show() {
        return apply_area_show;
    }

    public void setApply_area_show(String apply_area_show) {
        this.apply_area_show = apply_area_show;
    }

    public String getApply_car_show() {
        return apply_car_show;
    }

    public void setApply_car_show(String apply_car_show) {
        this.apply_car_show = apply_car_show;
    }

    public String getApply_home_show() {
        return apply_home_show;
    }

    public void setApply_home_show(String apply_home_show) {
        this.apply_home_show = apply_home_show;
    }

    public String getApply_goods_show() {
        return apply_goods_show;
    }

    public void setApply_goods_show(String apply_goods_show) {
        this.apply_goods_show = apply_goods_show;
    }

    public String getApply_food_show() {
        return apply_food_show;
    }

    public void setApply_food_show(String apply_food_show) {
        this.apply_food_show = apply_food_show;
    }

    public String getApply_room_show() {
        return apply_room_show;
    }

    public void setApply_room_show(String apply_room_show) {
        this.apply_room_show = apply_room_show;
    }

    public String getApply_state_show() {
        return apply_state_show;
    }

    public void setApply_state_show(String apply_state_show) {
        this.apply_state_show = apply_state_show;
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

    public String getGroup_uuid() {
        return group_uuid;
    }

    public void setGroup_uuid(String group_uuid) {
        this.group_uuid = group_uuid;
    }

    public String getIs_egis() {
        return is_egis;
    }

    public String getNext_staff_id() {
        return next_staff_id;
    }

    public void setNext_staff_id(String next_staff_id) {
        this.next_staff_id = next_staff_id;
    }

    public String getRecord_no() {
        return record_no;
    }

    public void setRecord_no(String record_no) {
        this.record_no = record_no;
    }

    public List<RecordListBean> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<RecordListBean> recordList) {
        this.recordList = recordList;
    }

    public void setIs_egis(String is_egis) {
        this.is_egis = is_egis;
    }

    public String getApply_id() {
        return apply_id;
    }

    public void setApply_id(String apply_id) {
        this.apply_id = apply_id;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }
}
