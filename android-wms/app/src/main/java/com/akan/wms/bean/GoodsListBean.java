package com.akan.wms.bean;

import java.io.Serializable;
import java.util.List;

public class GoodsListBean implements Serializable{
    private List<ItemInfoBean> list;

    public GoodsListBean(List<ItemInfoBean> list) {
        this.list = list;
    }

    public List<ItemInfoBean> getList() {
        return list;
    }

    public void setList(List<ItemInfoBean> list) {
        this.list = list;
    }
}
