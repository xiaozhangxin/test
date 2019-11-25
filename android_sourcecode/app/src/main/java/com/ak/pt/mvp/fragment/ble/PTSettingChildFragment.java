package com.ak.pt.mvp.fragment.ble;

import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ak.pt.R;
import com.ak.pt.bean.FirstEventDrop;
import com.ak.pt.bean.ModuleListBean;
import com.ak.pt.bean.PressureDropBean;
import com.ak.pt.mvp.adapter.NumSetAdapter;
import com.ak.pt.mvp.base.SimpleFragment;
import com.ak.pt.util.keyback.FragmentBackHandler;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by admin on 2019/1/18.
 */

public class PTSettingChildFragment extends SimpleFragment implements FragmentBackHandler {

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
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.tv_save)
    TextView tvSave;
    private NumSetAdapter numAdapter;
    private PressureDropBean dropBean;

    public static PTSettingChildFragment newInstance(PressureDropBean dropBean) {
        Bundle args = new Bundle();
        PTSettingChildFragment fragment = new PTSettingChildFragment();
        fragment.dropBean = dropBean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_pt_setting_child;
    }

    @Override
    public void initUI() {
        tvTitle.setText("本地参数设置");
        List<ModuleListBean> moduleList = dropBean.getModuleList();
        for (int i=0;i<moduleList.size();i++){
            moduleList.get(i).setmState("1");
        }
        recycleView.setLayoutManager(new LinearLayoutManager(context));
        numAdapter = new NumSetAdapter(context, moduleList);
        recycleView.setAdapter(numAdapter);
        numAdapter.setmOnSetClickListener(new NumSetAdapter.OnSetClickListener() {

            @Override
            public void oneClick(int position) {
                List<ModuleListBean> allData = numAdapter.getAllData();
                for (int i = 0; i < allData.size(); i++) {
                    allData.get(i).setmState("1");
                }
                numAdapter.notifyDataSetChanged();
            }

            @Override
            public void twoClick(int position) {
                List<ModuleListBean> allData = numAdapter.getAllData();
                for (int i = 0; i < allData.size(); i++) {
                    allData.get(i).setmState("2");
                }
                numAdapter.notifyDataSetChanged();
            }

            @Override
            public void threeClick(int position) {
                List<ModuleListBean> allData = numAdapter.getAllData();
                for (int i = 0; i < allData.size(); i++) {
                    allData.get(i).setmState("3");
                }
                numAdapter.notifyDataSetChanged();
            }

            @Override
            public void changeKg(String mkg) {
                List<ModuleListBean> allData = numAdapter.getAllData();
                for (int i = 0; i < allData.size(); i++) {
                     allData.get(i).setModule_num(mkg);
                }
              //  numAdapter.notifyDataSetChanged();
            }
        });


    }

    @Override
    public void initData() {

    }



    @OnClick({R.id.ivLeft, R.id.tv_save})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_save:
                dropBean.setDrop_num(numAdapter.getItem(0).getModule_num());
                dropBean.setModuleList(numAdapter.getAllData());
                EventBus.getDefault().post(new FirstEventDrop("drop",dropBean));
                finish();
                break;
            case R.id.ivLeft:
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

    @Override
    public boolean onBackPressed() {
        return false;
    }
}
