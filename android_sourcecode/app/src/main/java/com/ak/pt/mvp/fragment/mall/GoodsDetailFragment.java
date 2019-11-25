package com.ak.pt.mvp.fragment.mall;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ak.pt.Constants;
import com.ak.pt.R;
import com.ak.pt.bean.DetailImgBean;
import com.ak.pt.bean.FirstEvent;
import com.ak.pt.bean.GoodsBean;
import com.ak.pt.bean.GoodsImgBeans;
import com.ak.pt.bean.GoodsSpecificationBeans;
import com.ak.pt.bean.UserBean;
import com.ak.pt.mvp.adapter.GoodsDetailImgAdapter;
import com.ak.pt.mvp.base.BaseFragment;
import com.ak.pt.mvp.presenter.mall.GoodsDetailPresenter;
import com.ak.pt.mvp.view.mall.IGoodsDetailView;
import com.ak.pt.util.CustomDialog;
import com.ak.pt.util.SpSingleInstance;
import com.ak.pt.util.ToastUtil;
import com.bumptech.glide.Glide;
import com.githang.statusbar.StatusBarCompat;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.zhouwei.mzbanner.MZBannerView;
import com.zhouwei.mzbanner.holder.MZHolderCreator;
import com.zhouwei.mzbanner.holder.MZViewHolder;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by admin on 2019/5/6.
 */

public class GoodsDetailFragment extends BaseFragment<IGoodsDetailView, GoodsDetailPresenter> implements IGoodsDetailView {
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
    @BindView(R.id.tvName)
    TextView tvName;
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
    @BindView(R.id.tvGoodsRemake)
    TextView tvGoodsRemake;
    @BindView(R.id.tvOk)
    TextView tvOk;
    Unbinder unbinder;
    @BindView(R.id.detailBanner)
    MZBannerView banner;
    @BindView(R.id.llBottom)
    LinearLayout llBottom;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    private ArrayList<String> list;
    private Map<String, String> map = new HashMap<>();
    private Map<String, String> map1 = new HashMap<>();
    private UserBean userBean;
    private String goods_id;
    private GoodsBean mBean;
    private String mIntegral = "";
    private List<DetailImgBean> listImg;
    private GoodsDetailImgAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            StatusBarCompat.setStatusBarColor(getActivity(), getResources().getColor(R.color.white));
        } else {
            getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    public static GoodsDetailFragment newInstance(String goods_id) {
        Bundle args = new Bundle();
        GoodsDetailFragment fragment = new GoodsDetailFragment();
        fragment.goods_id = goods_id;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_goods_detail;
    }

    @Override
    public void initUI() {
        tvTitle.setText("商品详情");
        list = new ArrayList<>();

        listImg = new ArrayList<>();
        recycleView.setNestedScrollingEnabled(false);
        recycleView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new GoodsDetailImgAdapter(context, listImg);
        recycleView.setAdapter(adapter);

/*        banner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<GoodsImgBeans> goodsImgBeans = mBean.getGoodsImgBeans();
                for (int i = 0; i < goodsImgBeans.size(); i++) {
                    list.add(Constants.BASE_URL + goodsImgBeans.get(i).getImg_url());
                }
                Intent intent = new Intent(getActivity(), ShowPictureActivity.class);
                intent.putExtra("imagelist", (Serializable) list);
                intent.putExtra("position", 0);
                getActivity().startActivity(intent);
            }
        });*/
    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        map.put("goods_id", goods_id);
        getPresenter().getGoodsDetail(userBean.getStaff_token(), map);

    }


    @Override
    public void onGetGoodsDetail(GoodsBean data) {
        mBean = data;
        //商品banner
        list.clear();
        List<GoodsImgBeans> goodsImgBeans = data.getGoodsImgBeans();
        for (int i = 0; i < goodsImgBeans.size(); i++) {
            list.add(Constants.BASE_URL + goodsImgBeans.get(i).getImg_url());
        }
        banner.setPages(list, new MZHolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });
        banner.start();

        //详情
        tvName.setText(data.getGoods_name());
        tvOne.setText(data.getGoods_now_price());
        tvFour.setText(data.getGoods_stock() + "");
        tvGoodsRemake.setText(data.getGoods_desc());
        adapter.clear();
        String goods_url = data.getGoods_url();
        if (!goods_url.equals("") && goods_url.length() > 0) {
            Gson gson = new Gson();
            try {
                JsonArray array = new JsonParser().parse(goods_url).getAsJsonArray();
                for (JsonElement elem : array) {
                    DetailImgBean usern = gson.fromJson(elem, DetailImgBean.class);
                    adapter.add(usern);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onGetWaterIntegral(String data) {
        mIntegral = data;
    }

    @Override
    public void onPause() {
        super.onPause();
        banner.pause();//暂停轮播
    }

    @Override
    public void onResume() {
        super.onResume();
        map1.put("staff_id", userBean.getStaff_id());
        getPresenter().getWaterIntegral(userBean.getStaff_token(), map1);
        banner.start();//开始轮播
    }

    @OnClick({R.id.ivLeft, R.id.tvOk})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvOk:
                if (mBean != null) {
                    startChooseGoodsNormFragment();
                }
                break;
        }
    }

    private void startChooseGoodsNormFragment() {
        ChooseGoodsNormFragment fragment = ChooseGoodsNormFragment.newInstance(mBean);
        fragment.setOnOkClickListener(new ChooseGoodsNormFragment.OnOkClickListener() {
            @Override
            public void ok(final GoodsSpecificationBeans spBean, final String goodsNum) {

                float v = Float.parseFloat(goodsNum);
                float v1 = Float.parseFloat(spBean.getSpecification_price());
                if (!TextUtils.isEmpty(mIntegral)) {
                    float v2 = Float.parseFloat(mIntegral);
                    if (v2 > v * v1) {
                        startConfirmOrderFragment(spBean, goodsNum);
                    } else {
                        final CustomDialog.Builder builder = new CustomDialog.Builder(context);
                        builder.setTitle("提示");
                        builder.setMessage("您的积分不足以兑换此物品");
                        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        builder.onCreate().show();
                    }
                }
            }

        });

        fragment.show(getFragmentManager(), GoodsDetailFragment.class.getSimpleName());
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


    public static class BannerViewHolder implements MZViewHolder<String> {
        private ImageView mImageView;

        @Override
        public View createView(Context context) {
            // 返回页面布局
            View view = LayoutInflater.from(context).inflate(R.layout.banner_item, null);
            mImageView = (ImageView) view.findViewById(R.id.banner_image);
            return view;
        }

        @Override
        public void onBind(Context context, int i, String s) {
            Glide.with(context).load(s).error(R.drawable.img_goods).into(mImageView);
        }

    }


    @Override
    public GoodsDetailPresenter createPresenter() {
        return new GoodsDetailPresenter(getApp());
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


    private String worker_id = "";

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(FirstEvent event) {
        switch (event.getMsg()) {
            case "toMall":
                finish();
                break;
        }

    }
}