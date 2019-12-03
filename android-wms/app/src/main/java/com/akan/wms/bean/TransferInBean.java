package com.akan.wms.bean;

import java.io.Serializable;
import java.util.List;

public class TransferInBean implements Serializable{


    /**
     * id : 4
     * doc_type_id : 1001512205410302
     * doc_type_name : 仓库转储
     * org_id : 1001512200010027
     * doc_no : 2308542056841085956
     * u9_id :
     * u9_code :
     * apply_id : 1001901020011378
     * out_id : 1
     * out_no : 2307753358910292995
     * out_org_id : 1001512200010027
     * out_org_name : 爱康企业集团（上海）
     * in_org_id : 1001812110023330
     * in_org_name : 无锡朴乐建材有限公司
     * create_id : 6001
     * create_time : 2019-07-25 14:04:59
     * ap_id :
     * ap_name :
     * status : 0
     * in_wh_id : 1001512260168338
     * in_wh_name : 嵌件次品仓
     * remark :
     * status_show : 点收
     * create_name : 樊彩红
     * lineBeanList : [{"id":2,"u9_id":"","in_id":4,"out_line_id":1,"item_id":1001812181696434,"qty":10,"wh_id":1001512260168338,"wh_name":"嵌件次品仓","item_name":"灰色PPR冷水管S4","item_code":"12.11.111.203236\u20144","item_spec":"Φ32×3.6(4m)","out_qty":""}]
     * barBeanList : [{"id":2,"in_id":4,"item_bar":"12.11.111.203236\u20144","item_id":1001812181696434,"item_code":"12.11.111.203236\u20144","item_name":"灰色PPR冷水管S4","wh_id":1001512260168338}]
     */
    private List<RecordsBean> recordsBeans;

    public List<RecordsBean> getRecordsBeans() {
        return recordsBeans;
    }

    public void setRecordsBeans(List<RecordsBean> recordsBeans) {
        this.recordsBeans = recordsBeans;
    }

    private String id;
    private String doc_type_id;
    private String doc_type_name;
    private String org_id;
    private String doc_no;
    private String u9_id;
    private String u9_code;
    private String apply_id;
    private String out_id;
    private String out_no;
    private String out_org_id;
    private String out_org_name;
    private String in_org_id;
    private String in_org_name;
    private String create_id;
    private String create_time;
    private String ap_id;
    private String ap_name;
    private String status;
    private String in_wh_id;
    private String in_wh_name;
    private String remark;
    private String status_show;
    private String create_name;
    private List<LineBeanListBean> lineBeanList;
    private List<BarBeanListBean> barBeanList;



    public String getDoc_type_name() {
        return doc_type_name;
    }

    public void setDoc_type_name(String doc_type_name) {
        this.doc_type_name = doc_type_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDoc_type_id() {
        return doc_type_id;
    }

    public void setDoc_type_id(String doc_type_id) {
        this.doc_type_id = doc_type_id;
    }

    public String getOrg_id() {
        return org_id;
    }

    public void setOrg_id(String org_id) {
        this.org_id = org_id;
    }

    public String getApply_id() {
        return apply_id;
    }

    public void setApply_id(String apply_id) {
        this.apply_id = apply_id;
    }

    public String getOut_id() {
        return out_id;
    }

    public void setOut_id(String out_id) {
        this.out_id = out_id;
    }

    public String getOut_org_id() {
        return out_org_id;
    }

    public void setOut_org_id(String out_org_id) {
        this.out_org_id = out_org_id;
    }

    public String getIn_org_id() {
        return in_org_id;
    }

    public void setIn_org_id(String in_org_id) {
        this.in_org_id = in_org_id;
    }

    public String getCreate_id() {
        return create_id;
    }

    public void setCreate_id(String create_id) {
        this.create_id = create_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIn_wh_id() {
        return in_wh_id;
    }

    public void setIn_wh_id(String in_wh_id) {
        this.in_wh_id = in_wh_id;
    }

    public String getDoc_no() {
        return doc_no;
    }

    public void setDoc_no(String doc_no) {
        this.doc_no = doc_no;
    }

    public String getU9_id() {
        return u9_id;
    }

    public void setU9_id(String u9_id) {
        this.u9_id = u9_id;
    }

    public String getU9_code() {
        return u9_code;
    }

    public void setU9_code(String u9_code) {
        this.u9_code = u9_code;
    }



    public String getOut_no() {
        return out_no;
    }

    public void setOut_no(String out_no) {
        this.out_no = out_no;
    }


    public String getOut_org_name() {
        return out_org_name;
    }

    public void setOut_org_name(String out_org_name) {
        this.out_org_name = out_org_name;
    }


    public String getIn_org_name() {
        return in_org_name;
    }

    public void setIn_org_name(String in_org_name) {
        this.in_org_name = in_org_name;
    }



    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getAp_id() {
        return ap_id;
    }

    public void setAp_id(String ap_id) {
        this.ap_id = ap_id;
    }

    public String getAp_name() {
        return ap_name;
    }

    public void setAp_name(String ap_name) {
        this.ap_name = ap_name;
    }



    public String getIn_wh_name() {
        return in_wh_name;
    }

    public void setIn_wh_name(String in_wh_name) {
        this.in_wh_name = in_wh_name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus_show() {
        return status_show;
    }

    public void setStatus_show(String status_show) {
        this.status_show = status_show;
    }

    public String getCreate_name() {
        return create_name;
    }

    public void setCreate_name(String create_name) {
        this.create_name = create_name;
    }

    public List<LineBeanListBean> getLineBeanList() {
        return lineBeanList;
    }

    public void setLineBeanList(List<LineBeanListBean> lineBeanList) {
        this.lineBeanList = lineBeanList;
    }

    public List<BarBeanListBean> getBarBeanList() {
        return barBeanList;
    }

    public void setBarBeanList(List<BarBeanListBean> barBeanList) {
        this.barBeanList = barBeanList;
    }

    public static class LineBeanListBean implements Serializable{
        /**
         * id : 2
         * u9_id :
         * in_id : 4
         * out_line_id : 1
         * item_id : 1001812181696434
         * qty : 10
         * wh_id : 1001512260168338
         * wh_name : 嵌件次品仓
         * item_name : 灰色PPR冷水管S4
         * item_code : 12.11.111.203236—4
         * item_spec : Φ32×3.6(4m)
         * out_qty :
         */

        private int id;
        private String u9_id;
        private int in_id;
        private int out_line_id;
        private long item_id;
        private int qty;
        private long wh_id;
        private String wh_name;
        private String item_name;
        private String item_code;
        private String item_spec;
        private String out_qty;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getU9_id() {
            return u9_id;
        }

        public void setU9_id(String u9_id) {
            this.u9_id = u9_id;
        }

        public int getIn_id() {
            return in_id;
        }

        public void setIn_id(int in_id) {
            this.in_id = in_id;
        }

        public int getOut_line_id() {
            return out_line_id;
        }

        public void setOut_line_id(int out_line_id) {
            this.out_line_id = out_line_id;
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

        public String getWh_name() {
            return wh_name;
        }

        public void setWh_name(String wh_name) {
            this.wh_name = wh_name;
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

        public String getOut_qty() {
            return out_qty;
        }

        public void setOut_qty(String out_qty) {
            this.out_qty = out_qty;
        }
    }

    public static class BarBeanListBean implements Serializable{
        /**
         * id : 2
         * in_id : 4
         * item_bar : 12.11.111.203236—4
         * item_id : 1001812181696434
         * item_code : 12.11.111.203236—4
         * item_name : 灰色PPR冷水管S4
         * wh_id : 1001512260168338
         */

        private int id;
        private int in_id;
        private String item_bar;
        private long item_id;
        private String item_code;
        private String item_name;
        private long wh_id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getIn_id() {
            return in_id;
        }

        public void setIn_id(int in_id) {
            this.in_id = in_id;
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

        public long getWh_id() {
            return wh_id;
        }

        public void setWh_id(long wh_id) {
            this.wh_id = wh_id;
        }
    }
}
