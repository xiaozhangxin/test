package com.akan.qf.mvp.view.home;

import com.akan.qf.bean.LeaveBean;
import com.akan.qf.bean.MeParentBean;
import com.akan.qf.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2018/7/13.
 */

public interface ILeaveView extends BaseView{
    void onUploadFiles(String[] data);

    void onInsertAskLeave(String data);


    void onGetLeaveDetail(LeaveBean data);

    void auditAskLeave(String data);

    void onGetAskLeaveList(List<LeaveBean> data, String total);


    void onDeleteAskLeaveFile(String data);

    void onDeleteAskLeave(String data);

    void onGetCheckPerson(List<MeParentBean> data);
}
