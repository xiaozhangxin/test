package com.ak.pt.mvp.view;

import com.ak.pt.bean.PressureDropBean;
import com.ak.pt.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2019/4/29.
 */

public interface IPressureDropView extends BaseView{
    void OnGetPressureDropList(List<PressureDropBean> data);
}
