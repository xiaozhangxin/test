package com.akan.wms.bean;

import java.io.Serializable;

public class InfoBeanListBean implements Serializable{


    /**
     * item_id : 料品id
     * number : 领取量
     * benefit_dept : 使用部门
     * pub_desc_seg2 : 使用中心
     * pub_desc_seg3 : 负责部门
     * pub_desc_seg1 : 负责中心
     * cost_num : 3
     * info_wh_id : 仓储id
     * info_num : int配料数据
     */

    private String item_id;
    private String number;
    private String benefit_dept;
    private String pub_desc_seg2;
    private String pub_desc_seg3;
    private String pub_desc_seg1;
    private String cost_num;
    private String info_wh_id;
    private String info_num;
    private String mark_code;

    public String getMark_code() {
        return mark_code;
    }

    public void setMark_code(String mark_code) {
        this.mark_code = mark_code;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBenefit_dept() {
        return benefit_dept;
    }

    public void setBenefit_dept(String benefit_dept) {
        this.benefit_dept = benefit_dept;
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

    public String getCost_num() {
        return cost_num;
    }

    public void setCost_num(String cost_num) {
        this.cost_num = cost_num;
    }

    public String getInfo_wh_id() {
        return info_wh_id;
    }

    public void setInfo_wh_id(String info_wh_id) {
        this.info_wh_id = info_wh_id;
    }

    public String getInfo_num() {
        return info_num;
    }

    public void setInfo_num(String info_num) {
        this.info_num = info_num;
    }
}
