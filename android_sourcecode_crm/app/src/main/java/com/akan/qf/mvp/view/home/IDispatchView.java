package com.akan.qf.mvp.view.home;

import com.akan.qf.bean.ClassList;
import com.akan.qf.bean.SaleDataBean;
import com.akan.qf.bean.SaleDataContrastBean;
import com.akan.qf.bean.TaskBean;
import com.akan.qf.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2018/11/16.
 */

public interface IDispatchView extends BaseView{
    void OnGetSaleTaskList(List<TaskBean> data);


    void OnGetSaleDataSumList(List<SaleDataBean> data);

    void OnGetSaleDataContrastList(List<SaleDataContrastBean> data);

    void OnGetSaleTaskClassList(List<ClassList> data);
}
