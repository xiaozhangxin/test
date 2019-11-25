package com.ak.pt.mvp.view.people;

import com.ak.pt.bean.StaffInfoLeaveBean;
import com.ak.pt.mvp.base.BaseView;

import java.util.List;

public interface IChoosePeopleLeaveView extends BaseView{
    void getStaffInfoList(List<StaffInfoLeaveBean> data);
}
