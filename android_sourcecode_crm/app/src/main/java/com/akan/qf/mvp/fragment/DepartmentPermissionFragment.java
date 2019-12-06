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
import com.akan.qf.bean.DepartmentBean;
import com.akan.qf.bean.DepartmentEvent;
import com.akan.qf.bean.UserBean;
import com.akan.qf.bean.staffGroupBeans;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.presenter.ChoosePermissionPresenter;
import com.akan.qf.mvp.view.IChoosePermissionView;
import com.akan.qf.util.SpSingleInstance;
import com.akan.qf.util.ToastUtil;
import com.akan.qf.view.DialogLoadding;
import com.akan.qf.view.tree.Node;
import com.akan.qf.view.tree.SimpleTreeAdapter;
import com.akan.qf.view.tree.TreeListViewAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class DepartmentPermissionFragment extends BaseFragment<IChoosePermissionView, ChoosePermissionPresenter> implements IChoosePermissionView {
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
    private String permission;

    private String type;
    private DialogLoadding dialogLoadding;
    public static DepartmentPermissionFragment newInstance(String permission, String type) {
        Bundle args = new Bundle();
        DepartmentPermissionFragment fragment = new DepartmentPermissionFragment();
        fragment.permission = permission;
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
        map.put("is_app", "1");
        map.put("is_select", "1");
        map.put("operation", "1000");
        map.put("module_id", permission);
        getPresenter().getStaffGroupByDepartment(userBean.getStaff_token(), map);
    }

    @Override
    public void onGetStaffGroupByDepartment(List<staffGroupBeans> data) {
        if (data.size() > 0) {
            toSaveList(data);
        }

        mAdapter = new SimpleTreeAdapter(lvTree, context, mDatas, 0, R.drawable.tree_ex, R.drawable.tree_ec);
        lvTree.setAdapter(mAdapter);
        dialogLoadding.closeDialog();
    }

    //递归遍历添加数据
    private void toSaveList(List<staffGroupBeans> beansList) {
        if (beansList.size() > 0) {
            for (int m = 0; m < beansList.size(); m++) {
                mDatas.add(new Node(beansList.get(m).getGroup_id(), beansList.get(m).getParent_id(), beansList.get(m).getGroup_name(), beansList.get(m).getGroup_uuid(), beansList.get(m).getGroup_no()));
                toSaveList(beansList.get(m).getStaffGroupBeans());
            }

        }
    }


    @OnClick({R.id.ivLeft, R.id.ok})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.ok:
                StringBuilder groupName = new StringBuilder();
                StringBuilder groupId = new StringBuilder();
                StringBuilder groupUUID = new StringBuilder();
                StringBuilder departmentNo = new StringBuilder();
                StringBuilder sbName = new StringBuilder();
                sbName.append(",");
                //获取排序过的nodes
                //如果不需要刻意直接用 mDatas既可
                final List<Node> allNodes = mAdapter.getAllNodes();
                for (int i = 0; i < allNodes.size(); i++) {
                    if (allNodes.get(i).isChecked()) {
                        if (TextUtils.isEmpty(groupName)) {
                            groupName.append(allNodes.get(i).getName());
                            groupId.append(allNodes.get(i).getId());
                            groupUUID.append(allNodes.get(i).getGroup_uuid());
                            departmentNo.append(allNodes.get(i).getGroup_no());
                        }
                        sbName.append(allNodes.get(i).getId() + ",");
                    }
                }

                if (TextUtils.isEmpty(groupName)) {
                    ToastUtil.showToast(context.getApplicationContext(), getString(R.string.please_choose_department));
                    return;
                }
                if ("book".equals(type)) {
                    EventBus.getDefault().post(new DepartmentEvent("1", new DepartmentBean(groupName.toString(), sbName.toString())));
                } else {
                    EventBus.getDefault().post(new DepartmentEvent("1", new DepartmentBean(groupName.toString(), groupUUID.toString())));
                }
                finish();
                break;
        }
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public ChoosePermissionPresenter createPresenter() {
        return new ChoosePermissionPresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


}
