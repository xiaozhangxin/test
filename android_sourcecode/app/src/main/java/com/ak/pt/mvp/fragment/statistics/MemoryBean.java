package com.ak.pt.mvp.fragment.statistics;

import java.io.Serializable;

public class MemoryBean implements Serializable{


    /**
     * name : 1
     * tel :
     * edit_time :
     */

    private String name;
    private String tel;
    private String edit_time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEdit_time() {
        return edit_time;
    }

    public void setEdit_time(String edit_time) {
        this.edit_time = edit_time;
    }
}
