package com.ak.pt.bean;

import java.io.Serializable;

/**
 * Created by admin on 2019/4/17.
 */

public class BookNameBean implements Serializable{
    private String name;
    private String names;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public BookNameBean(String name, String names) {
        this.name = name;
        this.names = names;
    }
}
