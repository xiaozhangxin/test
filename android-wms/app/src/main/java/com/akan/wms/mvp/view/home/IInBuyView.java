package com.akan.wms.mvp.view.home;

import com.akan.wms.bean.DeliverGoodsBean;
import com.akan.wms.mvp.base.BaseView;

import java.util.List;

public interface IInBuyView extends BaseView{

    void OnAddDeliverGoods(String data);

    void OnQueryDeliverGoodsLists(List<DeliverGoodsBean> data);

    void OnQuerySupplierDeliverById(DeliverGoodsBean data);



    void qualityReceiveGoods(String data);

    void pastWh(String data);

    void rejectWh(String data);

    void onAddReceiveGoods(String data);
}
