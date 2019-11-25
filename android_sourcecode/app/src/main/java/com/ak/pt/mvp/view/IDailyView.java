package com.ak.pt.mvp.view;

import com.ak.pt.bean.DailyBean;
import com.ak.pt.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2019/3/20.
 */

public interface IDailyView extends BaseView {
    void onInsertDaily(String data);

    void onUploadFiles(String[] data);

    void onGetDailyList(List<DailyBean> data,String total);

    void onGetDailyDetail(DailyBean data);

    void auditDaily(String data);

    void onUpdateDaily(String data);

    void onDeleteDaily(String data);
}
