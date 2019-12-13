package com.ak.pt.mvp.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.appcompat.app.AlertDialog;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.ak.pt.App;
import com.ak.pt.Constants;
import com.ak.pt.MainActivity;
import com.ak.pt.R;
import com.ak.pt.bean.AppVersionBean;
import com.ak.pt.bean.DepartmentBean;
import com.ak.pt.bean.FirstEvent;
import com.ak.pt.bean.FirstEventWorker;
import com.ak.pt.bean.UserBean;
import com.ak.pt.bean.WorkerBean;
import com.ak.pt.mvp.activity.ContentActivity;
import com.ak.pt.mvp.base.BaseActivity;
import com.ak.pt.mvp.presenter.LoginPresenter;
import com.ak.pt.mvp.view.ILoginView;
import com.ak.pt.util.CustomDialog;
import com.ak.pt.util.SPUtils;
import com.ak.pt.util.SpSingleInstance;
import com.ak.pt.util.ToastUtil;
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
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * Created by sh-lx on 2017/7/12.
 */

public class LoginActivity extends BaseActivity<ILoginView, LoginPresenter> implements ILoginView {

    @BindView(R.id.etAccount)
    EditText etAccount;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.ok)
    Button ok;
    @BindView(R.id.parentview)
    ConstraintLayout parentview;
    Unbinder unbinder;

    //版本更新
    private String versionName = "";
    private Map<String, String> map = new HashMap<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        App.getInstance().backLogin();
        // getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_login;
    }

    @Override
    public void initUI() {
        unbinder = ButterKnife.bind(this);

        SharedPreferences sp = getSharedPreferences("login", MODE_PRIVATE);
        String username = sp.getString("username", "");
        String password = sp.getString("pwd", "");
        etAccount.setText(username);
        etPassword.setText(password);
    }


    @Override
    public void initData() {
        getVersion();
        map.clear();
        map.put("version_type", "android");
        getPresenter().getAppVersionDetail(map);
    }

    @Override
    public void onGetlogin(UserBean data) {
        String[] split = data.getGroup_parent_uuid().split("#");
        data.setGroup_parent_uuid_new(split[split.length - 2]);
        saveUser(data);
        getList(data);
        //极光推送绑定别名
        JPushInterface.setAlias(this, data.getStaff_id(), (i, s, set) -> {
        });
        //打开主界面
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    //保存个人信息
    protected void saveUser(UserBean userBean) {
        // 登录成功
        SPUtils.saveObJ1(this, Constants.USER_BEAN, userBean);
        SpSingleInstance.getSpSingleInstance().setUserBean(userBean);
    }

    //获取保存用户列表
    private void getList(UserBean bean) {
        SharedPreferences sp = getSharedPreferences("userList", MODE_PRIVATE);
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
                if (bean.getStaff_id().equals(usern.getStaff_id())) {
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

    @OnClick({R.id.ok, R.id.tvForget})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvForget:
                startForgetPwdFragment("0", "");
                break;
            case R.id.ok:
                String account = etAccount.getText().toString().trim();
                if (TextUtils.isEmpty(account)) {
                    ToastUtil.showToast(this, getString(R.string.input_name));
                    return;
                }
                String password = etPassword.getText().toString().trim();
                if (TextUtils.isEmpty(password)) {
                    ToastUtil.showToast(this, getString(R.string.input_pwd));
                    return;
                }

                if ("a123456".equals(password)) {
                    showChangePwdDialog(account);
                    return;
                }
                saveLoginInfo(this, account, password);
                map.put("staff_account", account);
                map.put("staff_password", password);
                map.put("terminal_type", "app");
                getPresenter().getLogin(map);
                break;
        }
    }

    //密码过于简单弹框
    private void showChangePwdDialog(String account) {
        final CustomDialog.Builder builder = new CustomDialog.Builder(this);
        builder.setMessage("您输入的密码为初始密码,请您修改后在进行登录！");
        builder.setPositiveButton(getString(R.string.fix_rightnow), (dialog, which) -> {
            startForgetPwdFragment("1", account);
            dialog.dismiss();
        });
        builder.setNegativeButton("重新输入", (dialog, which) -> {
            etPassword.setText("");
            dialog.dismiss();
        });
        builder.onCreate().show();
    }


    /**
     * 使用SharedPreferences保存用户登录信息
     *
     * @param context
     * @param username
     */
    public static void saveLoginInfo(Context context, String username, String pwd) {
        SharedPreferences sharedPre = context.getSharedPreferences("login", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPre.edit();
        editor.putString("username", username);
        editor.putString("pwd", pwd);
        editor.commit();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        ToastUtil.showToast(this, e.getMessage());
    }

    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter(getApp());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(FirstEvent msg) {
        switch (msg.getMsg()) {
            case "change_success":
                etPassword.setText("");
                break;
        }

    }

    /**
     * 获取版本号
     */
    public String getVersion() {
        try {
            PackageManager manager = getPackageManager();
            PackageInfo info = manager.getPackageInfo(getPackageName(), 0);
            String version = info.versionName;
            versionName = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


    @Override
    public void OnGetAppVersionDetail(AppVersionBean data) {

        if (!TextUtils.isEmpty(versionName) && "1".equals(data.getMust_update())) {
            if (!versionName.equals(data.getVersion_no())) {
                showDialogUpdate(data.getVersion_no(), data.getUpdate_content());
            } else {

            }
        }
    }

    // 提示版本更新的对话框
    private void showDialogUpdate(String versionName, String content) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("发现新版本V" + versionName + "！").
                setIcon(R.mipmap.ic_launcher).
                setMessage(content).
                setPositiveButton(getString(R.string.sure), (dialog, which) -> startSaftwareFragment())
                .setNegativeButton(getString(R.string.cancel), null);

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

}
