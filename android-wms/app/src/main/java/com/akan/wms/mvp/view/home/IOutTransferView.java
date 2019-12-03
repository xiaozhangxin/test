package com.akan.wms.mvp.view.home;

import com.akan.wms.bean.TransferOutBean;
import com.akan.wms.mvp.base.BaseView;

import java.util.List;

public interface IOutTransferView extends BaseView{
    void onQueryTransferOutPage(List<TransferOutBean> data);

    void onQueryTransferById(TransferOutBean data);

    void onAddTransferOut(String data);

    void onTransferOutWh(String data);

    void onQueryTransferOutPageTwo(List<TransferOutBean> data);

    void onSync(String data);
}
