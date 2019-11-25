package com.ak.pt.mvp.fragment.statistics;

import com.ak.pt.bean.AreaPressureBean;
import com.ak.pt.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2019/3/20.
 */

public interface IPtRankView extends BaseView{
    void OnGetAreaTestPressure(List<AreaPressureBean> data);
}
