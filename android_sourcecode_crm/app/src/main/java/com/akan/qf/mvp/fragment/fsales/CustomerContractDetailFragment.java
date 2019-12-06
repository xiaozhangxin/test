package com.akan.qf.mvp.fragment.fsales;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.qf.R;
import com.akan.qf.bean.ContractApplyBean;
import com.akan.qf.bean.FileNewBean;
import com.akan.qf.bean.FirstEventFilter;
import com.akan.qf.bean.PermissionsBean;
import com.akan.qf.bean.UserBean;
import com.akan.qf.mvp.activity.TbsFileActivity;
import com.akan.qf.mvp.adapter.ImageContractAdapter;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.presenter.home.CustomerContractPresenter;
import com.akan.qf.mvp.view.home.ICustomerContractView;
import com.akan.qf.util.SpSingleInstance;
import com.akan.qf.util.ToastUtil;
import com.akan.qf.view.img.ShowPictureActivity;
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
 * Created by admin on 2018/11/28.
 */

public class CustomerContractDetailFragment extends BaseFragment<ICustomerContractView, CustomerContractPresenter> implements ICustomerContractView {


    Unbinder unbinder;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.tvTop)
    TextView tvTop;
    @BindView(R.id.tvNo)
    TextView tvNo;
    @BindView(R.id.tvState)
    TextView tvState;
    @BindView(R.id.tvTime)
    TextView tvTime;
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
    @BindView(R.id.tvSix)
    TextView tvSix;
    @BindView(R.id.tvSeven)
    TextView tvSeven;
    @BindView(R.id.tvEight)
    TextView tvEight;
    @BindView(R.id.tvNine)
    TextView tvNine;
    @BindView(R.id.tvTen)
    TextView tvTen;
    @BindView(R.id.tvEleven)
    TextView tvEleven;
    @BindView(R.id.tvRemark)
    TextView tvRemark;
    @BindView(R.id.tvJobName)
    TextView tvJobName;
    @BindView(R.id.tvImgTittle)
    TextView tvImgTittle;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    private ContractApplyBean bean;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private List<FileNewBean> imgList;
    private ImageContractAdapter imgAdapter;
    private PermissionsBean permissionsBean;
    private AlertDialog alertDialog1;

    public static CustomerContractDetailFragment newInstance(ContractApplyBean bean, PermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        CustomerContractDetailFragment fragment = new CustomerContractDetailFragment();
        fragment.permissionsBean = permissionsBean;
        fragment.bean = bean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_constract_detail;
    }

    @Override
    public void initUI() {
        tvTitle.setText("客户合同详情");
        imgList = new ArrayList<>();
        recycleView.setLayoutManager(new GridLayoutManager(context, 3));
        recycleView.setNestedScrollingEnabled(false);
        imgAdapter = new ImageContractAdapter(context, imgList);
        recycleView.setAdapter(imgAdapter);
        imgAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String upUrl = imgAdapter.getItem(position).getFile_url();
                if (upUrl.endsWith(".png") | upUrl.endsWith(".jpg") | upUrl.endsWith(".jpeg")) {
                    ArrayList<String> list = new ArrayList<>();
                    int mPosition = 0;
                    for (int i = 0; i < imgAdapter.getAllData().size(); i++) {
                        String file_path = imgAdapter.getItem(i).getFile_url();
                        if (file_path.equals(upUrl)) {
                            mPosition = list.size();
                        }
                        if (file_path.endsWith(".png") | file_path.endsWith(".jpg") | file_path.endsWith(".jpeg")) {
                            list.add(file_path);
                        }
                    }

                    Intent intent = new Intent(getActivity(), ShowPictureActivity.class);
                    intent.putExtra("imagelist", (Serializable) list);
                    intent.putExtra("position", mPosition);
                    getActivity().startActivity(intent);
                } else {
                    Intent intentVisit = new Intent(getActivity(), TbsFileActivity.class);
                    intentVisit.putExtra("file_url", upUrl);
                    intentVisit.putExtra("file_name", imgAdapter.getItem(position).getFile_name());
                    startActivity(intentVisit);

                }
            }
        });

    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();


    }

    private void refresh() {
        map.clear();
        map.put("apply_id", bean.getApply_id());
        getPresenter().getContractApply(userBean.getStaff_token(), map);
    }

    @Override
    public void onResume() {
        super.onResume();
        refresh();
    }

    @Override
    public void getContractApply(ContractApplyBean data) {
        bean = data;
        //appOperation 0新增 1编辑 2删除 3审核
        String appOperation = permissionsBean.getApp_operation();
        String[] strings = appOperation.split(",");
        tvRight.setVisibility(View.VISIBLE);
        if (userBean.getStaff_id().equals(data.getStaff_id())) {
            if (isHave("2", strings)) {
                tvRight.setText(R.string.more);
            } else {
                tvRight.setText(R.string.edit);
            }
        } else {
            if (isHave("1", strings) && isHave("2", strings)) {
                tvRight.setText(R.string.more);
            } else if (isHave("1", strings)) {
                tvRight.setText(R.string.edit);
            } else if (isHave("2", strings)) {
                tvRight.setText(R.string.delete);
            } else {
                tvRight.setVisibility(View.GONE);
            }
        }
        tvJobName.setText(data.getJob_name());
        tvNo.setText(data.getApply_no());
        tvState.setText(data.getStaff_name());
        tvTime.setText(data.getApply_create_time());
        tvOne.setText(data.getApply_address());
        tvTwo.setText(data.getApply_department());
        tvThree.setText(data.getApply_cost());
        tvFour.setText(data.getCustomer_no());
        tvFive.setText(data.getApply_name());
        tvSix.setText(data.getApply_tel());
        tvSeven.setText(data.getApply_sale());
        tvEight.setText(data.getApply_info());
        tvNine.setText(data.getDeadline_time());
        tvTen.setText(data.getApply_fare());
        tvEleven.setText(data.getApply_rebate());
        tvRemark.setText(data.getApply_remark());

        if (data.getFileList().size() <= 0) {
            recycleView.setVisibility(View.GONE);
            tvImgTittle.setVisibility(View.GONE);
        } else {
            recycleView.setVisibility(View.VISIBLE);
            tvImgTittle.setVisibility(View.VISIBLE);
            imgAdapter.clear();
            imgAdapter.addAll(data.getFileList());
            imgAdapter.notifyDataSetChanged();
        }
    }

    //数组钟是否包含某元素
    public boolean isHave(String index, String[] split) {
        for (int i = 0; i < split.length; i++) {
            if (index.equals(split[i])) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void OnDeleteContractFileApply(String data) {

    }

    @Override
    public void onUploadFiles(String[] data) {

    }

    @OnClick({R.id.ivLeft, R.id.tvRight})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvRight:
                switch (tvRight.getText().toString()) {
                    case "更多":
                        showList(new String[]{"编辑", "删除"});
                        break;
                    case "编辑":
                        startContractAddFragment(bean, "1", permissionsBean);
                        break;
                    case "删除":
                        toDelete();
                        break;
                }

                break;
        }
    }

    public void showList(final String[] items) {

        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i) {
                    case 0:
                        startContractAddFragment(bean, "1", permissionsBean);
                        break;
                    case 1:
                        toDelete();
                        break;
                }

                alertDialog1.dismiss();
            }
        });
        alertDialog1 = alertBuilder.create();
        alertDialog1.show();
    }

    //删除单据
    private void toDelete() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(R.string.sure_delete);
        builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                map.clear();
                map.put("apply_id", bean.getApply_id());
                map.put("staff_id", userBean.getStaff_id());
                map.put("is_select", "0");
                map.put("is_app", "1");
                map.put("operation", "2");
                map.put("module_id", permissionsBean.getMenu_id());
                getPresenter().deleteContractApply(userBean.getStaff_token(), map);
            }
        });
        builder.setNegativeButton(getString(R.string.cancel), null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public CustomerContractPresenter createPresenter() {
        return new CustomerContractPresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
    public void OnInsertContractApply(String data) {

    }

    @Override
    public void OnGetContractApplyList(List<ContractApplyBean> data, String total) {

    }

    @Override
    public void OndeleteContractApply(String data) {
        EventBus.getDefault().post(new FirstEventFilter("contract_delete"));
        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();

    }

    @Override
    public void OnupdateContractApply(String data) {

    }


}

