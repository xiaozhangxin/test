package com.akan.wms.mvp.fragment.mine;

import android.os.Bundle;
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
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.FirstEvent;
import com.akan.wms.bean.UserBean;
import com.akan.wms.mvp.base.BaseFragment;
import com.akan.wms.mvp.presenter.mine.ChangePwdpresenter;
import com.akan.wms.mvp.view.mine.IChangePwdView;
import com.akan.wms.util.SpSingleInstance;
import com.akan.wms.util.ToastUtil;
import com.king.base.util.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ChangePwdFragment extends BaseFragment<IChangePwdView, ChangePwdpresenter> implements IChangePwdView {


    Unbinder unbinder;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.textView8)
    TextView textView8;
    @BindView(R.id.member_account)
    TextView memberAccount;
    @BindView(R.id.etOldPwd)
    EditText etOldPwd;
    @BindView(R.id.ckOne)
    CheckBox ckOne;
    @BindView(R.id.etOneNewPwd)
    EditText etOneNewPwd;
    @BindView(R.id.ckTwo)
    CheckBox ckTwo;
    @BindView(R.id.etTwoNewPwd)
    EditText etTwoNewPwd;
    @BindView(R.id.ckThree)
    CheckBox ckThree;
    @BindView(R.id.btnLogin)
    Button btnLogin;


    private UserBean userBean;
    private Map<String, String> map = new HashMap<>();

    public static ChangePwdFragment newIntance() {
        Bundle args = new Bundle();
        ChangePwdFragment fragment = new ChangePwdFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getRootViewId() {
        return R.layout.fragment_change_pwd;
    }

    @Override
    public void initUI() {
        tvTitle.setText("修改密码");

        ckOne.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ckOne.setButtonDrawable(R.drawable.eye_h);
                    etOldPwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    ckOne.setButtonDrawable(R.drawable.eye);
                    etOldPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
        ckTwo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ckTwo.setButtonDrawable(R.drawable.eye_h);
                    etOneNewPwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    ckTwo.setButtonDrawable(R.drawable.eye);
                    etOneNewPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
        ckThree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ckThree.setButtonDrawable(R.drawable.eye_h);
                    etTwoNewPwd.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    ckThree.setButtonDrawable(R.drawable.eye);
                    etTwoNewPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });

    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        memberAccount.setText(userBean.getStaff_account());
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
        ToastUtils.showToast(context.getApplicationContext(), e.getMessage());
    }







    @OnClick({R.id.ivLeft, R.id.btnLogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;

            case R.id.btnLogin:
                String oldPwd = etOldPwd.getText().toString().trim();
                if (TextUtils.isEmpty(oldPwd)) {
                    ToastUtils.showToast(context.getApplicationContext(), getString(R.string.input_old_pwd));
                    return;
                }

                String pwdOne = etOneNewPwd.getText().toString().trim();
                if (TextUtils.isEmpty(pwdOne)) {
                    ToastUtils.showToast(context.getApplicationContext(), getString(R.string.input_pwd));
                    return;
                }
                if (pwdOne.length() < 6) {
                    ToastUtils.showToast(context.getApplicationContext(), getString(R.string.pwd_length_no));
                    return;
                }

                String reg = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{1,20}$";
                Pattern pattern = Pattern.compile(reg);
                Matcher matcher = pattern.matcher(pwdOne);
                boolean b = matcher.matches();
                if (!b) {
                    ToastUtils.showToast(context.getApplicationContext(), "密码至少要有一位字母和数字");
                    return;
                }

                String pwdTwo = etTwoNewPwd.getText().toString().trim();
                if (TextUtils.isEmpty(pwdTwo)) {
                    ToastUtils.showToast(context.getApplicationContext(), getString(R.string.input_pwd_two));
                    return;
                }
                if (!pwdOne.equals(pwdTwo)) {
                    ToastUtils.showToast(context.getApplicationContext(), getString(R.string.new_twopwd_defrevent));
                    return;
                }
                if (oldPwd.equals(pwdOne)){
                    ToastUtils.showToast(context.getApplicationContext(), "旧密码不能跟新密码相同");
                    return;
                }
                map.clear();
                map.put("staff_id", userBean.getStaff_id());
                map.put("old_password", oldPwd);
                map.put("staff_password", pwdOne);
                getPresenter().updateStaffPassword(userBean.getStaff_token(),map);
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

    @Override
    public void OnUpdateStaffPassword(String data) {
        ToastUtil.showToast(context.getApplicationContext(),data);
        EventBus.getDefault().post(new FirstEvent("expired"));
        finish();

    }

    @Override
    public void OnTnsertCode(String data) {

    }

    @Override
    public void ongetwangjiminma(String data) {

    }
}
