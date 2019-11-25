package com.ak.pt.mvp.fragment.adaily;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ak.pt.Constants;
import com.ak.pt.R;
import com.ak.pt.bean.AppPermissionsBean;
import com.ak.pt.bean.SignBean;
import com.ak.pt.bean.UserBean;
import com.ak.pt.mvp.adapter.ImageAdapter;
import com.ak.pt.mvp.base.BaseFragment;
import com.ak.pt.mvp.presenter.SignPresenter;
import com.ak.pt.mvp.view.ISignView;
import com.ak.pt.util.GlideRoundTransform;
import com.ak.pt.util.SpSingleInstance;
import com.ak.pt.util.ToastUtil;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationListener;
import com.bilibili.boxing.Boxing;
import com.bilibili.boxing.model.config.BoxingConfig;
import com.bilibili.boxing.model.config.BoxingCropOption;
import com.bilibili.boxing.utils.BoxingFileHelper;
import com.bilibili.boxing_impl.ui.BoxingActivity;
import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;


import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import com.uniquext.android.widget.view.CornerImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

import static android.app.Activity.RESULT_OK;

/**
 * Created by admin on 2018/7/2.
 */

public class SignFragment extends BaseFragment<ISignView, SignPresenter> implements ISignView {


    Unbinder unbinder;
    public static final int TAKE_PHOTO = 101;
    public static final int REQUEST_CARERAM = 102;
    public static final int REQUEST_LOCATION = 103;
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
    @BindView(R.id.ivAvatar)
    CornerImageView ivAvatar;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvLine)
    View tvLine;
    @BindView(R.id.tvType)
    TextView tvType;
    @BindView(R.id.etContent)
    EditText etContent;
    @BindView(R.id.ivImg)
    ImageView ivImg;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.tvSign)
    TextView tvSign;


    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    //获取当前位置
    private AMapLocationClient mLocationClient = null;
    private String mAddress = "";
    //保存照片的目录
    Uri imageUri; //图片路径
    File imageFile; //图片文件
    String imagePath;

    private List<String> list;

    private ImageAdapter adapter;
    private static final int IMAGE = 0X02;
    private AppPermissionsBean permissionsBean;
    public static SignFragment newInstance(AppPermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        SignFragment fragment = new SignFragment();
        fragment.permissionsBean=permissionsBean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_sign;
    }

    @Override
    public void initUI() {
        tvTitle.setText(R.string.sign);
        list = new ArrayList<>();
        list.add("");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycleView.setLayoutManager(linearLayoutManager);
        adapter = new ImageAdapter(context, list);
        recycleView.setNestedScrollingEnabled(false);
        recycleView.setAdapter(adapter);
        adapter.setNoMore(R.layout.view_nomore);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (adapter.getAllData().size() <= 1) {
                    show_img(IMAGE);
                } else {
                    ToastUtil.showToast(context.getApplicationContext(), "最多1张");
                }
            }
        });
        adapter.setOnDeleteClick(new ImageAdapter.OnDeleteClick() {
            @Override
            public void onDeleteClick(int data) {
                adapter.remove(data);
            }
        });
    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        map.put("token_staff_id", userBean.getStaff_id());
        map.put("staff_token", userBean.getStaff_token());
        map.put("staff_id", userBean.getStaff_id());
        tvName.setText(userBean.getStaff_name());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        tvTime.setText(str);
        Glide.with(getContext())
                .load(Constants.BASE_URL + userBean.getHead_img())
                .error(R.drawable.error_img)
                .into(ivAvatar);
        if (Build.VERSION.SDK_INT >= 23) {
            showContacts();
        } else {
            initLocation();
        }

    }


    @Override
    public void onInsertSign(String data) {

        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();
    }

    @Override
    public void onUploadFile(String data) {
        map.put("sign_image", data);
        getPresenter().insertSign(userBean.getStaff_token(), map);
    }

    @Override
    public void onUploadFiles(String[] data) {
        map.put("sign_image", data[0]);
        getPresenter().insertSign(userBean.getStaff_token(), map);

    }

    @Override
    public void onGetSignList(List<SignBean> data) {

    }

    @Override
    public void onGetDaySignList(List<SignBean> data) {

    }


    //-----------------------------------------------上传报备图片------------------------------------------------------//
    protected void show_img(int requestcode) {
        String cachePath = BoxingFileHelper.getCacheDir(context);
        if (TextUtils.isEmpty(cachePath)) {
            ToastUtil.showToast(context.getApplicationContext(), R.string.boxing_storage_deny);
            return;
        }
        Uri destUri = new Uri.Builder()
                .scheme("file")
                .appendPath(cachePath)
                .appendPath(String.format(Locale.US, "%s.jpg", System.currentTimeMillis()))
                .build();
        BoxingConfig singleCropImgConfig = new BoxingConfig(BoxingConfig.Mode.MULTI_IMG).withMaxCount(2 - adapter.getAllData().size()).withCropOption(new BoxingCropOption(destUri)).needCamera(R.drawable.ic_boxing_camera_white)
                .withMediaPlaceHolderRes(R.drawable.ic_boxing_default_image);
        BoxingConfig singleCropImgConfigNew = new BoxingConfig(BoxingConfig.Mode.MULTI_IMG).needCamera(R.drawable.ic_boxing_camera_white);
        Boxing.of(singleCropImgConfigNew).withIntent(context, BoxingActivity.class).start(this, requestcode);
    }

/*    //从相册选择图片返回
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case IMAGE:
                if (resultCode == RESULT_OK) {
                    //adapter.clear();
                    adapter.remove(adapter.getAllData().size() - 1);
                    final ArrayList<BaseMedia> medias = Boxing.getResult(data);

                    for (BaseMedia media : medias) {
                        if (media instanceof ImageMedia) {
                            adapter.add(((ImageMedia) media).getThumbnailPath());

                        } else {
                            adapter.add(media.getPath());
                        }
                    }
                    if (adapter.getAllData().size() > 0) {

                    }
                    if (adapter.getAllData().size() <= 1) {
                        adapter.add("");
                    }
                    adapter.notifyDataSetChanged();
                }
                break;
        }
    }*/


    //上传图片

   /* private void upImage() {
        final List<File> files = new ArrayList<>();
        // adapter.remove(adapter.getAllData().size() - 1);
        final List<String> allData = adapter.getAllData();
        allData.remove(allData.size() - 1);
        Luban.with(getActivity())
                .load(allData)  // 传人要压缩的图片列表
                .ignoreBy(100).setCompressListener(new OnCompressListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(File file) {
                files.add(file);
                if (allData.size() == files.size()) {
                    MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                    builder.addFormDataPart("path", "/images/leave");
                    for (int i = 0; i < files.size(); i++) {
                        builder.addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("image/jpeg"), files.get(i)));
                    }
                    MultipartBody build = builder.build();
                    getPresenter().uploadFiles(userBean.getStaff_token(), build);
                }
            }

            @Override
            public void onError(Throwable e) {

            }
        }).launch();
    }*/


    //----------------------------------定位------------------------------------------------//

    //申请定位权限
    public void showContacts() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
        } else {
            initLocation();
        }
    }


    private void initLocation() {
        //初始化定位
        mLocationClient = new AMapLocationClient(context.getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //启动定位
        mLocationClient.startLocation();
    }

    //异步获取定位结果
    private AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation amapLocation) {
            if (amapLocation != null) {
                if (amapLocation.getErrorCode() == 0) {
                    String address = amapLocation.getAddress();
                    mAddress = address;
                }
                mLocationClient.stopLocation();
            }
        }
    };
    //----------------------------------拍照------------------------------------------------//

    public void takePhoto() {
        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        imageFile = new File(path, System.currentTimeMillis()+"sign.jpg");
        try {
            if (imageFile.exists()) {
                imageFile.delete();
            }
            imageFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //将File对象转换为Uri并启动照相程序
        //imageUri= FileProvider.getUriForFile(getActivity(), "com.ak.qf.fileProvider", imageFile);
        imageUri = Uri.fromFile(imageFile);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //照相
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri); //指定图片输出地址
        startActivityForResult(intent, TAKE_PHOTO); //启动照相
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == TAKE_PHOTO) {
                Glide.with(context).load(imageFile).bitmapTransform(new GlideRoundTransform(context,16)).into(ivImg);
            }
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
                takePhoto();
            }
        } else {
            takePhoto();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CARERAM:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    takePhoto();
                } else {
                    // Permission Denied
                    ToastUtil.showToast(context.getApplicationContext(), "请开启相机权限");
                }
                break;
            case REQUEST_LOCATION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    initLocation();
                } else {
                    ToastUtil.showToast(context.getApplicationContext(), "请开启定位权限");
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    //上传图片
    private void upImage() {
        Luban.with(getActivity())
                .load(imageFile)
                .ignoreBy(100)
                .setCompressListener(new OnCompressListener() {
                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onSuccess(File file) {
                        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                        builder.addFormDataPart("path", "/images/sign");
                        builder.addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("image/jpeg"), file));
                        MultipartBody build = builder.build();
                        getPresenter().uploadFile(userBean.getStaff_token(), build);
                    }

                    @Override
                    public void onError(Throwable e) {
                    } //设置回调

                }).launch();    //启动压缩

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public SignPresenter createPresenter() {
        return new SignPresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if (mLocationClient!=null){
        mLocationClient.onDestroy();}
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
        tvSign.setEnabled(true);

    }

    @OnClick({R.id.ivLeft, R.id.tvSign, R.id.ivImg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.ivImg:
                isHavePermission();
                break;
            case R.id.tvSign:
                if (TextUtils.isEmpty(mAddress)) {
                    ToastUtil.showToast(context.getApplicationContext(), "获取不到当前位置,请开启位置权限");
                    showContacts();
                    return;
                }
                String s = etContent.getText().toString();
                if (TextUtils.isEmpty(s)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入签到内容");
                    return;
                }
                tvSign.setEnabled(false);
                map.put("sign_content", s);
                map.put("sign_address", mAddress);
                map.put("is_select", "0");
                map.put("is_app", "1");
                map.put("module_id", permissionsBean.getMenu_id());
                map.put("operation", "0");
                if (imageFile != null) {
                    // if (adapter.getAllData().size() > 1) {
                    upImage();
                } else {
                    getPresenter().insertSign(userBean.getStaff_token(), map);
                }
                break;
        }
    }

}
