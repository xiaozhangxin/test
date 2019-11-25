package com.ak.pt.mvp.fragment.water;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.GridLayoutManager;
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
import com.ak.pt.bean.FirstEventFilter;
import com.ak.pt.bean.FixFileBean;
import com.ak.pt.bean.FixRecordBean;
import com.ak.pt.bean.UserBean;
import com.ak.pt.mvp.adapter.ImageShowAdapter;
import com.ak.pt.mvp.base.BaseFragment;
import com.ak.pt.mvp.presenter.water.FixRecordPresenter;
import com.ak.pt.mvp.view.water.IFixRecordView;
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

public class FixRecordDetailFragment extends BaseFragment<IFixRecordView, FixRecordPresenter> implements IFixRecordView {

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
    @BindView(R.id.tvQOne)
    TextView tvQOne;
    @BindView(R.id.tvQTwo)
    TextView tvQTwo;
    @BindView(R.id.tvQThree)
    TextView tvQThree;
    @BindView(R.id.tvQFour)
    TextView tvQFour;
    @BindView(R.id.tvWOne)
    TextView tvWOne;
    @BindView(R.id.tvWTwo)
    TextView tvWTwo;
    @BindView(R.id.tvWThree)
    TextView tvWThree;
    @BindView(R.id.tvWFour)
    TextView tvWFour;
    @BindView(R.id.tvWFive)
    TextView tvWFive;
    @BindView(R.id.tvWSix)
    TextView tvWSix;
    @BindView(R.id.imgTittleDown)
    TextView imgTittleDown;
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
    @BindView(R.id.llDepartment)
    LinearLayout llDepartment;
    @BindView(R.id.tvDepartmentAdd)
    TextView tvDepartmentAdd;


    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private String detail_id;
    private List<String> imgList;
    private ImageShowAdapter imgAdapter;
    private FixRecordBean bean;
    private AppPermissionsBean permissionsBean;

    public static FixRecordDetailFragment newInstance(String detail_id, AppPermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        FixRecordDetailFragment fragment = new FixRecordDetailFragment();
        fragment.permissionsBean = permissionsBean;
        fragment.detail_id = detail_id;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_fix_record_detail;
    }

    @Override
    public void initUI() {
        tvTitle.setText("维修记录表详情");
        llDepartment.setVisibility(View.GONE);
        imgList = new ArrayList<>();
        recycleView.setLayoutManager(new GridLayoutManager(context, 3));
        recycleView.setNestedScrollingEnabled(false);
        imgAdapter = new ImageShowAdapter(context, imgList);
        recycleView.setAdapter(imgAdapter);
        imgAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), ShowPictureActivity.class);
                intent.putExtra("imagelist", (Serializable) imgAdapter.getAllData());
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
        map.put("repair_id", detail_id);
        getPresenter().getRepairRecordDetail(userBean.getStaff_token(), map);
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
                        startFixRecordAddFragment(bean, "1", permissionsBean);
                        break;
                    case "更多":
                        showList(new String[]{"编辑", "删除"});
                        break;
                }
                break;
        }

    }

    private AlertDialog alertDialog1;

    public void showList(final String[] items) {

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (items[i]) {
                    case "编辑":
                        startFixRecordAddFragment(bean, "1", permissionsBean);
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


    private void onDelete() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("确定要删除此单据吗?");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                map.clear();
                map.put("repair_id", detail_id);
                map.put("is_select", "0");
                map.put("is_app", "1");
                map.put("operation", "2");
                map.put("module_id", permissionsBean.getMenu_id());
                getPresenter().deleteRepairRecord(userBean.getStaff_token(), map);
            }
        });
        builder.setNegativeButton("取消", null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    @Override
    public void onInsertRepairRecord(String data) {

    }

    @Override
    public void onDeleteRepairRecord(String data) {
        EventBus.getDefault().post(new FirstEventFilter("filter_delete"));
        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();
    }

    @Override
    public void onDeleteRepairFile(String data) {

    }

    @Override
    public void onUpdateRepairRecord(String data) {

    }

    @Override
    public void OnGetRepairRecordList(List<FixRecordBean> data) {

    }

    @Override
    public void onUploadFiles(String[] data) {

    }

    @Override
    public void onGetRepairRecordDetail(FixRecordBean data) {
        if (TextUtils.isEmpty(data.getRepair_id())) {
            final CustomDialog.Builder builder1 = new CustomDialog.Builder(context);
            builder1.setMessage(getString(R.string.deleted));
            builder1.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                    dialog.dismiss();
                }
            });
            builder1.onCreate().show();
            return;
        }
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
        bean = data;
        tvDepartmentAdd.setText(data.getDepartment_name());
        tvNo.setText(data.getRepair_no());
        tvName.setText(data.getStaff_name());
        tvJobName.setText(data.getJob_name());
        tvTime.setText(data.getCreate_time());
        tvOne.setText(data.getCustomer_name());
        tvTwo.setText(data.getCustomer_sex());
        tvThree.setText(data.getCustomer_tel());
        tvFour.setText(data.getCustomer_address() + "-" + data.getAddress_info());
        //  tvFive.setText(data.getAddress_code());

        tvQOne.setText(data.getRepair_time());
        tvQTwo.setText(data.getInstall_name());
        tvQThree.setText(data.getInstall_tel());
        tvQFour.setText(data.getService_shop());


        tvWOne.setText(data.getProduct_fault() + data.getProduct_model());
        tvWTwo.setText(data.getProduct_no());
        tvWThree.setText(data.getFault_model());
        tvWFour.setText(data.getFault_no());
        tvWFive.setText(data.getRepair_plan());
        tvWSix.setText(data.getChange_name());

        List<FixFileBean> upFileList = data.getFileList();
        if (upFileList.size() > 0) {
            imgTittleDown.setVisibility(View.VISIBLE);
            recycleView.setVisibility(View.VISIBLE);
            imgAdapter.clear();
            for (FixFileBean bean : upFileList) {
                imgAdapter.add(bean.getFile_url());
            }
            imgAdapter.notifyDataSetChanged();
        } else {
            imgTittleDown.setVisibility(View.GONE);
            recycleView.setVisibility(View.GONE);
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
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

