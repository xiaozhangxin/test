package com.ak.pt.mvp.fragment.table;

import android.content.DialogInterface;
import android.os.Bundle;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ak.pt.R;
import com.ak.pt.bean.InstallFormBean;
import com.ak.pt.bean.UserBean;
import com.ak.pt.mvp.adapter.area.TableAllTypeAdapter;
import com.ak.pt.mvp.base.BaseFragment;
import com.ak.pt.mvp.presenter.area.AllTablePresenter;
import com.ak.pt.mvp.view.area.IAllTableView;
import com.ak.pt.util.SpSingleInstance;
import com.ak.pt.util.ToastUtil;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by admin on 2019/5/31.
 */

public class TableAllTypeFragment extends BaseFragment<IAllTableView, AllTablePresenter> implements IAllTableView {
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
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvType)
    TextView tvType;
    @BindView(R.id.recycleView)
    EasyRecyclerView recycleView;


    private List<InstallFormBean> list;
    private TableAllTypeAdapter adapter;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private int page = 1;

    public static TableAllTypeFragment newInstance() {
        Bundle args = new Bundle();
        TableAllTypeFragment fragment = new TableAllTypeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_all_table;
    }


    @Override
    public void initUI() {
        tvTitle.setText("业主装修方式报表");
        list = new ArrayList<>();
        recycleView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new TableAllTypeAdapter(context, list);
        recycleView.setAdapterWithProgress(adapter);
        adapter.setNoMore(R.layout.view_nomore);
        //下拉刷新
        recycleView.setRefreshingColorResources(R.color.colorPrimaryNew);
        recycleView.setRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                refresh();
            }
        });
        //上拉加载
        adapter.setMore(R.layout.view_more, new RecyclerArrayAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                page++;
                refresh();
            }
        });
    }

    private void refresh() {
        map.put("page", page + "");
        getPresenter().getInstallForm(userBean.getStaff_token(), map);
    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        if ("0".equals(userBean.getIs_all_data())) {//有没有数据权限
            map.put("staff_uuid", userBean.getStaff_uuid());
        } else {

            map.put("group_parent_uuid", userBean.getGroup_parent_uuid_new());
        }
        map.put("type", "装饰公司安装");
        refresh();
    }

    @Override
    public void ongetInstallForm(List<InstallFormBean> data) {

        if (page == 1) {
            adapter.clear();
        }
        adapter.addAll(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void ongetDoorForm(List<InstallFormBean> data) {
        if (page == 1) {
            adapter.clear();
        }
        adapter.addAll(data);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void ongetTimelyForm(List<InstallFormBean> data) {

    }

    @Override
    public void ongetDistributorForm(List<InstallFormBean> data) {

    }

    @Override
    public void ongetPlumberForm(List<InstallFormBean> data) {

    }

    @Override
    public void ongetHydraulicNameForm(List<InstallFormBean> data) {

    }

    @Override
    public void ongetSpoolTypeForm(List<InstallFormBean> data) {

    }

    @Override
    public void ongetProjectManagerForm(List<InstallFormBean> data) {

    }

    @Override
    public void ongetHouseNameForm(List<InstallFormBean> data) {

    }

    @Override
    public void ongetOwnerForm(List<InstallFormBean> data) {

    }

    @Override
    public void ongetAuthenticityForm(List<InstallFormBean> data) {

    }

    @Override
    public void ongetStaffForm(List<InstallFormBean> data) {

    }

    @Override
    public void ongetDecorateCompanyForm(List<InstallFormBean> data) {

    }

    @Override
    public void ongetPipeTrendForm(List<InstallFormBean> data) {

    }

    @Override
    public void ongetEventForm(List<InstallFormBean> data) {

    }


    @OnClick({R.id.ivLeft, R.id.tvType})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvType:
                showSingleDialog(new String[]{"装饰公司安装", "水电工全包", "水电工半包", "自装"});
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public AllTablePresenter createPresenter() {
        return new AllTablePresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }



    private AlertDialog alertDialog2;
    private int choose = 0;

    public void showSingleDialog(final String[] items) {
        choose = 0;
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                choose = i;
            }
        });
        alertBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tvType.setText(items[choose]);
                map.put("type", items[choose]);
                refresh();
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

}