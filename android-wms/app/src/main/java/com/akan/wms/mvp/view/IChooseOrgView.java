package com.akan.wms.mvp.view;

import com.akan.wms.bean.StaffOrgsBean;
import com.akan.wms.mvp.base.BaseView;

import java.util.List;

public interface IChooseOrgView extends BaseView{
    void queryStaffOrgs(List<StaffOrgsBean> data);
}
