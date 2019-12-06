package com.akan.qf.mvp.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.akan.qf.R;
import com.akan.qf.bean.ContributeClassBean;
import com.akan.qf.bean.UserBean;
import com.akan.qf.bean.staffGroupBeans;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.presenter.treeview.ChooseDepartmentPresenter;
import com.akan.qf.mvp.view.treeview.IChooseDepartmentView;
import com.akan.qf.util.SpSingleInstance;
import com.akan.qf.util.ToastUtil;
import com.akan.qf.view.DialogLoadding;
import com.akan.qf.view.tree.Node;
import com.akan.qf.view.tree.SimpleTreeAdapter;
import com.akan.qf.view.tree.TreeListViewAdapter;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by admin on 2018/7/4.
 */

public class ChooseDepartmentFilterFragmrnt extends BaseFragment<IChooseDepartmentView, ChooseDepartmentPresenter> implements IChooseDepartmentView {
    @BindView(R.id.ivLeft)
    ImageView ivLeft;
    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.ivRight)
    ImageView ivRight;
    @BindView(R.id.tvRight)
    TextView tvRight;
    @BindView(R.id.lv_tree)
    ListView lvTree;
    Unbinder unbinder;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private TreeListViewAdapter mAdapter;
    protected List<Node> mDatas = new ArrayList<>();
    private String type;
    private DialogLoadding dialogLoadding;

    public static ChooseDepartmentFilterFragmrnt newInstance(String type) {
        Bundle args = new Bundle();
        ChooseDepartmentFilterFragmrnt fragment = new ChooseDepartmentFilterFragmrnt();
        fragment.type = type;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_choose_department;
    }

    @Override
    public void initUI() {
        dialogLoadding = new DialogLoadding(context);
        dialogLoadding.showDialog();
        tvTitle.setText(R.string.choose_department);
        mAdapter = new SimpleTreeAdapter(lvTree, context,
                mDatas, 1, R.drawable.tree_ex, R.drawable.tree_ec);
        lvTree.setAdapter(mAdapter);
    }

    @Override
    public void initData() {
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        map.put("staff_id", userBean.getStaff_id());
        getPresenter().getStaffGroupTreeByStaff(userBean.getStaff_token(), map);
    }

    @Override
    public void onGetStaffGroupTreeByStaff(List<staffGroupBeans> data) {
        if (data.size() > 0) {
            toSaveList(data);
        }
        mAdapter = new SimpleTreeAdapter(lvTree, context, mDatas, 0, R.drawable.tree_ex, R.drawable.tree_ec);
        lvTree.setAdapter(mAdapter);
        dialogLoadding.closeDialog();

    }

    //递归遍历添加数据 group_type 0公司  1部门 2岗位
    private void toSaveList(List<staffGroupBeans> data) {
        for (int i = 0; i < data.size(); i++) {
            if (!"2".equals(data.get(i).getGroup_type())) {
                mDatas.add(new Node(data.get(i).getGroup_id(), data.get(i).getParent_id(), data.get(i).getGroup_name(), data.get(i).getGroup_uuid()));
                toSaveList(data.get(i).getStaffGroupBeans());
            }
        }
    }

    @Override
    public void OnGetContributeClassTree(List<ContributeClassBean> data) {

    }

    @OnClick({R.id.ivLeft, R.id.ok})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                getActivity().getSupportFragmentManager().popBackStackImmediate();
                break;
            case R.id.ok:
                StringBuilder sb = new StringBuilder();
                StringBuilder sbuu = new StringBuilder();
                StringBuilder sbid = new StringBuilder();
                //获取排序过的nodes
                //如果不需要刻意直接用 mDatas既可
                final List<Node> allNodes = mAdapter.getAllNodes();
                for (int i = 0; i < allNodes.size(); i++) {
                    if (allNodes.get(i).isChecked()) {
                        if (TextUtils.isEmpty(sb)) {
                            sb.append(allNodes.get(i).getName());
                            sbuu.append(allNodes.get(i).getGroup_uuid());
                            sbid.append(allNodes.get(i).getId());
                        }
                    }
                }
                if (TextUtils.isEmpty(sb)) {
                    ToastUtil.showToast(context.getApplicationContext(), getString(R.string.please_choose_department));
                    return;
                }
                onChooseClickListener.onChoose(sb.toString(), sbuu.toString());
                getFragmentManager().popBackStackImmediate();
                break;
        }
    }

    public  interface OnChooseClickListener {
        void onChoose(String name, String uuid);
    }

    private OnChooseClickListener onChooseClickListener;

    public void setOnChooseClickListener(OnChooseClickListener onChooseClickListener) {
        this.onChooseClickListener = onChooseClickListener;
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        ToastUtil.showToast(context.getApplicationContext(), e.getMessage());
    }

    @Override
    public ChooseDepartmentPresenter createPresenter() {
        return new ChooseDepartmentPresenter(getApp());
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
