package com.ak.pt.mvp.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.ak.pt.R;
import com.ak.pt.bean.BookNameBean;
import com.ak.pt.bean.DepartmentBean;
import com.ak.pt.bean.DepartmentEvent;
import com.ak.pt.bean.FirstEventBook;
import com.ak.pt.bean.FirstEventNew;
import com.ak.pt.bean.FirstEventWorker;
import com.ak.pt.bean.UserBean;
import com.ak.pt.bean.staffGroupBeans;
import com.ak.pt.mvp.base.BaseFragment;
import com.ak.pt.mvp.presenter.ChoosePermissionPresenter;
import com.ak.pt.mvp.view.IChoosePermissionView;
import com.ak.pt.util.SpSingleInstance;
import com.ak.pt.util.ToastUtil;
import com.ak.pt.view.DialogLoading;
import com.ak.pt.view.tree.Node;
import com.ak.pt.view.tree.SimpleTreeAdapter;
import com.ak.pt.view.tree.TreeListViewAdapter;

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
    protected List<Node> mDatas = new ArrayList<>();
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
    private String permission;
    private DialogLoading dialogLoading;

    private String type;

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
        tvTitle.setText("选择部门");
        mAdapter = new SimpleTreeAdapter(lvTree, context,
                mDatas, 1, R.drawable.tree_ex, R.drawable.tree_ec);
        lvTree.setAdapter(mAdapter);
        dialogLoading = new DialogLoading(context);
        dialogLoading.showDialog();
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
        if (dialogLoading != null) {
            dialogLoading.closeDialog();
        }
    }

    private void toSaveList(List<staffGroupBeans> data) {
        for (int i = 0; i < data.size(); i++) {
            staffGroupBeans groupBean = data.get(i);
            mDatas.add(new Node(groupBean.getGroup_id(), groupBean.getParent_id(), groupBean.getGroup_name(), groupBean.getGroup_uuid(), groupBean.getGroup_no()));
            if (groupBean.getStaffGroupBeans() != null) {
                toSaveList(groupBean.getStaffGroupBeans());
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
                    ToastUtil.showToast(context.getApplicationContext(), "请选择部门");
                    return;
                }
                switch (type) {
                    case "book":
                        EventBus.getDefault().post(new FirstEventBook("book", new BookNameBean(sbName.toString(), groupName.toString())));
                        break;
                    case "mall":
                        EventBus.getDefault().post(new DepartmentEvent("1", new DepartmentBean(groupName.toString(), departmentNo.toString())));
                        break;
                    case "entry":
                        EventBus.getDefault().post(new FirstEventNew("entry", new DepartmentBean(groupName.toString(), groupId.toString())));
                        break;
                    case "leave":
                        EventBus.getDefault().post(new FirstEventNew("leave", new DepartmentBean(groupName.toString(), groupId.toString(), groupUUID.toString())));
                        break;
                    case "order_add":
                        EventBus.getDefault().post(new FirstEventWorker("orderAdd", new DepartmentBean(groupName.toString(), groupId.toString())));
                        break;
                    case "water":
                        EventBus.getDefault().post(new FirstEventBook("water", new BookNameBean(groupName.toString(), groupId.toString())));
                        break;
                    case "summary":
                        EventBus.getDefault().post(new FirstEventBook("department", new BookNameBean(groupName.toString(), groupId.toString())));
                        break;
                    case "complete"://试压记录(服务统计)
                    case "filter":
                    default:
                        EventBus.getDefault().post(new DepartmentEvent("1", new DepartmentBean(groupName.toString(), groupUUID.toString())));
                        break;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
