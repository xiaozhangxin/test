package com.akan.wms.bean;

import java.io.Serializable;
import java.util.List;

public class TransferOutBean implements Serializable {


    /**
     * id : 1
     * doc_type_id : 1001512205410403
     * doc_type_name : 委外加工发料
     * doc_no : 2307753358910292995
     * u9_id :
     * u9_code :
     * org_id : 1001512200010027
     * apply_id : 1001901020011378
     * apply_no : 0022019010001
     * out_org_id : 1001512200010027
     * out_org_name : 爱康企业集团（上海）
     * in_org_id : 1001812110023330
     * in_org_name : 无锡朴乐建材有限公司
     * create_id : 6001
     * create_time : 2019-07-24 11:56:53
     * out_wh_id :
     * out_wh_name :
     * status : 0
     * remark :
     * transfer_direction : 0
     * ap_id :
     * ap_name :
     * create_name : 樊彩红
     * status_show : 配货
     * transfer_direction_show : 普通
     * lineBeanList : [{"id":1,"u9_id":"","out_id":1,"apply_line_id":1001901020011379,"item_id":1001812181696434,"qty":222,"remark":"","wh_id":1001512260168338,"wh_name":"嵌件次品仓","item_name":"灰色PPR冷水管S4","item_code":"12.11.111.203236\u20144","item_spec":"Φ32×3.6(4m)","apply_qty":4800}]
     * barBeanList : [{"id":1,"out_id":1,"item_bar":"12.11.111.203236\u20144","wh_id":1001512260168338,"qty":222,"item_id":1001812181696434,"item_code":"12.11.111.203236\u20144","item_name":"灰色PPR冷水管S4","item_spec":"Φ32×3.6(4m)"}]
     */
    private List<RecordsBean> recordsBeans;
    private String id;
    private long doc_type_id;

    public List<RecordsBean> getRecordsBeans() {
        return recordsBeans;
    }

    public void setRecordsBeans(List<RecordsBean> recordsBeans) {
        this.recordsBeans = recordsBeans;
    }

    private String doc_type_name;
    private String doc_no;
    private String u9_id;
    private String u9_code;
    private long org_id;
    private long apply_id;
    private String apply_no;
    private long out_org_id;
    private String out_org_name;
    private long in_org_id;
    private String in_org_name;
    private int create_id;
    private String create_time;
    private String out_wh_id;
    private String out_wh_name;
    private int status;
    private String remark;
    private int transfer_direction;
    private String ap_id;
    private String ap_name;
    private String create_name;
    private String status_show;
    private String transfer_direction_show;
    private List<LineBeanListBean> lineBeanList;
    private List<BarVerificationListsBean> barBeanList;
    private List<BarBean> barList;

    public List<BarBean> getBarList() {
        return barList;
    }

    public void setBarList(List<BarBean> barList) {
        this.barList = barList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public long getOrg_id() {
        return org_id;
    }

    public void setOrg_id(long org_id) {
        this.org_id = org_id;
    }

    public long getApply_id() {
        return apply_id;
    }

    public void setApply_id(long apply_id) {
        this.apply_id = apply_id;
    }

    public String getApply_no() {
        return apply_no;
    }

    public void setApply_no(String apply_no) {
        this.apply_no = apply_no;
    }

    public long getOut_org_id() {
        return out_org_id;
    }

    public void setOut_org_id(long out_org_id) {
        this.out_org_id = out_org_id;
    }

    public String getOut_org_name() {
        return out_org_name;
    }

    public void setOut_org_name(String out_org_name) {
        this.out_org_name = out_org_name;
    }

    public long getIn_org_id() {
        return in_org_id;
    }

    public void setIn_org_id(long in_org_id) {
        this.in_org_id = in_org_id;
    }

    public String getIn_org_name() {
        return in_org_name;
    }

    public void setIn_org_name(String in_org_name) {
        this.in_org_name = in_org_name;
    }

    public int getCreate_id() {
        return create_id;
    }

    public void setCreate_id(int create_id) {
        this.create_id = create_id;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getOut_wh_id() {
        return out_wh_id;
    }

    public void setOut_wh_id(String out_wh_id) {
        this.out_wh_id = out_wh_id;
    }

    public String getOut_wh_name() {
        return out_wh_name;
    }

    public void setOut_wh_name(String out_wh_name) {
        this.out_wh_name = out_wh_name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getTransfer_direction() {
        return transfer_direction;
    }

    public void setTransfer_direction(int transfer_direction) {
        this.transfer_direction = transfer_direction;
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

    public String getCreate_name() {
        return create_name;
    }

    public void setCreate_name(String create_name) {
        this.create_name = create_name;
    }

    public String getStatus_show() {
        return status_show;
    }

    public void setStatus_show(String status_show) {
        this.status_show = status_show;
    }

    public String getTransfer_direction_show() {
        return transfer_direction_show;
    }

    public void setTransfer_direction_show(String transfer_direction_show) {
        this.transfer_direction_show = transfer_direction_show;
    }

    public List<LineBeanListBean> getLineBeanList() {
        return lineBeanList;
    }

    public void setLineBeanList(List<LineBeanListBean> lineBeanList) {
        this.lineBeanList = lineBeanList;
    }

    public List<BarVerificationListsBean> getBarBeanList() {
        return barBeanList;
    }

    public void setBarBeanList(List<BarVerificationListsBean> barBeanList) {
        this.barBeanList = barBeanList;
    }

    public static class LineBeanListBean implements Serializable {
        /**
         * id : 1
         * u9_id :
         * out_id : 1
         * apply_line_id : 1001901020011379
         * item_id : 1001812181696434
         * qty : 222
         * remark :
         * wh_id : 1001512260168338
         * wh_name : 嵌件次品仓
         * item_name : 灰色PPR冷水管S4
         * item_code : 12.11.111.203236—4
         * item_spec : Φ32×3.6(4m)
         * apply_qty : 4800
         */

        private String id;
        private String u9_id;
        private String out_id;
        private String apply_line_id;
        private String item_id;
        private int qty;
        private int send_qty;
        private int point_qty;
        private String remark;
        private String wh_id;
        private String wh_name;
        private String item_name;
        private String item_code;
        private String item_spec;
        private String item_bar;
        private String in_wh;
        private String in_wh_name;



        private int apply_qty;
        private boolean isCheck;

        public String getIn_wh() {
            return in_wh;
        }

        public void setIn_wh(String in_wh) {
            this.in_wh = in_wh;
        }

        public String getIn_wh_name() {
            return in_wh_name;
        }

        public void setIn_wh_name(String in_wh_name) {
            this.in_wh_name = in_wh_name;
        }

        public int getPoint_qty() {
            return point_qty;
        }

        public void setPoint_qty(int point_qty) {
            this.point_qty = point_qty;
        }

        public int getSend_qty() {
            return send_qty;
        }

        public void setSend_qty(int send_qty) {
            this.send_qty = send_qty;
        }

        public String getItem_bar() {
            return item_bar;
        }

        public void setItem_bar(String item_bar) {
            this.item_bar = item_bar;
        }

        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getU9_id() {
            return u9_id;
        }

        public void setU9_id(String u9_id) {
            this.u9_id = u9_id;
        }

        public String getOut_id() {
            return out_id;
        }

        public void setOut_id(String out_id) {
            this.out_id = out_id;
        }

        public String getApply_line_id() {
            return apply_line_id;
        }

        public void setApply_line_id(String apply_line_id) {
            this.apply_line_id = apply_line_id;
        }

        public String getItem_id() {
            return item_id;
        }

        public void setItem_id(String item_id) {
            this.item_id = item_id;
        }

        public int getQty() {
            return qty;
        }

        public void setQty(int qty) {
            this.qty = qty;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
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

        public int getApply_qty() {
            return apply_qty;
        }

        public void setApply_qty(int apply_qty) {
            this.apply_qty = apply_qty;
        }
    }


}
