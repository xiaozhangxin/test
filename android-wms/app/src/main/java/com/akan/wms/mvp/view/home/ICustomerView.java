package com.akan.wms.mvp.view.home;

import com.akan.wms.bean.CustomBean;
import com.akan.wms.mvp.base.BaseView;

import java.util.List;

public interface ICustomerView extends BaseView{
    void OnQueryCustomLists(List<CustomBean> data);
}
