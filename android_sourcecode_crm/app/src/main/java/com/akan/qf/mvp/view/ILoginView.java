package com.akan.qf.mvp.view;

import com.akan.qf.bean.AppVersionBean;
import com.akan.qf.bean.UserBean;
import com.akan.qf.mvp.base.BaseView;

/**
 * Created by admin on 2018/6/28.
 */

public interface ILoginView extends BaseView {
    void onGetlogin(UserBean data);

    void OnTnsertCode(String data);

    void OnGetAppVersionDetail(AppVersionBean data);
}
