package com.akan.wms.mvp.view.home;

import com.akan.wms.bean.RtnedGoodsBean;
import com.akan.wms.mvp.base.BaseView;

import java.util.List;

public interface IOutBuyReturnView extends BaseView{

    void OnAddRtnedGoods(String data);

    void onInvalidRtnedGoods(String data);

    void onPastRtnedGoods(String data);

    void OnQueryRtnedGoodsList(List<RtnedGoodsBean> data);

    void onQueryRtnedGoodsById(RtnedGoodsBean data);

    void onDelRtnedGoods(String data);
}
