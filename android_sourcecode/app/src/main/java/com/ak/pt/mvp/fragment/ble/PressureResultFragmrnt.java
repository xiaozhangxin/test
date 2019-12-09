package com.ak.pt.mvp.fragment.ble;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ak.pt.R;
import com.ak.pt.bean.FirstEvent;
import com.ak.pt.mvp.fragment.statistics.MemoryBean;
import com.ak.pt.mvp.fragment.statistics.PressurePageBean;
import com.ak.pt.bean.UserBean;
import com.ak.pt.mvp.base.BaseFragment;
import com.ak.pt.mvp.presenter.OrderPresenter;
import com.ak.pt.mvp.view.IOrderView;
import com.ak.pt.util.SpSingleInstance;
import com.ak.pt.util.ToastUtil;

import org.greenrobot.eventbus.EventBus;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by admin on 2019/3/19.
 */

public class PressureResultFragmrnt extends BaseFragment<IOrderView, OrderPresenter> implements IOrderView {

    Unbinder unbinder;
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
    @BindView(R.id.tvPhone)
    TextView tvPhone;
    @BindView(R.id.tvStartPt)
    TextView tvStartPt;
    @BindView(R.id.tvStartTime)
    TextView tvStartTime;
    @BindView(R.id.tvEndTime)
    TextView tvEndTime;
    @BindView(R.id.tvEndPt)
    TextView tvEndPt;
    @BindView(R.id.tvDown)
    TextView tvDown;
    @BindView(R.id.tvSix)
    TextView tvSix;
    @BindView(R.id.tvPt)
    TextView tvPt;
    @BindView(R.id.ok)
    Button ok;
    @BindView(R.id.tvAddTime)
    TextView tvAddTime;
    private String msg;

    private PressurePageBean ptBean;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;

    public static PressureResultFragmrnt newInstance(String msg, PressurePageBean ptBean) {
        Bundle args = new Bundle();
        PressureResultFragmrnt fragment = new PressureResultFragmrnt();
        fragment.msg = msg;
        fragment.ptBean = ptBean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_pt_result;
    }

    @Override
    public void initUI() {
        tvTitle.setText("测试结果");
        //  tvPhone.setText(ptBean.getHydraulic_tel());
        String pressure_pressure = ptBean.getPressure_pressure();
        tvPt.setText(pressure_pressure);//选

        // 择的压力值
        tvStartTime.setText(ptBean.getPressure_time());//试压开始时间
        tvEndTime.setText(ptBean.getPressure_complete_time());//试压结束时间
        tvAddTime.setText(ptBean.getPress_time());//试压时间
        tvStartPt.setText(ptBean.getPressure_start_value());//开始的压力值
        tvEndPt.setText(ptBean.getPressure_end_value());//结束压力值
        tvSix.setText(ptBean.getPressure_result());//测试结果

        float startP;
        float endP;
        if (TextUtils.isEmpty(ptBean.getPressure_start_value())) {
            startP = 0;
        } else {
            startP = Float.valueOf(ptBean.getPressure_start_value());
        }
        if (TextUtils.isEmpty(ptBean.getPressure_end_value())) {
            endP = 0;
        } else {
            endP = Float.valueOf(ptBean.getPressure_end_value());
        }
        DecimalFormat df = new DecimalFormat("00.00");//格式化小数，不足的补0
        String downSize = df.format(startP - endP);
        if (downSize.startsWith("00")){
            downSize=downSize.substring(1,downSize.length());
        }
        tvDown.setText(downSize);//压降

    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
    }

    @OnClick({R.id.ivLeft, R.id.ok})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.ok:
                ok.setEnabled(false);
                map.clear();
                map.put("staff_id", userBean.getStaff_id());
                map.put("is_weixin", "0");
                map.put("doc_no", ptBean.getDoc_no());
                map.put("pressure_time", ptBean.getPressure_time());//试压开始时间
                map.put("pressure_complete_time", ptBean.getPressure_complete_time());//试压结束时间
                map.put("pressure_pressure", ptBean.getPressure_pressure());//试压值
                map.put("pressure_drop", tvDown.getText().toString());//压降值
                map.put("last_pressure", ptBean.getPressure_end_value());//最终压力值
                map.put("press_time", ptBean.getPress_time());//第二阶段试压时间
                map.put("is_qualified", tvSix.getText().toString());
                map.put("is_app", "1");
                getPresenter().insertOrUpdateAppTestPressure(userBean.getStaff_token(), map);
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
    public OrderPresenter createPresenter() {
        return new OrderPresenter(getApp());
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
        ok.setEnabled(true);
        ToastUtil.showToast(context.getApplicationContext(), e.getMessage());

    }

    @Override
    public void OnGetTestPressureList(List<PressurePageBean> data,String total) {

    }

    @Override
    public void onInsertOrUpdateTestPressure(String data) {


    }

    @Override
    public void OnGetAppTestPressureList(List<PressurePageBean> data,String total) {

    }

    @Override
    public void onInsertOrUpdateAppTestPressure(String data) {
        ToastUtil.showToast(context.getApplicationContext(), "数据上传成功");
        EventBus.getDefault().post(new FirstEvent("close_ble_list"));
        finish();
    }

    @Override
    public void onGetMemoryList(String type, List<MemoryBean> data) {

    }
}
