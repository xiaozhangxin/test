package com.akan.qf.mvp.view.home;

import com.akan.qf.bean.DailyBean;
import com.akan.qf.bean.LableBean;
import com.akan.qf.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2018/6/28.
 */

public interface IDailyView extends BaseView{
    void onInsertDaily(String data);

    void onUploadFiles(String[] data);

    void onGetDailyList(List<DailyBean> data,String total);

    void onGetDailyDetail(DailyBean data);

    void auditDaily(String data);

    void onUpdateDaily(String data);

    void onDeleteDaily(String data);

    void OnGetStaffSignList(List<LableBean> data);
}
