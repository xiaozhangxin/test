package com.ak.pt.mvp.fragment.statistics;

import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ak.pt.R;
import com.ak.pt.bean.PhotoListBean;
import com.ak.pt.bean.UserBean;
import com.ak.pt.mvp.base.BaseFragment;
import com.ak.pt.util.SpSingleInstance;
import com.ak.pt.util.ToastUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by admin on 2019/3/18.
 */

public class OrderImgTwoFragment extends BaseFragment<IOrderImgView, OrderImgPresenter> implements IOrderImgView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    Unbinder unbinder;
    private List<PhotoListBean> imgList;
    private ImgTwoAdapter numAdapter;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;

    public static OrderImgTwoFragment newInstance(List<PhotoListBean> imgList) {
        Bundle args = new Bundle();
        OrderImgTwoFragment fragment = new OrderImgTwoFragment();
        fragment.imgList = imgList;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_order_img_two;
    }

    @Override
    public void initUI() {
        tvTitle.setText(R.string.pressure_img);
        recycleView.setLayoutManager(new LinearLayoutManager(context));
        numAdapter = new ImgTwoAdapter(context, imgList);
        numAdapter.setOnLongTimeDeleteClickListener(new ImgTwoAdapter.OnLongTimeDeleteClickListener() {
            @Override
            public void onLongDelete(final int position, final int childPosition) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage(R.string.sure_delete_img);
                builder.setPositiveButton(getString(R.string.sure), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String seq_no = numAdapter.getItem(position).getPhotos().get(childPosition).getSeq_no();
                        map.clear();
                        map.put("seq_no", seq_no);
                        getPresenter().deletePiptbFixPhoto(userBean.getStaff_token(), map);
                        numAdapter.getItem(position).getPhotos().remove(childPosition);
                        numAdapter.notifyItemChanged(position);
                    }
                });
                builder.setNegativeButton(getString(R.string.cancel), null);
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });
        recycleView.setAdapter(numAdapter);
    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
    }

    @OnClick({R.id.ivLeft})
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
    public OrderImgPresenter createPresenter() {
        return new OrderImgPresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void OnDeletePiptbFixPhoto(String data) {
        ToastUtil.showToast(context.getApplicationContext(),data);

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
}

