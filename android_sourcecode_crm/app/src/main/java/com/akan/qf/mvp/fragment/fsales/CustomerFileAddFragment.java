package com.akan.qf.mvp.fragment.fsales;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.qf.R;
import com.akan.qf.bean.ArchivesApplyBean;
import com.akan.qf.bean.UserBean;
import com.akan.qf.mvp.adapter.ImageAdapter;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.presenter.home.CustomerFilePresenter;
import com.akan.qf.mvp.view.home.ICustomerFileView;
import com.akan.qf.util.SpSingleInstance;
import com.akan.qf.util.ToastUtil;
import com.akan.qf.util.VerificationUtil;
import com.bilibili.boxing.Boxing;
import com.bilibili.boxing.model.config.BoxingConfig;
import com.bilibili.boxing.model.config.BoxingCropOption;
import com.bilibili.boxing.model.entity.BaseMedia;
import com.bilibili.boxing.model.entity.impl.ImageMedia;
import com.bilibili.boxing.utils.BoxingFileHelper;
import com.bilibili.boxing_impl.ui.BoxingActivity;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.king.base.util.ToastUtils;

import java.io.File;
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
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

import static android.app.Activity.RESULT_OK;

/**
 * Created by admin on 2018/11/27.
 */

public class CustomerFileAddFragment extends BaseFragment<ICustomerFileView, CustomerFilePresenter> implements ICustomerFileView {


    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvRemark)
    EditText tvRemark;
    @BindView(R.id.tvOne)
    EditText tvOne;
    @BindView(R.id.tvTwo)
    EditText tvTwo;
    @BindView(R.id.tvThree)
    EditText tvThree;
    @BindView(R.id.tvFour)
    EditText tvFour;
    @BindView(R.id.tvFive)
    EditText tvFive;
    @BindView(R.id.tvSix)
    EditText tvSix;

    @BindView(R.id.tvEight)
    EditText tvEight;
    @BindView(R.id.ok)
    TextView ok;
    Unbinder unbinder;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private String type;
    private ArchivesApplyBean data;

    private List<String> list;

    private ImageAdapter adapter;
    private static final int IMAGE = 0X02;

    public static CustomerFileAddFragment newInstance(ArchivesApplyBean bean, String type) {
        Bundle args = new Bundle();
        CustomerFileAddFragment fragment = new CustomerFileAddFragment();
        fragment.setArguments(args);
        fragment.data = bean;
        fragment.type = type;
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_file_add;
    }

    @Override
    public void initUI() {
        if ("0".equals(type)) {
            ok.setText("提交");
            tvTitle.setText("新增客户档案统计计");
            list = new ArrayList<>();
            list.add("");
            recyclerView.setLayoutManager(new GridLayoutManager(context, 3));
            adapter = new ImageAdapter(context, list);
            recyclerView.setNestedScrollingEnabled(false);
            recyclerView.setAdapter(adapter);
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

        } else {
            tvTitle.setText("修改客户档案统计");
            ok.setText("确认修改");
            tvRemark.setText(data.getApply_remark());
            tvOne.setText(data.getApply_name());
            tvTwo.setText(data.getCustomer_no());
            tvThree.setText(data.getApply_department());
            tvFour.setText(data.getApply_number());
            tvFive.setText(data.getApply_tel());
            tvSix.setText(data.getApply_address());
            // tvSeven.setText(data.getApply_license());
            tvEight.setText(data.getApply_time());

            list = new ArrayList<>();
            list.add(data.getApply_license());
            list.add("");
            recyclerView.setLayoutManager(new GridLayoutManager(context, 3));
            adapter = new ImageAdapter(context, list);
            recyclerView.setNestedScrollingEnabled(false);
            recyclerView.setAdapter(adapter);
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
    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        tvName.setText(userBean.getStaff_name());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        tvTime.setText(str);
        tvThree.setText(userBean.getSimple_department_name());
    }

    @OnClick({R.id.ivLeft, R.id.ok})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.ok:
                String mtvOne = tvOne.getText().toString().trim();
                if (TextUtils.isEmpty(mtvOne)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入客户姓名");
                    return;
                }
                String mPriceType = tvTwo.getText().toString().trim();
                if (TextUtils.isEmpty(mPriceType)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入客户抬头");
                    return;
                }
                String mPriceStyle = tvThree.getText().toString().trim();
                if (TextUtils.isEmpty(mPriceStyle)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入所属区域");
                    return;
                }
                String mBankNum = tvFour.getText().toString().trim();
                if (TextUtils.isEmpty(mBankNum)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入身份证号");
                    return;
                }
                if (!VerificationUtil.isIDNumber(mBankNum)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入正确的身份证号");
                    return;
                }

                String mtvFive = tvFive.getText().toString().trim();
                if (TextUtils.isEmpty(mtvFive)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入手机号");
                    return;
                }
                if (!VerificationUtil.isPhone(mtvFive)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入正确的手机号");
                    return;
                }
                String mtvSix = tvSix.getText().toString().trim();
                if (TextUtils.isEmpty(mtvSix)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入地址");
                    return;
                }
                if (adapter.getAllData().size() <= 1) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入营业执照");
                    return;
                }
                String mtvEight = tvEight.getText().toString().trim();
                if (TextUtils.isEmpty(mtvEight)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入合作时长");
                    return;
                }
                ok.setEnabled(false);
                map.clear();
                map.put("staff_id", userBean.getStaff_id());
                map.put("apply_remark", tvRemark.getText().toString());
                map.put("apply_name", mtvOne);
                map.put("customer_no", mPriceType);
                map.put("apply_department", mPriceStyle);
                map.put("apply_number", mBankNum);
                map.put("apply_tel", mtvFive);
                map.put("apply_address", mtvSix);
                // map.put("apply_license", mtvSeven);
                map.put("apply_time", mtvEight);
                if (adapter.getAllData().get(0).contains("images")) {
                    map.put("apply_license", data.getApply_license());
                    map.put("apply_id", data.getApply_id());
                    getPresenter().updateArchivesApply(userBean.getStaff_token(), map);
                } else {
                    upImage();
                }


                break;
        }
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
        BoxingConfig singleCropImgConfig = new BoxingConfig(BoxingConfig.Mode.MULTI_IMG).withMaxCount(1).withCropOption(new BoxingCropOption(destUri)).needCamera(R.drawable.ic_boxing_camera_white)
                .withMediaPlaceHolderRes(R.drawable.ic_boxing_default_image);

        Boxing.of(singleCropImgConfig).withIntent(context, BoxingActivity.class).start(this, requestcode);
    }

    //从相册选择图片返回
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
                    if (adapter.getAllData().size() <= 5) {
                        adapter.add("");
                    }
                    adapter.notifyDataSetChanged();
                }
                break;
        }
    }


    //上传图片
    private void upImage() {
        final List<File> files = new ArrayList<>();
        adapter.remove(adapter.getAllData().size() - 1);
        final List<String> allData = adapter.getAllData();
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
                    builder.addFormDataPart("path", "/images/customer");
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
    public CustomerFilePresenter createPresenter() {
        return new CustomerFilePresenter(getApp());
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
    public void OnInsertArchivesApply(String data) {
        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();
    }

    @Override
    public void OnGetArchivesApplyList(List<ArchivesApplyBean> data) {

    }

    @Override
    public void OngetArchivesApply(ArchivesApplyBean data) {

    }

    @Override
    public void OndeleteArchivesApply(String data) {

    }

    @Override
    public void OnupdateArchivesApply(String data) {
        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();

    }

    @Override
    public void onUploadFiles(String[] mdata) {
        map.put("apply_license", mdata[0]);
        if ("1".equals(type)) {
            map.put("apply_id", data.getApply_id());
            getPresenter().updateArchivesApply(userBean.getStaff_token(), map);
        } else {
            getPresenter().insertArchivesApply(userBean.getStaff_token(), map);
        }
    }
}

