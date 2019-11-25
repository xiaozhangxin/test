package com.ak.pt.mvp.view.mall;

import com.ak.pt.bean.GoodsBean;
import com.ak.pt.mvp.base.BaseView;

/**
 * Created by admin on 2019/5/10.
 */

public interface IGoodsDetailView extends BaseView{
    void onGetGoodsDetail(GoodsBean data);

    void onGetWaterIntegral(String data);
}
