package com.ak.pt.mvp.fragment.adaily;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ak.pt.R;
import com.ak.pt.bean.AppPermissionsBean;
import com.ak.pt.bean.DailyBean;
import com.ak.pt.bean.UserBean;
import com.ak.pt.mvp.adapter.ImageAdapter;
import com.ak.pt.mvp.base.BaseFragment;
import com.ak.pt.mvp.presenter.DailyPresenter;
import com.ak.pt.mvp.view.IDailyView;
import com.ak.pt.util.SpSingleInstance;
import com.ak.pt.util.ToastUtil;
import com.ak.pt.view.DialogLoading;
import com.bilibili.boxing.Boxing;
import com.bilibili.boxing.model.config.BoxingConfig;
import com.bilibili.boxing.model.config.BoxingCropOption;
import com.bilibili.boxing.model.entity.BaseMedia;
import com.bilibili.boxing.model.entity.impl.ImageMedia;
import com.bilibili.boxing.utils.BoxingFileHelper;
import com.bilibili.boxing_impl.ui.BoxingActivity;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;


import java.io.File;
import java.util.ArrayList;
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
 * Created by admin on 2018/6/28.
 */

public class DailyAddFragment extends BaseFragment<IDailyView, DailyPresenter> implements IDailyView {


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
    @BindView(R.id.tvToday)
    EditText tvToday;
    @BindView(R.id.dtvTomorrow)
    EditText dtvTomorrow;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.ok)
    Button ok;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;

    private List<String> list;
    private ImageAdapter adapter;
    private static final int IMAGE = 0X02;
    private String type;
    private DailyBean data;
    private List<String> oldList;
    private AppPermissionsBean permissionsBean;

    public static DailyAddFragment newInstance(DailyBean bean, String type,AppPermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        DailyAddFragment fragment = new DailyAddFragment();
        fragment.data = bean;
        fragment.type = type;
        fragment.permissionsBean=permissionsBean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_add_daily;
    }

    @Override
    public void initUI() {
        list = new ArrayList<>();
        if ("0".equals(type)) {
            ok.setText(R.string.commit);
            tvTitle.setText(R.string.daily_add);
        } else {
            tvTitle.setText(R.string.daily_update);
            ok.setText(R.string.update_sure);
            tvToday.setText(data.getToday_work());
            dtvTomorrow.setText(data.getTomorrow_work());
            list.addAll(data.getDailyImageBeans());
        }

        recycleView.setLayoutManager(new GridLayoutManager(context, 3));
        list.add("");
        adapter = new ImageAdapter(context, list);
        recycleView.setNestedScrollingEnabled(false);
        recycleView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (adapter.getAllData().size() <= 3) {
                    show_img(IMAGE);
                } else {
                    ToastUtil.showToast(context.getApplicationContext(), getString(R.string.max_three_img));
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
    }


    @Override
    public void onUploadFiles(String[] mdata) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < mdata.length; i++) {
            if (i == 0) {
                stringBuilder.append(mdata[i]);
            } else {
                stringBuilder.append("," + mdata[i]);
            }
        }
        if ("1".equals(type)) {
            for (int i = 0; i < oldList.size(); i++) {
                stringBuilder.append("," + oldList.get(i));
            }

            map.put("images", stringBuilder.toString());
            getPresenter().updateDaily(userBean.getStaff_token(), map);
        } else {
            map.put("images", stringBuilder.toString());
            getPresenter().insertDaily(userBean.getStaff_token(), map);
        }

    }

    @Override
    public void onGetDailyList(List<DailyBean> data,String total) {

    }

    @Override
    public void onGetDailyDetail(DailyBean data) {

    }

    @Override
    public void auditDaily(String data) {

    }

    @Override
    public void onInsertDaily(String data) {
        if (dialogLoading != null) {
            dialogLoading.closeDialog();
        }
        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();
    }

    @Override
    public void onUpdateDaily(String data) {
        if (dialogLoading != null) {
            dialogLoading.closeDialog();
        }
        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();
    }


    @Override
    public void onDeleteDaily(String data) {

    }


    @OnClick({R.id.ivLeft, R.id.ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.ok:
                String today = tvToday.getText().toString().trim();
                if (TextUtils.isEmpty(today)) {
                    ToastUtil.showToast(context.getApplicationContext(), getString(R.string.please_input_today));
                    return;
                }
                String tomorrow = dtvTomorrow.getText().toString();
                if (TextUtils.isEmpty(tomorrow)) {
                    ToastUtil.showToast(context.getApplicationContext(), getString(R.string.please_input_tomorrow));
                    return;
                }
                ok.setEnabled(false);
                dialogLoading = new DialogLoading(context);
                dialogLoading.showDialog();
                map.put("staff_id", userBean.getStaff_id());
                map.put("today_work", today);
                map.put("tomorrow_work", tomorrow);
                map.put("daily_type", "0");
                map.put("is_select", "0");
                map.put("is_app", "1");
                map.put("module_id", permissionsBean.getMenu_id());

                List<String> allData = adapter.getAllData();
                switch (type) {

                    case "0"://新建
                        map.put("operation", "0");
                        if (allData.size() > 1) {
                            allData.remove(allData.size() - 1);
                            upImage(allData);
                        } else {
                            getPresenter().insertDaily(userBean.getStaff_token(), map);
                        }
                        break;
                    case "1"://编辑
                        map.put("operation", "1");
                        map.put("daily_id", data.getDaily_id());
                        if (allData.size() <= 1) {
                            map.put("images", "");
                            getPresenter().updateDaily(userBean.getStaff_token(), map);
                        } else {
                            final List<String> newList = new ArrayList<>();
                            oldList = new ArrayList<>();
                            for (int i = 0; i < allData.size(); i++) {
                                String img = allData.get(i);
                                if (!TextUtils.isEmpty(img)) {
                                    if (img.contains("images")) {
                                        oldList.add(img);
                                    } else {
                                        newList.add(img);
                                    }
                                }
                            }

                            if (newList.size() > 0) {
                                upImage(newList);
                            } else {
                                StringBuilder stringBuilder = new StringBuilder();
                                for (int i = 0; i < oldList.size(); i++) {
                                    if (i == 0) {
                                        stringBuilder.append(oldList.get(i));
                                    } else {
                                        stringBuilder.append("," + oldList.get(i));
                                    }
                                }
                                map.put("images", stringBuilder.toString());
                                getPresenter().updateDaily(userBean.getStaff_token(), map);
                            }
                        }
                }
                break;


        }
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
                    adapter.remove(adapter.getAllData().size() - 1);
                    final ArrayList<BaseMedia> medias = Boxing.getResult(data);
                    for (BaseMedia media : medias) {
                        if (media instanceof ImageMedia) {
                            adapter.add(((ImageMedia) media).getThumbnailPath());
                        } else {
                            adapter.add(media.getPath());
                        }
                    }
                    if (adapter.getAllData().size() <= 3) {
                        adapter.add("");
                    }
                    adapter.notifyDataSetChanged();
                }
                break;
        }
    }


    //上传图片
    private void upImage(final List imgList) {
        final List<File> files = new ArrayList<>();
        Luban.with(getActivity())
                .load(imgList)  // 传人要压缩的图片列表
                .ignoreBy(100).setCompressListener(new OnCompressListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(File file) {
                files.add(file);
                if (imgList.size() == files.size()) {
                    MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                    builder.addFormDataPart("path", "/images/daily");
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
    public DailyPresenter createPresenter() {
        return new DailyPresenter(getApp());
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
