package com.akan.wms.mvp.fragment.in;

import android.app.AlertDialog;
import android.app.Dialog;
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
import com.akan.wms.bean.FirstEvent;
import com.akan.wms.bean.LineListBean;
import com.akan.wms.bean.ReturnLineBean;
import com.akan.wms.bean.SaleRcvBean;
import com.akan.wms.bean.SaleReturnBean;
import com.akan.wms.bean.UserBean;
import com.akan.wms.bean.WareHouseBean;
import com.akan.wms.mvp.adapter.home.InSaleReturnAddAdapter;
import com.akan.wms.mvp.base.BaseFragment;
import com.akan.wms.mvp.presenter.home.InSalePresenter;
import com.akan.wms.mvp.view.home.IInSaleView;
import com.akan.wms.util.LoadingUtil;
import com.akan.wms.util.SpSingleInstance;
import com.akan.wms.util.ToastUtil;
import com.google.gson.Gson;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class InSaleReturnAddFragment  extends BaseFragment<IInSaleView, InSalePresenter> implements IInSaleView {

    Unbinder unbinder;
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
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
    @BindView(R.id.lineOne)
    View lineOne;
    @BindView(R.id.tvFour)
    TextView tvFour;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.llAdd)
    LinearLayout llAdd;


    private List<SaleReturnBean> list;
    private  InSaleReturnAddAdapter adapter;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private String mDoc_type_id = "";
    private int mPosition;
    private int mChildPosition;
    private int mDeletePosition;
    private String mCustomer_id;//客户id

    public static InSaleReturnAddFragment newInstance() {
        Bundle args = new Bundle();
        InSaleReturnAddFragment fragment = new InSaleReturnAddFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_in_sale_add;
    }

    @Override
    public void initUI() {
        tvTitle.setText("新增销售退货单");
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("完成");
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new InSaleReturnAddAdapter(context, list);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //startChooseOutPlanChildFragment(adapter.getItem(position), "1");
            }
        });
        adapter.setOnStockListener(new InSaleReturnAddAdapter.onSelectStockClickListener() {
            @Override
            public void onSelect(int position, int childPosition, String itemId) {
                //影藏软键盘
                hideInputMethod();
                //选择仓库
                mPosition = position;
                mChildPosition=childPosition;
                startChooseDeportFragment();
            }

            @Override
            public void onDelete(int position) {
                mDeletePosition = position;
                showDialog("删除");
            }

            @Override
            public void onScan(int position) {

            }
        });

    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        tvOne.setText(userBean.getStaff_name());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
        Date curDate = new Date(System.currentTimeMillis());
        String str = formatter.format(curDate);
        tvTwo.setText(str);
    }


    @OnClick({R.id.ivLeft, R.id.tvRight, R.id.tvFour, R.id.llAdd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvRight:
                if (adapter.getAllData().size() <= 0) {
                    ToastUtil.showToast(context.getApplicationContext(), "请添加出货计划");
                    return;
                }
                showDialog("完成");
                break;
            case R.id.tvFour://选择单据类型
               // startChooseSaleTypeFragment();
                break;
            case R.id.llAdd:
                startChooseSaleReturnListFragment();
                break;
        }
    }


    //操作确认弹框
    private void showDialog(final String type) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("提示");
        switch (type) {
            case "完成":
                builder.setMessage("是否确认完成？");
                break;
            case "删除":
                builder.setMessage("是否确认删除？");
                break;
        }

        builder.setCancelable(true);
        builder.setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (type) {
                    case "完成":
                        toCommit();
                        break;
                    case "删除":
                        adapter.remove(mDeletePosition);
                        mCustomer_id = "";
                        tvThree.setText("");
                        break;
                }

            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();

    }

    //提交数据
    private void toCommit() {
        List<SaleReturnBean> allData = adapter.getAllData();
        SaleReturnBean returnBean = allData.get(0);
        map.clear();
        map.put("org_id", userBean.getOrg_id());
        map.put("sale_return_id", returnBean.getId()+"");
        map.put("sale_return_no", returnBean.getDoc_no());
        map.put("customer_id", mCustomer_id);
        map.put("customer_name", tvThree.getText().toString());

        List<ReturnLineBean> lineBeans = returnBean.getReturnLineBeans();
        List<LineListBean> mList = new ArrayList<>();
        for (int j = 0; j < lineBeans.size(); j++) {
            ReturnLineBean bean = lineBeans.get(j);
            if (bean.getSend_qty() <= 0) {
                ToastUtil.showToast(context.getApplicationContext(), "请输入配货数量");
                return;
            }

            if (bean.getReturn_qty() != bean.getSend_qty()) {
                ToastUtil.showToast(context.getApplicationContext(), "配货数量不等于申请数量");
                return;
            }
            LineListBean mBean = new LineListBean();
            mBean.setSale_return_line_id(bean.getId() + "");
            mBean.setItem_id(bean.getItem_id() + "");
            mBean.setWh_id(bean.getWh_id() + "");
            mBean.setArrive_qty(bean.getSend_qty() + "");
            mList.add(mBean);

        }
        Gson gson = new Gson();
        String json = gson.toJson(mList);
        map.put("lineList", json);
       // getPresenter().addSaleRcv(userBean.getStaff_token(), map);
    }


    @Override
    public InSalePresenter createPresenter() {
        return new InSalePresenter(getApp());
    }





    private Dialog loading;
    @Override
    public void showProgress() {
        loading = LoadingUtil.createLoading(getActivity(), "加载中...");

    }

    @Override
    public void onCompleted() {
        LoadingUtil.closeDialog(loading);
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
        EventBus.getDefault().register(this);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(FirstEvent event) {
        String msg = event.getmMsg();
        switch (msg) {
/*            case "1"://选择单据类型
                SaleShipTypeBean typeBean = event.getmBean();
                tvFour.setText(typeBean.getName());
                mDoc_type_id = typeBean.getId();
                break;*/
            case "13"://选择退货申请
                SaleReturnBean sBean = event.getmSaleReturnBean();
                tvThree.setText(sBean.getCustomer_name());
                mCustomer_id = sBean.getCustomer_id() + "";
                adapter.clear();
                adapter.add(sBean);
                adapter.notifyDataSetChanged();
                break;
            case "6"://选择仓库
                WareHouseBean houseBean = event.getmWareHouseBean();
                adapter.getItem(mPosition).getReturnLineBeans().get(mChildPosition).setWh_name(houseBean.getWarehouse_name());
                adapter.getItem(mPosition).getReturnLineBeans().get(mChildPosition).setWh_id(houseBean.getWarehouse_id());
                adapter.notifyItemChanged(mPosition);
                break;
        }
    }



    @Override
    public void onQuerySaleRcvPage(List<SaleRcvBean> data) {

    }

    @Override
    public void OnQuerySaleRcv(SaleRcvBean data) {

    }



    @Override
    public void OnWhSaleRcv(String data) {

    }


}
