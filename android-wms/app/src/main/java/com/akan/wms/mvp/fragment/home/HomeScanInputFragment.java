package com.akan.wms.mvp.fragment.home;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.wms.Constants;
import com.akan.wms.R;
import com.akan.wms.bean.BarMsgBean;
import com.akan.wms.bean.UserBean;
import com.akan.wms.bean.WarnTwoBean;
import com.akan.wms.mvp.activity.ContentActivity;
import com.akan.wms.mvp.base.BaseFragment;
import com.akan.wms.mvp.presenter.home.HomePresenter;
import com.akan.wms.mvp.view.home.IHomeView;
import com.akan.wms.util.SpSingleInstance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.akan.wms.Constants.HOME_SCAN;

public class HomeScanInputFragment extends BaseFragment<IHomeView, HomePresenter> implements IHomeView {

    Unbinder unbinder;
    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.tv_code_1)
    TextView tvCode1;
    @BindView(R.id.tv_code_2)
    TextView tvCode2;
    @BindView(R.id.tv_code_3)
    TextView tvCode3;
    @BindView(R.id.tv_code_4)
    TextView tvCode4;
    @BindView(R.id.code)
    EditText code;
    @BindView(R.id.layout_code)
    ConstraintLayout layoutCode;
    Unbinder unbinder1;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        makeStatusBarTransparent(getActivity());
    }

    @NonNull
    @Override
    public HomePresenter createPresenter() {
        return new HomePresenter(getApp());
    }

    public static void makeStatusBarTransparent(Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return;
        }
        Window window = activity.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            int option = window.getDecorView().getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            window.getDecorView().setSystemUiVisibility(option);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    public static HomeScanInputFragment newInstance() {
        Bundle args = new Bundle();
        HomeScanInputFragment fragment = new HomeScanInputFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_home_scan_input;
    }

    @Override
    public void initUI() {
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
                    tvCode1.setText("");
                } else if (s.length() > 0 && s.length() <= 5) {
                    tvCode1.setText(s.subSequence(0, s.length()));
                    tvCode2.setText("");
                } else if (s.length() > 5 && s.length() <= 10) {
                    tvCode2.setText(s.subSequence(5, s.length()));
                    tvCode3.setText("");
                } else if (s.length() > 10 && s.length() <= 15) {
                    tvCode3.setText(s.subSequence(10, s.length()));
                    tvCode4.setText("");
                } else if (s.length() > 15 && s.length() <= 20) {
                    tvCode4.setText(s.subSequence(15, s.length()));
                }

                if (s.length()==20){
                    map.clear();
                    map.put("barCode",s.toString());
                    getPresenter().selectItemBarMsgList(userBean.getStaff_token(),map);
                }
            }
        });
        code.setFocusable(true);
        code.setFocusableInTouchMode(true);
        code.requestFocus();
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                InputMethodManager imm= (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(code,0);
            }
        },200);



    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
    }

    private void openKeyboard() {
        InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * 隐藏键盘
     */
    protected void hideInputMethod() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        View v = getActivity().getWindow().peekDecorView();
        if (null != v) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }


    /**
     * 显示软键盘
     *
     * @param v
     */
    public void showInputMethod(final EditText v) {
        v.requestFocus();
        InputMethodManager imm = (InputMethodManager) context.getApplicationContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(v, InputMethodManager.SHOW_IMPLICIT);
    }

    @OnClick({R.id.ivBack,R.id.layout_code})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivBack:
                finish();
                break;
            case R.id.layout_code:
                code.requestFocus();
                openKeyboard();
                break;

        }
    }


    @Override
    public void onQueryBoardWarnings(List<WarnTwoBean> data) {

    }

    @Override
    public void onSelectItemBarMsgList(BarMsgBean data) {
        Intent intent = new Intent(getActivity(), ContentActivity.class);
        intent.putExtra(Constants.KEY_FRAGMENT, HOME_SCAN);
        intent.putExtra("bean",data);
        startActivity(intent);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("提示");
        builder.setMessage("查询失败,是否重新輸入？");
        builder.setCancelable(true);
        builder.setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                code.setText("");
                tvCode1.setText("");
                tvCode2.setText("");
                tvCode3.setText("");
                tvCode4.setText("");

            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.create().show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder1.unbind();
    }
}
