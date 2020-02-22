package com.akan.wms.bean;

import java.io.Serializable;
import java.util.List;

public class PastRtnedGoodsBean implements Serializable {


    /**
     * bar_code : 100ww0010027
     * business_date : 2019-07-18 10:21:12
     * description :
     * doc_no : 1001
     * id : 1001907102345129520
     * rtn_memo : 1001a
     * rtned_lines : [{"rtn_id":"1001907170049129","rtned_gods_lines":[{"bar_code":"1001b","deliver_line_id":10,"description":"1001a","item_id":"1001812190203441","item_name":"PERT银灰色母","line_no":20,"pur_id":"1001812280013172","alloc_qty":"1","receive_qty":"0","rtn_deduct_qty":"0","rtn_fill_qty":"0","send_qty":"1","specs":"1001a","un_receive_qty":"0","wh_id":"123","wh_qty":"1001"}]}]
     * supplier_code : 13003
     */


    private String id;
    private String supplier_code;
    private List<RtnedLinesBean> rtned_lines;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
        /**
         * rtn_id : 1001907170049129
         * rtned_gods_lines : [{"bar_code":"1001b","deliver_line_id":10,"description":"1001a","item_id":"1001812190203441","item_name":"PERT银灰色母","line_no":20,"pur_id":"1001812280013172","alloc_qty":"1","receive_qty":"0","rtn_deduct_qty":"0","rtn_fill_qty":"0","send_qty":"1","specs":"1001a","un_receive_qty":"0","wh_id":"123","wh_qty":"1001"}]
         */

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
             * bar_code : 1001b
             * deliver_line_id : 10
             * description : 1001a
             * item_id : 1001812190203441
             * item_name : PERT银灰色母
             * line_no : 20
             * pur_id : 1001812280013172
             * alloc_qty : 1
             * receive_qty : 0
             * rtn_deduct_qty : 0
             * rtn_fill_qty : 0
             * send_qty : 1
             * specs : 1001a
             * un_receive_qty : 0
             * wh_id : 123
             * wh_qty : 1001
             */

            private String id;
            private String wh_id;
            private String alloc_qty;
            private String rtn_line_no;
            private String rtn;
            private String wh_name;
            private String item_id;
            private String item_code;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }


            public String getItem_id() {
                return item_id;
            }

            public void setItem_id(String item_id) {
                this.item_id = item_id;
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

            public String getItem_code() {
                return item_code;
            }

            public void setItem_code(String item_code) {
                this.item_code = item_code;
            }

            public String getAlloc_qty() {
                return alloc_qty;
            }

            public void setAlloc_qty(String alloc_qty) {
                this.alloc_qty = alloc_qty;
            }



            public String getWh_id() {
                return wh_id;
            }

            public void setWh_id(String wh_id) {
                this.wh_id = wh_id;
            }


        }
    }
}
