package com.akan.wms.mvp.view.home;

import com.akan.wms.bean.InventoryBean;
import com.akan.wms.bean.ItemWhQohBean;
import com.akan.wms.mvp.base.BaseView;

import java.util.List;

public interface ICheckView extends BaseView{
    void onGetInventoryList(List<InventoryBean> data);

    void OnGetInventoryDetail(InventoryBean data);

    void OnUpdateInventoryStatus(String data);

    void OnInsertInventory(String data);

    void onGetItemWhQohByRandomList(List<ItemWhQohBean> data);

    void OnDeleteInventory(String data);
}
