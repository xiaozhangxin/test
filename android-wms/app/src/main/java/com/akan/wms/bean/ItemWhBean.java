package com.akan.wms.bean;

import java.io.Serializable;
import java.util.List;

public class ItemWhBean implements Serializable{


    /**
     * id : 1001812180044700
     * org_id : 1001512200010027
     * item_id : 1001812180044580
     * wh_id : {}
     * whList : [{"warehouse_id":1001512260168343,"warehouse_name":"材料仓","warehouse_code":"11","org_id":{},"is_effective":"0"}]
     */

    private String id;
    private String org_id;
    private String item_id;
    private List<WhListBean> whList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrg_id() {
        return org_id;
    }

    public void setOrg_id(String org_id) {
        this.org_id = org_id;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }




    public List<WhListBean> getWhList() {
        return whList;
    }

    public void setWhList(List<WhListBean> whList) {
        this.whList = whList;
    }


}
