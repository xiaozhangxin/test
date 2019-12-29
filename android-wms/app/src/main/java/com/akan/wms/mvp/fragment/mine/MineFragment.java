package com.akan.wms.mvp.fragment.mine;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.akan.wms.Constants;
import com.akan.wms.R;
import com.akan.wms.bean.AppVersionBean;
import com.akan.wms.bean.FirstEvent;
import com.akan.wms.bean.StaffOrgsBean;
import com.akan.wms.bean.UserBean;
import com.akan.wms.mvp.base.BaseFragment;
import com.akan.wms.mvp.presenter.LoginPresenter;
import com.akan.wms.mvp.view.ILoginView;
import com.akan.wms.util.SpSingleInstance;
import com.akan.wms.util.ToastUtil;
import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.content.Context.MODE_PRIVATE;

public class MineFragment extends BaseFragment<ILoginView, LoginPresenter> implements ILoginView {


    @BindView(R.id.view)
    View view;
    @BindView(R.id.tvSet)
    ImageView tvSet;
    @BindView(R.id.tvMsg)
    ImageView tvMsg;
    @BindView(R.id.llOne)
    LinearLayout llOne;
    @BindView(R.id.tvOne)
    TextView tvOne;
    @BindView(R.id.tvTwo)
    TextView tvTwo;
    @BindView(R.id.tvThree)
    TextView tvThree;
    @BindView(R.id.tvFour)
    TextView tvFour;
    @BindView(R.id.tvSetting)
    TextView tvSetting;
    Unbinder unbinder;
    @BindView(R.id.ivAvatar)
    CircleImageView ivAvatar;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvJob)
    TextView tvJob;
    @BindView(R.id.tvDepartment)
    TextView tvDepartment;
    @BindView(R.id.tvAddOne)
    TextView tvAddOne;
    private UserBean userBean;
    private Map<String, String> map = new HashMap<>();
    public static MineFragment newInstance() {
        Bundle args = new Bundle();
        MineFragment fragment = new MineFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public int getRootViewId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initUI() {
    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
    }

    @Override
    public void onResume() {
        super.onResume();
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        Glide.with(context)
                .load(Constants.BASE_URL + userBean.getHead_img())
                .error(R.drawable.error_img)
                .into(ivAvatar);

        tvName.setText(userBean.getStaff_name());
        //tvJob.setText(userBean.getJob_name());
        tvDepartment.setText(userBean.getOrg_name());
    }



    @Override
    public LoginPresenter createPresenter() {
        return new LoginPresenter(getApp());
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


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(FirstEvent event) {
        String msg = event.getMsg();
        switch (msg) {
            case "change_org":
                StaffOrgsBean bean = event.getmBean();
                SharedPreferences sp = context.getSharedPreferences("login", MODE_PRIVATE);
                String username = sp.getString("username", "");
                String password = sp.getString("pwd", "");
                map.clear();
                map.put("staff_account", username);
                map.put("staff_password", password);
                map.put("terminal_type", "app");
                map.put("org_id", bean.getId());
                getPresenter().getLogin(map);
                break;

        }

    }


    @OnClick({R.id.view, R.id.tvSet, R.id.tvMsg, R.id.llOne, R.id.tvOne, R.id.tvTwo, R.id.tvThree, R.id.tvFour, R.id.tvAddOne, R.id.tvSetting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.view:
                break;
            case R.id.tvSet:
                startSettingFragment();
                break;
            case R.id.tvMsg:
                //startMessageFragment();
                EventBus.getDefault().post(new FirstEvent("homeMessage"));
                break;
            case R.id.llOne:
                startPersonalnfoFragment();
                break;
            case R.id.tvOne:
                startPersonalnfoFragment();
                break;
            case R.id.tvAddOne:
                startChooseOrganizationFragment(userBean.getStaff_account(), userBean.getOrg_name());
                break;
            case R.id.tvTwo:
                startSuggestFragment();
                break;
            case R.id.tvThree:
                startSaftwareFragment();
                break;
            case R.id.tvFour:
                startShare();
                break;
            case R.id.tvSetting:
                startSettingFragment();
                break;
        }
    }

    private void startShare() {
        Intent textIntent = new Intent(Intent.ACTION_SEND);
        textIntent.setType("text/plain");
        textIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.download_link));
        startActivity(Intent.createChooser(textIntent, "分享"));
    }

    @Override
    public void onGetlogin(UserBean data) {
        ToastUtil.showToast(context.getApplicationContext(),"切换成功");
        saveUser(data);
        EventBus.getDefault().post(new FirstEvent("org_change"));
        onResume();
    }

    @Override
    public void queryStaffOrgs(List<StaffOrgsBean> data) {

    }

    @Override
    public void OnGetAppVersionDetail(AppVersionBean data) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        ToastUtil.showToast(context.getApplicationContext(),e.getMessage());

    }
}
