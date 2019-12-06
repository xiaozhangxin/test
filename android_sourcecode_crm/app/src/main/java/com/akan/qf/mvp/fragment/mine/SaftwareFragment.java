package com.akan.qf.mvp.fragment.mine;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
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

import com.akan.qf.R;
import com.akan.qf.bean.AppVersionBean;
import com.akan.qf.bean.UserBean;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.presenter.mine.SoftwareRelatedPresenter;
import com.akan.qf.mvp.view.mine.ISoftwareRelatedView;
import com.akan.qf.util.SpSingleInstance;
import com.akan.qf.util.ToastUtil;
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

/**
 * Created by admin on 2018/11/30.
 */

public class SaftwareFragment extends BaseFragment<ISoftwareRelatedView, SoftwareRelatedPresenter> implements ISoftwareRelatedView {
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
        if (!TextUtils.isEmpty(versionName)) {
            if (versionName.equals(data.getVersion_no())) {
                ToastUtil.showToast(context.getApplicationContext(), "已是最新版本！");
            } else {
                showDialogUpdate(data.getVersion_no(), data.getUpdate_content());//弹出提示版本更新的对话框
            }
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
                getPresenter().getAppVersionDetail(userBean.getStaff_token(), map);
                break;
        }
    }

    /**
     * 提示版本更新的对话框
     */
    private void showDialogUpdate(String versionName, String content) {
        // 这里的属性可以一直设置，因为每次设置后返回的是一个builder对象
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // 设置提示框的标题
        builder.setTitle("发现新版本V" + versionName + "！").
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
                            if (ContextCompat.checkSelfPermission(getActivity(),
                                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                    != PackageManager.PERMISSION_GRANTED) {
                                //申请WRITE_EXTERNAL_STORAGE权限
                                ActivityCompat.requestPermissions(getActivity(),
                                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
                            } else {
                                loadNewVersionProgress();//下载最新的版本程序
                            }

                        }
                    }
                }).

                // 设置取消按钮,null是什么都不做，并关闭对话框
                        setNegativeButton("取消", null);

        // 生产对话框
        AlertDialog alertDialog = builder.create();
        // 显示对话框
        alertDialog.show();


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted

                loadNewVersionProgress();//下载最新的版本程序
            } else {
                // Permission Denied
                ToastUtil.showToast(context.getApplicationContext(), "没有存储权限,下载失败");
            }
        }
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
                    pd.dismiss(); //结束掉进度条对话框
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


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public SoftwareRelatedPresenter createPresenter() {
        return new SoftwareRelatedPresenter(getApp());
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
