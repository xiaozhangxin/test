package com.akan.wms.mvp.view.home;

import com.akan.wms.bean.ProductionOrderBean;
import com.akan.wms.mvp.base.BaseView;

import java.util.List;

public interface ICompleteChooseView extends BaseView{
    void onQueryProductionOrderList(List<ProductionOrderBean> data);

    void onQueryProductionOrderTwoList(List<ProductionOrderBean> data);

    void syncProduction(String data);
}
