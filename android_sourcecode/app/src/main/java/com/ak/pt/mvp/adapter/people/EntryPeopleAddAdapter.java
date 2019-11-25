package com.ak.pt.mvp.adapter.people;

import android.content.Context;
import androidx.annotation.LayoutRes;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.ak.pt.R;
import com.ak.pt.bean.ClerkInfoListBean;
import com.ak.pt.mvp.adapter.area.PeopleAddAdapter;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class EntryPeopleAddAdapter extends RecyclerArrayAdapter<ClerkInfoListBean> {

    private String type;
    public EntryPeopleAddAdapter(Context context, List<ClerkInfoListBean> objects, String type) {
        super(context, objects);
        this.type=type;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new EntryPeopleAddAdapter.MyImageHolder(parent, R.layout.item_people_add);
    }

    public class MyImageHolder extends BaseViewHolder<ClerkInfoListBean> {

        private EditText tvTwo, tvOne;
        private TextView tvDelete, tvTop;

        public MyImageHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, res);
            tvDelete = $(R.id.tvDelete);
            tvOne = $(R.id.tvOne);
            tvTwo = $(R.id.tvTwo);
            tvTop = $(R.id.tvTop);


        }

        @Override
        public void setData(final ClerkInfoListBean data) {
            tvOne.setText(data.getInfo_name());
            tvTwo.setText(data.getInfo_tel());
            if (0 == getDataPosition()) {
                tvDelete.setText("添加");
                tvDelete.setTextColor(getContext().getResources().getColor(R.color.colorPrimary));
                tvDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onDeleteClick.onAddClick();
                    }
                });
            } else {
                tvDelete.setText("删除");
                tvDelete.setTextColor(getContext().getResources().getColor(R.color.img_color));
                tvDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onDeleteClick.onDeleteClick(getDataPosition());
                    }
                });
            }
            switch (type) {
                case "pressure":
                    tvTop.setText("试压工信息"+(getDataPosition()+1));
                    break;
                case "clerk":
                    tvTop.setText("文员信息"+(getDataPosition()+1));
                    break;
            }




            tvOne.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    String string = s.toString();
                    if (!TextUtils.isEmpty(string)) {
                        data.setInfo_name(string);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
            tvTwo.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    String string = s.toString();
                    if (!TextUtils.isEmpty(string)) {
                        data.setInfo_tel(string);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

        }
    }

    public interface OnDeleteClick {

        void onDeleteClick(int data);

        void onAddClick();

    }

    private PeopleAddAdapter.OnDeleteClick onDeleteClick;

    public void setOnDeleteClick(PeopleAddAdapter.OnDeleteClick onDeleteClick) {
        this.onDeleteClick = onDeleteClick;
    }


}
