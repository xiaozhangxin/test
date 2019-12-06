package com.akan.qf.mvp.view;

import com.akan.qf.bean.LableBean;
import com.akan.qf.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2019/4/2.
 */

public interface IFilterView extends BaseView{
    void OnQueryJobNames(List<String> data);

    void OnGetStaffSignList(List<LableBean> data);
}
