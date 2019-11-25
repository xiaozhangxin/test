package com.ak.pt.mvp.fragment.adaily;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioGroup;

import com.ak.pt.R;
import com.ak.pt.bean.AppPermissionsBean;
import com.ak.pt.mvp.base.PureActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by admin on 2018/10/22.
 */

public class SignActivity extends PureActivity {



    private RadioGroup rg;
    private SignFragment signFragment;
    private SignRecordFragment signRecordFragment;
    private AppPermissionsBean permissionsBean;

    @Override
    public int getRootViewId() {
        return R.layout.activity_sign;
    }

    @Override
    public void initUI() {
        permissionsBean = (AppPermissionsBean) getIntent().getSerializableExtra("permissions");
        rg = (RadioGroup) findViewById(R.id.rg);
        if (!permissionsBean.getApp_operation().contains("0")) {
            rg.setVisibility(View.GONE);
            showsignRecordFragment();
        } else {
            showsignFragment();
        }
    }


    //签到
    public void showsignFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hideAllFragment(fragmentTransaction);
        if (signFragment == null) {
            signFragment = SignFragment.newInstance(permissionsBean);
            fragmentTransaction.add(R.id.fragmentContent, signFragment);
        }
        commitShowFragment(fragmentTransaction, signFragment);
    }

    //签到记录
    public void showsignRecordFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hideAllFragment(fragmentTransaction);
        if (signRecordFragment == null) {
            signRecordFragment = SignRecordFragment.newInstance(permissionsBean);
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
