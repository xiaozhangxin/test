package com.akan.qf.mvp.fragment.mine;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.qf.R;
import com.akan.qf.mvp.base.SimpleFragment;
import com.akan.qf.util.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by admin on 2017/11/30.
 */

public class SecurityCheckFragment extends SimpleFragment {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.code)
    EditText code;
    @BindView(R.id.ok)
    TextView ok;
    Unbinder unbinder;


    public static SecurityCheckFragment newInstance() {
        Bundle args = new Bundle();
        SecurityCheckFragment fragment = new SecurityCheckFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_check;
    }

    @Override
    public void initUI() {
        tvTitle.setText(R.string.security_check);
    }

    @Override
    public void initData() {
    }


    @OnClick({R.id.code, R.id.ok, R.id.ivLeft})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.code:
                break;
            case R.id.ok:
                if (TextUtils.isEmpty(code.getText().toString())) {
                    ToastUtil.showToast(context.getApplicationContext(),
                            getString(R.string.please_enter_the_security_code));
                    return;
                }
                startWeb("查询结果", "http://120.79.29.57/AkanWeb/QueryEnter.aspx?code="
                        + code.getText().toString() + "&dis=&cap=180.168.217.156");
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
