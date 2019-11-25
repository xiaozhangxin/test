package com.ak.pt.mvp.view;

import com.ak.pt.mvp.base.BaseView;

import java.util.List;

/**
 * Created by admin on 2019/4/16.
 */

public interface IFilterView extends BaseView {
    void OnQueryJobNames(List<String> data);
}
