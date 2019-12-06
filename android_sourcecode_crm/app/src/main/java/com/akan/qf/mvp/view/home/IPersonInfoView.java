package com.akan.qf.mvp.view.home;

import com.akan.qf.mvp.fragment.qifei.StaffBean;
import com.akan.qf.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2018/11/22.
 */

public interface IPersonInfoView extends BaseView{
    void OnGetStaffList(List<StaffBean> data, String total);

    void OnGetStaffDetail(StaffBean data);

    void OnUpdateStaff(String data);
}
