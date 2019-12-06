package com.akan.qf.mvp.presenter.mine;


import com.akan.qf.App;
import com.akan.qf.http.HttpResult;
import com.akan.qf.mvp.base.BasePresenter;
import com.akan.qf.mvp.view.mine.IChangePwdView;

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
    public void updateStaffPassword(String  s,Map<String, String> parmer) {
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
    public void insertCode(Map<String, String> parmer) {
        if (isViewAttached())//为了避免内存泄露，它只持有 View 的弱引用，因此，使用之前需要先判断isViewAttached()并调用getView()来获取引用。
            getView().showProgress();
        getAppComponent().getAPIService()
                .sendCodeByAccount( parmer)
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
                                getView().OnTnsertCode(userBeanHttpResult.getData());
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
