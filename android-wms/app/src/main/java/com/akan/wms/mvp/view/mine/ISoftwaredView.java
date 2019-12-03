package com.akan.wms.mvp.view.mine;

import com.akan.wms.bean.AppVersionBean;
import com.akan.wms.mvp.base.BaseView;

public interface ISoftwaredView extends BaseView{
    void OnGetAppVersionDetail(AppVersionBean data);
}
