package com.ak.pt.mvp.view;

import com.ak.pt.mvp.base.BaseView;
import com.ak.pt.mvp.fragment.statistics.MemoryBean;
import com.ak.pt.mvp.fragment.statistics.PressurePageBean;

import java.util.List;

/**
 * Created by admin on 2019/1/16.
 */

public interface IOrderView extends BaseView{
    void OnGetTestPressureList(List<PressurePageBean> data);

    void onInsertOrUpdateTestPressure(String data);

    void OnGetAppTestPressureList(List<PressurePageBean> data);

    void onInsertOrUpdateAppTestPressure(String data);

    void onGetMemoryList(String type, List<MemoryBean> data);
}
