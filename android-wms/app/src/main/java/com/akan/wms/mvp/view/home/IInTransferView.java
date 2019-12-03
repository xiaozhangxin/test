package com.akan.wms.mvp.view.home;

import com.akan.wms.bean.TransferInBean;
import com.akan.wms.mvp.base.BaseView;

import java.util.List;

public interface IInTransferView extends BaseView{
    void onQueryTransferInPage(List<TransferInBean> data);

    void onQueryTransferInById(TransferInBean data);

    void onAddTransferIn(String data);

    void onTransferInWh(String data);
}
