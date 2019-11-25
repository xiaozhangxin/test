package com.ak.pt.mvp.presenter.mall;

import com.ak.pt.App;
import com.ak.pt.bean.AddressBean;
import com.ak.pt.http.HttpResult;
import com.ak.pt.mvp.base.BasePresenter;
import com.ak.pt.mvp.view.mall.IMallOrderView;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by admin on 2019/5/10.
 */

public class MallOrderPresenter extends BasePresenter<IMallOrderView>{
    public MallOrderPresenter(App app) {
        super(app);
    }

    public void getDefaultAddress(String token, Map<String, String> map) {
        getAppComponent().getAPIService()
                .getDefaultAddress(token, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<AddressBean>>() {

                    @Override
                    public void onError(Throwable e) {
                        if (isViewAttached())
                            getView().onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HttpResult<AddressBean> userBeanHttpResult) {
                        if (userBeanHttpResult != null) {
                            if (isViewAttached()) {
                                getView().onGetDefaultAddress(userBeanHttpResult.getData());
                            }
                        }
                    }
                });
    }

    public void insertOrder(String token, Map<String, String> map) {
        getAppComponent().getAPIService()
                .insertOrder(token, map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<String>>() {

                    @Override
                    public void onError(Throwable e) {
                        if (isViewAttached())
                            getView().onError(e);
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HttpResult<String> userBeanHttpResult) {
                        if (userBeanHttpResult != null) {
                            if (isViewAttached()) {
                                getView().onInsertOrder(userBeanHttpResult.getData());
                            }
                        }
                    }
                });
    }
}
