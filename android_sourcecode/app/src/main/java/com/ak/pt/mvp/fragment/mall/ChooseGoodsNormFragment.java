package com.ak.pt.mvp.fragment.mall;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ak.pt.Constants;
import com.ak.pt.R;
import com.ak.pt.bean.GoodsBean;
import com.ak.pt.bean.GoodsSpecificationBeans;
import com.ak.pt.mvp.adapter.NormChooseAdapter;
import com.ak.pt.util.FlowLayoutManager;
import com.ak.pt.util.ToastUtil;
import com.ak.pt.util.img.ShowPictureActivity;
import com.ak.pt.view.FixHeightBottomSheetDialog;
import com.bilibili.boxing_impl.view.SpacesItemDecoration;
import com.bumptech.glide.Glide;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;


import org.greenrobot.eventbus.EventBus;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * Created by admin on 2019/5/6.
 */

public class ChooseGoodsNormFragment extends BottomSheetDialogFragment {


    @BindView(R.id.ivGoods)
    ImageView ivGoods;
    @BindView(R.id.tvIntegral)
    TextView tvIntegral;
    @BindView(R.id.tvNum)
    TextView tvNum;
    @BindView(R.id.tvChoose)
    TextView tvChoose;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.num)
    EditText num;
    Unbinder unbinder;
    @BindView(R.id.ivClose)
    ImageView ivClose;
    @BindView(R.id.tvRecycleViewTittle)
    TextView tvRecycleViewTittle;
    @BindView(R.id.view)
    View view;
    @BindView(R.id.textView3)
    TextView textView3;
    @BindView(R.id.jian)
    TextView jian;
    @BindView(R.id.jia)
    TextView jia;
    @BindView(R.id.tvCommit)
    TextView tvCommit;



    private int count = 1;//数量
    private int cks = 1;//库存
    private String mImg = "";//商品图片
    private NormChooseAdapter adapter;
    private GoodsBean mBean;

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        if (getContext() == null) {
            return super.onCreateDialog(savedInstanceState);
        }
        return new FixHeightBottomSheetDialog(getContext(),R.style.CommentBottomSheetStyle);
    }

    public static ChooseGoodsNormFragment newInstance(GoodsBean bean) {
        Bundle args = new Bundle();
        ChooseGoodsNormFragment fragment = new ChooseGoodsNormFragment();
        fragment.mBean = bean;
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_goods_norm, container, false);
        List<GoodsSpecificationBeans> sBeans = mBean.getGoodsSpecificationBeans();
        unbinder = ButterKnife.bind(this, view);

        for (int i = 0; i < sBeans.size(); i++) {
            sBeans.get(i).setCheck(false);
        }

        adapter = new NormChooseAdapter(getContext(), sBeans);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        FlowLayoutManager flowLayoutManager = new FlowLayoutManager(getContext(), false);
        recyclerView.addItemDecoration(new SpacesItemDecoration(10));
        recyclerView.setLayoutManager(flowLayoutManager);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (adapter.getItem(position).getSpecification_stock()==0){
                    return;
                }
                List<GoodsSpecificationBeans> allData = adapter.getAllData();
                for (int i = 0; i < allData.size(); i++) {
                    allData.get(i).setCheck(false);
                }
                adapter.getItem(position).setCheck(true);
                adapter.notifyDataSetChanged();
                setTopData(adapter.getItem(position));
            }
        });

        adapter.getItem(0).setCheck(true);
        setTopData(adapter.getItem(0));
        return view;
    }

    public void setTopData(GoodsSpecificationBeans spBean) {
        tvIntegral.setText(spBean.getSpecification_price());
        tvNum.setText("库存" + spBean.getSpecification_stock() + "件");
        tvChoose.setText("已选：" + spBean.getSpecification_names());
        mImg = spBean.getSpecification_img();
        Glide.with(getContext())
                .load(Constants.BASE_URL + spBean.getSpecification_img())
                .error(R.drawable.error_img)
                .into(ivGoods);
        cks = spBean.getSpecification_stock();
        if (cks <= 0) {
            tvNum.setText("库存0件");
        }
    }


    @OnClick({R.id.ivClose, R.id.jian, R.id.jia, R.id.tvCommit, R.id.ivGoods})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivClose:
                dismiss();
                break;

            case R.id.ivGoods:
                List<String> strings = new ArrayList<>();
                strings.add(mImg);
                Intent intent = new Intent(getActivity(), ShowPictureActivity.class);
                intent.putExtra("imagelist", (Serializable) strings);
                intent.putExtra("position", 0);
                getActivity().startActivity(intent);
                break;
            case R.id.jian:
                String sa = num.getText().toString();
                if (TextUtils.isEmpty(sa)) {
                    num.setText("0");
                    count = 0;
                } else {
                    count = Integer.parseInt(sa);
                }
                count--;
                if (count < 1) {
                    count = 1;
                    ToastUtil.showToast(getActivity().getApplicationContext(), "数量不能小于1");
                }
                num.setText(String.valueOf(count));
                break;
            case R.id.jia:
                String sa2 = num.getText().toString();
                if (TextUtils.isEmpty(sa2)) {
                    num.setText("0");
                    count = 0;
                } else {
                    count = Integer.parseInt(sa2);
                }
                count++;
                if (count > cks) {
                    count--;
                    ToastUtil.showToast(getActivity().getApplicationContext(), "不能大于库存");
                }
                num.setText(String.valueOf(count));
                break;
            case R.id.tvCommit:
                String goodsNum = num.getText().toString();
                if (TextUtils.isEmpty(goodsNum) || "0".equals(goodsNum)) {
                    ToastUtil.showToast(getContext().getApplicationContext(), "请选择购买数量");
                    return;
                }

                List<GoodsSpecificationBeans> allData = adapter.getAllData();
                for (int i = 0; i < allData.size(); i++) {
                    if (allData.get(i).isCheck()) {
                        if (0 == allData.get(i).getSpecification_stock()) {
                            ToastUtil.showToast(getContext().getApplicationContext(), "库存不足");
                            return;
                        }

                        onOkClickListener.ok(allData.get(i), goodsNum);
                        dismiss();
                        return;
                    }
                }
                ToastUtil.showToast(getContext().getApplicationContext(), "请选择分类");
                break;
        }
    }


    private OnOkClickListener onOkClickListener;

    public void setOnOkClickListener(OnOkClickListener onOkClickListener) {
        this.onOkClickListener = onOkClickListener;
    }

    public interface OnOkClickListener {
        void ok(GoodsSpecificationBeans spBean, String goodsNum);
    }


    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
        }

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
        unbinder.unbind();
    }

}
