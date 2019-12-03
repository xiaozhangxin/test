package com.akan.wms.mvp.view.home;

import com.akan.wms.bean.WarnBean;
import com.akan.wms.bean.WarnTypeBean;
import com.akan.wms.mvp.base.BaseView;

import java.util.List;

public interface IFlowView extends BaseView{
    void onQueryWarningList(List<WarnBean> data);

    void onQueryInventoryStatementTypeList(List<WarnTypeBean> data);
}
