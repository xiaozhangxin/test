package com.ak.pt.mvp.view;

import com.ak.pt.mvp.base.BaseView;
import com.ak.pt.mvp.fragment.statistics.PressurePageBean;

import java.util.List;

public interface IOrderSearchView extends BaseView {
    void OnGetAppTestPressureList(List<PressurePageBean> data, String total);

    void OnGetTestPressureList(List<PressurePageBean> data, String total);
}
