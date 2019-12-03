package com.akan.wms.mvp.view.home;

import com.akan.wms.bean.ItemInfoBean;
import com.akan.wms.mvp.base.BaseView;

import java.util.List;

public interface IChooseView extends BaseView{
    void onGetItemInfoList(List<ItemInfoBean> data);

    void syncItemInfoList(String data);
}
