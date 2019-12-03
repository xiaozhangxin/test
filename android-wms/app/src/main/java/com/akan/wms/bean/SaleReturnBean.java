package com.akan.wms.bean;

import java.io.Serializable;
import java.util.List;

public class SaleReturnBean implements Serializable{


    /**
     * id : 1001901030144348
     * doc_no : 101-RXOUT-201901-0001
     * doc_type_id : 1001601230026620
     * doc_type_name : 普通退货[新]
     * back_type : 0
     * customer_id : 1001812180041498
     * customer_name : 赣州市经济技术开发区利保管道运营店
     * business_date : 2019-01-03 00:00:00
     * org_id : 1001512200010027
     * remark : 退货  合格
     * status : 3
     * update_time : 2019-02-15 10:02:52
     * status_show : 已核准
     * back_type_show : 接收并退款RMA
     * returnLineBeans : [{"id":1001901030144349,"sale_return_id":1001901030144348,"item_id":1001812181629322,"return_qty":3000,"rcv_qty":3000,"status":4,"rcv_org_id":1001512200010678,"wh_id":1001512260168318,"item_code":"11.53.328.6016","item_name":"红色PVC线管迫码(中卡)","item_spec":"Φ16","rcv_org_name":"爱康企业集团湖南分公司","wh_name":"湖南分公司成品仓","status_show":"自然关闭"}]
     */

    private long id;
    private String doc_no;
    private long doc_type_id;
    private String doc_type_name;
    private int back_type;
    private long customer_id;
    private String customer_name;
    private String business_date;
    private long org_id;
    private String remark;
    private int status;
    private String update_time;
    private String status_show;
    private String back_type_show;
    private List<ReturnLineBean> returnLineBeans;

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

    public int getBack_type() {
        return back_type;
    }

    public void setBack_type(int back_type) {
        this.back_type = back_type;
    }

    public long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(long customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getBusiness_date() {
        return business_date;
    }

    public void setBusiness_date(String business_date) {
        this.business_date = business_date;
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

    public String getStatus_show() {
        return status_show;
    }

    public void setStatus_show(String status_show) {
        this.status_show = status_show;
    }

    public String getBack_type_show() {
        return back_type_show;
    }

    public void setBack_type_show(String back_type_show) {
        this.back_type_show = back_type_show;
    }

    public List<ReturnLineBean> getReturnLineBeans() {
        return returnLineBeans;
    }

    public void setReturnLineBeans(List<ReturnLineBean> returnLineBeans) {
        this.returnLineBeans = returnLineBeans;
    }


}
