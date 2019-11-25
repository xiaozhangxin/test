package com.ak.pt.mvp.fragment.mall;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ak.pt.Constants;
import com.ak.pt.R;
import com.ak.pt.bean.OrderBean;
import com.ak.pt.bean.UserBean;
import com.ak.pt.mvp.base.BaseFragment;
import com.ak.pt.mvp.presenter.OrderMallPresenter;
import com.ak.pt.mvp.view.IOrderMallView;
import com.ak.pt.util.CustomDialog;
import com.ak.pt.util.SpSingleInstance;
import com.ak.pt.util.ToastUtil;
import com.bumptech.glide.Glide;
import com.githang.statusbar.StatusBarCompat;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by admin on 2019/5/7.
 */

public class OrderDetailFragmrnt extends BaseFragment<IOrderMallView, OrderMallPresenter> implements IOrderMallView {
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
    @BindView(R.id.addressNameTittle)
    TextView addressNameTittle;
    @BindView(R.id.addressName)
    TextView addressName;
    @BindView(R.id.addressPhone)
    TextView addressPhone;
    @BindView(R.id.addressLocalTittle)
    TextView addressLocalTittle;
    @BindView(R.id.addressLocal)
    TextView addressLocal;
    @BindView(R.id.imgRight)
    ImageView imgRight;
    @BindView(R.id.rlAddress)
    RelativeLayout rlAddress;
    @BindView(R.id.goodsImg)
    ImageView goodsImg;
    @BindView(R.id.goodsName)
    TextView goodsName;
    @BindView(R.id.goodsNorm)
    TextView goodsNorm;
    @BindView(R.id.goodsPrice)
    TextView goodsPrice;
    @BindView(R.id.goodsNum)
    TextView goodsNum;
    @BindView(R.id.tvOne)
    TextView tvOne;
    @BindView(R.id.tvTwo)
    TextView tvTwo;
    @BindView(R.id.tvThree)
    TextView tvThree;
    @BindView(R.id.tvFive)
    TextView tvFive;
    @BindView(R.id.totalNum)
    TextView totalNum;
    @BindView(R.id.totalPrice)
    TextView totalPrice;
    @BindView(R.id.tvOrderNo)
    TextView tvOrderNo;
    @BindView(R.id.tvOrderTime)
    TextView tvOrderTime;
    @BindView(R.id.tvBottomOne)
    TextView tvBottomOne;
    @BindView(R.id.tvBottomTwo)
    TextView tvBottomTwo;
    @BindView(R.id.tvBottomThree)
    TextView tvBottomThree;
    @BindView(R.id.tvSendTime)
    TextView tvSendTime;
    @BindView(R.id.tvLogisticsNo)
    TextView tvLogisticsNo;
    Unbinder unbinder;
    @BindView(R.id.llGoods)
    LinearLayout llGoods;
    @BindView(R.id.llReamrk)
    LinearLayout llReamrk;
    @BindView(R.id.llLogistics)
    LinearLayout llLogistics;
    private String order_id;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            StatusBarCompat.setStatusBarColor(getActivity(), getResources().getColor(R.color.white));
        } else {
            getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    public static OrderDetailFragmrnt newInstance(String order_id) {
        Bundle args = new Bundle();
        OrderDetailFragmrnt fragment = new OrderDetailFragmrnt();
        fragment.order_id = order_id;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_order_detail;
    }

    @Override
    public void initUI() {
        tvTitle.setText("订单详情");
    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        map.put("order_id", order_id);
        getPresenter().getOrderDetail(userBean.getStaff_token(), map);
    }

    private OrderBean mOrderBean;

    @Override
    public void onGetOrderDetail(OrderBean data) {
        mOrderBean = data;
        switch (data.getOrder_state()) {
            case "wait_send":
                tvBottomTwo.setVisibility(View.GONE);
                break;
            case "wait_receive":
                tvBottomTwo.setVisibility(View.VISIBLE);
                tvSendTime.setVisibility(View.VISIBLE);
                llLogistics.setVisibility(View.VISIBLE);
                tvLogisticsNo.setText("(" + data.getLogistics_name()
                        + ")" + data.getLogistics_no());
                tvSendTime.setText("发货时间：" + data.getSend_time());
                break;
            case "end":
                tvBottomTwo.setVisibility(View.GONE);
                tvSendTime.setVisibility(View.VISIBLE);
                llLogistics.setVisibility(View.VISIBLE);
                tvLogisticsNo.setText("(" + data.getLogistics_name()
                        + ")" + data.getLogistics_no());
                tvSendTime.setText("发货时间：" + data.getSend_time());
                break;
        }
        addressName.setText(data.getAddress_name());
        addressPhone.setText(data.getAddress_mobile());
        addressLocal.setText(data.getAddress_province() + data.getAddress_city()
                + data.getAddress_district() + data.getAddress_detail());
        Glide.with(context).load(Constants.BASE_URL + data.getGoods_img())
                .error(R.drawable.error_img).into(goodsImg);
        goodsName.setText(data.getGoods_name());
        goodsNorm.setText(data.getSpecification_names());
        goodsPrice.setText(data.getSpecification_price());
        totalPrice.setText(data.getOrder_actual_price());
        goodsNum.setText(data.getGoods_num() + "");
        if (TextUtils.isEmpty(data.getOrder_remark())) {
            llReamrk.setVisibility(View.GONE);
        } else {
            tvFive.setText(data.getOrder_remark());
        }

        goodsNum.setText("x" + data.getGoods_num());
        totalNum.setText("共" + data.getGoods_num() + "件");
        tvOrderNo.setText("订单编号：" + data.getOrder_no());
        tvOrderTime.setText("订单创建时间：" + data.getCreate_time());

    }

    @Override
    public void onConfirmOrder(String data) {
        ToastUtil.showToast(context.getApplicationContext(), data);
        map.clear();
        map.put("order_id", order_id);
        getPresenter().getOrderDetail(userBean.getStaff_token(), map);
    }


    @OnClick({R.id.ivLeft, R.id.tvBottomOne, R.id.tvBottomTwo, R.id.tvBottomThree, R.id.llGoods, R.id.tvLogisticsNo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvLogisticsNo:
                startWeb("查看物流", "https://m.kuaidi100.com/index_all.html?type=" + mOrderBean.getLogistics_name() + "&postid=" + mOrderBean.getLogistics_no());
                break;
            case R.id.llGoods:
                startGoodsDetailFragment(mOrderBean.getGoods_id());
                break;
            case R.id.tvBottomOne:
                final CustomDialog.Builder builder1 = new CustomDialog.Builder(context);
                builder1.setMessage("是否确认拨打客服电话?");
                builder1.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        sq(getString(R.string.mall_phone));
                        dialog.dismiss();
                    }
                });
                builder1.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder1.onCreate().show();
                break;
            case R.id.tvBottomTwo:
                final CustomDialog.Builder builder = new CustomDialog.Builder(context);
                builder.setMessage("是否确认收货？");
                builder.setPositiveButton("是", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        map.clear();
                        map.put("order_id", mOrderBean.getOrder_id());
                        map.put("logistics_no", mOrderBean.getLogistics_name());
                        map.put("logistics_name", mOrderBean.getLogistics_name());
                        getPresenter().confirmOrder(userBean.getStaff_token(), map);
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("否", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.onCreate().show();

                break;
            case R.id.tvBottomThree:
                break;
        }
    }

    private void sq(String phone) {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // 没有获得授权，申请授权
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.CALL_PHONE)) {
                ToastUtil.showToast(context.getApplicationContext(), getString(R.string.authorized));
                Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", context.getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
            } else {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, 1);
            }
        } else {
            CallPhone(phone);
        }
    }

    private void CallPhone(String phone) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL); // 设置动作
        Uri data = Uri.parse("tel:" + phone); // 设置数据
        intent.setData(data);
        startActivity(intent); // 激活Activity组件
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
    public void OnQueryMyOrderList(List<OrderBean> data) {

    }


    @Override
    public OrderMallPresenter createPresenter() {
        return new OrderMallPresenter(getApp());
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
