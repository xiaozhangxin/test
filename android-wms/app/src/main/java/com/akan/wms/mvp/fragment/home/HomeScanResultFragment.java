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
import com.akan.wms.bean.BarMsgBean;
import com.akan.wms.bean.UserBean;
import com.akan.wms.mvp.adapter.home.HomeScanChildListAdapter;
import com.akan.wms.mvp.adapter.home.HomeScanListAdapter;
import com.akan.wms.mvp.base.SimpleFragment;
import com.akan.wms.util.ToastUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class HomeScanResultFragment extends SimpleFragment {


    Unbinder unbinder;
    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.ivImg)
    ImageView ivImg;
    @BindView(R.id.tvTopCode)
    TextView tvTopCode;
    @BindView(R.id.tvTopName)
    TextView tvTopName;
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
    @BindView(R.id.tvSix)
    TextView tvSix;
    @BindView(R.id.tvSeven)
    TextView tvSeven;
    @BindView(R.id.tvEight)
    TextView tvEight;
    @BindView(R.id.tvNine)
    TextView tvNine;
    @BindView(R.id.tvTen)
    TextView tvTen;
    @BindView(R.id.tvEleven)
    TextView tvEleven;
    @BindView(R.id.tvTwelve)
    TextView tvTwelve;
    @BindView(R.id.tvThirteen)
    TextView tvThirteen;
    @BindView(R.id.tvForteen)
    TextView tvForteen;@BindView(R.id.empty)
    TextView empty;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private BarMsgBean scanBean;

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

    public static HomeScanResultFragment newInstance(BarMsgBean scanBean) {
        Bundle args = new Bundle();
        HomeScanResultFragment fragment = new HomeScanResultFragment();
        fragment.scanBean = scanBean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_home_scan;
    }

    @Override
    public void initUI() {



        if (scanBean == null) {
            ToastUtil.showToast(getContext().getApplicationContext(), "查询条码有误,清重新扫码");
            return;
        }

        BarMsgBean.LinfoBean bean = scanBean.getLinfo();

        tvTopCode.setText(bean.getQuery_code());
        tvTopName.setText(bean.getItem_name());
        tvOne.setText(bean.getParent_code());
        tvTwo.setText(bean.getLabel_code());
        tvFour.setText(bean.getInspector());
        tvFive.setText(bean.getItem_name());
        tvSix.setText(bean.getItem_cat_name());
        tvSeven.setText(bean.getItem_spec());
        tvEight.setText(bean.getWork_shop());
        tvNine.setText(bean.getProduct_line());
        tvTen.setText(bean.getWork_team());
        tvEleven.setText(bean.getCreate_time());
        tvTwelve.setText(bean.getOrg_name());
        tvThirteen.setText(bean.getBar_code());
        tvForteen.setText(bean.getNamed_custom());

        if (scanBean.getLogistics().size()<=0){
            empty.setVisibility(View.VISIBLE);

        }else {
            empty.setVisibility(View.GONE);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new HomeScanListAdapter(context, scanBean.getLogistics()));
        }

    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.ivBack, R.id.tvThree})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivBack:
                finish();
                break;
            case R.id.tvThree:

                startHomeScanChildResultFragment(scanBean.getBarCodes());
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
