package com.akan.qf.mvp.view.home;

import com.akan.qf.bean.DocumentBean;
import com.akan.qf.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2019/2/27.
 */

public interface IOfficeView extends BaseView{
    void OnGetDocumentList(List<DocumentBean> data,String total);
}
