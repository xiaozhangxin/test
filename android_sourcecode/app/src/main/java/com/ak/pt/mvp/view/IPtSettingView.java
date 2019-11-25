package com.ak.pt.mvp.view;

import com.ak.pt.bean.PressureDropBean;
import com.ak.pt.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2019/3/28.
 */

public interface IPtSettingView extends BaseView{
    void OnVerificationPass(String data);

    void OnGetPressureDropList(List<PressureDropBean> data);
}
