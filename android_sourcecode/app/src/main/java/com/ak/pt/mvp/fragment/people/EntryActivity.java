package com.ak.pt.mvp.fragment.people;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioGroup;

import com.ak.pt.R;
import com.ak.pt.bean.AppPermissionsBean;
import com.ak.pt.bean.StaffApplyBean;
import com.ak.pt.mvp.base.PureActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class EntryActivity extends PureActivity {


    private RadioGroup mRadioGroup;
    private EntryAddFragment signFragment;
    private EntryListFragment signRecordFragment;


    @Override
    public int getRootViewId() {
        return R.layout.activity_public;
    }

    private AppPermissionsBean permissionsBean;

    @Override
    public void initUI() {
        permissionsBean = (AppPermissionsBean) getIntent().getSerializableExtra("permissions");
        mRadioGroup = (RadioGroup) findViewById(R.id.rg);
        if (isHave("0", permissionsBean.getApp_operation().split(","))) {
            showsignFragment();
        } else {
            mRadioGroup.setVisibility(View.GONE);
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
            signFragment = EntryAddFragment.newInstance(new StaffApplyBean(), "0", permissionsBean);
            fragmentTransaction.add(R.id.fragmentContent, signFragment);
        }
        commitShowFragment(fragmentTransaction, signFragment);
    }

    //签到记录
    public void showsignRecordFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hideAllFragment(fragmentTransaction);
        if (signRecordFragment == null) {
            signRecordFragment = EntryListFragment.newInstance(permissionsBean);
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

