package com.akan.qf.mvp.fragment.bapproval;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.qf.R;
import com.akan.qf.bean.FilterBean;
import com.akan.qf.bean.FinancialBean;
import com.akan.qf.bean.FirstEventFilter;
import com.akan.qf.bean.PermissionsBean;
import com.akan.qf.bean.UserBean;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.presenter.home.CostStatisticsPresenter;
import com.akan.qf.mvp.view.home.ICostStatisticsView;
import com.akan.qf.util.SpSingleInstance;
import com.akan.qf.util.ToastUtil;
import com.akan.qf.view.TopMiddlePopup;
import com.king.base.util.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by admin on 2019/2/27.
 */

public class CostStatisticsDetailFragmrnt extends BaseFragment<ICostStatisticsView, CostStatisticsPresenter> implements ICostStatisticsView {

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
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvJobName)
    TextView tvJobName;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvState)
    TextView tvState;
    @BindView(R.id.tvDepartment)
    TextView tvDepartment;
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
    @BindView(R.id.tvNine)
    TextView tvNine;
    @BindView(R.id.tvTen)
    TextView tvTen;
    @BindView(R.id.tvEleven)
    TextView tvEleven;
    @BindView(R.id.tvTwelve)
    TextView tvTwelve;
    @BindView(R.id.tvThirteen)
    TextView tvThirteen;
    @BindView(R.id.tvEight)
    TextView tvEight;
    @BindView(R.id.tvFourteen)
    TextView tvFourteen;
    @BindView(R.id.tvFifteen)
    TextView tvFifteen;
    @BindView(R.id.tvSixteen)
    TextView tvSixteen;
    @BindView(R.id.tvSeventeen)
    TextView tvSeventeen;
    @BindView(R.id.tvEighteen)
    TextView tvEighteen;
    @BindView(R.id.tvNineteen)
    TextView tvNineteen;
    @BindView(R.id.tvTwenty)
    TextView tvTwenty;
    @BindView(R.id.tvTwentyOne)
    TextView tvTwentyOne;
    @BindView(R.id.tvTwentyTwo)
    TextView tvTwentyTwo;
    @BindView(R.id.tvAddOne)
    TextView tvAddOne;


    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private String detail_id;
    private FinancialBean bean;

    private PermissionsBean permissionsBean;

    public static CostStatisticsDetailFragmrnt newInstance(String detail_id, PermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        CostStatisticsDetailFragmrnt fragment = new CostStatisticsDetailFragmrnt();
        fragment.detail_id = detail_id;
        fragment.permissionsBean = permissionsBean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_coststatistics_detail;
    }

    @Override
    public void initUI() {
        tvTitle.setText(R.string.cost_statistics_details);
    }

    @Override
    public void initData() {

    }


    @Override
    public void onResume() {
        super.onResume();
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        map.clear();
        map.put("apply_id", detail_id);
        getPresenter().getFinancialStatistics(userBean.getStaff_token(), map);
    }


    @Override
    public void OnGetFinancialStatistics(FinancialBean data) {
        bean = data;
        //appOperation  0新增 1编辑 2删除 3审核 4导出
        String appOperation = permissionsBean.getApp_operation();
        String[] strings = appOperation.split(",");
        if (data.getStaff_id().equals(userBean.getStaff_id())) {
            tvRight.setVisibility(View.VISIBLE);
            if (isHave("2", strings)) {
                tvRight.setText(R.string.more);
            } else {
                tvRight.setText(R.string.edit);
            }
        }else {
            if (isHave("1", strings)&&isHave("2", strings)) {
                tvRight.setVisibility(View.VISIBLE);
                tvRight.setText(R.string.more);

            } else if (isHave("1", strings)) {
                tvRight.setVisibility(View.VISIBLE);
                tvRight.setText(R.string.edit);

            } else if (isHave("2", strings)) {
                tvRight.setVisibility(View.VISIBLE);
                tvRight.setText(R.string.delete);
            } else {
                tvRight.setVisibility(View.GONE);
            }
        }
        tvJobName.setText(data.getJob_name());
        tvName.setText(data.getStaff_name());
        tvNo.setText(data.getApply_no());
        tvTime.setText(data.getApply_create_time());
        tvDepartment.setText(data.getStaff_department());

        tvOne.setText(data.getApply_department());
        tvTwo.setText(data.getApply_year());
        tvThree.setText(data.getApply_month());

        tvFour.setText(data.getApply_income());
        tvFive.setText(data.getApply_others());
        tvSix.setText(data.getApply_cost());
        tvSeven.setText(data.getApply_claimant());
        tvEight.setText(data.getApply_rate());//费用率
        tvNine.setText(data.getApply_ring());

        tvTen.setText(data.getApply_total());
        tvEleven.setText(data.getApply_with());
        tvTwelve.setText(data.getApply_real());
        tvThirteen.setText(data.getApply_find());
        tvFourteen.setText(data.getApply_market());
        tvFifteen.setText(data.getApply_person());
        tvSixteen.setText(data.getApply_carry());
        tvSeventeen.setText(data.getApply_office());
        tvEighteen.setText(data.getApply_entertain());
        tvNineteen.setText(data.getApply_advert());
        tvTwenty.setText(data.getApply_depreciation());
        tvTwentyOne.setText(data.getApply_account());
        tvTwentyTwo.setText(data.getApply_remark());
        tvAddOne.setText(data.getApply_pay());
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


    @OnClick({R.id.ivLeft, R.id.tvRight})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvRight:
                switch (tvRight.getText().toString()) {
                    case "更多":
                        showList();
                        break;
                    case "编辑":
                        startCostStatisticsAddFragment(bean, "1", permissionsBean);
                        break;
                    case "删除":
                        toDelete();
                        break;
                }
                break;

        }
    }

    //删除编辑选择框
    private AlertDialog alertDialog;

    public void showList() {
        final String[] items = {"编辑", "删除"};
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i) {
                    case 0:
                        startCostStatisticsAddFragment(bean, "1", permissionsBean);
                        break;
                    case 1:
                        toDelete();
                        break;
                }
                alertDialog.dismiss();
            }
        });
        alertDialog = alertBuilder.create();
        alertDialog.show();
    }

    //单据删除
    private void toDelete() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(R.string.delete_order);
        builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                map.clear();
                map.put("apply_id", detail_id);
                map.put("is_select", "0");
                map.put("is_app", "1");
                map.put("operation", "2");
                map.put("module_id", permissionsBean.getMenu_id());
                getPresenter().deleteFinancialStatistics(userBean.getStaff_token(), map);
            }
        });
        builder.setNegativeButton(getString(R.string.cancel), null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
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
    public CostStatisticsPresenter createPresenter() {
        return new CostStatisticsPresenter(getApp());
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


    @Override
    public void OnInsertOrUpdateStatistics(String data) {

    }

    @Override
    public void OnGetFinancialStatisticsList(List<FinancialBean> data, String total) {

    }


    @Override
    public void OnDeleteFinancialStatistics(String data) {
        EventBus.getDefault().post(new FirstEventFilter("cost_delete"));
        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();
    }
}

