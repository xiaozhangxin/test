package com.ak.pt.mvp.fragment.water;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import com.ak.pt.bean.FilterTypeBean;
import com.ak.pt.bean.FirstEventBook;
import com.ak.pt.bean.TwoChooseBean;
import com.ak.pt.bean.UserBean;
import com.ak.pt.bean.WarrantyBean;
import com.ak.pt.bean.WarrantyFileBean;
import com.ak.pt.mvp.adapter.water.ImageWarrantyAdapter;
import com.ak.pt.mvp.adapter.water.WarranyTableListAdapter;
import com.ak.pt.mvp.base.BaseFragment;
import com.ak.pt.mvp.presenter.water.WarrantyPresenter;
import com.ak.pt.mvp.view.water.IWarrantyView;
import com.ak.pt.util.CustomDialog;
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
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

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
import static com.ak.pt.mvp.fragment.adaily.SignFragment.REQUEST_CARERAM;

/**
 * Created by admin on 2019/5/23.
 */

public class WarrantyAddFragment extends BaseFragment<IWarrantyView, WarrantyPresenter> implements IWarrantyView {


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
    @BindView(R.id.recycleViewTable)
    RecyclerView recycleViewTable;
    @BindView(R.id.tvAdd)
    TextView tvAdd;
    @BindView(R.id.tvWSix)
    EditText tvWSix;
    @BindView(R.id.tvWSeven)
    EditText tvWSeven;
    @BindView(R.id.tvQOne)
    EditText tvQOne;
    @BindView(R.id.tvQTwo)
    EditText tvQTwo;
    @BindView(R.id.tvQThree)
    EditText tvQThree;
    @BindView(R.id.tvQFour)
    TextView tvQFour;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.recycleViewTwo)
    RecyclerView recycleViewTwo;
    @BindView(R.id.recycleViewThree)
    RecyclerView recycleViewThree;
    @BindView(R.id.ok)
    Button ok;
    @BindView(R.id.tvPhoneAdd)
    EditText tvPhoneAdd;
    @BindView(R.id.tvWaterNo)
    EditText tvWaterNo;
    @BindView(R.id.tvDepartment)
    TextView tvDepartment;


    private DialogLoading dialogLoading;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;

    private List<WarrantyFileBean> list;
    private ImageWarrantyAdapter adapter;
    private List<WarrantyFileBean> listTwo;
    private ImageWarrantyAdapter adapterTwo;
    private List<WarrantyFileBean> listThree;
    private ImageWarrantyAdapter adapterThree;
    private static final int IMAGE_ONE = 0X01;
    private static final int IMAGE_TWO = 0X02;
    private static final int IMAGE_THREE = 0X03;
    private static final int SCAN_ONE = 0X05;
    private static final int SCAN_TWO = 0X06;
    private static final int SCAN_THREE = 0X07;
    private static final int SCAN_FOUR = 0X08;
    private static final int SCAN_FIVE = 0X09;
    private String type;
    private WarrantyBean data;
    private ArrayList<WarrantyFileBean> fileList;

    private ArrayList<FilterTypeBean> tableList;
    private WarranyTableListAdapter tableAdapter;
    private int typePosition = 0;
    private int scanPosition = 0;

    private AppPermissionsBean permissionsBean;

    public static WarrantyAddFragment newInstance(WarrantyBean bean, String type, AppPermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        WarrantyAddFragment fragment = new WarrantyAddFragment();
        fragment.permissionsBean = permissionsBean;
        fragment.data = bean;
        fragment.type = type;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_add_warranty;
    }

    @Override
    public void initUI() {
        list = new ArrayList<>();
        listTwo = new ArrayList<>();
        listThree = new ArrayList<>();
        tableList = new ArrayList<>();
        if ("0".equals(type)) {
            ok.setText("提交");
            tvTitle.setText("电子保修卡");
            tableList.add(new FilterTypeBean());
        } else {
            tvTitle.setText("修改电子保修卡");
            ok.setText("确认修改");
            tvDepartment.setText(data.getDepartment_name());
            group_id = data.getGroup_id();
            tvOne.setText(data.getCustomer_name());
            tvTwo.setText(data.getCustomer_sex());
            tvThree.setText(data.getCustomer_tel());
            tvFour.setText(data.getCustomer_address());
            tvFive.setText(data.getAddress_info());
            // tvSix.setText(data.getAddress_code());

            tvPhoneAdd.setText(data.getService_tel());
            tvWaterNo.setText(data.getInstall_no());
            tvWSix.setText(data.getWater_pressure());
            tvWSeven.setText(data.getWater_quality());

            tvQOne.setText(data.getService_provider());
            tvQTwo.setText(data.getAddress_info());
            tvQThree.setText(data.getInstall_tel());
            tvQFour.setText(data.getInstall_time());

            list.addAll(data.getImgSuccessList());
            listTwo.addAll(data.getImgBillList());
            listThree.addAll(data.getImgOtherList());

            if (data.getProductList().size() > 0) {
                tableList.addAll(data.getProductList());
            } else {
                tableList.add(new FilterTypeBean());
            }

        }


        recycleViewTable.setLayoutManager(new LinearLayoutManager(context));
        tableAdapter = new WarranyTableListAdapter(context, tableList);
        recycleViewTable.setAdapter(tableAdapter);
        tableAdapter.setOnDeleteClick(new WarranyTableListAdapter.OnDeleteClick() {
            @Override
            public void onDeleteClick(final int data) {
                final CustomDialog.Builder builder = new CustomDialog.Builder(context);
                builder.setMessage("你确定要删除产品" + (data + 1) + "?");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        tableAdapter.remove(data);
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.onCreate().show();
            }

            @Override
            public void onChoosType(int data) {
                startTwoChooseFragment(data);
            }

            @Override
            public void onCScan(int data) {
                scanPosition = data;
                Intent intent = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intent, data);
            }
        });
        tableAdapter.setOnDeleteClick(new WarranyTableListAdapter.OnDeleteClick() {
            @Override
            public void onDeleteClick(final int data) {
                final CustomDialog.Builder builder = new CustomDialog.Builder(context);
                builder.setMessage("你确定要当前删除产品" + (data + 1) + "?");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        tableAdapter.remove(data);
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.onCreate().show();
            }

            @Override
            public void onChoosType(int data) {
                startTwoChooseFragment(data);
            }

            @Override
            public void onCScan(int data) {
                scanPosition = data;
                isHavePermission(data);


            }
        });


        recycleView.setLayoutManager(new GridLayoutManager(context, 3));
        list.add(new WarrantyFileBean("", "success"));
        adapter = new ImageWarrantyAdapter(context, list);
        recycleView.setNestedScrollingEnabled(false);
        recycleView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (adapter.getAllData().size() <= 5) {
                    show_img(IMAGE_ONE);
                } else {
                    ToastUtil.showToast(context.getApplicationContext(), "最多5张");
                }
            }
        });
        adapter.setOnDeleteClick(new ImageWarrantyAdapter.OnDeleteClick() {
            @Override
            public void onDeleteClick(final int data) {
                final String file_id = adapter.getItem(data).getImg_id();
                if (TextUtils.isEmpty(file_id)) {
                    adapter.remove(data);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage(R.string.detete_file);
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            map.clear();
                            map.put("img_ids", file_id);
                            getPresenter().deleteElectronicImg(userBean.getStaff_token(), map);
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


        recycleViewTwo.setLayoutManager(new GridLayoutManager(context, 3));
        listTwo.add(new WarrantyFileBean("", "bill"));
        adapterTwo = new ImageWarrantyAdapter(context, listTwo);
        recycleViewTwo.setNestedScrollingEnabled(false);
        recycleViewTwo.setAdapter(adapterTwo);
        adapterTwo.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (adapterTwo.getAllData().size() <= 5) {
                    show_img(IMAGE_TWO);
                } else {
                    ToastUtil.showToast(context.getApplicationContext(), "最多5张");
                }
            }
        });

        adapterTwo.setOnDeleteClick(new ImageWarrantyAdapter.OnDeleteClick() {
            @Override
            public void onDeleteClick(final int data) {
                final String file_id = adapterTwo.getItem(data).getImg_id();
                if (TextUtils.isEmpty(file_id)) {
                    adapterTwo.remove(data);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage(R.string.detete_file);
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            map.clear();
                            map.put("img_ids", file_id);
                            getPresenter().deleteElectronicImg(userBean.getStaff_token(), map);
                            adapterTwo.remove(data);
                        }
                    });
                    builder.setNegativeButton("取消", null);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }
        });
        recycleViewTwo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });


        recycleViewThree.setLayoutManager(new GridLayoutManager(context, 3));
        listThree.add(new WarrantyFileBean("", "other"));
        adapterThree = new ImageWarrantyAdapter(context, listThree);
        recycleViewThree.setNestedScrollingEnabled(false);
        recycleViewThree.setAdapter(adapterThree);
        adapterThree.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (adapterThree.getAllData().size() <= 5) {
                    show_img(IMAGE_THREE);
                } else {
                    ToastUtil.showToast(context.getApplicationContext(), "最多5张");
                }
            }
        });
        adapterThree.setOnDeleteClick(new ImageWarrantyAdapter.OnDeleteClick() {
            @Override
            public void onDeleteClick(final int data) {
                final String file_id = adapterThree.getItem(data).getImg_id();
                if (TextUtils.isEmpty(file_id)) {
                    adapterThree.remove(data);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage(R.string.detete_file);
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            map.clear();
                            map.put("img_ids", file_id);
                            getPresenter().deleteElectronicImg(userBean.getStaff_token(), map);
                            adapterThree.remove(data);
                        }
                    });
                    builder.setNegativeButton("取消", null);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }
        });
        recycleViewThree.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });

    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        if ("0".equals(type)){
            tvDepartment.setText(userBean.getDepartment_name());
            group_id=userBean.getDepartment_id();
        }
    }

    public void isHavePermission(int data) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CARERAM);
                return;
            } else {
                startScan(data);

            }
        } else {
            startScan(data);
        }
    }

    private void startScan(int data) {
        switch (data) {
            case 0:
                Intent intentOne = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intentOne, SCAN_ONE);
                break;
            case 1:
                Intent intentTwo = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intentTwo, SCAN_TWO);
                break;
            case 2:
                Intent intentThree = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intentThree, SCAN_THREE);
                break;
            case 3:
                Intent intentFour = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intentFour, SCAN_FOUR);
                break;
            case 4:
                Intent intentFive = new Intent(getActivity(), CaptureActivity.class);
                startActivityForResult(intentFive, SCAN_FIVE);
                break;
        }
    }

    @OnClick({R.id.ivLeft, R.id.tv_two, R.id.tvAdd, R.id.tv_four, R.id.tvQFour, R.id.ok, R.id.tvDepartment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvDepartment:
                startDepartmentPermissionFragment(permissionsBean.getMenu_id(), "water");
                break;
            case R.id.tvAdd:
                if (tableAdapter.getAllData().size() >= 5) {
                    ToastUtil.showToast(context.getApplicationContext(), "最多添加五个产品");
                    return;
                }
                tableAdapter.add(new FilterTypeBean());
                break;

            case R.id.tv_two:
                showSingleAlertDialog(tvTwo, "选择性别", new String[]{"先生", "女士"});
                break;
            case R.id.tv_four:
                hideInputMethod(tvOne);
                cityPicker("上海市", "上海市", "浦东新区");
                break;
            case R.id.tvQFour:
                chooseTimeTwo("安装日期");
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
/*                String mtvSix = tvSix.getText().toString();
                if (TextUtils.isEmpty(mtvSix)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入邮编");
                    return;
                }*/

                String mtvWSix = tvWSix.getText().toString();
                if (TextUtils.isEmpty(mtvWSix)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入原水水压");
                    return;
                }

                String mtvWSeven = tvWSeven.getText().toString();
                if (TextUtils.isEmpty(mtvWSeven)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入原水水质");
                    return;
                }

                String mtvQOne = tvQOne.getText().toString();
                if (TextUtils.isEmpty(mtvQOne)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入服务商名称");
                    return;
                }
                String mtvPhoneAdd = tvPhoneAdd.getText().toString();
                if (TextUtils.isEmpty(mtvPhoneAdd)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入服务商电话");
                    return;
                }
                String mtvQTwo = tvQTwo.getText().toString();
                if (TextUtils.isEmpty(mtvQTwo)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入安装技师姓名");
                    return;
                }
                String mtvQThree = tvQThree.getText().toString();
                if (TextUtils.isEmpty(mtvQThree)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入安装技师手机号");
                    return;
                }
                String mtvQFour = tvQFour.getText().toString();
                if (TextUtils.isEmpty(mtvQFour)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择安装日期");
                    return;
                }

                List<WarrantyFileBean> allData = adapter.getAllData();
                List<WarrantyFileBean> allData1 = adapterTwo.getAllData();
                List<WarrantyFileBean> allData2 = adapterThree.getAllData();

                if (allData.size() <= 1) {
                    ToastUtil.showToast(context.getApplicationContext(), "请上传产品安装照片");
                    return;
                }
                if (allData1.size() <= 1) {
                    ToastUtil.showToast(context.getApplicationContext(), "请上传保修凭证照片");
                    return;
                }
                if (allData2.size() <= 1) {
                    ToastUtil.showToast(context.getApplicationContext(), "请上传测试照片");
                    return;
                }

                ok.setEnabled(false);
                dialogLoading = new DialogLoading(context);
                dialogLoading.showDialog();
                map.put("group_id", group_id);
                map.put("customer_name", mtvOne);
                map.put("customer_sex", mtvTwo);
                map.put("customer_tel", mtvThree);
                map.put("customer_address", mtvFour);
                map.put("address_info", mtvFive);
                //map.put("address_code", mtvSix);

                map.put("service_tel", mtvPhoneAdd);
                map.put("water_pressure", mtvWSix);
                map.put("water_quality", mtvWSeven);
                map.put("service_provider", mtvQOne);
                map.put("install_name", mtvQTwo);
                map.put("install_tel", mtvQThree);
                map.put("install_no", tvWaterNo.getText().toString());
                map.put("install_time", mtvQFour);
                List<FilterTypeBean> tableData = tableAdapter.getAllData();
                String tableJson = new Gson().toJson(tableData);
                map.put("productBeans", tableJson);

                map.put("is_select", "0");
                map.put("is_app", "1");
                map.put("module_id", permissionsBean.getMenu_id());

                fileList = new ArrayList<>();
                fileList.clear();
                switch (type) {
                    case "0"://新建
                        map.put("operation", "0");
                        for (WarrantyFileBean s : allData) {
                            if (!TextUtils.isEmpty(s.getImg_url())) {
                                fileList.add(new WarrantyFileBean(s.getImg_url(), "success"));
                            }
                        }
                        for (WarrantyFileBean s : allData1) {
                            if (!TextUtils.isEmpty(s.getImg_url())) {
                                fileList.add(new WarrantyFileBean(s.getImg_url(), "bill"));
                            }
                        }
                        for (WarrantyFileBean s : allData2) {
                            if (!TextUtils.isEmpty(s.getImg_url())) {
                                fileList.add(new WarrantyFileBean(s.getImg_url(), "other"));
                            }
                        }
                        if (fileList.size() > 0) {
                            upImage(fileList);
                        } else {
                            getPresenter().insertElectronicCard(userBean.getStaff_token(), map);
                        }
                        break;
                    case "1"://编辑
                        map.put("operation", "1");
                        map.put("card_id", data.getCard_id());
                        for (WarrantyFileBean s : allData) {
                            if (TextUtils.isEmpty(s.getImg_id()) && !TextUtils.isEmpty(s.getImg_url())) {
                                fileList.add(new WarrantyFileBean(s.getImg_url(), "success"));
                            }
                        }
                        for (WarrantyFileBean s : allData1) {
                            if (TextUtils.isEmpty(s.getImg_id()) && !TextUtils.isEmpty(s.getImg_url())) {
                                fileList.add(new WarrantyFileBean(s.getImg_url(), "bill"));
                            }
                        }
                        for (WarrantyFileBean s : allData2) {
                            if (TextUtils.isEmpty(s.getImg_id()) && !TextUtils.isEmpty(s.getImg_url())) {
                                fileList.add(new WarrantyFileBean(s.getImg_url(), "other"));
                            }
                        }

                        if (fileList.size() > 0) {
                            upImage(fileList);
                        } else {
                            getPresenter().updateElectronicCard(userBean.getStaff_token(), map);
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
                        tvQFour.setText(format);
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


    @Override
    public void onIinsertElectronicCard(String data) {
        if (dialogLoading != null) {
            dialogLoading.closeDialog();
        }
        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();
    }

    @Override
    public void onDdeleteElectronicCard(String data) {

    }

    @Override
    public void onDdeleteElectronicImg(String data) {


    }

    @Override
    public void onUupdateElectronicCard(String data) {
        if (dialogLoading != null) {
            dialogLoading.closeDialog();
        }
        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();
    }

    @Override
    public void OnGgetElectronicCardList(List<WarrantyBean> data) {

    }

    @Override
    public void onGgetElectronicCardDetail(WarrantyBean data) {

    }

    @Override
    public void onUploadFiles(String[] data) {
        for (int i = 0; i < data.length; i++) {
            fileList.get(i).setImg_url(data[i]);
        }
        String toJson = new Gson().toJson(fileList);
        if ("0".equals(type)) {
            map.put("electronicImgList", toJson);
            getPresenter().insertElectronicCard(userBean.getStaff_token(), map);
        } else {
            map.put("electronicImgList", toJson);
            getPresenter().updateElectronicCard(userBean.getStaff_token(), map);
        }
    }

    //单选
    private void showSingleAlertDialog(TextView textView, String title, String[] items) {
        DialogUtil.showSingleChoiceDialog(textView, title, items);
    }


    private void startTwoChooseFragment(final int position) {
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
                tableAdapter.getItem(position).setProduct_soft(s1);
                tableAdapter.getItem(position).setProduct_type(s2);
                tableAdapter.getItem(position).setProduct_name("");
                tableAdapter.notifyItemChanged(position);

            }
        });
        fragment.show(getFragmentManager(), FixRecordAddFragment.class.getSimpleName());
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
        switch (requestcode) {
            case IMAGE_ONE:
                BoxingConfig singleCropImgConfig = new BoxingConfig(BoxingConfig.Mode.MULTI_IMG).withMaxCount(6 - adapter.getAllData().size()).withCropOption(new BoxingCropOption(destUri)).needCamera(R.drawable.ic_boxing_camera_white)
                        .withMediaPlaceHolderRes(R.drawable.ic_boxing_default_image);
                Boxing.of(singleCropImgConfig).withIntent(context, BoxingActivity.class).start(this, requestcode);
                break;
            case IMAGE_TWO:
                BoxingConfig singleCropImgConfig1 = new BoxingConfig(BoxingConfig.Mode.MULTI_IMG).withMaxCount(6 - adapterTwo.getAllData().size()).withCropOption(new BoxingCropOption(destUri)).needCamera(R.drawable.ic_boxing_camera_white)
                        .withMediaPlaceHolderRes(R.drawable.ic_boxing_default_image);
                Boxing.of(singleCropImgConfig1).withIntent(context, BoxingActivity.class).start(this, requestcode);
                break;
            case IMAGE_THREE:
                BoxingConfig singleCropImgConfig2 = new BoxingConfig(BoxingConfig.Mode.MULTI_IMG).withMaxCount(6 - adapterThree.getAllData().size()).withCropOption(new BoxingCropOption(destUri)).needCamera(R.drawable.ic_boxing_camera_white)
                        .withMediaPlaceHolderRes(R.drawable.ic_boxing_default_image);
                Boxing.of(singleCropImgConfig2).withIntent(context, BoxingActivity.class).start(this, requestcode);
                break;
        }


    }

    //从相册选择图片返回
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case IMAGE_ONE:
                if (resultCode == RESULT_OK) {
                    adapter.remove(adapter.getAllData().size() - 1);
                    final ArrayList<BaseMedia> medias = Boxing.getResult(data);

                    for (BaseMedia media : medias) {
                        if (media instanceof ImageMedia) {
                            adapter.add(new WarrantyFileBean(((ImageMedia) media).getThumbnailPath(), "success"));

                        } else {
                            adapter.add(new WarrantyFileBean(media.getPath(), "success"));
                        }
                    }

                    if (adapter.getAllData().size() <= 5) {
                        adapter.add(new WarrantyFileBean("", "success"));
                    }
                }
                break;
            case IMAGE_TWO:
                if (resultCode == RESULT_OK) {
                    //adapter.clear();
                    adapterTwo.remove(adapterTwo.getAllData().size() - 1);
                    final ArrayList<BaseMedia> medias = Boxing.getResult(data);

                    for (BaseMedia media : medias) {
                        if (media instanceof ImageMedia) {
                            adapterTwo.add(new WarrantyFileBean(((ImageMedia) media).getThumbnailPath(), "bill"));

                        } else {
                            adapterTwo.add(new WarrantyFileBean(media.getPath(), "bill"));
                        }
                    }

                    if (adapterTwo.getAllData().size() <= 5) {
                        adapterTwo.add(new WarrantyFileBean("", "bill"));
                    }
                }
                break;
            case IMAGE_THREE:
                if (resultCode == RESULT_OK) {
                    //adapter.clear();
                    adapterThree.remove(adapterThree.getAllData().size() - 1);
                    final ArrayList<BaseMedia> medias = Boxing.getResult(data);

                    for (BaseMedia media : medias) {
                        if (media instanceof ImageMedia) {
                            adapterThree.add(new WarrantyFileBean(((ImageMedia) media).getThumbnailPath(), "other"));

                        } else {
                            adapterThree.add(new WarrantyFileBean(media.getPath(), "other"));
                        }
                    }

                    if (adapterThree.getAllData().size() <= 5) {
                        adapterThree.add(new WarrantyFileBean("", "other"));
                    }
                }
                break;
            case SCAN_ONE:
            case SCAN_TWO:
            case SCAN_THREE:
            case SCAN_FOUR:
            case SCAN_FIVE:
                if (resultCode == RESULT_OK) {
                    if (null != data) {
                        Bundle bundle = data.getExtras();
                        if (bundle == null) {
                            return;
                        }
                        if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                            String result = bundle.getString(CodeUtils.RESULT_STRING);
                            tableAdapter.getItem(scanPosition).setProduct_no(result);
                            tableAdapter.notifyItemChanged(scanPosition);
                        } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                            ToastUtil.showToast(context.getApplicationContext(), "解析二维码失败");
                        }
                    }
                }

        }

    }


    //上传图片
    private void upImage(final List<WarrantyFileBean> imgList) {
        final List<File> files = new ArrayList<>();
        final List<String> sList = new ArrayList<>();
        for (WarrantyFileBean bean : imgList) {
            sList.add(bean.getImg_url());
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
    public WarrantyPresenter createPresenter() {
        return new WarrantyPresenter(getApp());
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

