package com.akan.qf.mvp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akan.qf.R;
import com.akan.qf.bean.NullBean;
import com.akan.qf.bean.UserBean;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.presenter.WebLoginPresenter;
import com.akan.qf.mvp.view.IWebLoginView;
import com.akan.qf.util.SpSingleInstance;
import com.akan.qf.util.ToastUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by admin on 2018/12/4.
 */

public class WebLoginFragment extends BaseFragment<IWebLoginView, WebLoginPresenter> implements IWebLoginView {


    @BindView(R.id.close)
    TextView close;
    @BindView(R.id.ok)
    TextView ok;
    @BindView(R.id.cancel)
    TextView cancel;
    Unbinder unbinder;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private String values;

    public static WebLoginFragment newInstance(String values) {
        Bundle args = new Bundle();
        WebLoginFragment fragment = new WebLoginFragment();
        fragment.values = values;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_web_login;
    }

    @Override
    public void initUI() {

    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public WebLoginPresenter createPresenter() {
        return new WebLoginPresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.close, R.id.ok, R.id.cancel})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.close:
                finish();
                break;
            case R.id.ok:
                map.put("staff_account", userBean.getStaff_account());
                map.put("qrCode", values);
                getPresenter().scanQrLoginCode(userBean.getStaff_token(), map);

                break;
            case R.id.cancel:
                finish();
                break;
        }
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        ToastUtil.showToast(context.getApplicationContext(),e.getMessage());

    }

    @Override
    public void OnScanQrLoginCode(String data) {
        ToastUtil.showToast(context.getApplicationContext(), "操作成功");
        finish();
    }
}
