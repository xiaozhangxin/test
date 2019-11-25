package com.ak.pt.mvp.view;


import com.ak.pt.bean.AppVersionBean;
import com.ak.pt.mvp.base.BaseView;

/**
 * Created by admin on 2018/12/21.
 */

public interface ISoftwareRelatedView extends BaseView {
    void OnGetAppVersionDetail(AppVersionBean data);
}
