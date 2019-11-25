package com.ak.pt.mvp.fragment.water;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.ak.pt.R;
import com.ak.pt.bean.TwoChooseBean;
import com.ak.pt.mvp.adapter.water.TwoChooseAdapter;
import com.ak.pt.mvp.adapter.water.TwoChooseChildAdapter;
import com.ak.pt.util.ToastUtil;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.umeng.socialize.utils.DeviceConfig.context;


/**
 * Created by admin on 2019/5/23.
 */

public class TwoChooseFragment extends DialogFragment {


    Unbinder unbinder;
    @BindView(R.id.tvTittle)
    TextView tvTittle;
    @BindView(R.id.recycleView)
    RecyclerView recycleView;
    @BindView(R.id.recycleViewTwo)
    RecyclerView recycleViewTwo;
    private TwoChooseAdapter adapter;
    private ArrayList<TwoChooseBean> list;
    private TwoChooseChildAdapter adapterTwo;
    private ArrayList<String> listTwo;

    public static TwoChooseFragment newInstance(ArrayList<TwoChooseBean> list) {
        Bundle args = new Bundle();
        TwoChooseFragment fragment = new TwoChooseFragment();
        fragment.list = list;
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two_choose, container, false);
        setLayout();
        unbinder = ButterKnife.bind(this, view);

        recycleView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new TwoChooseAdapter(context, list);
        recycleView.setAdapter(adapter);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                for (int i = 0; i < adapter.getAllData().size(); i++) {
                    adapter.getItem(i).setCheck(false);
                }
                adapter.getItem(position).setCheck(true);
                adapter.notifyDataSetChanged();

                adapterTwo.clear();
                adapterTwo.addAll(adapter.getItem(position).getChildList());
                adapterTwo.notifyDataSetChanged();

            }
        });

        recycleViewTwo.setLayoutManager(new LinearLayoutManager(context));
        adapterTwo = new TwoChooseChildAdapter(context, list.get(0).getChildList());
        recycleViewTwo.setAdapter(adapterTwo);
        adapterTwo.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                for (int i = 0; i < adapterTwo.getAllData().size(); i++) {
                    adapterTwo.getItem(i).setCheck(false);
                }
                adapterTwo.getItem(position).setCheck(true);
                adapterTwo.notifyDataSetChanged();
            }
        });
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
       // lp.windowAnimations = R.style.BottomDialogAnimation;
        lp.dimAmount = 0;
        window.setAttributes(lp);
    }

    @OnClick({R.id.ivClose, R.id.ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivClose:
                dismiss();
                break;
            case R.id.ok:

                String s1="";
                String s2="";
                for (int i = 0; i < adapter.getAllData().size(); i++) {
                   if (adapter.getItem(i).isCheck()){
                       s1=adapter.getItem(i).getName();
                   }
                }
                for (int i = 0; i < adapterTwo.getAllData().size(); i++) {
                   if (adapterTwo.getItem(i).isCheck()){
                       s2=adapterTwo.getItem(i).getName();
                   }
                }
                if (TextUtils.isEmpty(s1)){
                        ToastUtil.showToast(getContext().getApplicationContext(),"请选择故障产品系列");
                    return;
                }   if (TextUtils.isEmpty(s2)){
                        ToastUtil.showToast(getContext().getApplicationContext(),"请选择故障产品型号");
                    return;
                }
                onOkClickListener.ok(s1,s2);
                dismiss();
                break;
        }
    }


    private OnOkClickListener onOkClickListener;

    public void setOnOkClickListener(OnOkClickListener onOkClickListener) {
        this.onOkClickListener = onOkClickListener;
    }

    public interface OnOkClickListener {
        void ok( String s1,String s2);
    }


/*
    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            dialog.getWindow().findViewById(R.id.design_bottom_sheet).setBackgroundResource(android.R.color.transparent);
        }
    }
*/


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        EventBus.getDefault().unregister(this);
        unbinder.unbind();
    }

/*    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new FixHeightBottomSheetDialog(getContext());
    }*/

}
