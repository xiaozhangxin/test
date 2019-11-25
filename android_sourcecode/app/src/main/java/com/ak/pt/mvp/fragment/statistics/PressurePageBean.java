package com.ak.pt.mvp.fragment.statistics;

import com.ak.pt.bean.PhotoListBean;
import com.ak.pt.mvp.fragment.statistics.ResultListBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by admin on 2019/1/9.
 */

public class PressurePageBean implements Serializable {


    /**
     * doc_no : SYBD2019011693915
     * group_no :
     * acc_period :
     * doc_code :
     * staff_no :
     * staff_name :
     * staff_id : 4
     * trn_date : 2019-01-16 15:57:26
     * post_date :
     * start_time :
     * end_time :
     * post_date_start :
     * post_date_end :
     * send_date :
     * if_post : 0
     * if_post_show :
     * flow_state : order
     * address : 把本来
     * area :
     * house_area_name :
     * owner_name :
     * owner_tel :
     * attention :
     * is_presence :
     * scene_contact :
     * scene_contact_tel :
     * declaration_type :
     * decoration_company :
     * decoration_company_tel :
     * project_manager :
     * project_manager_tel :
     * book_time : 2019-01-16 00:00:00
     * attention2 :
     * install_type :
     * pipe_type :
     * pipe_brand :
     * plumber_id :
     * plumber_name :
     * plumber_tel :
     * prospective_customer :
     * member_card :
     * leakage_reason :
     * description :
     * pressure_according :
     * pressure_time :
     * pressure_pressure :
     * pressure_cost_time :
     * authentic :
     * quality_card :
     * satisfactory :
     * suggest :
     * distributor_satisfactory :
     * pressure_complete_time :
     * inspector :
     * give :
     * kitchen :
     * toilet :
     * distributor_name :
     * distributor_tel :
     * hydraulic_tel :
     * integral_tel :
     * hydraulic_name :
     * integral_score :
     * balcony :
     * invoice_state :
     * pressure_code :
     * project_manager_score :
     * hydraulic_score :
     * pressure_type :
     * pipe_model :
     * pipe_trend :
     * spool_type :
     * address_type :
     * is_photos : 未上传
     * is_weixin : 0
     * weixin_address :
     * is_assign_pressure : 0
     * owner_weixin_id :
     * photo_count : 0
     * owner_sign :
     * weixin_open_id :
     *
     * edit_time : 2019-01-16 15:57:26
     * is_delete : 0
     * fileList : []
     * resultList : []
     * photoList : []
     * flow_state_show : 未发出
     * staff_uuid :
     * group_parent_uuid :
     * group_uuid :
     * pressure_show :
     * province :
     * county :
     * audit_start_time :
     * audit_end_time :
     * result_remark :
     */


    private String doc_no;
    private String tds;
    private String group_no;
    private String acc_period;
    private String doc_code;
    private String water_type;

    private String is_care_water;
    private String is_qualified;
    private String staff_no;
    private String staff_name;
    private String staff_id;
    private String trn_date;
    private String post_date;
    private String start_time;
    private String end_time;
    private String post_date_start;
    private String post_date_end;
    private String send_date;
    private String if_post;
    private String if_post_show;
    private String flow_state;
    private String address;
    private String area;
    private String house_area_name;
    private String owner_name;
    private String owner_tel;
    private String attention;
    private String is_presence;
    private String scene_contact;
    private String scene_contact_tel;
    private String declaration_type;
    private String decoration_company;
    private String decoration_company_tel;
    private String project_manager;
    private String project_manager_tel;
    private String book_time;
    private String attention2;
    private String install_type;
    private String pipe_type;
    private String pipe_brand;
    private String plumber_id;
    private String plumber_name;
    private String plumber_tel;
    private String prospective_customer;
    private String member_card;
    private String leakage_reason;
    private String description;
    private String pressure_according;

    private String pressure_time;
    private String pressure_pressure;
    private String pressure_cost_time;
    private String authentic;
    private String quality_card;
    private String satisfactory;
    private String suggest;
    private String distributor_satisfactory;
    private String pressure_complete_time;
    private String inspector;
    private String give;
    private String kitchen;
    private String toilet;
    private String distributor_name;
    private String distributor_tel;
    private String hydraulic_tel;
    private String integral_tel;
    private String hydraulic_name;
    private String integral_score;
    private String balcony;
    private String invoice_state;
    private String pressure_code;
    private String project_manager_score;
    private String hydraulic_score;
    private String pressure_type;
    private String pipe_model;
    private String pipe_trend;
    private String spool_type;
    private String address_type;
    private String is_photos;
    private String is_weixin;
    private String weixin_address;
    private String is_assign_pressure;
    private String owner_weixin_id;
    private String photo_count;
    private String owner_sign;
    private String edit_time;
    private String weixin_open_id;
    private String is_delete;
    private String flow_state_show;
    private String staff_uuid;
    private String group_parent_uuid;
    private String group_uuid;
    private String pressure_show;
    private String province;
    private String county;
    private String audit_start_time;
    private String audit_end_time;
    private String result_remark;
    private String pressure_drop;
    private String pressure_start_value;
    private String pressure_end_value;
    private String pressure_result;
    private String house_area_no;
    private String door_packet;
    private String special_event;
    private String pressure_tool;
    private String last_pressure;//最终压力
    private String press_time;//第二阶段 试压时间（分钟）


    private String area_id;//所属区域
    private String assurance_card;//质保卡类型

    public String getAssurance_card() {
        return assurance_card;
    }

    public void setAssurance_card(String assurance_card) {
        this.assurance_card = assurance_card;
    }

    public String getArea_id() {
        return area_id;
    }

    public void setArea_id(String area_id) {
        this.area_id = area_id;
    }

    public String getLast_pressure() {
        return last_pressure;
    }

    public void setLast_pressure(String last_pressure) {
        this.last_pressure = last_pressure;
    }

    public String getPress_time() {
        return press_time;
    }

    public void setPress_time(String press_time) {
        this.press_time = press_time;
    }

    public String getWater_type() {
        return water_type;
    }

    public void setWater_type(String water_type) {
        this.water_type = water_type;
    }

    public String getTds() {
        return tds;
    }

    public void setTds(String tds) {
        this.tds = tds;
    }

    public String getPressure_tool() {
        return pressure_tool;
    }

    public void setPressure_tool(String pressure_tool) {
        this.pressure_tool = pressure_tool;
    }

    public String getSpecial_event() {
        return special_event;
    }

    public void setSpecial_event(String special_event) {
        this.special_event = special_event;
    }

    public String getDoor_packet() {
        return door_packet;
    }

    public void setDoor_packet(String door_packet) {
        this.door_packet = door_packet;
    }

    public String getHydraulic_add() {
        return hydraulic_add;
    }

    public void setHydraulic_add(String hydraulic_add) {
        this.hydraulic_add = hydraulic_add;
    }

    private String serve_type;
    private String hydraulic_add;

    public String getServe_type() {
        return serve_type;
    }

    public void setServe_type(String serve_type) {
        this.serve_type = serve_type;
    }

    public String getHouse_area_no() {
        return house_area_no;
    }

    public void setHouse_area_no(String house_area_no) {
        this.house_area_no = house_area_no;
    }

    public String getPressure_result() {
        return pressure_result;
    }

    public void setPressure_result(String pressure_result) {
        this.pressure_result = pressure_result;
    }

    public String getPressure_start_value() {
        return pressure_start_value;
    }

    public void setPressure_start_value(String pressure_start_value) {
        this.pressure_start_value = pressure_start_value;
    }

    public String getPressure_end_value() {
        return pressure_end_value;
    }

    public void setPressure_end_value(String pressure_end_value) {
        this.pressure_end_value = pressure_end_value;
    }

    public String getPressure_drop() {
        return pressure_drop;
    }

    public void setPressure_drop(String pressure_drop) {
        this.pressure_drop = pressure_drop;
    }

    /*  private List<?> fileList;*/
    private List<ResultListBean> resultList;

    public List<ResultListBean> getResultList() {
        return resultList;
    }

    public void setResultList(List<ResultListBean> resultList) {
        this.resultList = resultList;
    }

    private List<PhotoListBean> photoList;

    public String getDoc_no() {
        return doc_no;
    }

    public void setDoc_no(String doc_no) {
        this.doc_no = doc_no;
    }

    public String getGroup_no() {
        return group_no;
    }

    public void setGroup_no(String group_no) {
        this.group_no = group_no;
    }

    public String getAcc_period() {
        return acc_period;
    }

    public void setAcc_period(String acc_period) {
        this.acc_period = acc_period;
    }

    public String getDoc_code() {
        return doc_code;
    }

    public void setDoc_code(String doc_code) {
        this.doc_code = doc_code;
    }

    public String getStaff_no() {
        return staff_no;
    }

    public void setStaff_no(String staff_no) {
        this.staff_no = staff_no;
    }

    public String getStaff_name() {
        return staff_name;
    }

    public void setStaff_name(String staff_name) {
        this.staff_name = staff_name;
    }


    public String getTrn_date() {
        return trn_date;
    }

    public void setTrn_date(String trn_date) {
        this.trn_date = trn_date;
    }

    public String getPost_date() {
        return post_date;
    }

    public void setPost_date(String post_date) {
        this.post_date = post_date;
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

    public String getPost_date_start() {
        return post_date_start;
    }

    public void setPost_date_start(String post_date_start) {
        this.post_date_start = post_date_start;
    }

    public String getPost_date_end() {
        return post_date_end;
    }

    public void setPost_date_end(String post_date_end) {
        this.post_date_end = post_date_end;
    }

    public String getSend_date() {
        return send_date;
    }

    public void setSend_date(String send_date) {
        this.send_date = send_date;
    }

    public String getIf_post() {
        return if_post;
    }

    public void setIf_post(String if_post) {
        this.if_post = if_post;
    }

    public String getIf_post_show() {
        return if_post_show;
    }

    public void setIf_post_show(String if_post_show) {
        this.if_post_show = if_post_show;
    }

    public String getFlow_state() {
        return flow_state;
    }

    public void setFlow_state(String flow_state) {
        this.flow_state = flow_state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getHouse_area_name() {
        return house_area_name;
    }

    public void setHouse_area_name(String house_area_name) {
        this.house_area_name = house_area_name;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getOwner_tel() {
        return owner_tel;
    }

    public void setOwner_tel(String owner_tel) {
        this.owner_tel = owner_tel;
    }

    public String getAttention() {
        return attention;
    }

    public void setAttention(String attention) {
        this.attention = attention;
    }

    public String getIs_presence() {
        return is_presence;
    }

    public void setIs_presence(String is_presence) {
        this.is_presence = is_presence;
    }

    public String getScene_contact() {
        return scene_contact;
    }

    public void setScene_contact(String scene_contact) {
        this.scene_contact = scene_contact;
    }

    public String getScene_contact_tel() {
        return scene_contact_tel;
    }

    public void setScene_contact_tel(String scene_contact_tel) {
        this.scene_contact_tel = scene_contact_tel;
    }

    public String getDeclaration_type() {
        return declaration_type;
    }

    public void setDeclaration_type(String declaration_type) {
        this.declaration_type = declaration_type;
    }

    public String getDecoration_company() {
        return decoration_company;
    }

    public void setDecoration_company(String decoration_company) {
        this.decoration_company = decoration_company;
    }

    public String getDecoration_company_tel() {
        return decoration_company_tel;
    }

    public void setDecoration_company_tel(String decoration_company_tel) {
        this.decoration_company_tel = decoration_company_tel;
    }

    public String getProject_manager() {
        return project_manager;
    }

    public void setProject_manager(String project_manager) {
        this.project_manager = project_manager;
    }

    public String getProject_manager_tel() {
        return project_manager_tel;
    }

    public void setProject_manager_tel(String project_manager_tel) {
        this.project_manager_tel = project_manager_tel;
    }

    public String getBook_time() {
        return book_time;
    }

    public void setBook_time(String book_time) {
        this.book_time = book_time;
    }

    public String getAttention2() {
        return attention2;
    }

    public void setAttention2(String attention2) {
        this.attention2 = attention2;
    }

    public String getInstall_type() {
        return install_type;
    }

    public void setInstall_type(String install_type) {
        this.install_type = install_type;
    }

    public String getPipe_type() {
        return pipe_type;
    }

    public void setPipe_type(String pipe_type) {
        this.pipe_type = pipe_type;
    }

    public String getPipe_brand() {
        return pipe_brand;
    }

    public void setPipe_brand(String pipe_brand) {
        this.pipe_brand = pipe_brand;
    }

    public String getPlumber_id() {
        return plumber_id;
    }

    public void setPlumber_id(String plumber_id) {
        this.plumber_id = plumber_id;
    }

    public String getPlumber_name() {
        return plumber_name;
    }

    public void setPlumber_name(String plumber_name) {
        this.plumber_name = plumber_name;
    }

    public String getPlumber_tel() {
        return plumber_tel;
    }

    public void setPlumber_tel(String plumber_tel) {
        this.plumber_tel = plumber_tel;
    }

    public String getProspective_customer() {
        return prospective_customer;
    }

    public void setProspective_customer(String prospective_customer) {
        this.prospective_customer = prospective_customer;
    }

    public String getMember_card() {
        return member_card;
    }

    public void setMember_card(String member_card) {
        this.member_card = member_card;
    }

    public String getLeakage_reason() {
        return leakage_reason;
    }

    public void setLeakage_reason(String leakage_reason) {
        this.leakage_reason = leakage_reason;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPressure_according() {
        return pressure_according;
    }

    public void setPressure_according(String pressure_according) {
        this.pressure_according = pressure_according;
    }

    public String getPressure_time() {
        return pressure_time;
    }

    public void setPressure_time(String pressure_time) {
        this.pressure_time = pressure_time;
    }

    public String getPressure_pressure() {
        return pressure_pressure;
    }

    public void setPressure_pressure(String pressure_pressure) {
        this.pressure_pressure = pressure_pressure;
    }

    public String getPressure_cost_time() {
        return pressure_cost_time;
    }

    public void setPressure_cost_time(String pressure_cost_time) {
        this.pressure_cost_time = pressure_cost_time;
    }

    public String getAuthentic() {
        return authentic;
    }

    public void setAuthentic(String authentic) {
        this.authentic = authentic;
    }

    public String getQuality_card() {
        return quality_card;
    }

    public void setQuality_card(String quality_card) {
        this.quality_card = quality_card;
    }

    public String getSatisfactory() {
        return satisfactory;
    }

    public void setSatisfactory(String satisfactory) {
        this.satisfactory = satisfactory;
    }

    public String getSuggest() {
        return suggest;
    }

    public void setSuggest(String suggest) {
        this.suggest = suggest;
    }

    public String getDistributor_satisfactory() {
        return distributor_satisfactory;
    }

    public void setDistributor_satisfactory(String distributor_satisfactory) {
        this.distributor_satisfactory = distributor_satisfactory;
    }

    public String getPressure_complete_time() {
        return pressure_complete_time;
    }

    public void setPressure_complete_time(String pressure_complete_time) {
        this.pressure_complete_time = pressure_complete_time;
    }

    public String getInspector() {
        return inspector;
    }

    public void setInspector(String inspector) {
        this.inspector = inspector;
    }

    public String getGive() {
        return give;
    }

    public void setGive(String give) {
        this.give = give;
    }

    public String getKitchen() {
        return kitchen;
    }

    public void setKitchen(String kitchen) {
        this.kitchen = kitchen;
    }

    public String getToilet() {
        return toilet;
    }

    public void setToilet(String toilet) {
        this.toilet = toilet;
    }

    public String getDistributor_name() {
        return distributor_name;
    }

    public void setDistributor_name(String distributor_name) {
        this.distributor_name = distributor_name;
    }

    public String getDistributor_tel() {
        return distributor_tel;
    }

    public void setDistributor_tel(String distributor_tel) {
        this.distributor_tel = distributor_tel;
    }

    public String getHydraulic_tel() {
        return hydraulic_tel;
    }

    public void setHydraulic_tel(String hydraulic_tel) {
        this.hydraulic_tel = hydraulic_tel;
    }

    public String getIntegral_tel() {
        return integral_tel;
    }

    public void setIntegral_tel(String integral_tel) {
        this.integral_tel = integral_tel;
    }

    public String getHydraulic_name() {
        return hydraulic_name;
    }

    public void setHydraulic_name(String hydraulic_name) {
        this.hydraulic_name = hydraulic_name;
    }

    public String getIntegral_score() {
        return integral_score;
    }

    public void setIntegral_score(String integral_score) {
        this.integral_score = integral_score;
    }

    public String getBalcony() {
        return balcony;
    }

    public void setBalcony(String balcony) {
        this.balcony = balcony;
    }

    public String getInvoice_state() {
        return invoice_state;
    }

    public void setInvoice_state(String invoice_state) {
        this.invoice_state = invoice_state;
    }

    public String getPressure_code() {
        return pressure_code;
    }

    public void setPressure_code(String pressure_code) {
        this.pressure_code = pressure_code;
    }

    public String getProject_manager_score() {
        return project_manager_score;
    }

    public void setProject_manager_score(String project_manager_score) {
        this.project_manager_score = project_manager_score;
    }

    public String getHydraulic_score() {
        return hydraulic_score;
    }

    public void setHydraulic_score(String hydraulic_score) {
        this.hydraulic_score = hydraulic_score;
    }

    public String getPressure_type() {
        return pressure_type;
    }

    public void setPressure_type(String pressure_type) {
        this.pressure_type = pressure_type;
    }

    public String getPipe_model() {
        return pipe_model;
    }

    public void setPipe_model(String pipe_model) {
        this.pipe_model = pipe_model;
    }

    public String getPipe_trend() {
        return pipe_trend;
    }

    public void setPipe_trend(String pipe_trend) {
        this.pipe_trend = pipe_trend;
    }

    public String getSpool_type() {
        return spool_type;
    }

    public void setSpool_type(String spool_type) {
        this.spool_type = spool_type;
    }

    public String getAddress_type() {
        return address_type;
    }

    public void setAddress_type(String address_type) {
        this.address_type = address_type;
    }

    public String getIs_photos() {
        return is_photos;
    }

    public void setIs_photos(String is_photos) {
        this.is_photos = is_photos;
    }

    public String getIs_weixin() {
        return is_weixin;
    }

    public void setIs_weixin(String is_weixin) {
        this.is_weixin = is_weixin;
    }

    public String getWeixin_address() {
        return weixin_address;
    }

    public void setWeixin_address(String weixin_address) {
        this.weixin_address = weixin_address;
    }

    public String getIs_assign_pressure() {
        return is_assign_pressure;
    }

    public void setIs_assign_pressure(String is_assign_pressure) {
        this.is_assign_pressure = is_assign_pressure;
    }

    public String getOwner_weixin_id() {
        return owner_weixin_id;
    }

    public void setOwner_weixin_id(String owner_weixin_id) {
        this.owner_weixin_id = owner_weixin_id;
    }


    public String getOwner_sign() {
        return owner_sign;
    }

    public void setOwner_sign(String owner_sign) {
        this.owner_sign = owner_sign;
    }

    public String getEdit_time() {
        return edit_time;
    }

    public void setEdit_time(String edit_time) {
        this.edit_time = edit_time;
    }

    public String getWeixin_open_id() {
        return weixin_open_id;
    }

    public void setWeixin_open_id(String weixin_open_id) {
        this.weixin_open_id = weixin_open_id;
    }

    public String getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(String is_delete) {
        this.is_delete = is_delete;
    }

    public String getFlow_state_show() {
        return flow_state_show;
    }

    public void setFlow_state_show(String flow_state_show) {
        this.flow_state_show = flow_state_show;
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

    public String getPressure_show() {
        return pressure_show;
    }

    public void setPressure_show(String pressure_show) {
        this.pressure_show = pressure_show;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getAudit_start_time() {
        return audit_start_time;
    }

    public void setAudit_start_time(String audit_start_time) {
        this.audit_start_time = audit_start_time;
    }

    public String getAudit_end_time() {
        return audit_end_time;
    }

    public void setAudit_end_time(String audit_end_time) {
        this.audit_end_time = audit_end_time;
    }

    public String getResult_remark() {
        return result_remark;
    }

    public void setResult_remark(String result_remark) {
        this.result_remark = result_remark;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public String getPhoto_count() {
        return photo_count;
    }

    public void setPhoto_count(String photo_count) {
        this.photo_count = photo_count;
    }


    public List<PhotoListBean> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<PhotoListBean> photoList) {
        this.photoList = photoList;
    }

    public String getIs_care_water() {
        return is_care_water;
    }

    public void setIs_care_water(String is_care_water) {
        this.is_care_water = is_care_water;
    }

    public String getIs_qualified() {
        return is_qualified;
    }

    public void setIs_qualified(String is_qualified) {
        this.is_qualified = is_qualified;
    }
}
