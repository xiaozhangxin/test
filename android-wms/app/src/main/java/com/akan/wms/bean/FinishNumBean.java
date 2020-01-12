package com.akan.wms.bean;

import java.io.Serializable;

public class FinishNumBean implements Serializable {


    /**
     * id : 1191230152139767
     * qualify_qty : 10
     * un_qualify_qty : 10
     */

    private String id;
    private String qualify_qty;
    private String un_qualify_qty;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQualify_qty() {
        return qualify_qty;
    }

    public void setQualify_qty(String qualify_qty) {
        this.qualify_qty = qualify_qty;
    }

    public String getUn_qualify_qty() {
        return un_qualify_qty;
    }

    public void setUn_qualify_qty(String un_qualify_qty) {
        this.un_qualify_qty = un_qualify_qty;
    }
}
