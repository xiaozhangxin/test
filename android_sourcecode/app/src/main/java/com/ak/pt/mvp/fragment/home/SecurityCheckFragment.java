package com.ak.pt.mvp.fragment.home;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.ak.pt.R;
import com.ak.pt.bean.AppPermissionsBean;
import com.ak.pt.mvp.base.SimpleFragment;
import com.ak.pt.util.ToastUtil;
import com.githang.statusbar.StatusBarCompat;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.ak.pt.mvp.fragment.adaily.SignFragment.REQUEST_CARERAM;
import static com.king.base.BaseInterface.REQUEST_CODE;


/**
 * Created by admin on 2018/11/30.
 */

public class SecurityCheckFragment extends SimpleFragment {

    @BindView(R.id.code)
    EditText code;
    @BindView(R.id.ok)
    TextView ok;
    Unbinder unbinder;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRightTwo)
    ImageView ivRightTwo;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;

    @BindView(R.id.tv_code_1)
    TextView tv_code_1;
    @BindView(R.id.tv_code_2)
    TextView tv_code_2;
    @BindView(R.id.tv_code_3)
    TextView tv_code_3;
    @BindView(R.id.tv_code_4)
    TextView tv_code_4;
    private AppPermissionsBean permissionsBean;

    public static SecurityCheckFragment newInstance(AppPermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        SecurityCheckFragment fragment = new SecurityCheckFragment();
        fragment.permissionsBean = permissionsBean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            StatusBarCompat.setStatusBarColor(getActivity(), getResources().getColor(R.color.white));
        } else {
            getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_check;
    }

    @Override
    public void initUI() {
        tvTitle.setText("防伪查询");
        ivRight.setVisibility(View.VISIBLE);
        ivRightTwo.setVisibility(View.VISIBLE);
        ivRight.setImageResource(R.drawable.jilu);
        ivRightTwo.setImageResource(R.drawable.saoma);

        code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //  4 5 4 5
                if (s.length() == 0) {
                    tv_code_1.setText("");
                } else if (s.length() > 0 && s.length() <= 4) {
                    tv_code_1.setText(s.subSequence(0, s.length()));
                    tv_code_2.setText("");
                } else if (s.length() > 4 && s.length() <= 9) {
                    tv_code_2.setText(s.subSequence(4, s.length()));
                    tv_code_3.setText("");
                } else if (s.length() > 9 && s.length() <= 13) {
                    tv_code_3.setText(s.subSequence(9, s.length()));
                    tv_code_4.setText("");
                } else if (s.length() > 13 && s.length() <= 18) {
                    tv_code_4.setText(s.subSequence(13, s.length()));
                }
            }
        });
    }

    @Override
    public void initData() {

    }


    @OnClick({R.id.layout_code, R.id.ok, R.id.ivLeft, R.id.ivRight, R.id.ivRightTwo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.ivRight:
                startSecurityListFragment(permissionsBean);
                break;
            case R.id.ivRightTwo:
                isHavePermission();
                break;
            case R.id.layout_code:
                code.requestFocus();
                openKeyboard();
                break;
            case R.id.ok:
                String mCode = code.getText().toString();
                if (TextUtils.isEmpty(mCode)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入防伪码");
                    return;
                }
                startWeb("查询结果", mCode, "http://120.79.29.57/AkanWeb/QueryEnter.aspx?code=" + mCode + "&dis=&cap=");
                break;
        }
    }

    private void openKeyboard() {
        InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    //-------------------------------------申请相机权限-------------------------------------------//

    public void isHavePermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CARERAM);
                return;
            } else {
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        } else {
            Intent intent = new Intent(getActivity(), CaptureActivity.class);
            startActivityForResult(intent, REQUEST_CODE);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            //处理扫描结果（在界面上显示）
            if (null != data) {
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    //ToastUtil.showToast(context.getApplicationContext(),result);
                    code.setText(result);
                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    ToastUtil.showToast(context.getApplicationContext(), "解析二维码失败");
                }
            }
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
