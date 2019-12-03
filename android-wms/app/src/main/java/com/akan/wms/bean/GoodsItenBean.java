package com.akan.wms.bean;

import java.io.Serializable;
import java.util.List;

public class GoodsItenBean  implements Serializable{
    public GoodsItenBean(List<ItemWhQohBean> itemWhQohBeanList) {
        this.itemWhQohBeanList = itemWhQohBeanList;
    }

    private List<ItemWhQohBean> itemWhQohBeanList;

    public List<ItemWhQohBean> getItemWhQohBeanList() {
        return itemWhQohBeanList;
    }

    public void setItemWhQohBeanList(List<ItemWhQohBean> itemWhQohBeanList) {
        this.itemWhQohBeanList = itemWhQohBeanList;
    }
}
