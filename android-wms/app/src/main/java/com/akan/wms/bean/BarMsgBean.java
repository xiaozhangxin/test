package com.akan.wms.bean;

import java.io.Serializable;
import java.util.List;

public class BarMsgBean implements Serializable {


    /**
     * logistics : [{"id":123,"inventory_type":"CGRK","inventory_type_name":"采购入库","org_id":1001512200010027,"org_name":"爱康企业集团（上海）","wh_id":1001512260168343,"wh_name":"材料仓","item_bar":"16973520629638839978","item_id":1001812190203441,"item_name":"","item_code":"","item_spec":"","item_sku":"","in_out_type":0,"qty":"","create_time":"2019-12-24 11:56:01","custom_name":""},{"id":122,"inventory_type":"CGRK","inventory_type_name":"采购入库","org_id":1001512200010027,"org_name":"爱康企业集团（上海）","wh_id":1001512260168343,"wh_name":"材料仓","item_bar":"16973520629638839978","item_id":1001812190203441,"item_name":"","item_code":"","item_spec":"","item_sku":"","in_out_type":0,"qty":"","create_time":"2019-12-24 11:56:01","custom_name":""}]
     * linfo : {"query_code":"16973520629638839978","parent_code":"","label_code":"","org_id":"","org_name":"","item_cat_name":"","item_id":"","item_code":"00.01.011.0001","item_name":"PPR主料","item_spec":"","item_sku":"公斤","item_color":"","bar_code":"16973520629638839978","qty":10,"create_time":"","bar_type":2,"work_team":"","work_shop":"","inspector":"","product_line":"","parent_label_code":"-1","named_custom":""}
     * barCodes : []
     */

    private LinfoBean linfo;
    private List<LogisticsBean> logistics;
    private List<String> barCodes;

    public List<String> getBarCodes() {
        return barCodes;
    }

    public void setBarCodes(List<String> barCodes) {
        this.barCodes = barCodes;
    }

    public LinfoBean getLinfo() {
        return linfo;
    }

    public void setLinfo(LinfoBean linfo) {
        this.linfo = linfo;
    }

    public List<LogisticsBean> getLogistics() {
        return logistics;
    }

    public void setLogistics(List<LogisticsBean> logistics) {
        this.logistics = logistics;
    }

    public static class LinfoBean implements Serializable{
        /**
         * query_code : 16973520629638839978
         * parent_code :
         * label_code :
         * org_id :
         * org_name :
         * item_cat_name :
         * item_id :
         * item_code : 00.01.011.0001
         * item_name : PPR主料
         * item_spec :
         * item_sku : 公斤
         * item_color :
         * bar_code : 16973520629638839978
         * qty : 10
         * create_time :
         * bar_type : 2
         * work_team :
         * work_shop :
         * inspector :
         * product_line :
         * parent_label_code : -1
         * named_custom :
         */

        private String query_code;
        private String parent_code;
        private String label_code;
        private String org_id;
        private String org_name;
        private String item_cat_name;
        private String item_id;
        private String item_code;
        private String item_name;
        private String item_spec;
        private String item_sku;
        private String item_color;
        private String bar_code;
        private int qty;
        private String create_time;
        private int bar_type;
        private String work_team;
        private String work_shop;
        private String inspector;
        private String product_line;
        private String parent_label_code;
        private String named_custom;

        public String getQuery_code() {
            return query_code;
        }

        public void setQuery_code(String query_code) {
            this.query_code = query_code;
        }

        public String getParent_code() {
            return parent_code;
        }

        public void setParent_code(String parent_code) {
            this.parent_code = parent_code;
        }

        public String getLabel_code() {
            return label_code;
        }

        public void setLabel_code(String label_code) {
            this.label_code = label_code;
        }

        public String getOrg_id() {
            return org_id;
        }

        public void setOrg_id(String org_id) {
            this.org_id = org_id;
        }

        public String getOrg_name() {
            return org_name;
        }

        public void setOrg_name(String org_name) {
            this.org_name = org_name;
        }

        public String getItem_cat_name() {
            return item_cat_name;
        }

        public void setItem_cat_name(String item_cat_name) {
            this.item_cat_name = item_cat_name;
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

        public String getItem_sku() {
            return item_sku;
        }

        public void setItem_sku(String item_sku) {
            this.item_sku = item_sku;
        }

        public String getItem_color() {
            return item_color;
        }

        public void setItem_color(String item_color) {
            this.item_color = item_color;
        }

        public String getBar_code() {
            return bar_code;
        }

        public void setBar_code(String bar_code) {
            this.bar_code = bar_code;
        }

        public int getQty() {
            return qty;
        }

        public void setQty(int qty) {
            this.qty = qty;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public int getBar_type() {
            return bar_type;
        }

        public void setBar_type(int bar_type) {
            this.bar_type = bar_type;
        }

        public String getWork_team() {
            return work_team;
        }

        public void setWork_team(String work_team) {
            this.work_team = work_team;
        }

        public String getWork_shop() {
            return work_shop;
        }

        public void setWork_shop(String work_shop) {
            this.work_shop = work_shop;
        }

        public String getInspector() {
            return inspector;
        }

        public void setInspector(String inspector) {
            this.inspector = inspector;
        }

        public String getProduct_line() {
            return product_line;
        }

        public void setProduct_line(String product_line) {
            this.product_line = product_line;
        }

        public String getParent_label_code() {
            return parent_label_code;
        }

        public void setParent_label_code(String parent_label_code) {
            this.parent_label_code = parent_label_code;
        }

        public String getNamed_custom() {
            return named_custom;
        }

        public void setNamed_custom(String named_custom) {
            this.named_custom = named_custom;
        }
    }

    public static class LogisticsBean implements Serializable{
        /**
         * id : 123
         * inventory_type : CGRK
         * inventory_type_name : 采购入库
         * org_id : 1001512200010027
         * org_name : 爱康企业集团（上海）
         * wh_id : 1001512260168343
         * wh_name : 材料仓
         * item_bar : 16973520629638839978
         * item_id : 1001812190203441
         * item_name :
         * item_code :
         * item_spec :
         * item_sku :
         * in_out_type : 0
         * qty :
         * create_time : 2019-12-24 11:56:01
         * custom_name :
         */

        private int id;
        private String inventory_type;
        private String inventory_type_name;
        private long org_id;
        private String org_name;
        private long wh_id;
        private String wh_name;
        private String item_bar;
        private long item_id;
        private String item_name;
        private String item_code;
        private String item_spec;
        private String item_sku;
        private String in_out_type;
        private String qty;
        private String create_time;
        private String custom_name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getInventory_type() {
            return inventory_type;
        }

        public void setInventory_type(String inventory_type) {
            this.inventory_type = inventory_type;
        }

        public String getInventory_type_name() {
            return inventory_type_name;
        }

        public void setInventory_type_name(String inventory_type_name) {
            this.inventory_type_name = inventory_type_name;
        }

        public long getOrg_id() {
            return org_id;
        }

        public void setOrg_id(long org_id) {
            this.org_id = org_id;
        }

        public String getOrg_name() {
            return org_name;
        }

        public void setOrg_name(String org_name) {
            this.org_name = org_name;
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

        public String getItem_bar() {
            return item_bar;
        }

        public void setItem_bar(String item_bar) {
            this.item_bar = item_bar;
        }

        public long getItem_id() {
            return item_id;
        }

        public void setItem_id(long item_id) {
            this.item_id = item_id;
        }

        public String getItem_name() {
            return item_name;
        }

        public void setItem_name(String item_name) {
            this.item_name = item_name;
        }

        public String getItem_code() {
            return item_code;
        }

        public void setItem_code(String item_code) {
            this.item_code = item_code;
        }

        public String getItem_spec() {
            return item_spec;
        }

        public void setItem_spec(String item_spec) {
            this.item_spec = item_spec;
        }

        public String getItem_sku() {
            return item_sku;
        }

        public void setItem_sku(String item_sku) {
            this.item_sku = item_sku;
        }

        public String getIn_out_type() {
            return in_out_type;
        }

        public void setIn_out_type(String in_out_type) {
            this.in_out_type = in_out_type;
        }

        public String getQty() {
            return qty;
        }

        public void setQty(String qty) {
            this.qty = qty;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public String getCustom_name() {
            return custom_name;
        }

        public void setCustom_name(String custom_name) {
            this.custom_name = custom_name;
        }
    }
}
