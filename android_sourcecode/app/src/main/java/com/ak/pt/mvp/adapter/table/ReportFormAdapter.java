package com.ak.pt.mvp.adapter.table;

import android.content.Context;
import androidx.annotation.LayoutRes;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ak.pt.R;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.util.List;

/**
 * Created by admin on 2019/5/31.
 */

public class ReportFormAdapter extends RecyclerArrayAdapter<String> {
    public ReportFormAdapter(Context context, List<String> list) {
        super(context, list);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent, viewType);
    }

    public class ViewHolder extends BaseViewHolder<String> {
        private TextView tvName;

        public ViewHolder(ViewGroup parent, @LayoutRes int res) {
            super(parent, R.layout.item_report_form);
            tvName = $(R.id.tvName);

        }

        @Override
        public void setData(String data) {
            super.setData(data);
            tvName.setText((getDataPosition()+1)+"、"+data);
        }
    }


}

