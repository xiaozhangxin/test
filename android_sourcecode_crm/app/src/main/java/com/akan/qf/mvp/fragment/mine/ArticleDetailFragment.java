package com.akan.qf.mvp.fragment.mine;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.akan.qf.Constants;
import com.akan.qf.R;
import com.akan.qf.bean.ContributeAuditBeans;
import com.akan.qf.bean.ContributeBean;
import com.akan.qf.bean.ContributeCommentBeansBean;
import com.akan.qf.bean.FilterBean;
import com.akan.qf.bean.FirstEventFilter;
import com.akan.qf.bean.NoticeBean;
import com.akan.qf.bean.PermissionsBean;
import com.akan.qf.bean.UserBean;
import com.akan.qf.mvp.adapter.ImageShowAdapter;
import com.akan.qf.mvp.adapter.mine.CommentListAdapter;
import com.akan.qf.mvp.base.BaseFragment;
import com.akan.qf.mvp.fragment.ReviewFragment;
import com.akan.qf.mvp.fragment.adaily.DailyDetailFragment;
import com.akan.qf.mvp.presenter.mine.ArticlePresenter;
import com.akan.qf.mvp.view.mine.IArticleView;
import com.akan.qf.util.SpSingleInstance;
import com.akan.qf.util.ToastUtil;
import com.akan.qf.view.TopMiddlePopup;
import com.bumptech.glide.Glide;
import com.king.base.util.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by admin on 2018/11/26.
 */

public class ArticleDetailFragment extends BaseFragment<IArticleView, ArticlePresenter> implements IArticleView {


    @BindView(R.id.ivLeft)
    TextView ivLeft;
    @BindView(R.id.line)
    View line;
    @BindView(R.id.tvTittle)
    TextView tvTittle;
    @BindView(R.id.ivAvatar)
    CircleImageView ivAvatar;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvNum)
    TextView tvNum;
    @BindView(R.id.tvTime)
    TextView tvTime;
    @BindView(R.id.tvContent)
    TextView tvContent;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.commentRecycleView)
    RecyclerView commentRecycleView;
    @BindView(R.id.etComment)
    EditText etComment;
    Unbinder unbinder;
    @BindView(R.id.tvCommentNum)
    TextView tvCommentNum;
    @BindView(R.id.tvSend)
    TextView tvSend;
    @BindView(R.id.tvMore)
    TextView tvMore;
    @BindView(R.id.lineOne)
    View lineOne;
    @BindView(R.id.annex)
    RelativeLayout annex;
    @BindView(R.id.tvAssessTittle)
    TextView tvAssessTittle;
    @BindView(R.id.tvAssess)
    TextView tvAssess;
    @BindView(R.id.llAddLine)
    View llAddLine;

    private List<ContributeCommentBeansBean> list;
    private CommentListAdapter adapter;
    private Map<String, String> map = new HashMap<>();
    private UserBean userBean;
    private String article_id;
    private ContributeBean contributeBean;

    private PermissionsBean permissionsBean;

    public static ArticleDetailFragment newInstance(String id, PermissionsBean permissionsBean) {
        Bundle args = new Bundle();
        ArticleDetailFragment fragment = new ArticleDetailFragment();
        fragment.article_id = id;
        fragment.permissionsBean = permissionsBean;
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getRootViewId() {
        return R.layout.fragment_article_detail;
    }

    @Override
    public void initUI() {
        list = new ArrayList<>();
        commentRecycleView.setLayoutManager(new LinearLayoutManager(context));
        commentRecycleView.setNestedScrollingEnabled(false);
        adapter = new CommentListAdapter(context, list);
        commentRecycleView.setAdapter(adapter);

    }

    @Override
    public void initData() {

    }

    @Override
    public void onResume() {
        super.onResume();
        userBean = SpSingleInstance.getSpSingleInstance().getUserBean();
        refresh();
    }

    private void refresh() {
        map.clear();
        map.put("id", article_id);
        getPresenter().getAreaContributeDetail(userBean.getStaff_token(), map);
    }

    private String appOperation;
    private String orderStaffId;//制单人id

    @Override
    public void OnGetAreaContributeDetail(ContributeBean data) {
        //本人是否审核过得状态 isAudio ture为已审阅
        boolean isAudio = false;
        List<ContributeAuditBeans> auditBeans = data.getContributeAuditBeans();
        if (auditBeans.size() > 0) {
            for (int i = 0; i < auditBeans.size(); i++) {
                if (auditBeans.get(i).getStaff_id().equals(userBean.getStaff_id())) {
                    isAudio = true;
                }
            }
        }
        orderStaffId = data.getStaff_id();
        //appOperation 0新增 1编辑 2删除 3审核
        appOperation = permissionsBean.getApp_operation();
        String[] strings = appOperation.split(",");
        switch (data.getState()) {
            case "wait_audit":
                if (data.getStaff_id().equals(userBean.getStaff_id())) {
                    if (isHave("2", strings)) {
                        tvMore.setVisibility(View.VISIBLE);
                        tvMore.setText(R.string.more);
                    } else {
                        tvMore.setVisibility(View.VISIBLE);
                        tvMore.setText(R.string.edit);
                    }
                } else {
                    showRight(strings);
                }
                etComment.setVisibility(View.GONE);
                tvSend.setVisibility(View.GONE);
                break;
            case "accept":
                if (data.getStaff_id().equals(userBean.getStaff_id())) {
                    tvMore.setVisibility(View.GONE);
                } else {
                    if (isHave("3", strings)) {
                        if (!isAudio) {
                            tvMore.setVisibility(View.VISIBLE);
                            tvMore.setText(R.string.review);
                        } else {
                            tvMore.setVisibility(View.GONE);
                        }
                    } else {
                        tvMore.setVisibility(View.GONE);
                    }
                }
                etComment.setVisibility(View.VISIBLE);
                tvSend.setVisibility(View.VISIBLE);
                break;
        }

        contributeBean = data;
        tvTittle.setText(data.getTitle());
        tvContent.setText(data.getContent());
        tvNum.setText("浏览记录 " + data.getRead_count());
        tvTime.setText(data.getCreate_time());
        Glide.with(context)
                .load(Constants.BASE_URL + data.getHead_img())
                .error(R.drawable.error_img)
                .into(ivAvatar);
        tvName.setText(data.getStaff_name());
        String files = data.getFiles();
        mFiles = files;
        if (!TextUtils.isEmpty(files)) {
            annex.setVisibility(View.VISIBLE);
            llAddLine.setVisibility(View.VISIBLE);
            lineOne.setVisibility(View.VISIBLE);


        }
        List<ContributeAuditBeans> contributeAuditBeans = data.getContributeAuditBeans();
        if (contributeAuditBeans.size() > 0) {
            tvAssess.setVisibility(View.VISIBLE);
            tvAssessTittle.setVisibility(View.VISIBLE);
            String mAudit = "";
            for (int i = 0; i < contributeAuditBeans.size(); i++) {
                mAudit = mAudit + contributeAuditBeans.get(i).getStaff_name() + ":"
                        + contributeAuditBeans.get(i).getAudit_content() + "\n";
            }
            tvAssess.setText(mAudit);
        } else {
            tvAssess.setVisibility(View.GONE);
            tvAssessTittle.setVisibility(View.GONE);
        }

        List<ContributeCommentBeansBean> commentList = data.getContributeCommentBeans();
        tvCommentNum.setText("评论(" + commentList.size() + ")");
        if (commentList.size() > 0) {
            adapter.clear();
            adapter.addAll(commentList);
            adapter.notifyDataSetChanged();
        }

    }

    //右上角显示
    private void showRight(String[] strings) {
        if (isHave("1", strings) && isHave("2", strings) && isHave("3", strings)) {
            tvMore.setVisibility(View.VISIBLE);
            tvMore.setText(R.string.more);
        } else if (isHave("1", strings) && isHave("2", strings) && !isHave("3", strings)) {
            tvMore.setVisibility(View.VISIBLE);
            tvMore.setText(R.string.more);
        } else if (isHave("1", strings) && isHave("3", strings) && !isHave("2", strings)) {
            tvMore.setVisibility(View.VISIBLE);
            tvMore.setText(R.string.more);
        } else if (isHave("2", strings) && isHave("3", strings) && !isHave("1", strings)) {
            tvMore.setVisibility(View.VISIBLE);
            tvMore.setText(R.string.more);
        } else if (isHave("1", strings) && !isHave("2", strings) && !isHave("3", strings)) {
            tvMore.setVisibility(View.VISIBLE);
            tvMore.setText(R.string.edit);
        } else if (isHave("2", strings) && !isHave("1", strings) && !isHave("3", strings)) {
            tvMore.setVisibility(View.VISIBLE);
            tvMore.setText(R.string.delete);
        } else if (isHave("3", strings) && !isHave("1", strings) && !isHave("2", strings)) {
            tvMore.setVisibility(View.VISIBLE);
            tvMore.setText(R.string.review);
        } else if (!isHave("1", strings) && !isHave("2", strings) && !isHave("3", strings)) {
            tvMore.setVisibility(View.GONE);
        }

    }

    //数组钟是否包含某元素
    public boolean isHave(String index, String[] split) {
        for (int i = 0; i < split.length; i++) {
            if (index.equals(split[i])) {
                return true;
            }
        }
        return false;
    }

    private String mFiles = "";

    @OnClick({R.id.ivLeft, R.id.tvSend, R.id.tvMore, R.id.annex})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivLeft:
                finish();
                break;
            case R.id.annex:
                if (!TextUtils.isEmpty(mFiles)) {
                    List<String> fileList = new ArrayList<>();
                    String[] split = mFiles.split(",");
                    for (int i = 0; i < split.length; i++) {
                        if (!TextUtils.isEmpty(split[i])) {
                            fileList.add(split[i]);
                        }
                    }
                    startNoticeFileFragment(new ArrayList<NoticeBean.NoticeFileBeans>(), "", fileList, "0");
                }

                break;
            case R.id.tvMore:
                switch (tvMore.getText().toString()) {
                    case "更多":
                        if (TextUtils.isEmpty(orderStaffId) || TextUtils.isEmpty(appOperation)) {
                            return;
                        }
                        moreOperation(orderStaffId, appOperation);
                        break;
                    case "审阅":
                        startDayReviewFragment();
                        break;
                    case "删除":
                        deleteDoc();
                        break;
                    case "编辑":
                        startArticleAddFragment("1", "", "", contributeBean, permissionsBean);
                        break;
                }
                break;
            case R.id.tvSend:
                String s = etComment.getText().toString();
                if (TextUtils.isEmpty(s)) {
                    ToastUtil.showToast(context.getApplicationContext(), "请输入评论内容");
                    return;
                }
                map.clear();
                map.put("contribute_id", article_id);
                map.put("staff_id", userBean.getStaff_id());
                map.put("content", s);
                map.put("is_select", "0");
                map.put("is_app", "1");
                map.put("operation", "3");
                map.put("module_id", permissionsBean.getMenu_id());
                getPresenter().insertContributeComment(userBean.getStaff_token(), map);
                break;
        }
    }

    //更多显示的操作  //appOperation  1编辑 2删除 3审核
    private void moreOperation(String orderStaffId, String appOperation) {
        if (orderStaffId.equals(userBean.getStaff_id())) {//自己的单据
            showList(new String[]{"编辑", "删除"});
        } else {
            String[] strings = appOperation.split(",");
            if (isHave("1",strings) && isHave("2", strings) && isHave("3", strings)) {
                showList(new String[]{"编辑", "删除", "审阅"});
            } else if (isHave("1",strings) && isHave("2", strings) && !isHave("3", strings)) {
                showList(new String[]{"编辑", "删除"});
            } else if (isHave("1",strings) && isHave("3", strings) && !isHave("2", strings)) {
                showList(new String[]{"编辑", "审阅"});
            } else if (isHave("2", strings) && isHave("3", strings) && !isHave("1",strings)) {
                showList(new String[]{"删除", "审阅"});
            }
        }
    }

    private AlertDialog alertDialog1;

    public void showList(final String[] items) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (items[i]) {
                    case "编辑":
                        startArticleAddFragment("1", "", "", contributeBean, permissionsBean);
                        break;
                    case "删除":
                        deleteDoc();
                        break;
                    case "审阅":
                        startDayReviewFragment();
                        break;
                }

                alertDialog1.dismiss();
            }


        });
        alertDialog1 = alertBuilder.create();
        alertDialog1.show();
    }

    //单据删除
    private void deleteDoc() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(R.string.delete_article);
        builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                map.clear();
                map.put("id", article_id);
                map.put("is_select", "0");
                map.put("is_app", "1");
                map.put("operation", "2");
                map.put("module_id", permissionsBean.getMenu_id());
                getPresenter().deleteAreaContribute(userBean.getStaff_token(), map);
            }
        });
        builder.setNegativeButton(getString(R.string.cancel), null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    //审阅
    private void startDayReviewFragment() {
        ReviewFragment fragment = ReviewFragment.newInstance("0");
        fragment.setOnReviewClickListener(new ReviewFragment.OnReviewClickListener() {
            @Override
            public void ok(String content) {
                map.clear();
                map.put("staff_id", userBean.getStaff_id());
                map.put("staff_name", userBean.getStaff_name());
                map.put("contribute_id", article_id);
                map.put("audit_content", content);
                map.put("is_select", "0");
                map.put("is_app", "1");
                map.put("operation", "3");
                map.put("module_id", permissionsBean.getMenu_id());
                getPresenter().auditAreaContribute(userBean.getStaff_token(), map);
            }
        });

        fragment.show(getFragmentManager(), DailyDetailFragment.class.getSimpleName());
    }

    @Override
    public void OnInsertContributeComment(String data) {
        ToastUtil.showToast(context.getApplicationContext(), data);
        etComment.setText("");
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        refresh();
    }

    @Override
    public void OnDeleteAreaContribute(String data) {
        EventBus.getDefault().post(new FirstEventFilter("article_delete"));
        ToastUtil.showToast(context.getApplicationContext(), data);
        finish();
    }


    @Override
    public void OnAauditAreaContribute(String data) {
        ToastUtil.showToast(context.getApplicationContext(), data);
        refresh();
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
    public void OnGetAreaContributeList(List<ContributeBean> data, String total) {

    }

    @Override
    public void onUploadFiles(String[] data) {

    }

    @Override
    public void OnInsertOrUpdateAreaContribute(String data) {

    }


    @Override
    public ArticlePresenter createPresenter() {
        return new ArticlePresenter(getApp());
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

