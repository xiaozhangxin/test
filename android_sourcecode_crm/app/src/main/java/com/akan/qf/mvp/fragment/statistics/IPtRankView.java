package com.akan.qf.mvp.fragment.statistics;

import com.akan.qf.mvp.base.BaseView;
import com.akan.qf.mvp.fragment.qifei.AreaPressureBean;
import com.akan.qf.mvp.fragment.qifei.BigAreaBean;

import java.util.List;

/**
 * Created by admin on 2019/3/20.
 */

public interface IPtRankView extends BaseView {
    void OnGetAreaTestPressure(List<BigAreaBean> data);
}
