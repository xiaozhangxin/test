package com.ak.pt.mvp.fragment.water;

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
import com.ak.pt.bean.BookNameBean;
import com.ak.pt.bean.FirstEventBook;
import com.ak.pt.bean.FixFileBean;
import com.ak.pt.bean.FixRecordBean;
import com.ak.pt.bean.TwoChooseBean;
import com.ak.pt.bean.UserBean;
import com.ak.pt.mvp.adapter.water.ImageFixAdapter;
import com.ak.pt.mvp.base.BaseFragment;
import com.ak.pt.mvp.presenter.water.FixRecordPresenter;
import com.ak.pt.mvp.view.water.IFixRecordView;
import com.ak.pt.util.DialogUtil;
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
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.lljjcoder.citypickerview.widget.CityPicker;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
 * Created by admin on 2019/5/22.
 */

public class FixRecordAddFragment extends BaseFragment<IFixRecordView, FixRecordPresenter> implements IFixRecordView {


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
    @BindView(R.id.et_one)
    EditText etOne;
    @BindView(R.id.tv_one)
    EditText tvOne;
    @BindView(R.id.tv_two)
    TextView tvTwo;
    @BindView(R.id.tv_three)
    EditText tvThree;
    @BindView(R.id.tv_four)
    TextView tvFour;
    @BindView(R.id.tv_five)
    EditText tvFive;
    @BindView(R.id.tv_six)
    EditText tvSix;
    @BindView(R.id.tvQOne)
    TextView tvQOne;
    @BindView(R.id.tvQTwo)
    EditText tvQTwo;
    @BindView(R.id.tvQThree)
    EditText tvQThree;
    @BindView(R.id.tvQFour)
    EditText tvQFour;
    @BindView(R.id.tvWOne)
    TextView tvWOne;
    @BindView(R.id.tvWTwo)
    EditText tvWTwo;
    @BindView(R.id.tvWThree)
    TextView tvWThree;
    @BindView(R.id.tvWFour)
    EditText tvWFour;
    @BindView(R.id.tvWFive)
    EditText tvWFive;
    @BindView(R.id.tvWSix)
    TextView tvWSix;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.ok)
    Button ok;
    @BindView(R.id.tvDepartment)
    TextView tvDepartment;
    private DialogLoading dialogLoading;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;

    private List<FixFileBean> list;
    private ImageFixAdapter adapter;
    private static final int IMAGE = 0X01;
    private String type;
    private FixRecordBean data;
    private ArrayList<FixFileBean> fileList;
    private AppPermissionsBean permissionsBean;


    public static FixRecordAddFragment newInstance(FixRecordBean bean, String type, AppPermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        FixRecordAddFragment fragment = new FixRecordAddFragment();
        fragment.permissionsBean = permissionsBean;
        fragment.data = bean;
        fragment.type = type;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_add_fix;
    }

    @Override
    public void initUI() {
        list = new ArrayList<>();
        if ("0".equals(type)) {
            ok.setText("提交");
            tvTitle.setText("维修记录表");
        } else {
            tvTitle.setText("修改维修记录表");
            ok.setText("确认修改");
            tvOne.setText(data.getCustomer_name());
            tvTwo.setText(data.getCustomer_sex());
            tvThree.setText(data.getCustomer_tel());
            tvFour.setText(data.getCustomer_address());
            tvFive.setText(data.getAddress_info());
            //  tvSix.setText(data.getAddress_code());
            tvDepartment.setText(data.getDepartment_name());
            group_id = data.getGroup_id();
            tvQOne.setText(data.getRepair_time());
            tvQTwo.setText(data.getInstall_name());
            tvQThree.setText(data.getInstall_tel());
            tvQFour.setText(data.getService_shop());
            tvWOne.setText(data.getProduct_fault() + data.getProduct_model());
            series = data.getProduct_fault();
            model = data.getProduct_model();
            tvWTwo.setText(data.getProduct_no());
            tvWThree.setText(data.getFault_model());
            tvWFour.setText(data.getFault_no());
            tvWFive.setText(data.getRepair_plan());
            tvWSix.setText(data.getChange_name());
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
                            getPresenter().deleteRepairFile(userBean.getStaff_token(), map);
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
        if ("0".equals(type)) {
            tvDepartment.setText(userBean.getDepartment_name());
            group_id = userBean.getDepartment_id();
        }
    }


    @OnClick({R.id.ivLeft, R.id.tv_two, R.id.tv_four, R.id.tvQOne, R.id.tvWThree, R.id.tvWSix, R.id.tvWOne, R.id.ok, R.id.tvDepartment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvDepartment:
                startDepartmentPermissionFragment(permissionsBean.getMenu_id(), "water");
                break;
            case R.id.tv_two:
                showSingleAlertDialog(tvTwo, "选择性别", new String[]{"先生", "女士"});
                break;
            case R.id.tvWOne:
                startTwoChooseFragment();
                break;
            case R.id.tv_four:
                hideInputMethod(tvOne);
                cityPicker("上海市", "上海市", "浦东新区");
                break;
            case R.id.tvQOne:
                chooseTimeTwo("安装日期");
                break;
            case R.id.tvWThree:
                chooseMore("故障类型", new String[]{"不工作、不出水", "出水量小", "电脑板故障，电脑板报警", "漏水", "工作程序混乱", "其他"});

                break;
            case R.id.tvWSix:
                chooseMore("更换配件名称", new String[]{"高压开关", "低压开关", "减压阀", "滤瓶",
                        "进水电磁阀", "增压泵", "组合电磁阀", "单向阀", "水箱", "加热器", "放水电磁阀",
                        "磁力泵", "电脑板", "水位开关", "温度传感器", "电源", "控制器", "接头"});
                break;
            case R.id.ok:
                String mtvOne = tvOne.getText().toString().trim();
                if (TextUtils.isEmpty(mtvOne)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入用户姓名");
                    return;
                }
                String mtvTwo = tvTwo.getText().toString();
                if (TextUtils.isEmpty(mtvTwo)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择用户性别");
                    return;
                }
                String mtvThree = tvThree.getText().toString();
                if (TextUtils.isEmpty(mtvThree)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入用户电话");
                    return;
                }
                String mtvFour = tvFour.getText().toString();
                if (TextUtils.isEmpty(mtvFour)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择地区");
                    return;
                }
                String mtvFive = tvFive.getText().toString();
                if (TextUtils.isEmpty(mtvFive)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入详细地址");
                    return;
                }
//                String mtvSix = tvSix.getText().toString();
//                if (TextUtils.isEmpty(mtvSix)) {
//                    ToastUtil.showToast(context.getApplicationContext(), "请输入邮编");
//                    return;
//                }

                String mtvQTwo = tvQTwo.getText().toString();
                if (TextUtils.isEmpty(mtvQTwo)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入安装技术姓名");
                    return;
                }
                String mtvQThree = tvQThree.getText().toString();
                if (TextUtils.isEmpty(mtvQThree)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入安装技术电话");
                    return;
                }
                String mtvQFour = tvQFour.getText().toString();
                if (TextUtils.isEmpty(mtvQFour)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入服务商名称");
                    return;
                }
                String mtvWOne = tvWOne.getText().toString();
                if (TextUtils.isEmpty(mtvWOne)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择故障产品型号");
                    return;
                }
                String mtvWTwo = tvWTwo.getText().toString();
                if (TextUtils.isEmpty(mtvWTwo)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入产品编号");
                    return;
                }
                String mtvWThree = tvWThree.getText().toString();
                if (TextUtils.isEmpty(mtvWThree)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择故障类型");
                    return;
                }
                String mtvWFour = tvWFour.getText().toString();
                if (TextUtils.isEmpty(mtvWFour)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入故障代码");
                    return;
                }
                String mtvWFive = tvWFive.getText().toString();
                if (TextUtils.isEmpty(mtvWFive)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入维修方案");
                    return;
                }
                String mtvWSix = tvWSix.getText().toString();
                if (TextUtils.isEmpty(mtvWSix)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择更换配件名称");
                    return;
                }

                ok.setEnabled(false);
                dialogLoading = new DialogLoading(context);
                dialogLoading.showDialog();
                map.put("customer_name", mtvOne);
                map.put("customer_sex", mtvTwo);
                map.put("customer_tel", mtvThree);
                map.put("customer_address", mtvFour);
                map.put("address_info", mtvFive);
                map.put("group_id", group_id);
                //  map.put("address_code", mtvSix);

                map.put("repair_time", tvQOne.getText().toString());
                map.put("install_name", mtvQTwo);
                map.put("install_tel", mtvQThree);
                map.put("service_shop", mtvQFour);

                map.put("product_fault", series);
                map.put("product_model", model);
                map.put("product_no", mtvWTwo);
                map.put("fault_model", mtvWThree);
                map.put("fault_no", mtvWFour);
                map.put("repair_plan", mtvWFive);
                map.put("change_name", mtvWSix);

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
                            getPresenter().insertRepairRecord(userBean.getStaff_token(), map);
                        }
                        break;
                    case "1"://编辑
                        map.put("operation", "1");
                        map.put("repair_id", data.getRepair_id());
                        for (FixFileBean s : allData) {
                            if (TextUtils.isEmpty(s.getFile_id()) && !TextUtils.isEmpty(s.getFile_url())) {
                                fileList.add(new FixFileBean(s.getFile_url()));
                            }
                        }
                        if (fileList.size() > 0) {
                            upImage(fileList);
                        } else {
                            getPresenter().updateRepairRecord(userBean.getStaff_token(), map);
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
                    case "安装日期":
                        tvQOne.setText(format);
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

    private String series = "";
    private String model = "";

    private void startTwoChooseFragment() {
        ArrayList<TwoChooseBean> list = new ArrayList<>();
        String json = "[{\"childList\":[{\"isCheck\":false,\"name\":\"CR50-D1\"},{\"isCheck\":false,\"name\":\"CR75-F1\"},{\"isCheck\":false,\"name\":\"CR400-K1\"},{\"isCheck\":false,\"name\":\"CR75-F2\"},{\"isCheck\":false,\"name\":\"CWRF-H11\"},{\"isCheck\":false,\"name\":\"CR-600V\"},{\"isCheck\":false,\"name\":\"CR50-D1\"}],\"isCheck\":true,\"name\":\"反渗透净水机系列\"},\n" +
                "{\"childList\":[{\"isCheck\":false,\"name\":\"CWUF-3100\"}],\"isCheck\":false,\"name\":\"超滤净水机\"},\n" +
                "{\"childList\":[{\"isCheck\":false,\"name\":\"CW-A1\"},{\"isCheck\":false,\"name\":\"CW-B1\"},{\"isCheck\":false,\"name\":\"CW-A3\"},{\"isCheck\":false,\"name\":\"CW-A8\"}],\"isCheck\":false,\"name\":\"前置过滤器系列\"},\n" +
                "{\"childList\":[{\"isCheck\":false,\"name\":\"CWAF-C10\"},{\"isCheck\":false,\"name\":\"CWAF-C05\"},{\"isCheck\":false,\"name\":\"CWAF-C06\"},{\"isCheck\":false,\"name\":\"CWAF-C15\"},{\"isCheck\":false,\"name\":\"CWAF-C20\"},{\"isCheck\":false,\"name\":\"CWAF-C25\"}],\"isCheck\":false,\"name\":\"中央净水机系列\"},\n" +
                "{\"childList\":[{\"isCheck\":false,\"name\":\"CWIE-R10\"},{\"isCheck\":false,\"name\":\"CWIE-R05\"},{\"isCheck\":false,\"name\":\"CWIE-R15\"},{\"isCheck\":false,\"name\":\"CWIE-R20\"},{\"isCheck\":false,\"name\":\"CWIE-R25\"}],\"isCheck\":false,\"name\":\"中央软水机系列\"},\n" +
                "{\"childList\":[{\"isCheck\":false,\"name\":\"CWHF-J11金色\"},{\"isCheck\":false,\"name\":\"CWHF-J11黑色\"},{\"isCheck\":false,\"name\":\"CWHF-J21\"}],\"isCheck\":false,\"name\":\"管线饮水机系列\"}]";

        if (!json.equals("") && json.length() > 0) {
            Gson gson = new Gson();
            JsonArray array = new JsonParser().parse(json).getAsJsonArray();
            for (JsonElement elem : array) {
                TwoChooseBean usern = gson.fromJson(elem, TwoChooseBean.class);
                list.add(usern);
            }
        }
        TwoChooseFragment fragment = TwoChooseFragment.newInstance(list);
        fragment.setOnOkClickListener(new TwoChooseFragment.OnOkClickListener() {
            @Override
            public void ok(String s1, String s2) {
                series = s1;
                model = s2;
                tvWOne.setText(s1 + s2);

            }
        });
        fragment.show(getFragmentManager(), FixRecordAddFragment.class.getSimpleName());
    }


    @Override
    public void onInsertRepairRecord(String data) {
        if (dialogLoading != null) {
            dialogLoading.closeDialog();
        }
        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();
    }

    @Override
    public void onDeleteRepairRecord(String data) {

    }

    @Override
    public void onDeleteRepairFile(String data) {
    }

    @Override
    public void onUpdateRepairRecord(String data) {
        if (dialogLoading != null) {
            dialogLoading.closeDialog();
        }
        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();
    }

    @Override
    public void OnGetRepairRecordList(List<FixRecordBean> data) {

    }

    @Override
    public void onUploadFiles(String[] data) {
        for (int i = 0; i < data.length; i++) {
            fileList.get(i).setFile_url(data[i]);
        }
        String toJson = new Gson().toJson(fileList);
        if ("0".equals(type)) {
            map.put("repairList", toJson);
            getPresenter().insertRepairRecord(userBean.getStaff_token(), map);
        } else {
            map.put("repairList", toJson);
            getPresenter().updateRepairRecord(userBean.getStaff_token(), map);
        }

    }

    @Override
    public void onGetRepairRecordDetail(FixRecordBean data) {

    }


    //单选
    private void showSingleAlertDialog(TextView textView, String title, String[] items) {
        DialogUtil.showSingleChoiceDialog(textView, title, items);
    }

    //多选
    public void chooseMore(final String tittle, final String[] items) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(tittle);
        final boolean[] checkedItems = new boolean[]{false, false, false, false, false, false, false,
                false, false, false, false, false, false, false, false, false, false, false, false, false};
        builder.setMultiChoiceItems(items, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which,
                                boolean isChecked) {
                checkedItems[which] = isChecked;
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                String s = "";
                for (int i = 0; i < items.length; i++) {
                    if (checkedItems[i]) {
                        if (TextUtils.isEmpty(s)) {
                            s = items[i];
                        } else {
                            s = s + "," + items[i];
                        }
                    }
                }
                switch (tittle) {
                    case "更换配件名称":
                        tvWSix.setText(s);
                        break;
                    case "故障类型":
                        tvWThree.setText(s);
                        break;
                }
                dialog.dismiss();

            }
        });
        builder.show();
    }

    /**
     * 显示城市列表
     */
    private void cityPicker(String province, String city, String district) {
        CityPicker cityPicker = new CityPicker.Builder(getActivity())
                .textSize(16)
                .title("地址选择")
                .backgroundPop(0xa0000000)
                .titleBackgroundColor("#fafafa")
                .titleTextColor("#666666")
                .backgroundPop(0xa0000000)
                .confirTextColor("#33aaff")
                .cancelTextColor("#999999")
                .province(province)
                .city(city)
                .district(district)
                .textColor(Color.parseColor("#666666"))
                .provinceCyclic(true)
                .cityCyclic(false)
                .districtCyclic(false)
                .visibleItemsCount(8)
                .itemPadding(10)
                .onlyShowProvinceAndCity(false)
                .build();
        cityPicker.show();

        //监听方法，获取选择结果
        cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... citySelected) {
                //省份
                String province = citySelected[0];
                //城市
                String city = citySelected[1];
                //区县（如果设定了两级联动，那么该项返回空）
                String district = citySelected[2];
                //邮编
                String code = citySelected[3];
                tvFour.setText(province + "-" + city + "-" + district);
                //  tvSix.setText(code);
            }

            @Override
            public void onCancel() {
            }

        });
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
                    builder.addFormDataPart("path", "/images/fixRecord");
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
    public FixRecordPresenter createPresenter() {
        return new FixRecordPresenter(getApp());
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
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }

    private String group_id = "";

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(FirstEventBook event) {
        switch (event.getType()) {
            case "water":
                BookNameBean bean = event.getBookNameBean();
                tvDepartment.setText(bean.getName());
                group_id = bean.getNames();
                break;
        }

    }


}
