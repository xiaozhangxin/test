package com.akan.wms.bean;

import java.io.Serializable;
import java.util.List;

public class DeliverGoodsBean implements Serializable {


    /**
     * id : 1190716145408631
     * supplier_id : 12009
     * supplier_name : 台州市中豪机械有限公司
     * status : 0
     * status_name : 已发货
     * is_delete :
     * send_time : 2019-07-16 14:54:52
     * receive_time :
     * creator_id : 6003
     * creator_name : AK128
     * express_company : null
     * express_number : null
     * description : null
     * org_id : 1001512200010027
     * org_name : null
     * bar_code : null
     * purchases : null
     * supplier_receives : null
     * tag : null
     */

    private String id;
    private String org_id;
    private String supplier_id;
    private String supplier_name;
    private String status;
    private String status_name;
    private String is_delete;
    private String send_time;
    private String receive_time;
    private String creator_id;
    private String creator_name;
    private String doc_no;
    private List<PurchasesBean> purchases;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSupplier_id() {
        return supplier_id;
    }

    public void setSupplier_id(String supplier_id) {
        this.supplier_id = supplier_id;
    }

    public String getSupplier_name() {
        return supplier_name;
    }

    public void setSupplier_name(String supplier_name) {
        this.supplier_name = supplier_name;
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

    public String getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(String is_delete) {
        this.is_delete = is_delete;
    }

    public String getSend_time() {
        return send_time;
    }

    public void setSend_time(String send_time) {
        this.send_time = send_time;
    }

    public String getReceive_time() {
        return receive_time;
    }

    public void setReceive_time(String receive_time) {
        this.receive_time = receive_time;
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

    public String getOrg_id() {
        return org_id;
    }

    public void setOrg_id(String org_id) {
        this.org_id = org_id;
    }

    public List<PurchasesBean> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<PurchasesBean> purchases) {
        this.purchases = purchases;
    }

}
