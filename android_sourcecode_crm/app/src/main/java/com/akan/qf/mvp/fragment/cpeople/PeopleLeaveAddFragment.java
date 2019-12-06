package com.akan.qf.mvp.fragment.cpeople;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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

import com.akan.qf.Constants;
import com.akan.qf.R;
import com.akan.qf.bean.FirstEventNew;
import com.akan.qf.bean.MeParentBean;
import com.akan.qf.bean.PeopleLeaveBean;
import com.akan.qf.bean.PermissionsBean;
import com.akan.qf.bean.UserBean;
import com.akan.qf.mvp.adapter.ImageAdapter;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.presenter.home.PeopleLeavePresenter;
import com.akan.qf.mvp.view.home.IPeopleLeaveView;
import com.akan.qf.util.SpSingleInstance;
import com.akan.qf.util.ToastUtil;
import com.akan.qf.view.DialogLoadding;
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
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONException;
import org.json.JSONObject;

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
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

/**
 * Created by admin on 2019/2/21.
 */

public class PeopleLeaveAddFragment extends BaseFragment<IPeopleLeaveView, PeopleLeavePresenter> implements IPeopleLeaveView {


    Unbinder unbinder;
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
    @BindView(R.id.tvDepartment)
    TextView tvDepartment;
    @BindView(R.id.tvOne)
    EditText tvOne;
    @BindView(R.id.tvTwo)
    EditText tvTwo;
    @BindView(R.id.tvThree)
    TextView tvThree;
    @BindView(R.id.tvFour)
    EditText tvFour;
    @BindView(R.id.tvFive)
    TextView tvFive;
    @BindView(R.id.tvSix)
    EditText tvSix;
    @BindView(R.id.tvImgTittle)
    TextView tvImgTittle;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.ok)
    TextView ok;
    @BindView(R.id.tvCheck)
    TextView tvCheck;
    @BindView(R.id.circleImageVIew)
    CircleImageView circleImageVIew;
    @BindView(R.id.ivCheckDelete)
    ImageView ivCheckDelete;
    @BindView(R.id.tvCheckPersonName)
    TextView tvCheckPersonName;
    @BindView(R.id.tvSeven)
    EditText tvSeven;
    @BindView(R.id.tvEight)
    EditText tvEight;
    private String mNext_staff_id = "";
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private DialogLoadding dialogLoadding;
    private List<String> list;
    private ImageAdapter adapter;
    private static final int IMAGE = 0X02;
    private String type;
    private PeopleLeaveBean data;
    private List<String> oldList;

    private PermissionsBean permissionsBean;
    public static PeopleLeaveAddFragment newInstance(PeopleLeaveBean bean, String type,PermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        PeopleLeaveAddFragment fragment = new PeopleLeaveAddFragment();
        fragment.data = bean;
        fragment.permissionsBean=permissionsBean;
        fragment.type = type;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_people_leave_add;
    }

    @Override
    public void initUI() {


        if ("0".equals(type)) {
            getData();
            ok.setText("提交");
            tvTitle.setText("离职登记");
            list = new ArrayList<>();
            list.add("");
            adapter = new ImageAdapter(context, list);
            recycleView.setNestedScrollingEnabled(false);
            recycleView.setLayoutManager(new GridLayoutManager(context, 3));
            recycleView.setAdapter(adapter);
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
            tvTitle.setText("修改离职登记");
            ok.setText("确认修改");


            tvName.setText(data.getStaff_name());
            tvTime.setText(data.getApply_create_time());
            tvDepartment.setText(data.getStaff_department());

            tvOne.setText(data.getApply_area());
            tvTwo.setText(data.getApply_job());
            tvThree.setText(data.getApply_entry_time());
            tvFour.setText(data.getApply_name());
            tvFive.setText(data.getApply_time());
            tvSix.setText(data.getApply_remark());
            tvSeven.setText(data.getStaff_account());
            tvEight.setText(data.getApply_form());

            list = new ArrayList<>();
            String images = data.getApply_file();
            if (!TextUtils.isEmpty(images)) {
                list.add(images);
            }
            list.add("");
            adapter = new ImageAdapter(context, list);
            recycleView.setNestedScrollingEnabled(false);
            recycleView.setLayoutManager(new GridLayoutManager(context, 3));
            recycleView.setAdapter(adapter);
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
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        tvTime.setText(str);
        tvName.setText(userBean.getStaff_name());
        tvDepartment.setText(userBean.getSimple_department_name());
        if ("0".equals(type)) {
            if (!TextUtils.isEmpty(userBean.getParent_id())) {
                tvOne.setText(userBean.getSimple_department_name());
                mNext_staff_id = userBean.getParent_id();
                Glide.with(context)
                        .load(Constants.BASE_URL + userBean.getParent_head_img())
                        .error(R.drawable.error_img)
                        .into(circleImageVIew);
                tvCheckPersonName.setText(userBean.getParent_staff_name());
            }
            map.clear();
            map.put("group_id", userBean.getDepartment_id());
            map.put("staff_id", userBean.getStaff_id());
            getPresenter().getCheckPerson(userBean.getStaff_token(), map);
        } else {
            tvOne.setText(userBean.getSimple_department_name());
            mNext_staff_id = data.getNext_staff_id();
            Glide.with(context)
                    .load(Constants.BASE_URL + data.getNext_audit_staff_head_img())
                    .error(R.drawable.error_img)
                    .into(circleImageVIew);
            tvCheckPersonName.setText(data.getNext_audit_staff_name());
        }
    }


    @Override
    public void onUploadFile(String data) {
        map.put("apply_file", data);
        getPresenter().insertOrUpdateQuiteApply(userBean.getStaff_token(), map);
    }

    @Override
    public void onGetCheckPerson(List<MeParentBean> data) {
        for (int i = 0; i < data.size(); i++) {
            String module = data.get(i).getStaff_module();
            if (module.contains("ResignationLetter")) {
                String staff_give = data.get(i).getStaff_give();
                if ("1".equals(staff_give)) {
                    MeParentBean checkBean = data.get(i);
                    mNext_staff_id = checkBean.getStaff_id();
                    Glide.with(context)
                            .load(Constants.BASE_URL + checkBean.getHead_img())
                            .error(R.drawable.error_img)
                            .into(circleImageVIew);
                    tvCheckPersonName.setText(checkBean.getStaff_name());
                }

            }
        }
    }

    @OnClick({R.id.tvThree, R.id.ivLeft, R.id.ok, R.id.tvFive, R.id.circleImageVIew, R.id.ivCheckDelete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.ivCheckDelete:
                ivCheckDelete.setVisibility(View.GONE);
                tvCheckPersonName.setText("请选择");
                Glide.with(context).load(Constants.BASE_URL)
                        .error(R.drawable.check_img)
                        .into(circleImageVIew);
                mNext_staff_id = "";
                break;
            case R.id.circleImageVIew:
                startChooseCheckPersonFragment("1");
                break;
            case R.id.tvThree:
                chooseTime("入职日期");
                break;
            case R.id.tvFive:
                chooseTime("离职日期");
                break;
            case R.id.ok:
                String mtvOne = tvOne.getText().toString().trim();
                if (TextUtils.isEmpty(mtvOne)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入区域");
                    return;
                }
                String mtvTwo = tvTwo.getText().toString().trim();
                if (TextUtils.isEmpty(mtvTwo)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入岗位");
                    return;
                }
                String mtvThree = tvThree.getText().toString().trim();
                if (TextUtils.isEmpty(mtvThree)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择入职日期");
                    return;
                }
                String mtvFour = tvFour.getText().toString().trim();
                if (TextUtils.isEmpty(mtvFour)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入姓名");
                    return;
                }
                String mtvFive = tvFive.getText().toString().trim();
                if (TextUtils.isEmpty(mtvFive)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择离职日期");
                    return;
                }
                String mtvSix = tvSix.getText().toString().trim();
                if (TextUtils.isEmpty(mtvSix)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入离职原因");
                    return;
                }
                String mtvSeven = tvSeven.getText().toString().trim();
                if (TextUtils.isEmpty(mtvSeven)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入登录账号");
                    return;
                }
                if (TextUtils.isEmpty(mNext_staff_id)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择审批人");
                    return;
                }
                ok.setEnabled(false);
                dialogLoadding = new DialogLoadding(context);
                dialogLoadding.showDialog();
                map.clear();
                map.put("staff_id", userBean.getStaff_id());
                map.put("apply_area", mtvOne);
                map.put("apply_job", mtvTwo);
                map.put("apply_entry_time", mtvThree);
                map.put("apply_name", mtvFour);
                map.put("apply_time", mtvFive);
                map.put("apply_remark", mtvSix);
                map.put("staff_account", mtvSeven);
                map.put("apply_form", tvEight.getText().toString());
                map.put("next_staff_id", mNext_staff_id);
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
                            getPresenter().insertOrUpdateQuiteApply(userBean.getStaff_token(), map);
                        }
                        break;
                    case "1"://编辑
                        map.put("operation", "1");
                        map.put("apply_id", data.getApply_id());
                        if (allData.size() <= 1) {
                            map.put("apply_file", "");
                            getPresenter().insertOrUpdateQuiteApply(userBean.getStaff_token(), map);
                        } else {
                            if (allData.get(0).contains("images")) {
                                map.put("apply_file", allData.get(0));
                                getPresenter().insertOrUpdateQuiteApply(userBean.getStaff_token(), map);
                            } else {
                                allData.remove(allData.size() - 1);
                                upImage(allData);
                            }
                        }
                        break;
                }
                break;
        }
    }

    private void chooseTime(final String type) {
        TimePickerView build = new TimePickerBuilder(getActivity(), new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String format = formatter.format(date);
                switch (type) {
                    case "入职日期":
                        tvThree.setText(format);
                        break;
                    case "离职日期":
                        tvFive.setText(format);
                        break;
                }
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false})
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
                //.setDate(selectedDate)// 如果不设置的话，默认是系统时间*/
                //.setRangDate(startDate,endDate)//起始终止年月日设定
                .setLabel("年", "月", "日", "时", "分", "秒")
                .isCenterLabel(false)
                .isDialog(true)//是否显示为对话框样式
                .build();
        build.show();

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
        BoxingConfig singleCropImgConfig = new BoxingConfig(BoxingConfig.Mode.MULTI_IMG)
                .withMaxCount(2 - adapter.getAllData().size()).withCropOption(new BoxingCropOption(destUri))
                .needCamera(R.drawable.ic_boxing_camera_white)
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
                    builder.addFormDataPart("path", "/images/peopleLeave");
                    for (int i = 0; i < files.size(); i++) {
                        builder.addFormDataPart("file", file.getName(), RequestBody.create(MediaType.parse("image/jpeg"), files.get(i)));
                    }
                    MultipartBody build = builder.build();
                    getPresenter().uploadFile(userBean.getStaff_token(), build);
                }
            }

            @Override
            public void onError(Throwable e) {

            }
        }).launch();
    }


    @Override
    public PeopleLeavePresenter createPresenter() {
        return new PeopleLeavePresenter(getApp());
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
        saveData();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    private void saveData() {
        PeopleLeaveBean bean = new PeopleLeaveBean();
        bean.setApply_area(tvOne.getText().toString());
        bean.setApply_job(tvTwo.getText().toString());
        bean.setApply_entry_time(tvThree.getText().toString());
        bean.setApply_time(tvFour.getText().toString());
        bean.setApply_name(tvFive.getText().toString());
        bean.setApply_remark(tvSix.getText().toString());
        bean.setStaff_account(tvSeven.getText().toString());
        bean.setApply_form(tvEight.getText().toString());
        String s = new Gson().toJson(bean);
        SharedPreferences sharedPre = context.getSharedPreferences("beanData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPre.edit();
        editor.putString("peopleLeave", s);
        editor.commit();
    }

    private void getData() {
        SharedPreferences sp = context.getSharedPreferences("beanData", MODE_PRIVATE);
        String s = sp.getString("peopleLeave", "");
        if (!TextUtils.isEmpty(s)) {
            try {
                JSONObject object = new JSONObject(s);
                tvOne.setText(object.getString("apply_area"));
                tvTwo.setText(object.getString("apply_job"));
                tvThree.setText(object.getString("apply_entry_time"));
                tvFour.setText(object.getString("apply_name"));
                tvFive.setText(object.getString("apply_time"));
                tvSix.setText(object.getString("apply_remark"));
                tvSeven.setText(object.getString("staff_account"));
                tvEight.setText(object.getString("apply_form"));

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(FirstEventNew event) {
        switch (event.getMsg()) {
            case "1":
                MeParentBean bean = event.getmBean();
                Glide.with(context)
                        .load(Constants.BASE_URL + bean.getHead_img())
                        .error(R.drawable.error_img)
                        .into(circleImageVIew);
                tvCheckPersonName.setText(bean.getStaff_name());
                ivCheckDelete.setVisibility(View.VISIBLE);
                mNext_staff_id = bean.getStaff_id();
                break;
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
        if (dialogLoadding != null) {
            dialogLoadding.closeDialog();
        }
        ToastUtil.showToast(context.getApplicationContext(), e.getMessage());
        ok.setEnabled(true);
    }


    @Override
    public void onInsertOrUpdateQuiteApply(String data) {
        if (dialogLoadding != null) {
            dialogLoadding.closeDialog();
        }
        ToastUtil.showToast(context.getApplicationContext(), data);
        tvOne.setText("");
        tvTwo.setText("");
        tvThree.setText("");
        tvFour.setText("");
        tvFive.setText("");
        tvSix.setText("");
        tvSeven.setText("");
        tvEight.setText("");
        finish();
    }

    @Override
    public void onGetQuiteApplyList(List<PeopleLeaveBean> data, String total) {

    }

    @Override
    public void onGetQuiteApplyDaily(PeopleLeaveBean data) {

    }

    @Override
    public void onAuditQuiteApply(String data) {

    }

    @Override
    public void onDeleteQuiteApply(String data) {

    }


}
