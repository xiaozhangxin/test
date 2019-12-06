package com.akan.qf.mvp.view.home;

import com.akan.qf.bean.FinancialBean;
import com.akan.qf.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2019/2/27.
 */

public interface ICostStatisticsView extends BaseView{
    void OnInsertOrUpdateStatistics(String data);

    void OnGetFinancialStatisticsList(List<FinancialBean> data, String total);

    void OnGetFinancialStatistics(FinancialBean data);

    void OnDeleteFinancialStatistics(String data);
}
