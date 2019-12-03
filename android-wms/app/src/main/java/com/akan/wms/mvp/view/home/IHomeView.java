package com.akan.wms.mvp.view.home;

import com.akan.wms.bean.WarnTwoBean;
import com.akan.wms.mvp.base.BaseView;

import java.util.List;

public interface IHomeView extends BaseView{
    void onQueryBoardWarnings(List<WarnTwoBean> data);
}
