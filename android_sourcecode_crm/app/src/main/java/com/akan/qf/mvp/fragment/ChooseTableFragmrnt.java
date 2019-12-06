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
import com.akan.qf.bean.ContributeBean;
import com.akan.qf.bean.ContributeClassBean;
import com.akan.qf.bean.ContributeClassBeansBean;
import com.akan.qf.bean.DepartmentBean;
import com.akan.qf.bean.DepartmentEvent;
import com.akan.qf.bean.FirstEvent;
import com.akan.qf.bean.PermissionsBean;
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
 * Created by admin on 2018/7/4.
 */

public class ChooseTableFragmrnt extends BaseFragment<IChooseDepartmentView, ChooseDepartmentPresenter> implements IChooseDepartmentView {
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
    private String table_id;
    private DialogLoadding dialogLoadding;

    private PermissionsBean permissionsBean;

    public static ChooseTableFragmrnt newInstance(String table_id, PermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        ChooseTableFragmrnt fragment = new ChooseTableFragmrnt();
        fragment.table_id = table_id;
        fragment.permissionsBean = permissionsBean;
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
        tvTitle.setText("选择目录");
        mAdapter = new SimpleTreeAdapter(lvTree, context,
                mDatas, 1, R.drawable.tree_ex, R.drawable.tree_ec);
        lvTree.setAdapter(mAdapter);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onResume() {
        super.onResume();
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        getPresenter().getContributeClassTree(userBean.getStaff_token(), map);
    }

    @Override
    public void onGetStaffGroupTreeByStaff(List<staffGroupBeans> data) {


    }

    @Override
    public void OnGetContributeClassTree(List<ContributeClassBean> data) {
        if (data.size() > 0) {
            toSaveList(data);
/*            for (int i = 0; i < data.size(); i++) {
                mDatas.add(new Node(data.get(i).getClass_id(), data.get(i).getParent_id(), data.get(i).getClass_name(), data.get(i).getClass_id()));
                List<ContributeClassBeansBean> contributeClassBeans = data.get(i).getContributeClassBeans();
                if (contributeClassBeans.size() > 0) {
                    for (int j = 0; j < contributeClassBeans.size(); j++) {
                        mDatas.add(new Node(contributeClassBeans.get(j).getClass_id(), contributeClassBeans.get(j).getParent_id(), contributeClassBeans.get(j).getClass_name(), contributeClassBeans.get(j).getClass_id()));
                        List<ContributeClassBeansBean> beans = contributeClassBeans.get(j).getContributeClassBeans();
                        if (beans.size() > 0) {
                            for (int m = 0; m < beans.size(); m++) {
                                mDatas.add(new Node(beans.get(m).getClass_id(), beans.get(m).getParent_id(), beans.get(m).getClass_name(), beans.get(m).getClass_id()));
                            }
                        }

                    }
                }

            }*/
        }
        mAdapter = new SimpleTreeAdapter(lvTree, context, mDatas, 0, R.drawable.tree_ex, R.drawable.tree_ec);
        lvTree.setAdapter(mAdapter);
        dialogLoadding.closeDialog();
    }

    private void toSaveList(List<ContributeClassBean> data) {
        for (int i = 0; i < data.size(); i++) {
            mDatas.add(new Node(data.get(i).getClass_id(), data.get(i).getParent_id(), data.get(i).getClass_name(), data.get(i).getClass_id()));
            toSaveList(data.get(i).getContributeClassBeans());
        }
    }

    @OnClick({R.id.ivLeft, R.id.ok})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.ok:
                StringBuilder sb = new StringBuilder();
                StringBuilder sbuu = new StringBuilder();
                //获取排序过的nodes
                //如果不需要刻意直接用 mDatas既可
                final List<Node> allNodes = mAdapter.getAllNodes();

                for (int i = 0; i < allNodes.size(); i++) {
                    if (allNodes.get(i).isChecked()) {
                        if (TextUtils.isEmpty(sb)) {
                            sb.append(allNodes.get(i).getName());
                            sbuu.append(allNodes.get(i).getGroup_uuid());
                        }
                    }
                }
                if (TextUtils.isEmpty(sb)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请选择目录");
                    return;
                }

                switch (table_id) {
                    case "0":
                        EventBus.getDefault().post(new FirstEvent("article_table" + sb.toString() + "+" + sbuu.toString()));
                        break;
                    case "1":
                        startArticleAddFragment("0", sbuu.toString(), sbuu.toString(), new ContributeBean(), permissionsBean);
                        break;
                    case "filter":
                        EventBus.getDefault().post(new DepartmentEvent("table", new DepartmentBean(sb.toString(), sbuu.toString())));
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
