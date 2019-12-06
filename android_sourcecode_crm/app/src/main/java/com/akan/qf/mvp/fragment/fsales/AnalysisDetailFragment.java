package com.akan.qf.mvp.fragment.fsales;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.akan.qf.R;
import com.akan.qf.bean.SaleDataContrastBean;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.base.SimpleFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by admin on 2018/11/20.
 */

public class AnalysisDetailFragment extends SimpleFragment {


    Unbinder unbinder;
    @BindView(R.id.rbDJ)
    RadioButton rbDJ;
    @BindView(R.id.rbYT)
    RadioButton rbYT;
    @BindView(R.id.rg)
    RadioGroup rg;
    @BindView(R.id.viewPager)
    ViewPager viewPager;

    private SaleDataContrastBean bean;

    public static AnalysisDetailFragment newInstance(SaleDataContrastBean bean) {
        Bundle args = new Bundle();
        AnalysisDetailFragment fragment = new AnalysisDetailFragment();
        fragment.bean = bean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_analysis_detail;
    }

    @Override
    public void initUI() {

        ArrayList<BaseFragment> list = new ArrayList<>();
        list.add(new AnalysisOneFragment().newInstance(bean));
        list.add(new AnalysisTwoFragment().newInstance(bean));
        ViewAdapter viewAdapter = new ViewAdapter(getChildFragmentManager(), list);
        viewPager.setAdapter(viewAdapter);
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (0 == position) {
                    rbDJ.setChecked(true);
                } else {
                    rbYT.setChecked(true);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    class ViewAdapter extends FragmentPagerAdapter {
        private List<BaseFragment> datas;

        public ViewAdapter(FragmentManager fm, ArrayList<BaseFragment> list) {
            super(fm);
            datas = list;
        }

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Fragment getItem(int position) {
            return datas.get(position);
        }


    }

    @Override
    public void initData() {

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

    @OnClick({R.id.rbDJ, R.id.rbYT, R.id.ivLeft})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rbDJ:
                viewPager.setCurrentItem(0);
                break;
            case R.id.rbYT:
                viewPager.setCurrentItem(1);
                break;
            case R.id.ivLeft:
                finish();
                break;

        }
    }
}
