package com.ak.pt.mvp.fragment.area;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ak.pt.R;
import com.ak.pt.bean.AppPermissionsBean;
import com.ak.pt.bean.AreaStudyBean;
import com.ak.pt.bean.FixFileBean;
import com.ak.pt.bean.UserBean;
import com.ak.pt.mvp.adapter.water.ImageFixAdapter;
import com.ak.pt.mvp.base.BaseFragment;
import com.ak.pt.mvp.presenter.area.RegionPresenter;
import com.ak.pt.mvp.view.area.IRegionView;
import com.ak.pt.util.SpSingleInstance;
import com.ak.pt.util.ToastUtil;
import com.ak.pt.view.DialogLoading;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.bilibili.boxing.Boxing;
import com.bilibili.boxing.model.config.BoxingConfig;
import com.bilibili.boxing.model.config.BoxingCropOption;
import com.bilibili.boxing.model.entity.BaseMedia;
import com.bilibili.boxing.model.entity.impl.ImageMedia;
import com.bilibili.boxing.utils.BoxingFileHelper;
import com.bilibili.boxing_impl.ui.BoxingActivity;
import com.google.gson.Gson;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;


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
 * Created by admin on 2019/5/27.
 */

public class RegionAddFragment extends BaseFragment<IRegionView, RegionPresenter> implements IRegionView {


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
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvDepartment)
    TextView tvDepartment;
    @BindView(R.id.tvOne)
    TextView tvOne;
    @BindView(R.id.tvTwo)
    EditText tvTwo;
    @BindView(R.id.tvThree)
    EditText tvThree;
    @BindView(R.id.tvFour)
    EditText tvFour;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.ok)
    Button ok;

    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;

    private List<FixFileBean> list;
    private ImageFixAdapter adapter;
    private static final int IMAGE = 0X02;
    private String type;
    private AreaStudyBean data;
    private ArrayList<FixFileBean> fileList;
    private AppPermissionsBean permissionsBean;

    public static RegionAddFragment newInstance(AreaStudyBean bean, String type,AppPermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        RegionAddFragment fragment = new RegionAddFragment();
        fragment.permissionsBean=permissionsBean;
        fragment.data = bean;
        fragment.type = type;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_add_region;
    }

    @Override
    public void initUI() {
        list = new ArrayList<>();
        if ("0".equals(type)) {
            ok.setText("提交");
            tvTitle.setText("新增区域培训");

        } else {
            tvTitle.setText("修改区域培训");
            ok.setText("确认修改");
            tvName.setText(data.getStaff_name());
            tvTime.setText(data.getCreate_time());
            tvDepartment.setText(data.getDepartment_name());
            tvOne.setText(data.getStudy_time());
            tvTwo.setText(data.getAddress());
            tvThree.setText(data.getContent());
            tvFour.setText(data.getRemark());
            list.addAll(data.getFileList());
        }

        recycleView.setLayoutManager(new GridLayoutManager(context, 3));
        list.add(new FixFileBean(""));
        adapter = new ImageFixAdapter(context, list);
        recycleView.setNestedScrollingEnabled(false);
        recycleView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (adapter.getAllData().size() <= 3) {
                    show_img(IMAGE);
                } else {
                    ToastUtil.showToast(context.getApplicationContext(), "最多3张");
                }
            }
        });
        adapter.setOnDeleteClick(new ImageFixAdapter.OnDeleteClick() {
            @Override
            public void onDeleteClick(final int data) {
                final String file_id = adapter.getItem(data).getFile_id();
                if (TextUtils.isEmpty(file_id)) {
                    adapter.remove(data);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage(R.string.detete_file);
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            map.clear();
                            map.put("file_id", file_id);
                            getPresenter().deleteAreaStudyFile(userBean.getStaff_token(), map);
                            adapter.remove(data);
                        }
                    });
                    builder.setNegativeButton("取消", null);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }
        });
        recycleView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        switch (type) {
            case "0":
                tvName.setText(userBean.getStaff_name());
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
                Date curDate = new Date(System.currentTimeMillis());
                String str = formatter.format(curDate);
                tvTime.setText(str);
                tvDepartment.setText(userBean.getSimple_department_name());
                break;
        }
    }


    @Override
    public void onIinsertAreaStudy(String data) {
        if (dialogLoading != null) {
            dialogLoading.closeDialog();
        }
        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();
    }

    @Override
    public void onDdeleteAreaStudy(String data) {

    }

    @Override
    public void onDdeleteAreaStudyFile(String data) {

    }

    @Override
    public void onUupdateAreaStudy(String data) {
        if (dialogLoading != null) {
            dialogLoading.closeDialog();
        }
        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();
    }

    @Override
    public void OnGgetAreaStudyList(List<AreaStudyBean> data) {

    }

    @Override
    public void onGgetAreaStudyDetail(AreaStudyBean data) {

    }

    @Override
    public void onUploadFiles(String[] data) {

        for (int i = 0; i < data.length; i++) {
            fileList.get(i).setFile_url(data[i]);
        }
        String toJson = new Gson().toJson(fileList);
        if ("0".equals(type)) {
            map.put("areaList", toJson);
            getPresenter().insertAreaStudy(userBean.getStaff_token(), map);
        } else {
            map.put("areaList", toJson);
            getPresenter().updateAreaStudy(userBean.getStaff_token(), map);
        }

    }

    @Override
    public void onUauditAreaStudy(String data) {

    }


    @OnClick({R.id.ivLeft, R.id.ok, R.id.tvOne})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvOne:
                chooseTimeTwo("日期");
                break;
            case R.id.ok:
                String mtvOne = tvOne.getText().toString().trim();
                if (TextUtils.isEmpty(mtvOne)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择日期");
                    return;
                }
                ok.setEnabled(false);
                dialogLoading = new DialogLoading(context);
                dialogLoading.showDialog();
                map.put("staff_id", userBean.getStaff_id());
                map.put("study_time", mtvOne);
                map.put("address", tvTwo.getText().toString());
                map.put("content", tvThree.getText().toString());
                map.put("remark", tvFour.getText().toString());
                map.put("is_select", "0");
                map.put("is_app", "1");
                map.put("module_id", permissionsBean.getMenu_id());

                fileList = new ArrayList<>();
                fileList.clear();
                List<FixFileBean> allData = adapter.getAllData();
                switch (type) {
                    case "0"://新建
                        map.put("operation", "0");
                        for (FixFileBean s : allData) {
                            if (!TextUtils.isEmpty(s.getFile_url())) {
                                fileList.add(new FixFileBean(s.getFile_url()));
                            }
                        }
                        if (fileList.size() > 0) {
                            upImage(fileList);
                        } else {
                            getPresenter().insertAreaStudy(userBean.getStaff_token(), map);
                        }
                        break;
                    case "1"://编辑
                        map.put("operation", "1");
                        map.put("study_id", data.getStudy_id());
                        for (FixFileBean s : allData) {
                            if (TextUtils.isEmpty(s.getFile_id()) && !TextUtils.isEmpty(s.getFile_url())) {
                                fileList.add(new FixFileBean(s.getFile_url()));
                            }
                        }
                        if (fileList.size() > 0) {
                            upImage(fileList);
                        } else {
                            getPresenter().updateAreaStudy(userBean.getStaff_token(), map);
                        }
                }
                break;

        }
    }


    private void chooseTimeTwo(final String type) {
        TimePickerView build = new TimePickerBuilder(getActivity(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String format = formatter.format(date);
                switch (type) {
                    case "日期":
                        tvOne.setText(format);
                        break;
                }
            }
        }).setType(new boolean[]{true, true, true, false, false, false})
                .setCancelText("取消")
                .setSubmitText("确认")
                .setTitleSize(15)
                .setSubCalSize(14)
                .setTitleText(type)
                .setOutSideCancelable(false)
                .isCyclic(true)//是否循环滚动
                .setTitleColor(Color.WHITE)
                .setSubmitColor(Color.WHITE)
                .setCancelColor(Color.WHITE)
                .setTitleBgColor(0xFF4DA9EB)
                .setBgColor(0xFFFFFFFF)
                .setLabel("年", "月", "日", "时", "分", "秒")
                .isCenterLabel(false)
                .isDialog(true)//是否显示为对话框样式
                .build();
        build.show();

    }


    private DialogLoading dialogLoading;
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
        BoxingConfig singleCropImgConfig = new BoxingConfig(BoxingConfig.Mode.MULTI_IMG).withMaxCount(4 - adapter.getAllData().size()).withCropOption(new BoxingCropOption(destUri)).needCamera(R.drawable.ic_boxing_camera_white)
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
                            adapter.add(new FixFileBean(((ImageMedia) media).getThumbnailPath()));

                        } else {
                            adapter.add(new FixFileBean(media.getPath()));
                        }
                    }

                    if (adapter.getAllData().size() <= 3) {
                        adapter.add(new FixFileBean(""));
                    }
                }
                break;

        }
    }


    //上传图片
    private void upImage(final List<FixFileBean> imgList) {
        final List<File> files = new ArrayList<>();
        final List<String> sList = new ArrayList<>();
        for (FixFileBean bean : imgList) {
            sList.add(bean.getFile_url());
        }
        Luban.with(getActivity())
                .load(sList)  // 传人要压缩的图片列表
                .ignoreBy(100).setCompressListener(new OnCompressListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(File file) {
                files.add(file);
                if (imgList.size() == files.size()) {
                    MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                    builder.addFormDataPart("path", "/images/area");
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
        ok.setEnabled(true);
    }

    @Override
    public RegionPresenter createPresenter() {
        return new RegionPresenter(getApp());
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
