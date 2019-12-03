package com.akan.wms.mvp.view.home;

import com.akan.wms.bean.DefineValueBean;
import com.akan.wms.bean.MiscShipDocTypeBean;
import com.akan.wms.bean.OperatorBean;
import com.akan.wms.bean.StaffGroupBean;
import com.akan.wms.bean.StaffOrgsBean;
import com.akan.wms.mvp.base.BaseView;

import java.util.List;

public interface IProduceChooseView extends BaseView{
    void onGetMiscShipDocTypeList(List<MiscShipDocTypeBean> data);

    void onGetOperatorList(List<OperatorBean> data);

    void onGetStaffGroupByOrgId(List<StaffGroupBean> data);

    void onGetDefineValueList(List<DefineValueBean> data);

    void queryStaffOrgs(List<StaffOrgsBean> data);

    void onGetMiscRcvDocTypeList(List<MiscShipDocTypeBean> data);
}
