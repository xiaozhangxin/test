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
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ak.pt.R;
import com.ak.pt.bean.AppPermissionsBean;
import com.ak.pt.bean.BookNameBean;
import com.ak.pt.bean.DownFileBean;
import com.ak.pt.bean.FilterReplaceBean;
import com.ak.pt.bean.FilterTypeBean;
import com.ak.pt.bean.FirstEventBook;
import com.ak.pt.bean.TwoChooseBean;
import com.ak.pt.bean.UserBean;
import com.ak.pt.mvp.adapter.water.FilterTableAdapter;
import com.ak.pt.mvp.adapter.water.ImageFilterAdapter;
import com.ak.pt.mvp.base.BaseFragment;
import com.ak.pt.mvp.presenter.water.FilterReplacePresenter;
import com.ak.pt.mvp.view.water.IFilterReplaceView;
import com.ak.pt.util.CustomDialog;
import com.ak.pt.util.DialogUtil;
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
import static com.ak.pt.mvp.fragment.adaily.SignFragment.REQUEST_CARERAM;

/**
 * Created by admin on 2019/5/22.
 */

public class FilterReplaceAddFragment extends BaseFragment<IFilterReplaceView, FilterReplacePresenter> implements IFilterReplaceView {


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
    @BindView(R.id.bt_one)
    EditText btOne;
    @BindView(R.id.bt_two)
    EditText btTwo;
    @BindView(R.id.bt_three)
    EditText btThree;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.recycleViewTwo)
    RecyclerView recycleViewTwo;
    @BindView(R.id.ok)
    Button ok;
    @BindView(R.id.tvDepartment)
    TextView tvDepartment;


    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;

    private List<DownFileBean> list;
    private List<DownFileBean> listTwo;
    private ImageFilterAdapter adapter;
    private ImageFilterAdapter adapterTwo;
    private static final int IMAGE = 0X01;
    private static final int IMAGE_TWO = 0X02;
    private static final int SCAN_ONE = 0X03;
    private static final int SCAN_TWO = 0X04;
    private static final int SCAN_THREE = 0X05;
    private static final int SCAN_FOUR = 0X06;
    private static final int SCAN_FIVE = 0X07;
    private String type;
    private FilterReplaceBean data;

    private ArrayList<DownFileBean> fileList;


    private ArrayList<FilterTypeBean> tableList;
    private FilterTableAdapter tableAdapter;
    private int scanPosition = 0;
    private int changePosition = 0;

    private AppPermissionsBean permissionsBean;

    public static FilterReplaceAddFragment newInstance(FilterReplaceBean bean, String type, AppPermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        FilterReplaceAddFragment fragment = new FilterReplaceAddFragment();
        fragment.permissionsBean = permissionsBean;
        fragment.data = bean;
        fragment.type = type;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_add_filter;
    }

    @Override
    public void initUI() {
        list = new ArrayList<>();
        listTwo = new ArrayList<>();
        tableList = new ArrayList<>();
        if ("0".equals(type)) {
            ok.setText("提交");
            tvTitle.setText("滤芯更换回执单");
            tableList.add(new FilterTypeBean());
        } else {
            tvTitle.setText("修改滤芯更换回执单");
            ok.setText("确认修改");
            tvDepartment.setText(data.getDepartment_name());
            group_id = data.getGroup_id();
            tvOne.setText(data.getCustomer_name());
            tvTwo.setText(data.getCustomer_sex());
            tvThree.setText(data.getCustomer_tel());
            tvFour.setText(data.getCustomer_address());
            tvFive.setText(data.getAddress_info());
            //  tvSix.setText(data.getAddress_code());

            btOne.setText(data.getServe_shop());
            btTwo.setText(data.getServe_name());
            btThree.setText(data.getServe_tel());
            list.addAll(data.getDownFileList());
            listTwo.addAll(data.getUpFileList());

            if (data.getProductList().size() > 0) {
                tableList.addAll(data.getProductList());
            } else {
                tableList.add(new FilterTypeBean());
            }
        }


        recycleViewTable.setLayoutManager(new LinearLayoutManager(context));
        tableAdapter = new FilterTableAdapter(context, tableList);
        recycleViewTable.setAdapter(tableAdapter);
        tableAdapter.setOnDeleteClick(new FilterTableAdapter.OnDeleteClick() {
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

            @Override
            public void onChooseChange(String type, int data) {
                changePosition = data;
                switch (type) {
                    case "反渗透净水机CR75-F2":
                        chooseMore(type, new String[]{"PP棉", "前置活性炭纤维滤芯", "超滤膜", "后置活性炭"});
                        break;
                    case "反渗透净水机CR50-D1":
                    case "反渗透净水机CR75-F1":
                    case "反渗透净水机CR400-K1":
                        chooseMore(type, new String[]{"前置活性炭", "PP棉5微米", "PP棉1微米", "RO膜", "后置活性炭小T33"});
                        break;
                    case "反渗透净水机CWRF-H11":
                        chooseMore(type, new String[]{"PP棉", "PP棉", "前置活性炭", "RO膜"});
                        break;
                    case "反渗透净水机CR-600V":
                        chooseMore(type, new String[]{"PAC复合滤芯", "RO膜", "3C后置活性炭"});
                        break;
                    case "超滤净水机CWUF-3100":
                        chooseMore(type, new String[]{"PP棉", "超滤膜", "后置活性炭纤维"});
                        break;
                    case "中央净水机CWAF-C10":
                    case "中央净水机CWAF-C05":
                    case "中央净水机CWAF-C06":
                    case "中央净水机CWAF-C15":
                    case "中央净水机CWAF-C20":
                    case "中央净水机CWAF-C25":
                        chooseMore(type, new String[]{"活性炭滤料", "KDF合金滤料"});
                        break;
                    case "中央软水机CWIE-R10":
                    case "中央软水机CWIE-R05":
                    case "中央软水机CWIE-R15":
                    case "中央软水机CWIE-R20":
                    case "中央软水机CWIE-R25":
                        chooseMore(type, new String[]{"软化树脂"});
                        break;
                }

            }


        });


        list.add(new DownFileBean("", "down"));
        recycleView.setLayoutManager(new GridLayoutManager(context, 3));
        adapter = new ImageFilterAdapter(context, list);
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
        adapter.setOnDeleteClick(new ImageFilterAdapter.OnDeleteClick() {
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
                            getPresenter().deleteFilterFile(userBean.getStaff_token(), map);
                            adapter.remove(data);
                        }
                    });
                    builder.setNegativeButton("取消", null);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }
        });


        listTwo.add(new DownFileBean("", "up"));
        recycleViewTwo.setLayoutManager(new GridLayoutManager(context, 3));
        adapterTwo = new ImageFilterAdapter(context, listTwo);
        recycleViewTwo.setNestedScrollingEnabled(false);
        recycleViewTwo.setAdapter(adapterTwo);
        adapterTwo.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (adapterTwo.getAllData().size() <= 3) {
                    show_img(IMAGE_TWO);
                } else {
                    ToastUtil.showToast(context.getApplicationContext(), "最多3张");
                }
            }
        });


        adapterTwo.setOnDeleteClick(new ImageFilterAdapter.OnDeleteClick() {
            @Override
            public void onDeleteClick(final int data) {
                final String file_id = adapterTwo.getItem(data).getFile_id();
                if (TextUtils.isEmpty(file_id)) {
                    adapterTwo.remove(data);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setMessage(R.string.detete_file);
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            map.clear();
                            map.put("file_id", file_id);
                            getPresenter().deleteFilterFile(userBean.getStaff_token(), map);
                            adapterTwo.remove(data);
                        }
                    });
                    builder.setNegativeButton("取消", null);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
            }
        });
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


    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        if ("0".equals(type)){
            tvDepartment.setText(userBean.getDepartment_name());
            group_id=userBean.getDepartment_id();
        }
    }


    @OnClick({R.id.ivLeft, R.id.tv_two, R.id.tv_four, R.id.ok, R.id.tvAdd, R.id.tvDepartment})
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
                String mbtOne = btOne.getText().toString();
                if (TextUtils.isEmpty(mbtOne)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入服务商信息");
                    return;
                }
                String mbtTwo = btTwo.getText().toString();
                if (TextUtils.isEmpty(mbtTwo)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入服务人员姓名");
                    return;
                }
                String mbtThree = btThree.getText().toString();
                if (TextUtils.isEmpty(mbtThree)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入服务人员电话");
                    return;
                }


                ok.setEnabled(false);
                dialogLoading = new DialogLoading(context);
                dialogLoading.showDialog();
                map.put("staff_id", userBean.getStaff_id());
                map.put("group_id", group_id);

                map.put("customer_name", mtvOne);
                map.put("customer_sex", mtvTwo);
                map.put("customer_tel", mtvThree);
                map.put("customer_address", mtvFour);
                map.put("address_info", mtvFive);
                //  map.put("address_code", mtvSix);
                map.put("serve_shop", mbtOne);
                map.put("serve_name", mbtTwo);
                map.put("serve_tel", mbtThree);
                map.put("is_select", "0");
                map.put("is_app", "1");
                map.put("module_id", permissionsBean.getMenu_id());

                List<FilterTypeBean> tableData = tableAdapter.getAllData();
                String tableJson = new Gson().toJson(tableData);
                map.put("productBeans", tableJson);

                fileList = new ArrayList<>();
                fileList.clear();
                List<DownFileBean> allData = adapter.getAllData();
                List<DownFileBean> allDataTwo = adapterTwo.getAllData();
                switch (type) {
                    case "0"://新建
                        map.put("operation", "0");
                        for (DownFileBean s : allData) {
                            if (!TextUtils.isEmpty(s.getFile_url())) {
                                fileList.add(new DownFileBean(s.getFile_url(), "down"));
                            }
                        }
                        for (DownFileBean s1 : allDataTwo) {
                            if (!TextUtils.isEmpty(s1.getFile_url())) {
                                fileList.add(new DownFileBean(s1.getFile_url(), "up"));
                            }
                        }
                        if (fileList.size() > 0) {
                            upImage(fileList);
                        } else {
                            getPresenter().insertFilterElement(userBean.getStaff_token(), map);
                        }
                        break;
                    case "1"://编辑
                        map.put("operation", "1");
                        map.put("filter_id", data.getFilter_id());
                        for (DownFileBean s : allData) {
                            if (TextUtils.isEmpty(s.getFilter_id()) && !TextUtils.isEmpty(s.getFile_url())) {
                                fileList.add(new DownFileBean(s.getFile_url(), "down"));
                            }
                        }
                        for (DownFileBean s : allDataTwo) {
                            if (TextUtils.isEmpty(s.getFilter_id()) && !TextUtils.isEmpty(s.getFile_url())) {
                                fileList.add(new DownFileBean(s.getFile_url(), "up"));
                            }
                        }
                        if (fileList.size() > 0) {
                            upImage(fileList);
                        } else {
                            getPresenter().updateFilterElement(userBean.getStaff_token(), map);
                        }
                }
                break;


        }
    }


    private void startTwoChooseFragment(final int position) {
        ArrayList<TwoChooseBean> list = new ArrayList<>();

        String json = "[{\"childList\":[" +
                "{\"isCheck\":false,\"name\":\"CR75-F1\"}," + "{\"isCheck\":false,\"name\":\"CR400-K1\"}," + "{\"isCheck\":false,\"name\":\"CWRF-H11\"}," + "{\"isCheck\":false,\"name\":\"CR75-F2\"}," + "{\"isCheck\":false,\"name\":\"CR-600V\"},{\"isCheck\":false,\"name\":\"CR50-D1\"}],\"isCheck\":true,\"name\":\"反渗透净水机\"},\n" +
                "{\"childList\":[" + "{\"isCheck\":false,\"name\":\"CWUF-3100\"}" + "],\"isCheck\":false,\"name\":\"超滤净水机\"},\n" + "{\"childList\":[" + "{\"isCheck\":false,\"name\":\"CWAF-C10\"}," + "{\"isCheck\":false,\"name\":\"CWAF-C05\"}," + "{\"isCheck\":false,\"name\":\"CWAF-C06\"}," + "{\"isCheck\":false,\"name\":\"CWAF-C20\"}," + "{\"isCheck\":false,\"name\":\"CWAF-C25\"}],\"isCheck\":false,\"name\":\"中央净水机\"},\n" +
                "{\"childList\":[" + "{\"isCheck\":false,\"name\":\"CWIE-R10\"}" + ",{\"isCheck\":false,\"name\":\"CWIE-R05\"}," + "{\"isCheck\":false,\"name\":\"CWIE-R15\"}," + "{\"isCheck\":false,\"name\":\"CWIE-R20\"}," + "{\"isCheck\":false,\"name\":\"CWIE-R25\"}],\"isCheck\":false,\"name\":\"中央软水机\"}]";

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


    @Override
    public void onInsertFilterElement(String data) {
        if (dialogLoading != null) {
            dialogLoading.closeDialog();
        }
        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();
    }

    @Override
    public void onUploadFiles(String[] data) {
        for (int i = 0; i < data.length; i++) {
            fileList.get(i).setFile_url(data[i]);
        }
        String toJson = new Gson().toJson(fileList);
        if ("0".equals(type)) {
            map.put("filterList", toJson);
            getPresenter().insertFilterElement(userBean.getStaff_token(), map);
        } else {
            map.put("filterList", toJson);
            getPresenter().updateFilterElement(userBean.getStaff_token(), map);
        }

    }

    @Override
    public void onDeleteFilterElement(String data) {

    }

    @Override
    public void onDeleteFilterFile(String data) {
        ToastUtil.showToast(context.getApplicationContext(), data);

    }

    @Override
    public void onUpdateFilterElement(String data) {
        if (dialogLoading != null) {
            dialogLoading.closeDialog();
        }
        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();
    }

    @Override
    public void onGetFilterElementDetail(FilterReplaceBean data) {

    }

    @Override
    public void OnGetFilterElementList(List<FilterReplaceBean> data) {

    }


    //单选
    private void showSingleAlertDialog(TextView textView, String title, String[] items) {
        DialogUtil.showSingleChoiceDialog(textView, title, items);
    }

    //多选
    private void chooseMore(final String tittle, final String[] items) {
        String[] source = tableAdapter.getItem(changePosition).getProduct_name().split(",");
        DialogUtil.showMultiChoiceDialog(getContext(), tittle, source, items, (items1, checkedItems) -> {
            tableAdapter.getItem(changePosition).setProduct_name(DialogUtil.formatChoiceResult(items1, checkedItems));
            tableAdapter.notifyItemChanged(changePosition);
        });
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
                tvFour.setText(province + " " + city + " " + district);
                //   tvSix.setText(code);
            }

            @Override
            public void onCancel() {
            }

        });
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
        if (0X01 == requestcode) {
            BoxingConfig singleCropImgConfig = new BoxingConfig(BoxingConfig.Mode.MULTI_IMG).withMaxCount(4 - adapter.getAllData().size()).withCropOption(new BoxingCropOption(destUri)).needCamera(R.drawable.ic_boxing_camera_white)
                    .withMediaPlaceHolderRes(R.drawable.ic_boxing_default_image);
            Boxing.of(singleCropImgConfig).withIntent(context, BoxingActivity.class).start(this, requestcode);
        } else {
            BoxingConfig singleCropImgConfig = new BoxingConfig(BoxingConfig.Mode.MULTI_IMG).withMaxCount(4 - adapterTwo.getAllData().size()).withCropOption(new BoxingCropOption(destUri)).needCamera(R.drawable.ic_boxing_camera_white)
                    .withMediaPlaceHolderRes(R.drawable.ic_boxing_default_image);
            Boxing.of(singleCropImgConfig).withIntent(context, BoxingActivity.class).start(this, requestcode);
        }


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
                            adapter.add(new DownFileBean(((ImageMedia) media).getThumbnailPath(), "down"));

                        } else {
                            adapter.add(new DownFileBean(media.getPath(), "down"));
                        }
                    }

                    if (adapter.getAllData().size() <= 3) {
                        adapter.add(new DownFileBean("", "down"));
                    }
                }
                break;
            case IMAGE_TWO:
                if (resultCode == RESULT_OK) {
                    adapterTwo.remove(adapterTwo.getAllData().size() - 1);
                    final ArrayList<BaseMedia> medias = Boxing.getResult(data);

                    for (BaseMedia media : medias) {
                        if (media instanceof ImageMedia) {
                            adapterTwo.add(new DownFileBean(((ImageMedia) media).getThumbnailPath(), "up"));

                        } else {
                            adapterTwo.add(new DownFileBean("", "up"));
                        }
                    }

                    if (adapterTwo.getAllData().size() <= 3) {
                        adapterTwo.add(new DownFileBean("", "up"));
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

                break;
        }


    }


    //上传图片
    private void upImage(final List<DownFileBean> imgList) {
        final List<File> files = new ArrayList<>();
        final List<String> sList = new ArrayList<>();
        for (DownFileBean bean : imgList) {
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
                    builder.addFormDataPart("path", "/images/waterFilter");
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
    public FilterReplacePresenter createPresenter() {
        return new FilterReplacePresenter(getApp());
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
