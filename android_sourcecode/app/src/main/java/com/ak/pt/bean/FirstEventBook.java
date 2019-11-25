package com.ak.pt.bean;

/**
 * Created by admin on 2019/4/17.
 */

public class FirstEventBook {

    private String type;

    private BookNameBean bookNameBean;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BookNameBean getBookNameBean() {
        return bookNameBean;
    }

    public void setBookNameBean(BookNameBean bookNameBean) {
        this.bookNameBean = bookNameBean;
    }

    public FirstEventBook(String type, BookNameBean bookNameBean) {
        this.type = type;
        this.bookNameBean = bookNameBean;
    }
}
