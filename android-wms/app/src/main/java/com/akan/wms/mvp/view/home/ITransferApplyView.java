package com.akan.wms.mvp.view.home;

import com.akan.wms.bean.TransferUnCompleteBean;
import com.akan.wms.mvp.base.BaseView;

import java.util.List;

public interface ITransferApplyView extends BaseView{

    void onQueryApplyDocNo(TransferUnCompleteBean data);

    void onQueryUnClosedApplyPage(List<TransferUnCompleteBean> data);

    void onSync(String data);
}
