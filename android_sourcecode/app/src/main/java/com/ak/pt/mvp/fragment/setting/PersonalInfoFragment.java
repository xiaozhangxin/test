package com.ak.pt.mvp.fragment.setting;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ak.pt.Constants;
import com.ak.pt.R;
import com.ak.pt.bean.UserBean;
import com.ak.pt.mvp.base.BaseFragment;
import com.ak.pt.mvp.presenter.PersonalInfoPersenter;
import com.ak.pt.mvp.view.IPersonalInfoView;
import com.ak.pt.util.SpSingleInstance;
import com.ak.pt.util.ToastUtil;
import com.bilibili.boxing.Boxing;
import com.bilibili.boxing.BoxingMediaLoader;
import com.bilibili.boxing.model.config.BoxingConfig;
import com.bilibili.boxing.model.config.BoxingCropOption;
import com.bilibili.boxing.model.entity.BaseMedia;
import com.bilibili.boxing.model.entity.impl.ImageMedia;
import com.bilibili.boxing.utils.BoxingFileHelper;
import com.bilibili.boxing_impl.ui.BoxingActivity;
import com.bumptech.glide.Glide;


import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
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
import static com.king.base.BaseInterface.REQUEST_CODE;

/**
 * Created by admin on 2018/11/1.
 */

public class PersonalInfoFragment extends BaseFragment<IPersonalInfoView, PersonalInfoPersenter> implements IPersonalInfoView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.tvPersonInfo)
    TextView tvPersonInfo;
    @BindView(R.id.ivAvatar)
    CornerImageView ivAvatar;
    @BindView(R.id.tvNameTittle)
    TextView tvNameTittle;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvSexTittle)
    TextView tvSexTittle;
    @BindView(R.id.tvSex)
    TextView tvSex;
    @BindView(R.id.tvBornTittle)
    TextView tvBornTittle;
    @BindView(R.id.tvBorn)
    TextView tvBorn;
    @BindView(R.id.tvAddressTittle)
    TextView tvAddressTittle;
    @BindView(R.id.tvAddress)
    TextView tvAddress;
    @BindView(R.id.tvPhoneTittle)
    TextView tvPhoneTittle;
    @BindView(R.id.tvSuperior)
    TextView tvSuperior;
    @BindView(R.id.tvPhone)
    TextView tvPhone;
    @BindView(R.id.tvRoleName)
    TextView tvRoleName;
    Unbinder unbinder;
    private String path = "";
    private String newPath = "";
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private  ProgressDialog dialog;
    public static PersonalInfoFragment newInstance() {
        Bundle args = new Bundle();
        PersonalInfoFragment fragment = new PersonalInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_personal_info;
    }

    @Override
    public void initUI() {
        tvTitle.setText("个人资料");
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("保存");

    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        Glide.with(context)
                .load(Constants.BASE_URL + userBean.getHead_img())
                .error(R.drawable.default_logo)
                .into(ivAvatar);
        tvName.setText(userBean.getStaff_name());
        tvPhone.setText(userBean.getPhone());
        tvSex.setText(userBean.getSex());
        tvBorn.setText(userBean.getBirthday());
        tvAddress.setText(userBean.getContact_address());
        tvSuperior.setText(userBean.getParent_staff_name());
        tvRoleName.setText(userBean.getModule_role_names());


    }

    @Override
    public void onUploadFile(String data) {
        newPath = data;
        map.put("head_img", data);
        map.put("staff_id", userBean.getStaff_id());
        map.put("job_id", userBean.getJob_id());
        getPresenter().updateStaff(userBean.getStaff_token(), map);
    }

    @Override
    public void OnUpdateStaff(String data) {
        if (dialog != null) {
            dialog.dismiss();
        }
        ToastUtil.showToast(context.getApplicationContext(), data);
        if (!TextUtils.isEmpty(newPath)) {
            userBean.setHead_img(newPath);
            saveUser(userBean);
            path = "";
        }
    }



    @OnClick({R.id.tvPersonInfo, R.id.ivAvatar, R.id.ivLeft, R.id.tvPhoneCall, R.id.tvRight})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvRight:
                if (TextUtils.isEmpty(path)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请先更换头像");
                    return;
                }
                dialog = ProgressDialog.show(context, "", "头像上传中...");
                dialog.setCancelable(true);
                upImg();
                break;
            case R.id.tvPersonInfo:
            case R.id.ivAvatar:
                show_img();
                break;
        }
    }

    //-----------------------------------------------上传图片------------------------------------------------------//

    protected void show_img() {
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
        BoxingConfig singleCropImgConfig = new BoxingConfig(BoxingConfig.Mode.SINGLE_IMG).withCropOption(new BoxingCropOption(destUri)).needCamera(R.drawable.ic_boxing_camera_white)
                .withMediaPlaceHolderRes(R.drawable.ic_boxing_default_image);
        Boxing.of(singleCropImgConfig).withIntent(context, BoxingActivity.class).start(this, REQUEST_CODE);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    final ArrayList<BaseMedia> medias = Boxing.getResult(data);
                    if (medias.get(0) instanceof ImageMedia) {
                        path = ((ImageMedia) medias.get(0)).getThumbnailPath();
                    } else {
                        path = medias.get(0).getPath();
                    }
                    BoxingMediaLoader.getInstance().displayThumbnail(ivAvatar, path, 480, 480);
                    Glide.with(context).load(path).into(ivAvatar);

                }
                break;
        }
    }

    //上传图片
    private void upImg() {
        Luban.with(getActivity())
                .load(path)  // 传人要压缩的图片列表
                .ignoreBy(100).setCompressListener(new OnCompressListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(File file) {
                MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                builder.addFormDataPart("path", "/images/member");
                builder.addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("image/jpeg"), file));
                MultipartBody build = builder.build();
                getPresenter().uploadFile(userBean.getStaff_token(), build);
            }

            @Override
            public void onError(Throwable e) {

            }
        }).launch();
    }
    public static String md5(String string) {
        if (TextUtils.isEmpty(string)) {
            return "";
        }
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = md5.digest(string.getBytes());
            StringBuilder result = new StringBuilder();
            for (byte b : bytes) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result.append(temp);
            }
            return result.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";

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
    public PersonalInfoPersenter createPresenter() {
        return new PersonalInfoPersenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
