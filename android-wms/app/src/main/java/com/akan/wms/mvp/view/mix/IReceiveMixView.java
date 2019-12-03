package com.akan.wms.mvp.view.mix;

import com.akan.wms.bean.RcvBean;
import com.akan.wms.mvp.base.BaseView;

import java.util.List;

public interface IReceiveMixView extends BaseView{
    void onQueryRcvPage(List<RcvBean> data);

    void onQueryRcvById(RcvBean data);

    void onAuditRcv(String data);

    void onSyncMix(String data);
}
