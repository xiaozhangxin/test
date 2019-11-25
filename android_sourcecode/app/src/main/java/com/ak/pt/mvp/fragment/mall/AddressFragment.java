package com.ak.pt.mvp.fragment.mall;

import android.os.Build;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ak.pt.R;
import com.ak.pt.bean.AddressBean;
import com.ak.pt.bean.FirstEventAddress;
import com.ak.pt.bean.UserBean;
import com.ak.pt.mvp.adapter.AddressListAdapter;
import com.ak.pt.mvp.base.BaseFragment;
import com.ak.pt.mvp.presenter.AddressPresenter;
import com.ak.pt.mvp.view.IAddressView;
import com.ak.pt.util.SpSingleInstance;
import com.ak.pt.util.ToastUtil;
import com.githang.statusbar.StatusBarCompat;
import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;


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
 * Created by admin on 2019/5/7.
 */

public class AddressFragment extends BaseFragment<IAddressView, AddressPresenter> implements IAddressView {


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
    EasyRecyclerView recycleView;
    @BindView(R.id.tvEmpty)
    TextView tvEmpty;
    @BindView(R.id.tvAddAddress)
    Button tvAddAddress;
    Unbinder unbinder;
    private UserBean userBean;
    private Map<String, String> map = new HashMap<>();

    private AddressListAdapter adapter;
    private List<AddressBean> list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            StatusBarCompat.setStatusBarColor(getActivity(), getResources().getColor(R.color.white));
        } else {
            getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    public static AddressFragment newIntance() {
        Bundle args = new Bundle();
        AddressFragment fragment = new AddressFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_mall_address;
    }

    @Override
    public void initUI() {
        tvTitle.setText("收货地址");
        list = new ArrayList<>();
        adapter = new AddressListAdapter(context, list);
        recycleView.setLayoutManager(new LinearLayoutManager(getContext()));
        recycleView.setAdapter(adapter);
        adapter.setNoMore(R.layout.view_nomore);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                EventBus.getDefault().post(new FirstEventAddress("address", adapter.getItem(position)));
                finish();
            }
        });
        adapter.setOnOnEditClickListener(new AddressListAdapter.OnEditClickListener() {
            @Override
            public void edit(int position) {
                startAddressAddFragment(adapter.getItem(position), "1");
            }
        });
    }


    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        map.put("staff_id", userBean.getStaff_id());
        getPresenter().getMemberAddressList(userBean.getStaff_token(), map);

    }

    @Override
    public void onResume() {
        super.onResume();
        map.put("staff_id", userBean.getStaff_id());
        getPresenter().getMemberAddressList(userBean.getStaff_token(), map);
    }

    @OnClick({R.id.ivLeft, R.id.tvAddAddress})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvAddAddress:
                startAddressAddFragment(new AddressBean(), "0");
                break;
        }
    }

    @Override
    public void OnGetMemberAddressList(List<AddressBean> data) {
        adapter.clear();
        adapter.addAll(data);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onInsertUpdateAddress(String data) {

    }

    @Override
    public void onDeleteAddress(String data) {

    }

    @Override
    public void onSetDefaultAddress(String data) {

    }

    @Override
    public AddressPresenter createPresenter() {
        return new AddressPresenter(getApp());
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
