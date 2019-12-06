package com.akan.qf.mvp.fragment.qifei;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.qf.R;
import com.akan.qf.bean.UserBean;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.util.SpSingleInstance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by admin on 2018/11/23.
 */

public class TestPressureDetailFragment extends BaseFragment<IPressureView, PressurePresenter> implements IPressureView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.one)
    TextView one;
    @BindView(R.id.tvOne)
    TextView tvOne;
    @BindView(R.id.two)
    TextView two;
    @BindView(R.id.tvTwo)
    TextView tvTwo;
    @BindView(R.id.three)
    TextView three;
    @BindView(R.id.tvThree)
    TextView tvThree;
    @BindView(R.id.four)
    TextView four;
    @BindView(R.id.tvFour)
    TextView tvFour;
    @BindView(R.id.five)
    TextView five;
    @BindView(R.id.tvFive)
    TextView tvFive;
    @BindView(R.id.six)
    TextView six;
    @BindView(R.id.tvSix)
    TextView tvSix;
    @BindView(R.id.oneQ)
    TextView oneQ;
    @BindView(R.id.tvOneQ)
    TextView tvOneQ;
    @BindView(R.id.twoQ)
    TextView twoQ;
    @BindView(R.id.tvTwoQ)
    TextView tvTwoQ;
    @BindView(R.id.threeQ)
    TextView threeQ;
    @BindView(R.id.tvThreeQ)
    TextView tvThreeQ;
    @BindView(R.id.fourQ)
    TextView fourQ;
    @BindView(R.id.tvfourQ)
    TextView tvfourQ;
    @BindView(R.id.oneW)
    TextView oneW;
    @BindView(R.id.tvoneW)
    TextView tvoneW;
    @BindView(R.id.twoW)
    TextView twoW;
    @BindView(R.id.tvtwoW)
    TextView tvtwoW;
    @BindView(R.id.threeW)
    TextView threeW;
    @BindView(R.id.tvthreeW)
    TextView tvthreeW;
    @BindView(R.id.oneE)
    TextView oneE;
    @BindView(R.id.tvOneE)
    TextView tvOneE;
    @BindView(R.id.twoE)
    TextView twoE;
    @BindView(R.id.tvTwoE)
    TextView tvTwoE;
    @BindView(R.id.threeE)
    TextView threeE;
    @BindView(R.id.tvThreeE)
    TextView tvThreeE;
    @BindView(R.id.fourE)
    TextView fourE;
    @BindView(R.id.tvFourE)
    TextView tvFourE;
    @BindView(R.id.fiveE)
    TextView fiveE;
    @BindView(R.id.tvFiveE)
    TextView tvFiveE;
    @BindView(R.id.sixE)
    TextView sixE;
    @BindView(R.id.tvsixE)
    TextView tvsixE;
    @BindView(R.id.sevenE)
    TextView sevenE;
    @BindView(R.id.tvsevenE)
    TextView tvsevenE;
    @BindView(R.id.eightE)
    TextView eightE;
    @BindView(R.id.tveightE)
    TextView tveightE;
    @BindView(R.id.nineE)
    TextView nineE;
    @BindView(R.id.tvnineE)
    TextView tvnineE;
    @BindView(R.id.oneR)
    TextView oneR;
    @BindView(R.id.tvOneR)
    TextView tvOneR;
    @BindView(R.id.twoR)
    TextView twoR;
    @BindView(R.id.tvTwoR)
    TextView tvTwoR;
    @BindView(R.id.threeR)
    TextView threeR;
    @BindView(R.id.tvThreeR)
    TextView tvThreeR;
    @BindView(R.id.fourR)
    TextView fourR;
    @BindView(R.id.tvFourR)
    TextView tvFourR;
    @BindView(R.id.fiveR)
    TextView fiveR;
    @BindView(R.id.tvfiveR)
    TextView tvfiveR;
    Unbinder unbinder;
    @BindView(R.id.tvNoTittle)
    TextView tvNoTittle;
    @BindView(R.id.tvNo)
    TextView tvNo;
    @BindView(R.id.tvNoTwoTittle)
    TextView tvNoTwoTittle;
    @BindView(R.id.tvNoTwo)
    TextView tvNoTwo;
    @BindView(R.id.tvNameTittle)
    TextView tvNameTittle;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvTimeTittle)
    TextView tvTimeTittle;
    @BindView(R.id.tvTime)
    TextView tvTime;
    private PressurePageBean beanNow;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private String doc_id;

    public static TestPressureDetailFragment newInstance(String doc_id) {
        Bundle args = new Bundle();
        TestPressureDetailFragment fragment = new TestPressureDetailFragment();
        fragment.doc_id = doc_id;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_pressure_detail;
    }

    @Override
    public void initUI() {
        tvTitle.setText("施工单详情");


    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        map.put("DocNo", doc_id);
        getPresenter().queryDetail(userBean.getStaff_token(), map);
    }

    @Override
    public void OnQueryDetail(PressurePageBean bean) {
        beanNow = bean;
        tvNo.setText(bean.getDoc_no());
        tvNoTwo.setText(bean.getPressure_code());
        tvName.setText(bean.getStaff_name());
        tvTime.setText(bean.getTrn_date());

        tvOne.setText(bean.getAddress());
        tvTwo.setText(bean.getHouse_area_name());
        tvThree.setText(bean.getOwner_name());
        tvFour.setText(bean.getOwner_tel());
        tvFive.setText(bean.getScene_contact());
        tvSix.setText(bean.getScene_contact_tel());

        tvOneQ.setText(bean.getDecoration_company());
        tvTwoQ.setText(bean.getDistributor_name());
        tvThreeQ.setText(bean.getProject_manager());
        tvfourQ.setText(bean.getProject_manager_tel());

        tvoneW.setText(bean.getQuality_card());
        tvoneW.setText(bean.getIntegral_tel());
        tvoneW.setText(bean.getIntegral_score());

        tvOneE.setText(bean.getHydraulic_name());
        tvTwoE.setText(bean.getHydraulic_tel());
        tvThreeE.setText(bean.getBook_time());
        tvFourE.setText(bean.getAttention2());
        tvFiveE.setText(bean.getKitchen());
        tvsixE.setText(bean.getToilet());
        tvsevenE.setText(bean.getBalcony());
        tveightE.setText(bean.getPipe_type());
        tvnineE.setText(bean.getPipe_brand());

        tvOneR.setText(bean.getPlumber_name());
        tvTwoR.setText(bean.getPlumber_tel());
        tvThreeR.setText(bean.getDescription());
        tvFourR.setText(bean.getPressure_pressure());
        if (bean.getPhoto_count() > 0) {
            tvfiveR.setText("已上传");
            tvfiveR.setTextColor(getResources().getColor(R.color.red));
        } else {
            tvfiveR.setText("未上传");
            tvfiveR.setTextColor(getResources().getColor(R.color.colorTextG6));
        }
    }

    @Override
    public void OnQueryBigAreaCountPressurePage(List<BigAreaBean> data) {

    }

    @OnClick({R.id.ivLeft, R.id.tvfiveR})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvfiveR:
                if ("已上传".equals(tvfiveR.getText().toString())) {
                    startOrderImgTwoFragment(beanNow.getBaseTypePhotos(), "图片");
                }

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
    public PressurePresenter createPresenter() {
        return new PressurePresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void OnQueryAreaCountPressurePage(List<AreaPressureBean> data) {

    }

    @Override
    public void OnQueryPressurePage(List<PressurePageBean> data) {

    }


}
