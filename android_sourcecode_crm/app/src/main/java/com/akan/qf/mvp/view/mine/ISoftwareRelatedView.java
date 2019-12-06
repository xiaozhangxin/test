package com.akan.qf.mvp.view.mine;

import com.akan.qf.bean.AppVersionBean;
import com.akan.qf.mvp.base.BaseView;

/**
 * Created by admin on 2018/12/21.
 */

public interface ISoftwareRelatedView extends BaseView{
    void OnGetAppVersionDetail(AppVersionBean data);
}
