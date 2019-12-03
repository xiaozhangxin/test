package com.akan.wms.mvp.view;


import com.akan.wms.bean.AppVersionBean;
import com.akan.wms.bean.StaffOrgsBean;
import com.akan.wms.bean.UserBean;
import com.akan.wms.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2018/6/28.
 */

public interface ILoginView extends BaseView {
    void onGetlogin(UserBean data);

    void queryStaffOrgs(List<StaffOrgsBean> data);

    void OnGetAppVersionDetail(AppVersionBean data);
}
