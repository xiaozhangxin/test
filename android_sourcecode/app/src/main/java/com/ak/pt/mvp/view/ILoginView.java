package com.ak.pt.mvp.view;


import com.ak.pt.bean.AppVersionBean;
import com.ak.pt.bean.UserBean;
import com.ak.pt.mvp.base.BaseView;

/**
 * Created by admin on 2018/6/28.
 */

public interface ILoginView extends BaseView {
    void onGetlogin(UserBean data);

    void OnGetAppVersionDetail(AppVersionBean data);
}
