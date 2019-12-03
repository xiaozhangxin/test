package com.akan.wms.bean;

import java.io.Serializable;

public class MfcBean implements Serializable{


    /**
     * mfc_code : 99087
     * mfc_name : 浙江七色鹿 9043
     * mfc : 1001812180487270
     */

    private String mfc_code;
    private String mfc_name;
    private String mfc;
    private String qty;

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getMfc_code() {
        return mfc_code;
    }

    public void setMfc_code(String mfc_code) {
        this.mfc_code = mfc_code;
    }

    public String getMfc_name() {
        return mfc_name;
    }

    public void setMfc_name(String mfc_name) {
        this.mfc_name = mfc_name;
    }

    public String getMfc() {
        return mfc;
    }

    public void setMfc(String mfc) {
        this.mfc = mfc;
    }
}
