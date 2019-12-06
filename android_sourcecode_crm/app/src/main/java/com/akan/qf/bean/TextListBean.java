package com.akan.qf.bean;

import java.io.Serializable;

/**
 * Created by admin on 2018/12/12.
 */

public class TextListBean implements Serializable {
    /**
     * text_price : 报销项价格
     * text_subject : 报销科目
     * text_number : 科目数量
     * text_info : 科目简介
     */

    private String text_price;
    private String text_subject;
    private String text_number;
    private String text_info;

    public TextListBean(String text_price, String text_subject, String text_number, String text_info) {
        this.text_price = text_price;
        this.text_subject = text_subject;
        this.text_number = text_number;
        this.text_info = text_info;
    }

    public String getText_price() {
        return text_price;
    }

    public void setText_price(String text_price) {
        this.text_price = text_price;
    }

    public String getText_subject() {
        return text_subject;
    }

    public void setText_subject(String text_subject) {
        this.text_subject = text_subject;
    }

    public String getText_number() {
        return text_number;
    }

    public void setText_number(String text_number) {
        this.text_number = text_number;
    }

    public String getText_info() {
        return text_info;
    }

    public void setText_info(String text_info) {
        this.text_info = text_info;
    }
}
