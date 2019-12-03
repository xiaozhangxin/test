package com.akan.wms.bean;

import java.io.Serializable;
import java.util.List;

public class StoragingProBean implements Serializable {


    /**
     * id : 1190730120546210
     * org_id : 1001512200010027
     * creator_id : 5998
     * doc_no :
     * creator_name : 系统管理员
     * status : 0
     * status_name : 未处理
     * report_type : 0
     * report_name : 注塑完工申报单
     * bar_code :
     * remark :
     * is_valid : 0
     * create_time : 2019-07-30 12:06:21
     * update_time : null
     * storaging_pro_lines : [{"id":1190730120546123,"storaging_pro_id":1190730120546210,"line_no":10,"org_id":1001512200010027,"pro_id":1001812260140003,"pro_type":"","item_id":1001812181054564,"item_code":"11.15.248.3025","item_name":"绿色双活接PPR铜球阀","item_spec":"Φ25","product_qty":"","complete_qty":612,"qualified_qty":"","scrap_qty":"","wh_qty":5,"wh_id":1001512260168424,"wh_name":"内销成品仓-PPR管件","wh_type":0,"operator_id":null,"operator_name":null,"bar_code":null}]
     * bar_lists : [{"id":3,"in_id":1190730120546210,"item_bar":"21","item_id":123,"item_code":"123123","item_name":"名称","wh_id":12239303},{"id":4,"in_id":1190730120546210,"item_bar":"222","item_id":123,"item_code":"123123","item_name":"名称","wh_id":12239303}]
     */

    private long id;
    private long org_id;
    private String creator_id;
    private String doc_no;
    private String creator_name;
    private String status;
    private String status_name;
    private String report_type;
    private String report_name;
    private String bar_code;
    private String remark;
    private String is_valid;
    private String create_time;
    private Object update_time;
    private List<StoragingProLinesBean> storaging_pro_lines;
    private List<BarListsBean> bar_lists;
    private List<RecordsBean> recordsBeans;

    public List<RecordsBean> getRecordsBeans() {
        return recordsBeans;
    }

    public void setRecordsBeans(List<RecordsBean> recordsBeans) {
        this.recordsBeans = recordsBeans;
    }

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

    public String getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(String creator_id) {
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

    public String getReport_type() {
        return report_type;
    }

    public void setReport_type(String report_type) {
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

    public String getIs_valid() {
        return is_valid;
    }

    public void setIs_valid(String is_valid) {
        this.is_valid = is_valid;
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

    public List<BarListsBean> getBar_lists() {
        return bar_lists;
    }

    public void setBar_lists(List<BarListsBean> bar_lists) {
        this.bar_lists = bar_lists;
    }

    public static class StoragingProLinesBean {
        /**
         * id : 1190730120546123
         * storaging_pro_id : 1190730120546210
         * line_no : 10
         * org_id : 1001512200010027
         * pro_id : 1001812260140003
         * pro_type :
         * item_id : 1001812181054564
         * item_code : 11.15.248.3025
         * item_name : 绿色双活接PPR铜球阀
         * item_spec : Φ25
         * product_qty :
         * complete_qty : 612
         * qualified_qty :
         * scrap_qty :
         * wh_qty : 5
         * wh_id : 1001512260168424
         * wh_name : 内销成品仓-PPR管件
         * wh_type : 0
         * operator_id : null
         * operator_name : null
         * bar_code : null
         */

        private long id;
        private long storaging_pro_id;
        private String line_no;
        private String doc_no;

        public String getDoc_no() {
            return doc_no;
        }

        public void setDoc_no(String doc_no) {
            this.doc_no = doc_no;
        }

        private long org_id;
        private long pro_id;
        private String pro_type;
        private String item_id;
        private String item_code;
        private String item_name;
        private String item_spec;
        private String product_qty;
        private String complete_qty;
        private String qualified_qty;
        private String scrap_qty;
        private int wh_qty;
        private int check_qty;
        private long wh_id;
        private String wh_name;
        private String wh_type;
        private Object operator_id;
        private Object operator_name;

        public List<BarBean> getBarList() {
            return barList;
        }

        public void setBarList(List<BarBean> barList) {
            this.barList = barList;
        }

        private Object bar_code;
        private List<BarBean> barList;
        public int getCheck_qty() {
            return check_qty;
        }

        public void setCheck_qty(int check_qty) {
            this.check_qty = check_qty;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public long getStoraging_pro_id() {
            return storaging_pro_id;
        }

        public void setStoraging_pro_id(long storaging_pro_id) {
            this.storaging_pro_id = storaging_pro_id;
        }

        public String getLine_no() {
            return line_no;
        }

        public void setLine_no(String line_no) {
            this.line_no = line_no;
        }

        public long getOrg_id() {
            return org_id;
        }

        public void setOrg_id(long org_id) {
            this.org_id = org_id;
        }

        public long getPro_id() {
            return pro_id;
        }

        public void setPro_id(long pro_id) {
            this.pro_id = pro_id;
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

        public int getWh_qty() {
            return wh_qty;
        }

        public void setWh_qty(int wh_qty) {
            this.wh_qty = wh_qty;
        }

        public long getWh_id() {
            return wh_id;
        }

        public void setWh_id(long wh_id) {
            this.wh_id = wh_id;
        }

        public String getWh_name() {
            return wh_name;
        }

        public void setWh_name(String wh_name) {
            this.wh_name = wh_name;
        }

        public String getWh_type() {
            return wh_type;
        }

        public void setWh_type(String wh_type) {
            this.wh_type = wh_type;
        }

        public Object getOperator_id() {
            return operator_id;
        }

        public void setOperator_id(Object operator_id) {
            this.operator_id = operator_id;
        }

        public Object getOperator_name() {
            return operator_name;
        }

        public void setOperator_name(Object operator_name) {
            this.operator_name = operator_name;
        }

        public Object getBar_code() {
            return bar_code;
        }

        public void setBar_code(Object bar_code) {
            this.bar_code = bar_code;
        }
    }

    public static class BarListsBean {
        /**
         * id : 3
         * in_id : 1190730120546210
         * item_bar : 21
         * item_id : 123
         * item_code : 123123
         * item_name : 名称
         * wh_id : 12239303
         */

        private String id;
        private long in_id;
        private String item_bar;
        private String item_id;
        private String item_code;
        private String item_name;
        private String wh_id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public long getIn_id() {
            return in_id;
        }

        public void setIn_id(long in_id) {
            this.in_id = in_id;
        }

        public String getItem_bar() {
            return item_bar;
        }

        public void setItem_bar(String item_bar) {
            this.item_bar = item_bar;
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

        public String getWh_id() {
            return wh_id;
        }

        public void setWh_id(String wh_id) {
            this.wh_id = wh_id;
        }
    }
}
