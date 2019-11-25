package com.ak.pt.mvp.view.area;

import com.ak.pt.bean.InstallFormBean;
import com.ak.pt.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2019/5/31.
 */

public interface IAllTableView extends BaseView{
    void ongetInstallForm(List<InstallFormBean> data);

    void ongetDoorForm(List<InstallFormBean> data);

    void ongetTimelyForm(List<InstallFormBean> data);

    void ongetDistributorForm(List<InstallFormBean> data);

    void ongetPlumberForm(List<InstallFormBean> data);

    void ongetHydraulicNameForm(List<InstallFormBean> data);

    void ongetSpoolTypeForm(List<InstallFormBean> data);

    void ongetProjectManagerForm(List<InstallFormBean> data);

    void ongetHouseNameForm(List<InstallFormBean> data);

    void ongetOwnerForm(List<InstallFormBean> data);

    void ongetAuthenticityForm(List<InstallFormBean> data);

    void ongetStaffForm(List<InstallFormBean> data);

    void ongetDecorateCompanyForm(List<InstallFormBean> data);

    void ongetPipeTrendForm(List<InstallFormBean> data);

    void ongetEventForm(List<InstallFormBean> data);
}
