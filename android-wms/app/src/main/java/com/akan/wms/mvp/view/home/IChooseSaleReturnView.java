package com.akan.wms.mvp.view.home;

import com.akan.wms.bean.SaleReturnBean;
import com.akan.wms.mvp.base.BaseView;

import java.util.List;

public interface IChooseSaleReturnView extends BaseView{
    void onQuerySaleReturnPage(List<SaleReturnBean> data);

    void OnQuerySaleReturnDetail(SaleReturnBean data);

    void OnWhSaleRcv(String data);
}
