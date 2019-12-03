package com.akan.wms.mvp.view.home;

import com.akan.wms.bean.MiscShipBean;
import com.akan.wms.mvp.base.BaseView;

import java.util.List;

public interface IProduceReceiveView extends BaseView{
    void onGetMiscShipList(List<MiscShipBean> data);

    void OnInsertMiscShip(String data);

    void OnGetMiscShipDetail(MiscShipBean data);

    void OnUpdateMiscShipCode(String data);

    void OnDeleteMiscShip(String data);
}
