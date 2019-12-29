package com.akan.wms.mvp.view;

import com.akan.wms.bean.StaffOrgsBean;
import com.akan.wms.bean.WareHouseBean;
import com.akan.wms.mvp.base.BaseView;

import java.util.List;

public interface IChooseDeportView extends BaseView{

    void getWareHouseList(List<WareHouseBean> data);

    void getWareHouseById(List<WareHouseBean> data);
}
