package com.akan.wms.bean;

import java.io.Serializable;
import java.util.List;

public class WhTransferApplyBean implements Serializable{


    /**
     * id : 1001901020011378
     * doc_no : 0022019010001
     * doc_type_id : 1001512314860384
     * doc_type_name : 成品调拨申请单
     * business_date : 2019-01-02 00:00:00
     * in_org_id : 1001512200010027
     * out_org_id : 1001512200010678
     * tra_type : 0
     * is_closed : 1
     * status : 2
     * update_time : 2019-01-05 13:45:15
     * org_id : 1001512200010027
     * remark : 1月2号 湖南发上海
     * status_show : 已审核
     * in_org_name : 爱康企业集团（上海）
     * out_org_name : 爱康企业集团湖南分公司
     * tra_type_show : 组织间调拨
     * lineBeanList : [{"id":1001901020011379,"apply_id":1001901020011378,"item_id":1001812181696434,"apply_qty":4800,"out_qty":4800,"in_qty":4800,"status":3,"update_time":"2019-01-05 13:45:15","item_code":"12.11.111.203236\u20144","item_name":"灰色PPR冷水管S4","item_spec":"Φ32×3.6(4m)","status_show":"自然关闭"},{"id":1001901020011380,"apply_id":1001901020011378,"item_id":1001812181708292,"apply_qty":7200,"out_qty":7200,"in_qty":7200,"status":3,"update_time":"2019-01-05 13:45:15","item_code":"12.11.112.203244\u20144","item_name":"灰色PPR热水管S3.2","item_spec":"Φ32×4.4(4m)","status_show":"自然关闭"},{"id":1001901020011381,"apply_id":1001901020011378,"item_id":1001812181696676,"apply_qty":640,"out_qty":640,"in_qty":640,"status":3,"update_time":"2019-01-05 13:45:15","item_code":"12.11.111.204037\u20144","item_name":"灰色PPR冷水管S5","item_spec":"Φ40×3.7(4m)","status_show":"自然关闭"},{"id":1001901020011382,"apply_id":1001901020011378,"item_id":1001812181702968,"apply_qty":3200,"out_qty":3200,"in_qty":3200,"status":3,"update_time":"2019-01-05 13:45:15","item_code":"12.11.112.104055\u20144","item_name":"白色PPR热水管S3.2","item_spec":"Φ40×5.5(4m)","status_show":"自然关闭"},{"id":1001901020011383,"apply_id":1001901020011378,"item_id":1001812181703452,"apply_qty":1000,"out_qty":1000,"in_qty":1000,"status":3,"update_time":"2019-01-05 13:45:15","item_code":"12.11.112.105069\u20144","item_name":"白色PPR热水管S3.2","item_spec":"Φ50×6.9(4m)","status_show":"自然关闭"},{"id":1001901020011384,"apply_id":1001901020011378,"item_id":1001812181709260,"apply_qty":4000,"out_qty":4000,"in_qty":4000,"status":3,"update_time":"2019-01-05 13:45:15","item_code":"12.11.112.205069\u20144","item_name":"灰色PPR热水管S3.2","item_spec":"Φ50×6.9(4m)","status_show":"自然关闭"},{"id":1001901020011385,"apply_id":1001901020011378,"item_id":1001812180469120,"apply_qty":6000,"out_qty":6000,"in_qty":6000,"status":3,"update_time":"2019-01-05 13:45:15","item_code":"11.11.116.203236","item_name":"灰色PPR热水管空调系统专用S4","item_spec":"Φ32×3.6","status_show":"自然关闭"},{"id":1001901020011386,"apply_id":1001901020011378,"item_id":1001812180469604,"apply_qty":3000,"out_qty":3000,"in_qty":3000,"status":3,"update_time":"2019-01-05 13:45:15","item_code":"11.11.116.205056","item_name":"灰色PPR热水管空调系统专用S4","item_spec":"Φ50×5.6","status_show":"自然关闭"},{"id":1001901020011387,"apply_id":1001901020011378,"item_id":1001812180469846,"apply_qty":1800,"out_qty":1800,"in_qty":1800,"status":3,"update_time":"2019-01-05 13:45:15","item_code":"11.11.116.206371","item_name":"灰色PPR热水管空调系统专用S4","item_spec":"Φ63×7.1","status_show":"自然关闭"},{"id":1001901020011388,"apply_id":1001901020011378,"item_id":1001812181697644,"apply_qty":120,"out_qty":120,"in_qty":120,"status":3,"update_time":"2019-01-05 13:45:15","item_code":"12.11.111.206358\u20144","item_name":"灰色PPR冷水管S5","item_spec":"Φ63×5.8(4m)","status_show":"自然关闭"},{"id":1001901020011389,"apply_id":1001901020011378,"item_id":1001812181698128,"apply_qty":1200,"out_qty":1200,"in_qty":1200,"status":3,"update_time":"2019-01-05 13:45:15","item_code":"12.11.111.206371\u20144","item_name":"灰色PPR冷水管S4","item_spec":"Φ63×7.1(4m)","status_show":"自然关闭"},{"id":1001901020011390,"apply_id":1001901020011378,"item_id":1001812181710228,"apply_qty":400,"out_qty":400,"in_qty":400,"status":3,"update_time":"2019-01-05 13:45:15","item_code":"12.11.112.2075103\u20144","item_name":"灰色PPR热水管S3.2","item_spec":"Φ75×10.3(4m)","status_show":"自然关闭"},{"id":1001901020011391,"apply_id":1001901020011378,"item_id":1001812181694256,"apply_qty":200,"out_qty":200,"in_qty":200,"status":3,"update_time":"2019-01-05 13:44:57","item_code":"12.11.111.20160146\u20144","item_name":"灰色PPR冷水管S5","item_spec":"Φ160×14.6(4m)","status_show":"自然关闭"}]
     */

    private long id;
    private String doc_no;
    private long doc_type_id;
    private String doc_type_name;
    private String business_date;
    private long in_org_id;
    private long out_org_id;
    private int tra_type;
    private int is_closed;
    private int status;

    public List<BarBean> getBarList() {
        return barList;
    }

    public void setBarList(List<BarBean> barList) {
        this.barList = barList;
    }

    private String update_time;
    private long org_id;
    private String remark;
    private String status_show;
    private String in_org_name;
    private String out_org_name;
    private String tra_type_show;
    private List<LineBeanListBean> lineBeanList;private List<BarBean> barList;

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

    public String getBusiness_date() {
        return business_date;
    }

    public void setBusiness_date(String business_date) {
        this.business_date = business_date;
    }

    public long getIn_org_id() {
        return in_org_id;
    }

    public void setIn_org_id(long in_org_id) {
        this.in_org_id = in_org_id;
    }

    public long getOut_org_id() {
        return out_org_id;
    }

    public void setOut_org_id(long out_org_id) {
        this.out_org_id = out_org_id;
    }

    public int getTra_type() {
        return tra_type;
    }

    public void setTra_type(int tra_type) {
        this.tra_type = tra_type;
    }

    public int getIs_closed() {
        return is_closed;
    }

    public void setIs_closed(int is_closed) {
        this.is_closed = is_closed;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public long getOrg_id() {
        return org_id;
    }

    public void setOrg_id(long org_id) {
        this.org_id = org_id;
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

    public String getIn_org_name() {
        return in_org_name;
    }

    public void setIn_org_name(String in_org_name) {
        this.in_org_name = in_org_name;
    }

    public String getOut_org_name() {
        return out_org_name;
    }

    public void setOut_org_name(String out_org_name) {
        this.out_org_name = out_org_name;
    }

    public String getTra_type_show() {
        return tra_type_show;
    }

    public void setTra_type_show(String tra_type_show) {
        this.tra_type_show = tra_type_show;
    }

    public List<LineBeanListBean> getLineBeanList() {
        return lineBeanList;
    }

    public void setLineBeanList(List<LineBeanListBean> lineBeanList) {
        this.lineBeanList = lineBeanList;
    }

    public static class LineBeanListBean implements Serializable {
        /**
         * id : 1001901020011379
         * apply_id : 1001901020011378
         * item_id : 1001812181696434
         * apply_qty : 4800
         * out_qty : 4800
         * in_qty : 4800
         * status : 3
         * update_time : 2019-01-05 13:45:15
         * item_code : 12.11.111.203236—4
         * item_name : 灰色PPR冷水管S4
         * item_spec : Φ32×3.6(4m)
         * status_show : 自然关闭
         */

        private long id;
        private long apply_id;
        private String item_id;
        private int apply_qty;
        private int out_qty;
        private int in_qty;
        private int send_qty;
        private int status;
        private String update_time;
        private String item_code;
        private String item_name;
        private String wh_name;
        private String wh_id;
        private String item_bar;

        public String getItem_bar() {
            return item_bar;
        }

        public void setItem_bar(String item_bar) {
            this.item_bar = item_bar;
        }

        public String getWh_name() {
            return wh_name;
        }

        public void setWh_name(String wh_name) {
            this.wh_name = wh_name;
        }

        public String getWh_id() {
            return wh_id;
        }

        public void setWh_id(String wh_id) {
            this.wh_id = wh_id;
        }

        public int getSend_qty() {
            return send_qty;
        }

        public void setSend_qty(int send_qty) {
            this.send_qty = send_qty;
        }

        private String item_spec;
        private String status_show;
        private  boolean isCheck;

        public boolean isCheck() {
            return isCheck;
        }

        public void setCheck(boolean check) {
            isCheck = check;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public long getApply_id() {
            return apply_id;
        }

        public void setApply_id(long apply_id) {
            this.apply_id = apply_id;
        }

        public String getItem_id() {
            return item_id;
        }

        public void setItem_id(String item_id) {
            this.item_id = item_id;
        }

        public int getApply_qty() {
            return apply_qty;
        }

        public void setApply_qty(int apply_qty) {
            this.apply_qty = apply_qty;
        }

        public int getOut_qty() {
            return out_qty;
        }

        public void setOut_qty(int out_qty) {
            this.out_qty = out_qty;
        }

        public int getIn_qty() {
            return in_qty;
        }

        public void setIn_qty(int in_qty) {
            this.in_qty = in_qty;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
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

        public String getStatus_show() {
            return status_show;
        }

        public void setStatus_show(String status_show) {
            this.status_show = status_show;
        }
    }
}
