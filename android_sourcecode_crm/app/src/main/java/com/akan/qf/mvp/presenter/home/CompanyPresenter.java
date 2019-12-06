package com.akan.qf.mvp.presenter.home;

import com.akan.qf.App;
import com.akan.qf.bean.CompanyBean;
import com.akan.qf.http.HttpResult;
import com.akan.qf.mvp.base.BasePresenter;
import com.akan.qf.mvp.view.home.ICompanyView;

import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by admin on 2018/11/7.
 */

public class CompanyPresenter extends BasePresenter<ICompanyView> {
    public CompanyPresenter(App app) {
        super(app);
    }

    public void insertOrUpdateCompanyApply(String token, Map<String, String> parmer) {
        if (isViewAttached())
            getView().showProgress();
        getAppComponent().getAPIService()
                .insertOrUpdateCompanyApply(token, parmer)
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
                                getView().OnInsertOrUpdateCompanyApply(userBeanHttpResult.getData());
                            }
                        }

                    }
                });

    }

    public void getCompanyApplyList(String token, Map<String, String> parmer) {
        if (isViewAttached())
            getView().showProgress();
        getAppComponent().getAPIService()
                .getCompanyApplyList(token, parmer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<List<CompanyBean>>>() {

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
                    public void onNext(HttpResult<List<CompanyBean>> userBeanHttpResult) {
                        if (userBeanHttpResult != null) {
                            if (isViewAttached()) {
                                getView().OnGetCompanyApplyList(userBeanHttpResult.getData(), userBeanHttpResult.getTotal());
                            }
                        }

                    }
                });

    }

    public void getCompanyApply(String token, Map<String, String> parmer) {
        if (isViewAttached())
            getView().showProgress();
        getAppComponent().getAPIService()
                .getCompanyApply(token, parmer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<CompanyBean>>() {

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
                    public void onNext(HttpResult<CompanyBean> userBeanHttpResult) {
                        if (userBeanHttpResult != null) {
                            if (isViewAttached()) {
                                getView().OnGetCompanyApply(userBeanHttpResult.getData());
                            }
                        }

                    }
                });

    }

    public void auditCompanyApply(String token, Map<String, String> parmer) {
        if (isViewAttached())
            getView().showProgress();
        getAppComponent().getAPIService()
                .auditCompanyApply(token, parmer)
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
                                getView().OnAuditCompanyApply(userBeanHttpResult.getData());
                            }
                        }

                    }
                });

    }

    public void deleteCompanyApply(String token, Map<String, String> parmer) {
        if (isViewAttached())
            getView().showProgress();
        getAppComponent().getAPIService()
                .deleteCompanyApply(token, parmer)
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
                                getView().OnDeleteCompanyApply(userBeanHttpResult.getData());
                            }
                        }

                    }
                });

    }
}
