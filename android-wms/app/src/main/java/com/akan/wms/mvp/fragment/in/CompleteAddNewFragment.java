package com.akan.wms.mvp.fragment.in;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.AddCompleteDecBean;
import com.akan.wms.bean.ClassTeamBean;
import com.akan.wms.bean.CompleteDecBean;
import com.akan.wms.bean.CompleteDecLinesBean;
import com.akan.wms.bean.FirstEvent;
import com.akan.wms.bean.ModNoBean;
import com.akan.wms.bean.OperatorStaffBean;
import com.akan.wms.bean.ProductionOrderBean;
import com.akan.wms.bean.UserBean;
import com.akan.wms.bean.WareHouseBean;
import com.akan.wms.mvp.adapter.home.CompleteAddAdapter;
import com.akan.wms.mvp.base.BaseFragment;
import com.akan.wms.mvp.presenter.home.CompletePresenter;
import com.akan.wms.mvp.view.home.ICompleteView;
import com.akan.wms.util.LoadingUtil;
import com.akan.wms.util.SpSingleInstance;
import com.akan.wms.util.ToastUtil;
import com.akan.wms.util.keyback.FragmentBackHandler;
import com.google.gson.Gson;

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

public class CompleteAddNewFragment extends BaseFragment<ICompleteView, CompletePresenter> implements ICompleteView, FragmentBackHandler {

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
    @BindView(R.id.tvSix)
    EditText tvSix;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.llAdd)
    LinearLayout llAdd;


    private List<ProductionOrderBean> list;
    private CompleteAddAdapter adapter;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private String transfer_direction = "1";//单据类型 默认挤出
    private int mDeletePosition;
    private int mOperatePosition;
    private int mGroupPosition;
    private int mCodePosition;
    private int mDeportPosition;
    private ProductionOrderBean produceBean;

    public static CompleteAddNewFragment newInstance(ProductionOrderBean produceBean) {
        Bundle args = new Bundle();
        CompleteAddNewFragment fragment = new CompleteAddNewFragment();
        fragment.setArguments(args);
        fragment.produceBean = produceBean;
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_complete_add;
    }

    @Override
    public void initUI() {
        tvTitle.setText("新增完工申报单");
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("提交");
        list = new ArrayList<>();
        list.add(produceBean);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new CompleteAddAdapter(context, list);
        adapter.setType("1");
        recyclerView.setAdapter(adapter);
        adapter.setOnStockListener(new CompleteAddAdapter.onSelectStockClickListener() {
            @Override
            public void onDelete(int position) {
                mDeletePosition = position;
                showDialog("删除");
            }

            @Override
            public void onOperate(int position) {
                mOperatePosition = position;
                startChooseCompleteParamFragment("1");
            }

            @Override
            public void onGroup(int position) {
                mGroupPosition = position;
                startChooseCompleteParamFragment("2");

            }

            @Override
            public void onDeport(int position) {
                mDeportPosition = position;
                startChooseDeportFragment();
            }

            @Override
            public void onCode(int position) {
                mCodePosition = position;
                startChooseCompleteParamFragment("3");
            }
        });
       // adapter.notifyDataSetChanged();


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


    @OnClick({R.id.ivLeft, R.id.tvRight, R.id.tvThree, R.id.llAdd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                showCloseDialog();
                break;
            case R.id.tvRight:
                if (TextUtils.isEmpty(tvThree.getText().toString())) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择单据类型");
                    return;
                }

                if (adapter.getAllData().size() <= 0) {
                    ToastUtil.showToast(context.getApplicationContext(), "请添加生产订单");
                    return;
                }
                showDialog("完成");
                break;
            case R.id.tvThree://选择单据类型
                showSingleDialog();
                break;
            case R.id.llAdd:
                String mType = tvThree.getText().toString();
                if (TextUtils.isEmpty(mType)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择单据类型");
                    return;
                }
                switch (mType) {
                    case "挤出":
                        adapter.setType("1");
                        break;
                    case "注塑":
                        adapter.setType("2");
                        break;
                }
                startCompleteChooseFragment(transfer_direction, "add");
                break;
        }
    }


    private AlertDialog alertDialog;
    private int choosePosition = 0;

    public void showSingleDialog() {
        final String[] items = {"挤出", "注塑"};
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setTitle("选择单据类型");
        alertBuilder.setSingleChoiceItems(items, choosePosition, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                choosePosition = i;
            }
        });
        alertBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                tvThree.setText(items[choosePosition]);
                if ("挤出".equals(items[choosePosition])) {
                    transfer_direction = "1";
                    adapter.setType("1");
                    adapter.notifyDataSetChanged();
                } else {
                    transfer_direction = "0";
                    adapter.setType("2");
                    adapter.notifyDataSetChanged();
                }
            }
        });

        alertBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        alertDialog = alertBuilder.create();
        alertDialog.show();

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

        AddCompleteDecBean mBean = new AddCompleteDecBean();
        mBean.setReport_type(transfer_direction);


        List<ProductionOrderBean> allData = adapter.getAllData();
        List<CompleteDecLinesBean> mList = new ArrayList<>();

        for (int j = 0; j < allData.size(); j++) {
            ProductionOrderBean orderBean = allData.get(j);
            if (TextUtils.isEmpty(orderBean.getOperator_Name())) {
                ToastUtil.showToast(context.getApplicationContext(), "请选择操作工");
                return;
            }
            if (TextUtils.isEmpty(orderBean.getClass_team_id())) {
                ToastUtil.showToast(context.getApplicationContext(), "请选择班组");
                return;
            }
            if (orderBean.getComplete_qty() <= 0) {
                ToastUtil.showToast(context.getApplicationContext(), "完工数量不能为0");
                return;
            }
            if ((orderBean.getQualified_qty() + orderBean.getScrap_qty()) <= 0) {
                ToastUtil.showToast(context.getApplicationContext(), "请输入合格数量和报废数量");
                return;
            }


            CompleteDecLinesBean mChildBean = new CompleteDecLinesBean();
            mChildBean.setId(orderBean.getId() + "");
            mChildBean.setPro_id(orderBean.getId() + "");

            mChildBean.setOperator_id(orderBean.getOperator_id());
            mChildBean.setClass_team_id(orderBean.getClass_team_id());
            mChildBean.setClass_team_name(orderBean.getClass_team_name());
            mChildBean.setMac_code(orderBean.getMac_code());
            mChildBean.setComplete_qty(orderBean.getApply_qty() + "");
            mChildBean.setQualified_qty(orderBean.getQualified_qty() + "");
            mChildBean.setScrap_qty(orderBean.getScrap_qty() + "");
            mChildBean.setScrap_weight(orderBean.getScrap_weight());

            mChildBean.setEve_weight(orderBean.getEve_weight());
            mChildBean.setSpeed(orderBean.getSpeed());
            mChildBean.setLabor_hours(orderBean.getLabor_hours());
            mChildBean.setMac_hours(orderBean.getMac_hours());
            mChildBean.setMac_pre_hours(orderBean.getMac_pre_hours());
            mChildBean.setWh_id(orderBean.getWh_id());


            mChildBean.setMod_code(orderBean.getMod_code());//模具编号
            mChildBean.setMod_weight(orderBean.getMod_weight());
            mChildBean.setEle_weight(orderBean.getEle_weight());
            mChildBean.setMod_outline_weight(orderBean.getMod_outline_weight());
            mChildBean.setMod_inserts_weight(orderBean.getMod_inserts_weight());
            mChildBean.setBush_weight(orderBean.getBush_weight());
            mChildBean.setMod_amount(orderBean.getMod_amount());
            mChildBean.setEle_amount(orderBean.getEle_amount());

            mList.add(mChildBean);


        }
        mBean.setComplete_dec_lines(mList);
        Gson gson = new Gson();
        String json = gson.toJson(mBean);
        map.put("addCompleteDec", json);
        getPresenter().addCompleteDec(userBean.getStaff_token(), map);
    }


    @Override
    public CompletePresenter createPresenter() {
        return new CompletePresenter(getApp());
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
        LoadingUtil.closeDialog(loading);
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
            case "18":
                ProductionOrderBean produceBean = event.getmProductionOrderBean();
                List<ProductionOrderBean> allData = adapter.getAllData();
                for (int i = 0; i < allData.size(); i++) {
                    if (produceBean.getDoc_no().equals(allData.get(i).getDoc_no())) {
                        ToastUtil.showToast(context.getApplicationContext(), "此单据已被选择");
                        return;
                    }
                }
                adapter.add(produceBean);
                break;
            case "19"://选择操作员
                OperatorStaffBean operatorStaffBean = event.getmOperatorStaffBean();
                adapter.getItem(mOperatePosition).setOperator_id(operatorStaffBean.getStaff_id());
                adapter.getItem(mOperatePosition).setOperator_Name(operatorStaffBean.getStaff_name());
                adapter.notifyDataSetChanged();
                break;
            case "20"://选择班组
                ClassTeamBean teamBean = event.getmClassTeamBean();
                adapter.getItem(mGroupPosition).setClass_team_id(teamBean.getId());
                adapter.getItem(mGroupPosition).setClass_team_name(teamBean.getName());
                adapter.notifyDataSetChanged();
                break;
            case "21"://选择模具编号
                ModNoBean modNoBean = event.getmModNoBean();
                adapter.getItem(mCodePosition).setMod_code(modNoBean.getCode());
                adapter.getItem(mCodePosition).setMod_name(modNoBean.getName());
                adapter.notifyDataSetChanged();
                break;
            case "6"://选择仓库
                WareHouseBean houseBean = event.getmWareHouseBean();
                adapter.getItem(mDeportPosition).setWh_name(houseBean.getWarehouse_name());
                adapter.getItem(mDeportPosition).setWh_id(houseBean.getWarehouse_id());
                adapter.notifyDataSetChanged();
                break;

        }
    }


    @Override
    public void onQueryCompleteDecList(List<CompleteDecBean> data) {

    }

    @Override
    public void onQueryCompleteDec(CompleteDecBean data) {

    }

    @Override
    public void onAddCompleteDec(String data) {
        ToastUtil.showToast(context.getApplicationContext(), "添加成功");
        EventBus.getDefault().post(new FirstEvent("refresh"));
        finish();
    }

    @Override
    public void onPastCompleteDec(String data) {

    }

    @Override
    public void onInvalidCompleteDec(String data) {

    }

    @Override
    public void onDelCompleteDec(String data) {

    }


    @Override
    public boolean onBackPressed() {
        showCloseDialog();
        return true;
    }

    //操作确认弹框
    private void showCloseDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.point);
        builder.setMessage(R.string.close_sure);
        builder.setCancelable(true);
        builder.setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        builder.create().show();

    }
}
