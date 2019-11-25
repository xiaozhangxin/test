package com.ak.pt.mvp.view.mall;

import com.ak.pt.bean.GoodsBean;
import com.ak.pt.bean.ShopBannerBean;
import com.ak.pt.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2019/5/10.
 */

public interface IMallView extends BaseView{
    void OnQuerySaleGoodsList(List<GoodsBean> data);
    void onGetWaterIntegral(String data);

    void OnGetShopBannerList(List<ShopBannerBean> data);
}
