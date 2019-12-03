package com.akan.wms.bean;

import java.io.Serializable;

public class ReturnLineBean implements Serializable {
    /**
     * id : 1001901030144349
     * sale_return_id : 1001901030144348
     * item_id : 1001812181629322
     * return_qty : 3000
     * rcv_qty : 3000
     * status : 4
     * rcv_org_id : 1001512200010678
     * wh_id : 1001512260168318
     * item_code : 11.53.328.6016
     * item_name : 红色PVC线管迫码(中卡)
     * item_spec : Φ16
     * rcv_org_name : 爱康企业集团湖南分公司
     * wh_name : 湖南分公司成品仓
     * status_show : 自然关闭
     */

    private long id;
    private long sale_return_id;
    private long item_id;
    private int return_qty;
    private int rcv_qty;
    private int send_qty;
    private int status;
    private long rcv_org_id;
    private String wh_id;
    private String item_code;
    private String item_name;
    private String item_spec;
    private String rcv_org_name;
    private String wh_name;

    public int getSend_qty() {
        return send_qty;
    }

    public void setSend_qty(int send_qty) {
        this.send_qty = send_qty;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    private String status_show;
    private boolean isCheck;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSale_return_id() {
        return sale_return_id;
    }

    public void setSale_return_id(long sale_return_id) {
        this.sale_return_id = sale_return_id;
    }

    public long getItem_id() {
        return item_id;
    }

    public void setItem_id(long item_id) {
        this.item_id = item_id;
    }

    public int getReturn_qty() {
        return return_qty;
    }

    public void setReturn_qty(int return_qty) {
        this.return_qty = return_qty;
    }

    public int getRcv_qty() {
        return rcv_qty;
    }

    public void setRcv_qty(int rcv_qty) {
        this.rcv_qty = rcv_qty;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public long getRcv_org_id() {
        return rcv_org_id;
    }

    public void setRcv_org_id(long rcv_org_id) {
        this.rcv_org_id = rcv_org_id;
    }

    public String getWh_id() {
        return wh_id;
    }

    public void setWh_id(String wh_id) {
        this.wh_id = wh_id;
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

    public String getRcv_org_name() {
        return rcv_org_name;
    }

    public void setRcv_org_name(String rcv_org_name) {
        this.rcv_org_name = rcv_org_name;
    }

    public String getWh_name() {
        return wh_name;
    }

    public void setWh_name(String wh_name) {
        this.wh_name = wh_name;
    }

    public String getStatus_show() {
        return status_show;
    }

    public void setStatus_show(String status_show) {
        this.status_show = status_show;
    }
}