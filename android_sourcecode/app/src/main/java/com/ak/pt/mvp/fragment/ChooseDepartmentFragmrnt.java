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
import com.ak.pt.bean.FirstEvent;
import com.ak.pt.bean.FirstEventBook;
import com.ak.pt.bean.UserBean;
import com.ak.pt.bean.WorkerBean;
import com.ak.pt.bean.staffGroupBeans;
import com.ak.pt.mvp.base.BaseFragment;
import com.ak.pt.mvp.presenter.ChoosePresenter;
import com.ak.pt.mvp.view.IChooseView;
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

/**
 * Created by admin on 2019/1/17.
 */

@Deprecated
public class ChooseDepartmentFragmrnt extends BaseFragment<IChooseView, ChoosePresenter> implements IChooseView {
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
    private DialogLoading dialogLoading;

    public static ChooseDepartmentFragmrnt newInstance(String type) {
        Bundle args = new Bundle();
        ChooseDepartmentFragmrnt fragment = new ChooseDepartmentFragmrnt();
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
        map.put("staff_id", userBean.getStaff_id());
        getPresenter().getStaffGroupTreeByStaff(userBean.getStaff_token(), map);
    }

    @Override
    public void OnGetWaterList(List<WorkerBean> data) {

    }

    @Override
    public void onGetStaffGroupTreeByStaff(List<staffGroupBeans> data) {
        if (data.size() > 0) {
            toSaveList(data);
        }
        mAdapter = new SimpleTreeAdapter(lvTree, context, mDatas, 0, R.drawable.tree_ex, R.drawable.tree_ec);
        lvTree.setAdapter(mAdapter);
        if (dialogLoading != null) {
            dialogLoading.closeDialog();
        }
    }

    //group_type 0公司 1部门 2经销商 3岗位
    private void toSaveList(List<staffGroupBeans> data) {
        for (int i = 0; i < data.size(); i++) {
            if (!"3".equals(data.get(i).getGroup_type())) {
                staffGroupBeans groupBean = data.get(i);
                mDatas.add(new Node(groupBean.getGroup_id(), groupBean.getParent_id(), groupBean.getGroup_name(),groupBean.getGroup_uuid(),groupBean.getGroup_no()));
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
                    case "complete"://试压记录(服务统计)
                    case "area"://区域试压量排行
                        EventBus.getDefault().post(new DepartmentEvent("1", new DepartmentBean(groupName.toString(), groupUUID.toString())));
                        break;
                    case "saleDate":
                        EventBus.getDefault().post(new FirstEvent(groupName.toString()));
                        break;
                    case "saleDateNew":
                        EventBus.getDefault().post(new FirstEvent("uuid_leave" + groupName.toString() + "+" + groupUUID.toString()));
                        break;
                    case "filter":
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
    public ChoosePresenter createPresenter() {
        return new ChoosePresenter(getApp());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
