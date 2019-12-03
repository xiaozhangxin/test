package com.akan.wms.mvp.view.home;

import com.akan.wms.bean.OutSaleRtuBean;
import com.akan.wms.mvp.base.BaseView;

import java.util.List;

public interface IChooseBuyReturnView extends BaseView{
    void OnQueryApplyedRtnList(List<OutSaleRtuBean> data);

    void onQueryApplyedRtnById(OutSaleRtuBean data);

    void importRtnData(String data);
}
