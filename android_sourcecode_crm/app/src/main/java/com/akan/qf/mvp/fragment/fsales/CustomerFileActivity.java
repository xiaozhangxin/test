package com.akan.qf.mvp.fragment.fsales;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.akan.qf.R;
import com.akan.qf.bean.ArchivesApplyBean;
import com.akan.qf.mvp.base.PureActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by admin on 2018/11/7.
 */

public class CustomerFileActivity extends PureActivity {


    @BindView(R.id.fragmentContent)
    FrameLayout fragmentContent;
    @BindView(R.id.sign)
    RadioButton sign;
    @BindView(R.id.signRecord)
    RadioButton signRecord;
    @BindView(R.id.rg)
    RadioGroup rg;
    private CustomerFileAddFragment signFragment;
    private CustomerFileListFragment signRecordFragment;


    @Override
    public int getRootViewId() {
        return R.layout.activity_reimburse;
    }

    @Override
    public void initUI() {
        showsignFragment();
    }


    //签到
    public void showsignFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hideAllFragment(fragmentTransaction);
        if (signFragment == null) {
            signFragment = CustomerFileAddFragment.newInstance(new ArchivesApplyBean(),"0");
            fragmentTransaction.add(R.id.fragmentContent, signFragment);
        }
        commitShowFragment(fragmentTransaction, signFragment);
    }

    //签到记录
    public void showsignRecordFragment() {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hideAllFragment(fragmentTransaction);
        if (signRecordFragment == null) {
            signRecordFragment = CustomerFileListFragment.newInstance();
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

