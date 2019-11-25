package com.ak.pt.mvp.view;

import com.ak.pt.bean.AddressBean;
import com.ak.pt.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2019/5/7.
 */

public interface IAddressView extends BaseView{
    void OnGetMemberAddressList(List<AddressBean> data);

    void onInsertUpdateAddress(String data);

    void onDeleteAddress(String data);

    void onSetDefaultAddress(String data);
}
