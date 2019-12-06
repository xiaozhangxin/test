package com.akan.qf.mvp.view.home;

import com.akan.qf.bean.MeParentBean;
import com.akan.qf.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2018/10/29.
 */

public interface IAddReimburseView extends BaseView{
    void onInsertExpenseReimbursement(String data);

    void onUploadFiles(String[] data);

    void onDeleteExpenseReimbursementFile(String data);

    void onUpdateExpenseReimbursement(String data);

    void onGetCheckPerson(List<MeParentBean> data);
}
