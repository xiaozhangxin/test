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
import com.ak.pt.bean.DownFileBean;
import com.ak.pt.bean.FilterReplaceBean;
import com.ak.pt.bean.FilterTypeBean;
import com.ak.pt.bean.FirstEventFilter;
import com.ak.pt.bean.UserBean;
import com.ak.pt.mvp.adapter.ImageShowAdapter;
import com.ak.pt.mvp.adapter.water.FilterTableShowAdapter;
import com.ak.pt.mvp.base.BaseFragment;
import com.ak.pt.mvp.presenter.water.FilterReplacePresenter;
import com.ak.pt.mvp.view.water.IFilterReplaceView;
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
 * Created by admin on 2019/5/22.
 */

public class FilterReplaceDetailFragment extends BaseFragment<IFilterReplaceView, FilterReplacePresenter> implements IFilterReplaceView {

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
    @BindView(R.id.textView4)
    TextView textView4;
    @BindView(R.id.btOne)
    TextView btOne;
    @BindView(R.id.btTwo)
    TextView btTwo;
    @BindView(R.id.btThree)
    TextView btThree;
    @BindView(R.id.imgTittleDown)
    TextView imgTittleDown;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.imgTittleUp)
    TextView imgTittleUp;
    @BindView(R.id.recycleViewTwo)
    RecyclerView recycleViewTwo;
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
    private List<String> imgListTwo;
    private ImageShowAdapter imgAdapterTwo;
    private FilterReplaceBean bean;
    private List<FilterTypeBean> tableList;
    private FilterTableShowAdapter tableAdapter;

    private AppPermissionsBean permissionsBean;

    public static FilterReplaceDetailFragment newInstance(String detail_id, AppPermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        FilterReplaceDetailFragment fragment = new FilterReplaceDetailFragment();
        fragment.detail_id = detail_id;
        fragment.permissionsBean = permissionsBean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_filter_replace_detail;
    }

    @Override
    public void initUI() {
        llDepartment.setVisibility(View.GONE);
        tvTitle.setText("滤芯更换回执单详情");
        tableList = new ArrayList<>();
        tableRecycle.setLayoutManager(new LinearLayoutManager(context));
        tableRecycle.setNestedScrollingEnabled(false);
        tableAdapter = new FilterTableShowAdapter(context, tableList);
        tableRecycle.setAdapter(tableAdapter);


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

        imgListTwo = new ArrayList<>();
        recycleViewTwo.setLayoutManager(new GridLayoutManager(context, 3));
        recycleViewTwo.setNestedScrollingEnabled(false);
        imgAdapterTwo = new ImageShowAdapter(context, imgListTwo);
        recycleViewTwo.setAdapter(imgAdapterTwo);
        imgAdapterTwo.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), ShowPictureActivity.class);
                intent.putExtra("imagelist", (Serializable) imgAdapterTwo.getAllData());
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
        map.put("filter_id", detail_id);
        getPresenter().getFilterElementDetail(userBean.getStaff_token(), map);
    }


    @Override
    public void onGetFilterElementDetail(FilterReplaceBean data) {
        if (TextUtils.isEmpty(data.getFilter_id())) {
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

        tvNo.setText(data.getFilter_no());
        tvName.setText(data.getStaff_name());
        tvJobName.setText(data.getJob_name());
        tvTime.setText(data.getCreate_time());

        tvOne.setText(data.getCustomer_name());
        tvTwo.setText(data.getCustomer_sex());
        tvThree.setText(data.getCustomer_tel());
        tvFour.setText(data.getCustomer_address() + " " + data.getAddress_info());
        //  tvFive.setText(data.getAddress_code());

        btOne.setText(data.getServe_shop());
        btTwo.setText(data.getServe_name());
        btThree.setText(data.getServe_tel());

        tableAdapter.clear();
        tableAdapter.addAll(data.getProductList());
        tableAdapter.notifyDataSetChanged();


        List<DownFileBean> upFileList = data.getDownFileList();
        if (upFileList.size() > 0) {
            imgTittleDown.setVisibility(View.VISIBLE);
            recycleView.setVisibility(View.VISIBLE);
            imgAdapter.clear();
            for (DownFileBean bean : upFileList) {
                imgAdapter.add(bean.getFile_url());
            }
            imgAdapter.notifyDataSetChanged();
        } else {
            imgTittleDown.setVisibility(View.GONE);
            recycleView.setVisibility(View.GONE);
        }
        List<DownFileBean> downFileList = data.getUpFileList();
        if (downFileList.size() > 0) {
            imgTittleUp.setVisibility(View.VISIBLE);
            recycleViewTwo.setVisibility(View.VISIBLE);
            imgAdapterTwo.clear();
            for (DownFileBean bean : downFileList) {
                imgAdapterTwo.add(bean.getFile_url());
            }
            imgAdapterTwo.notifyDataSetChanged();
        } else {
            imgTittleUp.setVisibility(View.GONE);
            recycleViewTwo.setVisibility(View.GONE);
        }
    }


    @Override
    public void onDeleteFilterElement(String data) {
        EventBus.getDefault().post(new FirstEventFilter("filter_delete"));
        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();
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
                        startFilterReplaceAddFragment(bean, "1", permissionsBean);
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
                map.put("filter_id", detail_id);
                map.put("is_select", "0");
                map.put("is_app", "1");
                map.put("operation", "2");
                map.put("module_id", permissionsBean.getMenu_id());
                getPresenter().deleteFilterElement(userBean.getStaff_token(), map);
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
                        startFilterReplaceAddFragment(bean, "1", permissionsBean);
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
    public void onInsertFilterElement(String data) {

    }


    @Override
    public void onDeleteFilterFile(String data) {

    }

    @Override
    public void onUpdateFilterElement(String data) {

    }


    @Override
    public void OnGetFilterElementList(List<FilterReplaceBean> data) {

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
    public FilterReplacePresenter createPresenter() {
        return new FilterReplacePresenter(getApp());
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

