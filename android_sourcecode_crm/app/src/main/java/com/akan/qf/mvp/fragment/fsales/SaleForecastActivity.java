package com.akan.qf.mvp.fragment.fsales;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.akan.qf.R;
import com.akan.qf.bean.FinancialBean;
import com.akan.qf.bean.LableBean;
import com.akan.qf.bean.PermissionsBean;
import com.akan.qf.mvp.base.PureActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SaleForecastActivity extends PureActivity {


    private RadioGroup rg;
    private SaleForecastAddFragment signFragment;
    private SaleForecastListFragment signRecordFragment;


    @Override
    public int getRootViewId() {
        return R.layout.activity_public;
    }


    private PermissionsBean permissionsBean;

    private List<LableBean> signList;

    @Override
    public void initUI() {
        permissionsBean = (PermissionsBean) getIntent().getSerializableExtra("permissions");
        signList = (List<LableBean>) getIntent().getSerializableExtra("signList");
        rg = findViewById(R.id.rg);
        if (isHave("0", permissionsBean.getApp_operation().split(","))) {
            showsignFragment();
        } else {
            rg.setVisibility(View.GONE);
            showsignRecordFragment();
        }
    }

    public boolean isHave(String index, String[] split) {
        for (int i = 0; i < split.length; i++) {
            if (index.equals(split[i])) {
                return true;
            }
        }
        return false;
    }

    //签到
    public void showsignFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hideAllFragment(fragmentTransaction);
        if (signFragment == null) {
            signFragment = SaleForecastAddFragment.newInstance(new FinancialBean(), "0",permissionsBean);
            fragmentTransaction.add(R.id.fragmentContent, signFragment);
        }
        commitShowFragment(fragmentTransaction, signFragment);
    }

    //签到记录
    public void showsignRecordFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hideAllFragment(fragmentTransaction);
        if (signRecordFragment == null) {
            signRecordFragment = SaleForecastListFragment.newInstance(permissionsBean,signList);
            fragmentTransaction.add(R.id.fragmentContent, signRecordFragment);
        }
        commitShowFragment(fragmentTransaction, signRecordFragment);
    }

    public void hideAllFragment(FragmentTransaction fragmentTransaction) {
        hideFragment(fragmentTransaction, signFragment);
        hideFragment(fragmentTransaction, signRecordFragment);
    }

    private void hideFragment(FragmentTransaction fragmentTransaction, Fragment fragment) {
        if (fragment != null) {
            fragmentTransaction.hide(fragment);
        }
    }

    public void commitShowFragment(FragmentTransaction fragmentTransaction, Fragment fragment) {
        fragmentTransaction.show(fragment);
        fragmentTransaction.commit();
    }

    @OnClick({R.id.sign, R.id.signRecord})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.sign:
                showsignFragment();
                break;
            case R.id.signRecord:
                showsignRecordFragment();
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}

