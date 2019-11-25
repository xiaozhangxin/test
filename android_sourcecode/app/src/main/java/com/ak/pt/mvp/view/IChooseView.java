package com.ak.pt.mvp.view;

import com.ak.pt.bean.WorkerBean;
import com.ak.pt.bean.staffGroupBeans;
import com.ak.pt.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2019/1/16.
 */

public interface IChooseView extends BaseView{
    void OnGetWaterList(List<WorkerBean> data);

    void onGetStaffGroupTreeByStaff(List<staffGroupBeans> data);
}
