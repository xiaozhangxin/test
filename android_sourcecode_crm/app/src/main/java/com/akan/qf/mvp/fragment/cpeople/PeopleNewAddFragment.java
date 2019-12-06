package com.akan.qf.mvp.fragment.cpeople;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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
import com.akan.qf.bean.PeopleNewBean;
import com.akan.qf.bean.PermissionsBean;
import com.akan.qf.bean.UserBean;
import com.akan.qf.mvp.adapter.ImageAdapter;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.presenter.home.PeopleNewPresenter;
import com.akan.qf.mvp.view.home.IPeopleNewView;
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
 * Created by admin on 2019/2/20.
 */

public class PeopleNewAddFragment extends BaseFragment<IPeopleNewView, PeopleNewPresenter> implements IPeopleNewView {


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
    @BindView(R.id.textView26)
    TextView textView26;
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
    @BindView(R.id.tvSeven)
    TextView tvSeven;
    @BindView(R.id.tvEight)
    TextView tvEight;
    @BindView(R.id.tvNine)
    EditText tvNine;
    @BindView(R.id.tvTen)
    EditText tvTen;
    @BindView(R.id.tvEleven)
    EditText tvEleven;
    @BindView(R.id.tvTwelve)
    EditText tvTwelve;
    @BindView(R.id.tvThirteen)
    EditText tvThirteen;

    @BindView(R.id.tvFifteen)
    TextView tvFifteen;
    @BindView(R.id.tvSixteen)
    EditText tvSixteen;
    @BindView(R.id.tvSeventeen)
    EditText tvSeventeen;
    @BindView(R.id.tvEighteen)
    EditText tvEighteen;
    @BindView(R.id.ok)
    TextView ok;
    @BindView(R.id.tvImgTittle)
    TextView tvImgTittle;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.tvRemark)
    EditText tvRemark;
    @BindView(R.id.tvFourteen)
    EditText tvFourteen;
    @BindView(R.id.textView27)
    TextView textView27;
    @BindView(R.id.tvCheck)
    TextView tvCheck;
    @BindView(R.id.circleImageVIew)
    CircleImageView circleImageVIew;
    @BindView(R.id.ivCheckDelete)
    ImageView ivCheckDelete;
    @BindView(R.id.tvCheckPersonName)
    TextView tvCheckPersonName;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private DialogLoadding dialogLoadding;
    private List<String> list;
    private ImageAdapter adapter;
    private static final int IMAGE = 0X02;
    private String type;
    private PeopleNewBean data;
    private List<String> oldList;
    private String mNext_staff_id = "";
    private PermissionsBean permissionsBean;

    public static PeopleNewAddFragment newInstance(PeopleNewBean bean, String type, PermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        PeopleNewAddFragment fragment = new PeopleNewAddFragment();
        fragment.data = bean;
        fragment.type = type;
        fragment.permissionsBean = permissionsBean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_people_new_add;
    }

    @Override
    public void initUI() {


        if ("0".equals(type)) {
            getData();
            ok.setText("提交");
            tvTitle.setText("新人入职");
            list = new ArrayList<>();
            list.add("");
            adapter = new ImageAdapter(context, list);
            recycleView.setNestedScrollingEnabled(false);
            recycleView.setLayoutManager(new GridLayoutManager(context, 3));
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
            adapter.setOnDeleteClick(new ImageAdapter.OnDeleteClick() {
                @Override
                public void onDeleteClick(int data) {
                    adapter.remove(data);
                }
            });
        } else {
            tvTitle.setText("修改新人入职");
            ok.setText("确认修改");


            tvName.setText(data.getStaff_name());
            tvTime.setText(data.getApply_create_time());
            tvDepartment.setText(data.getStaff_department());
            tvRemark.setText(data.getApply_remark());

            tvOne.setText(data.getApply_area());
            tvTwo.setText(data.getApply_job());
            tvThree.setText(data.getApply_time());
            tvFour.setText(data.getApply_name());
            tvFive.setText(data.getApply_sex());
            tvSix.setText(data.getApply_nation());
            tvSeven.setText(data.getApply_birthday());
            tvEight.setText(data.getApply_marriage());
            tvNine.setText(data.getApply_address());
            tvTen.setText(data.getApply_tel());
            tvEleven.setText(data.getApply_num());
            tvTwelve.setText(data.getApply_home());
            tvThirteen.setText(data.getApply_contact());
            tvFourteen.setText(data.getApply_contact_tel());
            tvFifteen.setText(data.getApply_education());
            tvSixteen.setText(data.getApply_graduate());
            tvSeventeen.setText(data.getApply_experience());
            tvEighteen.setText(data.getApply_number());
            list = new ArrayList<>();
            String images = data.getApply_images();
            if (!TextUtils.isEmpty(images)) {
                String[] split = images.split(",");
                for (int i = 0; i < split.length; i++) {
                    if (!TextUtils.isEmpty(split[i])) {
                        list.add(split[i]);
                    }
                }
            }
            list.add("");
            adapter = new ImageAdapter(context, list);
            recycleView.setNestedScrollingEnabled(false);
            recycleView.setLayoutManager(new GridLayoutManager(context, 3));
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
        }
        map.put("apply_images", stringBuilder.toString());
        getPresenter().insertOrUpdateEntryApply(userBean.getStaff_token(), map);
    }

    @Override
    public void onGetCheckPerson(List<MeParentBean> data) {
        for (int i = 0; i < data.size(); i++) {
            String module = data.get(i).getStaff_module();
            if (module.contains("NewcomerJoin")) {
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


    @OnClick({R.id.tvThree, R.id.ivLeft, R.id.tvSeven, R.id.ok, R.id.tvEight, R.id.tvFive, R.id.tvFifteen, R.id.circleImageVIew, R.id.ivCheckDelete})
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
            case R.id.tvSeven:
                chooseTime("出生日期");
                break;
            case R.id.tvFive:
                showSingleAlertDialog("选择性别", new String[]{"男", "女"});
                break;
            case R.id.tvEight:
                showSingleAlertDialog("选择婚姻状况", new String[]{"已婚", "未婚", "离异"});
                break;
            case R.id.tvFifteen:
                showSingleAlertDialog("选择学历", new String[]{"初中及以下学历", "高中", "专科", "本科", "研究生", "博士"});
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
                map.put("apply_time", mtvThree);
                map.put("apply_name", mtvFour);
                map.put("apply_sex", tvFive.getText().toString());
                map.put("apply_nation", tvSix.getText().toString());
                map.put("apply_birthday", tvSeven.getText().toString());
                map.put("apply_marriage", tvEight.getText().toString());
                map.put("apply_address", tvNine.getText().toString());
                map.put("apply_tel", tvTen.getText().toString());
                map.put("apply_num", tvEleven.getText().toString());
                map.put("apply_home", tvTwelve.getText().toString());
                map.put("apply_contact", tvThirteen.getText().toString());
                map.put("apply_contact_tel", tvFourteen.getText().toString());
                map.put("apply_education", tvFifteen.getText().toString());
                map.put("apply_graduate", tvSixteen.getText().toString());
                map.put("apply_experience", tvSeventeen.getText().toString());
                map.put("apply_number", tvEighteen.getText().toString());
                map.put("apply_remark", tvRemark.getText().toString());
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
                            getPresenter().insertOrUpdateEntryApply(userBean.getStaff_token(), map);
                        }
                        break;
                    case "1"://编辑
                        map.put("operation", "1");
                        map.put("apply_id", data.getApply_id());
                        if (allData.size() <= 1) {
                            map.put("apply_images", "");
                            getPresenter().insertOrUpdateEntryApply(userBean.getStaff_token(), map);
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
                                map.put("apply_images", stringBuilder.toString());
                                getPresenter().insertOrUpdateEntryApply(userBean.getStaff_token(), map);
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
                    case "出生日期":
                        tvSeven.setText(format);
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


    private AlertDialog alertDialog2;
    private int choose = 0;

    public void showSingleAlertDialog(final String tittle, final String[] items) {
        choose = 0;
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setTitle(tittle);
        alertBuilder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                choose = i;
            }
        });
        alertBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (tittle) {
                    case "选择性别":
                        tvFive.setText(items[choose]);
                        break;
                    case "选择婚姻状况":
                        tvEight.setText(items[choose]);
                        break;
                    case "选择学历":
                        tvFifteen.setText(items[choose]);
                        break;
                }
                alertDialog2.dismiss();
            }
        });

        alertBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                alertDialog2.dismiss();
            }
        });
        alertDialog2 = alertBuilder.create();
        alertDialog2.show();
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
                .withMaxCount(4 - adapter.getAllData().size()).withCropOption(new BoxingCropOption(destUri))
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
                    builder.addFormDataPart("path", "/images/peopleNew");
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
    public PeopleNewPresenter createPresenter() {
        return new PeopleNewPresenter(getApp());
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
        PeopleNewBean bean = new PeopleNewBean();


        bean.setApply_area(tvOne.getText().toString());
        bean.setApply_job(tvTwo.getText().toString());
        bean.setApply_time(tvThree.getText().toString());
        bean.setApply_name(tvFour.getText().toString());
        bean.setApply_sex(tvFive.getText().toString());
        bean.setApply_nation(tvSix.getText().toString());
        bean.setApply_birthday(tvSeven.getText().toString());
        bean.setApply_marriage(tvEight.getText().toString());
        bean.setApply_address(tvNine.getText().toString());
        bean.setApply_tel(tvTen.getText().toString());
        bean.setApply_num(tvEleven.getText().toString());
        bean.setApply_home(tvTwelve.getText().toString());
        bean.setApply_contact(tvThirteen.getText().toString());
        bean.setApply_contact_tel(tvFourteen.getText().toString());
        bean.setApply_education(tvFifteen.getText().toString());
        bean.setApply_graduate(tvSixteen.getText().toString());
        bean.setApply_experience(tvSeventeen.getText().toString());
        bean.setApply_number(tvEighteen.getText().toString());
        bean.setApply_remark(tvRemark.getText().toString());
        String s = new Gson().toJson(bean);
        SharedPreferences sharedPre = context.getSharedPreferences("beanData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPre.edit();
        editor.putString("peopleNew", s);
        editor.commit();
    }

    private void getData() {
        SharedPreferences sp = context.getSharedPreferences("beanData", MODE_PRIVATE);
        String s = sp.getString("peopleNew", "");
        if (!TextUtils.isEmpty(s)) {
            try {
                JSONObject object = new JSONObject(s);
                tvOne.setText(object.getString("apply_area"));
                tvTwo.setText(object.getString("apply_job"));
                tvThree.setText(object.getString("apply_time"));
                tvFour.setText(object.getString("apply_name"));
                tvFive.setText(object.getString("apply_sex"));
                tvSix.setText(object.getString("apply_nation"));
                tvSeven.setText(object.getString("apply_birthday"));
                tvEight.setText(object.getString("apply_marriage"));
                tvNine.setText(object.getString("apply_address"));
                tvTen.setText(object.getString("apply_tel"));
                tvEleven.setText(object.getString("apply_num"));
                tvTwelve.setText(object.getString("apply_home"));
                tvThirteen.setText(object.getString("apply_contact"));
                tvFourteen.setText(object.getString("apply_contact_tel"));
                tvFifteen.setText(object.getString("apply_education"));
                tvSixteen.setText(object.getString("apply_graduate"));
                tvSeventeen.setText(object.getString("apply_experience"));
                tvEighteen.setText(object.getString("apply_number"));
                tvRemark.setText(object.getString("apply_remark"));

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
        ToastUtil.showToast(context.getApplicationContext(), e.getMessage());
        ok.setEnabled(true);
    }


    @Override
    public void onInsertOrUpdateEntryApply(String data) {
        dialogLoadding.closeDialog();
        ToastUtil.showToast(context.getApplicationContext(), data);

        tvOne.setText("");
        tvTwo.setText("");
        tvThree.setText("");
        tvFour.setText("");
        tvFive.setText("");
        tvSix.setText("");
        tvSeven.setText("");
        tvEight.setText("");
        tvNine.setText("");
        tvTen.setText("");
        tvEleven.setText("");
        tvTwelve.setText("");
        tvThirteen.setText("");
        tvFourteen.setText("");
        tvFifteen.setText("");
        tvSixteen.setText("");
        tvSeventeen.setText("");
        tvEighteen.setText("");
        tvRemark.setText("");
        finish();
    }

    @Override
    public void onGetEntryApplyList(List<PeopleNewBean> data, String total) {

    }

    @Override
    public void onGetEntryApplyDaily(PeopleNewBean data) {

    }

    @Override
    public void onAuditEntryApply(String data) {

    }

    @Override
    public void onDeleteEntryApply(String data) {

    }
}