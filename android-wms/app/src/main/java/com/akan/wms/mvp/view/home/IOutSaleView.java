package com.akan.wms.mvp.view.home;

import com.akan.wms.bean.SaleShipBean;
import com.akan.wms.mvp.base.BaseView;

import java.util.List;

public interface IOutSaleView extends BaseView{
    void onQuerySaleShipPage(List<SaleShipBean> data);

    void OnQuerySaleShipByCode(SaleShipBean data);

    void OnAddSaleShip(String data);

    void OninvalidSaleShip(String data);

    void OnoutOfShip(String data);

    void OnsendSaleShip(String data);

    void OnDeleteSaleShip(String data);
}
