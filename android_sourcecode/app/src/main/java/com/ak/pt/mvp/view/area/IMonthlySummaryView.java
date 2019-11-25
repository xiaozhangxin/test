package com.ak.pt.mvp.view.area;

import com.ak.pt.bean.MonthTotalBean;
import com.ak.pt.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2019/5/28.
 */

public interface IMonthlySummaryView extends BaseView{
    void oninsertMonthTotal(String data);

    void ondeleteMonthTotal(String data);

    void ondeleteMonthTotalFile(String data);

    void onupdateMonthTotal(String data);

    void onauditMonthTotal(String data);

    void OngetMonthTotalList(List<MonthTotalBean> data);

    void ongetMonthTotalDetail(MonthTotalBean data);

    void onUploadFiles(String[] data);
}
