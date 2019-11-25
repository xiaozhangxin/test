package com.ak.pt.mvp.fragment.table;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ak.pt.R;
import com.ak.pt.mvp.adapter.table.ReportFormAdapter;
import com.ak.pt.mvp.base.SimpleFragment;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by admin on 2019/5/31.
 */

public class TableFragmrnt extends SimpleFragment {
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
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    Unbinder unbinder;
    ArrayList<String> list;

    public static TableFragmrnt newInstance() {

        Bundle args = new Bundle();

        TableFragmrnt fragment = new TableFragmrnt();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_table;
    }

    @Override
    public void initUI() {
        tvTitle.setText("报表");
        list = new ArrayList<>();
        list.add("单据审核及时率统计表");
        list.add("管路走向统计表");
        list.add("经销商统计报表");
        list.add("门套安装情况统计报表");
        list.add("试压人员试压量统计表报");
        list.add("水工试压量统计报表");
        list.add("问题记录详情统计报表");
        list.add("线管品牌统计表");
        list.add("项目经理信息登记表");
        list.add("小区名称报表");
        list.add("业主信息收集数量报表");
        list.add("业主装修方式报表");
        list.add("真伪查询率");
        list.add("制单数量统计报");
        list.add("装饰公司名称报表");

        recycleView.setLayoutManager(new LinearLayoutManager(context));
        ReportFormAdapter adapter = new ReportFormAdapter(context, list);
        recycleView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                switch (position) {
                    case 0://单据审核
                        startTableTwelveFragment();
                        break;
                    case 1://管路
                        startTableTwoFragment();
                        break;
                    case 2://经销商
                        startTableThreeFragment();
                        break;
                    case 3://门套安装
                        startTableFourFragment();
                        break;
                    case 4:
                        startTableFiveFragment();
                        break;
                    case 5:
                        startTableSixFragment();
                        break;
                    case 6:
                        startTableSevenFragment();
                        break;
                    case 7:
                        startTableEightFragment();
                        break;
                    case 8:
                        startTableNineFragment();
                        break;
                    case 9:
                        startTableTenFragment();
                        break;
                    case 10:
                        startTableElevenFragment();
                        break;
                    case 11://业主装修方式
                        startAllTypeTableFragment();

                        break;
                    case 12:
                        startTableforteenFragment();
                        break;
                    case 13:
                        startTablefifteenFragment();
                        break;
                    case 14:
                        startTableSixteenFragment();
                        break;

                }

            }
        });

    }

    @Override
    public void initData() {

    }

    @OnClick(R.id.ivLeft)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
        }
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
