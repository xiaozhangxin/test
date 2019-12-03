package com.akan.wms.bean;

import java.io.Serializable;
import java.util.List;

public class AddStoragingProBean implements Serializable {

    private String remark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    private List<StoragingProLinesBean> storaging_pro_lines;

    public List<StoragingProLinesBean> getStoraging_pro_lines() {
        return storaging_pro_lines;
    }

    public void setStoraging_pro_lines(List<StoragingProLinesBean> storaging_pro_lines) {
        this.storaging_pro_lines = storaging_pro_lines;
    }

    public static class StoragingProLinesBean {
        /**
         * pro_id : 1001812260140003
         * item_id : 1001812181054564
         * wh_qty : 10.0
         * wh_id : 1001512260168424
         * bar_code : 604
         */

        private String pro_id;
        private String item_id;
        private String wh_qty;
        private String wh_id;
        private String bar_code;

        public String getPro_id() {
            return pro_id;
        }

        public void setPro_id(String pro_id) {
            this.pro_id = pro_id;
        }

        public String getItem_id() {
            return item_id;
        }

        public void setItem_id(String item_id) {
            this.item_id = item_id;
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

        public String getBar_code() {
            return bar_code;
        }

        public void setBar_code(String bar_code) {
            this.bar_code = bar_code;
        }
    }
}
