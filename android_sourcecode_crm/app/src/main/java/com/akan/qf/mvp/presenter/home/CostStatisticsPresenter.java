package com.akan.qf.mvp.presenter.home;

import com.akan.qf.App;
import com.akan.qf.bean.FinancialBean;
import com.akan.qf.http.HttpResult;
import com.akan.qf.mvp.base.BasePresenter;
import com.akan.qf.mvp.view.home.ICostStatisticsView;

import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by admin on 2019/2/27.
 */

public class CostStatisticsPresenter extends BasePresenter<ICostStatisticsView>{
    public CostStatisticsPresenter(App app) {
        super(app);
    }
    public void insertOrUpdateStatistics(String token, Map<String, String> parmer) {
        if (isViewAttached())
            getView().showProgress();
        getAppComponent().getAPIService()
                .insertOrUpdateStatistics(token, parmer)
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
                                getView().OnInsertOrUpdateStatistics(userBeanHttpResult.getData());
                            }
                        }

                    }
                });

    }

    public void getFinancialStatisticsList(String token, Map<String, String> parmer) {
        if (isViewAttached())
            getView().showProgress();
        getAppComponent().getAPIService()
                .getFinancialStatisticsList(token, parmer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<List<FinancialBean>>>() {

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
                    public void onNext(HttpResult<List<FinancialBean>> userBeanHttpResult) {
                        if (userBeanHttpResult != null) {
                            if (isViewAttached()) {
                                getView().OnGetFinancialStatisticsList(userBeanHttpResult.getData(), userBeanHttpResult.getTotal());
                            }
                        }

                    }
                });

    }

    public void getFinancialStatistics(String token, Map<String, String> parmer) {
        if (isViewAttached())
            getView().showProgress();
        getAppComponent().getAPIService()
                .getFinancialStatistics(token, parmer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<FinancialBean>>() {

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
                    public void onNext(HttpResult<FinancialBean> userBeanHttpResult) {
                        if (userBeanHttpResult != null) {
                            if (isViewAttached()) {
                                getView().OnGetFinancialStatistics(userBeanHttpResult.getData());
                            }
                        }

                    }
                });

    }

 

    public void deleteFinancialStatistics(String token, Map<String, String> parmer) {
        if (isViewAttached())
            getView().showProgress();
        getAppComponent().getAPIService()
                .deleteFinancialStatistics(token, parmer)
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
                                getView().OnDeleteFinancialStatistics(userBeanHttpResult.getData());
                            }
                        }

                    }
                });

    }
}
