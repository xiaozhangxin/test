package com.akan.qf.mvp.base;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.akan.qf.App;
import com.akan.qf.di.component.AppComponent;
import com.king.base.util.LogUtils;

import javax.inject.Inject;

/**
 * @author Jenly <a href="mailto:jenly1314@gmail.com">Jenly</a>
 * @since 2017/2/20
 */

public class BasePresenter<V extends BaseView> extends MvpBasePresenter<V> {

    private App app;


    private AppComponent mAppComponent;

    @Inject
    public BasePresenter(App app){
        super();
        this.app = app;
        mAppComponent = app.getAppCommponent();
    }


    public AppComponent getAppComponent(){
        return mAppComponent;
    }


    public App getApp(){
        return getApp();
    }

    @Override
    public boolean isViewAttached() {
        LogUtils.d("isViewAttached:" + super.isViewAttached());
        return super.isViewAttached();
    }
}
