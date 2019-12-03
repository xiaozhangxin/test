package com.akan.wms.mvp.view;

import com.akan.wms.bean.MfcBean;
import com.akan.wms.mvp.base.BaseView;

import java.util.List;

public interface IChooseMfcView extends BaseView{
    void OnQueryMfc(List<MfcBean> data);
}
