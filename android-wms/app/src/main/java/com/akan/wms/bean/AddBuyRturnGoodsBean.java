package com.akan.wms.bean;

import java.io.Serializable;
import java.util.List;

public class AddBuyRturnGoodsBean implements Serializable {



    private String supplier_code;
    private List<RtnedLinesBean> rtned_lines;

    public String getSupplier_code() {
        return supplier_code;
    }

    public void setSupplier_code(String supplier_code) {
        this.supplier_code = supplier_code;
    }

    public List<RtnedLinesBean> getRtned_lines() {
        return rtned_lines;
    }

    public void setRtned_lines(List<RtnedLinesBean> rtned_lines) {
        this.rtned_lines = rtned_lines;
    }

    public static class RtnedLinesBean {


        private String rtn_id;
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
             * id : 1001908300447976
             * wh_id : 1001512260168428
             * alloc_qty : 10
             * rtn_line_no : 10
             * rtn : 1001908300447975
             * wh_name : 天下保利仓
             * item_id : 1001812180255256
             * item_code : 01.02.0017
             */

            private String id;
            private String wh_id;
            private String alloc_qty;
            private String rtn_line_no;
            private String rtn;
            private String wh_name;
            private String item_id;
            private String item_code;
            private String mfc_code;
            private String mfc_name;
            private String mfc;
            private String wh_manager_id;

            public String getWh_manager_id() {
                return wh_manager_id;
            }

            public void setWh_manager_id(String wh_manager_id) {
                this.wh_manager_id = wh_manager_id;
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

            public String getWh_id() {
                return wh_id;
            }

            public void setWh_id(String wh_id) {
                this.wh_id = wh_id;
            }

            public String getAlloc_qty() {
                return alloc_qty;
            }

            public void setAlloc_qty(String alloc_qty) {
                this.alloc_qty = alloc_qty;
            }

            public String getRtn_line_no() {
                return rtn_line_no;
            }

            public void setRtn_line_no(String rtn_line_no) {
                this.rtn_line_no = rtn_line_no;
            }

            public String getRtn() {
                return rtn;
            }

            public void setRtn(String rtn) {
                this.rtn = rtn;
            }

            public String getWh_name() {
                return wh_name;
            }

            public void setWh_name(String wh_name) {
                this.wh_name = wh_name;
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
        }
    }
}
