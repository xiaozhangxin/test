package com.akan.qf.bean;

/**
 * Created by admin on 2018/11/20.
 */

public class AnalysisBean {
    private String month;
    private String sale;
    private String complete;
    private String up;

    public AnalysisBean(String month, String sale, String complete, String up) {
        this.month = month;
        this.sale = sale;
        this.complete = complete;
        this.up = up;
    }

    public String getMonth() {
        return month;
    }

    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }

    public String getComplete() {
        return complete;
    }

    public void setComplete(String complete) {
        this.complete = complete;
    }

    public String getUp() {
        return up;
    }

    public void setUp(String up) {
        this.up = up;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
