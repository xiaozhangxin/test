package com.akan.wms.mvp.fragment.mine;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.FirstEvent;
import com.akan.wms.mvp.base.SimpleFragment;
import com.akan.wms.util.DataCleanManager;
import com.akan.wms.view.CustomDialog;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SettingFragment extends SimpleFragment {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.tvPersonInfo)
    TextView tvPersonInfo;
    @BindView(R.id.tvAboutUs)
    TextView tvAboutUs;
    @BindView(R.id.tvClear)
    TextView tvClear;
    @BindView(R.id.tvSaftware)
    TextView tvSaftware;
    @BindView(R.id.tvSuggest)
    TextView tvSuggest;
    Unbinder unbinder;
    @BindView(R.id.cacheSize)
    TextView cacheSize;
    @BindView(R.id.exit)
    Button exit;

    public static SettingFragment newInstance() {
        Bundle args = new Bundle();
        SettingFragment fragment = new SettingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_setting;
    }

    @Override
    public void initUI() {
        tvTitle.setText("设置");
        try {
            cacheSize.setText(DataCleanManager.getTotalCacheSize(context));
        } catch (Exception e) {
            e.printStackTrace();
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

    @OnClick({R.id.tvPersonInfo, R.id.tvAccount, R.id.tvAboutUs, R.id.tvClear, R.id.tvPwd, R.id.tvSaftware, R.id.tvSuggest, R.id.ivLeft, R.id.exit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.tvPersonInfo:
                startPersonalnfoFragment();
                break;
            case R.id.tvAccount:
                startAccountFragment();
                break;
            case R.id.tvPwd:
                startChangePwdFragment();
                break;
            case R.id.tvAboutUs:
                break;
            case R.id.tvClear:
                DataCleanManager.clearAllCache(context);
                try {
                    cacheSize.setText(DataCleanManager.getTotalCacheSize(getContext()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.tvSaftware:
                startSaftwareFragment();
                break;
            case R.id.tvSuggest:
               startSuggestFragment();
                break;
            case R.id.exit:
                exit();
                break;
        }
    }

    private void exit() {
        final CustomDialog.Builder builder = new CustomDialog.Builder(context);
        builder.setMessage(getString(R.string.quit));
        builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                exitLogin();
                EventBus.getDefault().post(new FirstEvent("3"));
                dialog.dismiss();
            }
        });
        builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.onCreate().show();
    }
}
