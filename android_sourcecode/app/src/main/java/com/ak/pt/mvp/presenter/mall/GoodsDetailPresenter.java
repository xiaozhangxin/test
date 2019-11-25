package com.ak.pt.mvp.presenter.mall;

import com.ak.pt.App;
import com.ak.pt.bean.GoodsBean;
import com.ak.pt.http.HttpResult;
import com.ak.pt.mvp.base.BasePresenter;
import com.ak.pt.mvp.view.mall.IGoodsDetailView;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by admin on 2019/5/10.
 */

public class GoodsDetailPresenter extends BasePresenter<IGoodsDetailView>{

    public GoodsDetailPresenter(App app) {
        super(app);
    }
    public void getGoodsDetail(String token, Map<String, String> map) {
        getAppComponent().getAPIService()
                .getGoodsDetail(token,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<GoodsBean>>() {

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
                    public void onNext(HttpResult<GoodsBean> userBeanHttpResult) {
                        if (userBeanHttpResult != null) {
                            if (isViewAttached()) {
                                getView().onGetGoodsDetail(userBeanHttpResult.getData());
                            }
                        }
                    }
                });
    }
    public void getWaterIntegral(String token, Map<String, String> map) {
        getAppComponent().getAPIService()
                .getWaterIntegral(token, map)
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
                                getView().onGetWaterIntegral(userBeanHttpResult.getData());
                            }
                        }
                    }
                });
    }
}
