package com.akan.qf.mvp.view;

import com.akan.qf.bean.SignBean;
import com.akan.qf.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2018/7/2.
 */

public interface ISignRecordView extends BaseView{
    void onGetSignList(List<SignBean> data, String total);

    void onGetDaySignList(List<SignBean> data);
}
