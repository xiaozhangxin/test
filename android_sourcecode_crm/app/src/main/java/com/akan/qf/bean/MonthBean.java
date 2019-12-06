package com.akan.qf.bean;

/**
 * Created by admin on 2018/11/19.
 */

public class MonthBean {
    private String month;
    private String number;

    public MonthBean(String month, String number) {
        this.month = month;
        this.number = number;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
