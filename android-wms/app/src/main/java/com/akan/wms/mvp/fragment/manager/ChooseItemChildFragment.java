package com.akan.wms.mvp.fragment.manager;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.akan.wms.R;
import com.akan.wms.bean.FirstEvent;
import com.akan.wms.bean.ItemWhQohBean;
import com.akan.wms.mvp.adapter.home.ChooseItemChildAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ChooseItemChildFragment extends DialogFragment {


    Unbinder unbinder;
    @BindView(R.id.tvClear)
    TextView tvClear;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.ok)
    TextView ok;
    @BindView(R.id.tvChooseNum)
    TextView tvChooseNum;
    @BindView(R.id.ivLog)
    ImageView ivLog;


    private List<ItemWhQohBean> list;
    private ChooseItemChildAdapter adapter;
    private String chooseS;


    public static ChooseItemChildFragment newInstance(List<ItemWhQohBean> list, String s) {
        Bundle args = new Bundle();
        ChooseItemChildFragment fragment = new ChooseItemChildFragment();
        fragment.list = list;
        fragment.chooseS = s;

        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_choose_more_child, container, false);
        unbinder = ButterKnife.bind(this, view);
        setLayout();
        initViews();
        return view;
    }

    private void setLayout() {
        Window window = getDialog().getWindow();
        window.getDecorView().setPadding(0, 0, 0, 0);
        window.setGravity(Gravity.BOTTOM);
        window.setBackgroundDrawableResource(R.color.black_transparent);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        lp.height = ViewGroup.LayoutParams.MATCH_PARENT;
        lp.dimAmount = 0;
        window.setAttributes(lp);
    }


    private void initViews() {
        tvChooseNum.setText(chooseS);
        adapter = new ChooseItemChildAdapter(getContext(), list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        adapter.setOnChangeClickListener(new ChooseItemChildAdapter.OnChangeClickListener() {
            @Override
            public void delete(int position) {
                adapter.remove(position);
                if (adapter.getAllData().size() <= 0) {
                    EventBus.getDefault().post(new FirstEvent("clear"));
                    dismiss();
                    return;
                }
                tvChooseNum.setText("已选择：" + adapter.getAllData().size());

            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tvClear, R.id.ok, R.id.ivLog})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivLog:
                onclickListener.onOk(adapter.getAllData(), "1");
                dismiss();
                break;
            case R.id.tvClear:
                EventBus.getDefault().post(new FirstEvent("clear"));
                dismiss();
                break;
            case R.id.ok:
                onclickListener.onOk(adapter.getAllData(), "2");
                dismiss();
                break;
        }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);

    }

    public interface OnOkChangeclickListener {
        void onOk(List<ItemWhQohBean> list, String type);
    }

    private OnOkChangeclickListener onclickListener;

    public void setAddOnclickListener(OnOkChangeclickListener onOnclickListener) {
        this.onclickListener = onOnclickListener;
    }

}
