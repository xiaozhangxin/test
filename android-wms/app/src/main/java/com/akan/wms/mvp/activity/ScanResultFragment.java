package com.akan.wms.mvp.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.BarBean;
import com.akan.wms.bean.FirstEvent;
import com.akan.wms.bean.InforListBean;
import com.akan.wms.bean.ScanBean;
import com.akan.wms.mvp.adapter.ScanResultListAdapter;
import com.akan.wms.mvp.adapter.ScanResultTopAdapter;
import com.akan.wms.mvp.base.SimpleFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ScanResultFragment extends SimpleFragment {


    Unbinder unbinder;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.llbg)
    LinearLayout llbg;
    @BindView(R.id.childRecyclerView)
    RecyclerView childRecyclerView;
    @BindView(R.id.topBg)
    LinearLayout topBg;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ok)
    TextView ok;
    private ScanResultListAdapter adapter;
    private ScanResultTopAdapter adapterTop;
    private List<InforListBean> mList;
    private List<BarBean> barList;
    private String type;

    public static ScanResultFragment newInstance(List<InforListBean> list, List<BarBean> barList, String type) {
        Bundle args = new Bundle();
        ScanResultFragment fragment = new ScanResultFragment();
        fragment.type = type;
        fragment.mList = list;
        fragment.barList = barList;
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getRootViewId() {
        return R.layout.fragment_scan_result;
    }

    @Override
    public void initUI() {
        childRecyclerView.setNestedScrollingEnabled(false);
        childRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterTop = new ScanResultTopAdapter(getContext(), mList);
        childRecyclerView.setAdapter(adapterTop);

        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ScanResultListAdapter(getContext(), barList);
        recyclerView.setAdapter(adapter);
        adapter.setOnCustomClickListener(new ScanResultListAdapter.OnCustomClickListener() {
            @Override
            public void onDeldte(int position, String name) {
                showDialog(position, name);
            }

            @Override
            public void onChange(int position, String name) {

            }
        });


    }

    @Override
    public void initData() {

    }

    //是否删除弹框
    private void showDialog(final int position, String name) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("提示");
        builder.setMessage("是否确认删除" + name + "？");
        builder.setCancelable(true);
        builder.setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                adapter.remove(position);
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();

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

    @OnClick({R.id.ivLeft, R.id.ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.ok:
                if ("1".equals(type)) {
                    EventBus.getDefault().post(new FirstEvent("scan", new ScanBean(mList)));
                }
                EventBus.getDefault().post(new FirstEvent("scan_finish"));
                finish();
                break;
        }
    }
}
