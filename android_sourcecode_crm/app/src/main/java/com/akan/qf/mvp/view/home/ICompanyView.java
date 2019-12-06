package com.akan.qf.mvp.view.home;

import com.akan.qf.bean.CompanyBean;
import com.akan.qf.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2018/11/7.
 */

public interface ICompanyView extends BaseView {

    void OnInsertOrUpdateCompanyApply(String data);

    void OnGetCompanyApplyList(List<CompanyBean> data, String total);

    void OnGetCompanyApply(CompanyBean data);

    void OnAuditCompanyApply(String data);

    void OnDeleteCompanyApply(String data);
}
