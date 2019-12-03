package com.akan.wms.bean;

import java.io.Serializable;
import java.util.List;

public class TransferUnCompleteBean implements Serializable{


    /**
     * id : 1001907300039117
     * doc_no : 0012019070001
     * doc_type_id : 1001512314860383
     * doc_type_name : 原材料调拨申请单
     * business_date : 2019-07-30 00:00:00
     * in_org_id : 1001512200010027
     * out_org_id : 1001512200010027
     * tra_type : 1
     * is_closed : 0
     * status : 2
     * update_time : 2019-07-31 19:52:41
     * org_id : 1001512200010027
     * remark :
     * status_show : 已审核
     * in_org_name : 爱康企业集团（上海）
     * out_org_name : 爱康企业集团（上海）
     * tra_type_show : 仓库转储
     * start_time :
     * end_time :
     * lineBeanList : [{"id":1001907300039118,"apply_id":1001907300039117,"item_id":1001812190203441,"apply_qty":1000,"out_qty":25,"in_qty":10,"status":2,"update_time":"2019-07-31 19:52:41","out_wh":1001512260168343,"in_wh":"","supplier_id":1001812180480335,"supplier_code":"99046","item_code":"00.01.011.0001","item_name":"PPR主料","item_spec":"","status_show":"已审核","out_wh_name":"材料仓"},{"id":1001907300039119,"apply_id":1001907300039117,"item_id":1001907040039177,"apply_qty":1100,"out_qty":0,"in_qty":0,"status":2,"update_time":"2019-07-30 12:19:36","out_wh":1001512260168343,"in_wh":"","supplier_id":1001812200010696,"supplier_code":"11003","item_code":"00.01.011.0001231","item_name":"PPR主料1","item_spec":"00.01.011.0001231","status_show":"已审核","out_wh_name":"材料仓"},{"id":1001907300039120,"apply_id":1001907300039117,"item_id":1001812180044580,"apply_qty":1200,"out_qty":0,"in_qty":0,"status":2,"update_time":"2019-07-30 12:19:36","out_wh":1001512260168343,"in_wh":"","supplier_id":"","supplier_code":"","item_code":"00.01.011.0002","item_name":"PPR主料\u2014绿色混配料","item_spec":"","status_show":"已审核","out_wh_name":"材料仓"}]
     */

    private String id;
    private String doc_no;
    private String doc_type_id;
    private String doc_type_name;
    private String business_date;
    private String in_org_id;
    private String out_org_id;

    public List<BarBean> getBarList() {
        return barList;
    }

    public void setBarList(List<BarBean> barList) {
        this.barList = barList;
    }

    private int tra_type;
    private int is_closed;
    private int status;
    private List<BarBean> barList;
    private String update_time;


    private String org_id;
    private String remark;
    private String status_show;
    private String in_org_name;
    private String out_org_name;
    private String tra_type_show;
    private String start_time;
    private String end_time;
    private List<LineBeanListBean> lineBeanList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDoc_no() {
        return doc_no;
    }

    public void setDoc_no(String doc_no) {
        this.doc_no = doc_no;
    }

    public String getDoc_type_id() {
        return doc_type_id;
    }

    public void setDoc_type_id(String doc_type_id) {
        this.doc_type_id = doc_type_id;
    }

    public String getDoc_type_name() {
        return doc_type_name;
    }

    public void setDoc_type_name(String doc_type_name) {
        this.doc_type_name = doc_type_name;
    }

    public String getBusiness_date() {
        return business_date;
    }

    public void setBusiness_date(String business_date) {
        this.business_date = business_date;
    }

    public String getIn_org_id() {
        return in_org_id;
    }

    public void setIn_org_id(String in_org_id) {
        this.in_org_id = in_org_id;
    }

    public String getOut_org_id() {
        return out_org_id;
    }

    public void setOut_org_id(String out_org_id) {
        this.out_org_id = out_org_id;
    }

    public int getTra_type() {
        return tra_type;
    }

    public void setTra_type(int tra_type) {
        this.tra_type = tra_type;
    }

    public int getIs_closed() {
        return is_closed;
    }

    public void setIs_closed(int is_closed) {
        this.is_closed = is_closed;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public String getOrg_id() {
        return org_id;
    }

    public void setOrg_id(String org_id) {
        this.org_id = org_id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus_show() {
        return status_show;
    }

    public void setStatus_show(String status_show) {
        this.status_show = status_show;
    }

    public String getIn_org_name() {
        return in_org_name;
    }

    public void setIn_org_name(String in_org_name) {
        this.in_org_name = in_org_name;
    }

    public String getOut_org_name() {
        return out_org_name;
    }

    public void setOut_org_name(String out_org_name) {
        this.out_org_name = out_org_name;
    }

    public String getTra_type_show() {
        return tra_type_show;
    }

    public void setTra_type_show(String tra_type_show) {
        this.tra_type_show = tra_type_show;
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

    public List<LineBeanListBean> getLineBeanList() {
        return lineBeanList;
    }

    public void setLineBeanList(List<LineBeanListBean> lineBeanList) {
        this.lineBeanList = lineBeanList;
    }

    public static class LineBeanListBean implements Serializable{
        /**
         * id : 1001907300039118
         * apply_id : 1001907300039117
         * item_id : 1001812190203441
         * apply_qty : 1000
         * out_qty : 25
         * in_qty : 10
         * status : 2
         * update_time : 2019-07-31 19:52:41
         * out_wh : 1001512260168343
         * in_wh :
         * supplier_id : 1001812180480335
         * supplier_code : 99046
         * item_code : 00.01.011.0001
         * item_name : PPR主料
         * item_spec :
         * status_show : 已审核
         * out_wh_name : 材料仓
         */

        private String id;
        private String apply_id;
        private String item_id;
        private int apply_qty;
        private int out_qty;
        private int in_qty;
        private int status;
        private String update_time;
        private String out_wh;
        private String in_wh;
        private String supplier_id;
        private String supplier_code;
        private String item_code;
        private String item_name;
        private String item_spec;
        private String status_show;
        private String out_wh_name;
        private String item_bar;
        private  boolean isCheck;
        private int send_qty;

        public String getItem_bar() {
            return item_bar;
        }

        public void setItem_bar(String item_bar) {
            this.item_bar = item_bar;
        }

        public int getSend_qty() {

            return send_qty;
        }

        public void setSend_qty(int send_qty) {
            this.send_qty = send_qty;
        }
        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getApply_id() {
            return apply_id;
        }

        public void setApply_id(String apply_id) {
            this.apply_id = apply_id;
        }

        public String getItem_id() {
            return item_id;
        }

        public void setItem_id(String item_id) {
            this.item_id = item_id;
        }

        public int getApply_qty() {
            return apply_qty;
        }

        public void setApply_qty(int apply_qty) {
            this.apply_qty = apply_qty;
        }

        public int getOut_qty() {
            return out_qty;
        }

        public void setOut_qty(int out_qty) {
            this.out_qty = out_qty;
        }

        public int getIn_qty() {
            return in_qty;
        }

        public void setIn_qty(int in_qty) {
            this.in_qty = in_qty;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public String getOut_wh() {
            return out_wh;
        }

        public void setOut_wh(String out_wh) {
            this.out_wh = out_wh;
        }

        public String getIn_wh() {
            return in_wh;
        }

        public void setIn_wh(String in_wh) {
            this.in_wh = in_wh;
        }

        public String getSupplier_id() {
            return supplier_id;
        }

        public void setSupplier_id(String supplier_id) {
            this.supplier_id = supplier_id;
        }

        public String getSupplier_code() {
            return supplier_code;
        }

        public void setSupplier_code(String supplier_code) {
            this.supplier_code = supplier_code;
        }

        public String getItem_code() {
            return item_code;
        }

        public void setItem_code(String item_code) {
            this.item_code = item_code;
        }

        public String getItem_name() {
            return item_name;
        }

        public void setItem_name(String item_name) {
            this.item_name = item_name;
        }

        public String getItem_spec() {
            return item_spec;
        }

        public void setItem_spec(String item_spec) {
            this.item_spec = item_spec;
        }

        public String getStatus_show() {
            return status_show;
        }

        public void setStatus_show(String status_show) {
            this.status_show = status_show;
        }

        public String getOut_wh_name() {
            return out_wh_name;
        }

        public void setOut_wh_name(String out_wh_name) {
            this.out_wh_name = out_wh_name;
        }
    }
}
