package com.ak.pt.mvp.view;

import com.ak.pt.bean.BookBean;
import com.ak.pt.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2019/1/17.
 */

public interface IBookView extends BaseView{
    void onGetAddressBookList(List<BookBean> data);
}
