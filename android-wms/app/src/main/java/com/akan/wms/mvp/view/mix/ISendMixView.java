package com.akan.wms.mvp.view.mix;

import com.akan.wms.bean.ShipBean;
import com.akan.wms.mvp.base.BaseView;

import java.util.List;

public interface ISendMixView extends BaseView{
    void onQueryShipPage(List<ShipBean> data);

    void onQueryShipById(ShipBean data);

    void onAuditShip(String data);

    void onSyncMix(String data);
}
