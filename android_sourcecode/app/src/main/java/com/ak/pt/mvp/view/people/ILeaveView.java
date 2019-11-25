package com.ak.pt.mvp.view.people;

import com.ak.pt.bean.MeParentBean;
import com.ak.pt.bean.QuitJobBean;
import com.ak.pt.bean.staffGroupBeans;
import com.ak.pt.mvp.base.BaseView;

import java.util.List;

public interface ILeaveView extends BaseView{
    void insertQuitJob(String data);

    void deleteQuitJob(String data);

    void updateQuitJob(String data);

    void auditQuitJob(String data);

    void getQuitJobList(List<QuitJobBean> data);

    void getQuitJobDetail(QuitJobBean data);

    void getAuditStaffList(List<MeParentBean> data);

}
