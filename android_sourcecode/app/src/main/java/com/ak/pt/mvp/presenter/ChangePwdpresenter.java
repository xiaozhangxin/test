package com.ak.pt.mvp.presenter;


import com.ak.pt.App;
import com.ak.pt.http.HttpResult;
import com.ak.pt.mvp.base.BasePresenter;
import com.ak.pt.mvp.view.IChangePwdView;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by liuxiaoxiang on 2018/3/9.
 */

public class ChangePwdpresenter extends BasePresenter<IChangePwdView> {
    public ChangePwdpresenter(App app) {
        super(app);
    }
    public void updateStaffPassword(String s, Map<String, String> parmer) {
        if (isViewAttached())//为了避免内存泄露，它只持有 View 的弱引用，因此，使用之前需要先判断isViewAttached()并调用getView()来获取引用。
            getView().showProgress();
        getAppComponent().getAPIService()
                .updateStaffPassword(s ,parmer)
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
                                getView().OnUpdateStaffPassword(userBeanHttpResult.getData());
                            }
                        }

                    }
                });

    }
    public void getYanZhengMa(Map<String, String> parmer) {
        getAppComponent().getAPIService()
                .insertCode(parmer)
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
                                getView().ongetYanZhengMa(userBeanHttpResult.getData());
                            }
                        }

                    }
                });
    }
    public void getwangjiminma(Map<String, String> parmer) {
        getAppComponent().getAPIService()
                .getwangjiminma(parmer)
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
                                getView().ongetwangjiminma(userBeanHttpResult.getData());
                            }
                        }
                    }
                });
    }


}
