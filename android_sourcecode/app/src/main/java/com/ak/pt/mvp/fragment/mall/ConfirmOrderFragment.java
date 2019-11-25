package com.ak.pt.mvp.fragment.mall;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ak.pt.Constants;
import com.ak.pt.R;
import com.ak.pt.bean.AddressBean;
import com.ak.pt.bean.FirstEventAddress;
import com.ak.pt.bean.GoodsSpecificationBeans;
import com.ak.pt.bean.UserBean;
import com.ak.pt.mvp.base.BaseFragment;
import com.ak.pt.mvp.presenter.mall.MallOrderPresenter;
import com.ak.pt.mvp.view.mall.IMallOrderView;
import com.ak.pt.util.CustomDialog;
import com.ak.pt.util.SpSingleInstance;
import com.ak.pt.util.ToastUtil;
import com.bumptech.glide.Glide;
import com.githang.statusbar.StatusBarCompat;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by admin on 2019/5/6.
 */

public class ConfirmOrderFragment extends BaseFragment<IMallOrderView, MallOrderPresenter> implements IMallOrderView {


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
    @BindView(R.id.tvAddressNull)
    TextView tvAddressNull;
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
    @BindView(R.id.jian)
    TextView jian;
    @BindView(R.id.count)
    TextView count;
    @BindView(R.id.jia)
    TextView jia;
    @BindView(R.id.tvOne)
    TextView tvOne;
    @BindView(R.id.tvTwo)
    TextView tvTwo;
    @BindView(R.id.tvThree)
    TextView tvThree;
    @BindView(R.id.tvFive)
    EditText tvFive;
    @BindView(R.id.totalNum)
    TextView totalNum;
    @BindView(R.id.totalPrice)
    TextView totalPrice;
    @BindView(R.id.confirmPrice)
    TextView confirmPrice;
    @BindView(R.id.ok)
    TextView ok;
    @BindView(R.id.llBottom)
    LinearLayout llBottom;
    Unbinder unbinder;

    private GoodsSpecificationBeans spBean;
    private String num;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private String mAddress_id = "";
    private int amount = 1;//数量
    private int cks = 1;//库存

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            StatusBarCompat.setStatusBarColor(getActivity(), getResources().getColor(R.color.white));
        } else {
            getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    public static ConfirmOrderFragment newInstance(GoodsSpecificationBeans spBean, String num) {
        Bundle args = new Bundle();
        ConfirmOrderFragment fragment = new ConfirmOrderFragment();
        fragment.spBean = spBean;
        fragment.num = num;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_confirm_order;
    }

    @Override
    public void initUI() {
        tvTitle.setText("确认订单");
        cks = spBean.getSpecification_stock();
        amount = Integer.parseInt(num);
        Glide.with(context).load(Constants.BASE_URL + spBean.getSpecification_img())
                .error(R.drawable.error_img).into(goodsImg);
        //   goodsName.setText(spBean.getSpecification_names());
        goodsNorm.setText(spBean.getSpecification_names());
        goodsPrice.setText(spBean.getSpecification_price());
        //goodsNum.setText(num);
        float v = Float.parseFloat(num);
        float v1 = Float.parseFloat(spBean.getSpecification_price());
        count.setText(num);
        totalNum.setText("共" + num + "件");
        totalPrice.setText(v * v1 + "");
        confirmPrice.setText(v * v1 + "");
        count.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String s1 = s.toString();

                if (!TextUtils.isEmpty(s1)) {
                    int i = Integer.parseInt(s1);
                    float v2 = i * Float.parseFloat(spBean.getSpecification_price());
                    totalPrice.setText(v2 + "");
                    confirmPrice.setText(v2 + "");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        map.put("staff_id", userBean.getStaff_id());
        getPresenter().getDefaultAddress(userBean.getStaff_token(), map);
    }


    @OnClick({R.id.ivLeft, R.id.rlAddress, R.id.jian, R.id.jia, R.id.ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.rlAddress:
                startAddressFragment();
                break;
            case R.id.jian:
                amount = Integer.parseInt(count.getText().toString().trim());
                amount--;
                if (amount < 1) {
                    amount = 1;
                    ToastUtil.showToast(getActivity().getApplicationContext(), "数量不能小于1");
                }
                count.setText(String.valueOf(amount));
                totalNum.setText("共" + String.valueOf(amount) + "件");
                break;
            case R.id.jia:
                amount = Integer.parseInt(count.getText().toString().trim());
                amount++;
                if (amount > cks) {
                    amount--;
                    ToastUtil.showToast(getActivity().getApplicationContext(), "不能大于库存");
                }
                count.setText(String.valueOf(amount));
                totalNum.setText("共" + String.valueOf(amount) + "件");
                break;
            case R.id.ok:
                if (TextUtils.isEmpty(mAddress_id)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请添加收货地址");
                    return;
                }
                final CustomDialog.Builder builder = new CustomDialog.Builder(context);
                builder.setMessage("是否确认兑换此商品？");
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        ok.setEnabled(false);
                        map.clear();
                        map.put("staff_id", userBean.getStaff_id());
                        map.put("address_id", mAddress_id);
                        map.put("goods_id", spBean.getGoods_id());
                        map.put("specification_id", spBean.getSpecification_id());
                        map.put("goods_num", count.getText().toString());
                        map.put("order_desc", tvFive.getText().toString());
                        getPresenter().insertOrder(userBean.getStaff_token(), map);
                        dialog.dismiss();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.onCreate().show();


                break;
        }
    }

    @Override
    public void onGetDefaultAddress(AddressBean data) {
        if (data == null || TextUtils.isEmpty(data.getAddress_id())) {
          //  ToastUtil.showToast(context.getApplicationContext(), "请添加收货地址");
        } else {
            mAddress_id = data.getAddress_id();
            tvAddressNull.setVisibility(View.GONE);
            addressName.setVisibility(View.VISIBLE);
            addressPhone.setVisibility(View.VISIBLE);
            addressLocal.setVisibility(View.VISIBLE);
            addressNameTittle.setVisibility(View.VISIBLE);
            addressLocalTittle.setVisibility(View.VISIBLE);
            addressName.setText(data.getAddress_name());
            addressPhone.setText(data.getAddress_mobile());
            addressLocal.setText(data.getAddress_province() + data.getAddress_city()
                    + data.getAddress_district() + data.getAddress_detail());
        }

    }

    @Override
    public void onInsertOrder(String data) {
        ToastUtil.showToast(context.getApplicationContext(), "兑换成功");
        startOrderCompleteFragment(data);
        finish();
    }


    @Override
    public MallOrderPresenter createPresenter() {
        return new MallOrderPresenter(getApp());
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
    public void onEventMainThread(FirstEventAddress event) {
        switch (event.getType()) {
            case "address":
                AddressBean addressBean = event.getAddressBean();
                mAddress_id = addressBean.getAddress_id();
                mAddress_id = addressBean.getAddress_id();
                tvAddressNull.setVisibility(View.GONE);
                addressName.setVisibility(View.VISIBLE);
                addressPhone.setVisibility(View.VISIBLE);
                addressLocal.setVisibility(View.VISIBLE);
                addressNameTittle.setVisibility(View.VISIBLE);
                addressLocalTittle.setVisibility(View.VISIBLE);
                addressName.setText(addressBean.getAddress_name());
                addressPhone.setText(addressBean.getAddress_mobile());
                addressLocal.setText(addressBean.getAddress_province() + addressBean.getAddress_city()
                        + addressBean.getAddress_district() + addressBean.getAddress_detail());
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
        ok.setEnabled(true);
        ToastUtil.showToast(context.getApplicationContext(), e.getMessage());

    }


}