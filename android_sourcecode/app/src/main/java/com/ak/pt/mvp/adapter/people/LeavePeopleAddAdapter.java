package com.ak.pt.mvp.adapter.people;

import android.content.Context;
import androidx.annotation.LayoutRes;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ak.pt.R;
import com.ak.pt.bean.LeavePerpleBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

public class LeavePeopleAddAdapter extends RecyclerArrayAdapter<LeavePerpleBean> {

    private String type;

    public LeavePeopleAddAdapter(Context context, List<LeavePerpleBean> objects, String type) {
        super(context, objects);
        this.type = type;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new LeavePeopleAddAdapter.MyImageHolder(parent, R.layout.item_people_leave_add);
    }


    public class MyImageHolder extends BaseViewHolder<LeavePerpleBean> {


        private TextView tvDelete, tvTop, tvOne, tvTwo;

        public MyImageHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, res);
            tvDelete = $(R.id.tvDelete);
            tvOne = $(R.id.tvOne);
            tvTwo = $(R.id.tvTwo);
            tvTop = $(R.id.tvTop);


        }

        @Override
        public void setData(final LeavePerpleBean data) {
            switch (type) {
                case "0":
                    tvTop.setText("文员信息" + (getDataPosition() + 1));
                    break;
                case "1":
                    tvTop.setText("试压工信息" + (getDataPosition() + 1));
                    break;

            }


            tvOne.setText(data.getStaff_name());
            tvTwo.setText(data.getTel());

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

            tvOne.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onDeleteClick.onChooseClick(getDataPosition());

                }
            });


        }
    }

    public interface OnDeleteClick {

        void onDeleteClick(int data);

        void onChooseClick(int position);

        void onAddClick();

    }

    private OnDeleteClick onDeleteClick;

    public void setOnDeleteClick(OnDeleteClick onDeleteClick) {
        this.onDeleteClick = onDeleteClick;
    }


}
