package com.akan.qf.mvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * @since 2017/3/10
 */

public abstract class CommonFragment extends BaseFragment<BaseView,BasePresenter<BaseView>> {

    @Override
    public BasePresenter<BaseView> createPresenter() {
        return new BasePresenter<>(getApp());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return super.onCreateView(inflater, container, savedInstanceState);
    }






}
