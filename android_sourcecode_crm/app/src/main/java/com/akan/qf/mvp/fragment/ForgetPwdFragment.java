package com.akan.qf.mvp.fragment;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.akan.qf.R;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.presenter.mine.ChangePwdpresenter;
import com.akan.qf.mvp.view.mine.IChangePwdView;
import com.akan.qf.util.ToastUtil;


import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by admin on 2019/4/23.
 */

public class ForgetPwdFragment extends BaseFragment<IChangePwdView, ChangePwdpresenter> implements IChangePwdView {
    Unbinder unbinder;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.registered_number)
    EditText registeredNumber;
    @BindView(R.id.registered_new_password)
    EditText registeredNewPassword;
    @BindView(R.id.ckOne)
    CheckBox ckOne;
    @BindView(R.id.registered_news_password)
    EditText registeredNewsPassword;
    @BindView(R.id.ckTwo)
    CheckBox ckTwo;
    @BindView(R.id.registered_yzm)
    EditText registeredYzm;
    @BindView(R.id.get_yzm)
    TextView getYzm;
    @BindView(R.id.relativeLayout2)
    RelativeLayout relativeLayout2;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    private Map<String, String> map = new HashMap<>();
    private String account;


    public static ForgetPwdFragment newInstance(String account) {
        Bundle args = new Bundle();
        ForgetPwdFragment fragment = new ForgetPwdFragment();
        fragment.account=account;
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getRootViewId() {
        return R.layout.fragment_forget_pwd;
    }

    @Override
    public void initUI() {
        tvTitle.setText("忘记密码");
        if (!TextUtils.isEmpty(account)){
            registeredNumber.setText(account);
        }
        ckOne.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ckOne.setButtonDrawable(R.drawable.eye_h);
                    registeredNewPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    ckOne.setButtonDrawable(R.drawable.eye);
                    registeredNewPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
        ckTwo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ckTwo.setButtonDrawable(R.drawable.eye_h);
                    registeredNewsPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    ckTwo.setButtonDrawable(R.drawable.eye);
                    registeredNewsPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
    }

    @Override
    public void initData() {
    }

    @Override
    public void OnTnsertCode(String data) {
        ToastUtil.showToast(context.getApplicationContext(), "验证码已发送");
        timer.start();
        getYzm.setEnabled(false);

    }


    @Override
    public void ongetwangjiminma(String data) {
        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();
    }

    @OnClick({R.id.ivLeft, R.id.get_yzm, R.id.btnLogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.get_yzm:
                String mPhone = registeredNumber.getText().toString().trim();
                if (TextUtils.isEmpty(mPhone)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入账号");
                    return;
                }
                map.clear();
                map.put("staff_account", mPhone);
                getPresenter().insertCode(map);

                break;
            case R.id.btnLogin:
                String nPhone = registeredNumber.getText().toString().trim();
                if (TextUtils.isEmpty(nPhone)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入账号");
                    return;
                }

                String mPwdOne = registeredNewPassword.getText().toString().trim();
                if (TextUtils.isEmpty(mPwdOne)) {
                    ToastUtil.showToast(context.getApplicationContext(), getString(R.string.input_pwd));
                    return;
                }
                if (mPwdOne.length() < 6) {
                    ToastUtil.showToast(context.getApplicationContext(), getString(R.string.pwd_length_no));
                    return;
                }

                String reg = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{1,20}$";
                Pattern pattern = Pattern.compile(reg);
                Matcher matcher = pattern.matcher(mPwdOne);
                boolean b = matcher.matches();
                if (!b) {
                    ToastUtil.showToast(context.getApplicationContext(), "密码至少要有一位字母和数字");
                    return;
                }

                String mPwdTwo = registeredNewsPassword.getText().toString().trim();
                if (TextUtils.isEmpty(mPwdTwo)) {
                    ToastUtil.showToast(context.getApplicationContext(), getString(R.string.input_pwd_two));
                    return;
                }
                if (!mPwdTwo.equals(mPwdOne)) {
                    ToastUtil.showToast(context.getApplicationContext(), "两次密码输入不一致");
                    return;
                }
                String mCode = registeredYzm.getText().toString().trim();
                if (TextUtils.isEmpty(mCode)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入验证码");
                    return;
                }
                map.clear();
                map.put("staff_account", nPhone);
                map.put("staff_password", mPwdOne);
                map.put("code", mCode);
                getPresenter().getwangjiminma(map);
                break;
        }
    }

    CountDownTimer timer = new CountDownTimer(60000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            getYzm.setText(millisUntilFinished / 1000 + "秒");
        }

        @Override
        public void onFinish() {
            getYzm.setEnabled(true);
            getYzm.setText("获取验证码");
        }
    };

    @Override
    public void onDestroy() {
        timer.cancel();
        super.onDestroy();
    }

    @Override
    public ChangePwdpresenter createPresenter() {
        return new ChangePwdpresenter(getApp());
    }


    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        ToastUtil.showToast(context.getApplicationContext(), e.getMessage());
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

    @Override
    public void OnUpdateStaffPassword(String data) {

    }


}
