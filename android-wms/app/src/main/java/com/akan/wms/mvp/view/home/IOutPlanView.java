package com.akan.wms.mvp.view.home;

import com.akan.wms.bean.ShipPlanBean;
import com.akan.wms.mvp.base.BaseView;

import java.util.List;

public interface IOutPlanView extends BaseView{
    void onQueryShipPlanPage(List<ShipPlanBean> data);

    void OnQueryPlanById(ShipPlanBean data);

    void onSyncPlan(String data);
}
