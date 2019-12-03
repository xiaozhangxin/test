package com.akan.wms.bean;

import java.io.Serializable;

public class SupplierReceivesBeanDetail implements Serializable {


    /**
     * id : 1190716145408250
     * deliver_id : 1190716145408631
     * deliver_line_id :
     * pur_id : 1001812270017730
     * doc_no : 101-AKPO-201804-00242
     * pur_code :
     * item_id : 1001812181119014
     * item_name : 灰黄色暗阀(AG系列)
     * item_code : 11.15.475.7125
     * item_spec : Φ25
     * arrive_qty : {}
     * send_qty : 50
     * qualified_qty : {}
     * unqualified_qty : {}
     * wh_qty : {}
     * rtn_fill_qty : {}
     * rtn_deduct_qty : {}
     * qualified_staff_id :
     * qualified_staff_name :
     * wh_staff_id :
     * wh_staff_name :
     * creator_id :
     * creator_name :
     * <p>
     * create_time : 2019-07-16 14:54:52
     * wh_id : 1001512260168424
     * wh_code :
     * trade_mark : {}
     * wh_name : 内销成品仓-PPR管件
     * status :
     * status_name :
     * bar_code :
     * description :
     * qty : {}
     * pur_qty : 350
     * tag :
     * trade_uom :
     * trade_uom_name :
     * final_price :
     * wh :
     * receipt_rule :
     * rcv_dept :
     * mfc_code :
     * srcDocTransTypeId :
     * srcDocTransTypeCode :
     */

    private String id;
    private String deliver_id;
    private String deliver_line_id;
    private String pur_id;
    private String doc_no;
    private String pur_code;
    private String item_id;
    private String item_name;
    private String item_code;
    private String item_spec;
    private int send_qty;
    private String qualified_staff_id;
    private String qualified_staff_name;
    private String wh_staff_id;
    private String wh_staff_name;
    private String creator_id;
    private String creator_name;
    private String create_time;
    private String wh_id;
    private String wh_code;
    private String wh_name;
    private String status;
    private String status_name;
    private String bar_code;
    private String description;
    private String pur_qty;
    private String tag;
    private String trade_uom;
    private String trade_uom_name;
    private String final_price;
    private String wh;
    private String receipt_rule;
    private String rcv_dept;
    private String mfc_code;
    private String mfc_name;

    private String mfc;
    public String getMfc_name() {
        return mfc_name;
    }

    public void setMfc_name(String mfc_name) {
        this.mfc_name = mfc_name;
    }

    public String getMfc() {
        return mfc;
    }

    public void setMfc(String mfc) {
        this.mfc = mfc;
    }

    private String srcDocTransTypeId;
    private String srcDocTransTypeCode;
    private int arrive_qty;
    private int qualified_qty;
    private int unqualified_qty;
    private int in_qty;//入库扫码数量
    private String item_bar;//入库二维码code

    public String getItem_bar() {
        return item_bar;
    }

    public void setItem_bar(String item_bar) {
        this.item_bar = item_bar;
    }

    public int getIn_qty() {

        return in_qty;
    }

    public void setIn_qty(int in_qty) {
        this.in_qty = in_qty;
    }

    public int getQualified_qty() {
        return qualified_qty;
    }

    public void setQualified_qty(int qualified_qty) {
        this.qualified_qty = qualified_qty;
    }

    public int getUnqualified_qty() {
        return unqualified_qty;
    }

    public void setUnqualified_qty(int unqualified_qty) {
        this.unqualified_qty = unqualified_qty;
    }


    public int getArrive_qty() {
        return arrive_qty;
    }

    public void setArrive_qty(int arrive_qty) {
        this.arrive_qty = arrive_qty;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeliver_id() {
        return deliver_id;
    }

    public void setDeliver_id(String deliver_id) {
        this.deliver_id = deliver_id;
    }

    public String getDeliver_line_id() {
        return deliver_line_id;
    }

    public void setDeliver_line_id(String deliver_line_id) {
        this.deliver_line_id = deliver_line_id;
    }

    public String getPur_id() {
        return pur_id;
    }

    public void setPur_id(String pur_id) {
        this.pur_id = pur_id;
    }

    public String getDoc_no() {
        return doc_no;
    }

    public void setDoc_no(String doc_no) {
        this.doc_no = doc_no;
    }

    public String getPur_code() {
        return pur_code;
    }

    public void setPur_code(String pur_code) {
        this.pur_code = pur_code;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getItem_code() {
        return item_code;
    }

    public void setItem_code(String item_code) {
        this.item_code = item_code;
    }

    public String getItem_spec() {
        return item_spec;
    }

    public void setItem_spec(String item_spec) {
        this.item_spec = item_spec;
    }


    public int getSend_qty() {
        return send_qty;
    }

    public void setSend_qty(int send_qty) {
        this.send_qty = send_qty;
    }


    public String getQualified_staff_id() {
        return qualified_staff_id;
    }

    public void setQualified_staff_id(String qualified_staff_id) {
        this.qualified_staff_id = qualified_staff_id;
    }

    public String getQualified_staff_name() {
        return qualified_staff_name;
    }

    public void setQualified_staff_name(String qualified_staff_name) {
        this.qualified_staff_name = qualified_staff_name;
    }

    public String getWh_staff_id() {
        return wh_staff_id;
    }

    public void setWh_staff_id(String wh_staff_id) {
        this.wh_staff_id = wh_staff_id;
    }

    public String getWh_staff_name() {
        return wh_staff_name;
    }

    public void setWh_staff_name(String wh_staff_name) {
        this.wh_staff_name = wh_staff_name;
    }

    public String getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(String creator_id) {
        this.creator_id = creator_id;
    }

    public String getCreator_name() {
        return creator_name;
    }

    public void setCreator_name(String creator_name) {
        this.creator_name = creator_name;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getWh_id() {
        return wh_id;
    }

    public void setWh_id(String wh_id) {
        this.wh_id = wh_id;
    }

    public String getWh_code() {
        return wh_code;
    }

    public void setWh_code(String wh_code) {
        this.wh_code = wh_code;
    }

    public String getWh_name() {
        return wh_name;
    }

    public void setWh_name(String wh_name) {
        this.wh_name = wh_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }

    public String getBar_code() {
        return bar_code;
    }

    public void setBar_code(String bar_code) {
        this.bar_code = bar_code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getPur_qty() {
        return pur_qty;
    }

    public void setPur_qty(String pur_qty) {
        this.pur_qty = pur_qty;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getTrade_uom() {
        return trade_uom;
    }

    public void setTrade_uom(String trade_uom) {
        this.trade_uom = trade_uom;
    }

    public String getTrade_uom_name() {
        return trade_uom_name;
    }

    public void setTrade_uom_name(String trade_uom_name) {
        this.trade_uom_name = trade_uom_name;
    }

    public String getFinal_price() {
        return final_price;
    }

    public void setFinal_price(String final_price) {
        this.final_price = final_price;
    }

    public String getWh() {
        return wh;
    }

    public void setWh(String wh) {
        this.wh = wh;
    }

    public String getReceipt_rule() {
        return receipt_rule;
    }

    public void setReceipt_rule(String receipt_rule) {
        this.receipt_rule = receipt_rule;
    }

    public String getRcv_dept() {
        return rcv_dept;
    }

    public void setRcv_dept(String rcv_dept) {
        this.rcv_dept = rcv_dept;
    }

    public String getMfc_code() {
        return mfc_code;
    }

    public void setMfc_code(String mfc_code) {
        this.mfc_code = mfc_code;
    }

    public String getSrcDocTransTypeId() {
        return srcDocTransTypeId;
    }

    public void setSrcDocTransTypeId(String srcDocTransTypeId) {
        this.srcDocTransTypeId = srcDocTransTypeId;
    }

    public String getSrcDocTransTypeCode() {
        return srcDocTransTypeCode;
    }

    public void setSrcDocTransTypeCode(String srcDocTransTypeCode) {
        this.srcDocTransTypeCode = srcDocTransTypeCode;
    }


}
