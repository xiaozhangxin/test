package com.akan.wms.bean;

import java.io.Serializable;
import java.util.List;

public class StoragingProListBean implements Serializable {


    /**
     * id : 1191219175631337
     * org_id : 1001512200010027
     * creator_id : 150399
     * doc_no : CPRK-101912191756311547
     * creator_name : 刘青
     * status : 0
     * status_name : 未处理
     * report_type : 0
     * report_name : 生产订单
     * bar_code :
     * remark :
     * is_valid : 0
     * is_delete : 1
     * create_time : 2019-12-19 17:56:31
     * update_time : null
     * storaging_pro_lines : [{"id":"","storaging_pro_id":"","line_no":"","org_id":"","pro_id":"","doc_no":"101-AKWO-201910-00008","pro_type":"","item_id":"","item_code":"","item_name":"","item_spec":"","product_qty":"","complete_qty":"","qualified_qty":"","scrap_qty":"","wh_qty":"","wh_id":"","wh_name":"","wh_type":0,"operator_id":null,"operator_name":null,"bar_code":null,"mfc":null,"default_mfc":null,"mfc_code":"","mfc_name":"","default_mfc_code":"","default_mfc_name":""}]
     * bar_lists : null
     * past_bar_lists : null
     * recordsBeans : null
     */

    private long id;
    private long org_id;
    private int creator_id;
    private String doc_no;
    private String creator_name;
    private int status;
    private String status_name;
    private int report_type;
    private String report_name;
    private String bar_code;
    private String remark;
    private int is_valid;
    private int is_delete;
    private String create_time;
    private Object update_time;
    private List<StoragingProLinesBean> storaging_pro_lines;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrg_id() {
        return org_id;
    }

    public void setOrg_id(long org_id) {
        this.org_id = org_id;
    }

    public int getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(int creator_id) {
        this.creator_id = creator_id;
    }

    public String getDoc_no() {
        return doc_no;
    }

    public void setDoc_no(String doc_no) {
        this.doc_no = doc_no;
    }

    public String getCreator_name() {
        return creator_name;
    }

    public void setCreator_name(String creator_name) {
        this.creator_name = creator_name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatus_name() {
        return status_name;
    }

    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }

    public int getReport_type() {
        return report_type;
    }

    public void setReport_type(int report_type) {
        this.report_type = report_type;
    }

    public String getReport_name() {
        return report_name;
    }

    public void setReport_name(String report_name) {
        this.report_name = report_name;
    }

    public String getBar_code() {
        return bar_code;
    }

    public void setBar_code(String bar_code) {
        this.bar_code = bar_code;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getIs_valid() {
        return is_valid;
    }

    public void setIs_valid(int is_valid) {
        this.is_valid = is_valid;
    }

    public int getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(int is_delete) {
        this.is_delete = is_delete;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public Object getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Object update_time) {
        this.update_time = update_time;
    }


    public List<StoragingProLinesBean> getStoraging_pro_lines() {
        return storaging_pro_lines;
    }

    public void setStoraging_pro_lines(List<StoragingProLinesBean> storaging_pro_lines) {
        this.storaging_pro_lines = storaging_pro_lines;
    }

    public static class StoragingProLinesBean {
        /**
         * id :
         * storaging_pro_id :
         * line_no :
         * org_id :
         * pro_id :
         * doc_no : 101-AKWO-201910-00008
         * pro_type :
         * item_id :
         * item_code :
         * item_name :
         * item_spec :
         * product_qty :
         * complete_qty :
         * qualified_qty :
         * scrap_qty :
         * wh_qty :
         * wh_id :
         * wh_name :
         * wh_type : 0
         * operator_id : null
         * operator_name : null
         * bar_code : null
         * mfc : null
         * default_mfc : null
         * mfc_code :
         * mfc_name :
         * default_mfc_code :
         * default_mfc_name :
         */

        private String id;
        private String storaging_pro_id;
        private String line_no;
        private String org_id;
        private String pro_id;
        private String doc_no;
        private String pro_type;
        private String item_id;
        private String item_code;
        private String item_name;
        private String item_spec;
        private String product_qty;
        private String complete_qty;
        private String qualified_qty;
        private String scrap_qty;
        private String wh_qty;
        private String wh_id;
        private String wh_name;
        private int wh_type;
        private String mfc_code;
        private String mfc_name;
        private String default_mfc_code;
        private String default_mfc_name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStoraging_pro_id() {
            return storaging_pro_id;
        }

        public void setStoraging_pro_id(String storaging_pro_id) {
            this.storaging_pro_id = storaging_pro_id;
        }

        public String getLine_no() {
            return line_no;
        }

        public void setLine_no(String line_no) {
            this.line_no = line_no;
        }

        public String getOrg_id() {
            return org_id;
        }

        public void setOrg_id(String org_id) {
            this.org_id = org_id;
        }

        public String getPro_id() {
            return pro_id;
        }

        public void setPro_id(String pro_id) {
            this.pro_id = pro_id;
        }

        public String getDoc_no() {
            return doc_no;
        }

        public void setDoc_no(String doc_no) {
            this.doc_no = doc_no;
        }

        public String getPro_type() {
            return pro_type;
        }

        public void setPro_type(String pro_type) {
            this.pro_type = pro_type;
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

        public String getProduct_qty() {
            return product_qty;
        }

        public void setProduct_qty(String product_qty) {
            this.product_qty = product_qty;
        }

        public String getComplete_qty() {
            return complete_qty;
        }

        public void setComplete_qty(String complete_qty) {
            this.complete_qty = complete_qty;
        }

        public String getQualified_qty() {
            return qualified_qty;
        }

        public void setQualified_qty(String qualified_qty) {
            this.qualified_qty = qualified_qty;
        }

        public String getScrap_qty() {
            return scrap_qty;
        }

        public void setScrap_qty(String scrap_qty) {
            this.scrap_qty = scrap_qty;
        }

        public String getWh_qty() {
            return wh_qty;
        }

        public void setWh_qty(String wh_qty) {
            this.wh_qty = wh_qty;
        }

        public String getWh_id() {
            return wh_id;
        }

        public void setWh_id(String wh_id) {
            this.wh_id = wh_id;
        }

        public String getWh_name() {
            return wh_name;
        }

        public void setWh_name(String wh_name) {
            this.wh_name = wh_name;
        }

        public int getWh_type() {
            return wh_type;
        }

        public void setWh_type(int wh_type) {
            this.wh_type = wh_type;
        }


        public String getMfc_code() {
            return mfc_code;
        }

        public void setMfc_code(String mfc_code) {
            this.mfc_code = mfc_code;
        }

        public String getMfc_name() {
            return mfc_name;
        }

        public void setMfc_name(String mfc_name) {
            this.mfc_name = mfc_name;
        }

        public String getDefault_mfc_code() {
            return default_mfc_code;
        }

        public void setDefault_mfc_code(String default_mfc_code) {
            this.default_mfc_code = default_mfc_code;
        }

        public String getDefault_mfc_name() {
            return default_mfc_name;
        }

        public void setDefault_mfc_name(String default_mfc_name) {
            this.default_mfc_name = default_mfc_name;
        }
    }
}
