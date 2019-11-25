package com.ak.pt.mvp.view.people;

import com.ak.pt.bean.MeParentBean;
import com.ak.pt.bean.ShopCloseBean;
import com.ak.pt.mvp.base.BaseView;

import java.util.List;

public interface ICloseView extends BaseView{
    void insertShopClose(String data);

    void deleteShopClose(String data);

    void updateShopClose(String data);

    void auditShopClose(String data);

    void getShopCloseList(List<ShopCloseBean> data);

    void getShopCloseDetail(ShopCloseBean data);

    void onGgetParentDepartmentStaffList(List<MeParentBean> data);
}
