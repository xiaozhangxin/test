package com.akan.qf.mvp.view.sale;

import com.akan.qf.bean.CostTypeBean;
import com.akan.qf.bean.ProfitBean;
import com.akan.qf.mvp.base.BaseView;

import java.util.List;

public interface IProfitReportView extends BaseView{
    void OnQueryCostTypeList(List<CostTypeBean> data);

    void OnQueryCostTypeProfitStatisticList(List<ProfitBean> data);
}
