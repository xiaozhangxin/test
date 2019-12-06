package com.akan.qf.mvp.presenter.treeview;

import com.akan.qf.App;
import com.akan.qf.bean.ContributeBean;
import com.akan.qf.bean.ContributeClassBean;
import com.akan.qf.bean.staffGroupBeans;
import com.akan.qf.http.HttpResult;
import com.akan.qf.mvp.base.BasePresenter;
import com.akan.qf.mvp.view.treeview.IChooseDepartmentView;

import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by admin on 2018/7/5.
 */

public class ChooseDepartmentPresenter extends BasePresenter<IChooseDepartmentView>{
    public ChooseDepartmentPresenter(App app) {
        super(app);
    }
    public void getStaffGroupTreeByStaff(String token,Map<String, String> parmer) {
        if (isViewAttached()) {
            getView().showProgress();
        }
        getAppComponent().getAPIService()
                .getStaffGroupTreeByStaff(token,parmer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<List<staffGroupBeans>>>() {

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
                    public void onNext(HttpResult<List<staffGroupBeans>> userBeanHttpResult) {
                        if (userBeanHttpResult != null) {
                            if (isViewAttached()) {
                                getView().onGetStaffGroupTreeByStaff(userBeanHttpResult.getData());
                            }
                        }

                    }
                });
    }
    public void getContributeClassTree(String token, Map<String, String> parmer) {
        if (isViewAttached())
            getView().showProgress();
        getAppComponent().getAPIService()
                .getContributeClassTree(token, parmer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HttpResult<List<ContributeClassBean>>>() {

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
                    public void onNext(HttpResult<List<ContributeClassBean>> userBeanHttpResult) {
                        if (userBeanHttpResult != null) {
                            if (isViewAttached()) {
                                getView().OnGetContributeClassTree(userBeanHttpResult.getData());
                            }
                        }

                    }
                });

    }
}
