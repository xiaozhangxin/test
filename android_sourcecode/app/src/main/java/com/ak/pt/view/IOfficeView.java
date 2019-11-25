package com.ak.pt.view;

import com.ak.pt.bean.DocumentBean;
import com.ak.pt.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2019/3/26.
 */

public interface IOfficeView extends BaseView {
    void OnGetDocumentList(List<DocumentBean> data,String total);
}