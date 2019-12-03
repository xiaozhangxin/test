package com.akan.wms.mvp.view.home;

import com.akan.wms.bean.MiscRcvBean;
import com.akan.wms.mvp.base.BaseView;

import java.util.List;

public interface IProduceReturnView extends BaseView{
    void onGetMiscRcvList(List<MiscRcvBean> data);

    void OnGetMiscRcvDetail(MiscRcvBean data);

    void OnInsertMiscRcv(String data);

    void OnUpdateMiscRcvCode(String data);

    void OnDeleteMiscRcv(String data);
}
