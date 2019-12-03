package com.akan.wms.bean;

import java.io.Serializable;
import java.util.List;

public class CompleteDecBean implements Serializable{


    /**
     * id : 1190726171835114
     * org_id : 1001512200010027
     * creator_id : 6001
     * creator_name : 樊彩红
     * status : 1
     * status_name : 已审核
     * report_type : 0
     * report_name : 注塑完工申报单
     * bar_code :
     * remark : 在UI没事的时候不要忘记了
     * is_valid : 0
     * create_time : 2019-07-26 17:19:13
     * update_time : null
     * complete_dec_lines : [{"id":1190726171835695,"comp_dec_id":1190726171835114,"line_no":10,"org_id":1001512200010027,"pro_id":1001812260140019,"pro_type":"0","item_id":1001812181077796,"item_code":"11.15.272.3025","item_name":"绿色普通截止阀","item_spec":"Φ25","product_qty":"","complete_qty":0,"qualified_qty":3,"scrap_qty":4,"wh_qty":0,"operator_id":7,"operator_name":"刚哥","class_team_id":1001601100177744,"class_team_name":"甲","mac_code":"1","mod_id":"","mod_code":"1001812170018476","mod_name":"","wh_id":9,"wh_name":"","eve_weight":"","speed":"","labor_hours":6,"mac_pre_hours":7,"mac_hours":8,"scrap_weight":5,"mod_weight":10,"ele_weight":11,"mod_outline_weight":12,"mod_inserts_weight":12,"bush_weight":13,"mod_amount":14,"ele_amount":15}]
     */

    private long id;
    private long org_id;
    private String creator_id;
    private String creator_name;
    private String status;
    private String status_name;
    private String report_type;
    private String report_name;
    private String bar_code;
    private String remark;
    private String doc_no;
    private List<RecordsBean> recordsBeans;

    public List<RecordsBean> getRecordsBeans() {
        return recordsBeans;
    }

    public void setRecordsBeans(List<RecordsBean> recordsBeans) {
        this.recordsBeans = recordsBeans;
    }

    public String getDoc_no() {
        return doc_no;
    }

    public void setDoc_no(String doc_no) {
        this.doc_no = doc_no;
    }

    private String is_valid;
    private String create_time;
    private List<CompleteDecLinesBean> complete_dec_lines;

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



    public List<CompleteDecLinesBean> getComplete_dec_lines() {
        return complete_dec_lines;
    }

    public void setComplete_dec_lines(List<CompleteDecLinesBean> complete_dec_lines) {
        this.complete_dec_lines = complete_dec_lines;
    }

    public static class CompleteDecLinesBean implements Serializable{
        /**
         * id : 1190726171835695
         * comp_dec_id : 1190726171835114
         * line_no : 10
         * org_id : 1001512200010027
         * pro_id : 1001812260140019
         * pro_type : 0
         * item_id : 1001812181077796
         * item_code : 11.15.272.3025
         * item_name : 绿色普通截止阀
         * item_spec : Φ25
         * product_qty :
         * complete_qty : 0
         * qualified_qty : 3
         * scrap_qty : 4
         * wh_qty : 0
         * operator_id : 7
         * operator_name : 刚哥
         * class_team_id : 1001601100177744
         * class_team_name : 甲
         * mac_code : 1
         * mod_id :
         * mod_code : 1001812170018476
         * mod_name :
         * wh_id : 9
         * wh_name :
         * eve_weight :
         * speed :
         * labor_hours : 6
         * mac_pre_hours : 7
         * mac_hours : 8
         * scrap_weight : 5
         * mod_weight : 10
         * ele_weight : 11
         * mod_outline_weight : 12
         * mod_inserts_weight : 12
         * bush_weight : 13
         * mod_amount : 14
         * ele_amount : 15
         */

        private String id;
        private String comp_dec_id;
        private String line_no;
        private String org_id;
        private String pro_id;
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
        private String operator_id;
        private String operator_name;
        private String class_team_id;
        private String class_team_name;
        private String mac_code;
        private String mod_id;
        private String mod_code;
        private String mod_name;
        private String wh_id;
        private String wh_name;
        private String eve_weight;
        private String speed;
        private String labor_hours;
        private String mac_pre_hours;
        private String mac_hours;
        private String scrap_weight;
        private String mod_weight;
        private String ele_weight;
        private String mod_outline_weight;
        private String mod_inserts_weight;
        private String bush_weight;
        private String mod_amount;
        private String ele_amount;
        private String doc_no;

        public String getDoc_no() {
            return doc_no;
        }

        public void setDoc_no(String doc_no) {
            this.doc_no = doc_no;
        }




        public String getLine_no() {
            return line_no;
        }

        public void setLine_no(String line_no) {
            this.line_no = line_no;
        }



        public String getPro_type() {
            return pro_type;
        }

        public void setPro_type(String pro_type) {
            this.pro_type = pro_type;
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

        public String getOperator_id() {
            return operator_id;
        }

        public void setOperator_id(String operator_id) {
            this.operator_id = operator_id;
        }

        public String getOperator_name() {
            return operator_name;
        }

        public void setOperator_name(String operator_name) {
            this.operator_name = operator_name;
        }

        public String getId() {
            return id;
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

        public String getItem_id() {
            return item_id;
        }

        public void setItem_id(String item_id) {
            this.item_id = item_id;
        }

        public String getClass_team_id() {
            return class_team_id;
        }

        public void setClass_team_id(String class_team_id) {
            this.class_team_id = class_team_id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getComp_dec_id() {
            return comp_dec_id;
        }

        public void setComp_dec_id(String comp_dec_id) {
            this.comp_dec_id = comp_dec_id;
        }

        public String getClass_team_name() {
            return class_team_name;
        }

        public void setClass_team_name(String class_team_name) {
            this.class_team_name = class_team_name;
        }

        public String getMac_code() {
            return mac_code;
        }

        public void setMac_code(String mac_code) {
            this.mac_code = mac_code;
        }

        public String getMod_id() {
            return mod_id;
        }

        public void setMod_id(String mod_id) {
            this.mod_id = mod_id;
        }

        public String getMod_code() {
            return mod_code;
        }

        public void setMod_code(String mod_code) {
            this.mod_code = mod_code;
        }

        public String getMod_name() {
            return mod_name;
        }

        public void setMod_name(String mod_name) {
            this.mod_name = mod_name;
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

        public String getEve_weight() {
            return eve_weight;
        }

        public void setEve_weight(String eve_weight) {
            this.eve_weight = eve_weight;
        }

        public String getSpeed() {
            return speed;
        }

        public void setSpeed(String speed) {
            this.speed = speed;
        }

        public String getLabor_hours() {
            return labor_hours;
        }

        public void setLabor_hours(String labor_hours) {
            this.labor_hours = labor_hours;
        }

        public String getMac_pre_hours() {
            return mac_pre_hours;
        }

        public void setMac_pre_hours(String mac_pre_hours) {
            this.mac_pre_hours = mac_pre_hours;
        }

        public String getMac_hours() {
            return mac_hours;
        }

        public void setMac_hours(String mac_hours) {
            this.mac_hours = mac_hours;
        }

        public String getScrap_weight() {
            return scrap_weight;
        }

        public void setScrap_weight(String scrap_weight) {
            this.scrap_weight = scrap_weight;
        }

        public String getMod_weight() {
            return mod_weight;
        }

        public void setMod_weight(String mod_weight) {
            this.mod_weight = mod_weight;
        }

        public String getEle_weight() {
            return ele_weight;
        }

        public void setEle_weight(String ele_weight) {
            this.ele_weight = ele_weight;
        }

        public String getMod_outline_weight() {
            return mod_outline_weight;
        }

        public void setMod_outline_weight(String mod_outline_weight) {
            this.mod_outline_weight = mod_outline_weight;
        }

        public String getMod_inserts_weight() {
            return mod_inserts_weight;
        }

        public void setMod_inserts_weight(String mod_inserts_weight) {
            this.mod_inserts_weight = mod_inserts_weight;
        }

        public String getBush_weight() {
            return bush_weight;
        }

        public void setBush_weight(String bush_weight) {
            this.bush_weight = bush_weight;
        }

        public String getMod_amount() {
            return mod_amount;
        }

        public void setMod_amount(String mod_amount) {
            this.mod_amount = mod_amount;
        }

        public String getEle_amount() {
            return ele_amount;
        }

        public void setEle_amount(String ele_amount) {
            this.ele_amount = ele_amount;
        }
    }
}
