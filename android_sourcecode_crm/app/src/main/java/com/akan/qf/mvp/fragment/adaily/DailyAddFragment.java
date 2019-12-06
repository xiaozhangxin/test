package com.akan.qf.mvp.fragment.adaily;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.qf.R;
import com.akan.qf.bean.LableBean;
import com.akan.qf.bean.PermissionsBean;
import com.akan.qf.bean.DailyBean;
import com.akan.qf.bean.UserBean;
import com.akan.qf.mvp.adapter.ImageAdapter;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.presenter.home.DailyPresenter;
import com.akan.qf.mvp.view.home.IDailyView;
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
import com.google.gson.Gson;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import org.json.JSONException;
import org.json.JSONObject;

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
import static android.content.Context.MODE_PRIVATE;

/**
 * Created by admin on 2018/6/28.
 */

public class DailyAddFragment extends BaseFragment<IDailyView, DailyPresenter> implements IDailyView {

    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.tvTodayTittle)
    TextView tvTodayTittle;
    @BindView(R.id.tvToday)
    EditText tvToday;
    @BindView(R.id.tvTomorrowTittle)
    TextView tvTomorrowTittle;
    @BindView(R.id.dtvTomorrow)
    EditText tvTomorrow;
    @BindView(R.id.lineOne)
    View lineOne;
    @BindView(R.id.tvImgTittle)
    TextView tvImgTittle;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.ok)
    Button ok;
    Unbinder unbinder;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;

    private List<String> list;
    private ImageAdapter adapter;
    private static final int IMAGE = 0X02;
    private String type;//0新增 1编辑
    private DailyBean data;
    private List<String> oldList;
    private DialogLoadding dialogLoadding;
    private PermissionsBean permissionsBean;

    public static DailyAddFragment newInstance(DailyBean bean, String type,PermissionsBean permissionsBean) {
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
            getData();
        } else {
            tvTitle.setText(R.string.daily_modify);
            ok.setText(R.string.modify_sure);
            tvToday.setText(data.getToday_work());
            tvTomorrow.setText(data.getTomorrow_work());
            list.addAll(data.getDailyImageBeans());
        }
        initImgList();
    }

    //初始化图片添加列表
    private void initImgList() {

        list.add("");
        recycleView.setLayoutManager(new GridLayoutManager(context, 3));
        recycleView.setNestedScrollingEnabled(false);
        adapter = new ImageAdapter(context, list);
        recycleView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (adapter.getAllData().size() > 10) {
                    ToastUtil.showToast(context.getApplicationContext(), getString(R.string.img_max_ten));
                    return;
                }
                show_img(IMAGE);
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
    public void onInsertDaily(String data) {
        if (dialogLoadding != null) {
            dialogLoadding.closeDialog();
        }
        ToastUtil.showToast(context.getApplicationContext(), data);
        tvToday.setText("");
        tvTomorrow.setText("");
        finish();
    }

    @Override
    public void onUpdateDaily(String data) {
        if (dialogLoadding != null) {
            dialogLoadding.closeDialog();
        }
        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();
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
    public void onDeleteDaily(String data) {

    }

    @Override
    public void OnGetStaffSignList(List<LableBean> data) {

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
                    ToastUtil.showToast(context.getApplicationContext(), getString(R.string.please_fill_today_summary));
                    return;
                }
                if (today.length() > 1000) {
                    ToastUtil.showToast(context.getApplicationContext(), getString(R.string.today_most_one_thousand));
                    return;
                }
                String tomorrow = tvTomorrow.getText().toString();
                if (TextUtils.isEmpty(tomorrow)) {
                    ToastUtil.showToast(context.getApplicationContext(), getString(R.string.please_fill_tomorrow_summary));
                    return;
                }
                if (tomorrow.length() > 1000) {
                    ToastUtil.showToast(context.getApplicationContext(), getString(R.string.tomorrow_most_one_thousand));
                    return;
                }
                ok.setEnabled(false);
                dialogLoadding = new DialogLoadding(context);
                dialogLoadding.showDialog();
                map.put("staff_id", userBean.getStaff_id());
                map.put("today_work", today);
                map.put("tomorrow_work", tomorrow);
                map.put("daily_type", "0");
                map.put("is_select", "0");
                map.put("is_app", "1");
                map.put("module_id", permissionsBean.getMenu_id());
                map.put("operation", "0");
                List<String> allData = adapter.getAllData();
                switch (type) {
                    case "0"://新建
                        if (allData.size() > 1) {
                            allData.remove(allData.size() - 1);
                            upImage(allData);
                        } else {
                            getPresenter().insertDaily(userBean.getStaff_token(), map);
                        }
                        break;
                    case "1"://编辑
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


    //-----------------------------------------------上传图片------------------------------------------------------//
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
        BoxingConfig singleCropImgConfig = new BoxingConfig(BoxingConfig.Mode.MULTI_IMG).withMaxCount(11 - adapter.getAllData().size()).withCropOption(new BoxingCropOption(destUri)).needCamera(R.drawable.ic_boxing_camera_white)
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

                    if (adapter.getAllData().size() <= 10) {
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
        ok.setEnabled(true);
        ToastUtil.showToast(context.getApplicationContext(), e.getMessage());

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
        saveData();
        unbinder.unbind();
    }


    private void saveData() {
        DailyBean dailyBean = new DailyBean();
        dailyBean.setToday_work(tvToday.getText().toString());
        dailyBean.setTomorrow_work(tvTomorrow.getText().toString());
        String s = new Gson().toJson(dailyBean);
        SharedPreferences sharedPre = context.getSharedPreferences("beanData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPre.edit();
        editor.putString("daily", s);
        editor.commit();
    }

    private void getData() {
        SharedPreferences sp = context.getSharedPreferences("beanData", MODE_PRIVATE);
        String s = sp.getString("daily", "");
        if (!TextUtils.isEmpty(s)) {
            try {
                JSONObject object = new JSONObject(s);
                tvToday.setText(object.getString("today_work"));
                tvTomorrow.setText(object.getString("tomorrow_work"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }


}
