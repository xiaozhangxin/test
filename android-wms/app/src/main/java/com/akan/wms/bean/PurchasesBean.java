package com.akan.wms.bean;

import java.io.Serializable;
import java.util.List;

public class PurchasesBean implements Serializable {

    public String getDoc_no() {
        return doc_no;
    }

    public void setDoc_no(String doc_no) {
        this.doc_no = doc_no;
    }

    /**
     * pur_id : 1001812270017730
     * supplier_receives : [{"id":1190716145408250,"deliver_id":1190716145408631,"deliver_line_id":"","pur_id":1001812270017730,"doc_no":"101-AKPO-201804-00242","pur_code":"","item_id":1001812181119014,"item_name":"灰黄色暗阀(AG系列)","item_code":"11.15.475.7125","item_spec":"Φ25","arrive_qty":{},"send_qty":50,"qualified_qty":{},"unqualified_qty":{},"wh_qty":{},"rtn_fill_qty":{},"rtn_deduct_qty":{},"qualified_staff_id":"","qualified_staff_name":"","wh_staff_id":"","wh_staff_name":"","creator_id":"","creator_name":"","create_time":"2019-07-16 14:54:52","wh_id":1001512260168424,"wh_code":"","trade_mark":{},"wh_name":"内销成品仓-PPR管件","status":"","status_name":"","bar_code":"","description":"","qty":{},"pur_qty":350,"tag":"","trade_uom":"","trade_uom_name":"","final_price":"","wh":"","receipt_rule":"","rcv_dept":"","mfc_code":"","srcDocTransTypeId":"","srcDocTransTypeCode":""}]
     */

    private String pur_id;
    private String doc_no;
    private List<SupplierReceivesBeanDetail> supplier_receives;
    private List<BarBean> barList;

    public List<BarBean> getBarList() {
        return barList;
    }

    public void setBarList(List<BarBean> barList) {
        this.barList = barList;
    }

    public String getPur_id() {
        return pur_id;
    }

    public void setPur_id(String pur_id) {
        this.pur_id = pur_id;
    }

    public List<SupplierReceivesBeanDetail> getSupplier_receives() {
        return supplier_receives;
    }

    public void setSupplier_receives(List<SupplierReceivesBeanDetail> supplier_receives) {
        this.supplier_receives = supplier_receives;
    }
}
