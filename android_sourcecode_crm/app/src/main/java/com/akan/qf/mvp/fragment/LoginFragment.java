package com.akan.qf.mvp.fragment;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.akan.qf.MainActivity;
import com.akan.qf.R;
import com.akan.qf.bean.AppVersionBean;
import com.akan.qf.bean.FirstEvent;
import com.akan.qf.bean.UserBean;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.presenter.LoginPresenter;
import com.akan.qf.mvp.view.ILoginView;
import com.akan.qf.util.ToastUtil;
import com.akan.qf.view.CustomDialog;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by sh-lx on 2017/7/12.
 */

public class LoginFragment extends BaseFragment<ILoginView, LoginPresenter> implements ILoginView {

    @BindView(R.id.etAccount)
    EditText etAccount;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.ok)
    Button ok;
    @BindView(R.id.parentview)
    ConstraintLayout parentview;
    Unbinder unbinder;
    private Map<String, String> versionMap = new HashMap<>();
    //版本更新
    private String versionName;
    private String server_address;
    private String isServer;
    private String webAddress;

    private Map<String, String> map = new HashMap<>();
    public static LoginFragment newInstance() {
        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
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
        etAccount.setText(username);
        etPassword.setText(password);
    }

    @Override
    public void initData() {
        versionName = getVersionName();
        versionMap.put("version_type", "android");
        getPresenter().getAppVersionDetail("", versionMap);
    }

    @Override
    public void onGetlogin(UserBean data) {
        if ("verification".equals(data.getSign())) {
            startVerificationfragment(data, getIdentity());
        } else {
            String[] split = data.getGroup_parent_uuid().split("#");
            data.setGroup_parent_uuid_new(split[split.length - 2]);
            saveUser(data);
            getList(data);
            JPushInterface.setAlias(context.getApplicationContext(), data.getStaff_id(), new TagAliasCallback() {
                @Override
                public void gotResult(int i, String s, Set<String> set) {
                }
            });
            startActivity(MainActivity.class);
            finish();
        }
    }

    @Override
    public void OnTnsertCode(String data) {

    }

    @Override
    public void OnGetAppVersionDetail(AppVersionBean data) {
        server_address = data.getServer_address();
        isServer = data.getServer_update();
        webAddress = data.getDownload_address();
        if (!TextUtils.isEmpty(versionName)) {
            if (!versionName.equals(data.getVersion_no())) {
                if ("0".equals(data.getMust_update())) {
                } else {
                    showDialogUpdate(data.getVersion_no(), data.getUpdate_content());
                }
            } else {
            }
        }
    }

    @OnClick({R.id.ok, R.id.tvForget})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvForget:
                //showPopWindow();
                startForgetPwdFragment(etAccount.getText().toString());
                break;
            case R.id.ok:
                String account = etAccount.getText().toString().trim();
                if (TextUtils.isEmpty(account)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入用户名");
                    return;
                }
                String password = etPassword.getText().toString().trim();
                if (TextUtils.isEmpty(password)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入密码");
                    return;
                }
                saveLoginInfo(context, account, password);
                map.put("staff_account", account);
                map.put("staff_password", password);
                map.put("terminal_type", "app");
                map.put("app_sign", getIdentity());
                getPresenter().getLogin("", map);
                break;
        }
    }

    //获取设备唯一标识
    public String getIdentity() {
        SharedPreferences sp = context.getSharedPreferences("identity", MODE_PRIVATE);
        String uuid = sp.getString("uuid", "");
        if (TextUtils.isEmpty(uuid)) {
            uuid = UUID.randomUUID().toString();
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("uuid", uuid);
            editor.commit();
        }
        return uuid;
    }

    private void showPopWindow() {
        final CustomDialog.Builder builder = new CustomDialog.Builder(context);
        builder.setOutSideButton(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.onCreateOne().show();
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
                    ishave=true;
                    oldList.add(bean);
                } else {
                    oldList.add(usern);
                }
            }
            if (!ishave){
                oldList.add(bean);
            }
            String toJson = new Gson().toJson(oldList);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("list", toJson);
            editor.commit();

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
        ToastUtil.showToast(context.getApplicationContext(), e.getMessage());
    }

    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter(getApp());
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
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(FirstEvent event) {
        switch (event.getMsg()) {
            case "finishLogin":
                finish();
                break;
        }


    }


    //----------------------------------------版本更新----------------------------------------------//
    /*
 * 获取当前程序的版本名
 */
    private String getVersionName() {
        //获取packagemanager的实例
        PackageManager packageManager = getActivity().getPackageManager();
        //getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(getActivity().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packInfo.versionName;
    }

    private final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 102;//内存权限

    /**
     * 提示版本更新的对话框
     */
    private void showDialogUpdate(String vesion, String content) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("发现新版本V" + vesion + "!").
                setIcon(R.mipmap.ic_launcher).
                setMessage(content).
                setPositiveButton("确定", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if ("0".equals(isServer)) {
                            if (!TextUtils.isEmpty(webAddress)) {
                                Uri uri = Uri.parse(webAddress);
                                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                startActivity(intent);
                            }
                        } else {
                            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                    != PackageManager.PERMISSION_GRANTED) {
                                //申请WRITE_EXTERNAL_STORAGE权限
                                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
                            } else {
                                loadNewVersionProgress();//下载最新的版本程序
                            }
                        }
                    }
                }).setNegativeButton("取消", null);
        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.show();
    }


    /**
     * 下载新版本程序，需要子线程
     */
    private void loadNewVersionProgress() {
        final ProgressDialog pd;    //进度条对话框
        pd = new ProgressDialog(getActivity());
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setCanceledOnTouchOutside(false);
        pd.setMessage("正在下载更新");
        pd.show();
        //启动子线程下载任务
        new Thread() {
            @Override
            public void run() {
                try {
                    File file = getFileFromServer(server_address, pd);
                    sleep(2000);
                    installApk(file);
                    pd.dismiss();
                } catch (Exception e) {
                    //下载apk失败
                    ToastUtil.showToast(context.getApplicationContext(), "下载新版本失败");
                    e.printStackTrace();
                }
            }
        }.start();
    }


    /**
     * 从服务器获取apk文件的代码
     * 传入网址uri，进度条对象即可获得一个File文件
     * （要在子线程中执行哦）
     */
    public static File getFileFromServer(String uri, ProgressDialog pd) throws Exception {
        //如果相等的话表示当前的sdcard挂载在手机上并且是可用的
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            URL url = new URL(uri);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            //获取到文件的大小
            pd.setMax(conn.getContentLength());
            InputStream is = conn.getInputStream();
            long time = System.currentTimeMillis();//当前时间的毫秒数
            File file = new File(Environment.getExternalStorageDirectory(), time + "updata.apk");
            FileOutputStream fos = new FileOutputStream(file);
            BufferedInputStream bis = new BufferedInputStream(is);
            byte[] buffer = new byte[1024];
            int len;
            int total = 0;
            while ((len = bis.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
                total += len;
                //获取当前下载量
                pd.setProgress(total);
            }
            fos.close();
            bis.close();
            is.close();
            return file;
        } else {

            return null;
        }
    }


    /**
     * 安装apk
     */
    protected void installApk(File file) {
        Intent intent = new Intent();
        //执行动作
        intent.setAction(Intent.ACTION_VIEW);
        //执行的数据类型
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        startActivity(intent);
    }


}
