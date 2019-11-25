package com.ak.pt.bean;

/**
 * Created by admin on 2019/4/16.
 */

public class ImgUpBean {
    private  String type;
    private String utl;
    public ImgUpBean(String type, String utl) {
        this.type = type;
        this.utl = utl;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUtl() {
        return utl;
    }

    public void setUtl(String utl) {
        this.utl = utl;
    }
}
