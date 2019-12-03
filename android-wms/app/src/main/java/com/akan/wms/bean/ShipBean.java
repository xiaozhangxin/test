package com.akan.wms.bean;

import java.io.Serializable;
import java.util.List;

public class ShipBean implements Serializable{


    /**
     * id : 1001906200157116
     * doc_no : 101-QOUT-201906-0043
     * org_id : 1001512200010027
     * business_date : 2019-06-20 00:00:00
     * status : 2
     * doc_type_id : 1001512270011216
     * doc_type_name : 高值礼品出库
     * update_time : 2019-06-20 19:49:13
     * wh_man_id : 1001811010660699
     * center_def_code : 04
     * use_def_code : 06
     * manage_group_code : 12
     * wh_man_name : 周宇
     * center_def_name : 生产技术中心
     * use_def_name : 供应中心
     * manage_group_name : 总经办
     * status_show : 已审核
     * start_time :
     * end_time :
     * lineBeanList : [{"id":1001906200157117,"trans_id":1001906200157116,"item_id":1001812190204414,"qty":1,"wh_id":1001512260168343,"benefit_dep_id":1001712280800216,"update_time":"2019-06-20 19:49:13","wh_name":"材料仓","benefit_dep_name":"上海市场部","item_code":"00.01.012.0002","item_name":"PPR管材专用助剂粉料","item_spec":""}]
     */
    private List<RecordsBean> recordsBeans;
    private long id;
    private String doc_no;

    public List<RecordsBean> getRecordsBeans() {
        return recordsBeans;
    }

    public void setRecordsBeans(List<RecordsBean> recordsBeans) {
        this.recordsBeans = recordsBeans;
    }

    private long org_id;
    private String business_date;
    private int status;
    private long doc_type_id;
    private String doc_type_name;
    private String update_time;
    private long wh_man_id;
    private String center_def_code;
    private String use_def_code;
    private String manage_group_code;
    private String wh_man_name;
    private String center_def_name;
    private String use_def_name;
    private String manage_group_name;
    private String status_show;
    private String start_time;
    private String end_time;
    private List<LineBeanListBean> lineBeanList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDoc_no() {
        return doc_no;
    }

    public void setDoc_no(String doc_no) {
        this.doc_no = doc_no;
    }

    public long getOrg_id() {
        return org_id;
    }

    public void setOrg_id(long org_id) {
        this.org_id = org_id;
    }

    public String getBusiness_date() {
        return business_date;
    }

    public void setBusiness_date(String business_date) {
        this.business_date = business_date;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getDoc_type_id() {
        return doc_type_id;
    }

    public void setDoc_type_id(long doc_type_id) {
        this.doc_type_id = doc_type_id;
    }

    public String getDoc_type_name() {
        return doc_type_name;
    }

    public void setDoc_type_name(String doc_type_name) {
        this.doc_type_name = doc_type_name;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public long getWh_man_id() {
        return wh_man_id;
    }

    public void setWh_man_id(long wh_man_id) {
        this.wh_man_id = wh_man_id;
    }

    public String getCenter_def_code() {
        return center_def_code;
    }

    public void setCenter_def_code(String center_def_code) {
        this.center_def_code = center_def_code;
    }

    public String getUse_def_code() {
        return use_def_code;
    }

    public void setUse_def_code(String use_def_code) {
        this.use_def_code = use_def_code;
    }

    public String getManage_group_code() {
        return manage_group_code;
    }

    public void setManage_group_code(String manage_group_code) {
        this.manage_group_code = manage_group_code;
    }

    public String getWh_man_name() {
        return wh_man_name;
    }

    public void setWh_man_name(String wh_man_name) {
        this.wh_man_name = wh_man_name;
    }

    public String getCenter_def_name() {
        return center_def_name;
    }

    public void setCenter_def_name(String center_def_name) {
        this.center_def_name = center_def_name;
    }

    public String getUse_def_name() {
        return use_def_name;
    }

    public void setUse_def_name(String use_def_name) {
        this.use_def_name = use_def_name;
    }

    public String getManage_group_name() {
        return manage_group_name;
    }

    public void setManage_group_name(String manage_group_name) {
        this.manage_group_name = manage_group_name;
    }

    public String getStatus_show() {
        return status_show;
    }

    public void setStatus_show(String status_show) {
        this.status_show = status_show;
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

    public static class LineBeanListBean {
        /**
         * id : 1001906200157117
         * trans_id : 1001906200157116
         * item_id : 1001812190204414
         * qty : 1
         * wh_id : 1001512260168343
         * benefit_dep_id : 1001712280800216
         * update_time : 2019-06-20 19:49:13
         * wh_name : 材料仓
         * benefit_dep_name : 上海市场部
         * item_code : 00.01.012.0002
         * item_name : PPR管材专用助剂粉料
         * item_spec :
         */

        private long id;
        private long trans_id;
        private long item_id;
        private int qty;
        private long wh_id;
        private long benefit_dep_id;
        private String update_time;
        private String wh_name;
        private String benefit_dep_name;
        private String item_code;
        private String item_name;
        private String item_spec;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public long getTrans_id() {
            return trans_id;
        }

        public void setTrans_id(long trans_id) {
            this.trans_id = trans_id;
        }

        public long getItem_id() {
            return item_id;
        }

        public void setItem_id(long item_id) {
            this.item_id = item_id;
        }

        public int getQty() {
            return qty;
        }

        public void setQty(int qty) {
            this.qty = qty;
        }

        public long getWh_id() {
            return wh_id;
        }

        public void setWh_id(long wh_id) {
            this.wh_id = wh_id;
        }

        public long getBenefit_dep_id() {
            return benefit_dep_id;
        }

        public void setBenefit_dep_id(long benefit_dep_id) {
            this.benefit_dep_id = benefit_dep_id;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }

        public String getWh_name() {
            return wh_name;
        }

        public void setWh_name(String wh_name) {
            this.wh_name = wh_name;
        }

        public String getBenefit_dep_name() {
            return benefit_dep_name;
        }

        public void setBenefit_dep_name(String benefit_dep_name) {
            this.benefit_dep_name = benefit_dep_name;
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
    }
}
