package com.akan.wms.mvp.view.home;

import com.akan.wms.bean.ItemInfoBean;
import com.akan.wms.bean.ItemWhQohBean;
import com.akan.wms.mvp.base.BaseView;

import java.util.List;

public interface IChooseItemView extends BaseView{
    void onGetItemWhQohByWhList(List<ItemWhQohBean> data);

    void onGetItemInfoList(List<ItemInfoBean> data);
}
