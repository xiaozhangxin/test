package com.akan.wms.mvp.view.home;

import com.akan.wms.bean.PurchaseBean;
import com.akan.wms.mvp.base.BaseView;

import java.util.List;

public interface IStockView extends BaseView{
    void onQueryPurchaseLists(List<PurchaseBean> data);

    void importPurchaseData(String data);
}
