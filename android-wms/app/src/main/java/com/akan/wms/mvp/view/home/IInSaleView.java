package com.akan.wms.mvp.view.home;

import com.akan.wms.bean.SaleRcvBean;
import com.akan.wms.mvp.base.BaseView;

import java.util.List;

public interface IInSaleView extends BaseView{
    void onQuerySaleRcvPage(List<SaleRcvBean> data);

    void OnQuerySaleRcv(SaleRcvBean data);

    void OnWhSaleRcv(String data);

    void onSyncPlan(String data);
}
