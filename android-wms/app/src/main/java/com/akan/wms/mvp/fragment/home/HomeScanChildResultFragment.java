package com.akan.wms.mvp.fragment.home;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.mvp.adapter.home.CompleteListAdapter;
import com.akan.wms.mvp.adapter.home.HomeScanChildListAdapter;
import com.akan.wms.mvp.base.SimpleFragment;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class HomeScanChildResultFragment extends SimpleFragment {


    Unbinder unbinder;
    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.tvTittle)
    TextView tvTittle;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private List<String> scanChildCode;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        makeStatusBarTransparent(getActivity());
    }

    public static void makeStatusBarTransparent(Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            return;
        }
        Window window = activity.getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            int option = window.getDecorView().getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;
            window.getDecorView().setSystemUiVisibility(option);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else {
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    public static HomeScanChildResultFragment newInstance(List<String> scanChildCode) {
        Bundle args = new Bundle();
        HomeScanChildResultFragment fragment = new HomeScanChildResultFragment();
        fragment.scanChildCode = scanChildCode;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_home_scan_child;
    }

    @Override
    public void initUI() {
        tvTittle.setText("子码信息("+scanChildCode.size()+")");
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(new HomeScanChildListAdapter(context, scanChildCode));
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.ivBack})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivBack:
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
