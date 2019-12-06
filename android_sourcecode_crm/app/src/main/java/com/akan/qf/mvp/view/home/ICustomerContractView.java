package com.akan.qf.mvp.view.home;

import com.akan.qf.bean.ContractApplyBean;
import com.akan.qf.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2018/11/27.
 */

public interface ICustomerContractView extends BaseView{
    void OnInsertContractApply(String data);

    void OnGetContractApplyList(List<ContractApplyBean> data, String total);

    void OndeleteContractApply(String data);

    void OnupdateContractApply(String data);

    void getContractApply(ContractApplyBean data);

    void OnDeleteContractFileApply(String data);

    void onUploadFiles(String[] data);
}
