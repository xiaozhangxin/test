package com.akan.wms.mvp.view.home;

import com.akan.wms.bean.WhBean;
import com.akan.wms.mvp.base.BaseView;

import java.util.List;

public interface IChooseReceiptReportView extends BaseView{
    void OnQueryWh(List<WhBean> data);
}
