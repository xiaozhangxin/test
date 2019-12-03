package com.akan.wms.mvp.fragment.mine;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.akan.wms.R;
import com.akan.wms.bean.AppVersionBean;
import com.akan.wms.bean.UserBean;
import com.akan.wms.mvp.base.BaseFragment;
import com.akan.wms.mvp.presenter.mine.SoftwarePresenter;
import com.akan.wms.mvp.view.mine.ISoftwaredView;
import com.akan.wms.util.SpSingleInstance;
import com.akan.wms.util.ToastUtil;
import com.king.base.util.ToastUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SaftwareFragment extends BaseFragment<ISoftwaredView, SoftwarePresenter> implements ISoftwaredView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.tvNo)
    TextView tvNo;
    @BindView(R.id.tvCheck)
    TextView tvCheck;
    Unbinder unbinder;
    private int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 1001;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private String versionName;
    private String server_address;
    private String webAddress;
    private String isServer;

    public static SaftwareFragment newInstance() {
        Bundle args = new Bundle();
        SaftwareFragment fragment = new SaftwareFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_saftware;
    }

    @Override
    public void initUI() {
        tvTitle.setText("软件信息");
        tvNo.setText("版本号：V" + getVersion());
    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        map.put("version_type", "android");
        getPresenter().getAppVersionDetail(map);

    }

    /**
     * 获取版本号
     */
    public String getVersion() {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            String version = info.versionName;
            versionName = info.versionName;
            return version;
        } catch (Exception e) {
            e.printStackTrace();
            return "未获取到版本号";
        }
    }


    @Override
    public void OnGetAppVersionDetail(AppVersionBean data) {
        server_address = data.getServer_address();
        webAddress = data.getDownload_address();
        isServer = data.getServer_update();
        if (data.needUpdate()){
            showDialogUpdate(data.getVersion_no(), data.getUpdate_content(), data.getMust_update());//弹出提示版本更新的对话框
        } else {
            ToastUtils.showToast(context.getApplicationContext(), "已是最新版本！");
        }
    }


    @OnClick({R.id.ivLeft, R.id.tvCheck})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvCheck:
                map.put("version_type", "android");
                getPresenter().getAppVersionDetail(map);
                break;
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
                setPositiveButton(getString(R.string.sure), new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if ("0".equals(isServer)) {//市场更新
                            if (!TextUtils.isEmpty(webAddress)) {
                                Uri uri = Uri.parse(webAddress);
                                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                                startActivity(intent);
                            }
                        } else {//服务器更新
                            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                                //申请WRITE_EXTERNAL_STORAGE权限
                                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
                            } else {
                                loadNewVersionProgress();
                            }

                        }
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


    //请求权限结果
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                loadNewVersionProgress();//下载最新的版本程序
            } else {
                ToastUtils.showToast(context.getApplicationContext(), getString(R.string.permission_ask_fail));
            }
        }
    }

    // 显示下载进度(下载新版本程序，需要子线程)
    private void loadNewVersionProgress() {
        final ProgressDialog pd;
        pd = new ProgressDialog(getActivity());
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.setCanceledOnTouchOutside(false);
        pd.setMessage(getString(R.string.update_loading));
        pd.show();
        //启动子线程下载任务
        new Thread() {
            @Override
            public void run() {
                try {
                    File file = getFileFromServer(server_address, pd);
                    sleep(2000);
                    installApk(file);
                    pd.dismiss(); //结束掉进度条对话框
                } catch (Exception e) {
                    //下载apk失败
                    Looper.prepare();
                    Toast.makeText(context, getString(R.string.update_fail), Toast.LENGTH_SHORT).show();
                    Looper.loop();

                }
            }
        }.start();
    }

    //从服务器获取apk文件的代码  传入网址uri，进度条对象即可获得一个File文件（要在子线程中执行）
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
            File file = new File(Environment.getExternalStorageDirectory(), "wms" + time + ".apk");
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

    // 安装apk
    protected void installApk(File file) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        startActivity(intent);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public SoftwarePresenter createPresenter() {
        return new SoftwarePresenter(getApp());
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
        ToastUtil.showToast(context.getApplicationContext(), e.getMessage());

    }


}
