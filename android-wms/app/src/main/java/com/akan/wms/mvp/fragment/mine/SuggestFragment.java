package com.akan.wms.mvp.fragment.mine;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.UserBean;
import com.akan.wms.mvp.base.BaseFragment;
import com.akan.wms.mvp.presenter.mine.SuggestPresenter;
import com.akan.wms.mvp.view.mine.ISuggestView;
import com.akan.wms.util.SpSingleInstance;
import com.king.base.util.ToastUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by admin on 2018/11/22.
 */

public class SuggestFragment extends BaseFragment<ISuggestView, SuggestPresenter> implements ISuggestView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.etContent)
    TextView etContent;
    Unbinder unbinder;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    public static SuggestFragment newInstance() {
        Bundle args = new Bundle();
        SuggestFragment fragment = new SuggestFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_suggest;
    }

    @Override
    public void initUI() {
        tvTitle.setText("意见反馈");
    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
    }

    @OnClick({R.id.ivLeft, R.id.tvOk})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvOk:
                String s = etContent.getText().toString().trim();
                if (TextUtils.isEmpty(s)){
                    ToastUtils.showToast(context.getApplicationContext(),"请输入要反馈的意见");
                    return;
                }
                map.clear();
                map.put("staff_id", userBean.getStaff_id());
                map.put("suggest_text",s);
                getPresenter().insertSuggestList(userBean.getStaff_token(),map);
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public SuggestPresenter createPresenter() {
        return new SuggestPresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        ToastUtils.showToast(context.getApplicationContext(), e.getMessage());

    }



    @Override
    public void OnInsertSuggestList(String data) {
        ToastUtils.showToast(context.getApplicationContext(),"提交成功,感谢您的反馈");
        finish();

    }
}

