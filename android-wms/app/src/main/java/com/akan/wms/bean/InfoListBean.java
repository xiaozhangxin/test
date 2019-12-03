package com.akan.wms.bean;

import java.io.Serializable;

public class InfoListBean implements Serializable {
    /**
     * info_id : 88
     * info_wh_id : 1001512260168343
     * ship_id : 50
     * item_id : 1001812180044580
     * number : 20
     * benefit_dept : {}
     * pub_desc_seg2 : 3
     * pub_desc_seg3 : 4
     * pub_desc_seg1 : 2
     * cost_num : 20
     * info_num : 2
     * benefit_org_name :
     * pub_desc_seg2_name :
     * pub_desc_seg3_name : 营销中心
     * pub_desc_seg1_name :
     * create_time : 2019-07-17 11:41:41
     * codeList : []
     * info_wh_name : 材料仓
     * info_spec :
     * info_name : PPR主料—绿色混配料
     */

    private String pub_desc_seg3;
    private String pub_desc_seg1;
    private int cost_num;
    private int info_num;
    private String benefit_org_name;
    private String pub_desc_seg2_name;
    private String pub_desc_seg3_name;
    private String pub_desc_seg1_name;
    private String create_time;
    private String info_wh_name;
    private String info_spec;
    private String info_name;

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

    public int getCost_num() {
        return cost_num;
    }

    public void setCost_num(int cost_num) {
        this.cost_num = cost_num;
    }

    public int getInfo_num() {
        return info_num;
    }

    public void setInfo_num(int info_num) {
        this.info_num = info_num;
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

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getInfo_wh_name() {
        return info_wh_name;
    }

    public void setInfo_wh_name(String info_wh_name) {
        this.info_wh_name = info_wh_name;
    }

    public String getInfo_spec() {
        return info_spec;
    }

    public void setInfo_spec(String info_spec) {
        this.info_spec = info_spec;
    }

    public String getInfo_name() {
        return info_name;
    }

    public void setInfo_name(String info_name) {
        this.info_name = info_name;
    }




}