package com.akan.wms.mvp.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.akan.wms.MainActivity;
import com.akan.wms.R;
import com.akan.wms.bean.AppVersionBean;
import com.akan.wms.bean.FirstEvent;
import com.akan.wms.bean.StaffOrgsBean;
import com.akan.wms.bean.UserBean;
import com.akan.wms.mvp.base.BaseFragment;
import com.akan.wms.mvp.presenter.LoginPresenter;
import com.akan.wms.mvp.view.ILoginView;
import com.akan.wms.util.LoadingUtil;
import com.akan.wms.util.ToastUtil;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by sh-lx on 2017/7/12.
 */

public class LoginFragment extends BaseFragment<ILoginView, LoginPresenter> implements ILoginView {


    Unbinder unbinder;
    @BindView(R.id.imgOne)
    ImageView imgOne;

    @BindView(R.id.etAccount)
    EditText etAccount;
    @BindView(R.id.rlOne)
    RelativeLayout rlOne;
    @BindView(R.id.imgTwo)
    ImageView imgTwo;
    @BindView(R.id.ivClean)
    ImageView ivClean;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.lineThree)
    View lineThree;
    @BindView(R.id.pwdCheck)
    CheckBox pwdCheck;
    @BindView(R.id.rlTwo)
    RelativeLayout rlTwo;
    @BindView(R.id.tvType)
    TextView tvType;
    @BindView(R.id.ok)
    Button ok;
    @BindView(R.id.tvForget)
    TextView tvForget;


    private String mOrg_id = "";

    private Map<String, String> map = new HashMap<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            Window window = getActivity().getWindow();
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    public static LoginFragment newInstance() {
        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getRootViewId() {
        return R.layout.fragment_login;
    }

    @Override
    public void initUI() {

        SharedPreferences sp = context.getSharedPreferences("login", MODE_PRIVATE);
        String username = sp.getString("username", "");
        String password = sp.getString("pwd", "");
        String typeName = sp.getString("typeName", "");
        mOrg_id = sp.getString("typeId", "");
        etAccount.setText(username);
        etPassword.setText(password);
        tvType.setText(typeName);

        pwdCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    pwdCheck.setButtonDrawable(R.drawable.eye_h);
                    etPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    pwdCheck.setButtonDrawable(R.drawable.eye);
                    etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });

        etAccount.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    imgOne.setImageResource(R.drawable.login_phone_h);
                    rlOne.setBackgroundResource(R.drawable.setbar_color3);
                } else {
                    rlOne.setBackgroundResource(R.drawable.setbar_gray3);
                    imgOne.setImageResource(R.drawable.login_phone);
                }
            }
        });
        etPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    imgTwo.setImageResource(R.drawable.login_pwd_h);
                    rlTwo.setBackgroundResource(R.drawable.setbar_color3);
                } else {
                    imgTwo.setImageResource(R.drawable.login_pwd);
                    rlTwo.setBackgroundResource(R.drawable.setbar_gray3);
                }
            }
        });

        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (TextUtils.isEmpty(s.toString())) {
                    ivClean.setVisibility(View.GONE);
                } else {
                    ivClean.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
/*                if (TextUtils.isEmpty(tvType.getText().toString())) {
                    String toString = s.toString();
                    if (TextUtils.isEmpty(toString)) {
                        return;
                    }
                    map.clear();
                    map.put("staff_account", toString);
                    getPresenter().queryStaffOrgs(map);
                }*/
            }
        });

    }

    @Override
    public void initData() {
        map.put("version_type", "android");
        getPresenter().getAppVersionDetail(map);

    }

    @Override
    public void onGetlogin(UserBean data) {
        LoadingUtil.closeDialog(loading);
        saveUser(data);
        getList(data);
        startActivity(MainActivity.class);
        finish();
    }

    @Override
    public void queryStaffOrgs(List<StaffOrgsBean> data) {
        if (data.size() > 0) {
            StaffOrgsBean orgsBean = data.get(0);
            mOrg_id = orgsBean.getId();
            tvType.setText(orgsBean.getName());
        }

    }



    /**
     * 获取版本号
     */
    public String getVersion() {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            String version = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "未获取到版本号";
        }
    }



    @Override
    public void OnGetAppVersionDetail(AppVersionBean data) {
        if (data.needUpdate()){
            showDialogUpdate(data.getVersion_no(), data.getUpdate_content(), data.getMust_update());//弹出提示版本更新的对话框
        }
    }

    /**
     * 提示版本更新的对话框
     */
    private void showDialogUpdate(String versionName, String content, String mustUpdate) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("发现新版本" + versionName + "！").
                setIcon(R.mipmap.ic_launcher).
                setMessage(content).
                setPositiveButton("立刻更新", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startSaftwareFragment();
                    }
                });
        if ("0".equals(mustUpdate)) {
            // 设置取消按钮,null是什么都不做，并关闭对话框
            builder.setNegativeButton(getString(R.string.cancel), null);
        }
        builder.setCancelable(false);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();


    }



    @OnClick({R.id.ok, R.id.ivClean, R.id.tvForget, R.id.tvType})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvForget:
                startForgetPwdFragment(etAccount.getText().toString());
                break;
            case R.id.ivClean:
                etPassword.setText("");
                ivClean.setVisibility(View.GONE);
                break;
            case R.id.tvType:
                String mAccount = etAccount.getText().toString().trim();
                if (TextUtils.isEmpty(mAccount)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请先输入登录账号");
                    return;
                }
                startChooseOrganizationFragment(mAccount, tvType.getText().toString());
                break;
            case R.id.ok:
                String account = etAccount.getText().toString().trim();
                if (TextUtils.isEmpty(account)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入登录账号");
                    return;
                }
                String password = etPassword.getText().toString().trim();
                if (TextUtils.isEmpty(password)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入登录密码");
                    return;
                }
                String mType = tvType.getText().toString().trim();
                if (TextUtils.isEmpty(mType)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择所属公司");
                    return;
                }
                ok.setEnabled(false);
                loading = LoadingUtil.createLoading(getActivity(), "登录中...");
                saveLoginInfo(context, account, password, mType, mOrg_id);
                map.put("staff_account", account);
                map.put("staff_password", password);
                map.put("terminal_type", "app");
                map.put("org_id", mOrg_id);
                getPresenter().getLogin(map);
                break;
        }
    }


    /**
     * 使用SharedPreferences保存用户登录信息
     *
     * @param context
     * @param username
     */
    public static void saveLoginInfo(Context context, String username, String pwd, String typeName, String typeId) {
        SharedPreferences sharedPre = context.getSharedPreferences("login", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPre.edit();
        editor.putString("username", username);
        editor.putString("pwd", pwd);
        editor.putString("typeName", typeName);
        editor.putString("typeId", typeId);
        editor.commit();
    }

    private void getList(UserBean bean) {
        SharedPreferences sp = context.getSharedPreferences("userList", MODE_PRIVATE);
        String list = sp.getString("list", "");
        if (TextUtils.isEmpty(list) | "[]".equals(list)) {
            ArrayList<UserBean> newList = new ArrayList();
            newList.add(bean);
            String toJson = new Gson().toJson(newList);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("list", toJson);
            editor.commit();
        } else {
            boolean ishave = false;
            List<UserBean> oldList = new ArrayList();
            Gson gson = new Gson();
            JsonArray array = new JsonParser().parse(list).getAsJsonArray();
            for (JsonElement elem : array) {
                UserBean usern = gson.fromJson(elem, UserBean.class);
                if (usern.getStaff_id().equals(bean.getStaff_id())) {
                    ishave = true;
                    oldList.add(bean);
                } else {
                    oldList.add(usern);
                }
            }
            if (!ishave) {
                oldList.add(bean);
            }
            String toJson = new Gson().toJson(oldList);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("list", toJson);
            editor.commit();

        }
    }


    private Dialog loading;

    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {
    }

    @Override
    public void onError(Throwable e) {
        ok.setEnabled(true);
        LoadingUtil.closeDialog(loading);
        ToastUtil.showToast(context.getApplicationContext(), e.getMessage());
    }

    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter(getApp());
    }



    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        EventBus.getDefault().register(this);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //移除焦点监听
        etAccount.setOnFocusChangeListener(null);
        etPassword.setOnFocusChangeListener(null);
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(FirstEvent event) {
        String msg = event.getMsg();
        switch (msg) {
            case "change_org":
                StaffOrgsBean bean = event.getmBean();
                tvType.setText(bean.getName());
                mOrg_id = bean.getId();
                break;

        }

    }


}
