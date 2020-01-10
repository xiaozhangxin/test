package com.akan.wms.mvp.fragment.scan;

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
import com.akan.wms.bean.FirstEventTwo;
import com.akan.wms.bean.ScanInBuyBean;
import com.akan.wms.bean.ScanInfoBean;
import com.akan.wms.bean.ScanListBean;
import com.akan.wms.mvp.adapter.ScanInBuyResultAdapter;
import com.akan.wms.mvp.adapter.ScanResultListAdapter;
import com.akan.wms.mvp.base.SimpleFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ScanInBuyResultFragment extends SimpleFragment {


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
    @BindView(R.id.topThree)
    TextView topThree;
    @BindView(R.id.topFour)
    TextView topFour;
    private ScanResultListAdapter adapter;
    private ScanInBuyResultAdapter adapterTop;
    private List<ScanInfoBean> mList;
    private List<BarBean> barList;
    private String type;

    public static ScanInBuyResultFragment newInstance(List<ScanInfoBean> list, List<BarBean> barList, String type) {
        Bundle args = new Bundle();
        ScanInBuyResultFragment fragment = new ScanInBuyResultFragment();
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

        recyclerView.setNestedScrollingEnabled(false);
        childRecyclerView.setNestedScrollingEnabled(false);
        tvTitle.setText("扫码数据");
        switch (type) {
            case "in_buy_point"://采购入库点收
                topThree.setText("送货数量");
                topFour.setText("实收数量");
                break;
            case "in_buy_agree"://采购入库同意入库
                topThree.setText("合格数量");
                topFour.setText("核定数量");
                break;
            case "finish_check"://成品入库(审核)
                topThree.setText("入库数量");
                topFour.setText("核定数量");
                break;
            case "finish_add"://成品入库(添加)
                topThree.setText("可入库数量");
                topFour.setText("入库数量");
                break;
            case "pro_return_point"://生产退料(点收)
            case "pro_return_in"://生产退料(入库)
            case "pro_receive_point"://生产领料(点收)
            case "pro_receive_out"://生产领料(出库)
                topThree.setText("申请数量");
                topFour.setText("核定数量");
                break;
            case "out_buy_add"://采购退货(新增)
                topThree.setText("申请数量");
                topFour.setText("配货数量");
                break;
            case "out_buy_detail"://采购退货(核定)
            case "out_sale_add"://销售出库(新增)
                topThree.setText("配货数量");
                topFour.setText("核定数量");
                break;
            case "check_add"://盘点新增
                topThree.setText("盘点数量");
                topFour.setVisibility(View.GONE);
                break;
            case "transfer_add"://同组织调拨新增
                topThree.setText("配货数量");
                topFour.setVisibility(View.GONE);
                break;
            case "in_transfer_add"://调拨出库(新增)
                topThree.setText("申请数量");
                topFour.setText("调出数量");
                break;
            case "out_transfer_add":
                topThree.setText("调拨数量");
                topFour.setText("配货数量");
                break;


        }
        childRecyclerView.setNestedScrollingEnabled(false);
        childRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterTop = new ScanInBuyResultAdapter(getContext(), mList, type);
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
                int totalNum = 0;
                List<BarBean> allData = adapter.getAllData();
                for (int j = 0; j < allData.size(); j++) {
                    if (name.equals(allData.get(j).getItem_code())) {
                        totalNum = totalNum + allData.get(j).getQty();
                    }
                }

                List<ScanInfoBean> topAllData = adapterTop.getAllData();
                for (int i = 0; i < topAllData.size(); i++) {
                    ScanInfoBean infoBean = topAllData.get(i);
                    if (infoBean.getItem_code().equals(name)) {
                        switch (type) {
                            case "in_buy_point":
                            case "finish_check":
                            case "finish_add":
                            case "pro_return_point":
                            case "pro_return_in":
                            case "pro_receive_point":
                            case "pro_receive_out":
                            case "out_buy_add":
                            case "out_buy_detail":
                            case "out_sale_add":
                            case "check_add":
                            case "transfer_add":
                            case "in_transfer_add":
                            case "out_transfer_add":
                                infoBean.setArrive_qty(totalNum);
                                break;
                            case "in_buy_agree":
                                infoBean.setIn_qty(totalNum);
                                break;
                        }

                    }
                }
                adapterTop.notifyDataSetChanged();

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
                List<ScanInfoBean> allData = adapterTop.getAllData();
                if ("in_buy_agree".equals(type)){
                    for (int i = 0; i < allData.size(); i++) {
                        if (adapter.getItem(position).getItem_code().equals(allData.get(i).getItem_code())) {
                            allData.get(i).setIn_qty(allData.get(i).getIn_qty() - adapter.getItem(position).getQty());
                            adapterTop.notifyItemChanged(i);
                        }
                    }
                }else {
                    for (int i = 0; i < allData.size(); i++) {
                        if (adapter.getItem(position).getItem_code().equals(allData.get(i).getItem_code())) {
                            allData.get(i).setArrive_qty(allData.get(i).getArrive_qty() - adapter.getItem(position).getQty());
                            adapterTop.notifyItemChanged(i);
                        }
                    }
                }
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
                hideInputMethod();
                List<ScanInfoBean> allData = adapterTop.getAllData();
                for (int i = 0; i < allData.size(); i++) {
                    ScanInfoBean infoBean = allData.get(i);
                    switch (type) {
                        case "in_buy_point"://采购入库点收
                            if (infoBean.getSend_qty() < infoBean.getArrive_qty()) {
                                showNotDialog("料品（" + infoBean.getItem_name() + "）数量大于送货数量，请先修改条码数量");
                                return;
                            }
                            break;
                        case "in_buy_agree"://采购入库同意入库
                            if (infoBean.getArrive_qty() < infoBean.getIn_qty()) {
                                showNotDialog("料品（" + infoBean.getItem_name() + "）数量大于质检合格数量，请先修改条码数量");
                                return;
                            }
                            break;
                        case "finish_check"://成品入库(审核)
                            if (infoBean.getSend_qty() < infoBean.getArrive_qty()) {
                                showNotDialog("料品（" + infoBean.getItem_name() + "）数量大于入库数量，请先修改条码数量");
                                return;
                            }
                        case "finish_add"://成品入库(添加)
                            if (infoBean.getSend_qty() < infoBean.getArrive_qty()) {
                                showNotDialog("料品（" + infoBean.getItem_name() + "）入库数量大于可入库数量，请先修改条码数量");
                                return;
                            }
                            break;
                        case "out_buy_add"://采购退货(添加)
                            if (infoBean.getSend_qty() < infoBean.getArrive_qty()) {
                                showNotDialog("料品（" + infoBean.getItem_name() + "）配货数量大于申请数量，请先修改条码数量");
                                return;
                            }
                            break;
                        case "out_buy_detail"://采购退货(核定)
                            if (infoBean.getSend_qty() < infoBean.getArrive_qty()) {
                                showNotDialog("料品（" + infoBean.getItem_name() + "）核定数量大于配货数量，请先修改条码数量");
                                return;
                            }
                            break;
                        case "out_sale_add"://销售出库(新增)
                            if (infoBean.getSend_qty() < infoBean.getArrive_qty()) {
                                showNotDialog("料品（" + infoBean.getItem_name() + "）配货数量大于计划数量，请先修改条码数量");
                                return;
                            }

                            break;
                        case "in_transfer_add"://调拨入库(新增)
                            if (infoBean.getSend_qty() < infoBean.getArrive_qty()) {
                                showNotDialog("料品（" + infoBean.getItem_name() + "）调出数量大于申请数量，请先修改条码数量");
                                return;
                            }

                            break;
                        case "out_transfer_add"://调拨出库(新增)
                            if (infoBean.getSend_qty() < infoBean.getArrive_qty()) {
                                showNotDialog("料品（" + infoBean.getItem_name() + "）配货数量大于调拨数量，请先修改条码数量");
                                return;
                            }
                            break;
                        case "pro_return_point"://生产退料(点收)
                        case "pro_return_in"://生产退料(入库)
                        case "pro_receive_point":
                        case "pro_receive_out":
                            if (infoBean.getSend_qty() < infoBean.getArrive_qty()) {
                                showNotDialog("料品（" + infoBean.getItem_name() + "）核定数量大于申请数量，请先修改条码数量");
                                return;
                            }
                            break;
                    }
                }
                EventBus.getDefault().post(new FirstEvent("24", new ScanListBean(adapter.getAllData())));
                EventBus.getDefault().post(new FirstEventTwo("24", new ScanListBean(adapter.getAllData())));
                EventBus.getDefault().post(new FirstEvent(type, new ScanInBuyBean(allData)));
                EventBus.getDefault().post(new FirstEventTwo(type, new ScanInBuyBean(allData)));
                EventBus.getDefault().post(new FirstEvent("scan_finish"));
                finish();
                break;
        }
    }


    //扫码过多弹框
    private void showNotDialog(String result) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("提示");
        builder.setMessage(result);
        builder.setCancelable(false);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.create().show();

    }

}
