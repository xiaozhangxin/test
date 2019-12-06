package com.akan.qf.mvp.presenter;

import com.akan.qf.App;
import com.akan.qf.bean.staffGroupBeans;
import com.akan.qf.http.HttpResult;
import com.akan.qf.mvp.base.BasePresenter;
import com.akan.qf.mvp.view.IChoosePermissionView;

import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ChoosePermissionPresenter extends BasePresenter<IChoosePermissionView>{

    public ChoosePermissionPresenter(App app) {
        super(app);
    }
    public void getStaffGroupByDepartment(String token,Map<String, String> parmer) {
        if (isViewAttached()) {
            getView().showProgress();
        }
        getAppComponent().getAPIService()
                .getStaffGroupByDepartment(token,parmer)
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
                                getView().onGetStaffGroupByDepartment(userBeanHttpResult.getData());
                            }
                        }

                    }
                });
    }
}
