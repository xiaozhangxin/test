package com.ak.pt.mvp.view;

import com.ak.pt.bean.OrderBean;
import com.ak.pt.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2019/5/7.
 */

public interface IOrderMallView extends BaseView{
    void OnQueryMyOrderList(List<OrderBean> data);

    void onGetOrderDetail(OrderBean data);

    void onConfirmOrder(String data);
}
