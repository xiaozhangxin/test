package com.akan.qf.bean;

import java.io.Serializable;

/**
 * Created by admin on 2019/4/2.
 */


public class FilterBean implements Serializable{
    private String startTime;
    private String endTime;
    private String uuid;
    private String postNames;
    private String orderState;
    private String tableId;
    private String signId;

    public String getSignId() {
        return signId;
    }

    public void setSignId(String signId) {
        this.signId = signId;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }

    public FilterBean(String startTime, String endTime, String uuid, String postNames, String orderState, String tableId,String signId) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.uuid = uuid;
        this.postNames = postNames;
        this.orderState = orderState;
        this.tableId = tableId;
        this.signId=signId;
    }

    public FilterBean(String startTime, String endTime, String uuid, String postNames, String orderState) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.uuid = uuid;
        this.postNames = postNames;
        this.orderState = orderState;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getPostNames() {
        return postNames;
    }

    public void setPostNames(String postNames) {
        this.postNames = postNames;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }
}
