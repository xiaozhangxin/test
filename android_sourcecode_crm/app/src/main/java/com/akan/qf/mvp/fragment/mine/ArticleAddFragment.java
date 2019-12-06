package com.akan.qf.mvp.fragment.mine;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.akan.qf.R;
import com.akan.qf.bean.ContributeBean;
import com.akan.qf.bean.PermissionsBean;
import com.akan.qf.bean.UserBean;
import com.akan.qf.mvp.adapter.ImageAdapter;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.presenter.mine.ArticlePresenter;
import com.akan.qf.mvp.view.mine.IArticleView;
import com.akan.qf.util.SpSingleInstance;
import com.akan.qf.util.ToastUtil;
import com.akan.qf.view.DialogLoadding;
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
 * Created by admin on 2018/11/26.
 */

public class ArticleAddFragment extends BaseFragment<IArticleView, ArticlePresenter> implements IArticleView {


    Unbinder unbinder;
    @BindView(R.id.tvNum)
    TextView tvNum;
    @BindView(R.id.tvOk)
    TextView tvOk;
    @BindView(R.id.tvTittle)
    EditText tvTittle;
    @BindView(R.id.tvTag)
    EditText tvTag;
    @BindView(R.id.tvContent)
    EditText tvContent;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.view)
    TextView view;

    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private List<String> oldimgList = new ArrayList<>();

    private List<String> list;
    private ImageAdapter adapter;
    private static final int IMAGE = 0X02;
    private String class_ids;
    private String class_id;
    private ContributeBean articleBean;
    private String type;
    private List<String> strings;
    private PermissionsBean permissionsBean;
    private DialogLoadding dialogLoadding;
    public static ArticleAddFragment newInstance(String type, String class_ids, String class_id,
                                                 ContributeBean articleBean,PermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        ArticleAddFragment fragment = new ArticleAddFragment();
        fragment.type = type;
        fragment.class_id = class_id;
        fragment.class_ids = class_ids;
        fragment.articleBean = articleBean;
        fragment.permissionsBean=permissionsBean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_add_article;
    }

    @Override
    public void initUI() {
        if ("0".equals(type)) {
            recycleView.setLayoutManager(new GridLayoutManager(context, 3));
            view.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            list = new ArrayList<>();
            list.add("");
            adapter = new ImageAdapter(context, list);
            recycleView.setNestedScrollingEnabled(false);
            recycleView.setAdapter(adapter);
            adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    if (adapter.getAllData().size() <= 5) {
                        show_img(IMAGE);
                    } else {
                        ToastUtil.showToast(context.getApplicationContext(), "最多5张");
                    }
                }
            });
            adapter.setOnDeleteClick(new ImageAdapter.OnDeleteClick() {
                @Override
                public void onDeleteClick(int data) {
                    adapter.remove(data);
                }
            });
            recycleView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return false;
                }
            });
            tvContent.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {

                    tvNum.setText(tvContent.getText().length() + "字");
                }
            });
        } else {
            tvTittle.setText(articleBean.getTitle());
            tvContent.setText(articleBean.getContent());
            tvTag.setText(articleBean.getTag());
            tvNum.setText(articleBean.getContent().length() + "字");
            String filesString = articleBean.getFiles();
            strings = new ArrayList<>();
            if (!TextUtils.isEmpty(filesString)) {
                if (filesString.contains(",")) {
                    String[] split = filesString.split(",");
                    for (int i = 0; i < split.length; i++) {
                        if (!TextUtils.isEmpty(split[i])) {
                            strings.add(split[i]);
                        }
                    }
                } else {
                    strings.add(filesString);
                }
            }
            strings.add("");
            recycleView.setLayoutManager(new GridLayoutManager(context, 3));
            adapter = new ImageAdapter(context, strings);
            recycleView.setNestedScrollingEnabled(false);
            recycleView.setAdapter(adapter);
            adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    if (adapter.getAllData().size() <= 5) {
                        show_img(IMAGE);
                    } else {
                        ToastUtil.showToast(context.getApplicationContext(), "最多5张");
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

    }


    @Override
    public void onUploadFiles(String[] data) {
        String files = "";
        for (int i = 0; i < data.length; i++) {
            if (i == 0) {
                files = data[i];
            } else {
                files = files + "," + data[i];
            }
        }
        if (oldimgList.size() > 0) {
            for (int j = 0; j < oldimgList.size(); j++) {
                files = files + "," + oldimgList.get(j);
            }
        }
        map.put("files", files);
        getPresenter().insertOrUpdateAreaContribute(userBean.getStaff_token(), map);


    }

    @Override
    public void OnInsertOrUpdateAreaContribute(String data) {
        if (dialogLoadding!=null){
            dialogLoadding.closeDialog();
        }
        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();

    }

    @Override
    public void OnGetAreaContributeDetail(ContributeBean data) {

    }

    @Override
    public void OnInsertContributeComment(String data) {

    }

    @Override
    public void OnDeleteAreaContribute(String data) {

    }

    @Override
    public void OnAauditAreaContribute(String data) {

    }

    @OnClick({R.id.tvNum, R.id.tvOk})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvNum:
                finish();
                break;
            case R.id.tvOk:
                String mtvTittle = tvTittle.getText().toString().trim();
                if (TextUtils.isEmpty(mtvTittle)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入标题");
                    return;
                }
                String mtvContent = tvContent.getText().toString();
                if (TextUtils.isEmpty(mtvContent)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入正文");
                    return;
                }
                tvOk.setEnabled(false);
                dialogLoadding = new DialogLoadding(context);
                dialogLoadding.showDialog();
                if ("0".equals(type)) {
                    map.clear();
                    map.put("staff_id", userBean.getStaff_id());
                    map.put("title", mtvTittle);
                    map.put("class_id", class_id);
                    map.put("class_ids", class_ids);
                    map.put("tag", tvTag.getText().toString());
                    map.put("content", mtvContent);
                    map.put("is_select", "0");
                    map.put("is_app", "1");
                    map.put("module_id", permissionsBean.getMenu_id());
                    map.put("operation", "0");
                    if (adapter.getAllData().size() > 1) {
                        upImageTwo();
                    } else {
                        getPresenter().insertOrUpdateAreaContribute(userBean.getStaff_token(), map);
                    }
                } else {
                    map.clear();
                    map.put("id", articleBean.getId());
                    map.put("staff_id", userBean.getStaff_id());
                    map.put("title", mtvTittle);
                    map.put("class_id", articleBean.getClass_id());
                    map.put("class_ids", articleBean.getClass_ids());
                    map.put("tag", tvTag.getText().toString());
                    map.put("content", mtvContent);
                    map.put("is_select", "0");
                    map.put("is_app", "1");
                    map.put("module_id", permissionsBean.getMenu_id());
                    map.put("operation", "1");

                    final List<String> allList = new ArrayList<>();
                    allList.addAll(adapter.getAllData());
                    allList.remove(allList.size() - 1);
                    final List<String> newimgList = new ArrayList<>();

                    for (int i = 0; i < allList.size(); i++) {
                        if (!allList.get(i).contains("images")) {
                            newimgList.add(allList.get(i));
                        } else {
                            oldimgList.add(allList.get(i));
                        }
                    }
                    if (newimgList.size() > 0) {
                        upImage(newimgList);
                    } else {
                        String files = "";
                        if (oldimgList.size() > 0) {
                            for (int j = 0; j < oldimgList.size(); j++) {
                                files = files + "," + oldimgList.get(j);
                            }
                        }
                        map.put("files", files);
                        getPresenter().insertOrUpdateAreaContribute(userBean.getStaff_token(), map);
                    }
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
        BoxingConfig singleCropImgConfig = new BoxingConfig(BoxingConfig.Mode.MULTI_IMG).withMaxCount(6 - adapter.getAllData().size()).withCropOption(new BoxingCropOption(destUri)).needCamera(R.drawable.ic_boxing_camera_white)
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
    private void upImage(final List<String> allList) {
        final List<File> files = new ArrayList<>();
        Luban.with(getActivity())
                .load(allList)  // 传人要压缩的图片列表
                .ignoreBy(100).setCompressListener(new OnCompressListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onSuccess(File file) {
                files.add(file);
                if (allList.size() == files.size()) {
                    MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
                    builder.addFormDataPart("path", "/images/article");
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

    //上传图片
    private void upImageTwo() {
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
                    builder.addFormDataPart("path", "/images/article");
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
        if (dialogLoadding!=null){
            dialogLoadding.closeDialog();
        }
        ToastUtil.showToast(context.getApplicationContext(), e.getMessage());
        tvOk.setEnabled(true);

    }

    @Override
    public void OnGetAreaContributeList(List<ContributeBean> data,String total) {

    }


    @Override
    public ArticlePresenter createPresenter() {
        return new ArticlePresenter(getApp());
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
