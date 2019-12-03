package com.akan.wms.mvp.view.home;

import com.akan.wms.bean.SupplierBean;
import com.akan.wms.mvp.base.BaseView;

import java.util.List;

public interface IChooseSupplierView extends BaseView{
    void OnQuerySupplier(List<SupplierBean> data);
}
