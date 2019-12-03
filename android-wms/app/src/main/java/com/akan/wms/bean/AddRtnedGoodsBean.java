package com.akan.wms.bean;

import java.io.Serializable;
import java.util.List;

public class AddRtnedGoodsBean implements Serializable{


    /**
     * bar_code : 100ww0010027
     * business_date : 2019-07-18 10:21:12
     * description :
     * id :
     * rtn_memo : 1001a
     * rtned_lines : [{"rtn_id":"1001907170049096","rtned_gods_lines":[{"bar_code":"1001b","deliver_line_id":10,"description":"1001a","item_id":"1001812190210713","item_name":"PERT银灰色母","line_no":20,"alloc_qty":"1","receive_qty":"0","rtn_deduct_qty":"0","rtn_fill_qty":"0","send_qty":"1","specs":"1001a","un_receive_qty":"0","wh_id":"123","wh_qty":"1001"}]}]
     * supplier_code : 13003
     */

    private String bar_code;
    private String business_date;
    private String description;
    private String id;
    private String rtn_memo;
    private String supplier_code;
    private List<RtnedLinesBean> rtned_lines;

    public String getBar_code() {
        return bar_code;
    }

    public void setBar_code(String bar_code) {
        this.bar_code = bar_code;
    }

    public String getBusiness_date() {
        return business_date;
    }

    public void setBusiness_date(String business_date) {
        this.business_date = business_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRtn_memo() {
        return rtn_memo;
    }

    public void setRtn_memo(String rtn_memo) {
        this.rtn_memo = rtn_memo;
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
         * rtn_id : 1001907170049096
         * rtned_gods_lines : [{"bar_code":"1001b","deliver_line_id":10,"description":"1001a","item_id":"1001812190210713","item_name":"PERT银灰色母","line_no":20,"alloc_qty":"1","receive_qty":"0","rtn_deduct_qty":"0","rtn_fill_qty":"0","send_qty":"1","specs":"1001a","un_receive_qty":"0","wh_id":"123","wh_qty":"1001"}]
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
             * item_id : 1001812190210713
             * item_name : PERT银灰色母
             * line_no : 20
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

            private String bar_code;
            private int deliver_line_id;
            private String description;
            private String item_id;
            private String item_name;
            private int line_no;
            private String alloc_qty;
            private String rtn_qty;
            private String receive_qty;
            private String mfc;

            public String getMfc() {
                return mfc;
            }

            public void setMfc(String mfc) {
                this.mfc = mfc;
            }

            public String getRtn_qty() {
                return rtn_qty;
            }

            public void setRtn_qty(String rtn_qty) {
                this.rtn_qty = rtn_qty;
            }

            private String rtn_deduct_qty;
            private String rtn_fill_qty;
            private String send_qty;
            private String specs;
            private String un_receive_qty;
            private String wh_id;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            private String wh_qty;
            private String id;

            public String getBar_code() {
                return bar_code;
            }

            public void setBar_code(String bar_code) {
                this.bar_code = bar_code;
            }

            public int getDeliver_line_id() {
                return deliver_line_id;
            }

            public void setDeliver_line_id(int deliver_line_id) {
                this.deliver_line_id = deliver_line_id;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
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

            public int getLine_no() {
                return line_no;
            }

            public void setLine_no(int line_no) {
                this.line_no = line_no;
            }

            public String getAlloc_qty() {
                return alloc_qty;
            }

            public void setAlloc_qty(String alloc_qty) {
                this.alloc_qty = alloc_qty;
            }

            public String getReceive_qty() {
                return receive_qty;
            }

            public void setReceive_qty(String receive_qty) {
                this.receive_qty = receive_qty;
            }

            public String getRtn_deduct_qty() {
                return rtn_deduct_qty;
            }

            public void setRtn_deduct_qty(String rtn_deduct_qty) {
                this.rtn_deduct_qty = rtn_deduct_qty;
            }

            public String getRtn_fill_qty() {
                return rtn_fill_qty;
            }

            public void setRtn_fill_qty(String rtn_fill_qty) {
                this.rtn_fill_qty = rtn_fill_qty;
            }

            public String getSend_qty() {
                return send_qty;
            }

            public void setSend_qty(String send_qty) {
                this.send_qty = send_qty;
            }

            public String getSpecs() {
                return specs;
            }

            public void setSpecs(String specs) {
                this.specs = specs;
            }

            public String getUn_receive_qty() {
                return un_receive_qty;
            }

            public void setUn_receive_qty(String un_receive_qty) {
                this.un_receive_qty = un_receive_qty;
            }

            public String getWh_id() {
                return wh_id;
            }

            public void setWh_id(String wh_id) {
                this.wh_id = wh_id;
            }

            public String getWh_qty() {
                return wh_qty;
            }

            public void setWh_qty(String wh_qty) {
                this.wh_qty = wh_qty;
            }
        }
    }
}
