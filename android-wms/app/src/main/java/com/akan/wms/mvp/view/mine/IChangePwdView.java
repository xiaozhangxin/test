package com.akan.wms.mvp.view.mine;


import com.akan.wms.mvp.base.BaseView;

/**
 * Created by liuxiaoxiang on 2018/3/9.
 */

public interface IChangePwdView extends BaseView {


    void OnUpdateStaffPassword(String data);

    void OnTnsertCode(String data);

    void ongetwangjiminma(String data);
}
