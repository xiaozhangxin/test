package com.akan.wms.mvp.view.home;

import com.akan.wms.bean.AppVersionBean;
import com.akan.wms.bean.BarMsgBean;
import com.akan.wms.bean.WarnTwoBean;
import com.akan.wms.mvp.base.BaseView;

import java.util.List;

public interface IHomeView extends BaseView{
    void onQueryBoardWarnings(List<WarnTwoBean> data);

    void onSelectItemBarMsgList(BarMsgBean data);

    void OnGetAppVersionDetail(AppVersionBean data);
}
