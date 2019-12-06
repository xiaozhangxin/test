package com.akan.qf.mvp.view.sale;

import com.akan.qf.bean.ClassList;
import com.akan.qf.bean.SaleForecastBean;
import com.akan.qf.mvp.base.BaseView;

import java.util.List;

public interface ISaleForecastView extends BaseView{
    void OnGetSaleTaskClassList(List<ClassList> data);

    void OnAddOrUpdateSaleForecast(String data);

    void OnDeleteSaleForecast(String data);

    void OnQuerySaleForecastPage(List<SaleForecastBean> data);
}
