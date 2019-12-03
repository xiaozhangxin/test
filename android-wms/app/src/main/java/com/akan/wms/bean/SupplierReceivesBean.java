package com.akan.wms.bean;

import java.io.Serializable;

public class SupplierReceivesBean implements Serializable {

    private String send_qty;
    private String wh_id;
    private String pur_qty;
    private String pur_id;
    private String item_id;
    private String arrive_qty;
    private String id;
    private String mfc;
    private String wh_staff_id;


    public String getWh_staff_id() {
        return wh_staff_id;
    }

    public void setWh_staff_id(String wh_staff_id) {
        this.wh_staff_id = wh_staff_id;
    }

    public String getMfc() {
        return mfc;
    }

    public void setMfc(String mfc) {
        this.mfc = mfc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQualified_qty() {
        return qualified_qty;
    }

    public void setQualified_qty(String qualified_qty) {
        this.qualified_qty = qualified_qty;
    }

    public String getUnqualified_qty() {
        return unqualified_qty;
    }

    public void setUnqualified_qty(String unqualified_qty) {
        this.unqualified_qty = unqualified_qty;
    }

    private String qualified_qty;
    private String unqualified_qty;

    public String getArrive_qty() {
        return arrive_qty;
    }

    public void setArrive_qty(String arrive_qty) {
        this.arrive_qty = arrive_qty;
    }

    public String getSend_qty() {
        return send_qty;
    }

    public void setSend_qty(String send_qty) {
        this.send_qty = send_qty;
    }

    public String getWh_id() {
        return wh_id;
    }

    public void setWh_id(String wh_id) {
        this.wh_id = wh_id;
    }

    public String getPur_qty() {
        return pur_qty;
    }

    public void setPur_qty(String pur_qty) {
        this.pur_qty = pur_qty;
    }

    public String getPur_id() {
        return pur_id;
    }

    public void setPur_id(String pur_id) {
        this.pur_id = pur_id;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }
}
