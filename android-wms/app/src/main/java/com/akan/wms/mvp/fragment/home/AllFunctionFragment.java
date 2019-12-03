package com.akan.wms.mvp.fragment.home;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.AllFunctionBean;
import com.akan.wms.mvp.adapter.home.AllFunctionChildAdapter;
import com.akan.wms.mvp.adapter.home.AllFunctionListAdapter;
import com.akan.wms.mvp.base.SimpleFragment;
import com.akan.wms.util.ToastUtil;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class AllFunctionFragment extends SimpleFragment {

    Unbinder unbinder;

    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;

    @BindView(R.id.recyclerViewOne)
    RecyclerView recyclerViewOne;
    @BindView(R.id.recyclerViewTwo)
    RecyclerView recyclerViewTwo;
    @BindView(R.id.tvTittle)
    TextView tvTittle;
    private List<AllFunctionBean> functionList;
    private AllFunctionListAdapter adapter;
    private List<String> childList;
    private AllFunctionChildAdapter adapterTwo;

    private String mType;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public static AllFunctionFragment newInstance(String type) {
        Bundle args = new Bundle();
        AllFunctionFragment fragment = new AllFunctionFragment();
        fragment.mType = type;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_all_function;
    }

    @Override
    public void initUI() {

        tvTitle.setText(R.string.all_function);
        functionList = new ArrayList<>();
        childList = new ArrayList<>();
        initList();
        recyclerViewTwo.setLayoutManager(new GridLayoutManager(context, 3));
        adapterTwo = new AllFunctionChildAdapter(context, childList);
        recyclerViewTwo.setAdapter(adapterTwo);
        adapterTwo.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                switch (adapterTwo.getItem(position)) {
                    case "采购入库":
                        startInBuyListFragment();
                        break;
                    case "销售退货":
                        startInSaleReturnListFragment();
                        break;
                    case "完工申报":
                        startCompleteListFragment();
                        break;
                    case "成品入库":
                        startFinishListFragment();
                        break;
                    case "生产退料":
                        startProduceReturnListFragment();
                        break;
                    case "采购退货":
                        startOutBuyReturnListFragment();
                        break;
                    case "销售出库":
                        startOutSaleListFragment();
                        break;
                    case "生产领料":
                        startProduceReceiveListFragment();
                        break;
                    case "供应商":
                        startSupplierFragment();
                        break;
                    case "仓库信息":
                        startDeportBaseFragment();
                        break;
                    case "设备信息":
                        ToastUtil.showToast(context.getApplicationContext(), getString(R.string.no_device_information));
                        break;
                    case "客户管理":
                        startCustomerFragment();
                        break;
                    case "盘点":
                        startCheckListFragment();
                        break;
                    case "调拨":
                        startTransferListFragment();
                        break;
                    case "库存查询":
                        startStockFindListFragment();
                        break;
                    case "调拨入库":
                        startInTransferListFragment();
                        break;
                    case "调拨出库":
                        startOutTransferListFragment();
                        break;
                    case "出入库流水":
                        startFlowListFragment("");
                        break;
                    case "出货计划":
                        startChooseOutPlanListFragment("");
                        break;
                    case "采购退货申请":
                        startChooseBuyReturnListFragment("", "home");
                        break;
                    case "生产订单":
                        startCompleteChooseFragment("", "home");
                        break;
                    case "调拨申请":
                        startTransferApplyListFragment("", "home");
                        break;
                    case "采购申请":
                        startStockListFragment("", "home");
                        break;
                }
            }
        });
        recyclerViewOne.setLayoutManager(new LinearLayoutManager(context));
        adapter = new AllFunctionListAdapter(context, functionList);
        recyclerViewOne.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                setCheckPosition(position);

            }
        });
        switch (mType) {
            case "1":
                setCheckPosition(0);
                break;
            case "2":
                setCheckPosition(1);
                break;
            case "3":
                setCheckPosition(2);
                break;
            case "4":
                setCheckPosition(3);
                break;
            case "5":
                setCheckPosition(4);
                break;
        }
    }

    private void initList() {
        ArrayList<String> list1 = new ArrayList<>();
        list1.add(getString(R.string.in_buy));
        list1.add(getString(R.string.sale_return));
        list1.add(getString(R.string.complete));
        list1.add(getString(R.string.produce_return));
        list1.add(getString(R.string.finish));
        list1.add(getString(R.string.produce_order));
        list1.add(getString(R.string.buy_apply));
        functionList.add(new AllFunctionBean(getString(R.string.depot_in), list1));
        ArrayList<String> list2 = new ArrayList<>();
        list2.add(getString(R.string.buy_return));
        list2.add(getString(R.string.sale_out));
        list2.add(getString(R.string.produce_receive));
        list2.add(getString(R.string.out_plan));
        list2.add(getString(R.string.purchase_return_application));
        functionList.add(new AllFunctionBean(getString(R.string.depot_out), list2));
        ArrayList<String> list3 = new ArrayList<>();
        list3.add(getString(R.string.supplier));
        list3.add(getString(R.string.depot_information));
        list3.add(getString(R.string.devide_information));
        list3.add(getString(R.string.customer_manager));
        functionList.add(new AllFunctionBean(getString(R.string.base), list3));
        ArrayList<String> list4 = new ArrayList<>();
        list4.add(getString(R.string.check));
        list4.add(getString(R.string.transfer));
        list4.add(getString(R.string.depot_search));
        list4.add(getString(R.string.transfer_in));
        list4.add(getString(R.string.transfer_out));
        list4.add(getString(R.string.transfer_apply));
        functionList.add(new AllFunctionBean(getString(R.string.depot_manager), list4));
        ArrayList<String> list5 = new ArrayList<>();
        list5.add(getString(R.string.flow_in_and_out));
        functionList.add(new AllFunctionBean(getString(R.string.count), list5));
    }

    private void setCheckPosition(int position) {
        List<AllFunctionBean> allData = adapter.getAllData();
        for (int i = 0; i < allData.size(); i++) {
            allData.get(i).setCkeck(false);
        }
        adapter.getItem(position).setCkeck(true);
        tvTittle.setText(adapter.getItem(position).getType());
        adapter.notifyDataSetChanged();
        adapterTwo.clear();
        adapterTwo.addAll(adapter.getItem(position).getChildList());
        adapterTwo.notifyDataSetChanged();
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
