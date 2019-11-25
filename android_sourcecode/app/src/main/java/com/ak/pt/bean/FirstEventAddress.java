package com.ak.pt.bean;

/**
 * Created by admin on 2019/4/17.
 */

public class FirstEventAddress {

    private String type;

    private AddressBean addressBean;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public AddressBean getAddressBean() {
        return addressBean;
    }

    public void setAddressBean(AddressBean addressBean) {
        this.addressBean = addressBean;
    }

    public FirstEventAddress(String type, AddressBean addressBean) {
        this.type = type;
        this.addressBean = addressBean;
    }
}
