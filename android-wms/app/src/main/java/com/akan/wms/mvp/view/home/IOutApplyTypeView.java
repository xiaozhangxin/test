package com.akan.wms.mvp.view.home;

import com.akan.wms.bean.OutTypeLBean;
import com.akan.wms.mvp.base.BaseView;

import java.util.List;

public interface IOutApplyTypeView extends BaseView{
    void queryOutTypeList(List<OutTypeLBean> data);

    void queryInTypeList(List<OutTypeLBean> data);
}
