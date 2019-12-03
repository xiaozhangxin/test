package com.akan.wms.bean;

import java.io.Serializable;
import java.util.List;

public class MiscShipBean implements Serializable {


    /**
     * ship_id : 50
     * ship_no : SCLL2019071779745
     * staff_id : 6003
     * memo : 备注一下
     * doc_no : 1001512200010027
     * account_period : {}
     * wh_man : 1001712281047821
     * benefit_org : 1001512200010027
     * pub_desc_seg2 : 1001601050164782
     * pub_desc_seg3 :
     * pub_desc_seg1 : 1001601050164783
     * ship_org : 1001512200010027
     * is_delete : 0
     * distribution :
     * ex_warehouse :
     * ship_status : 0
     * create_time : 2019-07-17 11:40:46
     * edit_time : 2019-07-17 11:40:46
     * benefit_dept : {}
     * infoList : [{"info_id":88,"info_wh_id":1001512260168343,"ship_id":50,"item_id":1001812180044580,"number":20,"benefit_dept":{},"pub_desc_seg2":"3","pub_desc_seg3":"4","pub_desc_seg1":"2","cost_num":20,"info_num":2,"benefit_org_name":"","pub_desc_seg2_name":"","pub_desc_seg3_name":"营销中心","pub_desc_seg1_name":"","create_time":"2019-07-17 11:41:41","codeList":[],"info_wh_name":"材料仓","info_spec":"","info_name":"PPR主料\u2014绿色混配料"},{"info_id":89,"info_wh_id":1001512260168343,"ship_id":50,"item_id":1001812180044822,"number":30,"benefit_dept":{},"pub_desc_seg2":"3","pub_desc_seg3":"4","pub_desc_seg1":"2","cost_num":30,"info_num":2,"benefit_org_name":"","pub_desc_seg2_name":"","pub_desc_seg3_name":"营销中心","pub_desc_seg1_name":"","create_time":"2019-07-17 11:41:42","codeList":[],"info_wh_name":"材料仓","info_spec":"","info_name":"PPR主料\u2014黄色混配料"},{"info_id":90,"info_wh_id":1001512260168343,"ship_id":50,"item_id":1001812180045064,"number":40,"benefit_dept":{},"pub_desc_seg2":"3","pub_desc_seg3":"4","pub_desc_seg1":"2","cost_num":40,"info_num":2,"benefit_org_name":"","pub_desc_seg2_name":"","pub_desc_seg3_name":"营销中心","pub_desc_seg1_name":"","create_time":"2019-07-17 11:41:42","codeList":[],"info_wh_name":"材料仓","info_spec":"","info_name":"PPR主料\u2014灰黄色混配料"}]
     * ship_status_show : 未处理
     * staff_name : 文云会
     * benefit_dept_name :
     * benefit_org_name :
     * pub_desc_seg2_name :
     * pub_desc_seg3_name :
     * pub_desc_seg1_name :
     * distribution_name :
     * ex_warehouse_name :
     * wh_man_name : 陈小珺
     * doc_no_name :
     * start_time :
     * end_time :
     */

    private String ship_id;
    private String ship_no;
    private String staff_id;
    private String memo;

    private Object wh_man;
    private Object doc_no;
    private Object benefit_org;
    private String pub_desc_seg2;
    private String pub_desc_seg3;
    private String pub_desc_seg1;
    private String ship_org;
    private String is_delete;
    private String distribution;
    private String ex_warehouse;
    private String ship_status;
    private String create_time;
    private String edit_time;
    private String ship_status_show;
    private String staff_name;
    private String benefit_dept_name;
    private String benefit_org_name;
    private String pub_desc_seg2_name;
    private String pub_desc_seg3_name;
    private String pub_desc_seg1_name;
    private String distribution_name;
    private String ex_warehouse_name;
    private String wh_man_name;
    private String doc_no_name;
    private String start_time;
    private List<RecordsBean> recordsBeans;

    public List<RecordsBean> getRecordsBeans() {
        return recordsBeans;
    }

    public void setRecordsBeans(List<RecordsBean> recordsBeans) {
        this.recordsBeans = recordsBeans;
    }

    public List<BarBean> getBarList() {
        return barList;
    }

    public void setBarList(List<BarBean> barList) {
        this.barList = barList;
    }

    private String is_valid;
    private List<BarBean> barList;
    public String getIs_valid() {
        return is_valid;
    }

    public void setIs_valid(String is_valid) {
        this.is_valid = is_valid;
    }

    private String end_time;
    private List<InforListBean> infoList;

    public String getShip_id() {
        return ship_id;
    }

    public void setShip_id(String ship_id) {
        this.ship_id = ship_id;
    }

    public String getShip_no() {
        return ship_no;
    }

    public void setShip_no(String ship_no) {
        this.ship_no = ship_no;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Object getDoc_no() {
        return doc_no;
    }

    public void setDoc_no(Object doc_no) {
        this.doc_no = doc_no;
    }

    public Object getBenefit_org() {
        return benefit_org;
    }

    public void setBenefit_org(Object benefit_org) {
        this.benefit_org = benefit_org;
    }

    public String getShip_org() {
        return ship_org;
    }

    public void setShip_org(String ship_org) {
        this.ship_org = ship_org;
    }

    public Object getWh_man() {
        return wh_man;
    }

    public void setWh_man(Object wh_man) {
        this.wh_man = wh_man;
    }

    public String getPub_desc_seg2() {
        return pub_desc_seg2;
    }

    public void setPub_desc_seg2(String pub_desc_seg2) {
        this.pub_desc_seg2 = pub_desc_seg2;
    }

    public String getPub_desc_seg3() {
        return pub_desc_seg3;
    }

    public void setPub_desc_seg3(String pub_desc_seg3) {
        this.pub_desc_seg3 = pub_desc_seg3;
    }

    public String getPub_desc_seg1() {
        return pub_desc_seg1;
    }

    public void setPub_desc_seg1(String pub_desc_seg1) {
        this.pub_desc_seg1 = pub_desc_seg1;
    }



    public String getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(String is_delete) {
        this.is_delete = is_delete;
    }

    public String getDistribution() {
        return distribution;
    }

    public void setDistribution(String distribution) {
        this.distribution = distribution;
    }

    public String getEx_warehouse() {
        return ex_warehouse;
    }

    public void setEx_warehouse(String ex_warehouse) {
        this.ex_warehouse = ex_warehouse;
    }

    public String getShip_status() {
        return ship_status;
    }

    public void setShip_status(String ship_status) {
        this.ship_status = ship_status;
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


    public String getShip_status_show() {
        return ship_status_show;
    }

    public void setShip_status_show(String ship_status_show) {
        this.ship_status_show = ship_status_show;
    }

    public String getStaff_name() {
        return staff_name;
    }

    public void setStaff_name(String staff_name) {
        this.staff_name = staff_name;
    }

    public String getBenefit_dept_name() {
        return benefit_dept_name;
    }

    public void setBenefit_dept_name(String benefit_dept_name) {
        this.benefit_dept_name = benefit_dept_name;
    }

    public String getBenefit_org_name() {
        return benefit_org_name;
    }

    public void setBenefit_org_name(String benefit_org_name) {
        this.benefit_org_name = benefit_org_name;
    }

    public String getPub_desc_seg2_name() {
        return pub_desc_seg2_name;
    }

    public void setPub_desc_seg2_name(String pub_desc_seg2_name) {
        this.pub_desc_seg2_name = pub_desc_seg2_name;
    }

    public String getPub_desc_seg3_name() {
        return pub_desc_seg3_name;
    }

    public void setPub_desc_seg3_name(String pub_desc_seg3_name) {
        this.pub_desc_seg3_name = pub_desc_seg3_name;
    }

    public String getPub_desc_seg1_name() {
        return pub_desc_seg1_name;
    }

    public void setPub_desc_seg1_name(String pub_desc_seg1_name) {
        this.pub_desc_seg1_name = pub_desc_seg1_name;
    }

    public String getDistribution_name() {
        return distribution_name;
    }

    public void setDistribution_name(String distribution_name) {
        this.distribution_name = distribution_name;
    }

    public String getEx_warehouse_name() {
        return ex_warehouse_name;
    }

    public void setEx_warehouse_name(String ex_warehouse_name) {
        this.ex_warehouse_name = ex_warehouse_name;
    }

    public String getWh_man_name() {
        return wh_man_name;
    }

    public void setWh_man_name(String wh_man_name) {
        this.wh_man_name = wh_man_name;
    }

    public String getDoc_no_name() {
        return doc_no_name;
    }

    public void setDoc_no_name(String doc_no_name) {
        this.doc_no_name = doc_no_name;
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

    public List<InforListBean> getInfoList() {
        return infoList;
    }

    public void setInfoList(List<InforListBean> infoList) {
        this.infoList = infoList;
    }


}
