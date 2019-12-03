package com.akan.wms.mvp.fragment.out;

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
import com.akan.wms.bean.DefineValueBean;
import com.akan.wms.bean.FirstEvent;
import com.akan.wms.bean.InfoBeanListBean;
import com.akan.wms.bean.ItemInfoBean;
import com.akan.wms.bean.MfcBean;
import com.akan.wms.bean.MiscShipBean;
import com.akan.wms.bean.MiscShipDocTypeBean;
import com.akan.wms.bean.OperatorBean;
import com.akan.wms.bean.StaffGroupBean;
import com.akan.wms.bean.StaffOrgsBean;
import com.akan.wms.bean.UserBean;
import com.akan.wms.bean.WareHouseBean;
import com.akan.wms.mvp.adapter.home.ProduceReturnAdapter;
import com.akan.wms.mvp.base.BaseFragment;
import com.akan.wms.mvp.presenter.home.ProduceReceivePresenter;
import com.akan.wms.mvp.view.home.IProduceReceiveView;
import com.akan.wms.util.LoadingUtil;
import com.akan.wms.util.SpSingleInstance;
import com.akan.wms.util.ToastUtil;
import com.akan.wms.util.keyback.FragmentBackHandler;
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

public class ProduceReceiveAddFragment extends BaseFragment<IProduceReceiveView, ProduceReceivePresenter> implements IProduceReceiveView, FragmentBackHandler {

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
    @BindView(R.id.tvFour)
    TextView tvFour;
    @BindView(R.id.tvFive)
    TextView tvFive;
    @BindView(R.id.tvSix)
    TextView tvSix;
    @BindView(R.id.tvSeven)
    TextView tvSeven;
    @BindView(R.id.tvEight)
    EditText tvEight;
    @BindView(R.id.tvDelete)
    TextView tvDelete;
    @BindView(R.id.llbg)
    LinearLayout llbg;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.llAdd)
    LinearLayout llAdd;
    @BindView(R.id.tvFiveAdd)
    TextView tvFiveAdd;
    @BindView(R.id.tvSevenAdd)
    TextView tvSevenAdd;


    private List<ItemInfoBean> list;
    private ProduceReturnAdapter adapter;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private int mPosition;
    private String wh_man;
    private String doc_no;
    private String benefit_org;
    private String pub_desc_seg2;
    private String pub_desc_seg3;
    private String pub_desc_seg1;
    private String benefit_dept;
    private int mMfcPosition = 0;

    public static ProduceReceiveAddFragment newInstance() {
        Bundle args = new Bundle();
        ProduceReceiveAddFragment fragment = new ProduceReceiveAddFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_add_produce_receive;
    }

    @Override
    public void initUI() {
        tvTitle.setText("新增生产领料");
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText("完成");
        list = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new ProduceReturnAdapter(context, list);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemLongClickListener(new RecyclerArrayAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(int position) {
                showDeleteDialog(position);
                return false;
            }
        });

        adapter.setOnStockListener(new ProduceReturnAdapter.OnSelectClickListener() {
            @Override
            public void onChooseDeport(int position) {
                //选择仓库
                mPosition = position;
                startChooseDeportFragment();
            }

            @Override
            public void onChooseMfc(int position, String itemId) {
                mMfcPosition = position;
                startChooseMfcFragment(itemId);
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
        tvFive.setText(userBean.getOrg_name());
        benefit_org = userBean.getOrg_id();
    }


    @OnClick({R.id.ivLeft, R.id.tvRight, R.id.tvThree, R.id.tvFour, R.id.tvFive, R.id.tvSix, R.id.tvSeven, R.id.tvFiveAdd, R.id.tvSevenAdd, R.id.llAdd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                showCloseDialog();
                break;
            case R.id.tvRight:

                String mThree = tvThree.getText().toString();
                if (TextUtils.isEmpty(mThree)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择单据类型");
                    return;
                }
                String mFour = tvFour.getText().toString();
                if (TextUtils.isEmpty(mFour)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择库管员");
                    return;
                }
                String mFive = tvFive.getText().toString();
                if (TextUtils.isEmpty(mFive)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择受益组织");
                    return;
                }
                String mtvFiveAdd = tvFiveAdd.getText().toString();
                if (TextUtils.isEmpty(mtvFiveAdd)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择受益部门");
                    return;
                }
                String mSix = tvSix.getText().toString();
                if (TextUtils.isEmpty(mSix)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择使用中心");
                    return;
                }
                String mtvSevenAdd = tvSevenAdd.getText().toString();
                if (TextUtils.isEmpty(mtvSevenAdd)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择负责中心");
                    return;
                }
                String mSeven = tvSeven.getText().toString();
                if (TextUtils.isEmpty(mSeven)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择负责部门");
                    return;
                }

                List<ItemInfoBean> allData = adapter.getAllData();
                if (allData.size() <= 0) {
                    ToastUtil.showToast(context.getApplicationContext(), "请添加料品");
                    return;
                }
                showDialog(allData);
                break;
            case R.id.tvThree:
                startProduceChooseFragment("1");
                break;
            case R.id.tvFour:
                startProduceChooseFragment("2");
                break;
            case R.id.tvFive:
                startProduceChooseFragment("3");
                break;
            case R.id.tvSix:
                startProduceChooseFragment("4");
                break;
            case R.id.tvSeven:
                startProduceChooseFragment("5");
                break;
            case R.id.tvFiveAdd:
                startProduceChooseFragment("6");
                break;
            case R.id.tvSevenAdd:
                startProduceChooseFragment("7");
                break;
            case R.id.llAdd:
                startChooseGoodsMoreFragment();
                break;
        }
    }

    //删除料品弹框
    private void showDeleteDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("提示");
        builder.setMessage("是否确认移除料品(" + adapter.getItem(position).getItem_name() + ")？");
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


    //确认提交弹框
    private void showDialog(final List<ItemInfoBean> allData) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("提示");
        builder.setMessage("是否确认提交此单据？");
        builder.setCancelable(true);
        builder.setPositiveButton(R.string.sure, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                map.clear();
                map.put("memo", tvEight.getText().toString());//备注
                map.put("doc_no", doc_no);
                map.put("wh_man", wh_man);
                map.put("benefit_org", benefit_org);
                map.put("pub_desc_seg2", pub_desc_seg2);
                map.put("pub_desc_seg3", pub_desc_seg3);
                map.put("pub_desc_seg1", pub_desc_seg1);
                map.put("benefit_dept", benefit_dept);
                ArrayList<InfoBeanListBean> mList = new ArrayList<>();
                for (int i = 0; i < allData.size(); i++) {
                    ItemInfoBean adapterBean = allData.get(i);
                    if (TextUtils.isEmpty(adapterBean.getNum())) {
                        ToastUtil.showToast(context.getApplicationContext(), "请输入申请数量");
                        return;
                    }
                    InfoBeanListBean mBean = new InfoBeanListBean();
                    mBean.setItem_id(adapterBean.getItem_id());
                    mBean.setNumber(adapterBean.getNum());
                    mBean.setMark_code(adapterBean.getTrade_mark());
                    mBean.setInfo_wh_id(adapterBean.getWarehouse_id());
                    mList.add(mBean);
                }
                Gson gson = new Gson();
                String toJson = gson.toJson(mList);
                map.put("infoBeanList", toJson);
                getPresenter().insertMiscShip(userBean.getStaff_token(), map);
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
        String msg = event.getMsg();
        switch (msg) {
            case "2"://选择料品
                List<ItemInfoBean> list = event.getGoodsListBean().getList();
                adapter.addAll(list);
                break;
            case "6"://选择仓库
                WareHouseBean houseBean = event.getmWareHouseBean();
                adapter.getItem(mPosition).setWarehouse_name(houseBean.getWarehouse_name());
                adapter.getItem(mPosition).setWarehouse_id(houseBean.getWarehouse_id());
                adapter.notifyDataSetChanged();
                break;
            case "23"://选择厂牌
                MfcBean mfcBean = event.getmMfcBean();
                adapter.getItem(mMfcPosition).setTrade_mark_name(mfcBean.getMfc_name());
                adapter.getItem(mMfcPosition).setTrade_mark(mfcBean.getMfc());
                adapter.notifyDataSetChanged();
                break;
            case "8"://选择单据类型
                MiscShipDocTypeBean shipDocTypeBean = event.getmMiscShipDocTypeBean();
                tvThree.setText(shipDocTypeBean.getName());
                doc_no = shipDocTypeBean.getId() + "";
                break;
            case "9"://选择库管员
                OperatorBean operatorBean = event.getmOperatorBean();
                tvFour.setText(operatorBean.getOperator_name());
                wh_man = operatorBean.getOperator_id() + "";
                break;
            case "1"://选择组织
                StaffOrgsBean staffOrgsBean = event.getmBean();
                tvFive.setText(staffOrgsBean.getName());
                benefit_org = staffOrgsBean.getId();
                break;
            case "10"://选择使用中心
                DefineValueBean defineValueBean = event.getmDefineValueBean();
                tvSix.setText(defineValueBean.getDefine_name());
                pub_desc_seg2 = defineValueBean.getDefine_code() + "";
                break;
            case "11"://选择负责部门
                StaffGroupBean staffGroupBean = event.getmStaffGroupBean();
                tvSeven.setText(staffGroupBean.getGroup_name());
                pub_desc_seg3 = staffGroupBean.getU9_code() + "";
                break;
            case "12"://选择受益部门
                StaffGroupBean staffGroupBeanNew = event.getmStaffGroupBean();
                tvFiveAdd.setText(staffGroupBeanNew.getGroup_name());
                benefit_dept = staffGroupBeanNew.getU9_id() + "";
                break;
            case "13"://选择负责中心
                DefineValueBean defineValueBeanNew = event.getmDefineValueBean();
                tvSevenAdd.setText(defineValueBeanNew.getDefine_name());
                pub_desc_seg1 = defineValueBeanNew.getDefine_code() + "";
                break;

        }

    }


    @Override
    public ProduceReceivePresenter createPresenter() {
        return new ProduceReceivePresenter(getApp());
    }


    @Override
    public void onGetMiscShipList(List<MiscShipBean> data) {

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
    public void OnInsertMiscShip(String data) {
        ToastUtil.showToast(context.getApplicationContext(), data);
        EventBus.getDefault().post(new FirstEvent("refresh"));
        finish();
    }

    @Override
    public void OnGetMiscShipDetail(MiscShipBean data) {

    }

    @Override
    public void OnUpdateMiscShipCode(String data) {

    }

    @Override
    public void OnDeleteMiscShip(String data) {

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
