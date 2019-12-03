package com.akan.wms.mvp.view.home;

import com.akan.wms.bean.SaleShipTypeBean;
import com.akan.wms.mvp.base.BaseView;

import java.util.List;

public interface ISaleOutTypeView extends BaseView{
    void OnQuerySaleShipTypeList(List<SaleShipTypeBean> data);
}
