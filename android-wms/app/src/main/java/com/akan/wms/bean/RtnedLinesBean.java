package com.akan.wms.bean;

import java.io.Serializable;
import java.util.List;

public class RtnedLinesBean implements Serializable{


    /**
     * rtn_id : 1001907170049096
     * rtned_gods_lines : [{"id":1190719140535651,"rtned_goods_id":1190719140535680,"rtned_goods_line_id":"","item_id":1001812190210713,"item_code":"00.01.013.0001","item_name":"白色PPR色母(R1610C)","item_spec":"","src_doc_type":0,"src_doc_no":null,"doc_no":null,"rtn_qty":0,"rtn_order_qty":0,"rtn_ar_qty":0,"alloc_qty":1,"rtn":1001907170049096,"wh_id":123,"wh_code":0,"wh_name":null,"rtn_line_no":0,"org_id":1001512200010027,"org_name":null,"bar_code":"1001b","description":"1001a","status":0,"status_name":"已配货"}]
     */

    private String rtn_id;
    private String doc_no;

    public String getDoc_no() {
        return doc_no;
    }

    public void setDoc_no(String doc_no) {
        this.doc_no = doc_no;
    }

    public List<BarBean> getBarList() {
        return barList;
    }

    public void setBarList(List<BarBean> barList) {
        this.barList = barList;
    }

    private List<BarBean> barList;
    private List<RtnedGodsLinesBean> rtned_gods_lines;

    public String getRtn_id() {
        return rtn_id;
    }

    public void setRtn_id(String rtn_id) {
        this.rtn_id = rtn_id;
    }

    public List<RtnedGodsLinesBean> getRtned_gods_lines() {
        return rtned_gods_lines;
    }

    public void setRtned_gods_lines(List<RtnedGodsLinesBean> rtned_gods_lines) {
        this.rtned_gods_lines = rtned_gods_lines;
    }

    public static class RtnedGodsLinesBean {
        /**
         * id : 1190719140535651
         * rtned_goods_id : 1190719140535680
         * rtned_goods_line_id :
         * item_id : 1001812190210713
         * item_code : 00.01.013.0001
         * item_name : 白色PPR色母(R1610C)
         * item_spec :
         * src_doc_type : 0
         * src_doc_no : null
         * doc_no : null
         * rtn_qty : 0
         * rtn_order_qty : 0
         * rtn_ar_qty : 0
         * alloc_qty : 1
         * rtn : 1001907170049096
         * wh_id : 123
         * wh_code : 0
         * wh_name : null
         * rtn_line_no : 0
         * org_id : 1001512200010027
         * org_name : null
         * bar_code : 1001b
         * description : 1001a
         * status : 0
         * status_name : 已配货
         */

        private String id;
        private long rtned_goods_id;
        private String rtned_goods_line_id;
        private String item_id;
        private String item_code;
        private String item_name;
        private String item_spec;
        private String src_doc_type;
        private Object src_doc_no;
        private Object doc_no;
        private int rtn_qty;
        private String rtn_order_qty;
        private String rtn_ar_qty;
        private int alloc_qty;
        private int check_qty;
        private long rtn;
        private String wh_id;
        private String wh_code;
        private Object wh_name;
        private String rtn_line_no;
        private long org_id;
        private Object org_name;
        private Object bar_code;
        private String description;

        public int getCheck_qty() {
            return check_qty;
        }

        public void setCheck_qty(int check_qty) {
            this.check_qty = check_qty;
        }

        public String getMfc_name() {
            return mfc_name;
        }

        public void setMfc_name(String mfc_name) {
            this.mfc_name = mfc_name;
        }

        private String status;
        private String status_name;
        private String mfc_name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public long getRtned_goods_id() {
            return rtned_goods_id;
        }

        public void setRtned_goods_id(long rtned_goods_id) {
            this.rtned_goods_id = rtned_goods_id;
        }

        public String getRtned_goods_line_id() {
            return rtned_goods_line_id;
        }

        public void setRtned_goods_line_id(String rtned_goods_line_id) {
            this.rtned_goods_line_id = rtned_goods_line_id;
        }

        public String getItem_id() {
            return item_id;
        }

        public void setItem_id(String item_id) {
            this.item_id = item_id;
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

        public String getSrc_doc_type() {
            return src_doc_type;
        }

        public void setSrc_doc_type(String src_doc_type) {
            this.src_doc_type = src_doc_type;
        }

        public Object getSrc_doc_no() {
            return src_doc_no;
        }

        public void setSrc_doc_no(Object src_doc_no) {
            this.src_doc_no = src_doc_no;
        }

        public Object getDoc_no() {
            return doc_no;
        }

        public void setDoc_no(Object doc_no) {
            this.doc_no = doc_no;
        }

        public int getRtn_qty() {
            return rtn_qty;
        }

        public void setRtn_qty(int rtn_qty) {
            this.rtn_qty = rtn_qty;
        }

        public String getRtn_order_qty() {
            return rtn_order_qty;
        }

        public void setRtn_order_qty(String rtn_order_qty) {
            this.rtn_order_qty = rtn_order_qty;
        }

        public String getRtn_ar_qty() {
            return rtn_ar_qty;
        }

        public void setRtn_ar_qty(String rtn_ar_qty) {
            this.rtn_ar_qty = rtn_ar_qty;
        }

        public int getAlloc_qty() {
            return alloc_qty;
        }

        public void setAlloc_qty(int alloc_qty) {
            this.alloc_qty = alloc_qty;
        }

        public long getRtn() {
            return rtn;
        }

        public void setRtn(long rtn) {
            this.rtn = rtn;
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

        public Object getWh_name() {
            return wh_name;
        }

        public void setWh_name(Object wh_name) {
            this.wh_name = wh_name;
        }

        public String getRtn_line_no() {
            return rtn_line_no;
        }

        public void setRtn_line_no(String rtn_line_no) {
            this.rtn_line_no = rtn_line_no;
        }

        public long getOrg_id() {
            return org_id;
        }

        public void setOrg_id(long org_id) {
            this.org_id = org_id;
        }

        public Object getOrg_name() {
            return org_name;
        }

        public void setOrg_name(Object org_name) {
            this.org_name = org_name;
        }

        public Object getBar_code() {
            return bar_code;
        }

        public void setBar_code(Object bar_code) {
            this.bar_code = bar_code;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
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
    }
}
