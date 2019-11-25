package com.ak.pt.mvp.fragment.water;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ak.pt.R;
import com.ak.pt.bean.AppPermissionsBean;
import com.ak.pt.bean.FilterTypeBean;
import com.ak.pt.bean.FirstEventFilter;
import com.ak.pt.bean.UserBean;
import com.ak.pt.bean.WarrantyBean;
import com.ak.pt.bean.WarrantyFileBean;
import com.ak.pt.mvp.adapter.water.ImageShowNameAdapter;
import com.ak.pt.mvp.adapter.water.WarranyTableShowAdapter;
import com.ak.pt.mvp.base.BaseFragment;
import com.ak.pt.mvp.presenter.water.WarrantyPresenter;
import com.ak.pt.mvp.view.water.IWarrantyView;
import com.ak.pt.util.CustomDialog;
import com.ak.pt.util.SpSingleInstance;
import com.ak.pt.util.ToastUtil;
import com.ak.pt.util.img.ShowPictureActivity;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by admin on 2019/5/23.
 */

public class WarrantyDetailFragment extends BaseFragment<IWarrantyView, WarrantyPresenter> implements IWarrantyView {

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
    @BindView(R.id.tvOne)
    TextView tvOne;
    @BindView(R.id.tvTwo)
    TextView tvTwo;
    @BindView(R.id.tvThree)
    TextView tvThree;
    @BindView(R.id.tvFour)
    TextView tvFour;
    @BindView(R.id.tvFive)
    TextView tvFive;
    @BindView(R.id.tableRecycle)
    RecyclerView tableRecycle;
    @BindView(R.id.tvWSix)
    TextView tvWSix;
    @BindView(R.id.tvWSeven)
    TextView tvWSeven;
    @BindView(R.id.tvQOne)
    TextView tvQOne;
    @BindView(R.id.tvQTwo)
    TextView tvQTwo;
    @BindView(R.id.tvQThree)
    TextView tvQThree;
    @BindView(R.id.tvQFour)
    TextView tvQFour;
    @BindView(R.id.imgLine)
    View imgLine;
    @BindView(R.id.imgTittle)
    TextView imgTittle;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.tvTop)
    TextView tvTop;
    @BindView(R.id.tvNo)
    TextView tvNo;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvJobName)
    TextView tvJobName;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.textView5)
    TextView textView5;
    @BindView(R.id.tvDepartment)
    TextView tvDepartment;
    @BindView(R.id.tvPhoneAdd)
    TextView tvPhoneAdd;
    @BindView(R.id.tvWorkerNo)
    TextView tvWorkerNo;
    @BindView(R.id.llDepartment)
    LinearLayout llDepartment;
    @BindView(R.id.tvDepartmentAdd)
    TextView tvDepartmentAdd;


    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private String detail_id;
    private List<WarrantyFileBean> imgList;
    private ImageShowNameAdapter imgAdapter;
    private WarrantyBean bean;
    private List<FilterTypeBean> tableList;
    private WarranyTableShowAdapter tableAdapter;

    private AppPermissionsBean permissionsBean;

    public static WarrantyDetailFragment newInstance(String detail_id, AppPermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        WarrantyDetailFragment fragment = new WarrantyDetailFragment();
        fragment.permissionsBean = permissionsBean;
        fragment.detail_id = detail_id;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_warraty_detail;
    }

    @Override
    public void initUI() {
        llDepartment.setVisibility(View.GONE);
        tvTitle.setText("电子保修卡详情");
        tableList = new ArrayList<>();
        tableRecycle.setLayoutManager(new LinearLayoutManager(context));
        tableRecycle.setNestedScrollingEnabled(false);
        tableAdapter = new WarranyTableShowAdapter(context, tableList);
        tableRecycle.setAdapter(tableAdapter);


        imgList = new ArrayList<>();
        recycleView.setLayoutManager(new GridLayoutManager(context, 3));
        recycleView.setNestedScrollingEnabled(false);
        imgAdapter = new ImageShowNameAdapter(context, imgList);
        recycleView.setAdapter(imgAdapter);
        imgAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                List<WarrantyFileBean> allData = imgAdapter.getAllData();
                ArrayList<String> list = new ArrayList<>();
                for (int i = 0; i < allData.size(); i++) {
                    list.add(allData.get(i).getImg_url());
                }
                Intent intent = new Intent(getActivity(), ShowPictureActivity.class);
                intent.putExtra("imagelist", (Serializable) list);
                intent.putExtra("position", position);
                getActivity().startActivity(intent);


            }
        });
    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
    }

    @Override
    public void onResume() {
        super.onResume();
        map.put("card_id", detail_id);
        getPresenter().getElectronicCardDetail(userBean.getStaff_token(), map);
    }

    @OnClick({R.id.ivLeft, R.id.tvRight})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvRight:
                String s = tvRight.getText().toString();
                switch (s) {
                    case "删除":
                        onDelete();
                        break;
                    case "编辑":
                        startWarrantyAddFragment(bean, "1", permissionsBean);
                        break;
                    case "更多":
                        showList(new String[]{"编辑", "删除"});
                        break;
                }
                break;
        }

    }

    private void onDelete() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("确定要删除此单据吗?");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                map.clear();
                map.put("card_id", detail_id);
                map.put("is_select", "0");
                map.put("is_app", "1");
                map.put("operation", "2");
                map.put("module_id", permissionsBean.getMenu_id());
                getPresenter().deleteElectronicCard(userBean.getStaff_token(), map);
            }
        });
        builder.setNegativeButton("取消", null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private AlertDialog alertDialog1;

    public void showList(final String[] items) {

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (items[i]) {
                    case "编辑":
                        startWarrantyAddFragment(bean, "1", permissionsBean);
                        break;
                    case "删除":

                        onDelete();
                        break;
                }

                alertDialog1.dismiss();
            }
        });
        alertDialog1 = alertBuilder.create();
        alertDialog1.show();
    }

    @Override
    public void onIinsertElectronicCard(String data) {

    }

    @Override
    public void onDdeleteElectronicCard(String data) {
        EventBus.getDefault().post(new FirstEventFilter("filter_delete"));
        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();
    }

    @Override
    public void onDdeleteElectronicImg(String data) {

    }

    @Override
    public void onUupdateElectronicCard(String data) {

    }

    @Override
    public void OnGgetElectronicCardList(List<WarrantyBean> data) {

    }

    @Override
    public void onGgetElectronicCardDetail(WarrantyBean data) {
        if (TextUtils.isEmpty(data.getCard_id())) {
            final CustomDialog.Builder builder1 = new CustomDialog.Builder(context);
            builder1.setMessage(getString(R.string.deleted));
            builder1.setPositiveButton(getString(R.string.sure), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                    dialog.dismiss();
                }
            });
            builder1.onCreate().show();
            return;
        }
        bean = data;
        //权限控制
        String appOperation = permissionsBean.getApp_operation();
        if (appOperation.contains("1") && appOperation.contains("2")) {
            tvRight.setVisibility(View.VISIBLE);
            tvRight.setText("更多");
        } else if (appOperation.contains("1") && !appOperation.contains("2")) {
            tvRight.setVisibility(View.VISIBLE);
            tvRight.setText("编辑");

        } else if (!appOperation.contains("1") && appOperation.contains("2")) {
            tvRight.setVisibility(View.VISIBLE);
            tvRight.setText("删除");
        } else if (!appOperation.contains("1") && !appOperation.contains("2")) {
            tvRight.setVisibility(View.GONE);
        }
        tvDepartmentAdd.setText(data.getDepartment_name());
        tvNo.setText(data.getCard_no());
        tvName.setText(data.getStaff_name());
        tvJobName.setText(data.getJob_name());
        tvTime.setText(data.getCreate_time());
        tvOne.setText(data.getCustomer_name());
        tvTwo.setText(data.getCustomer_sex());
        tvThree.setText(data.getCustomer_tel());
        tvFour.setText(data.getCustomer_address() + "-" + data.getAddress_info());
        // tvFive.setText(data.getAddress_code());


        tvWSix.setText(data.getWater_pressure());
        tvPhoneAdd.setText(data.getService_tel());
        tvWorkerNo.setText(data.getInstall_no());
        tvWSeven.setText(data.getWater_quality());

        tableAdapter.clear();
        tableAdapter.addAll(data.getProductList());
        tableAdapter.notifyDataSetChanged();


        tvQOne.setText(data.getService_provider());
        tvQTwo.setText(data.getAddress_info());
        tvQThree.setText(data.getInstall_tel());
        tvQFour.setText(data.getInstall_time());
        if (data.getImgSuccessList().size() <= 0 && data.getImgBillList().size() <= 0 && data.getImgOtherList().size() <= 0) {
            imgTittle.setVisibility(View.GONE);
            imgLine.setVisibility(View.GONE);
        } else {
            imgTittle.setVisibility(View.VISIBLE);
            imgLine.setVisibility(View.VISIBLE);
            imgAdapter.clear();
            imgAdapter.addAll(data.getImgSuccessList());
            imgAdapter.addAll(data.getImgBillList());
            imgAdapter.addAll(data.getImgOtherList());
        }


    }

    @Override
    public void onUploadFiles(String[] data) {

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
    public WarrantyPresenter createPresenter() {
        return new WarrantyPresenter(getApp());
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

