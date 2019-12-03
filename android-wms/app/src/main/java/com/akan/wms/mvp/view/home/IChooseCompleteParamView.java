package com.akan.wms.mvp.view.home;

import com.akan.wms.bean.ClassTeamBean;
import com.akan.wms.bean.ModNoBean;
import com.akan.wms.bean.OperatorStaffBean;
import com.akan.wms.mvp.base.BaseView;

import java.util.List;

public interface IChooseCompleteParamView extends BaseView{
    void onQueryOperatorStaffList(List<OperatorStaffBean> data);

    void onQueryClassTeam(List<ClassTeamBean> data);

    void onQueryModNo(List<ModNoBean> data);
}
