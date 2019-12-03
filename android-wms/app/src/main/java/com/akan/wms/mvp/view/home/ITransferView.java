package com.akan.wms.mvp.view.home;

import com.akan.wms.bean.TransferBean;
import com.akan.wms.mvp.base.BaseView;

import java.util.List;

public interface ITransferView extends BaseView{
    void onQueryTransferOutInPage(List<TransferBean> data);

    void onQueryTransferOutInById(TransferBean data);

    void OnAuditOutAndIn(String data);

    void OnAddOutAndIn(String data);
}
