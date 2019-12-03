package com.akan.wms.mvp.view.home;

import com.akan.wms.bean.WarnTwoBean;
import com.akan.wms.bean.WarnTypeBean;
import com.akan.wms.mvp.base.BaseView;

import java.util.List;

public interface IStockFindView extends BaseView{
    void onQueryWarningTwoList(List<WarnTwoBean> data);

    void onQueryInventoryStatementTypeList(List<WarnTypeBean> data);
}
